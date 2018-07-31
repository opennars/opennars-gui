/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package org.opennars.gui.output.graph;

import automenta.vivisect.dimensionalize.FastOrganicLayout;
import automenta.vivisect.dimensionalize.HyperassociativeLayout;
import automenta.vivisect.graph.AnimatingGraphVis;
import automenta.vivisect.graph.GraphDisplay;
import automenta.vivisect.graph.GraphDisplays;
import automenta.vivisect.swing.NSlider;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.opennars.io.events.EventEmitter.EventObserver;
import org.opennars.io.events.Events.CyclesEnd;
import org.opennars.io.events.Events.ResetEnd;
import org.opennars.main.Nar;
import org.opennars.gui.output.graph.layout.CircleLayout;
import org.opennars.gui.util.DefaultGraphizer;
import org.opennars.gui.util.NARGraph;
import org.opennars.gui.graph.InheritanceGraph;
import org.opennars.gui.graph.ImplicationGraph;
import org.opennars.gui.output.graph.layout.SpiralLayout;
import org.opennars.LockedValueTypes.PortableDouble;
import org.jgrapht.Graph;

/**
 *
 * @author me
 */
public class NARGraphVis extends AnimatingGraphVis<Object,Object> implements EventObserver {
        
    
    final AtomicReference<Graph> displayedGraph = new AtomicReference();
    private final Nar nar;
    
    private final GraphDisplays displays;
    private NARGraphDisplay style;
    private GraphDisplay layout;
    private JPanel modePanelHolder;
    
    public interface GraphMode {
        Graph nextGraph();
        default void stop() {
        }
        
        JPanel newControlPanel();
    }

    public abstract class MinPriorityGraphMode implements GraphMode {
        float minPriority = 0;

        @Override
        public JPanel newControlPanel() {
            JPanel j = new JPanel(new FlowLayout(FlowLayout.LEFT));

            j.add(conceptPriSlider);
            return j;
        }
    }
    
    public final PortableDouble conceptPriorityThreshold = new PortableDouble(0.0);
    public final PortableDouble taskPriorityThreshold = new PortableDouble(0.1);
    public final PortableDouble nConcepts = new PortableDouble(0.004); //10000*0.004=40
    JTextField filterBox = new JTextField();
    NSlider conceptPriSlider = new NSlider(conceptPriorityThreshold, "ConcP", 0.0f, 1.0f);
    NSlider taskPriSlider = new NSlider(taskPriorityThreshold, "TaskP", 0.0f, 1.0f);
    NSlider nConceptsSlider = new NSlider(nConcepts, "number of Concepts: The maximum number of concepts (long slider for a good accuracy)", 0.0f, 1.0f);
    public class ConceptGraphMode extends MinPriorityGraphMode implements GraphMode {
        private boolean showBeliefs = false;    
        private boolean showQuestions = false;
        private boolean showTermContent = false;
        
        boolean showTaskLinks = false;
        boolean showTermLinks = true;

        @Override
        public Graph nextGraph() {
            return new NARGraph().add(nar, new NARGraph.ExcludeBelowPriority(minPriority), 
                    new DefaultGraphizer(showBeliefs, showBeliefs, showQuestions, showTermContent, 
                            0, showTermLinks, showTaskLinks, filterBox, conceptPriorityThreshold, taskPriorityThreshold, nConcepts, nar.narParameters));
        }

        @Override
        public JPanel newControlPanel() {
            JPanel j = super.newControlPanel();

            final JCheckBox termlinkEnable = new JCheckBox("TermLinks");
            termlinkEnable.setSelected(showTermLinks);
            termlinkEnable.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    showTermLinks = (termlinkEnable.isSelected());                
                    setUpdateNext();
                }
            });
            j.add(termlinkEnable);        

            final JCheckBox taskLinkEnable = new JCheckBox("TaskLinks");
            taskLinkEnable.setSelected(showTaskLinks);
            taskLinkEnable.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    showTaskLinks = (taskLinkEnable.isSelected());                
                    setUpdateNext();
                }
            });
            j.add(taskLinkEnable);

           /* final JCheckBox beliefsEnable = new JCheckBox("Beliefs");
            beliefsEnable.setSelected(showBeliefs);
            beliefsEnable.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    showBeliefs = (beliefsEnable.isSelected());
                    setUpdateNext();
                }
            });
            j.add(beliefsEnable);*/
            
            filterBox.setPreferredSize(new Dimension(100,20));
            j.add(filterBox);
            j.add(conceptPriSlider);
            j.add(taskPriSlider);
            j.add(nConceptsSlider);

            return j;
            
        }
        
    }
    public class InheritanceGraphMode extends MinPriorityGraphMode implements GraphMode {
        private InheritanceGraph ig;

        @Override
        public Graph nextGraph() {
            if (this.ig==null) {
                this.ig = new InheritanceGraph(nar, true, true, conceptPriorityThreshold);
                ig.start();
            }
            
            return ig;
        }        

        @Override
        public void stop() {
            if (ig!=null) {
                ig.stop();
                ig = null;
            }
        }
        
    }

    public class ImplicationGraphMode extends MinPriorityGraphMode implements GraphMode {
        private ImplicationGraph ig;

        @Override
        public Graph nextGraph() {
            if (this.ig==null) {
                this.ig = new ImplicationGraph(nar, true, true, conceptPriorityThreshold);
                ig.start();
            }
            
            return ig;
        }        

        @Override
        public void stop() {
            if (ig!=null) {
                ig.stop();
                ig = null;
            }
        }
        
    }
    
    public GraphMode mode = new ConceptGraphMode();
    
    boolean updateNextGraph = false;
            
    public NARGraphVis(Nar n) {
        super(null, new GraphDisplays());
        this.nar = n;
        this.displays = (GraphDisplays)getDisplay();
        NARGraphDisplay grap = new NARGraphDisplay(n);
        update(grap, new FastOrganicLayout());
    }
    
    public void update(NARGraphDisplay style, GraphDisplay layout) {
        this.style = style;
        this.layout = layout;
        displays.sequence.clear();
        displays.sequence.add(style);
        displays.sequence.add(layout);
        setUpdateNext();
    }

    @Override
    public void onVisible(boolean showing) {  
        nar.memory.event.set(this, showing, CyclesEnd.class, ResetEnd.class);        
        if (!showing) {
            mode.stop();
        }
    }

    @Override
    public void event(Class event, Object[] args) {
        if (event == CyclesEnd.class) {
            displayedGraph.set(nextGraph());
        }
        else if (event == ResetEnd.class) {
            displayedGraph.set(null);
        }
    }
        
            
    protected Graph nextGraph() {
        if (nar == null) return null;
        return mode.nextGraph();                
    }

    @Override
    public void setUpdateNext() {
        super.setUpdateNext();        
        updateNextGraph = true;
    }

    
    
    @Override
    public Graph<Object, Object> getGraph() {        
        if (displayedGraph == null)
            return null;
        
        
        if (updateNextGraph) {
            updateNextGraph = false;

            if (!nar.isRunning()) {
                //only update from here if Nar isnt running; otherwise a concurrency exception can occurr

                Graph ng = nextGraph();
                if (ng!=null)
                    displayedGraph.set(ng);            
            }
        }
        
        return displayedGraph.get();
    }
    

    
    public JPanel newLayoutPanel() {
        JPanel j = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JComboBox layoutSelect = new JComboBox();
        layoutSelect.addItem("Organic");
        layoutSelect.addItem("Hyperassociative");
        layoutSelect.addItem("Circle");       
        layoutSelect.addItem("Circle (Half)");
        layoutSelect.addItem("Spiral");
        
        //modeSelect.setSelectedIndex(cg.mode);
        layoutSelect.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                switch (layoutSelect.getSelectedIndex()) {
                    case 0:
                        update(style, new FastOrganicLayout());     
                        break;
                    case 1:
                        update(style, new HyperassociativeLayout());
                        break;
                    case 2:
                        update(style, new CircleLayout(nar, 0f, 1f, 50));     
                        break;
                    case 3:
                        update(style, new CircleLayout(nar, 0.25f, 0.75f, 75));     
                        break;
                    case 4:
                        update(style, new SpiralLayout(nar, 75));     
                        break;
                }
//cg.mode = modeSelect.getSelectedIndex();
                setUpdateNext();
            }
        });
        j.add(layoutSelect);
        return j;
    }
    
    public void setMode(GraphMode g) {
        if (this.mode!=null) {
            this.mode.stop(); //stop existing
        }
        
        this.mode = g;
        
        modePanelHolder.removeAll();
        modePanelHolder.add(mode.newControlPanel());
        modePanelHolder.doLayout();
    }
    
    public JPanel newGraphPanel() {
        JPanel j = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JComboBox modeSel = new JComboBox();
        modeSel.addItem("Concepts");
        modeSel.addItem("Inheritance");       
        modeSel.addItem("Implication");  
        //modeSelect.setSelectedIndex(cg.mode);
        modeSel.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                switch (modeSel.getSelectedIndex()) {
                    case 0:
                        setMode(new ConceptGraphMode());
                        break;
                    case 1:
                        setMode(new InheritanceGraphMode());
                        break;
                    case 2:
                        setMode(new ImplicationGraphMode());
                        break;

                }
                setUpdateNext();
            }
        });
        
        j.add(modeSel);
        
        modePanelHolder = new JPanel(new FlowLayout());
        modePanelHolder.add(mode.newControlPanel());
        
        j.add(modePanelHolder);
        return j;
    }
    
    public JPanel newStylePanel() {
        JPanel j = new JPanel(new FlowLayout(FlowLayout.LEFT));
        j.add(style.getControls());
        return j;
    }
}

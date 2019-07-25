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

import automenta.vivisect.Video;
import automenta.vivisect.graph.AbstractGraphVis;
import automenta.vivisect.graph.EdgeVis;
import automenta.vivisect.graph.GraphDisplay;
import automenta.vivisect.graph.VertexVis;
import automenta.vivisect.swing.NSlider;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import org.opennars.entity.Concept;
import org.opennars.entity.Sentence;
import org.opennars.entity.Task;
import org.opennars.entity.TaskLink;
import org.opennars.entity.TermLink;
import org.opennars.language.Term;
import org.opennars.gui.util.NARGraph;
import org.opennars.inference.TruthFunctions;
import org.opennars.main.Nar;
/**
 *
 * @author me
 */
public class NARGraphDisplay<V,E> implements GraphDisplay<V,E> {

    float maxNodeSize = 160f;
    
    float lineWidth = 4f;
    float nodeSize = 16f;
    int maxLabelLen = 99999;
    float nodeSpeed = 0.2f;
    float textSize = 1f;
    
    int defaultEdgeColor = Video.color(127,127,127,200);
    int defaultTextColor = Video.color(255,255,255,255);
    private Nar nar;
    
    public NARGraphDisplay(Nar nar) {
        this.nar = nar;
    }

    public NARGraphDisplay setTextSize(float textSize, int maxLabelLen) {
        this.textSize = textSize;
        this.maxLabelLen = maxLabelLen;
        return this;
    }
    
    
            

    @Override
    public void vertex(AbstractGraphVis<V, E> g, VertexVis<V, E> v) {
        float alpha = 0.9f;
        V o = v.getVertex();
        
        v.shape = Shape.Ellipse;

        float rad = 1f;
        
        if (o instanceof Sentence) {
            Sentence kb = (Sentence) o;
            if (kb.truth!=null)  {            
                float confidence = (float)kb.truth.getConfidence();
                alpha = 0.5f + 0.5f * confidence;
            }
        } else if (o instanceof Task) {
            Task ta = (Task) o;
            rad = 2.0f + ta.getPriority() * 2.0f;
            alpha = ta.getDurability();                
            v.shape = Shape.Rectangle;
        } else if (o instanceof Concept) {
            Concept co = (Concept) o;

            rad = (2 + 6 * co.budget.summary());
            
            
            if (!co.beliefs.isEmpty()) {
                float confidence = (float)co.beliefs.get(0).sentence.truth.getConfidence();
                alpha = 0.5f + 0.5f * confidence;
            }
        }
        
        Object x = o;
        if(!(x instanceof Concept)) {
            
            if(!(x instanceof Task)) {
                if (x instanceof Concept) x = ((Concept)o).getTerm();
                float hue = 0.0f;
                if (x instanceof Task)
                    hue = 0.4f;


                float brightness = 0.33f+0.66f*rad/9.0f;
                float saturation = 0.33f+0.66f*rad/9.0f;
                // brightness*=brightness;
                //saturation*=saturation;
                 v.color =  Video.colorHSB( hue, saturation, brightness, 0.25f+(0.75f*alpha) );
            } 
            else
            if(x instanceof Task){
                float rr=0.5f,gg=0.5f,bb=0.5f,aa=1.0f;

                Task t = (Task) o;
                if(t.sentence.truth!=null) {
                    float conf = (float)t.sentence.truth.getConfidence();
                    float freq = t.sentence.truth.getFrequency();
                    aa = 0.25f + conf * 0.75f;
                    float evidence = (float)TruthFunctions.c2w(conf, this.nar.narParameters);
                    float positive_evidence_in_0_1 = (float)TruthFunctions.w2c(evidence*freq, this.nar.narParameters);
                    float negative_evidence_in_0_1 = (float)TruthFunctions.w2c(evidence*(1.0f-freq), this.nar.narParameters);
                    rr = positive_evidence_in_0_1;
                    bb = negative_evidence_in_0_1;
                    gg = 0.0f;
                }

                Color HSB = new Color(rr,gg,bb,aa);
                float hsb[] = new float[3];
                Color.RGBtoHSB((int)(rr*255.0f), (int)(gg*255.0f), (int)(bb*255.0f), hsb);
                v.color =  Video.colorHSB( hsb[0], hsb[1], hsb[2], aa);
            }
        }
        else
        if(x instanceof Concept){
            Concept conc = ((Concept)o);
            float rr=0.5f,gg=0.5f,bb=0.5f,aa=1.0f;

            if(conc.beliefs.size()>0) {
                Sentence sent = conc.beliefs.get(0).sentence;
                float conf = (float)sent.truth.getConfidence();
                float freq = sent.truth.getFrequency();
                aa = 0.25f + conf * 0.75f;
                float evidence = (float)TruthFunctions.c2w(conf, this.nar.narParameters);
                float positive_evidence_in_0_1 = (float)TruthFunctions.w2c(evidence*freq, this.nar.narParameters);
                float negative_evidence_in_0_1 = (float)TruthFunctions.w2c(evidence*(1.0f-freq), this.nar.narParameters);
                rr = positive_evidence_in_0_1;
                bb = negative_evidence_in_0_1;
                gg = 0.0f;
                
            }
            Color HSB = new Color(rr,gg,bb,aa);
            float hsb[] = new float[3];
            Color.RGBtoHSB((int)(rr*255.0f), (int)(gg*255.0f), (int)(bb*255.0f), hsb);
            v.color =  Video.colorHSB( hsb[0], hsb[1], hsb[2], aa);
        }
         
       

        String label;
        if (o instanceof Concept) {
             label = ((Concept) o).term.toString();
         } else if (o instanceof Task) {
             label = ((Task)o).sentence.toString();
         } else {
             label = o.toString();
         }

         if (label.length() > maxLabelLen) {
             label = label.substring(0, maxLabelLen - 2) + "..";
         }
         
         v.label = label;         
         v.speed = nodeSpeed;
         v.radius = rad * nodeSize;
         v.textColor = defaultTextColor;
         v.textScale = textSize;
    }

    @Override
    public void edge(AbstractGraphVis<V, E> g, EdgeVis<V, E> e) {
   
        E edge = e.edge;
        

        int color = defaultEdgeColor;
        
        float thickness = lineWidth;
        if (edge instanceof NARGraph.TermLinkEdge) {
            TermLink t = ((NARGraph.TermLinkEdge)edge).getObject();
            float p = t.getPriority();            
            thickness = (1 + p) * lineWidth;            
            color = Video.color(255f * (0.5f + p*0.5f), 255f * (0.5f + p*0.5f), 125f, 255f * (0.5f + p*0.5f) );
        }
        if (edge instanceof NARGraph.TaskLinkEdge) {
            TaskLink t = ((NARGraph.TaskLinkEdge)edge).getObject();
            float p = t.targetTask.getPriority();            
            thickness = (1 + p) * lineWidth;
            color = Video.color(125f, 255f * (0.5f + p*0.5f), 125f, 255f * (0.5f + p*0.5f) );
        }
    
        e.color = color;
        e.thickness = thickness;
    }

    public JPanel getControls() {
        JPanel menu = new JPanel(new FlowLayout(FlowLayout.LEFT));

        NSlider nodeSize = new NSlider(this.nodeSize, 1, maxNodeSize) {
            @Override
            public void onChange(float v) {
                NARGraphDisplay.this.nodeSize = v;
            }
        };
        nodeSize.setPrefix("Node Size: ");
        nodeSize.setPreferredSize(new Dimension(80, 25));
        menu.add(nodeSize);
        
        NSlider edgeWidth = new NSlider(this.lineWidth, 0f, maxNodeSize/4f) {
            @Override public void onChange(float v) {
                lineWidth = v;
            }
        };
        edgeWidth.setPrefix("Line Thick: ");
        edgeWidth.setPreferredSize(new Dimension(80, 25));
        menu.add(edgeWidth);

        

        NSlider nodeSpeed = new NSlider(this.nodeSpeed, 0.001f, 0.99f) {
            @Override
            public void onChange(float v) {
                NARGraphDisplay.this.nodeSpeed = v;
            }
        };
        nodeSpeed.setPrefix("Speed: ");
        nodeSpeed.setPreferredSize(new Dimension(70, 25));
        menu.add(nodeSpeed);

        NSlider fontSize = new NSlider(this.textSize, 0f, 2f) {
            @Override
            public void onChange(float v) {
                NARGraphDisplay.this.textSize = v;
                //app.drawn = false;
            }
        };
        fontSize.setPrefix("Font: ");
        fontSize.setPreferredSize(new Dimension(70, 25));
        menu.add(fontSize);

        return menu;
    }

    @Override
    public boolean preUpdate(AbstractGraphVis<V, E> g) {
        return true;
    }

    @Override
    public boolean postUpdate(AbstractGraphVis<V, E> g) {
        return true;
    }
}

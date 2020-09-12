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
package org.opennars.gui.output;

import automenta.vivisect.Video;
import automenta.vivisect.swing.NPanel;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import org.opennars.io.events.EventEmitter.EventObserver;
import org.opennars.io.events.Events.CyclesEnd;
import org.opennars.io.events.Events.TaskAdd;
import org.opennars.io.events.Events.TaskRemove;
import org.opennars.main.Nar;
import org.opennars.entity.Concept;
import org.opennars.entity.Task;
import org.opennars.entity.TaskLink;
import org.opennars.entity.TruthValue;
import org.opennars.gui.WrapLayout;
import org.opennars.storage.Memory;

/**
 *
 * @author me
 */
public class TaskTree extends NPanel implements EventObserver, Runnable {

    long updatePeriodMS = 250;
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tasks");
    private DefaultTreeModel model;
    Map<Task, DefaultMutableTreeNode> nodes = new ConcurrentHashMap();
    private final JTree tree;
    private final Nar nar;
    final WeakHashMap<Task, TaskLabel> components = new WeakHashMap<>();
    float priorityThreshold = 0.01f;
    final Set<TreeNode> needRefresh = Collections.synchronizedSet(new HashSet());
    final ConcurrentLinkedDeque<Task> toAdd = new ConcurrentLinkedDeque<>();
    final ConcurrentLinkedDeque<Task> toRemove = new ConcurrentLinkedDeque<>();
    long lastUpdateTime = 0;
    
    boolean needsRestart = false;
    boolean showingJudgments = false;
    boolean showingQuestions = true;
    boolean showingGoals = true;
    
    public TaskTree(Nar nar) {
        super(new BorderLayout());

        tree = new JTree();
        model = new DefaultTreeModel(root);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.setModel(model);
        tree.setCellRenderer(new CustomDefaultRenderer());

        this.nar = nar;        

        JPanel menu = new JPanel(new WrapLayout(FlowLayout.LEFT));
        
        JCheckBox showJudgments = new JCheckBox("Judgments");
        showJudgments.setSelected(showingJudgments);
        showJudgments.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                showingJudgments = showJudgments.isSelected();
                reset();
            }            
        });
        menu.add(showJudgments);
        JCheckBox showQuestions = new JCheckBox("Questions");
        showQuestions.setSelected(showingQuestions);
        showQuestions.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                showingQuestions = showQuestions.isSelected();
                reset();
            }            
        });
        menu.add(showQuestions);
        JCheckBox showGoals = new JCheckBox("Goals");
        showGoals.setSelected(showingGoals);
        showGoals.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                showingGoals = showGoals.isSelected();
                reset();
            }            
        });
        menu.add(showGoals);
        
        add(menu, NORTH);
        add(new JScrollPane(tree), BorderLayout.CENTER);

        reset();
    }
    
    protected void reset() {
        components.clear();
        nodes.clear();
        toAdd.clear();
        toRemove.clear();
        root.removeAllChildren();
        lastUpdateTime = 0;
        
        needsRestart = true;                
    }

    @Override
    public void onShowing(boolean b) {
        nar.memory.event.set(this, b, TaskAdd.class, CyclesEnd.class /*, TaskRemove.class*/);
    }

    public void add(Task t) {
        if (isActive(t))
            toAdd.add(t);        
    }

    public DefaultMutableTreeNode getNode(final Task t) {
        return nodes.get(t);
    }

    public DefaultMutableTreeNode newNode(final Task t) {
        DefaultMutableTreeNode existing = nodes.get(t);
        if (existing != null) {
            return existing;
        }

        //String key = t.name().toString();
        DefaultMutableTreeNode d = new DefaultMutableTreeNode(t);
        nodes.put(t, d);
        return d;
    }

    protected boolean isActive(final Task t) {
        if ((t.sentence.isJudgment()) && (!showingJudgments)) return false;
        if ((t.sentence.isQuestion()) && (!showingQuestions)) return false;
        if ((t.sentence.isGoal()) && (!showingGoals)) return false;
        return t.getPriority() >= priorityThreshold;
    }
    
    /** get all tasks in the system by iterating all newTasks, novelTasks, Concept TaskLinks */
    //not part of Memory.java anymore as this is not something nars_core needs!
    public Set<Task> getTasks(Memory mem, boolean includeTaskLinks, boolean includeNovelTasks) {
        
        Set<Task> t = new HashSet();
        
        if (includeTaskLinks) {
            for (Concept c : mem) {
                for (TaskLink tl : c.taskLinks) {
                    t.add(tl.targetTask);
                }
            }
        }
        
        if (includeNovelTasks)
            for (Task n : mem.globalBuffer)
                t.add(n);
            
        return t;        
    }
    
    public void update() {
        //TODO get existing Tasks at the next frame event by new method: memory.getTasks() which iterates all concepts tasklinks
        if (needsRestart) {
            Set<Task> tasks = getTasks(nar.memory, true, false);
            for (Task t : tasks)
                add(t);
        }
        
        //remove dead tasks
        for (Task t : nodes.keySet()) {
            if (!isActive(t))
                toRemove.add(t);            
        }

        long now = System.currentTimeMillis();
        if (now - lastUpdateTime > updatePeriodMS) {
            SwingUtilities.invokeLater(this);            
        }        
    }

    @Override public void run() {
        for (Task t : toRemove) {
            DefaultMutableTreeNode node = nodes.get(t);
            if (node!=null) {
                TreeNode p = node.getParent();
                node.removeFromParent();                
                needRefresh.add(p);
                needRefresh.remove(node);
            }
            nodes.remove(t);
            components.remove(t);
            toAdd.remove(t);
        }
        
        for (Task t : toAdd) {
            Task parent = null; //t.getParentTask();
            if (parent!=null && parent.equals(t)) {
                //System.err.println(t + " has parentTask equal to itself");
                parent = null;
            }

            DefaultMutableTreeNode tnode = getNode(t);
            if (tnode != null) {
                continue;
            }

            tnode = newNode(t);

            if (parent == null) {
                //System.out.println(tnode + " add to root");
                root.add(tnode);                
                needRefresh.add(root);
            } else {
                DefaultMutableTreeNode pnode = getNode(parent);

                if (pnode != null) {
                    //System.out.println(tnode + "Adding to: " + pnode);
                    if (tnode.getParent()!=null) { //pnode.isNodeAncestor(tnode)) {
                        tnode.removeFromParent();
                    }
                    pnode.add(tnode);
                    needRefresh.add(pnode);
                } else {
                    //missing parent, reparent to root?
                    //System.out.println(tnode + " add to root by default");
                    root.add(tnode);
                    needRefresh.add(root);
                }

            }
        }

        for (TaskLabel t : components.values())
            t.updateTask();

        for (TreeNode t : needRefresh)
            model.reload(t);
        
        
        needRefresh.clear();        
        toAdd.clear();
        toRemove.clear();
        
        repaint();        
        lastUpdateTime = System.currentTimeMillis();
    }
    
    public class TaskLabel extends JLabel {
        private final Task task;

        public TaskLabel(Task t) {
            this.task = t;

            setOpaque(false);
            setFont(Video.monofont);
            
            updateTask();
        }

        protected void updateTask() {
            final Task t = task;
            
            Concept con = nar.memory.concept(t.getTerm());
            float conPri = 0;
            if (con == null) {
                /*System.err.println("TaskTree: " + t + " missing concept.  either memory was reset or concept should have been created but wasnt.");*/
                //toRemove.add(t);            
            }
            else {
                conPri = con.getPriority();
            }
            float taskPri = t.getPriority();
            TruthValue desire = t.sentence.truth;
            
            Color iColor;
            if (desire!=null) {
                float confidence = (float)t.sentence.truth.getConfidence();
                iColor = new Color(0,confidence/1.5f,conPri/1.5f,0.75f + 0.25f * confidence);
            }        
            else {
                iColor = new Color(0,0,conPri/1.5f,1f);
            }
            setBorder(new MatteBorder(0,15,0,0,iColor));
            
           
            setForeground(Color.WHITE);
            
            setOpaque(true);
            final float hue = 0.3f + 0.5f * conPri;            
            Color c = Color.getHSBColor(hue, 0.4f, conPri * 0.2f);
            //setBackground(new Color(1f-taskPri/4f,1f,1f-taskPri/4f));
            setBackground(c);
            
            setFont(Video.monofont.deriveFont(14f + taskPri * 4f));
            setText(t.toStringExternal2());
            
        }
    }
    
    protected void updateComponent(Task t, JLabel c) {
        if (c instanceof TaskLabel)
            ((TaskLabel)c).updateTask();
    }
    
    protected class CustomDefaultRenderer
            extends DefaultTreeCellRenderer {

        public Component getTreeCellRendererComponent(
                JTree tree,
                Object value,
                boolean selected,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {
            // Allow the original renderer to set up the label
            /*Component c = super.getTreeCellRendererComponent(
                    tree, value, selected,
                    expanded, leaf, row,
                    hasFocus);*/
            
         
            
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            Object v = node.getUserObject();
            if (v instanceof Task) {                
                TaskLabel c = new TaskLabel((Task)v);
                components.put((Task)v, c);
                return c;
            }
            
            return super.getTreeCellRendererComponent(
                    tree, value, selected,
                    expanded, leaf, row,
                    hasFocus);
        }

    }

    @Override
    public void event(Class channel, Object[] arguments) {        
//        if (channel == OUT.class) {
//            Object o = arguments[0];
//            if (o instanceof Task) {
//                add((Task) o);
//            }
//        }
        if (channel == TaskAdd.class) {
            add((Task)arguments[0]);
        }
        else if (channel == TaskRemove.class) {
            //..
        }
        else if (channel == CyclesEnd.class) {
            update();
        }
    }

}

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
//package org.opennars.gui.output;
//
//import java.awt.Dimension;
//import java.util.Map;
//import java.util.TreeMap;
//import static java.util.stream.Collectors.toList;
//import javax.swing.BoxLayout;
//import javax.swing.JComponent;
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTextPane;
//import org.opennars.core.EventEmitter.Observer;
//import org.opennars.core.Events.CycleEnd;
//import org.opennars.core.Nar;
//import org.opennars.core.build.DefaultNARBuilder;
//import org.opennars.entity.Task;
//import org.opennars.gui.NPanel;
//import org.opennars.gui.Window;
//import org.opennars.io.TextOutput;
//
///**
// *
// * @author me
// */
//
//
//public class TimePanel extends NPanel implements Observer {
//    
//    private final Nar nar;
//    int cyclesShown = 60;
//    Map<Long,String> cycleSummary = new TreeMap();
//    
//    public TimePanel(Nar n) {
//        super();
//        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
//        this.nar = n;
//        
//        update();
//    }
//    
//    public void update() {
//        removeAll();
//        
//        long now = nar.memory.getTime();
//        String s = String.join("\n", nar.memory.newTasks.stream().map(t -> t.toString()).collect(toList()));
//        cycleSummary.put(now, s);
//            
//    
//        for (int i = cyclesShown - 1; i >= 0; i--) {
//            long cycle = nar.memory.getTime() - i;
//            if (cycle < 0)
//                continue;
//            
//            JComponent cp = getTimeSlice(cycle);
//            cp.setMaximumSize(new Dimension(350, 800));            
//            add(new JScrollPane(cp));
//        }
//        
//        validate();
//    }
//    
//    public JComponent getTimeSlice(long cycle) {
//        JTextPane p = new JTextPane();
//        
//        String t = "Cycle: " + cycle + "\n";
//        //TODO realtime stamp
//        for (Task task : nar.memory.executive.shortTermMemory) {
//            if (task.getCreationTime() == cycle)
//                t += "STM Task: " + task.toStringExternal() + "\n";
//        }
//        
//        String cs = cycleSummary.get(cycle);
//        if (cs!=null)
//            t += cs + "\n";
//        
//        p.setText(t);
//        return p;
//    }
//    
//
//    
//    @Override
//    protected void onShowing(boolean showing) {
//     
//        if (showing) {
//            nar.memory.event.on(CycleEnd.class, this);
//        }
//        else {
//            nar.memory.event.off(CycleEnd.class, this);
//        }                    
//    }
//    
//    
//    
//    public static void main(String[] args) {
//        
//        Nar n = new DefaultNARBuilder().build();
//        
//        Window w = new Window("TimePanel", new JScrollPane(new TimePanel(n), JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
//        w.setSize(800, 200);
//        w.setVisible(true);
//        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//        new TextOutput(n, System.out);
//        
//        n.addInput("a. :|:");
//        n.addInput("6");
//        n.addInput("b. :|:");
//        n.addInput("6");
//        n.addInput("c. :|:");
//        n.finish(20);
//        
////        n.addInput("<a --> b>. :|:");
////        n.addInput("5");
////        n.addInput("<b --> c>. :|:");
////        n.addInput("5");
////        n.addInput("<c --> a>. :|:");
////        n.finish(1);
//        
//    }
//
//    @Override
//    public void event(Class event, Object... arguments) {
//        update();
//    }
//
//    
//    
//}

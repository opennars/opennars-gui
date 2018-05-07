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

import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JPanel;
import org.opennars.main.Nar;
import org.opennars.gui.util.DefaultGraphizer;
import org.opennars.gui.util.NARGraph;
import static org.opennars.gui.util.NARGraph.IncludeEverything;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;

/**
 *
 * https://github.com/jgrapht/jgrapht/blob/master/jgrapht-demo/src/main/java/org/jgrapht/demo/JGraphXAdapterDemo.java
 */
public class JGraphXGraphPanel extends JPanel {
    
    private final JGraphXAdapter jgxAdapter;
    

    public JGraphXGraphPanel(Graph g) {
        super(new BorderLayout());
        

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter(g) {


          
        };
        jgxAdapter.setMultigraph(true);
        jgxAdapter.setEdgeLabelsMovable(false);
        jgxAdapter.setVertexLabelsMovable(false);
        jgxAdapter.setAutoOrigin(true);
        jgxAdapter.setLabelsClipped(true);
        
        //System.out.println(jgxAdapter.getStylesheet().getDefaultEdgeStyle());
        
        //{perimeter=com.mxgraph.view.mxPerimeter$1@7b3300e5, shape=rectangle, fontColor=#774400, strokeColor=#6482B9, fillColor=#C3D9FF, align=center, verticalAlign=middle}
        Map<String, Object> vstyle = jgxAdapter.getStylesheet().getDefaultVertexStyle();

        vstyle.put("fillColor", "#CCCCCC");
        
        //{endArrow=classic, shape=connector, fontColor=#446299, strokeColor=#6482B9, align=center, verticalAlign=middle}
        Map<String, Object> estyle = jgxAdapter.getStylesheet().getDefaultEdgeStyle();
        estyle.put("strokeColor", "#333333");
        estyle.put("fontColor", "#333333");
        estyle.put(mxConstants.STYLE_STROKEWIDTH, 2);
        
        

        mxGraphComponent mxc = new mxGraphComponent(jgxAdapter) {
            
        };
        mxc.setAntiAlias(true);
        mxc.setConnectable(false);        
        mxc.setExportEnabled(false);
        mxc.setFoldingEnabled(false);
        mxc.setPanning(true);
        mxc.setTextAntiAlias(true);

        
                
        add(mxc, BorderLayout.CENTER);


        /*
        

        
        mxOrganicLayout layout = 
                new mxCompactTreeLayout(jgxAdapter);
                new mxOrganicLayout(jgxAdapter);
                //new mxCircleLayout(jgxAdapter);        
        layout.setEdgeLengthCostFactor(0.001);
        */
        /*
        {
        mxCompactTreeLayout layout = 
                new mxCompactTreeLayout(jgxAdapter);
        
        layout.setLevelDistance(40);
        layout.setNodeDistance(50);
        layout.setEdgeRouting(true);
        layout.setHorizontal(false);
        layout.setMoveTree(true);
        layout.setResizeParent(false);
        layout.execute(jgxAdapter.getDefaultParent());
        }
        */
        {
        mxFastOrganicLayout layout = 
                //new mxCompactTreeLayout(jgxAdapter);
                new mxFastOrganicLayout(jgxAdapter);
                //new mxCircleLayout(jgxAdapter);                
        layout.setForceConstant(400);
        layout.setMaxIterations(2000);
        layout.setUseBoundingBox(true);
        layout.execute(jgxAdapter.getDefaultParent());
            
        }
        

        
        jgxAdapter.setConnectableEdges(false);
        jgxAdapter.setCellsDisconnectable(false);
        jgxAdapter.setEdgeLabelsMovable(false);
        //jgxAdapter.setCellsLocked(true);
        
    }
    
    public JGraphXGraphPanel(Nar n) {
        this(new NARGraph().add(n, IncludeEverything, new DefaultGraphizer(true,true,true,true,3,false, false, null, null, null, null, n.narParameters)));
    }
    
//    public static void main(String[] args) {
//        Nar n = new Nar();
//        
//        /*
//        new TextInput(n, "<a --> b>.");
//        new TextInput(n, "<b --> c>.");
//        new TextInput(n, "<d <-> c>. %0.75;0.90%");
//        new TextInput(n, "<a --> c>?");      
//        new TextInput(n, "<a --> d>?");
//        n.run(12);
//        */
//        
//        n.addInput("<0 --> num>. %1.00;0.90% {0 : 1}");
//        n.addInput("<<$1 --> num> ==> <(*,$1) --> num>>. %1.00;0.90% {0 : 2}"); 
//        n.addInput("<(*,(*,(*,0))) --> num>?  {0 : 3}");
//       
//        n.run(220);
//        
//
//        
//        Window w = new Window("GraphPanel", new JGraphXGraphPanel(n)) {
//
//            @Override           protected void close() {            }
//            
//        };
//        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        w.setSize(1200,900);
//        w.setVisible(true);
//    }
}

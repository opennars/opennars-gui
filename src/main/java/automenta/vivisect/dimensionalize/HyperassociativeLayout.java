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
package automenta.vivisect.dimensionalize;

import automenta.vivisect.graph.AbstractGraphVis;
import automenta.vivisect.graph.EdgeVis;
import automenta.vivisect.graph.GraphDisplay;
import automenta.vivisect.graph.VertexVis;
import java.util.concurrent.atomic.AtomicBoolean;
import org.opennars.entity.Concept;
import org.opennars.entity.TaskLink;

import org.apache.commons.math3.linear.ArrayRealVector;

/**
 *
 * @author me
 */
public class HyperassociativeLayout implements GraphDisplay {

    HyperassociativeMap h = null;
    float spcing = 200.0f;
    
    private AtomicBoolean newNode = new AtomicBoolean(false);
    
    @Override
    public boolean preUpdate(AbstractGraphVis g) {
        
        
        if (h == null)
            h = new HyperassociativeMap(g.getGraph(), HyperassociativeMap.Euclidean, 2) {
                @Override
                protected ArrayRealVector newNodeCoordinates(Object node) {
                    newNode.set(true);
                    return super.newNodeCoordinates(node);
                }

            @Override
            public double getEdgeWeight(Object e) {
                if (e instanceof TaskLink) {                   
                    return 1.0 + ((TaskLink)e).getBudget().getPriority() * 1.0;                }
                return 1;
            }

                
            
                
            @Override
            public double getRadius(Object n) {
                if (n instanceof Concept) {
                    return 1.0 + ((Concept)n).getBudget().getPriority() * 1.0;                }
                return 1;
            }

                
            };    
        else {
            if (newNode.get()) {
                h.resetLearning();
                newNode.set(false);
            }
            
            h.setGraph(g.getGraph());
        }
        
        h.align();
        return true;
    }
    
    
    @Override
    public void vertex(AbstractGraphVis g, VertexVis v) {
        if (h == null) return;
        if (v == null) return;
        if (v.vertex == null) return;                
        
        ArrayRealVector c = h.getPosition(v.vertex); 
        if (c==null) return;
        
        
        double[] cc = c.getDataRef();
        v.tx = (float)cc[0] * spcing;
        v.ty = (float)cc[1] * spcing;
        
    }

    @Override
    public void edge(AbstractGraphVis g, EdgeVis e) {
    }
}

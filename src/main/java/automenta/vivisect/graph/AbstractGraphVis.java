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
package automenta.vivisect.graph;



import automenta.vivisect.Vis;
import automenta.vivisect.swing.PCanvas;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.math3.util.FastMath;
import org.jgrapht.Graph;
import static processing.core.PConstants.MITER;
import static processing.core.PConstants.SQUARE;
import processing.core.PGraphics;

/**
 *
 * @author me
 */
abstract public class AbstractGraphVis<V, E> implements Vis {

    

    Map<V, VertexVis<V,E>> vertices = new LinkedHashMap();
    Map<E, EdgeVis<V,E>> edges = new LinkedHashMap();
    Set<V> deadVertices = new LinkedHashSet();
    Set<E> deadEdges = new LinkedHashSet();

    Graph<V,E> currentGraph;

    boolean updateNext = true;
    
    //bounds of last positioned vertex
    float minX = 0f, minY = 0f, maxX = 0f, maxY = 0f;
    
    private GraphDisplay<V,E> display;


    int maxNodesWithLabels = 5000;
    int maxNodes = 5000;
    int maxEdgesWithArrows = 10000;
    int maxEdges = 10000;

    float nodeSpeed = 0.1f;

    float sx = 800f;
    float sy = 800f;

    float arrowHeadScale = 1f/16f;
    static final float vertexTargetThreshold = 4f;
    private PGraphics graphics;
    private PCanvas canvas;

    
    public AbstractGraphVis(GraphDisplay display) {
        super();
        this.display = display;       
    }

    public void setUpdateNext() {
        updateNext = true;
    }
    
    public void setDisplay(GraphDisplay display) {
        this.display = display;
    }

    public GraphDisplay getDisplay() {
        return display;
    }
    

    public VertexVis getVertexDisplay(V v) {
        return vertices.get(v);
    }
    


    public VertexVis updateVertex(final V o) {        
        
        deadVertices.remove(o);
        
        VertexVis v = vertices.get(o);      
        if (v != null) {
            display.vertex(this, v);
            return v;
        }
        
        v = new VertexVis(getCanvas(), o);
        vertices.put(o, v);

        return v;
    }

    public EdgeVis updateEdge(final E o) {        
        
        deadEdges.remove(o);
        
        EdgeVis v = edges.get(o);      
        if (v != null) {
            display.edge(this, v);
            return v;
        }
        
        v = new EdgeVis(o);
        edges.put(o, v);

        return v;
    }
    

    public abstract Graph<V,E> getGraph();
    
    abstract protected boolean hasUpdate();
    
    /**
     * called from Nar update thread, not swing thread
     */
    public void updateGraph() {
        
            
        if (hasUpdate() || (updateNext) || display.preUpdate(this)) {

            updateNext = false;

            /*synchronized (vertices)*/ {
                deadVertices.clear();
                deadEdges.clear();
                
                currentGraph = getGraph();
                if (currentGraph == null) {
                    vertices.clear();
                    edges.clear();
                    return;
                }
                
                deadVertices.addAll(vertices.keySet());
                deadEdges.addAll(edges.keySet());
                
                for (final V v : currentGraph.vertexSet())
                   updateVertex(v);
                
                for (final E e : currentGraph.edgeSet())
                    updateEdge(e);
                
                for (final V v : deadVertices)
                    vertices.remove(v);
                for (final E e : deadEdges)
                    edges.remove(e);
            }

        }
    }

    
    @Override public void init(PCanvas canvas) {
        this.canvas = canvas;
    }

    public PCanvas getCanvas() {
        return canvas;
    }
    
    
    public PGraphics getGraphics() {
        return graphics;
    }

    
    @Override
    public boolean draw(PGraphics g) {

        this.graphics = g;
        
        if (currentGraph == null) {
            return true;
        }

        /*synchronized (vertices)*/ {
            // for speed
            g.noFill();
            g.strokeCap(SQUARE);
            g.strokeJoin(MITER); //https://www.processing.org/reference/strokeJoin_.html

            
            int numEdges = currentGraph.edgeSet().size();            
            if (numEdges < maxEdges) {
                
                for (final EdgeVis d : edges.values()) {
                    d.draw(this, g);
                }
                
            }
            
            g.noStroke();

            int numNodes = vertices.size();
            if (numNodes < maxNodes) {
                for (final VertexVis d : vertices.values()) {
                    d.draw(this, g);
                }
            }
        }

        display.postUpdate(this);

        return true;
    }
    
    
    public void resurrectVertex(V v) {
        deadVertices.remove(v);        
    }
    

 
    /* TODO avoid using transform, just calculation coordinates in current transform */
    void drawArrow(final PGraphics g, final float x1, final float y1, float x2, float y2, float destinationRadius) {
        float dx = x2-x1;
        float dy = y2-y1;
        
        float angle = (float) (FastMath.atan2(dy, dx));

        final float arrowAngle = (float)Math.PI/12f + g.strokeWeight/200f;
        final float arrowHeadRadius = /*len **/ arrowHeadScale * (g.strokeWeight*16f);
        if (arrowHeadRadius > 0) {

            float len = (float) FastMath.sqrt(dx*dx + dy*dy) - destinationRadius;
            if (len <= 0) return;
            
            x2 = (float)FastMath.cos(angle) * len  + x1;
            y2 = (float)FastMath.sin(angle) * len  + y1;
            
            
            float plx = (float)FastMath.cos(angle-Math.PI-arrowAngle) * arrowHeadRadius; 
            float ply = (float)FastMath.sin(angle-Math.PI-arrowAngle) * arrowHeadRadius; 
            float prx = (float)FastMath.cos(angle-Math.PI+arrowAngle) * arrowHeadRadius;
            float pry = (float)FastMath.sin(angle-Math.PI+arrowAngle) * arrowHeadRadius; 

            g.fill(g.strokeColor);
            g.noStroke();
            g.quad(x2, y2, x2 + prx, y2 + pry, x1, y1, x2 + plx, y2 + ply);
            g.noFill();
        }
    }
}

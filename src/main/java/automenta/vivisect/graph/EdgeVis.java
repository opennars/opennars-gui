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
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automenta.vivisect.graph;

import processing.core.PGraphics;

/**
 *
 * @author me
 */
public class EdgeVis<V, E> {
    public final E edge;
    public VertexVis elem1 = null;
    public VertexVis elem2 = null;
    public int color;
    public float thickness;
    
    public EdgeVis(E edge) {
        this.edge = edge;
        color = 0xffffff;
        thickness = 1f;
    }
    
            
    @Override
    public int hashCode() {
        return edge.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return edge.equals(obj);
    }    
    
    private void updateVertices(final AbstractGraphVis c) {
        elem1 = c.getVertexDisplay(c.currentGraph.getEdgeSource(edge));
        elem2 = c.getVertexDisplay(c.currentGraph.getEdgeTarget(edge));
        if ((elem1 == null) || (elem2 == null) || (elem1 == elem2)) {
            throw new RuntimeException(this + " has missing vertices");
        }        
    }

    public void draw(final AbstractGraphVis c, final PGraphics g) {

        if (elem1 == null) {            
            updateVertices(c);
        }
        

        float scale = elem1.scale;
        assert(elem2.scale == scale);
        
        // TODO create EdgeDisplay class to cacahe these properties
        g.stroke(color);
        g.strokeWeight(thickness);

        float x1 = elem1.x*scale;
        float y1 = elem1.y*scale;
        float x2 = elem2.x*scale;
        float y2 = elem2.y*scale;

        c.drawArrow(g, x1, y1, x2, y2, elem2.radius/2f);
    }
}

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

import org.jgrapht.Graph;
import processing.core.PGraphics;

/**
 *
 * @author me
 */
public class AnimatingGraphVis<V,E> extends AbstractGraphVis<V,E> {
    Graph<V, E> graph;    

    public AnimatingGraphVis(Graph<V,E> graph, GraphDisplay<V,E>... displays) {
        this(graph, new GraphDisplays<V,E>(displays));
    }
    
    public AnimatingGraphVis(Graph<V,E> graph, GraphDisplay<V,E> display) {
        super(display);
        
        this.graph = graph;
                
        updateGraph();
        setUpdateNext();
    }

    
    @Override
    public Graph<V,E> getGraph() {
        return this.graph;
    }


    @Override
    public boolean draw(PGraphics g) {
        updateGraph();
        return super.draw(g);
    }

    @Override
    protected boolean hasUpdate() {
        return false;
    }
}

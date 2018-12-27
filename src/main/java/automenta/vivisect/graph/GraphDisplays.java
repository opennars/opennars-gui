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

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author me
 */
public class GraphDisplays<V,E> implements GraphDisplay<V,E> {
    
    final public List<GraphDisplay<V,E>> sequence;

    public GraphDisplays(GraphDisplay<V,E>... d) {
        sequence = new CopyOnWriteArrayList( Lists.newArrayList(d) );
    }

    @Override
    public boolean preUpdate(final AbstractGraphVis<V, E> g) {
        boolean allTrue = true;
        for (GraphDisplay<V, E> aSequence : sequence) {
            GraphDisplay s = aSequence;
            allTrue &= s.preUpdate(g);
        }
        return allTrue;
    }

    @Override
    public void vertex(final AbstractGraphVis<V, E> g, final VertexVis<V, E> v) {
        for (GraphDisplay<V, E> aSequence : sequence) {
            aSequence.vertex(g, v);
        }        
    }

    @Override
    public void edge(final AbstractGraphVis<V, E> g, final EdgeVis<V, E> e) {
        for (GraphDisplay<V, E> aSequence : sequence) {
            aSequence.edge(g, e);
        }
    }

    @Override
    public boolean postUpdate(final AbstractGraphVis<V, E> g) {
        boolean allTrue = true;
        for (GraphDisplay<V, E> aSequence : sequence) {
            allTrue &= aSequence.postUpdate(g);
        }
        return allTrue;
    }
}

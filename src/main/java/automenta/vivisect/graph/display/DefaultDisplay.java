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
package automenta.vivisect.graph.display;

import automenta.vivisect.Video;
import automenta.vivisect.graph.AbstractGraphVis;
import automenta.vivisect.graph.EdgeVis;
import automenta.vivisect.graph.GraphDisplay;
import automenta.vivisect.graph.VertexVis;
import java.awt.Color;

/**
 *
 * @author me
 */
public class DefaultDisplay<V, E> implements GraphDisplay<V, E> {
    final int gray = Color.GRAY.getRGB();
    final int white = Color.WHITE.getRGB();
    long startTime = System.currentTimeMillis();

    public float getTime() {
        return (System.currentTimeMillis() - startTime) / 1000.0f;
    }

    @Override
    public void edge(AbstractGraphVis<V, E> g, EdgeVis<V, E> e) {
        e.thickness = 23f;
        e.color = gray;
    }

    @Override
    public void vertex(final AbstractGraphVis<V, E> g, final VertexVis<V, E> v) {
        final Object o = v.vertex;
        v.shape = Shape.Ellipse;
        if (v.label == null) {
            v.label = v.vertex.toString();
        }
        v.radius = 16.0f + 7f * (float) Math.sin(getTime() * 4f);
        v.color = Video.getColor(o.hashCode(), 0.75f, 0.95f, 0.75f);
        v.textColor = white;
        v.stroke = 0;
        v.strokeColor = 0;
        v.scale = 10f;
        v.speed = 0.5f;
    }

    @Override
    public boolean preUpdate(AbstractGraphVis<V, E> g) {
        //enables continuous animation
        return true;
    }

    @Override
    public boolean postUpdate(AbstractGraphVis<V, E> g) {
        return true;
    }
    
}

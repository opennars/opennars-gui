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

/**
 *
 * @author me
 */
public interface GraphDisplay<V,E> {

    default public boolean preUpdate(AbstractGraphVis<V,E> g) {
        return true;
    }

    public void vertex(AbstractGraphVis<V,E> g, VertexVis<V,E> v);
    public void edge(AbstractGraphVis<V,E> g, EdgeVis<V,E> e);

    default public boolean postUpdate(AbstractGraphVis<V,E> g) {
        return true;
    }
    
    public static enum Shape { Rectangle, Ellipse };

//    
//    public Shape getVertexShape(V v);
//    public String getVertexLabel(final V v);
//    
//    /** return 0 to hide vertex */
//    public float getVertexSize(final V v);
//    
//    public int getVertexColor(V o);
//    public float getEdgeThickness(E edge, VertexVis source, VertexVis target);
//    public int getEdgeColor(E e);
//    public int getTextColor(V v);
//
//    public int getVertexStrokeColor(V v);
//    public float getVertexStroke(V v);
}

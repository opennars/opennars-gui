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
package org.opennars.gui.output.graph.layout;

import automenta.vivisect.Video;
import automenta.vivisect.graph.AbstractGraphVis;
import automenta.vivisect.graph.EdgeVis;
import automenta.vivisect.graph.GraphDisplay;
import automenta.vivisect.graph.VertexVis;
import org.opennars.main.Nar;
import org.opennars.entity.Concept;
import org.opennars.entity.Item;
import org.opennars.entity.Task;

/**
 * Item Hash = theta, Priority = radius
 */
public class SpiralLayout implements GraphDisplay<Item, Object> {
    float spacing;
    Nar nar;

    public SpiralLayout(Nar nar, float spacing) {
        this.spacing = spacing;
        this.nar = nar;
    }

    @Override
    public void vertex(AbstractGraphVis<Item, Object> g, VertexVis<Item, Object> v) {
        Item vertex = v.getVertex();

        float priority = vertex.getPriority();

        boolean task = false;
        Concept x = null;
        if(vertex instanceof Concept) {
            x = (Concept) vertex;
        }
        else
        if(vertex instanceof Task) {
            task = true;
            x = nar.memory.concept(((Task) vertex).getTerm());
        }

        int i = 0;
        try{
            for(Concept c: nar.memory.concepts) {
                if(x == c) { //not elegant and fast but k at least no term equals
                    break;
                }
                i++;
            }
        }catch(Exception ex){}
        float count_elems = nar.memory.concepts.size();
        // float ratio = 30.0f*(0.10f + (((float)priority) / (1.0f)));
        float ratio = 30.0f*(0.10f + (((float)i) / (count_elems)));
        v.tx = (float) (ratio*Math.cos(ratio)) * spacing;
        v.ty = (float) (ratio*Math.sin(ratio)) * spacing;
        
        if(task) {
            v.ty += spacing*Video.hashFloat(vertex.hashCode());
        }
        
    }

    @Override
    public void edge(AbstractGraphVis<Item, Object> g, EdgeVis<Item, Object> e) {

    }

}

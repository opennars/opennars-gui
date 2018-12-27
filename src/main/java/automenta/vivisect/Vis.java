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
package automenta.vivisect;

import automenta.vivisect.swing.PCanvas;
import processing.core.PGraphics;

/**
 * Something that can be visualized, drawn, or otherwies represented graphically / visually.
 */
public interface Vis {
    
    /** returns true if it should remain visible, false if it is to be removed */
    boolean draw(PGraphics g);

    /** notifies this when visibility has changed */
    default void onVisible(boolean showing) {
        
    }

    default void init(PCanvas p) {
        
    }
}

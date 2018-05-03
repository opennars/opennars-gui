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
package automenta.vivisect.swing.dock;

/**
 *
 * @author kitfox
 */
public class DockingPickRecord {

    private final DockingChild child;
    private final int direction;

    public DockingPickRecord(final DockingChild child, final int direction) {
        this.child = child;
        this.direction = direction;
    }

    /**
     * @return the child
     */
    public DockingChild getChild() {
        return child;
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

}

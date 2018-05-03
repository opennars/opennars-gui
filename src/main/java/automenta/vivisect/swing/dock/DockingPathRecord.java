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
abstract public class DockingPathRecord {

    final DockingPathRecord next;

    public DockingPathRecord() {
        this.next = null;
    }

    public DockingPathRecord(final DockingPathRecord next) {
        this.next = next;
    }

//    abstract public DockingChild getDockingChild();
    public DockingPathRecord getLast() {
        return (next == null) ? this : next.getLast();
    }
}

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
public interface DockingContainer {

    /**
     * Divide this container in two
     *
     * @param child The child component which is being split. Must be a child of
     * this container.
     * @param content Content to add to the newly created side of the split
     * @param right If true, content will be placed on the right or bottom of
     * the new split
     * @param vertical If true, split will divide the panel into a top and
     * bottom. Otherwise will divide left and right.
     * @return The newly created region.
     */
    DockingRegionSplit split(DockingChild child,
                             DockingContent content,
                             boolean right, boolean vertical);

    /**
     * Indicates that oldChild has become invalid and should be replaced with
     * newChild. If newChild is null, indicates oldChild should be removed.
     *
     * @param oldChild Child to be removed
     * @param newChild Child to replace oldChild with. If null, oldChild is
     * simply removed
     */
    void join(DockingChild oldChild, DockingChild newChild);

    DockingPathRecord buildPath(DockingChild dockChild, DockingPathRecord childPath);

    DockingRegionContainer getContainerRoot();
}

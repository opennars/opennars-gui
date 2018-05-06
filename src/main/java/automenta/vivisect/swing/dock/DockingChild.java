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

import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author kitfox
 */
public interface DockingChild {

    JComponent getComponent();

    DockingContainer getDockParent();

    void setDockParent(DockingContainer dockParent);

    void addDockContent(DockingContent content);

    DockingChild getDockingChild(DockingPathRecord subpath);

    void restore(DockingContent content, DockingPathRecord subpath);

    DockingPickRecord pickContainer(Point containerPoint);

    void closeAll();

}

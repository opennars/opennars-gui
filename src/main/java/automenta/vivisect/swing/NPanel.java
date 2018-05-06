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
package automenta.vivisect.swing;

import java.awt.LayoutManager;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import javax.swing.JPanel;

/**
 * JPanel subclass that is aware of when it is shown. This allows event handlers to attach and reattach to Nar's
 * @author SeH
 */
abstract public class NPanel extends JPanel implements HierarchyListener {

    public NPanel() {
        super();
    }

    public NPanel(LayoutManager l) {
        super(l);
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        addHierarchyListener(this);
    }

    @Override
    public void removeNotify() {
        removeHierarchyListener(this);
        super.removeNotify();
    }

    @Override
    public void hierarchyChanged(HierarchyEvent e) {
        if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
            boolean showing = isShowing();
            onShowing(showing);
        }
    }    
    
    abstract protected void onShowing(boolean showing);
    
}

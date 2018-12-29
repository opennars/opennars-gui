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

import java.awt.BorderLayout;
import java.util.EventObject;
import javax.swing.JDialog;
import static javax.swing.SwingUtilities.getWindowAncestor;

/**
 *
 * @author kitfox
 */
public class DockingRegionWindow extends JDialog
        implements DockingRegionContainerListener {

    private final DockingRegionContainer root;

    public DockingRegionWindow(DockingRegionRoot dockRoot, DockingRegionContainer root) {
        super(getWindowAncestor(dockRoot),
                null, ModalityType.MODELESS);

        this.root = root;
//        root = new DockingRegionContainer(dockRoot);
        getContentPane().add(root, BorderLayout.CENTER);
        root.addDockingRegionContainerListener(this);
    }

    /**
     * @return the root
     */
    public DockingRegionContainer getRoot() {
        return root;
    }

    @Override
    public void dockingContainerEmpty(EventObject evt) {
        setVisible(false);
        dispose();
    }

}

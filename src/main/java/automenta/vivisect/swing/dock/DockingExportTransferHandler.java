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

import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author kitfox
 */
public class DockingExportTransferHandler extends TransferHandler {

    DockingRegionTabbed tabbed;
    DockingContent content;

    public DockingExportTransferHandler(DockingRegionTabbed tabbed, DockingContent content) {
        this.tabbed = tabbed;
        this.content = content;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    public Transferable createTransferable(JComponent c) {
        DockingPathRecord path = tabbed.getPath(content);
        DockingRegionContainer container = tabbed.getContainerRoot();
        int idx = container.getDockRoot().indexOf(container);

        container.getDockRoot().startDragging();

        DockingTransferType xfer = new DockingTransferType(path, idx);
        return xfer;
    }

    @Override
    public void exportDone(JComponent c, Transferable t, int action) {
        DockingRegionContainer container = tabbed.getContainerRoot();
        container.getDockRoot().stopDragging();
    }
}

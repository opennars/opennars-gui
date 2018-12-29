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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author kitfox
 */
public class DockingTransferType implements Transferable {

    public static final DataFlavor FLAVOR
            = new DataFlavor(DockingTransferType.class, null);

    private final DockingPathRecord path;
    private final int windowIndex;

    public DockingTransferType(final DockingPathRecord path, final int windowIndex) {
        this.path = path;
        this.windowIndex = windowIndex;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(final DataFlavor flavor) {
        return this;
    }

    /**
     * @return the path
     */
    public DockingPathRecord getPath() {
        return path;
    }

    /**
     * @return the windowIndex
     */
    public int getContainerIndex() {
        return windowIndex;
    }

}

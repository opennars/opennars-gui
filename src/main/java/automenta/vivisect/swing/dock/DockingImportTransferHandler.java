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
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import static java.util.logging.Logger.getLogger;
import javax.swing.TransferHandler;

/**
 *
 * @author kitfox
 */
public class DockingImportTransferHandler extends TransferHandler {

    DraggingOverlayPanel overlayPanel;

    public DockingImportTransferHandler(DraggingOverlayPanel overlayPanel) {
        this.overlayPanel = overlayPanel;
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport info) {
        Point point = info.getDropLocation().getDropPoint();
        overlayPanel.sampleImportPoint(point);

        return info.isDataFlavorSupported(DockingTransferType.FLAVOR);
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport info) {
//        Point point = info.getDropLocation().getDropPoint();
//        DockingPickRecord rec = overlayPanel.sampleImportPoint(point);

        //Clear overlay
//        overlayPanel.sampleImportPoint(null);
//        if (rec == null)
//        {
//            return false;
//        }
        Transferable xfer = info.getTransferable();
        DockingTransferType data = null;
        try {
            data = (DockingTransferType) xfer
                    .getTransferData(DockingTransferType.FLAVOR);
        } catch (UnsupportedFlavorException | IOException ex) {
            getLogger(DockingImportTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (data == null) {
            return false;
        }

        overlayPanel.importContent(data);

        //Clear overlay
        overlayPanel.sampleImportPoint(null);
        return true;
    }

}

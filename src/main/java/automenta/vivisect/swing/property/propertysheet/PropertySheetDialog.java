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
/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package automenta.vivisect.swing.property.propertysheet;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import automenta.vivisect.swing.property.swing.BaseDialog;

/**
 * PropertySheetDialog
 *
 */
public class PropertySheetDialog extends BaseDialog {

    public PropertySheetDialog() throws HeadlessException {
        super();
    }

    public PropertySheetDialog(Dialog owner) throws HeadlessException {
        super(owner);
    }

    public PropertySheetDialog(Dialog owner, boolean modal)
        throws HeadlessException {
        super(owner, modal);
    }

    public PropertySheetDialog(Frame owner) throws HeadlessException {
        super(owner);
    }

    public PropertySheetDialog(Frame owner, boolean modal)
        throws HeadlessException {
        super(owner, modal);
    }

    public PropertySheetDialog(Dialog owner, String title)
        throws HeadlessException {
        super(owner, title);
    }

    public PropertySheetDialog(Dialog owner, String title, boolean modal)
        throws HeadlessException {
        super(owner, title, modal);
    }

    public PropertySheetDialog(Frame owner, String title)
        throws HeadlessException {
        super(owner, title);
    }

    public PropertySheetDialog(Frame owner, String title, boolean modal)
        throws HeadlessException {
        super(owner, title, modal);
    }

    public PropertySheetDialog(
        Dialog owner,
        String title,
        boolean modal,
        GraphicsConfiguration gc)
        throws HeadlessException {
        super(owner, title, modal, gc);
    }

    public PropertySheetDialog(
        Frame owner,
        String title,
        boolean modal,
        GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }
}

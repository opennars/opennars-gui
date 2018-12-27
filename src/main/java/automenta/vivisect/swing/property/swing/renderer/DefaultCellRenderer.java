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
package automenta.vivisect.swing.property.swing.renderer;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;

import automenta.vivisect.swing.property.model.DefaultObjectRenderer;
import automenta.vivisect.swing.property.model.ObjectRenderer;


/**
 * DefaultCellRenderer.<br>
 * 
 */
public class DefaultCellRenderer extends DefaultTableCellRenderer implements ListCellRenderer {

	private static final long serialVersionUID = -6142292027983690799L;

	private ObjectRenderer objectRenderer = new DefaultObjectRenderer();

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean focus) {

		setBorder(null);

		if (selected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		setValue(value);

		return this;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focus, int row, int column) {
		super.getTableCellRendererComponent(table, value, selected, focus, row, column);
		setValue(value);
		return this;
	}

	@Override
	public void setValue(Object value) {
		String text = convertToString(value);
		Icon icon = convertToIcon(value);

		setText(text == null ? "" : text);
		setIcon(icon);
		setDisabledIcon(icon);
	}

	/**
	 * Converts cell value to string.
	 * 
	 * @param value the value to be displayed in cell
	 * @return String representation of given value
	 */
	protected String convertToString(Object value) {
		return objectRenderer.getText(value);
	}

	protected Icon convertToIcon(Object value) {
		return null;
	}

}

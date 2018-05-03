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
package automenta.vivisect.swing.property.sheet.renderer;

import java.awt.Component;
import java.io.Serializable;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import automenta.vivisect.swing.property.sheet.I18N;


/**
 * Boolean value table cell renderer.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class BooleanRenderer extends JPanel implements TableCellRenderer, Serializable {

	private static final long serialVersionUID = 8848514762273327844L;

	private JCheckBox checkbox = null;

	public BooleanRenderer() {

		checkbox = new JCheckBox();
		checkbox.setBounds(-3, 0, 200, 15);
		checkbox.setText("");

		setBorder(null);
		setLayout(null);

		add(checkbox);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		JTable.DropLocation dl = table.getDropLocation();
		if (dl != null && !dl.isInsertRow() && !dl.isInsertColumn() && dl.getRow() == row && dl.getColumn() == column) {
			isSelected = true;
		}

		if (isSelected) {
			setBackground(table.getSelectionBackground());
			setForeground(table.getSelectionForeground());
			checkbox.setBackground(table.getSelectionBackground());
			checkbox.setForeground(table.getSelectionForeground());
		} else {
			setBackground(table.getBackground());
			setForeground(table.getForeground());
			checkbox.setBackground(table.getBackground());
			checkbox.setForeground(table.getForeground());
		}

		boolean selected = Boolean.TRUE.equals(value);

		checkbox.setSelected(selected);
		checkbox.setText(selected ? I18N.TRUE : I18N.FALSE);

		return this;
	}
}

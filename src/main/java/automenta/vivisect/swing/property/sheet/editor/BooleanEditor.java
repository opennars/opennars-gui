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
package automenta.vivisect.swing.property.sheet.editor;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;

import automenta.vivisect.swing.property.beans.editor.AbstractPropertyEditor;
import automenta.vivisect.swing.property.propertysheet.Property;
import automenta.vivisect.swing.property.sheet.I18N;
import automenta.vivisect.swing.property.sheet.ResizeLayout;


/**
 * Boolean value table cell editor.
 */
public class BooleanEditor extends AbstractPropertyEditor {

	private JCheckBox checkbox = null;
	private JPanel panel = null;

	public BooleanEditor(Object property) {

		Property prop = (Property) property;

		checkbox = new JCheckBox();

		boolean selected = Boolean.TRUE.equals(prop.getValue());

		checkbox.setSelected(selected);
		checkbox.setText(selected ? I18N.TRUE : I18N.FALSE);
		checkbox.setOpaque(true);
		checkbox.setLocation(-3, 0);
		checkbox.setFocusable(false);
		checkbox.setBackground(UIManager.getColor("Table.selectionBackground"));
		checkbox.setForeground(UIManager.getColor("Table.selectionForeground"));
		checkbox.setAction(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				checkbox.setText(checkbox.isSelected() ? I18N.TRUE : I18N.FALSE);
			}
		});

		panel = new JPanel();
		panel.setLayout(new ResizeLayout());
		panel.setBorder(null);
		panel.add(checkbox);
		panel.setFocusCycleRoot(true);
		panel.setBackground(UIManager.getColor("Table.selectionBackground"));
		panel.setForeground(UIManager.getColor("Table.selectionForeground"));
		panel.setOpaque(true);
		panel.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				Object older = checkbox.isSelected() ? Boolean.FALSE : Boolean.TRUE;
				Object newer = checkbox.isSelected() ? Boolean.TRUE : Boolean.FALSE;
				firePropertyChange(older, newer);
			}

			@Override
			public void focusGained(FocusEvent e) {
				checkbox.requestFocus();
			}
		});

		this.editor = panel;
	}

	@Override
	public Object getValue() {
		return checkbox.isSelected() ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public void setValue(Object value) {
		boolean selected = Boolean.TRUE.equals(value);
		checkbox.setSelected(selected);
		checkbox.setText(selected ? I18N.TRUE : I18N.FALSE);
	}

}

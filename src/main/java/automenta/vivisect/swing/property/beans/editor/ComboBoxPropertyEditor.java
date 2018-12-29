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
package automenta.vivisect.swing.property.beans.editor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxEditor;


/**
 * ComboBoxPropertyEditor. <br>
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ComboBoxPropertyEditor extends AbstractPropertyEditor {

	private Object oldValue;
	private Icon[] icons;

	public ComboBoxPropertyEditor() {

		final JComboBox combo = new JComboBox() {

			private static final long serialVersionUID = -7048198994640023540L;

			@Override
			public void setSelectedItem(Object anObject) {
				oldValue = getSelectedItem();
				super.setSelectedItem(anObject);
			}
		};

		combo.setRenderer(new Renderer());
		combo.setBorder(null);
		combo.setOpaque(false);
		combo.setFocusable(false);

		BasicComboBoxEditor comboEditor = (BasicComboBoxEditor) combo.getEditor();
		JTextField textField = (JTextField) comboEditor.getEditorComponent();
		textField.setBorder(null);
		textField.setFocusable(false);
		textField.setOpaque(false);

		combo.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				ComboBoxPropertyEditor.this.firePropertyChange(oldValue, combo.getSelectedItem());
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});

		combo.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ComboBoxPropertyEditor.this.firePropertyChange(oldValue,
					combo.getSelectedItem());
				}
			}
		});
		combo.setSelectedIndex(-1);

		editor = combo;
	}

	@Override
	public Object getValue() {
		Object selected = ((JComboBox) editor).getSelectedItem();
		if (selected instanceof Value) {
			return ((Value) selected).value;
		} else {
			return selected;
		}
	}

	@Override
	public void setValue(Object value) {
		JComboBox combo = (JComboBox) editor;
		Object current = null;
		int index = -1;
		for (int i = 0, c = combo.getModel().getSize(); i < c; i++) {
			current = combo.getModel().getElementAt(i);
			if (value == current || (current != null && current.equals(value))) {
				index = i;
				break;
			}
		}
		((JComboBox) editor).setSelectedIndex(index);
	}

	public void setAvailableValues(Object[] values) {
		((JComboBox) editor).setModel(new DefaultComboBoxModel(values));
	}

	public void setAvailableIcons(Icon[] icons) {
		this.icons = icons;
	}

	public class Renderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = -419585447957652497L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean focus) {

			Object v = value instanceof Value ? ((Value) value).visualValue : value;
			Component component = super.getListCellRendererComponent(list, v, index, selected, focus);

			if (component instanceof JLabel) {

				JLabel label = (JLabel) component;

				if (icons != null && index >= 0) {
					label.setIcon(icons[index]);
				}

				label.setPreferredSize(new Dimension(component.getSize().width, 16));
			}

			return component;
		}
	}

	public static final class Value {

		private Object value;
		private Object visualValue;

		public Value(Object value, Object visualValue) {
			this.value = value;
			this.visualValue = visualValue;
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;
            return value == o || (value != null && value.equals(o));
        }

		@Override
		public int hashCode() {
			return value == null ? 0 : value.hashCode();
		}
	}
}

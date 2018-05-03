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

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import automenta.vivisect.swing.property.util.converter.ConverterRegistry;


/**
 * StringConverterPropertyEditor. <br>
 * A comma separated list of values.
 */
public abstract class StringConverterPropertyEditor extends AbstractPropertyEditor {

	private Object oldValue;

	public StringConverterPropertyEditor() {

		JTextField text = new JTextField();
		text.setBorder(BorderFactory.createEmptyBorder());

		editor = text;
	}

	@Override
	public Object getValue() {
		String text = ((JTextComponent) editor).getText();
		if (text == null || text.trim().length() == 0) {
			return null;
		} else {
			try {
				return convertFromString(text.trim());
			} catch (Exception e) {
				/* UIManager.getLookAndFeel().provideErrorFeedback(editor); */
				return oldValue;
			}
		}
	}

	@Override
	public void setValue(Object value) {
		if (value == null) {
			((JTextComponent) editor).setText("");
		} else {
			oldValue = value;
			((JTextComponent) editor).setText(convertToString(value));
		}
	}

	protected abstract Object convertFromString(String text);

	protected String convertToString(Object value) {
		return (String) ConverterRegistry.instance().convert(String.class, value);
	}

}

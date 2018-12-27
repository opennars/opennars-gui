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

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import automenta.vivisect.swing.property.swing.LookAndFeelTweaks;

/**
 * StringPropertyEditor.<br>
 *
 */
public class StringPropertyEditor extends AbstractPropertyEditor {

  public StringPropertyEditor() {
    editor = new JTextField();
    ((JTextField)editor).setBorder(LookAndFeelTweaks.EMPTY_BORDER);
  }
  
  public Object getValue() {
    return ((JTextComponent)editor).getText();
  }
  
  public void setValue(Object value) {
    if (value == null) {
      ((JTextComponent)editor).setText("");
    } else {
      ((JTextComponent)editor).setText(String.valueOf(value));
    }
  }
  
}

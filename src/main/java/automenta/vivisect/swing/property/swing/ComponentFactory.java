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
package automenta.vivisect.swing.property.swing;

import javax.swing.JButton;
import javax.swing.JComboBox;

import automenta.vivisect.swing.property.beans.editor.FixedButton;

public interface ComponentFactory {

  JButton createMiniButton();

  JComboBox createComboBox();

  class Helper {

    static ComponentFactory factory = new DefaultComponentFactory();

    public static ComponentFactory getFactory() {
      return factory;
    }
    
    public static void setFactory(ComponentFactory factory) {
      Helper.factory = factory;
    }
  }

  class DefaultComponentFactory implements ComponentFactory {
    public JButton createMiniButton() {
      return new FixedButton();
    }
    public JComboBox createComboBox() {
      return new JComboBox();
    }
  }
}

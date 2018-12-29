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
package automenta.vivisect.swing.property.swing.plaf.metal;

import automenta.vivisect.swing.property.swing.plaf.basic.BasicLookAndFeelAddons;

/**
 * MetalLookAndFeelAddons
 *
 */
public class MetalLookAndFeelAddons extends BasicLookAndFeelAddons {

  public void initialize() {
    super.initialize();
    loadDefaults(getDefaults());
  }

  public void uninitialize() {
    super.uninitialize();
    unloadDefaults(getDefaults());
  }
  
  private Object[] getDefaults() {
    Object[] defaults =
      new Object[] {
        "DirectoryChooserUI",
        "com.l2fprod.common.swing.plaf.windows.WindowsDirectoryChooserUI",
    };
    return defaults;
  }
  
}

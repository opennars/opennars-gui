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
package automenta.vivisect.swing.property.model;

import java.io.File;

import automenta.vivisect.swing.property.util.ResourceManager;
import automenta.vivisect.swing.property.util.converter.ConverterRegistry;

/**
 * DefaultObjectRenderer. <br>
 *  
 */
public class DefaultObjectRenderer implements ObjectRenderer {

  private boolean idVisible = false;

  public void setIdVisible(boolean b) {
    idVisible = b;
  }

  public String getText(Object object) {
    if (object == null) {
      return null;
    }

    // lookup the shared ConverterRegistry
    try {
      return (String)ConverterRegistry.instance().convert(String.class, object);
    } catch (IllegalArgumentException e) {
    }

    if (object instanceof Boolean) {
      return Boolean.TRUE.equals(object)
        ? ResourceManager.common().getString("true")
        : ResourceManager.common().getString("false");
    }

    if (object instanceof File) {
      return ((File)object).getAbsolutePath();
    }

    StringBuffer buffer = new StringBuffer();
    if (idVisible && object instanceof HasId) {
      buffer.append(((HasId)object).getId());
    }
    if (object instanceof TitledObject) {
      buffer.append(((TitledObject)object).getTitle());
    }
    if (!(object instanceof HasId || object instanceof TitledObject)) {
      buffer.append(String.valueOf(object));
    }
    return buffer.toString();
  }

}

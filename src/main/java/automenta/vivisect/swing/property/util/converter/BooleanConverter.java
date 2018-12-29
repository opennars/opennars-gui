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
package automenta.vivisect.swing.property.util.converter;

/**
 * Converts a boolean to string and vice-versa.
 */
public class BooleanConverter implements Converter {

  public void register(ConverterRegistry registry) {
    registry.addConverter(String.class, boolean.class, this);
    registry.addConverter(String.class, Boolean.class, this);
    registry.addConverter(Boolean.class, String.class, this);
    registry.addConverter(boolean.class, String.class, this);
  }
  
  public Object convert(Class type, Object value) {
    if (String.class.equals(type) && Boolean.class.equals(value.getClass())) {
      return String.valueOf(value);
    } else if (boolean.class.equals(type) || Boolean.class.equals(type)) {
      return Boolean.valueOf(String.valueOf(value));
    } else {
      throw new IllegalArgumentException("Can't convert " + value + " to "
        + type.getName());
    }
  }
  
}

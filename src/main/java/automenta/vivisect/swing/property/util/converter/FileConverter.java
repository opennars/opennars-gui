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

import java.io.File;

public class FileConverter implements Converter {

	public void register(ConverterRegistry registry) {
		registry.addConverter(String.class, File.class, this);
		registry.addConverter(File.class, String.class, this);
	}

	public Object convert(Class type, Object value) {
		if (value == null)
			return null;
		if (String.class.equals(type) && File.class.equals(value.getClass())) {
			return ((File) value).getAbsolutePath();
		} else if (File.class.equals(type)
				&& String.class.equals(value.getClass())) {
			return new File(value.toString());
		} else {
			throw new IllegalArgumentException("Can't convert " + value
					+ " to " + type.getName());
		}
	}
}

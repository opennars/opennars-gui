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

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public void register(ConverterRegistry registry) {
		registry.addConverter(String.class, Date.class, this);
		registry.addConverter(Date.class, String.class, this);
	}

	public Object convert(Class type, Object value) {
		if (value == null)
			return null;
		if (String.class.equals(type) && Date.class.equals(value.getClass())) {
			return sdf.format((Date)value);
		} else if (Date.class.equals(type)
				&& String.class.equals(value.getClass())) { 
			try {
				return sdf.parse(value.toString());	
			}
			catch (Exception e) {
				throw new IllegalArgumentException("Can't convert " + value
						+ " to " + type.getName());
			}
			
		} else {
			throw new IllegalArgumentException("Can't convert " + value
					+ " to " + type.getName());
		}
	}
	

}

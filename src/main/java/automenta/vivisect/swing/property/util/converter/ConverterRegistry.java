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

import java.util.HashMap;
import java.util.Map;



/**
 * ConverterRegistry. <br>
 * 
 */
public class ConverterRegistry implements Converter {

	private static ConverterRegistry sharedInstance = new ConverterRegistry();

	private Map fromMap;

	public ConverterRegistry() {
		fromMap = new HashMap();

		new BooleanConverter().register(this);
		new AWTConverters().register(this);
		new NumberConverters().register(this);
		new BasicNumberTypeConverters().register(this);
		new FileConverter().register(this);
		new DateConverter().register(this);
	}

	public void addConverter(Class from, Class to, Converter converter) {
		Map toMap = (Map) fromMap.get(from);
		if (toMap == null) {
			toMap = new HashMap();
			fromMap.put(from, toMap);
		}
		toMap.put(to, converter);
	}

	public Converter getConverter(Class from, Class to) {
		Map toMap = (Map) fromMap.get(from);
		if (toMap != null) {
			return (Converter) toMap.get(to);
		} else {
			return null;
		}
	}

	@Override
	public Object convert(Class targetType, Object value) {
		if (value == null) {
			return null;
		}

		Converter converter = getConverter(value.getClass(), targetType);
		if (converter == null) {
			throw new IllegalArgumentException(
			"No converter from " + value.getClass() + " to " + targetType.getName());
		} else {
			return converter.convert(targetType, value);
		}
	}

	public static ConverterRegistry instance() {
		return sharedInstance;
	}

}

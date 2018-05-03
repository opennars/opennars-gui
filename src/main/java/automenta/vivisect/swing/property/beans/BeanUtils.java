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
package automenta.vivisect.swing.property.beans;

import java.lang.reflect.Method;


/**
 * BeanUtils. <br>
 * 
 */
public class BeanUtils {

	private BeanUtils() {
	}

	public static Method getReadMethod(Class<?> clazz, String propertyName) {

		Method readMethod = null;
		String base = capitalize(propertyName);

		// Since there can be multiple setter methods but only one getter
		// method, find the getter method first so that you know what the
		// property type is. For booleans, there can be "is" and "get"
		// methods. If an "is" method exists, this is the official
		// reader method so look for this one first.

		try {
			readMethod = clazz.getMethod("is" + base, (Class<?>[]) null);
		} catch (Exception getterExc) {
			try {
				// no "is" method, so look for a "get" method.
				readMethod = clazz.getMethod("get" + base, (Class<?>[]) null);
			} catch (Exception e) {
				// fall thru, no is and no get, we will return null
			}
		}

		return readMethod;
	}

	public static Method getWriteMethod(Class<?> clazz, String propertyName, Class<?> propertyType) {

		Method writeMethod = null;
		String base = capitalize(propertyName);

		Class<?> params[] = { propertyType };
		try {
			writeMethod = clazz.getMethod("set" + base, params);
		} catch (Exception e) {
			// no write method
		}

		return writeMethod;
	}

	private static String capitalize(String s) {
		if (s.length() == 0) {
			return s;
		} else {
			char chars[] = s.toCharArray();
			chars[0] = Character.toUpperCase(chars[0]);
			return String.valueOf(chars);
		}
	}

}

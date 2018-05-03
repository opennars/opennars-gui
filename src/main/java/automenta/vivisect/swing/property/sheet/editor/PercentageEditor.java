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
package automenta.vivisect.swing.property.sheet.editor;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import automenta.vivisect.swing.property.propertysheet.Property;


/**
 * Percentage editor.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class PercentageEditor extends SpinnerEditor {

	/**
	 * Percentage editor. Argument has to be an object so javax.bean API handles
	 * it correctly.
	 * 
	 * @param property the property object (instance of {@link Property})
	 */
	public PercentageEditor(Object property) {
		super();

		if (!(property instanceof Property)) {
			throw new IllegalArgumentException(String.format("Property has to be %s instance. Instead found %s", Property.class, property.getClass()));
		}

		Property prop = (Property) property;
		Class<?> type = prop.getType();

		int pstart = 0;
		int pmin = 0;
		int pmax = 100;
		int pstep = 1;

		Number start = null;
		Comparable<?> min = null;
		Comparable<?> max = null;
		Number step = null;

		if (type == Byte.class || type == byte.class) {
			start = Byte.valueOf((byte) pstart);
			min = Byte.valueOf((byte) pmin);
			max = Byte.valueOf((byte) pmax);
			step = Byte.valueOf((byte) pstep);
		} else if (type == Short.class || type == short.class) {
			start = Short.valueOf((short) pstart);
			min = Short.valueOf((short) pmin);
			max = Short.valueOf((short) pmax);
			step = Short.valueOf((short) pstep);
		} else if (type == Integer.class || type == int.class) {
			start = Integer.valueOf(pstart);
			min = Integer.valueOf(pmin);
			max = Integer.valueOf(pmax);
			step = Integer.valueOf(pstep);
		} else if (type == Long.class || type == long.class) {
			start = Long.valueOf(pstart);
			min = Long.valueOf(pmin);
			max = Long.valueOf(pmax);
			step = Long.valueOf(pstep);
		} else if (type == Float.class || type == float.class) {
			start = Float.valueOf(pstart);
			min = Float.valueOf(pmin);
			max = Float.valueOf(pmax);
			step = Float.valueOf(pstep);
		} else if (type == Double.class || type == double.class) {
			start = Double.valueOf(pstart);
			min = Double.valueOf(pmin);
			max = Double.valueOf(pmax);
			step = Double.valueOf(pstep);
		} else if (type == BigDecimal.class) {
			start = new BigDecimal(pstart);
			min = new BigDecimal(pmin);
			max = new BigDecimal(pmax);
			step = new BigDecimal(pstep);
		} else if (type == BigInteger.class) {
			start = new BigInteger(Integer.toString(pstart), 10);
			min = new BigInteger(Integer.toString(pmin), 10);
			max = new BigInteger(Integer.toString(pmax), 10);
			step = new BigInteger(Integer.toString(pstep), 10);
		}

		SpinnerModel model = new SpinnerNumberModel(start, min, max, step);

		spinner.setModel(model);

		formatSpinner();
	}
}

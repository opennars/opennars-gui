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

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Comparator;


/**
 * ExtendedPropertyDescriptor. <br>
 * 
 */
public class ExtendedPropertyDescriptor extends PropertyDescriptor {

	private Class<?> tableCellRendererClass = null;
	private String category = "";

	public ExtendedPropertyDescriptor(String propertyName, Class<?> beanClass) throws IntrospectionException {
		super(propertyName, beanClass);
	}

	public ExtendedPropertyDescriptor(String propertyName, Method getter, Method setter) throws IntrospectionException {
		super(propertyName, getter, setter);
	}

	public ExtendedPropertyDescriptor(String propertyName, Class<?> beanClass, String getterName, String setterName) throws IntrospectionException {
		super(propertyName, beanClass, getterName, setterName);
	}

	/**
	 * Sets this property category
	 * 
	 * @param category
	 * @return this property for chaining calls.
	 */
	public ExtendedPropertyDescriptor setCategory(String category) {
		this.category = category;
		return this;
	}

	/**
	 * @return the category in which this property belongs
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Force this property to be readonly
	 * 
	 * @return this property for chaining calls.
	 */
	public ExtendedPropertyDescriptor setReadOnly() {
		try {
			setWriteMethod(null);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * You can associate a special tablecellrenderer with a particular Property.
	 * If set to null default renderer will be used.
	 * 
	 * @param tableCellRendererClass
	 */
	public void setPropertyTableRendererClass(Class<?> tableCellRendererClass) {
		this.tableCellRendererClass = tableCellRendererClass;
	}

	/**
	 * @return null or a custom TableCellRenderer-Class for this property
	 */
	public Class<?> getPropertyTableRendererClass() {
		return this.tableCellRendererClass;
	}

	public static ExtendedPropertyDescriptor newPropertyDescriptor(String propertyName, Class<?> beanClass) throws IntrospectionException {
		// the same initialization phase as in the PropertyDescriptor
		Method readMethod = BeanUtils.getReadMethod(beanClass, propertyName);
		Method writeMethod = null;

		if (readMethod == null) {
			throw new IntrospectionException(
			"No getter for property "
			+ propertyName
			+ " in class "
			+ beanClass.getName());
		}

		writeMethod =
		BeanUtils.getWriteMethod(
		beanClass,
		propertyName,
		readMethod.getReturnType());

		return new ExtendedPropertyDescriptor(
		propertyName,
		readMethod,
		writeMethod);
	}

	public static final Comparator<PropertyDescriptor> BY_CATEGORY_COMPARATOR = new Comparator<PropertyDescriptor>() {

		@Override
		public int compare(PropertyDescriptor desc1, PropertyDescriptor desc2) {

			if (desc1 == null && desc2 == null) {
				return 0;
			} else if (desc1 != null && desc2 == null) {
				return 1;
			} else if (desc1 == null && desc2 != null) {
				return -1;
			} else {
				if (desc1 instanceof ExtendedPropertyDescriptor && !(desc2 instanceof ExtendedPropertyDescriptor)) {
					return -1;
				} else if (!(desc1 instanceof ExtendedPropertyDescriptor) && desc2 instanceof ExtendedPropertyDescriptor) {
					return 1;
				} else if (!(desc1 instanceof ExtendedPropertyDescriptor) && !(desc2 instanceof ExtendedPropertyDescriptor)) {
					return String.CASE_INSENSITIVE_ORDER.compare(desc1.getDisplayName(), desc2.getDisplayName());
				} else {
					int category =
					String.CASE_INSENSITIVE_ORDER.compare(
					((ExtendedPropertyDescriptor) desc1).getCategory() == null
					? ""
					: ((ExtendedPropertyDescriptor) desc1).getCategory(),
					((ExtendedPropertyDescriptor) desc2).getCategory() == null
					? ""
					: ((ExtendedPropertyDescriptor) desc2).getCategory());

					if (category == 0) {
						return String.CASE_INSENSITIVE_ORDER.compare(
						desc1.getDisplayName(),
						desc2.getDisplayName());
					} else {
						return category;
					}
				}
			}
		}
	};

}

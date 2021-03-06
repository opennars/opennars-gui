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

import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;

import automenta.vivisect.swing.property.util.ResourceManager;


/**
 * A convenient class to build bean info by adding and removing properties.
 */
public class BaseBeanInfo extends SimpleBeanInfo {

	private Class<?> type;

	private BeanDescriptor beanDescriptor;

	private List<PropertyDescriptor> properties = new ArrayList<PropertyDescriptor>();

	public BaseBeanInfo(Class<?> type) {
		this.type = type;
	}

	public final Class<?> getType() {
		return type;
	}

	public ResourceManager getResources() {
		return ResourceManager.get(getType());
	}

	@Override
	public BeanDescriptor getBeanDescriptor() {
		if (beanDescriptor == null) {
			beanDescriptor = new DefaultBeanDescriptor(this);
		}
		return beanDescriptor;
	}

	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		return properties.toArray(new PropertyDescriptor[0]);
	}

	public int getPropertyDescriptorCount() {
		return properties.size();
	}

	public PropertyDescriptor getPropertyDescriptor(int index) {
		return properties.get(index);
	}

	protected PropertyDescriptor addPropertyDescriptor(PropertyDescriptor property) {
		properties.add(property);
		return property;
	}

	public ExtendedPropertyDescriptor addProperty(String propertyName) {
		ExtendedPropertyDescriptor descriptor;
		try {
			if (propertyName == null || propertyName.trim().length() == 0) {
				throw new IntrospectionException("bad property name");
			}

			descriptor = ExtendedPropertyDescriptor
			.newPropertyDescriptor(propertyName, getType());

			try {
				descriptor.setDisplayName(getResources().getString(propertyName));
			} catch (MissingResourceException e) {
				// ignore, the resource may not be provided
			}
			try {
				descriptor.setShortDescription(
				getResources().getString(
				propertyName + ".shortDescription"));
			} catch (MissingResourceException e) {
				// ignore, the resource may not be provided
			}
			addPropertyDescriptor(descriptor);
			return descriptor;
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes the first occurrence of the property named
	 * <code>propertyName</code>
	 * 
	 * @param propertyName
	 * @return the removed PropertyDescriptor or null if not found.
	 */
	public PropertyDescriptor removeProperty(String propertyName) {
		if (propertyName == null) {
			throw new IllegalArgumentException("Property name can not be null");
		}

		Iterator<PropertyDescriptor> pdi = properties.iterator();

		while (pdi.hasNext()) {

			PropertyDescriptor property = pdi.next();
			if (propertyName.equals(property.getName())) {

				// remove the property from the list
				pdi.remove();
				return property;
			}
		}
		return null;
	}

	/**
	 * Get the icon for displaying this bean.
	 * 
	 * @param kind Kind of icon.
	 * @return Image for bean, or null if none.
	 */
	@Override
	public Image getIcon(int kind) {
		return null;
	}

	/**
	 * Return a text describing the object.
	 * 
	 * @param value an <code>Object</code> value
	 * @return a text describing the object.
	 */
	public String getText(Object value) {
		return value.toString();
	}

	/**
	 * Return a text describing briefly the object. The text will be used
	 * whereever a explanation is needed to give to the user
	 * 
	 * @param value an <code>Object</code> value
	 * @return a <code>String</code> value
	 */
	public String getDescription(Object value) {
		return getText(value);
	}

	/**
	 * Return a text describing the object. The text will be displayed in a
	 * tooltip.
	 * 
	 * @param value an <code>Object</code> value
	 * @return a <code>String</code> value
	 */
	public String getToolTipText(Object value) {
		return getText(value);
	}

}

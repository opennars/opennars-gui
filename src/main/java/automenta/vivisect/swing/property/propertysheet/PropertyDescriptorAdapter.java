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
package automenta.vivisect.swing.property.propertysheet;

import java.beans.PropertyDescriptor;
import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import automenta.vivisect.swing.property.beans.ExtendedPropertyDescriptor;


/**
 * PropertyDescriptorAdapter
 * 
 */
class PropertyDescriptorAdapter extends AbstractProperty {

	private static final long serialVersionUID = 5032245762634340773L;

	private PropertyDescriptor descriptor;

	public PropertyDescriptorAdapter() {
		super();
	}

	public PropertyDescriptorAdapter(PropertyDescriptor descriptor) {
		this();
		setDescriptor(descriptor);
	}

	public void setDescriptor(PropertyDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public PropertyDescriptor getDescriptor() {
		return descriptor;
	}

	public String getName() {
		return descriptor.getName();
	}

	public String getDisplayName() {
		return descriptor.getDisplayName();
	}

	public String getShortDescription() {
		return descriptor.getShortDescription();
	}

	public Class<?> getType() {
		return descriptor.getPropertyType();
	}

	@Override
	public Object clone() {
		PropertyDescriptorAdapter clone = new PropertyDescriptorAdapter(descriptor);
		clone.setValue(getValue());
		return clone;
	}

	public void readFromObject(Object object) {
		try {
			Method method = descriptor.getReadMethod();
			if (method != null) {
				setValue(method.invoke(object, (Object[]) null));
			}
		} catch (Exception e) {
			String message = "Got exception when reading property " + getName();
			if (object == null) {
				message += ", object was 'null'";
			} else {
				message += ", object was " + String.valueOf(object);
			}
			throw new RuntimeException(message, e);
		}
	}

	public void writeToObject(Object object) {
		try {
			Method method = descriptor.getWriteMethod();
			if (method != null) {
				method.invoke(object, getValue());
			}
		} catch (Exception e) {
			// let PropertyVetoException go to the upper level without logging
			if (e instanceof InvocationTargetException &&
			((InvocationTargetException) e).getTargetException() instanceof PropertyVetoException) {
				throw new RuntimeException(((InvocationTargetException) e).getTargetException());
			}

			String message = "Got exception when writing property " + getName();
			if (object == null) {
				message += ", object was 'null'";
			} else {
				message += ", object was " + String.valueOf(object);
			}
			throw new RuntimeException(message, e);
		}
	}

	public boolean isEditable() {
		return descriptor.getWriteMethod() != null;
	}

	public String getCategory() {
		if (descriptor instanceof ExtendedPropertyDescriptor) {
			return ((ExtendedPropertyDescriptor) descriptor).getCategory();
		} else {
			return null;
		}
	}

}

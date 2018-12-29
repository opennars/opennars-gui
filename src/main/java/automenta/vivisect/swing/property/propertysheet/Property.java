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

import java.beans.PropertyChangeListener;
import java.io.Serializable;


/**
 * Property. <br>
 * Component of a PropertySheet, based on the java.beans.PropertyDescriptor for
 * easy wrapping of beans in PropertySheet.
 */
public interface Property extends Serializable, Cloneable {

	String getName();

	String getDisplayName();

	String getShortDescription();

	Class<?> getType();

	Object getValue();

	void setValue(Object value);

	boolean isEditable();

	String getCategory();

	void readFromObject(Object object);

	void writeToObject(Object object);

	void addPropertyChangeListener(PropertyChangeListener listener);

	void removePropertyChangeListener(PropertyChangeListener listener);

	Object clone();

	Property getParentProperty();

	Property[] getSubProperties();
}

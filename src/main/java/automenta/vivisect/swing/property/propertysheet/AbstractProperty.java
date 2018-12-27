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
import java.beans.PropertyChangeSupport;
import java.io.IOException;

/**
 * AbstractProperty
 *
 */
public abstract class AbstractProperty implements Property {

  private Object value;
  
  // PropertyChangeListeners are not serialized.
  private transient PropertyChangeSupport listeners =
    new PropertyChangeSupport(this);

  public Object getValue() {
    return value;
  }

  public Object clone() {
    AbstractProperty clone = null;
    try {
      clone = (AbstractProperty)super.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);      
    }
  }
  
  public void setValue(Object value) {
    Object oldValue = this.value;
    this.value = value;
    if (value != oldValue && (value == null || !value.equals(oldValue)))
      firePropertyChange(oldValue, getValue());
  }

  protected void initializeValue(Object value) {
    this.value = value;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    listeners.addPropertyChangeListener(listener);
    Property[] subProperties = getSubProperties();
    if (subProperties != null)
        for (Property subProperty : subProperties) subProperty.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    listeners.removePropertyChangeListener(listener);
    Property[] subProperties = getSubProperties();
    if (subProperties != null)
        for (Property subProperty : subProperties) subProperty.removePropertyChangeListener(listener);
  }

  protected void firePropertyChange(Object oldValue, Object newValue) {
    listeners.firePropertyChange("value", oldValue, newValue);
  }

  private void readObject(java.io.ObjectInputStream in) throws IOException,
    ClassNotFoundException {
    in.defaultReadObject();
    listeners = new PropertyChangeSupport(this);    
  }
  
  public Property getParentProperty() {
  	return null;
  }
  
  public Property[] getSubProperties() {
  	return null;
  }
}

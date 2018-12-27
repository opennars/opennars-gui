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
package automenta.vivisect.swing.property.beans.editor;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyEditor;

/**
 * AbstractPropertyEditor. <br>
 *  
 */
public class AbstractPropertyEditor implements PropertyEditor {

  protected Component editor;
  private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

  public boolean isPaintable() {
    return false;
  }

  public boolean supportsCustomEditor() {
    return false;
  }

  public Component getCustomEditor() {
    return editor;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    listeners.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    listeners.removePropertyChangeListener(listener);
  }

  protected void firePropertyChange(Object oldValue, Object newValue) {
    listeners.firePropertyChange("value", oldValue, newValue);
  }
    
  public Object getValue() {
    return null;
  }

  public void setValue(Object value) {
  }

  public String getAsText() {
    return null;
  }

  public String getJavaInitializationString() {
    return null;
  }

  public String[] getTags() {
    return null;
  }

  public void setAsText(String text) throws IllegalArgumentException {
  }

  public void paintValue(Graphics gfx, Rectangle box) {
  }

}

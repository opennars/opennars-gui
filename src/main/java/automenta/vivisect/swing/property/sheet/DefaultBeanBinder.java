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
package automenta.vivisect.swing.property.sheet;

import java.beans.BeanInfo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;

import javax.swing.UIManager;

import automenta.vivisect.swing.property.model.DefaultBeanInfoResolver;
import automenta.vivisect.swing.property.propertysheet.Property;
import automenta.vivisect.swing.property.propertysheet.PropertySheetPanel;


/**
 * Binds a bean object to a PropertySheet.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class DefaultBeanBinder implements PropertyChangeListener {

	private Object object = null;
	private BeanInfo info = null;
	private PropertySheetPanel sheet = null;

	public DefaultBeanBinder(Object object, PropertySheetPanel sheet) {
		this(object, sheet, new DefaultBeanInfoResolver().getBeanInfo(object));
	}

	public DefaultBeanBinder(Object object, PropertySheetPanel sheet, BeanInfo info) {

		if (info == null) {
			throw new IllegalArgumentException(String.format("Cannot find %s for %s", BeanInfo.class.getSimpleName(), object.getClass()));
		}

		this.object = object;
		this.sheet = sheet;
		this.info = info;

		bind();
	}

	public void bind() {
		sheet.setProperties(info.getPropertyDescriptors());
		sheet.readFromObject(object);
		sheet.addPropertySheetChangeListener(this);
	}

	public void unbind() {
		sheet.removePropertyChangeListener(this);
		sheet.setProperties(new Property[0]);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {

		Property prop = (Property) event.getSource();

		try {
			prop.writeToObject(object);
		} catch (RuntimeException e) {

			if (e.getCause() instanceof PropertyVetoException) {
				UIManager.getLookAndFeel().provideErrorFeedback(sheet);
				prop.setValue(event.getOldValue());
			} else {
				throw e;
			}
		}
	}
}

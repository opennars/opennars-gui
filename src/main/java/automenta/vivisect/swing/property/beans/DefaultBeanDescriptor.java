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

import java.beans.BeanDescriptor;
import java.util.MissingResourceException;




/**
 * Default bean descriptor.
 */
public class DefaultBeanDescriptor extends BeanDescriptor {

	private String displayName;

	public DefaultBeanDescriptor(BaseBeanInfo info) {

		super(info.getType());

		try {
			setDisplayName(info.getResources().getString("beanName"));
		} catch (MissingResourceException e) {
			// fall thru, this resource is not mandatory
		}

		try {
			setShortDescription(info.getResources().getString("beanDescription"));
		} catch (MissingResourceException e) {
			// fall thru, this resource is not mandatory
		}
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void setDisplayName(String name) {
		displayName = name;
	}

}

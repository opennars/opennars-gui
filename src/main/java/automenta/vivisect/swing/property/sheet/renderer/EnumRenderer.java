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
package automenta.vivisect.swing.property.sheet.renderer;

import automenta.vivisect.swing.property.sheet.I18N;
import automenta.vivisect.swing.property.swing.renderer.DefaultCellRenderer;


/**
 * Enumeration value renderer.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class EnumRenderer extends DefaultCellRenderer {

	private static final long serialVersionUID = 6826062487749986507L;

	@Override
	protected String convertToString(Object value) {
		if (value == null) {
			return I18N.NOT_SET;
		} else {
			return value.toString();
		}
	}
}

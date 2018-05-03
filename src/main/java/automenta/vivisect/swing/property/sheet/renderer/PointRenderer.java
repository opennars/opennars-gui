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

import java.awt.Point;
import java.text.NumberFormat;

import automenta.vivisect.swing.property.swing.renderer.DefaultCellRenderer;


public class PointRenderer extends DefaultCellRenderer {

	private static final long serialVersionUID = -777052685333950693L;

	@Override
	protected String convertToString(Object value) {
		Point p = (Point) value;
		NumberFormat format = NumberFormat.getInstance();
		String x = format.format(p.x);
		String y = format.format(p.y);
		return String.format("[%s; %s]", x, y);
	}

}

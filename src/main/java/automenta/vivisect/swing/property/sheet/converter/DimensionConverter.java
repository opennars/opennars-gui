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
package automenta.vivisect.swing.property.sheet.converter;

import java.awt.Dimension;

import automenta.vivisect.swing.property.sheet.Converter;


public class DimensionConverter implements Converter<Dimension> {

	@Override
	public Dimension toObject(String string) {
		string = string.trim();
		String[] parts = string.split("([^0-9])+");
		if (parts.length == 2) {
			int width = Integer.parseInt(parts[0].trim());
			int height = Integer.parseInt(parts[1].trim());
			return new Dimension(width, height);
		} else {
			throw new NumberFormatException("Incorrect dimension format");
		}
	}

	@Override
	public String toString(Dimension dimension) {
		return String.format("%d \u00D7 %d", dimension.width, dimension.height);
	}

}

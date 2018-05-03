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
package automenta.vivisect.swing.property.sheet.editor;

import java.awt.Dimension;

import automenta.vivisect.swing.property.beans.editor.StringConverterPropertyEditor;
import automenta.vivisect.swing.property.sheet.Converter;
import automenta.vivisect.swing.property.sheet.converter.DimensionConverter;


/**
 * DimensionPropertyEditor. <br>
 * Editor for java.awt.Dimension object, where the dimension is specified as
 * "width x height"
 */
public class DimensionEditor extends StringConverterPropertyEditor {

	private Converter<Dimension> converter = new DimensionConverter();

	@Override
	protected Object convertFromString(String text) {
		return converter.toObject(text);
	}

	@Override
	protected String convertToString(Object value) {
		return converter.toString((Dimension) value);
	}
}

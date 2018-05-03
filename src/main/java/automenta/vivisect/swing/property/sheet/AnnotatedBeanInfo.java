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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableCellRenderer;

import automenta.vivisect.swing.property.beans.BaseBeanInfo;
import automenta.vivisect.swing.property.beans.ExtendedPropertyDescriptor;
import automenta.vivisect.swing.property.beans.editor.AbstractPropertyEditor;
import automenta.vivisect.swing.property.sheet.annotation.PropertyInfo;


public class AnnotatedBeanInfo extends BaseBeanInfo {

	private static final class PropertyPair {

		private PropertyInfo property;
		private Field field;

		public PropertyPair(PropertyInfo property, Field field) {
			super();
			this.property = property;
			this.field = field;
		}

		public PropertyInfo getProperty() {
			return property;
		}

		public Field getField() {
			return field;
		}

	}

	private static final String DEFAULT_CATEGORY = "General";

	public AnnotatedBeanInfo(Class<?> type) {

		super(type);

		for (PropertyPair pair : getProperties(type)) {

			PropertyInfo property = pair.getProperty();
			Field field = pair.getField();

			String name = field.getName();

			ExtendedPropertyDescriptor descriptor = addProperty(name);

			String displayName = property.name();
			if ("#default".equals(displayName)) {
				displayName = name;
			}

			descriptor.setDisplayName(displayName);

			String category = property.category();
			if (category.isEmpty()) {
				category = DEFAULT_CATEGORY;
			}

			descriptor.setCategory(category);

			String description = property.description();
			if (!description.isEmpty()) {
				descriptor.setShortDescription(description);
			}

			Class<? extends AbstractPropertyEditor> editorClass = property.editor();
			if (editorClass != AbstractPropertyEditor.class) {
				descriptor.setPropertyEditorClass(editorClass);
			}

			Class<? extends TableCellRenderer> renderer = property.renderer();
			if (renderer != TableCellRenderer.class) {
				descriptor.setPropertyTableRendererClass(renderer);
			}

			if (property.readonly()) {
				descriptor.setReadOnly();
			}

			descriptor.setExpert(property.expert());
			descriptor.setPreferred(property.important());
			descriptor.setConstrained(property.constrained());
			descriptor.setHidden(property.hidden());
			descriptor.setBound(true);
		}
	}

	private List<PropertyPair> getProperties(Class<?> type) {

		List<PropertyPair> pairs = new ArrayList<PropertyPair>();
		Field[] fields = type.getDeclaredFields();

		for (Field field : fields) {
			PropertyInfo property = field.getAnnotation(PropertyInfo.class);
			if (property != null) {
				pairs.add(new PropertyPair(property, field));
			}
		}

		return pairs;

	}
}

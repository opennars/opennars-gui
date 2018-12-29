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
package automenta.vivisect.swing.property.sheet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.swing.table.TableCellRenderer;

import automenta.vivisect.swing.property.beans.editor.AbstractPropertyEditor;
import automenta.vivisect.swing.property.sheet.AnnotatedBeanInfo;


/**
 * This annotation used together with {@link AnnotatedBeanInfo} expose only
 * those properties which are annotated with.
 * 
 * @author Bartosz Firyn (SarXos)
 * @see AnnotatedBeanInfo
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface PropertyInfo {

	String name() default "#default";

	String category() default "";

	String description() default "";

	Class<? extends AbstractPropertyEditor> editor() default AbstractPropertyEditor.class;

	Class<? extends TableCellRenderer> renderer() default TableCellRenderer.class;

	boolean readonly() default false;

	boolean expert() default false;

	boolean important() default false;

	boolean constrained() default false;

	boolean hidden() default false;
}

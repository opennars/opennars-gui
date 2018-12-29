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
package automenta.vivisect.swing.property;

import java.beans.PropertyEditor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
	/**
	 * @return The name of the field (to be presented by the GUI). If not set, the name of the
	 * variable will be used.
	 */
	String name() default "";
	
	/**
	 * The description will provide further information on this field (seen in the configuration panel)
	 * @return The description of the field. If not set, the <b>name</b> parameter will be used.
	 */
    String description() default "";
    
    /**
     * You can use categories to group various properties that are somehow related
     * @return The category for this configuration field
     */
    String category() default "";
    
    boolean editable() default true;
    
    /**
     * The (full) class name of the editor to be used for this property
     */
    Class<? extends PropertyEditor> editorClass() default PropertyEditor.class;
}

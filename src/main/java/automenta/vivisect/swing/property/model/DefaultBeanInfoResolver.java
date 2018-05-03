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
package automenta.vivisect.swing.property.model;

import java.beans.BeanInfo;

import automenta.vivisect.swing.property.beans.BeanInfoResolver;

/**
 * DefaultBeanInfoResolver. <br>
 *  
 */
public class DefaultBeanInfoResolver implements BeanInfoResolver {

  public DefaultBeanInfoResolver() {
    super();
  }

  public BeanInfo getBeanInfo(Object object) {
    if (object == null) {
      return null;
    }

    return getBeanInfo(object.getClass());
  }

  public BeanInfo getBeanInfo(Class clazz) {
    if (clazz == null) {
      return null;
    }

    String classname = clazz.getName();

    // look for .impl.basic., remove it and call getBeanInfo(class)
    int index = classname.indexOf(".impl.basic");
    if (index != -1 && classname.endsWith("Basic")) {
      classname =
        classname.substring(0, index)
          + classname.substring(
            index + ".impl.basic".length(),
            classname.lastIndexOf("Basic"));
      try {
        return getBeanInfo(Class.forName(classname));
      } catch (ClassNotFoundException e) {
        return null;
      }
    } else {
      try {
        BeanInfo beanInfo =
          (BeanInfo)Class.forName(classname + "BeanInfo").newInstance();
        return beanInfo;
      } catch (Exception e) {
        return null;
      }
    }
  }

}

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
package automenta.vivisect.swing.property.propertysheet;

import java.util.Iterator;

/**
 * PropertySheet.<br>
 *
 */
public interface PropertySheet {

  public final static int VIEW_AS_FLAT_LIST = 0;
  
  public final static int VIEW_AS_CATEGORIES = 1;

  public void setProperties(Property[] properties);
  
  public Property[] getProperties();
  
  public void addProperty(Property property);
  
  public void addProperty(int index, Property property);

  public void removeProperty(Property property);
  
  public int getPropertyCount();
  
  public Iterator propertyIterator();
  
}

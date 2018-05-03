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
package automenta.vivisect.swing.property.swing.renderer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A renderer for Date.
 *  
 * @author Ricardo Lopes
 */
public class DateRenderer extends DefaultCellRenderer {

  private DateFormat dateFormat;

  public DateRenderer() {
    this(DateFormat.getDateInstance(DateFormat.SHORT));
  }

  public DateRenderer(String formatString) {
    this(formatString, Locale.getDefault());
  }

  public DateRenderer(Locale l) {
    this(DateFormat.getDateInstance(DateFormat.SHORT, l));
  }

  public DateRenderer(String formatString, Locale l) {
    this(new SimpleDateFormat(formatString, l));
  }

  public DateRenderer(DateFormat dateFormat) {
    this.dateFormat = dateFormat;
  }

  public void setValue(Object value) {
    if (value == null) {
      setText("");
    } else {
      setText(dateFormat.format((Date)value));
    }
  }
  
}
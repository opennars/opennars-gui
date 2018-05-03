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
package automenta.vivisect.swing.property.beans.editor;

/**
 * Date Property Editor based on <a
 * href="http://www.toedter.com/en/jcalendar/index.html">toedter
 * JCalendar </a> component. <br>
 */
public class JCalendarDatePropertyEditor extends AbstractPropertyEditor {

//  /**
//   * Constructor for JCalendarDatePropertyEditor
//   */
//  public JCalendarDatePropertyEditor() {
//    editor = new JDateChooser();
//  }
//
//  /**
//   * Constructor for JCalendarDatePropertyEditor
//   * 
//   * @param dateFormatString string used to format the Date object,
//   *          see: <b>java.text.SimpleDateFormat </b>
//   * 
//   * @param locale Locale used to display the Date object
//   */
//  public JCalendarDatePropertyEditor(String dateFormatString, Locale locale) {
//    editor = new JDateChooser();
//    ((JDateChooser)editor).setDateFormatString(dateFormatString);
//    ((JDateChooser)editor).setLocale(locale);
//  }
//
//  /**
//   * Constructor for JCalendarDatePropertyEditor
//   * 
//   * @param locale Locale used to display the Date object
//   */
//  public JCalendarDatePropertyEditor(Locale locale) {
//    editor = new JDateChooser();
//    ((JDateChooser)editor).setLocale(locale);
//  }
//
//  /**
//   * Returns the Date of the Calendar
//   * 
//   * @return the date choosed as a <b>java.util.Date </b>b> object or
//   *         null is the date is not set
//   */
//  public Object getValue() {
//    return ((JDateChooser)editor).getDate();
//  }
//
//  /**
//   * Sets the Date of the Calendar
//   * 
//   * @param value the Date object
//   */
//  public void setValue(Object value) {
//    if (value != null) {
//      ((JDateChooser)editor).setDate((Date)value);
//    }
//  }
//
//  /**
//   * Returns the Date formated with the locale and formatString set.
//   * 
//   * @return the choosen Date as String
//   */
//  public String getAsText() {
//    Date date = (Date)getValue();
//    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
//      getDateFormatString());
//    String s = formatter.format(date);
//    return s;
//  }
//
//  /**
//   * Sets the date format string. E.g "MMMMM d, yyyy" will result in
//   * "July 21, 2004" if this is the selected date and locale is
//   * English.
//   * 
//   * @param dateFormatString The dateFormatString to set.
//   */
//  public void setDateFormatString(String dateFormatString) {
//    ((JDateChooser)editor).setDateFormatString(dateFormatString);
//  }
//
//  /**
//   * Gets the date format string.
//   * 
//   * @return Returns the dateFormatString.
//   */
//  public String getDateFormatString() {
//    return ((JDateChooser)editor).getDateFormatString();
//  }
//
//  /**
//   * Sets the locale.
//   * 
//   * @param l The new locale value
//   */
//  public void setLocale(Locale l) {
//    ((JDateChooser)editor).setLocale(l);
//  }
//
//  /**
//   * Returns the Locale used.
//   * 
//   * @return the Locale object
//   */
//  public Locale getLocale() {
//    return ((JDateChooser)editor).getLocale();
//  }
  
}
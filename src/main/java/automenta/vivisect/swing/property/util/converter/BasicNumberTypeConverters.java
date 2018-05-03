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
package automenta.vivisect.swing.property.util.converter;

import java.text.NumberFormat;

public class BasicNumberTypeConverters implements Converter {

  private static NumberFormat defaultFormat;

  private NumberFormat format;

  public BasicNumberTypeConverters() {
    this(getDefaultFormat());
  }

  public BasicNumberTypeConverters(NumberFormat format) {
    this.format = format;
  }

  public static NumberFormat getDefaultFormat() {
    synchronized (BasicNumberTypeConverters.class) {
      if (defaultFormat == null) {
        defaultFormat = NumberFormat.getNumberInstance();
        defaultFormat.setMinimumIntegerDigits(1);
        defaultFormat.setMaximumIntegerDigits(64);
        defaultFormat.setMinimumFractionDigits(0);
        defaultFormat.setMaximumFractionDigits(64);
        defaultFormat.setGroupingUsed(false);
      }
    }
    return defaultFormat;
  }

  public void register(ConverterRegistry registry) {

    registry.addConverter(double.class, String.class, this);
    registry.addConverter(float.class, String.class, this);
    registry.addConverter(int.class, String.class, this);
    registry.addConverter(long.class, String.class, this);
    registry.addConverter(short.class, String.class, this);
    registry.addConverter(byte.class, String.class, this);
    
    registry.addConverter(String.class, double.class, this);
    registry.addConverter(String.class, float.class, this);
    registry.addConverter(String.class, int.class, this);
    registry.addConverter(String.class, long.class, this);
    registry.addConverter(String.class, short.class, this);
    registry.addConverter(String.class, byte.class, this);
  }

  public Object convert(Class targetType, Object value) {
	  
	  if (targetType.equals(String.class))
		  return ""+value;
	  switch (targetType.getSimpleName()) {
	case "int":
		return Integer.parseInt(""+value);
	case "long":
		return Long.parseLong(""+value);
	case "short":
		return Short.parseShort(""+value);
	case "float":
		return Float.parseFloat(""+value);
	case "double":
		return Double.parseDouble(""+value);
	case "byte":
		return Byte.parseByte(""+value);
		
	default:
		break;
	}
    throw new IllegalArgumentException("no conversion supported for "+targetType.getSimpleName());
  }

}
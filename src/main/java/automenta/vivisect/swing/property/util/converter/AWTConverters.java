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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.StringTokenizer;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;


/**
 * AWTConverters. <br>
 * Converter commonly used AWT classes like Point, Dimension, Rectangle, Insets
 * to/from Strings and between each others when possible.
 *
 */
// Robert Wünsche: I removed all of the buggy documentation because it breaks javadocs
public class AWTConverters implements Converter {

	public AWTConverters() {
		super();
	}

	public void register(ConverterRegistry registry) {
		registry.addConverter(Dimension.class, String.class, this);
		registry.addConverter(String.class, Dimension.class, this);
		registry.addConverter(DimensionUIResource.class, String.class, this);

		registry.addConverter(Insets.class, String.class, this);
		registry.addConverter(String.class, Insets.class, this);
		registry.addConverter(InsetsUIResource.class, String.class, this);

		registry.addConverter(Point.class, String.class, this);
		registry.addConverter(String.class, Point.class, this);

		registry.addConverter(Rectangle.class, String.class, this);
		registry.addConverter(String.class, Rectangle.class, this);

		registry.addConverter(Font.class, String.class, this);
		registry.addConverter(FontUIResource.class, String.class, this);
		
		registry.addConverter(Color.class, String.class, this);
		registry.addConverter(String.class, Color.class, this);
	}

	@Override
	public Object convert(Class type, Object value) {
		if (String.class.equals(type)) {
			if (value instanceof Rectangle) {
				return ((Rectangle) value).x
						+ " "
						+ ((Rectangle) value).y
						+ " "
						+ ((Rectangle) value).width
						+ " "
						+ ((Rectangle) value).height;
			} else if (value instanceof Insets) {
				return ((Insets) value).top
						+ " "
						+ ((Insets) value).left
						+ " "
						+ ((Insets) value).bottom
						+ " "
						+ ((Insets) value).right;
			} else if (value instanceof Dimension) {
				return ((Dimension) value).width
						+ " x "
						+ ((Dimension) value).height;
			} else if (Point.class.equals(value.getClass())) {
				return ((Point) value).x + " " + ((Point) value).y;
			} else if (value instanceof Font) {
				return ((Font) value).getFontName()
						+ ", "
						+ ((Font) value).getStyle()
						+ ", "
						+ ((Font) value).getSize();
			}
			else if (value instanceof Color) {
				return ((Color) value).getRGB()+"";				
			}
		}

		if (value instanceof String) {
			if (Rectangle.class.equals(type)) {
				double[] values = convert((String) value, 4, " ");
				if (values == null) {
					throw new IllegalArgumentException("Invalid format");
				}
				Rectangle rect = new Rectangle();
				rect.setFrame(values[0], values[1], values[2], values[3]);
				return rect;
			} else if (Insets.class.equals(type)) {
				double[] values = convert((String) value, 4, " ");
				if (values == null) {
					throw new IllegalArgumentException("Invalid format");
				}
				return new Insets(
						(int) values[0],
						(int) values[1],
						(int) values[2],
						(int) values[3]);
			} else if (Dimension.class.equals(type)) {
				double[] values = convert((String) value, 2, "x");
				if (values == null) {
					throw new IllegalArgumentException("Invalid format");
				}
				Dimension dim = new Dimension();
				dim.setSize(values[0], values[1]);
				return dim;
			} else if (Point.class.equals(type)) {
				double[] values = convert((String) value, 2, " ");
				if (values == null) {
					throw new IllegalArgumentException("Invalid format");
				}
				Point p = new Point();
				p.setLocation(values[0], values[1]);
				return p;
			} else if (Color.class.equals(type)) {
				int rgb = Integer.parseInt(value.toString());
				return new Color(rgb);				
			}
		}
		return null;
	}

	private double[] convert(String text, int tokenCount, String delimiters) {
		StringTokenizer tokenizer = new StringTokenizer(text, delimiters);
		if (tokenizer.countTokens() != tokenCount) {
			return null;
		}

		try {
			double[] values = new double[tokenCount];
			for (int i = 0; tokenizer.hasMoreTokens(); i++) {
				values[i] = Double.parseDouble(tokenizer.nextToken());
			}
			return values;
		} catch (Exception e) {
			return null;
		}
	}

}

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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.Icon;
import javax.swing.UIManager;

/**
 * ColorCellRenderer.
 */
public class ColorCellRenderer extends DefaultCellRenderer {

  public static String toHex(Color color) {
    String red = Integer.toHexString(color.getRed());
    String green = Integer.toHexString(color.getGreen());
    String blue = Integer.toHexString(color.getBlue());

    if (red.length() == 1) {
      red = "0" + red;
    }
    if (green.length() == 1) {
      green = "0" + green;
    }
    if (blue.length() == 1) {
      blue = "0" + blue;
    }
    return ("#" + red + green + blue).toUpperCase();
  }

  protected String convertToString(Object value) {
    if (value instanceof Integer) {
      value = new Color(((Integer)value).intValue());
    }
    if (!(value instanceof Color)) { return null; }

    Color color = (Color)value;
    return "R:" + color.getRed() + " G:" + color.getGreen() + " B:"
      + color.getBlue() + " - " + toHex(color);
  }

  protected Icon convertToIcon(Object value) {
    if (value == null) { return null; }
    if (value instanceof Integer) {
      value = new Color(((Integer)value).intValue());
    }
    return new PaintIcon((Paint)value);
  }

  public static class PaintIcon implements Icon {
    private final Paint color;
    private final int width;
    private final int height;

    public PaintIcon(Paint color) {
      this(color, 20, 10);
    }

    public PaintIcon(Paint color, int width, int height) {
      this.color = color;
      this.width = width;
      this.height = height;
    }

    public int getIconHeight() {
      return height;
    }
    public int getIconWidth() {
      return width;
    }
    public void paintIcon(Component c, Graphics g, int x, int y) {
      Graphics2D g2d = (Graphics2D)g;
      Paint oldPaint = g2d.getPaint();

      if (color != null) {
        g2d.setPaint(color);
        g.fillRect(x, y, getIconWidth(), getIconHeight());
      }

      g.setColor(UIManager.getColor("controlDkShadow"));
      g.drawRect(x, y, getIconWidth(), getIconHeight());

      g2d.setPaint(oldPaint);
    }
  }
}

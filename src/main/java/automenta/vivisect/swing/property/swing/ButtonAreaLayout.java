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
package automenta.vivisect.swing.property.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * ButtonAreaLayout. <br>
 *  
 */
public final class ButtonAreaLayout implements LayoutManager {

  private int gap;

  public ButtonAreaLayout(int gap) {
    this.gap = gap;
  }

  public void addLayoutComponent(String string, Component comp) {
  }

  public void layoutContainer(Container container) {
    Insets insets = container.getInsets();
    Component[] children = container.getComponents();

    // calculate the max width
    int maxWidth = 0;
    int maxHeight = 0;
    int visibleCount = 0;
    Dimension componentPreferredSize;

      for (Component aChildren1 : children) {
          if (aChildren1.isVisible()) {
              componentPreferredSize = aChildren1.getPreferredSize();
              maxWidth = Math.max(maxWidth, componentPreferredSize.width);
              maxHeight = Math.max(maxHeight, componentPreferredSize.height);
              visibleCount++;
          }
      }

    int usedWidth = maxWidth * visibleCount + gap * (visibleCount - 1);
    
    visibleCount = 0;
      for (Component aChildren : children) {
          if (aChildren.isVisible()) {
              aChildren.setBounds(
                  container.getWidth()
                      - insets.right
                      - usedWidth
                      + (maxWidth + gap) * visibleCount,
                  insets.top,
                  maxWidth,
                  maxHeight);
              visibleCount++;
          }
      }
  }

  public Dimension minimumLayoutSize(Container c) {
    return preferredLayoutSize(c);
  }

  public Dimension preferredLayoutSize(Container container) {
    Insets insets = container.getInsets();
    Component[] children = container.getComponents();

    // calculate the max width
    int maxWidth = 0;
    int maxHeight = 0;
    int visibleCount = 0;
    Dimension componentPreferredSize;

      for (Component aChildren : children) {
          if (aChildren.isVisible()) {
              componentPreferredSize = aChildren.getPreferredSize();
              maxWidth = Math.max(maxWidth, componentPreferredSize.width);
              maxHeight = Math.max(maxHeight, componentPreferredSize.height);
              visibleCount++;
          }
      }

    int usedWidth = maxWidth * visibleCount + gap * (visibleCount - 1);

    return new Dimension(
      insets.left + usedWidth + insets.right,
      insets.top + maxHeight + insets.bottom);
  }

  public void removeLayoutComponent(Component c) {
  }
}

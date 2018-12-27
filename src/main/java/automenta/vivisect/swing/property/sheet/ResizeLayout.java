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
package automenta.vivisect.swing.property.sheet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;


public class ResizeLayout extends BorderLayout {

	private static final long serialVersionUID = -1227060876626317222L;

	public ResizeLayout() {
		super(0, 0);
	}

	@Override
	public void layoutContainer(Container target) {
		synchronized (target.getTreeLock()) {
			for (int i = 0; i < target.getComponentCount(); i++) {
				Component c = target.getComponent(i);
				Point p = c.getLocation();
				int dx = Math.abs(p.x);
				int dy = Math.abs(p.y);
				int w = target.getWidth();
				int h = target.getHeight();
				c.setBounds(p.x, p.y, w + 2 * dx, h + 2 * dy);
			}
		}
	}
}
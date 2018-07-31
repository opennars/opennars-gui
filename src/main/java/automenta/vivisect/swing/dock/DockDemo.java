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
package automenta.vivisect.swing.dock;

import automenta.vivisect.swing.NWindow;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 *
 * @author me
 * @see <a href="https://java.net/projects/raven/sources/svn/show/trunk/proj/RavenDocking/src/com/kitfox/docking/test?rev=116">link</a>
 * @see <a href="https://java.net/projects/raven/sources/svn/content/trunk/proj/RavenDocking/src/com/kitfox/docking/test/DockingTestFrame2.java?rev=116">link</a>
 */
public class DockDemo extends DockingRegionRoot {

    int index;

    public DockDemo() {
        super();
        
        DockingContent cont = new DockingContent("uid" + index, "Component " + (index++), new JButton("x"));
        
        DockingContent cont2 = new DockingContent("uid" + index, "Component " + (index++), new JButton("y"));
        
        getDockingRoot().addDockContent(cont);
        getDockingRoot().addDockContent(cont2);
    }

    public static void main(String[] args)     {
        NWindow w = new NWindow("Dock Test", new DockDemo());
        
        w.setSize(640, 480);
        w.setVisible(true);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

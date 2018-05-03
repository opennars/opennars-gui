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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * IconPool.<br>
 *
 */
public class IconPool {

  private static IconPool iconPool = new IconPool();
  
  private Map pool;
  
  public IconPool() {
    pool = new HashMap();    
  }

  public static IconPool shared() {
    return iconPool;
  }
  
  /**
   * Gets the icon denoted by url.
   * If url is relative, it is relative to the caller.
   * 
   * @param url
   * @return an icon
   */
  public Icon get(String url) {
    StackTraceElement[] stacks = new Exception().getStackTrace();
    try {
      Class callerClazz = Class.forName(stacks[1].getClassName());
      return get(callerClazz.getResource(url));
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  
  public synchronized Icon get(URL url) {
    if (url == null) {
      return null;
    }
    
    Icon icon = (Icon)pool.get(url.toString());
    if (icon == null) {
      icon = new ImageIcon(url);
      pool.put(url.toString(), icon);
    }
    return icon;
  }
  
  public synchronized void clear() {
    pool.clear();
  }
  
}

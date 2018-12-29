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
package automenta.vivisect.swing.property;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Date;

public class PropsTest implements PropertyChangeListener {

	@Property
	byte x = 23;
	
	@Property
	long y = 10;
	
	@Property
	Rectangle r = new Rectangle(10, 20);
	
	@Property(category="Advanced stuff!", name="A file", editable=false)
	File f = new File("/home/zp/Destop/x.tdt");
	
	@Property(name="This is the date", description="Enter any date you want")
	Date date = new Date();
	
	@Property
	float d = 10.0f;
	
	@Property
	String s = "sdfsdf";
	
	@Property
	Line2D.Double line = new Line2D.Double(10, 10, 11, 11);
	
	public static void main(String[] args) {
		PropsTest pt = new PropsTest();
		/*if (new File("/home/zp/Desktop/pt.props").canRead())
			PropertyUtils.loadProperties(pt, new File("/home/zp/Desktop/pt.props"), false);*/
		PropertyUtils.editProperties(null, pt, true);
		//PropertyUtils.saveProperties(pt, new File("/home/zp/Desktop/pt.props"));
		//System.out.println(PropertyUtils.saveProperties(pt));
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt);
	}
}

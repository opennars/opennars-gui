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
package automenta.vivisect.face;


import java.awt.Image;
import java.util.StringTokenizer;

public class FaceFrame extends Object {
    private double[][] targets;
    private Image snapshot;
    private double when;

    public FaceFrame(double[][] t, Image snap, double w) {
	targets = new double[2][t[0].length];
	for(int s = 0; s < 2; s++)
	    System.arraycopy(t[s], 0, targets[s], 0, t[0].length);
	snapshot = snap;
	when = w;
    }
    
    public FaceFrame(FaceFrame f) {
	targets = f.getTargets();
	snapshot = f.getSnapshot();
	when = f.getTime();
    }

    public FaceFrame(String s) {
	StringTokenizer outside = new StringTokenizer(s, "|\n");
	String w = outside.nextToken();
	when = Double.parseDouble(w);

	String val = outside.nextToken();
	StringTokenizer inside = new StringTokenizer(val, ",");
	targets = new double[2][inside.countTokens()];
	int i = 0;
	String v;
	while(inside.hasMoreTokens()) {
	    v = inside.nextToken();
	    targets[0][i] = Double.parseDouble(v);
	    i++;
	}

	val = outside.nextToken();
	inside = new StringTokenizer(val, ",");
	i = 0;
	while(inside.hasMoreTokens()) {
	    v = inside.nextToken();
	    targets[1][i] = Double.parseDouble(v);
	    i++;
	}
	snapshot = null;
    }

    public double[][] getTargets() {
	return targets;
    }

    public Image getSnapshot() {
	return snapshot;
    }

    public void setSnapshot(Image snap) {
	snapshot = snap;
    }

    public double getTime() {
	return when;
    }

    public void setTime(double t) {
	when = t;
    }

    public String toString() {
	String retVal = new String();
	retVal += when + "\n";
	for(int s = 0; s < 2; s++) {
	    for(int v = 0; v < targets[s].length; v++) {
		if(v != 0)
		    retVal += ",";
		retVal += "" + targets[s][v];
	    }
	    retVal += "\n";
	}
	return retVal;
    }
}

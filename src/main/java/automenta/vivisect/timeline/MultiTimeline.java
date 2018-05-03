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
package automenta.vivisect.timeline;

import automenta.vivisect.swing.PCanvas;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


public abstract class MultiTimeline extends JPanel {

    List<TimelineVis> timeline = new ArrayList();

    final int hgap = 4;
    final int vgap = 4;
    
    public MultiTimeline(int n, int rows, int cols) {
        this(n);
        setLayout(new GridLayout(rows, cols, hgap, vgap));
        doLayout();
    }
    public MultiTimeline(int n) {
        super(new GridLayout());
        TimelineVis.Camera sharedCam = new TimelineVis.Camera();
        for (int i = 0; i < n; i++) {
            TimelineVis t = new TimelineVis(sharedCam, getCharts(i));
            timeline.add(t);
            add(new PCanvas(t));
        }
        doLayout();
    }

    public abstract Chart[] getCharts(int experiment);
}

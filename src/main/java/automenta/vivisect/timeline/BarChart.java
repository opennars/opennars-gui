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

import automenta.vivisect.TreeMLData;


public class BarChart extends LineChart {

    float barWidth = 0.9f;

    public BarChart(TreeMLData t) {
        super(t);
    }


    @Override
    protected void drawData(TimelineVis l, float timeScale, float yScale1, float y) {
        
        
        if (data.size()!=1) 
            throw new RuntimeException("BarChart only supports one data set");
        
        TreeMLData chart = data.get(0);
        
        int ccolor = chart.getColor();
        l.g.noStroke();
        for (int t = l.cycleStart; t < l.cycleEnd; t++) {
            float x = (t-l.cycleStart) * timeScale;
            float v = (float)chart.getData(t);
            
            if (Float.isNaN(v)) {
                continue;
            }
            
            float p = (max == min) ? 0 : (v - min) / (max - min);
            float px = width * x;
            float h = p * yScale1;
            float py = y + yScale1 - h;
            l.g.fill(ccolor, 255f * (0.5f + 0.5f * p));
            l.g.rect(px, py, width * timeScale * barWidth, h);
        }
    }

    public BarChart setBarWidth(float f) {
        this.barWidth = f;
        return this;
    }
}

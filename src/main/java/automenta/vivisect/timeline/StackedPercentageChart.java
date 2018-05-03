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
/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package automenta.vivisect.timeline;

import automenta.vivisect.TreeMLData;



/**
 *
 * @author me
 */
public class StackedPercentageChart extends LineChart {
    float barWidth = 0.9f;
    float barHeight = 0.9f;

    public StackedPercentageChart(TreeMLData... series) {
        super(series);
    }


    @Override
    protected void updateRange(TimelineVis l) {
        super.updateRange(l);
        min = 0;
        max = 1.0f;
    }

    @Override
    protected void drawData(TimelineVis l, float timeScale, float yScale, float y) {
        l.g.noStroke();
        for (int t = l.cycleStart; t < l.cycleEnd; t++) {
            float total = 0;
            for (TreeMLData chart : data) {
                float v = (float)chart.getData(t);
                if (Float.isNaN(v)) {
                    continue;
                }
                total += v;
            }
            if (total == 0) {
                continue;
            }
            float sy = y;
            float gap = yScale * (1.0f - barHeight) / data.size();
            
            l.g.strokeWeight(1f);
            
            for (TreeMLData chart : data) {
                int ccolor = chart.getColor();

                
                float x = (t - l.cycleStart) * timeScale;
                float v = (float)chart.getData(t);
                if (Float.isNaN(v)) {
                    continue;
                }
                float p = v / total;
                float px = width * x;
                float h = p * yScale;
                l.g.fill(ccolor, 255f * (0.5f + 0.5f * p));
                l.g.rect(px, sy + gap / 2, width * timeScale * barWidth, h - gap / 2);
                sy += h;
            }
        }
    }

    public StackedPercentageChart setBarWidth(float f) {
        this.barWidth = f;
        return this;
    }
    
}

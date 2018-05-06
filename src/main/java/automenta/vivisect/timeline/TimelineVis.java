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

import automenta.vivisect.Vis;
import java.awt.Graphics2D;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static processing.core.PConstants.HSB;
import processing.core.PGraphics;
import processing.core.PGraphicsJava2D;

/**
 * Timeline view of an inference trace. Focuses on a specific window and certain
 * features which can be adjusted dynamically. Can either analyze a trace while
 * a Nar runs, or after it finishes.
 */
public class TimelineVis implements Vis {

    float camScale = 1f;
    float scaleSpeed = 4f;
    private float lastMousePressY = Float.NaN;
    private float lastMousePressX = Float.NaN;

    final int defaultFrameRate = 25;
    
    boolean updating = true;

    float minLabelScale = 10f;
    float minYScale = 0.5f;
    float minTimeScale = 0.5f;
    float maxYScale = 1000f;
    float maxTimeScale = 1000f;
    float drawnTextScale = 0;

    //display options to extract to a parameter class ----------------------------------------
    boolean showItemLabels = true;
    float textScale = 0.1f;
    int cycleStart = 0;
    int cycleEnd = 0;
    
    public PGraphics g;
    public Graphics2D g2; //for direct Swing control

    public static class Camera {
        public float camX = 0f;
        public float camY = 0f;
        public float timeScale = 32f;
        public float yScale = 32f;   
        public long lastUpdate = 0;
    }
    
    public final Camera camera;
    
    long lastUpdate = System.nanoTime();
    long lastCameraUpdate = 0;

    public final List<Chart> charts;
    //display options ----------------------------------------

    public TimelineVis(List<Chart> charts) {
        this(new Camera(), charts);
    }
    
    public TimelineVis(Camera camera, List<Chart> charts) {
        super();
        this.camera = camera;
        this.charts = charts;
    }
    
    public TimelineVis(Chart... charts) {        
        this(new Camera(), charts);
    }
    
    public TimelineVis(Camera camera, Chart... charts) {
        this(camera, new CopyOnWriteArrayList<>(charts));
    }
    
    public void view(long start, long end) {

        //TODO calculate timeScale and camX to view the cycle range
        
    }

    public void clearCharts() {
        charts.clear();
    }
    
    public void setCharts(Collection<Chart> newChartList) {
        clearCharts();
        charts.addAll(newChartList);
    }
    
    public void addChart(Chart c) {
        charts.add(c);
    }

    public long getStart() {
        return cycleStart;
    }
    
    public long getEnd() {
        return cycleEnd;
    }
    
    public void updateNext() {
        if (!updating) {
            for (Chart c : charts) {
                float h = c.height * camera.yScale;
                c.update(this, camera.timeScale, camera.yScale);
            }            
        }
        updating = true;        
    }

    @Override
    public boolean draw(PGraphics g) {
    
       
        this.g = g;
        this.g2 = ((PGraphicsJava2D)g).g2;
        
        int originalColorMode = g.colorMode;
        g.colorMode(HSB);
        
        lastCameraUpdate = camera.lastUpdate;

        //background(0);

        float y = 0;
        float yMargin = camera.yScale * 0.1f;
        
        cycleStart = Integer.MAX_VALUE;
        cycleEnd = 0;
        for (Chart c : charts) {
            int cstart = c.getStart();
            int cend = c.getEnd();
            if (cstart < cycleStart) cycleStart = cstart;
            if (cend > cycleEnd) cycleEnd = cend;
        }
        for (Chart c : charts) {
            float h = c.height * camera.yScale;
            c.draw(this, y, camera.timeScale, camera.yScale);
            if(c.drawOverlapped) {
                y += 10;
                continue;
            }
            y += (h + yMargin);
        }
               
        g.colorMode(originalColorMode);
        return true;
    }


    public boolean isShowingItemLabels() {
        return showItemLabels;
    }

    public float getMinLabelScale() {
        return minLabelScale;
    }

    public float getDrawnTextScale() {
        return drawnTextScale;
    }
    
    
}

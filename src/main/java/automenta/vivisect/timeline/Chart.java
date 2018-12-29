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
import java.util.List;

/**
 * Modes: Line Line with vertical pole to base Stacked bar Stacked bar
 * normalized each step Scatter Spectral Event Bubble
 *
 */
public abstract class Chart {
    protected float height = 1.0f;
    protected float width = 1.0f;
    protected boolean overlayEnable = false;
    boolean drawOverlapped = false;

    public interface MultiChart {
        List<TreeMLData> getData();
    }
    
    public Chart() {
        height = 1f;
    }

    public Chart size(float w , float h) {
        this.width = w;
        this.height = h;
        return this;
    }
    public Chart height(float h) {
        this.height = h;
        return this;
    }
    public Chart width(float w) {
        this.width = w;
        return this;
    }
    public Chart drawOverlapped() {
        this.drawOverlapped = true;
        return this;
    }
    
    public void setOverlayEnable(boolean overlayEnable) {
        this.overlayEnable = overlayEnable;
    }
    
    //called during Nar thread
    public void update(TimelineVis l, float timeScale, float yScale) {
    }

    //called during Swing thread
    public abstract void draw(TimelineVis l, float y, float timeScale, float yScale);

    public float getHeight() {
        return height;
    }
    public float getWidth() {
        return width;
    }    
    abstract public int getStart();
    abstract public int getEnd();
    
}

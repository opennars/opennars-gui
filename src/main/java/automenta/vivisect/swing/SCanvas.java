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
package automenta.vivisect.swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Renders to Swing BufferedImage
 */
public class SCanvas extends Canvas {

    protected RenderingHints renderHints;
    protected BufferedImage image = null;
    protected Color backgroundClearColor = Color.BLACK;
    

    @Override
    public void paint(Graphics g) {
    }

    protected final Graphics2D getBufferGraphics() {
        if (!updateDoubleBuffer()) 
            return null;
        
	return (Graphics2D)image.getGraphics();
    }
    
    
    protected final void showBuffer(final Graphics2D g) {
        Graphics localGraphics = getGraphics();
        localGraphics.drawImage(image, 0, 0, null);
        
        g.dispose();
        localGraphics.dispose();        
    }
    
    
    private final boolean updateDoubleBuffer() {
        int w = getWidth();
        int h = getHeight();
        if ((w == 0) || (h == 0)) {
            return false;
        }

        /*
         * if image is already compatible and optimized for current system 
         * settings, simply return it
         */
        if ((image != null) && /*(image.getColorModel().equals(gfx_config.getColorModel())) &&*/ (image.getWidth() == w) && (image.getHeight() == h)) {
            //use existing image
        } else {
            // obtain the current system graphical settings
            GraphicsConfiguration gfx_config = GraphicsEnvironment.
                    getLocalGraphicsEnvironment().getDefaultScreenDevice().
                    getDefaultConfiguration();
            image = gfx_config.createCompatibleImage(w, h);
        }

        return true;
    }
}

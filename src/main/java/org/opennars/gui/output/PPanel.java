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
package org.opennars.gui.output;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import processing.core.PApplet;

/**
 * Processing.org Panel
 */
public class PPanel extends PApplet {

    public PPanel() {
        super();
    }



    @Override
    public void setup() {
        noLoop();
        
    }

    

    public JPanel newPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(this, BorderLayout.CENTER);
        init();
        return p;
    }
    

}


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

import automenta.vivisect.Video;
import java.awt.Graphics;
import javax.swing.JToggleButton;


/** ToggleButton with FontAwesome icon */
public class AwesomeToggleButton extends JToggleButton {

    private final char codeUnselected;
    private final char codeSelected;

    public AwesomeToggleButton(char faCodeUnselected, char faCodeSelected) {
        super();
        this.codeUnselected = faCodeUnselected;
        this.codeSelected = faCodeSelected;
        setFont(Video.FontAwesome);
        setText(String.valueOf(faCodeUnselected));
    }

    @Override
    public void setSelected(boolean b) {
        super.setSelected(b);
    }

    @Override
    public void paint(Graphics g) {
        if (isSelected()) {
            setText(String.valueOf(codeSelected));
        } else {
            setText(String.valueOf(codeUnselected));
        }
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }
}

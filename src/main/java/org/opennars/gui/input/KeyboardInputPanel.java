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
package org.opennars.gui.input;

import automenta.vivisect.swing.NPanel;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.CHAR_UNDEFINED;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;
import org.opennars.main.Nar;
import org.opennars.io.Texts;
import static org.opennars.io.Texts.n2;

/**
 * Direct keyboard input
 * @author me
 */
public class KeyboardInputPanel extends NPanel implements KeyListener, FocusListener {
    private final Nar nar;
    private final JTextArea text;

    public KeyboardInputPanel(Nar n) {
        super(new BorderLayout());
        
        text = new JTextArea();
        add(text, CENTER);
        
        this.nar = n;
        
        
        text.addKeyListener(this);
        text.addFocusListener(this);
    }

    @Override
    protected void onShowing(boolean showing) {
        
    }

    /** can be adjusted according to how many other windows are active, etc. */
    public void setFocus(float freq, float conf) {
        nar.addInput("<{focus} --> kb>. :|: %" + n2(freq) + ";" + n2(conf) + "%");        
    }

    public void onCharTyped(char c, float priority, float freq, float conf) {        
        String charTerm = "\"" + Character.toString(c) + "\"";
        nar.addInput("$" + n2(priority) + "$ < {" + charTerm + "} --> kb>. :|: %" + n2(freq) + ";" + n2(conf) + "%" );        
        nar.addInput("<(&/, <" + charTerm + " --> kb>, ?dt) =/> <?next --> kb>>?");
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == CHAR_UNDEFINED)
            return;
        if (e.isActionKey())
            return;
        onCharTyped(c, 0.8f, 1.0f, 0.9f);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {        
        setFocus(1f, 0.9f);
    }

    @Override
    public void focusLost(FocusEvent e) {        
        setFocus(0f, 0.9f);
    }
    
            
}

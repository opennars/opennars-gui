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
package org.opennars.gui.output;

import automenta.vivisect.swing.NPanel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author me
 */
abstract public class VerticalPanel extends NPanel {

    protected final JPanel content;
    protected final JPanel contentWrap;
    private final JScrollPane scrollPane;
    
    public VerticalPanel() {
        super(new BorderLayout());
        
        content = new JPanel(new GridBagLayout());
        contentWrap = new JPanel(new BorderLayout());
        contentWrap.add(content, BorderLayout.NORTH);
        add(scrollPane = new JScrollPane(contentWrap), BorderLayout.CENTER);
        
    }
    
    public void scrollBottom() {
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue( vertical.getMaximum() );        
    }
    
    public void addPanel(int index, JComponent j) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        
        gc.gridx = 0;
        gc.weightx = 1.0;
        gc.weighty = 0.0;
        gc.gridy = index;
        
        content.add(j, gc);
        
    }
    
    
    
}

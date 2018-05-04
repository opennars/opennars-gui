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

import automenta.vivisect.Video;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;



/**
 * @author http://stackoverflow.com/questions/19766/how-do-i-make-a-list-with-checkboxes-in-java-swing
 */
public class JCategoryList extends JList<JButton> {

    protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

    /*addMouseListener(new MouseAdapter() {
    public void mousePressed(MouseEvent e) {
    int index = locationToIndex(e.getPoint());
    if (index != -1) {
    JButton checkbox = (JButton)getModel().getElementAt(index);
    repaint();
    }
    }
    });*/
    public JCategoryList() {
        setCellRenderer(new CellRenderer());
        /*addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        int index = locationToIndex(e.getPoint());
        if (index != -1) {
        JButton checkbox = (JButton)getModel().getElementAt(index);
        repaint();
        }
        }
        });*/
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public JCategoryList(ListModel<JButton> model) {
        this();
        setModel(model);
    }

    protected class CellRenderer implements ListCellRenderer<JButton> {

        Font f = Video.fontMono(16f);
        private JButton lastCellFocus;

        public Component getListCellRendererComponent(JList<? extends JButton> list, JButton value, int index, boolean isSelected, boolean cellHasFocus) {
            JButton b = value;
            b.setContentAreaFilled(false);
            b.setHorizontalTextPosition(JButton.LEFT);
            b.setHorizontalAlignment(JButton.LEFT);
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setFont(f);
            if ((cellHasFocus) && (lastCellFocus != b)) {
                b.doClick();
                lastCellFocus = b;
            }
            return b;
        }
    }
}

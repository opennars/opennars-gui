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

import automenta.vivisect.swing.NPanel;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.opennars.main.Nar;
import org.opennars.io.events.OutputHandler;


public abstract class TablePanel extends NPanel  {

    protected final Nar nar;
    protected DefaultTableModel data;
    protected final JTable table;
    private final OutputHandler out;

    public TablePanel(Nar nar) {
        super(new BorderLayout());
        this.nar = nar;
        table = new JTable();   
        table.setFillsViewportHeight(true);
        
        add(new JScrollPane(table), CENTER);
        out = new OutputHandler(nar, false) {

            @Override
            public void event(Class event, Object[] arguments) {
                output(event, arguments.length > 1 ? arguments : arguments[0]);            
            }
            
        };        
    }

    abstract public void output(Class c, Object s);
    
    @Override
    protected void onShowing(boolean showing) {
        out.setActive(showing);
    }

    protected List<Object> getSelectedRows(int column) {
        int[] selectedRows = table.getSelectedRows();
        List<Object> l = new ArrayList(selectedRows.length);
        for (int i : selectedRows) {
            int selectedRow = table.convertRowIndexToModel(i);
            l.add(data.getValueAt(selectedRow, column));
        }
        return l;
    }
}

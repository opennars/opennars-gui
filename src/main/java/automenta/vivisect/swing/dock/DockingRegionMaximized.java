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
package automenta.vivisect.swing.dock;

import java.awt.Component;
import javax.swing.JTabbedPane;
import automenta.vivisect.swing.dock.DockingRegionContainer.MinMaxRecord;

/**
 *
 * @author kitfox
 */
public class DockingRegionMaximized extends JTabbedPane {

    MinMaxRecord record;

    public DockingRegionMaximized(MinMaxRecord record) {
        this.record = record;
        DockingContent content = record.getContent();

        Component comp = content.getComponent();
        addTab(content.getTitle(), comp);
        int idx = indexOfComponent(comp);
        setTabComponentAt(idx, new TabbedPaneTitleMax(this, content));
    }

    public void minimize() {
        DockingContent content = record.getContent();
        record.getContainer().restoreFromMaximize(content);
    }

    public void close() {
        DockingContent content = record.getContent();
        record.getContainer().restoreFromMaximize(content);

        DockingRegionTabbed panel
                = (DockingRegionTabbed) record.getContainer()
                .getRoot().getDockingChild(record.getPath());
//        DockingRegionTabbed panel =
//                (DockingRegionTabbed)record.getPath().getLast().getDockingChild();
        panel.removeTab(content);
    }

    void restoreFromMaximize(DockingContent content) {
        record.getContainer().restoreFromMaximize(content);
    }

    void closeFromMaximize(DockingContent content) {
        record.getContainer().closeFromMaximize(content);
    }

    void floatFromMaximize(DockingContent content) {
        record.getContainer().floatFromMaximize(content);
    }

}

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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.Icon;

/**
 *
 * @author kitfox
 */
public class DockingContent {
    public static final String PROP_PARENT = "parent";

    private final String uid;
    final private String title;
    final private Icon icon;
    final private Component component;
    private DockingRegionTabbed parent;
    private RestoreRecord restoreRecord;

    //EventListenerList listenerList = new EventListenerList();
    PropertyChangeSupport support = new PropertyChangeSupport(this);
    private TabbedPaneTitle tab;


    /**
     * Provide a dockable component to the docking system.
     *
     * @param uid String used to uniquely identify this window within the
     * docking system.
     * @param title Name of component that will be displayed in tabs
     * @param component The component that will be displayed in the body of the
     * docked window
     */
    public DockingContent(String uid, String title, Component component) {
        this(uid, title, null, component);
    }

    /**
     * Provide a dockable component to the docking system.
     *
     * @param uid String used to uniquely identify this window within the
     * docking system.
     * @param title Name of component that will be displayed in tabs
     * @param icon Icon that will be displayed to the left of the title. If
     * null, no icon is displayed.
     * @param component The component that will be displayed in the body of the
     * docked window
     */
    public DockingContent(String uid, String title, Icon icon, Component component) {
        this.uid = uid;
        this.title = title;
        this.icon = icon;
        this.component = component;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        support.removePropertyChangeListener(l);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the icon
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * @return the component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * @return the parent
     */
    public DockingRegionTabbed getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    protected void setParent(DockingRegionTabbed parent) {
        DockingRegionTabbed old = this.parent;
        this.parent = parent;
        support.fireIndexedPropertyChange(PROP_PARENT, 0, old, parent);
    }

    /**
     * @return the restoreRecord
     */
    public RestoreRecord getRestoreRecord() {
        return restoreRecord;
    }

    /**
     * @param restoreRecord the restoreRecord to set
     */
    protected void setRestoreRecord(RestoreRecord restoreRecord) {
        this.restoreRecord = restoreRecord;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

//    public String getTitle();
//    public Icon getIcon();
//    public JComponent getComponent();
//
//    public void setDockingParent(DockingRegionTabbed parent);

    void setTab(TabbedPaneTitle tab) {
        this.tab = tab;
    }

    public TabbedPaneTitle getTab() {
        return tab;
    }
    
    
}

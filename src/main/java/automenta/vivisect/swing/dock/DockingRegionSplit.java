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

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import static javax.swing.SwingUtilities.convertPoint;

/**
 *
 * @author kitfox
 */
public class DockingRegionSplit extends JSplitPane
        implements DockingContainer, DockingChild {

    private DockingContainer dockParent;
    private DockingChild left;
    private DockingChild right;

    public DockingRegionSplit(DockingChild left, DockingChild right) {
        this.left = left;
        this.right = right;

//        setDividerSize(8);
        setLeftComponent(left.getComponent());
        setRightComponent(right.getComponent());
        left.setDockParent(this);
        right.setDockParent(this);

        //resetToPreferredSizes();
    }

    @Override
    public void resetToPreferredSizes() {
        super.resetToPreferredSizes();
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public DockingRegionSplit split(DockingChild child,
            DockingContent content, boolean splitRight, boolean vertical) {
        DockingRegionTabbed newRegion = new DockingRegionTabbed();
        newRegion.addTab(content);

        DockingChild splitLeftChild, splitRightChild;
        splitLeftChild = splitRight ? child : newRegion;
        splitRightChild = splitRight ? newRegion : child;

        DockingRegionSplit split = new DockingRegionSplit(splitLeftChild, splitRightChild);
        split.setOrientation(vertical ? JSplitPane.VERTICAL_SPLIT : JSplitPane.HORIZONTAL_SPLIT);
        split.setDividerLocation(vertical ? getHeight() / 2 : getWidth() / 2);
        split.setDockParent(this);

        int oldDivide = getDividerLocation();
        if (left == child) {
            setLeftComponent(split);
            left = split;
        } else if (right == child) {
            setRightComponent(split);
            right = split;
        } else {
            throw new IllegalArgumentException();
        }
        setDividerLocation(oldDivide);

        revalidate();
        return split;
    }

    /**
     * Called when oldChild is no longer valid and should be removed from the
     * docking layout.
     *
     * @param oldChild Child to remove
     * @param newChild Child to replace oldChild with, or null if pane should
     * simply be removed.
     */
    @Override
    public void join(DockingChild oldChild, DockingChild newChild) {
        if (left == oldChild) {
            if (newChild == null) {
                dockParent.join(this, right);
            } else {
                int divLoc = getDividerLocation();
                left = newChild;
                left.setDockParent(this);
                setLeftComponent(newChild.getComponent());
                setDividerLocation(divLoc);
            }
        } else if (right == oldChild) {
            if (newChild == null) {
                dockParent.join(this, left);
            } else {
                int divLoc = getDividerLocation();
                right = newChild;
                right.setDockParent(this);
                setRightComponent(newChild.getComponent());
                setDividerLocation(divLoc);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void addDockContent(DockingContent content) {
        left.addDockContent(content);
    }

    /**
     * @return the dockParent
     */
    @Override
    public DockingContainer getDockParent() {
        return dockParent;
    }

    /**
     * @param dockParent the dockParent to set
     */
    @Override
    public void setDockParent(DockingContainer dockParent) {
        this.dockParent = dockParent;
    }

    @Override
    public DockingPathRecord buildPath(DockingChild dockChild, DockingPathRecord childPath) {
        return dockParent.buildPath(this,
                new PathRecordSplit(childPath, right == dockChild));
    }

    @Override
    public DockingRegionContainer getContainerRoot() {
        return dockParent.getContainerRoot();
    }

    @Override
    public DockingChild getDockingChild(DockingPathRecord subpath) {
        if (subpath instanceof PathRecordSplit) {
            //Path still consitent.  Pass restore down to indicated child
            PathRecordSplit rec = (PathRecordSplit) subpath;
            if (rec.right) {
                return right.getDockingChild(subpath.next);
            } else {
                return left.getDockingChild(subpath.next);
            }
        } else {
            //We've lost the trail.  Just add in the content here
            return this;
        }
    }

    @Override
    public void restore(DockingContent content, DockingPathRecord subpath) {
        if (subpath instanceof PathRecordSplit) {
            //Path still consitent.  Pass restore down to indicated child
            PathRecordSplit rec = (PathRecordSplit) subpath;
            if (rec.right) {
                right.restore(content, subpath.next);
            } else {
                left.restore(content, subpath.next);
            }
        } else {
            //We've lost the trail.  Just add in the content here
            addDockContent(content);
        }
    }

    @Override
    public DockingPickRecord pickContainer(Point containerPoint) {
        {
            Point relPoint = convertPoint(this, containerPoint, leftComponent);
            Rectangle bounds = left.getComponent().getBounds();
            bounds.x = bounds.y = 0;
            if (bounds.contains(relPoint)) {
                return left.pickContainer(relPoint);
            }
        }

        {
            Point relPoint = convertPoint(this, containerPoint, rightComponent);
            Rectangle bounds = right.getComponent().getBounds();
            bounds.x = bounds.y = 0;
            if (bounds.contains(relPoint)) {
                return right.pickContainer(relPoint);
            }
        }

        return null;
    }

    /**
     * @return the left
     */
    public DockingChild getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public DockingChild getRight() {
        return right;
    }

    @Override
    public void closeAll() {
        left.closeAll();
        right.closeAll();
    }

    //---------------------------------
    public static class PathRecordSplit extends DockingPathRecord {

        boolean right;

        public PathRecordSplit(DockingPathRecord child, boolean right) {
            super(child);
            this.right = right;
        }

//        @Override
//        public DockingChild getDockingChild()
//        {
//            return DockingRegionSplit.this;
//        }
    }

}

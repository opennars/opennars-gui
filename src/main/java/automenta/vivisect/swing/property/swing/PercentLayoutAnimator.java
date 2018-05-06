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
package automenta.vivisect.swing.property.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

/**
 * Animates a PercentLayout
 */
public class PercentLayoutAnimator implements ActionListener {

  private Timer animatorTimer;
  private List tasks = new ArrayList();
  private PercentLayout layout;
  private Container container;

  public PercentLayoutAnimator(Container container, PercentLayout layout) {
    this.container = container;
    this.layout = layout;
  }

  public void setTargetPercent(Component component, float percent) {
    PercentLayout.Constraint oldConstraint = layout.getConstraint(component);
    if (oldConstraint instanceof PercentLayout.PercentConstraint) {
      setTargetPercent(component,
        ((PercentLayout.PercentConstraint)oldConstraint).floatValue(), percent);
    }
  }

  public void setTargetPercent(Component component, float startPercent, float endPercent) {
    tasks.add(new PercentTask(component, startPercent, endPercent));
  }

  public void start() {
    animatorTimer = new Timer(15, this);
    animatorTimer.start();
  }

  public void stop() {
    animatorTimer.stop();
  }

  protected void complete() {
    animatorTimer.stop();
  }
  
  public void actionPerformed(ActionEvent e) {
    boolean allCompleted = true;

      for (Object task : tasks) {
          PercentTask element = (PercentTask) task;
          if (!element.isCompleted()) {
              allCompleted = false;
              element.execute();
          }
      }

    container.invalidate();
    container.doLayout();
    container.repaint();

    if (allCompleted) {
      complete();
    }
  }

  class PercentTask {

    Component component;

    float targetPercent;
    float currentPercent;
    
    boolean completed;
    boolean incrementing;
    float delta;
    
    public PercentTask(Component component, float currentPercent,
      float targetPercent) {
      this.component = component;
      this.currentPercent = currentPercent;
      this.targetPercent = targetPercent;
      
      float diff = targetPercent - currentPercent;
      incrementing = diff > 0;
      delta = diff / 10;
    }

    public void execute() {
      currentPercent += delta;
      if (incrementing) {
        if (currentPercent > targetPercent) {
          currentPercent = targetPercent;
          completed = true;
        }
      } else {
        if (currentPercent < targetPercent) {
          currentPercent = targetPercent;
          completed = true;
        }
      }

      layout.setConstraint(component, new PercentLayout.PercentConstraint(
        currentPercent));
    }

    public boolean isCompleted() {
      return completed;
    }
  }

}

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
package org.opennars.gui.util;

import org.opennars.io.events.EventHandler;
import org.opennars.io.events.Events;
import org.opennars.io.events.Events.CycleEnd;
import org.opennars.io.events.Events.CycleStart;
import org.opennars.main.Nar;
import org.opennars.storage.Memory;
import org.opennars.entity.Concept;
import org.opennars.entity.Task;
import org.opennars.interfaces.pub.Reasoner;
import org.opennars.io.events.OutputHandler;

public abstract class MemoryObserver extends EventHandler {

    private final Memory memory;
    private final Reasoner reasoner;

    public MemoryObserver(Reasoner n, boolean active) {
        super(((Nar)n).memory.event, active,
                Events.CycleStart.class,
                Events.CycleEnd.class,
                Events.ConceptNew.class,
                Events.ConceptForget.class,
                Events.ConceptBeliefAdd.class,
                Events.ConceptBeliefRemove.class,
                Events.ConceptDirectProcessedTask.class,
                Events.ConceptFire.class,
                Events.ConceptGoalAdd.class,
                Events.ConceptGoalRemove.class,
                Events.ConceptQuestionAdd.class,
                Events.ConceptQuestionRemove.class,
                Events.ConceptUnification.class,
                Events.BeliefSelect.class,
                Events.PluginsChange.class,
                Events.TaskAdd.class,
                Events.TaskRemove.class,
                Events.TaskDerive.class,
                Events.TaskImmediateProcess.class,
                Events.TermLinkSelect.class,
                //Events.UnExecutedGoal.class,

                //Output.OUT.class, 

                Events.ResetEnd.class);
        this.memory = ((Nar)n).memory;
        this.reasoner = n;
    }

    @Override
    public void event(final Class event, final Object[] arguments) {
        if (event == Events.ConceptNew.class) {
            onConceptAdd((Concept) arguments[0]);
        } else if (event == OutputHandler.OUT.class) {
            output(event, arguments[0].toString());
        } else if (event == Events.ResetEnd.class) {
            output(event);        
        } else if (event == CycleStart.class) {
            onCycleStart(this.reasoner.time());
        } else if (event == CycleEnd.class) {
            onCycleEnd(this.reasoner.time());
        } else {
            output(event, arguments);
        }

        //cycle start
        //cycle end
        //task add
        //task remove
    }

    /**
     * Add new text to display
     *
     * @param channel
     * @param args the arguments
     */
    abstract public void output(Class channel, Object... args);

    public void output(String s) {
        output(String.class, s);
    }

    /**
     * when a concept is instantiated
     */
    abstract public void onConceptAdd(Concept concept);

    /**
     * called at the beginning of each inference clock cycle
     */
    abstract public void onCycleStart(long clock);

    /**
     * called at the end of each inference clock cycle
     */
    abstract public void onCycleEnd(long clock);

    /**
     * Added task
     */
    abstract public void onTaskAdd(Task task, String reason);

    /**
     * Neglected task
     */
    abstract public void onTaskRemove(Task task, String reason);

}

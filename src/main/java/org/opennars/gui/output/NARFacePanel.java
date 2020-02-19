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

import automenta.vivisect.face.HumanoidFacePanel;
import org.opennars.main.Nar;


public class NARFacePanel extends HumanoidFacePanel  {
    private final Nar nar;

    public NARFacePanel(Nar n) {
        super();
        this.nar = n;
    }
    
    @Override
    protected void onShowing(boolean showing) {
        super.onShowing(showing);
        
//        if (showing) {
//            nar.addOutput(this);
//        }
//        else {
//            nar.removeOutput(this);
//        }
    }
    

    @Override
    public void update(double t) {
        if(nar.memory.emotion == null) {
            return;
        }
        happy = nar.memory.emotion.happy() > nar.memory.emotion.HAPPY_EVENT_HIGHER_THRESHOLD;
        unhappy = nar.memory.emotion.happy() < nar.memory.emotion.HAPPY_EVENT_LOWER_THRESHOLD;
        float conceptPriority = 0.5f; //((Number)nar.memory.logic.get("concept.priority.mean")).floatValue();
        float taskNewPriority = 0.5f; //((Number)nar.memory.logic.get("task.new.priority.mean")).floatValue();        
        
        //max out at 0.5
        conceptPriority = Math.min(conceptPriority, 0.4f);
        //if (nar.memory.getConcepts().isEmpty())
            conceptPriority = 0; //if no concepts, start at zero, regardless of what mean might be valued
        
        face.setPupil(12f * (conceptPriority+0.35f)+2f,                 
                taskNewPriority*taskNewPriority*0.45f,0,0,0.9f); //pupils glow a little red for priority of new tasks
        
        face.setEyeball(8f * (conceptPriority + 0.35f)+12f,1f,1f,1f,0.85f);
        super.update(t);        
    }

//    @Override
//    public void output(Class channel, Object signal) {
//        talk=1;
//    }
    
}

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

package org.opennars.gui;

import org.opennars.main.Nar;
import org.opennars.plugin.Plugin;

/**
 *
 * @author tc
 */
public class RuntimeNarSettings implements Plugin {

    Nar n=null;
    @Override
    public boolean setEnabled(final Nar n, final boolean enabled) {
        this.n=n;
        return true;
    }

    public boolean isImmediateEternalization() {
        return n.narParameters.IMMEDIATE_ETERNALIZATION;
    }
    public void setImmediateEternalization(final boolean val) {
        n.narParameters.IMMEDIATE_ETERNALIZATION=val;
    }
    
    public double getDerivationPriorityLeak() {
        return n.narParameters.DERIVATION_PRIORITY_LEAK;
    }
    public void setDerivationPriorityLeak(final double val) {
        n.narParameters.DERIVATION_PRIORITY_LEAK=(float) val;
    }
    
    public double getDerivationDurabilityLeak() {
        return n.narParameters.DERIVATION_DURABILITY_LEAK;
    }
    public void setDerivationDurabilityLeak(final double val) {
        n.narParameters.DERIVATION_DURABILITY_LEAK=(float) val;
    }

    
    public double getEvidentalHorizon() {
        return n.narParameters.HORIZON;
    }
    public void setEvidentalHorizon(final double val) {
        n.narParameters.HORIZON=(float) val;
    }
    
    public double getCuriosityDesireConfidenceMul() {
        return n.narParameters.CURIOSITY_DESIRE_CONFIDENCE_MUL;
    }
    public void setCuriosityDesireConfidenceMul(final double val) {
        n.narParameters.CURIOSITY_DESIRE_CONFIDENCE_MUL=(float) val;
    }
    
    public double getCuriosityDesirePriorityMul() {
        return n.narParameters.CURIOSITY_DESIRE_PRIORITY_MUL;
    }
    public void setCuriosityDesirePriorityMul(final double val) {
        n.narParameters.CURIOSITY_DESIRE_PRIORITY_MUL=(float)val;
    }
    
    public double getCuriosityDesireDurabilityMul() {
        return n.narParameters.CURIOSITY_DESIRE_DURABILITY_MUL;
    }
    public void setCuriosityDesireDurabilityMul(final double val) {
        n.narParameters.CURIOSITY_DESIRE_DURABILITY_MUL=(float) val;
    }
    
    public boolean isCuriosityForOperatorOnly() {
        return n.narParameters.CURIOSITY_FOR_OPERATOR_ONLY;
    }
    public void setCuriosityForOperatorOnly(final boolean val) {
        n.narParameters.CURIOSITY_FOR_OPERATOR_ONLY=val;
    }
    
    
    public double getHappyEventHigherThreshold() {
        if(n.memory.emotion == null) {
            return 0;
        }
        return n.memory.emotion.HAPPY_EVENT_HIGHER_THRESHOLD;
    }
    public void setHappyEventHigherThreshold(final double val) {
        if(n.memory.emotion == null) {
            return;
        }
        n.memory.emotion.HAPPY_EVENT_HIGHER_THRESHOLD=(float) val;
    }
    
    public double getHappyEventLowerThreshold() {
        if(n.memory.emotion == null) {
            return 0;
        }
        return n.memory.emotion.HAPPY_EVENT_LOWER_THRESHOLD;
    }
    public void setHappyEventLowerThreshold(final double val) {
        if(n.memory.emotion == null) {
            return;
        }
        n.memory.emotion.HAPPY_EVENT_LOWER_THRESHOLD=(float) val;
    }
    
    public double getBusyEventHigherThreshold() {
        if(n.memory.emotion == null) {
            return 0;
        }
        return n.memory.emotion.BUSY_EVENT_HIGHER_THRESHOLD;
    }
    public void setBusyEventHigherThreshold(final double val) {
        if(n.memory.emotion == null) {
            return;
        }
        n.memory.emotion.BUSY_EVENT_HIGHER_THRESHOLD=(float) val;
    }
    
   public double getBusyEventLowerThreshold() {
        if(n.memory.emotion == null) {
            return 0;
        }
        return n.memory.emotion.BUSY_EVENT_LOWER_THRESHOLD;
    }
    public void setBusyEventLowerThreshold(final double val) {
        if(n.memory.emotion == null) {
            return;
        }
        n.memory.emotion.BUSY_EVENT_LOWER_THRESHOLD=(float) val;
    }
    
    public boolean isQuestionGenerationOnDecisionMaking() {
        return n.narParameters.QUESTION_GENERATION_ON_DECISION_MAKING;
    }
    public void setQuestionGenerationOnDecisionMaking(final boolean val) {
        n.narParameters.QUESTION_GENERATION_ON_DECISION_MAKING=val;
    }
    
    public boolean isDecisionQuestionGen() {
        return n.narParameters.QUESTION_GENERATION_ON_DECISION_MAKING;
    }
    public void setDecisionQuestionGen(final boolean val) {
        n.narParameters.QUESTION_GENERATION_ON_DECISION_MAKING=val;
    }
    
    public boolean isHowQuestionGenerationOnDecisionMaking() {
        return n.narParameters.HOW_QUESTION_GENERATION_ON_DECISION_MAKING;
    }
    public void setHowQuestionGenerationOnDecisionMaking(final boolean val) {
        n.narParameters.HOW_QUESTION_GENERATION_ON_DECISION_MAKING=val;
    }
    
    public double getAnticipationConfidence() {
        return n.narParameters.ANTICIPATION_CONFIDENCE;
    }
    public void setAnticipationConfidence(final double val) {
        n.narParameters.ANTICIPATION_CONFIDENCE=(float) val;
    }
    
    public double getSatisfactionThreshold() {
        return n.narParameters.SATISFACTION_TRESHOLD;
    }
    public void setSatisfactionThreshold(final double val) {
        n.narParameters.SATISFACTION_TRESHOLD=(float) val;
    }
}

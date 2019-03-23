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
    
    //Generate the following getters and setters with
    //https://gist.github.com/patham9/bddfeff299ee8c74978fbfc7b06e0ea2
    //note: it generates only volatile ones that are meant to be changed directly, 
    //ss the other params may demand a more complex setter (like re-creating a bag),
    //future versions might also allow to change bag sizes etc. from the GUI
    
    public double getNOVELTY_HORIZON() {
        return (double) n.narParameters.NOVELTY_HORIZON;
    }
    public void setNOVELTY_HORIZON(final double val) {
        n.narParameters.NOVELTY_HORIZON = (int) val;
    }

    public double getDECISION_THRESHOLD() {
        return (double) n.narParameters.DECISION_THRESHOLD;
    }
    public void setDECISION_THRESHOLD(final double val) {
        n.narParameters.DECISION_THRESHOLD = (float) val;
    }

    public double getDURATION() {
        return (double) n.narParameters.DURATION;
    }
    public void setDURATION(final double val) {
        n.narParameters.DURATION = (int) val;
    }

    public double getBUDGET_THRESHOLD() {
        return (double) n.narParameters.BUDGET_THRESHOLD;
    }
    public void setBUDGET_THRESHOLD(final double val) {
        n.narParameters.BUDGET_THRESHOLD = (float) val;
    }

    public double getDEFAULT_CONFIRMATION_EXPECTATION() {
        return (double) n.narParameters.DEFAULT_CONFIRMATION_EXPECTATION;
    }
    public void setDEFAULT_CONFIRMATION_EXPECTATION(final double val) {
        n.narParameters.DEFAULT_CONFIRMATION_EXPECTATION = (float) val;
    }

    public boolean isALWAYS_CREATE_CONCEPT() {
        return  n.narParameters.ALWAYS_CREATE_CONCEPT;
    }
    public void setALWAYS_CREATE_CONCEPT(final boolean val) {
        n.narParameters.ALWAYS_CREATE_CONCEPT =  val;
    }

    public double getDEFAULT_CREATION_EXPECTATION() {
        return (double) n.narParameters.DEFAULT_CREATION_EXPECTATION;
    }
    public void setDEFAULT_CREATION_EXPECTATION(final double val) {
        n.narParameters.DEFAULT_CREATION_EXPECTATION = (float) val;
    }

    public double getDEFAULT_CREATION_EXPECTATION_GOAL() {
        return (double) n.narParameters.DEFAULT_CREATION_EXPECTATION_GOAL;
    }
    public void setDEFAULT_CREATION_EXPECTATION_GOAL(final double val) {
        n.narParameters.DEFAULT_CREATION_EXPECTATION_GOAL = (float) val;
    }

    public double getDEFAULT_JUDGMENT_CONFIDENCE() {
        return (double) n.narParameters.DEFAULT_JUDGMENT_CONFIDENCE;
    }
    public void setDEFAULT_JUDGMENT_CONFIDENCE(final double val) {
        n.narParameters.DEFAULT_JUDGMENT_CONFIDENCE = (float) val;
    }

    public double getDEFAULT_JUDGMENT_PRIORITY() {
        return (double) n.narParameters.DEFAULT_JUDGMENT_PRIORITY;
    }
    public void setDEFAULT_JUDGMENT_PRIORITY(final double val) {
        n.narParameters.DEFAULT_JUDGMENT_PRIORITY = (float) val;
    }

    public double getDEFAULT_JUDGMENT_DURABILITY() {
        return (double) n.narParameters.DEFAULT_JUDGMENT_DURABILITY;
    }
    public void setDEFAULT_JUDGMENT_DURABILITY(final double val) {
        n.narParameters.DEFAULT_JUDGMENT_DURABILITY = (float) val;
    }

    public double getDEFAULT_QUESTION_PRIORITY() {
        return (double) n.narParameters.DEFAULT_QUESTION_PRIORITY;
    }
    public void setDEFAULT_QUESTION_PRIORITY(final double val) {
        n.narParameters.DEFAULT_QUESTION_PRIORITY = (float) val;
    }

    public double getDEFAULT_QUESTION_DURABILITY() {
        return (double) n.narParameters.DEFAULT_QUESTION_DURABILITY;
    }
    public void setDEFAULT_QUESTION_DURABILITY(final double val) {
        n.narParameters.DEFAULT_QUESTION_DURABILITY = (float) val;
    }

    public double getDEFAULT_GOAL_CONFIDENCE() {
        return (double) n.narParameters.DEFAULT_GOAL_CONFIDENCE;
    }
    public void setDEFAULT_GOAL_CONFIDENCE(final double val) {
        n.narParameters.DEFAULT_GOAL_CONFIDENCE = (float) val;
    }

    public double getDEFAULT_GOAL_PRIORITY() {
        return (double) n.narParameters.DEFAULT_GOAL_PRIORITY;
    }
    public void setDEFAULT_GOAL_PRIORITY(final double val) {
        n.narParameters.DEFAULT_GOAL_PRIORITY = (float) val;
    }

    public double getDEFAULT_GOAL_DURABILITY() {
        return (double) n.narParameters.DEFAULT_GOAL_DURABILITY;
    }
    public void setDEFAULT_GOAL_DURABILITY(final double val) {
        n.narParameters.DEFAULT_GOAL_DURABILITY = (float) val;
    }

    public double getDEFAULT_QUEST_PRIORITY() {
        return (double) n.narParameters.DEFAULT_QUEST_PRIORITY;
    }
    public void setDEFAULT_QUEST_PRIORITY(final double val) {
        n.narParameters.DEFAULT_QUEST_PRIORITY = (float) val;
    }

    public double getDEFAULT_QUEST_DURABILITY() {
        return (double) n.narParameters.DEFAULT_QUEST_DURABILITY;
    }
    public void setDEFAULT_QUEST_DURABILITY(final double val) {
        n.narParameters.DEFAULT_QUEST_DURABILITY = (float) val;
    }

    public double getFORGET_QUALITY_RELATIVE() {
        return (double) n.narParameters.FORGET_QUALITY_RELATIVE;
    }
    public void setFORGET_QUALITY_RELATIVE(final double val) {
        n.narParameters.FORGET_QUALITY_RELATIVE = (float) val;
    }

    public double getREVISION_MAX_OCCURRENCE_DISTANCE() {
        return (double) n.narParameters.REVISION_MAX_OCCURRENCE_DISTANCE;
    }
    public void setREVISION_MAX_OCCURRENCE_DISTANCE(final double val) {
        n.narParameters.REVISION_MAX_OCCURRENCE_DISTANCE = (int) val;
    }

    public double getTERM_LINK_MAX_MATCHED() {
        return (double) n.narParameters.TERM_LINK_MAX_MATCHED;
    }
    public void setTERM_LINK_MAX_MATCHED(final double val) {
        n.narParameters.TERM_LINK_MAX_MATCHED = (int) val;
    }

    public double getOPERATION_SAMPLES() {
        return (double) n.narParameters.OPERATION_SAMPLES;
    }
    public void setOPERATION_SAMPLES(final double val) {
        n.narParameters.OPERATION_SAMPLES = (int) val;
    }

    public double getPROJECTION_DECAY() {
        return  n.narParameters.PROJECTION_DECAY;
    }
    public void setPROJECTION_DECAY(final double val) {
        n.narParameters.PROJECTION_DECAY =  val;
    }

    public double getTERMLINK_MAX_REASONED() {
        return (double) n.narParameters.TERMLINK_MAX_REASONED;
    }
    public void setTERMLINK_MAX_REASONED(final double val) {
        n.narParameters.TERMLINK_MAX_REASONED = (int) val;
    }

    public double getreliance() {
        return (double) n.narParameters.reliance;
    }
    public void setreliance(final double val) {
        n.narParameters.reliance = (float) val;
    }

    public double getDISCOUNT_RATE() {
        return (double) n.narParameters.DISCOUNT_RATE;
    }
    public void setDISCOUNT_RATE(final double val) {
        n.narParameters.DISCOUNT_RATE = (float) val;
    }

    public boolean isIMMEDIATE_ETERNALIZATION() {
        return  n.narParameters.IMMEDIATE_ETERNALIZATION;
    }
    public void setIMMEDIATE_ETERNALIZATION(final boolean val) {
        n.narParameters.IMMEDIATE_ETERNALIZATION =  val;
    }

    public double getSEQUENCE_BAG_ATTEMPTS() {
        return (double) n.narParameters.SEQUENCE_BAG_ATTEMPTS;
    }
    public void setSEQUENCE_BAG_ATTEMPTS(final double val) {
        n.narParameters.SEQUENCE_BAG_ATTEMPTS = (int) val;
    }

    public double getCONDITION_BAG_ATTEMPTS() {
        return (double) n.narParameters.CONDITION_BAG_ATTEMPTS;
    }
    public void setCONDITION_BAG_ATTEMPTS(final double val) {
        n.narParameters.CONDITION_BAG_ATTEMPTS = (int) val;
    }

    public double getDERIVATION_PRIORITY_LEAK() {
        return (double) n.narParameters.DERIVATION_PRIORITY_LEAK;
    }
    public void setDERIVATION_PRIORITY_LEAK(final double val) {
        n.narParameters.DERIVATION_PRIORITY_LEAK = (float) val;
    }

    public double getDERIVATION_DURABILITY_LEAK() {
        return (double) n.narParameters.DERIVATION_DURABILITY_LEAK;
    }
    public void setDERIVATION_DURABILITY_LEAK(final double val) {
        n.narParameters.DERIVATION_DURABILITY_LEAK = (float) val;
    }

    public double getCURIOSITY_DESIRE_CONFIDENCE_MUL() {
        return (double) n.narParameters.CURIOSITY_DESIRE_CONFIDENCE_MUL;
    }
    public void setCURIOSITY_DESIRE_CONFIDENCE_MUL(final double val) {
        n.narParameters.CURIOSITY_DESIRE_CONFIDENCE_MUL = (float) val;
    }

    public double getCURIOSITY_DESIRE_PRIORITY_MUL() {
        return (double) n.narParameters.CURIOSITY_DESIRE_PRIORITY_MUL;
    }
    public void setCURIOSITY_DESIRE_PRIORITY_MUL(final double val) {
        n.narParameters.CURIOSITY_DESIRE_PRIORITY_MUL = (float) val;
    }

    public double getCURIOSITY_DESIRE_DURABILITY_MUL() {
        return (double) n.narParameters.CURIOSITY_DESIRE_DURABILITY_MUL;
    }
    public void setCURIOSITY_DESIRE_DURABILITY_MUL(final double val) {
        n.narParameters.CURIOSITY_DESIRE_DURABILITY_MUL = (float) val;
    }

    public boolean isCURIOSITY_FOR_OPERATOR_ONLY() {
        return  n.narParameters.CURIOSITY_FOR_OPERATOR_ONLY;
    }
    public void setCURIOSITY_FOR_OPERATOR_ONLY(final boolean val) {
        n.narParameters.CURIOSITY_FOR_OPERATOR_ONLY =  val;
    }

    public boolean isBREAK_NAL_HOL_BOUNDARY() {
        return  n.narParameters.BREAK_NAL_HOL_BOUNDARY;
    }
    public void setBREAK_NAL_HOL_BOUNDARY(final boolean val) {
        n.narParameters.BREAK_NAL_HOL_BOUNDARY =  val;
    }

    public boolean isQUESTION_GENERATION_ON_DECISION_MAKING() {
        return  n.narParameters.QUESTION_GENERATION_ON_DECISION_MAKING;
    }
    public void setQUESTION_GENERATION_ON_DECISION_MAKING(final boolean val) {
        n.narParameters.QUESTION_GENERATION_ON_DECISION_MAKING =  val;
    }

    public boolean isHOW_QUESTION_GENERATION_ON_DECISION_MAKING() {
        return  n.narParameters.HOW_QUESTION_GENERATION_ON_DECISION_MAKING;
    }
    public void setHOW_QUESTION_GENERATION_ON_DECISION_MAKING(final boolean val) {
        n.narParameters.HOW_QUESTION_GENERATION_ON_DECISION_MAKING =  val;
    }

    public double getANTICIPATION_CONFIDENCE() {
        return (double) n.narParameters.ANTICIPATION_CONFIDENCE;
    }
    public void setANTICIPATION_CONFIDENCE(final double val) {
        n.narParameters.ANTICIPATION_CONFIDENCE = (float) val;
    }

    public double getANTICIPATION_TOLERANCE() {
        return (double) n.narParameters.ANTICIPATION_TOLERANCE;
    }
    public void setANTICIPATION_TOLERANCE(final double val) {
        n.narParameters.ANTICIPATION_TOLERANCE = (float) val;
    }
        
    public boolean isRETROSPECTIVE_ANTICIPATIONS() {
        return  n.narParameters.RETROSPECTIVE_ANTICIPATIONS;
    }
    public void setRETROSPECTIVE_ANTICIPATIONS(final boolean val) {
        n.narParameters.RETROSPECTIVE_ANTICIPATIONS =  val;
    }

    public double getSATISFACTION_TRESHOLD() {
        return (double) n.narParameters.SATISFACTION_TRESHOLD;
    }
    public void setSATISFACTION_TRESHOLD(final double val) {
        n.narParameters.SATISFACTION_TRESHOLD = (float) val;
    }

    public double getCOMPLEXITY_UNIT() {
        return (double) n.narParameters.COMPLEXITY_UNIT;
    }
    public void setCOMPLEXITY_UNIT(final double val) {
        n.narParameters.COMPLEXITY_UNIT = (float) val;
    }

    public double getINTERVAL_ADAPT_SPEED() {
        return (double) n.narParameters.INTERVAL_ADAPT_SPEED;
    }
    public void setINTERVAL_ADAPT_SPEED(final double val) {
        n.narParameters.INTERVAL_ADAPT_SPEED = (float) val;
    }

    public double getDEFAULT_FEEDBACK_PRIORITY() {
        return (double) n.narParameters.DEFAULT_FEEDBACK_PRIORITY;
    }
    public void setDEFAULT_FEEDBACK_PRIORITY(final double val) {
        n.narParameters.DEFAULT_FEEDBACK_PRIORITY = (float) val;
    }

    public double getDEFAULT_FEEDBACK_DURABILITY() {
        return (double) n.narParameters.DEFAULT_FEEDBACK_DURABILITY;
    }
    public void setDEFAULT_FEEDBACK_DURABILITY(final double val) {
        n.narParameters.DEFAULT_FEEDBACK_DURABILITY = (float) val;
    }

    public double getCONCEPT_FORGET_DURATIONS() {
        return (double) n.narParameters.CONCEPT_FORGET_DURATIONS;
    }
    public void setCONCEPT_FORGET_DURATIONS(final double val) {
        n.narParameters.CONCEPT_FORGET_DURATIONS = (float) val;
    }

    public double getTERMLINK_FORGET_DURATIONS() {
        return (double) n.narParameters.TERMLINK_FORGET_DURATIONS;
    }
    public void setTERMLINK_FORGET_DURATIONS(final double val) {
        n.narParameters.TERMLINK_FORGET_DURATIONS = (float) val;
    }

    public double getTASKLINK_FORGET_DURATIONS() {
        return (double) n.narParameters.TASKLINK_FORGET_DURATIONS;
    }
    public void setTASKLINK_FORGET_DURATIONS(final double val) {
        n.narParameters.TASKLINK_FORGET_DURATIONS = (float) val;
    }

    public double getEVENT_FORGET_DURATIONS() {
        return (double) n.narParameters.EVENT_FORGET_DURATIONS;
    }
    public void setEVENT_FORGET_DURATIONS(final double val) {
        n.narParameters.EVENT_FORGET_DURATIONS = (float) val;
    }

    public double getTHREADS_AMOUNT() {
        return (double) n.narParameters.THREADS_AMOUNT;
    }
    public void setTHREADS_AMOUNT(final double val) {
        n.narParameters.THREADS_AMOUNT = (int) val;
    }

    public double getVOLUME() {
        return (double) n.narParameters.VOLUME;
    }
    public void setVOLUME(final double val) {
        n.narParameters.VOLUME = (int) val;
    }

    public double getMILLISECONDS_PER_STEP() {
        return (double) n.narParameters.MILLISECONDS_PER_STEP;
    }
    public void setMILLISECONDS_PER_STEP(final double val) {
        n.narParameters.MILLISECONDS_PER_STEP = (int) val;
    }

    public boolean isSTEPS_CLOCK() {
        return  n.narParameters.STEPS_CLOCK;
    }
    public void setSTEPS_CLOCK(final boolean val) {
        n.narParameters.STEPS_CLOCK =  val;
    }

    public double getVARIABLE_INTRODUCTION_COMBINATIONS_MAX() {
        return (double) n.narParameters.VARIABLE_INTRODUCTION_COMBINATIONS_MAX;
    }
    public void setVARIABLE_INTRODUCTION_COMBINATIONS_MAX(final double val) {
        n.narParameters.VARIABLE_INTRODUCTION_COMBINATIONS_MAX = (int) val;
    }
    
    public double getVARIABLE_INTRODUCTION_CONFIDENCE_MUL() {
        return (double) n.narParameters.VARIABLE_INTRODUCTION_CONFIDENCE_MUL;
    }
    public void setVARIABLE_INTRODUCTION_CONFIDENCE_MUL(final double val) {
        n.narParameters.VARIABLE_INTRODUCTION_CONFIDENCE_MUL = (float) val;
    }
    
    public double getANTICIPATIONS_PER_CONCEPT_MAX() {
        return (double) n.narParameters.ANTICIPATIONS_PER_CONCEPT_MAX;
    }
    public void setANTICIPATIONS_PER_CONCEPT_MAX(final double val) {
        n.narParameters.ANTICIPATIONS_PER_CONCEPT_MAX = (int) val;
    }
    
    public double getMOTOR_BABBLING_CONFIDENCE_THRESHOLD() {
        return (double) n.narParameters.MOTOR_BABBLING_CONFIDENCE_THRESHOLD;
    }
    public void setMOTOR_BABBLING_CONFIDENCE_THRESHOLD(final double val) {
        n.narParameters.MOTOR_BABBLING_CONFIDENCE_THRESHOLD = (float) val;
    }
}
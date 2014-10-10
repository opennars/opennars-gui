package nars.util;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import nars.core.NAR;
import nars.entity.Concept;
import nars.entity.Task;
import nars.inference.InferenceRecorder;
import nars.io.Output;

/**
 * Records all sensors, output, and trace events in an indexed data structure for runtime or subsequent analysis of a NAR's execution telemetry.
 */
public class InferenceTrace implements InferenceRecorder, Output, Serializable {

    /**
     * utility method for diagnosing stack overflow errors caused by unbounded
     * recursion or other phenomena
     */
    public static boolean guardStack(int alertDepth, String methodname, Object... args) {
        StackTraceElement[] st = new Exception().getStackTrace();
        if (st.length < 1 + alertDepth) {
            return false;
        }
        for (int i = 1; i < alertDepth; i++) {
            //look for a series of equal (TODO: or cyclic) method names
            if (!st[i].getMethodName().contains(methodname)) {
                return false;
            }
        }
        return true;
    }

    public final Map<Concept, List<InferenceEvent>> concept = new HashMap();
    public final TreeMap<Long, List<InferenceEvent>> time = new TreeMap();

    private long t;
    transient private boolean active = true;


    abstract public static class InferenceEvent {

        public final long when;
        public final List<StackTraceElement> stack;

        //how many stack frames down to record from; we don't need to include the current and the previous (InferenceEvent subclass's constructor
        int STACK_PREFIX = 4;

        protected InferenceEvent(long when) {
            this(when, 0);
        }
        
        protected InferenceEvent(long when, int stackFrames) {
            this.when = when;
            
            if (stackFrames > 0) {
                List<StackTraceElement> sl = Arrays.asList(Thread.currentThread().getStackTrace());

                int frame = 0;
                
                for (StackTraceElement e : sl) {
                    frame++;
                    if (e.getClassName().equals("nars.core.NAR")) {
                        break;
                    }                    
                }
                if (frame - STACK_PREFIX > stackFrames)
                    frame = STACK_PREFIX + stackFrames;
                this.stack = sl.subList(STACK_PREFIX, frame);
            }
            else {
                this.stack = null;
            }
        }

    }

    public static class Comment extends InferenceEvent {

        public final String message;

        public Comment(long when, String message) {
            super(when);
            this.message = message;
        }

        @Override
        public String toString() {
            return "\"" + message + "\"";
        }

    }

    public static class ConceptCreation extends InferenceEvent {

        public final Concept concept;

        public ConceptCreation(Concept concept, long when) {
            super(when);
            this.concept = concept;
        }

        @Override
        public String toString() {
            return "Concept Created: " + concept + " " + stack;
        }

    }

    public static enum TaskEventType {

        Added, Removed
    }

    public static class TaskEvent extends InferenceEvent {

        public final Task task;
        public final TaskEventType type;
        public final String reason;
        public float priority;

        public TaskEvent(Task t, long when, TaskEventType type, String reason) {
            super(when);
            this.task = t;
            this.type = type;
            this.reason = reason;
            this.priority = t.getPriority();
        }

        @Override
        public String toString() {
            return "Task " + type + " (" + reason + "): " + task.toStringExternal() + " " + stack;
        }
    }

    public InferenceTrace(NAR n) {
        super();
        n.addOutput(this);
        n.memory.setRecorder(this);
    }
    
    

    public void addEvent(InferenceEvent e) {
        List<InferenceEvent> timeslot = time.get(t);
        if (timeslot == null) {
            timeslot = new ArrayList();
            time.put(t, timeslot);
        }
        timeslot.add(e);
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void reset() {
        time.clear();
        concept.clear();
    }

    @Override
    public void append(String channel, String s) {
        addEvent(new Comment(t, channel + ": " + s.trim()));
    }

    @Override
    public void onConceptNew(Concept concept) {
        ConceptCreation cc = new ConceptCreation(concept, t);

        List<InferenceEvent> lc = new ArrayList(1);
        lc.add(cc);

        this.concept.put(concept, lc);
        addEvent(cc);
    }

    @Override
    public void onCycleStart(long clock) {
        this.t = clock;
    }

    @Override
    public void onCycleEnd(long clock) {
    }

    @Override
    public void onTaskAdd(Task task, String reason) {
        TaskEvent ta = new TaskEvent(task, t, TaskEventType.Added, reason);
        addEvent(ta);
    }

    @Override
    public void onTaskRemove(Task task, String reason) {
        TaskEvent tr = new TaskEvent(task, t, TaskEventType.Removed, reason);
        addEvent(tr);
    }

    @Override
    public void output(Class channel, Object signal) {
        //TODO use a new InferenceEvent subclass for outputs
        addEvent(new Comment(t, channel + ": " + signal));
    }
    
    public void printTime() {
        printTime(System.out);
    }

    public void printTime(PrintStream out) {
        for (Long w : time.keySet()) {
            List<InferenceEvent> events = time.get(w);
            if (events.isEmpty()) {
                continue;
            }

            out.println(w + " ---------\\");
            for (InferenceEvent e : events) {
                System.out.println("  " + e);
            }
            out.println(w + " ---------/\n");

        }
    }

}
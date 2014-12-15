package nars.multistep;

import java.io.IOException;
import nars.core.NAR;
import nars.core.build.Default;
import nars.core.NALTest.ExpectContains;
import static org.junit.Assert.assertEquals;
import org.junit.Test;



public class TemporalParticlePlannerTest {

//    
//    /** NOTE: needs search depth >= 12 */
//    @Test public void testGraphPlan1Repeat() throws Exception {
//        String input = "";
//        
//        
//        //same as above with inputs rearranged
//        input += "<(&/,<a --> b>,+1,(^pick,Y),+3,<c --> d>) =/> <goal --> reached>>.\n";
//        input += "<(&/,(^pick,X),+2) =/> <a --> b>>.\n";
//        input += "<(&/,(^pick,Z),+1) =/> <c --> d>>.\n";    
//        input += "<goal --> reached>!\n";
////        if (repeat) {
////            input += "100\n";
////            input += "<goal --> reached>!\n";
////        }
//        String exp = "<(&/,(^pick,X),+3,(^pick,Y),+3,(^pick,Z)) =/> <goal --> reached>>!";
//        
//        testGraphPlan(input, exp, true, true, 44);
//        testGraphPlan(input, exp, false, false, 1000);
//    }    
//
//    public void testGraphPlan(String input, String expected, boolean withPlanner, boolean expectSuccess, int cyclesToSolve) throws IOException {
//        
//        Default d = new Default().setInternalExperience(Default.InternalExperienceMode.None);
//        
//        NAR n = new NAR(withPlanner?
//                d.temporalPlanner(12, 64, 24) :
//                d);
//        
//        (n.param).decisionThreshold.set(0.3f);
//        
//        ExpectContains e = new ExpectContains(n, expected, true);
//               
//        n.addInput(input);
//        
//        //new TextOutput(n, System.out);
//
//        n.step(cyclesToSolve);
//        
//        assertEquals("planner enabled? " + withPlanner + ".  ", expectSuccess, e.success());
//        
//    }
//

//    @Test public void testGraphPlan1a() throws Exception {
//        String input = "";
//        input += "<(&/,(^pick,X),+2) =/> <a --> b>>.\n";
//        input += "<(&/,(^pick,Z),+1) =/> <c --> d>>.\n";    
//        input += "<(&/,<a --> b>,+1,(^pick,Y),+3,<c --> d>) =/> <goal --> reached>>.\n";
//        input += "<goal --> reached>!\n";
//        testGraphPlan(input, 
//                "<(&/,(^pick,X),+3,(^pick,Y),+3,(^pick,Z),+1) =/> <goal --> reached>>! %1.00"
//        );
//    }
//    @Test public void testGraphPlan1() throws Exception {
//        String input = "";
//        
//        //same as above with inputs rearranged
//        input += "<(&/,<a --> b>,+1,(^pick,Y),+3,<c --> d>) =/> <goal --> reached>>.\n";
//        input += "<(&/,(^pick,X),+2) =/> <a --> b>>.\n";
//        input += "<(&/,(^pick,Z),+1) =/> <c --> d>>.\n";    
//        input += "<goal --> reached>!\n";
//        testGraphPlan(input, 
//                "<(&/,(^pick,X),+3,(^pick,Y),+3,(^pick,Z),+1) =/> <goal --> reached>>! %1.00"
//        );
//    }    
//    
//    @Test public void testGraphPlan2() throws Exception {
//        String input = "";
//        input += "<C =/> <goal --> reached>>.\n";
//        input += "<B =/> C>.\n";
//        input += "<A =/> B>.\n";
//        input += "<(&/,(^pick,X),+1) =/> A>.\n";    
//        input += "<goal --> reached>!\n";
//        testGraphPlan(input, "<(&/,(^pick,X),+1) =/> <goal --> reached>>! %1.00");
//    }
    

        
        //System.out.println(input);
        /*new NWindow("Implication Graph",
        new ProcessingGraphPanel(n,
        new ImplicationGraphCanvas(
        n.memory.executive.graph))).show(500, 500);*/    
}

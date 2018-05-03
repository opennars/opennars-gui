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
package nars.gui.graph;

import nars.main.NAR;
import nars.entity.Item;
import nars.entity.Sentence;
import nars.io.Symbols;
import nars.language.CompoundTerm;
import nars.main.NAR.PortableDouble;
import nars.language.Statement;
import nars.language.Term;

/** Maintains a directed grpah of Inheritance and Similiarty statements */
public class ImplicationGraph extends SentenceGraph {

    float minConfidence = 0.01f;
    private final boolean includeImplication;
    private final boolean includeEquivalence;

    public ImplicationGraph(NAR nar, boolean includeImplication, boolean includeEquivalence, PortableDouble minConceptPri) {
        super(nar.memory, minConceptPri);
        this.includeImplication = includeImplication;
        this.includeEquivalence = includeEquivalence;
    }
    
    @Override
    public boolean allow(final Sentence s) {        
        float conf = s.truth.getConfidence();
        if (conf > minConfidence)
            return true;
        return false;
    }

    @Override
    public boolean allow(final CompoundTerm st) {
        Symbols.NativeOperator o = st.operator();
        
        
        
        if ((o == Symbols.NativeOperator.IMPLICATION ||
             o == Symbols.NativeOperator.IMPLICATION_BEFORE || 
             o == Symbols.NativeOperator.IMPLICATION_AFTER ||
             o == Symbols.NativeOperator.IMPLICATION_WHEN) && includeImplication)
            return true;
        if ((o == Symbols.NativeOperator.EQUIVALENCE ||
             o == Symbols.NativeOperator.EQUIVALENCE_AFTER ||
             o == Symbols.NativeOperator.EQUIVALENCE_WHEN) && includeEquivalence) {
            return true;
        }

        return false;
    }

    @Override
    public boolean add(Sentence s, CompoundTerm ct, Item c) {
        if (ct instanceof Statement) {
            Statement st = (Statement)ct;
            Term subject = st.getSubject();
            Term predicate = st.getPredicate();
            addVertex(subject);
            addVertex(predicate);
            System.out.println(subject.toString().trim() + " " +
                               predicate.toString().trim()+" " +
                               s.truth.getExpectation() +
                               s.truth.getFrequency() + " " + 
                               s.truth.getConfidence() + " " +
                               " Implication");
            addEdge(subject, predicate, s);        
            return true;
        }
        return false;
        
    }    
    
    
}

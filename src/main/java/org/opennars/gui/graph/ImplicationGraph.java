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
package org.opennars.gui.graph;

import org.opennars.main.Nar;
import org.opennars.entity.Item;
import org.opennars.entity.Sentence;
import org.opennars.io.Symbols;
import org.opennars.language.CompoundTerm;
import org.opennars.language.Statement;
import org.opennars.language.Term;
import org.opennars.LockedValueTypes.PortableDouble;

/** Maintains a directed grpah of Inheritance and Similiarty statements */
public class ImplicationGraph extends SentenceGraph {

    float minConfidence = 0.01f;
    private final boolean includeImplication;
    private final boolean includeEquivalence;

    public ImplicationGraph(Nar nar, boolean includeImplication, boolean includeEquivalence, PortableDouble minConceptPri) {
        super(nar.memory, minConceptPri);
        this.includeImplication = includeImplication;
        this.includeEquivalence = includeEquivalence;
    }
    
    @Override
    public boolean allow(final Sentence s) {        
        double conf = s.truth.getConfidence();
        return conf > minConfidence;
    }

    @Override
    public boolean allow(final CompoundTerm st) {
        Symbols.NativeOperator o = st.operator();
        
        
        
        if ((o == Symbols.NativeOperator.IMPLICATION ||
             o == Symbols.NativeOperator.IMPLICATION_BEFORE || 
             o == Symbols.NativeOperator.IMPLICATION_AFTER ||
             o == Symbols.NativeOperator.IMPLICATION_WHEN) && includeImplication)
            return true;
        return (o == Symbols.NativeOperator.EQUIVALENCE ||
            o == Symbols.NativeOperator.EQUIVALENCE_AFTER ||
            o == Symbols.NativeOperator.EQUIVALENCE_WHEN) && includeEquivalence;

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

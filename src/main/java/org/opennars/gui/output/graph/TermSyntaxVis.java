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
package org.opennars.gui.output.graph;

import automenta.vivisect.dimensionalize.AbegoTreeLayout;
import automenta.vivisect.graph.AnimatingGraphVis;
import org.opennars.language.CompoundTerm;
import org.opennars.language.Term;
import org.opennars.gui.util.NARGraph;
import org.opennars.gui.util.NARGraph.UniqueEdge;
import org.jgrapht.Graph;
import org.opennars.main.Nar;

/**
 *
 * @author me
 */
public class TermSyntaxVis extends AnimatingGraphVis {
    private NARGraph syntaxGraph;

    public TermSyntaxVis(Nar nar, Term... t) {
        super(new NARGraph(), new NARGraphDisplay(nar).setTextSize(0.25f,64)
                , new AbegoTreeLayout());
        
        update(t);
        updateGraph();
    }
    
    @Override
    public Graph getGraph() {
        if (syntaxGraph == null)
            syntaxGraph = new NARGraph();
        return syntaxGraph;
    }
    
    public static Term addSyntax(NARGraph g, Term t) {
        if (t instanceof CompoundTerm) {
            CompoundTerm ct = (CompoundTerm)t;
            g.addVertex(ct);
            int n = 0;
            for (Term s : ct.term) {
                Term v = addSyntax(g, s);
                g.addEdge(ct, v, new UniqueEdge(ct.operator() + ":" + n));
                n++;
            }
            return ct;
        }
        else {
            g.addVertex(t);
            return t;
        }
    }

    protected void update(Term... t) {
        
        NARGraph g = new NARGraph();
        
        for (Term x : t) {
            addSyntax(g, x);
        }
        
        this.syntaxGraph = g;
    }
}

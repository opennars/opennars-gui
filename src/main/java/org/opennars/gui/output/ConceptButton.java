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
package org.opennars.gui.output;

import automenta.vivisect.swing.NWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import org.opennars.main.Nar;
import org.opennars.entity.Concept;
import org.opennars.language.Term;

/** represents either a Term or its Concept (if exists).
 *
 * if a concept is involved, there may be additional data to display.
 */
public class ConceptButton extends JButton implements ActionListener {

    private Concept concept;
    private final Term term;
    private Nar nar;

    public ConceptButton(Nar n, Term t) {
        super(t.toString());
        this.term = t;
        this.nar = n;
        addActionListener(this);
    }

    public ConceptButton(Nar n, Concept c) {
        this(n, c.term);
        this.concept = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.concept == null) {
            this.concept = nar.memory.concept(term);
        }
        if (this.concept == null) {
            //concept doesnt exist (yet)
            return;
        }
        popup(nar, concept);
    }

    public static void popup(Nar nar, Concept concept) {
        ConceptsPanel cp;
        NWindow w = new NWindow(concept.term.toString(), new JScrollPane(cp = new ConceptsPanel(nar, concept)));
        cp.onShowing(true);
        w.pack();
        w.setVisible(true);
    }
    
}

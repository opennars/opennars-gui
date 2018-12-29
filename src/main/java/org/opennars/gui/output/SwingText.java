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

import automenta.vivisect.Video;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class SwingText extends JTextPane {

    /**
     * # of messages to buffer in log
     */
    int maxLineWidth = 200;
    protected final float baseFontScale = 1.0f;
    protected final DefaultStyledDocument doc;
    protected final Style mainStyle;

    public SwingText() {
        super(new DefaultStyledDocument(new StyleContext()));

        doc = (DefaultStyledDocument) getDocument();
        setEditable(false);

        // Create and add the main document style
        Style defaultStyle = getStyle(StyleContext.DEFAULT_STYLE);
        mainStyle = doc.addStyle("MainStyle", defaultStyle);

        //StyleConstants.setLeftIndent(mainStyle, 16);
        //StyleConstants.setRightIndent(mainStyle, 16);
        //StyleConstants.setFirstLineIndent(mainStyle, 16);
        //StyleConstants.setFontFamily(mainStyle, Video.monofont.getFamily());
        //StyleConstants.setFontSize(mainStyle, 16);
        doc.setLogicalStyle(0, mainStyle);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Element ele = doc.getCharacterElement(viewToModel(e.getPoint()));
                AttributeSet as = ele.getAttributes();
                Action fla = (Action) as.getAttribute("linkact");
                if (fla != null) {
                    fla.actionPerformed(null);
                }
            }

        });

    }

    public void print(final Color color, final CharSequence text) {
        //System.out.println("print:: " + text);
        print(color, null, text, null);
    }

    public void print(final Color color, final CharSequence text, Action a) {
        print(color, null, text, a);
    }

    public void print(final Color color, final Color bgColor, CharSequence text, Action action) {

        MutableAttributeSet aset = getInputAttributes();
        StyleConstants.setForeground(aset, color);
        StyleConstants.setBackground(aset, bgColor != null ? bgColor : Color.BLACK);
        //StyleConstants.setUnderline(aset, false);
        //StyleConstants.setBold(aset, bold);
        
        try {
            if (action == null) {
                doc.insertString(doc.getLength(), text.toString(), aset);
            } else {
                //http://stackoverflow.com/questions/16131811/clickable-text-in-a-jtextpane
                Style link = doc.addStyle(null, StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE));
                StyleConstants.setForeground(link, color);
                //StyleConstants.setUnderline(link, true);
                //StyleConstants.setBold(link, true);
                link.addAttribute("linkact", action);
                doc.insertString(doc.getLength(), text.toString(), link);
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(SwingText.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void printColorBlock(final Color color, final String s) {

        //StyleContext sc = StyleContext.getDefaultStyleContext();
        MutableAttributeSet aset = getInputAttributes();
        StyleConstants.setBackground(aset, color);
        try {
            int l = doc.getLength();
            doc.insertString(l, s, aset);
        } catch (BadLocationException ex) {
            Logger.getLogger(SwingLogText.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //    public void print(Class c, Object o, boolean showStamp, Nar n) {
    //        //limitBuffer(nextOutput.baseLength());
    //        out(c, o);
    //    }
    void limitBuffer() {
        Document doc = getDocument();
        int overLength = doc.getLength() - LogPanel.maxIOTextSize;
        if (overLength > 0) {
            try {
                doc.remove(0, overLength + LogPanel.clearMargin);
            } catch (BadLocationException ex) {
            }
        }
    }

    public void setFontSize(float v) {
        setFont(Video.monofont.deriveFont(v));
    }

}

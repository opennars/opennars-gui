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
package org.opennars.gui;

import automenta.vivisect.swing.NWindow;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import org.opennars.main.Nar;
import org.opennars.io.events.TextOutputHandler;

/**
 * The main Swing GUI class of the open-org.opennars project.
 * Creates default Swing GUI windows to operate a Nar.
 */
public class NARSwing  {
    public static void themeInvert() {
        //http://alvinalexander.com/java/java-swing-uimanager-defaults
        //UIManager.put("ScrollBar.foreground", Color.WHITE);
       //  UIManager.put("ScrollBar.background", Color.WHITE);
        UIManager.put("ScrollBar.width", 12);
        UIManager.put("ScrollBar.shadow", Color.BLACK);
        UIManager.put("ScrollBar.darkShadow", Color.WHITE);
        ArrayList<Object> gradients = new ArrayList<Object>(5);
        gradients.add(0.0f);
        gradients.add(0.0f);
        gradients.add(Color.WHITE);
        gradients.add(Color.WHITE);
        gradients.add(Color.WHITE);
        UIManager.put("ScrollBar.gradient", gradients);
        
        UIManager.put("CheckBox.gradient", gradients);
        
        UIManager.put("OptionPane.gradient", gradients);
        
        UIManager.put("OptionPane.background", Color.GRAY);
        UIManager.put("OptionPane.foreground", Color.WHITE);
        
        UIManager.put("ComboBox.background", Color.DARK_GRAY);
        UIManager.put("ComboBox.foreground", Color.WHITE);
        
        UIManager.put("Table.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("TextArea.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("TextField.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("Text.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("Pane.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("Menu.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("MenuItem.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("List.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("Tabel.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("Panel.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("Tree.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("EditorPane.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("TextPane.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("TextPane.selectionBackground", Color.LIGHT_GRAY);
        UIManager.put("ComboBox.selectionBackground", Color.BLACK);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        
        UIManager.put("Pane.background", Color.DARK_GRAY);
        UIManager.put("Pane.foreground", Color.WHITE);
        
        UIManager.put("CheckBox.background", Color.DARK_GRAY);
        UIManager.put("CheckBox.foreground", Color.WHITE);
         

        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Button.border", new EmptyBorder(4,8,4,8));
        UIManager.put("ToggleButton.border", new EmptyBorder(4,8,4,8));
        UIManager.put("ScrollPane.border", new EmptyBorder(1,1,1,1));
        UIManager.put("SplitPane.border", new EmptyBorder(1,1,1,1));
        UIManager.put("TextEdit.border", new EmptyBorder(1,1,1,1));
        UIManager.put("TextArea.border", new EmptyBorder(1,1,1,1));
        UIManager.put("TextField.border", new EmptyBorder(1,1,1,1));

        UIManager.put("Label.foreground", Color.WHITE);

        UIManager.put("Tree.background", Color.BLACK);
        UIManager.put("Tree.foreground", Color.BLACK);
        UIManager.put("Tree.textForeground", Color.WHITE);
        UIManager.put("Tree.textBackground", Color.BLACK);
        UIManager.put("TextPane.background", Color.BLACK);
        UIManager.put("TextPane.foreground", Color.WHITE);
        UIManager.put("TextEdit.background", Color.BLACK);
        UIManager.put("TextEdit.foreground", Color.WHITE);
        UIManager.put("TextArea.background", Color.BLACK);
        UIManager.put("TextArea.foreground", Color.WHITE);

        UIManager.put("TextPane.border", new EmptyBorder(1,1,1,1));
        UIManager.put("TextPane.border", new EmptyBorder(1,1,1,1));
        UIManager.put("Panel.border", new EmptyBorder(1,1,1,1));
        UIManager.put("Button.select", Color.GREEN);
        UIManager.put("Button.highlight", Color.GRAY);
        UIManager.put("ToggleButton.foreground", Color.WHITE);
        UIManager.put("ToggleButton.background", Color.DARK_GRAY);
        UIManager.put("ToggleButton.select", Color.GRAY);
        UIManager.put("Button.select", Color.GRAY);
        UIManager.put("Button.opaque", false);
        UIManager.put("Panel.opaque", false);
        UIManager.put("ScrollBar.opaque", false);
        UIManager.put("ScrollBar.background", Color.BLACK);
        UIManager.put("ScrollBar.border", new EmptyBorder(1,1,1,1));
        
        UIManager.put("Table.background", Color.BLACK);
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("TableHeader.background", Color.BLACK);
        UIManager.put("TableHeader.foreground", Color.ORANGE);
    }

    public final Nar nar;
    public final NWindow mainWindow;
    public final NARControls controls;

    public NARSwing(Nar nar) {
        this(nar, true);
    }

    public NARSwing(Nar nar, boolean logPanel) {
        super();
                
        this.nar = nar;                
        this.nar.addPlugin(new RuntimeNarSettings());
        controls = new NARControls(nar, this);    
        controls.setPreferredSize(new Dimension(200,10));
        mainWindow = new NWindow(Nar.NAME + " " + Nar.VERSION);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setBounds(10, 10, 870, 650);
       
        mainWindow.setLayout(new BorderLayout());
        mainWindow.getContentPane().add(controls, BorderLayout.WEST);
        mainWindow.getContentPane().add(new ConsolePanel(controls), BorderLayout.CENTER);
         mainWindow.setVisible(true);

        if (logPanel) {
        }
        else {
            new TextOutputHandler(nar, System.out);
        }
    }

    /**
     * The entry point of the standalone application.
     * <p>
     * Create an instance of the class
     *
     * @param args optional argument used : one addInput file, possibly followed by --silence &lt;integer&gt;
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        themeInvert();
          
        Nar nar = new Nar();
        NARSwing swing = new NARSwing(nar);

        if (args.length > 0) {

            try {
                nar.addInputFile(args[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        
        if (args.length > 1) {
            swing.nar.start(0);
        }
    }

    /**
     * Color for the background of the main window
     */
    static final Color MAIN_WINDOW_COLOR = new Color( 172,170,194);
    /**
     * Color for the background of the windows with unique instantiation
     */
    static final Color SINGLE_WINDOW_COLOR = new Color(213,212,223);
    /**
     * Color for the background of the windows with multiple instantiations
     */
    static final Color MULTIPLE_WINDOW_COLOR = new Color(34,102,102);
    /**
     * Color for the background of the text components that are read-only
     */
    static final Color DISPLAY_BACKGROUND_COLOR = new Color(240,240,240);
    /**
     * Color for the background of the text components that are being saved into
     * a file
     */
    static final Color SAVING_BACKGROUND_COLOR = new Color(230,230,230);
    
    /**
     * Font for NARS GUI
     */
    
    /**
     * Message for unimplemented functions
     */
    public static final String UNAVAILABLE = "\n Not implemented in this version.";
    public static final String ON_LABEL = "On";
    public static final String OFF_LABEL = "Off";
}

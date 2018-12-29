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
package automenta.vivisect.swing.property.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


/**
 * An extension of the <code>JDialog</code> with built-in support for
 * OK/CANCEL/CLOSE buttons.
 * 
 * <code>BaseDialog</code> adds:
 * <ul>
 * <li>support for ESCAPE key to dispose the window</li>
 * <li>ok, cancel methods</li>
 * <li>simple method to check if ok or cancel was called ({@link #ask})
 * </li>
 * <li>button pane</li>
 * <li>banner pane</li>
 * </ul>
 */
public class BaseDialog extends JDialog {

  /**
   * Used to set the mode of the dialog to OK/CANCEL. When in this mode, OK and
   * Cancel buttons are automatically added to the dialog.
   */
  public final static int OK_CANCEL_DIALOG = 0;

  /**
   * Used to set the mode of the dialog to OK/CANCEL. When in this mode, a
   * Close button is automatically added to the dialog.
   */
  public final static int CLOSE_DIALOG = 1;

  private BannerPanel banner;
  private JPanel contentPane;
  private JPanel buttonPane;
  private boolean cancelClicked;

  private JButton okButton;
  private JButton cancelOrCloseButton;
  private int mode;

  private Action okAction = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      ok();
    }
  };

  private Action cancelOrCloseAction = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      cancel();
    }
  };

  public BaseDialog() throws HeadlessException {
    super();
    buildUI();
  }

  public BaseDialog(Dialog owner) throws HeadlessException {
    super(owner);
    buildUI();
  }

  public static BaseDialog newBaseDialog(Component parent) {
    Window window = parent instanceof Window?(Window)parent
      :(Window)SwingUtilities.getAncestorOfClass(Window.class, parent);
    
    if (window instanceof Frame) {
      return new BaseDialog((Frame)window);
    } else if (window instanceof Dialog) {
      return new BaseDialog((Dialog)window);      
    } else {
      return new BaseDialog();
    }
  }

  public BaseDialog(Dialog owner, boolean modal) throws HeadlessException {
    super(owner, modal);
    buildUI();
  }

  public BaseDialog(Frame owner) throws HeadlessException {
    super(owner);
    buildUI();
  }

  public BaseDialog(Frame owner, boolean modal) throws HeadlessException {
    super(owner, modal);
    buildUI();
  }

  public BaseDialog(Dialog owner, String title) throws HeadlessException {
    super(owner, title);
    buildUI();
  }

  public BaseDialog(Dialog owner, String title, boolean modal)
    throws HeadlessException {
    super(owner, title, modal);
    buildUI();
  }

  public BaseDialog(Frame owner, String title) throws HeadlessException {
    super(owner, title);
    buildUI();
  }

  public BaseDialog(Frame owner, String title, boolean modal)
    throws HeadlessException {
    super(owner, title, modal);
    buildUI();
  }

  public BaseDialog(
    Dialog owner,
    String title,
    boolean modal,
    GraphicsConfiguration gc)
    throws HeadlessException {
    super(owner, title, modal, gc);
    buildUI();
  }

  public BaseDialog(
    Frame owner,
    String title,
    boolean modal,
    GraphicsConfiguration gc) {
    super(owner, title, modal, gc);
    buildUI();
  }

  /**
   * Gets the BannerPanel displayed in this dialog. The BannerPanel can be made
   * invisible by calling <code>getBanner().setVisible(false);</code> if it
   * is not needed.
   * 
   * @see BannerPanel
   */
  public final BannerPanel getBanner() {
    return banner;
  }

  public final Container getContentPane() {
    return contentPane;
  }

  public final Container getButtonPane() {
    return buttonPane;
  }

  /**
   * @return true if OK was clicked, false if CANCEL or CLOSE was clicked
   */
  public boolean ask() {
    setVisible(true);
    dispose();
    return !cancelClicked;
  }

  /**
   * Called when the user clicks on the OK button.
   */
  public void ok() {
    cancelClicked = false;
    setVisible(false);
  }

  /**
   * Called when the user clicks on the CANCEL or CLOSE button
   */
  public void cancel() {
    cancelClicked = true;
    setVisible(false);
  }

  /**
   * Sets the mode of this dialog. Default mode is
   * {@link BaseDialog#OK_CANCEL_DIALOG}
   * 
   * @param mode {@link BaseDialog#OK_CANCEL_DIALOG}or
   *          {@link BaseDialog#CLOSE_DIALOG}
   */
  public void setDialogMode(int mode) {
    if (!(mode == OK_CANCEL_DIALOG || mode == CLOSE_DIALOG)) {
      throw new IllegalArgumentException("invalid dialog mode");
    }

    if (okButton != null) {
      buttonPane.remove(okButton);
      okButton = null;
    }
    if (cancelOrCloseButton != null) {
      buttonPane.remove(cancelOrCloseButton);
      cancelOrCloseButton = null;
    }

    switch (mode) {
      case OK_CANCEL_DIALOG :
        okButton =
          new JButton("OK" /*ResourceManager.all(BaseDialog.class).getString("ok")*/);
        okButton.addActionListener(okAction);
        buttonPane.add(okButton);
        cancelOrCloseButton =
          new JButton("Cancel"
            /*ResourceManager.all(BaseDialog.class).getString("cancel")*/);
        cancelOrCloseButton.addActionListener(cancelOrCloseAction);
        buttonPane.add(cancelOrCloseButton);
        getRootPane().setDefaultButton(okButton);
        break;
      case CLOSE_DIALOG :
        cancelOrCloseButton =
          new JButton("Close" /*ResourceManager.all(BaseDialog.class).getString("close")*/);
        cancelOrCloseButton.addActionListener(cancelOrCloseAction);
        buttonPane.add(cancelOrCloseButton);
        break;
    }

    this.mode = mode;
  }

  /**
   * Gets this dialog mode
   * 
   * @return this dialog mode
   */
  public int getDialogMode() {
    return mode;
  }

  /**
   * Centers this dialog on screen.
   */
  public void centerOnScreen() {
    UIUtilities.centerOnScreen(this);
  }

  protected String paramString() {
    return super.paramString()
      + ",dialogMode="
      + (mode == OK_CANCEL_DIALOG ? "OK_CANCEL_DIALOG" : "CLOSE_DIALOG");
  }

  private void buildUI() {
    Container container = super.getContentPane();
    container.setLayout(new BorderLayout(0, 0));

    banner = new BannerPanel();
    container.add("North", banner);

    JPanel contentPaneAndButtons = new JPanel();
    contentPaneAndButtons.setLayout(
      LookAndFeelTweaks.createVerticalPercentLayout());
    contentPaneAndButtons.setBorder(LookAndFeelTweaks.WINDOW_BORDER);
    container.add("Center", contentPaneAndButtons);

    contentPane = new JPanel();
    LookAndFeelTweaks.setBorderLayout(contentPane);
    LookAndFeelTweaks.setBorder(contentPane);
    contentPaneAndButtons.add(contentPane, "*");

    buttonPane = new JPanel();
    buttonPane.setLayout(LookAndFeelTweaks.createButtonAreaLayout());
    LookAndFeelTweaks.setBorder(buttonPane);
    contentPaneAndButtons.add(buttonPane);

    // layout is done, now everything about "cancel", "escape key" and "click
    // on close in window bar"
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    ((JComponent)container).registerKeyboardAction(
      cancelOrCloseAction,
      KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
      JComponent.WHEN_IN_FOCUSED_WINDOW);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        cancel();
      }
    });

    // default mode is OK_CANCEL_DIALOG
    setDialogMode(OK_CANCEL_DIALOG);
  }

}

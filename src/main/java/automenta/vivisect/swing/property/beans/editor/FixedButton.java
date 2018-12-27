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
package automenta.vivisect.swing.property.beans.editor;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.UIManager;

import automenta.vivisect.swing.property.util.OS;

/**
 * A button with a fixed size to workaround bugs in OSX. Submitted by Hani
 * Suleiman. Hani uses an icon for the ellipsis, I've decided to hardcode the
 * dimension to 16x30 but only on Mac OS X.
 */
public final class FixedButton extends JButton {

	private static final long serialVersionUID = 6032639948619967752L;

	public FixedButton() {
		super("...");

		if (OS.isMacOSX() && UIManager.getLookAndFeel().isNativeLookAndFeel()) {
			setPreferredSize(new Dimension(16, 30));
		}

		setMargin(new Insets(0, 0, 0, 0));
	}

}

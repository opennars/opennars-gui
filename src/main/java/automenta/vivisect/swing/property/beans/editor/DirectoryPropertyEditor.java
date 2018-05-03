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

//import com.l2fprod.common.swing.JDirectoryChooser;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import automenta.vivisect.swing.property.sheet.editor.FileEditor;
import automenta.vivisect.swing.property.swing.UserPreferences;
import automenta.vivisect.swing.property.util.ResourceManager;


/**
 * DirectoryPropertyEditor.<br>
 * 
 */
public class DirectoryPropertyEditor extends FileEditor {

	@Override
	protected void selectFile() {
		ResourceManager rm = ResourceManager.all(FileEditor.class);

		JFileChooser chooser = UserPreferences.getDefaultDirectoryChooser();

		chooser.setDialogTitle(rm.getString("DirectoryPropertyEditor.dialogTitle"));
		chooser.setApproveButtonText(
		rm.getString("DirectoryPropertyEditor.approveButtonText"));
		chooser.setApproveButtonMnemonic(
		rm.getChar("DirectoryPropertyEditor.approveButtonMnemonic"));

		File oldFile = (File) getValue();
		if (oldFile != null && oldFile.isDirectory()) {
			try {
				chooser.setCurrentDirectory(oldFile.getCanonicalFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(editor)) {
			File newFile = chooser.getSelectedFile();
			String text = newFile.getAbsolutePath();
			((FileEditorComponent) editor).setText(text);
			firePropertyChange(oldFile, newFile);
		}
	}

}

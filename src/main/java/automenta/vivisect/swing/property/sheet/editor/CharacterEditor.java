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
package automenta.vivisect.swing.property.sheet.editor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SpinnerListModel;


/**
 * Number editor.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class CharacterEditor extends SpinnerEditor {

	public CharacterEditor() {
		super();
	}

	public CharacterEditor(Object property) {
		super();
		buildModel(Character.MIN_VALUE, (char) (Character.MAX_VALUE - 1));
		formatSpinner();
	}

	protected void buildModel(char min, char max) {

		List<Character> characters = new ArrayList<Character>();

		for (char c = min; c <= max; c++) {
			characters.add(c);
		}

		spinner.setModel(new SpinnerListModel(characters));

	}
}

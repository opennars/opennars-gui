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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.UIManager;

import automenta.vivisect.swing.property.beans.editor.AbstractPropertyEditor;
import automenta.vivisect.swing.property.swing.LookAndFeelTweaks;

/**
 * @author zp
 * 
 */
public class NumberEditor extends AbstractPropertyEditor {

	private double minVal, maxVal;
	private Object lastGoodValue;
	protected NumberFormat format;
	    
	    
	public NumberEditor(double minVal, double maxVal, int fracDigits) {
	    editor = new JTextField();
        ((JTextField)editor).setBorder(LookAndFeelTweaks.EMPTY_BORDER);
        this.minVal = minVal;
        this.maxVal = maxVal;
		
		if(fracDigits == 0) {
			format = NumberFormat.getIntegerInstance();			
		}
		else {
			format = new DecimalFormat("0.0########");
			((DecimalFormat)format).setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
		    format.setMaximumFractionDigits(fracDigits);			
		}
		format.setGroupingUsed(false);		
	}
	
   
    public Object getValue() {
        String text = ((JTextField) editor).getText();
        if (text == null || text.trim().length() == 0) {
            return getDefaultValue();
        }

        // collect all numbers from this textfield
        StringBuffer number = new StringBuffer();
        number.ensureCapacity(text.length());
        for (int i = 0, c = text.length(); i < c; i++) {
            char character = text.charAt(i);
            if ('.' == character || '-' == character || 'E' == character || Character.isDigit(character)) {
                number.append(character);
            }
            else if (' ' == character) {
                continue;
            }
            else {
                break;
            }
        }
        
        Object before = lastGoodValue;
        try {        	
        	lastGoodValue = Double.parseDouble(number.toString());
        	if ((double)lastGoodValue > maxVal)
        		throw new Exception(lastGoodValue + " is too large");
        	if ((double)lastGoodValue < minVal)
        		throw new Exception(lastGoodValue + " is too small");
        }
        catch (Exception e) {
        	lastGoodValue = before;
            UIManager.getLookAndFeel().provideErrorFeedback(editor);
        }

        return lastGoodValue;
    }

    public void setValue(Object value) {
        if (value instanceof Number) {
            ((JTextField) editor).setText(format.format(((Number)value).doubleValue()));
        }
        else {
            ((JTextField) editor).setText("" + getDefaultValue());
        }
        lastGoodValue = value;
    }

    private Object getDefaultValue() {
       return 0d;
    }
    
    public static class IntegerEditor extends NumberEditor {
    	public IntegerEditor() {
    		super(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    	}
    }
    
    public static class ShortEditor extends NumberEditor {
    	public ShortEditor() {
    		super(Short.MIN_VALUE, Short.MAX_VALUE, 0);
    	}
    }
    
    public static class ByteEditor extends NumberEditor {
    	public ByteEditor() {
    		super(Byte.MIN_VALUE, Byte.MAX_VALUE, 0);
    	}
    }
    
    public static class LongEditor extends NumberEditor {
    	public LongEditor() {
    		super(Long.MIN_VALUE, Long.MAX_VALUE, 0);
    	}
    }
    
    public static class FloatEditor extends NumberEditor {
    	public FloatEditor() {
    		super(-Float.MAX_VALUE, Float.MAX_VALUE, 4);
    	}
    }
    
    public static class DoubleEditor extends NumberEditor {
    	public DoubleEditor() {
    		super(-Double.MAX_VALUE, Double.MAX_VALUE, 12);
    	}
    }
}

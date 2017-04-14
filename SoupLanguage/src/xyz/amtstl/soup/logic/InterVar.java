package xyz.amtstl.soup.logic;

import java.util.ArrayList;
import java.util.List;

import xyz.amtstl.soup.exceptions.SoupVariableException;
import xyz.amtstl.soup.misc.IO;

public class InterVar {
	private static List<String> temp = new ArrayList<String>();
	
	public static void parseInternalVar(String[] numbers) throws NumberFormatException, SoupVariableException {
		temp = new ArrayList<String>();
		
		/* Parse the v function */
		for (String e : numbers) {
			if (e.charAt(0) == 'v') {
				int indexReq = Integer.parseInt(e.substring(1));
				temp.add(String.valueOf(VariableHandler.getVar(indexReq)));
			}
			else {
				temp.add(e);
			}
		}
	}
	
	public static List<String> getParsedNumbers() {
		return temp;
	}
}
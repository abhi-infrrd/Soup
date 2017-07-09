package xyz.amtstl.soup.logic;

import xyz.amtstl.soup.Soup;
import xyz.amtstl.soup.exceptions.SoupFunctionNotDeclaredException;
import xyz.amtstl.soup.exceptions.SoupSyntaxException;
import xyz.amtstl.soup.exceptions.SoupVariableException;

public class Looper {
	private static int groundState = 0;
	public static boolean isBreak = false;
	
	/**
	 * Executes a new loop sequence
	 * @param minBound starting point
	 * @param maxBound loop will continue until
	 * @param cache line of code from program
	 * @throws NumberFormatException
	 * @throws SoupVariableException
	 * @throws SoupSyntaxException
	 * @throws SoupFunctionNotDeclaredException 
	 */
	public static void execNewForLoop(int minBound, int maxBound, String cache, String direction) throws NumberFormatException, SoupVariableException, SoupSyntaxException, SoupFunctionNotDeclaredException {
		groundState = Soup.getMainLogic().getIndex();
		for (int e = minBound; e < maxBound; e++) {
			//IO.printFloat(Soup.logic.v.getVar(1000));
			
			for (int i = groundState; i < cache.length(); i++) {
				//IO.printInt(i);
				if (cache.charAt(i) == ';') {
					Soup.checkToken(i, cache, cache.charAt(i));
					i = Soup.getMainLogic().getIndex();
				}
				else {
					Soup.checkToken(i, cache, cache.charAt(i));
				}
				//Soup.checkToken(i, cache, cache.charAt(i));
			}
			Soup.getMainLogic().setIndex(groundState);
			Soup.getMainLogic();
			//e = (int) Soup.logic.v.getVar(1000);
			LogicController.v.insertVar((float) Float.valueOf(e), 1000);
			if (isBreak) {
				isBreak = false;
				break;
			}
		}
	}
	
	public static void execNewForLoopDecre(int maxBound, int minBound, String cache, String direction) throws NumberFormatException, SoupVariableException, SoupSyntaxException, SoupFunctionNotDeclaredException {
		groundState = Soup.getMainLogic().getIndex();
		for (int e = maxBound; e > minBound; e--) {
			//IO.printFloat(Soup.logic.v.getVar(1000));
			
			for (int i = groundState; i < cache.length(); i++) {
				//IO.printInt(i);
				if (cache.charAt(i) == ';') {
					Soup.checkToken(i, cache, cache.charAt(i));
					i = Soup.getMainLogic().getIndex();
				}
				else {
					Soup.checkToken(i, cache, cache.charAt(i));
				}
				//Soup.checkToken(i, cache, cache.charAt(i));
			}
			Soup.getMainLogic().setIndex(groundState);
			Soup.getMainLogic();
			//e = (int) Soup.logic.v.getVar(1000);
			LogicController.v.insertVar((float) Float.valueOf(e), 1000);
			if (isBreak) {
				isBreak = false;
				break;
			}
		}
	}
	
	public static void execNewWhileLoop(String cache) throws NumberFormatException, SoupVariableException, SoupSyntaxException, SoupFunctionNotDeclaredException {
		groundState = Soup.getMainLogic().getIndex();
		
		int firstCondition = (int)Integer.valueOf((int) Float.parseFloat(LogicController.ns.get(0)));
		int secondCondition = (int)Integer.valueOf((int) Float.parseFloat(LogicController.ns.get(1)));
		
		while ((float)firstCondition == (float)secondCondition) {
			// parse the line
			for (int i = groundState; i < cache.length(); i++) {
				if (cache.charAt(i) == ';') {
					Soup.checkToken(i, cache, cache.charAt(i));
					i = Soup.getMainLogic().getIndex();
				}
				else {
					Soup.checkToken(i, cache, cache.charAt(i));
				}
			}
			
			Soup.getMainLogic().setIndex(groundState);
			LogicController.ns = LogicController.p.parse(0, cache);
			firstCondition = (int)Integer.valueOf((int) Float.parseFloat(LogicController.ns.get(0)));
			secondCondition = (int)Integer.valueOf((int) Float.parseFloat(LogicController.ns.get(1)));
			
			/*IO.printInt(firstCondition);
			IO.printInt(secondCondition);*/
			
			if (isBreak) {
				isBreak = false;
				break;
			}
		}
	}
	
	public static void execNewWhileNotLoop(String cache) throws NumberFormatException, SoupVariableException, SoupSyntaxException, SoupFunctionNotDeclaredException {
		groundState = Soup.getMainLogic().getIndex();
		
		int firstCondition = (int)Integer.valueOf((int) Float.parseFloat(LogicController.ns.get(0)));
		int secondCondition = (int)Integer.valueOf((int) Float.parseFloat(LogicController.ns.get(1)));
		
		while ((float)firstCondition != (float)secondCondition) {
			// parse the line
			for (int i = groundState; i < cache.length(); i++) {
				if (cache.charAt(i) == ';') {
					Soup.checkToken(i, cache, cache.charAt(i));
					i = Soup.getMainLogic().getIndex();
				}
				else {
					Soup.checkToken(i, cache, cache.charAt(i));
				}
			}
			
			Soup.getMainLogic().setIndex(groundState);
			LogicController.ns = LogicController.p.parse(0, cache);
			firstCondition = (int)Integer.valueOf((int) Float.parseFloat(LogicController.ns.get(0)));
			secondCondition = (int)Integer.valueOf((int) Float.parseFloat(LogicController.ns.get(1)));
			
			/*IO.printInt(firstCondition);
			IO.printInt(secondCondition);*/
			
			if (isBreak) {
				isBreak = false;
				break;
			}
		}
	}
}
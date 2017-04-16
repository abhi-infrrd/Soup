package xyz.amtstl.soup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import xyz.amtstl.soup.exceptions.SoupSyntaxException;
import xyz.amtstl.soup.exceptions.SoupVariableException;
import xyz.amtstl.soup.logic.LanguageDictionary;
import xyz.amtstl.soup.logic.LogicController;
import xyz.amtstl.soup.misc.IO;

public class Soup {
	public static int lineNumber = 1;
	private static boolean canAdvance = true;
	
	// controllers
	static LogicController logic = new LogicController();
	static LanguageDictionary lang = new LanguageDictionary();
	
	@SuppressWarnings("static-access")
	/**
	 * Main thread marshal
	 * @param args args from user
	 * @throws Exception for forced exit
	 */
	public static void main(String args[]) throws Exception {
		FileReader reader = null;
		
		try {
			//reader = new FileReader(System.getProperty("user.dir") + "/" + args[0].toString());
			//FileReader reader = new FileReader("C:/users/alex/desktop/github/soup/Files/program.soup");
			//FileReader reader = new FileReader("C:/Users/amigala/Desktop/Github/Soup/Files/program.soup");
			reader = new FileReader("C:/Users/Alex/Desktop/Github/Soup/Files/program.soup");
		}
		catch (FileNotFoundException ex) {
			IO.println("File not found! Are you sure it is in this folder?");
			System.exit(0);
		}
		BufferedReader buff = new BufferedReader(reader);
		
		/*
		 * ALWAYS USE BREAKS WHEN ADDING NEW TOKENS AND FUNCTIONS
		 * 
		 */
		while (true) {
			final String cache = buff.readLine();
			
			try {
				for (int i = 0; i < cache.length(); i++) {
					char c = cache.charAt(i);
					switch (c) {
					case '+' : // add two numbers
						logic.soupAdd(i, cache);
						i = logic.getIndex();
						break;
					case '-' : // subtract two numbers
						logic.soupSubtract(i, cache);
						i = logic.getIndex();
						break;
					case '*' : // multiply two numbers
						logic.soupMultiply(i, cache);
						i = logic.getIndex();
						break;
					case '%' : // divide two numbers
						logic.soupDivide(i, cache);		
						i = logic.getIndex();
						break;
					case '^' : // pow one number
						logic.soupPow(i, cache);	
						i = logic.getIndex();
						break;
					case '#' : // basic logarithm
						logic.soupLog(i, cache);	
						i = logic.getIndex();
						break;
					case 'A' : // area
						logic.soupArea(i, cache);
						i = logic.getIndex();
						break;
					case '[' : // basic if statement
						logic.soupIf(i, cache);
						i = logic.getIndex();
						break;
					case 'p' : // print line
						logic.soupPrint(i, cache);
						i = logic.getIndex();
						break;
					case ';' : // extension of if
						logic.soupIfDo(i, cache);
						i = logic.getIndex();
						break;
					case ':' : // stores last result
						logic.soupStoreVar(i, cache);
						i = logic.getIndex();
						break;
					case 'v': // gets a variable
						logic.soupRetrieveVar(i, cache);
						i = logic.getIndex();
						break;
					case 'i': // gets var from user and stores it
						logic.soupStoreUserIn(i, cache);
						i = logic.getIndex();
						break;
					case '@': // quadratic formula
						logic.soupQuad(i, cache);
						i = logic.getIndex();
						break;
					case '$' : // trig
						logic.soupTrig(i, cache);
						i = logic.getIndex();
						break;
					case '|' : // absolute value
						logic.soupAbs(i, cache);
						i = logic.getIndex();
						break;
					case '?' : // round number to int
						logic.soupRound(i, cache);
						i = logic.getIndex();
						break;
					case '&' : // square root
						logic.soupSquareRoot(i, cache);
						i = logic.getIndex();
						break;
					case '~': // stores a single variable
						logic.soupStoreSingle(i, cache);
						i = logic.getIndex();
						break;
					case '/': // comments
						logic.soupComment(i, cache);
						i = logic.getIndex();
						break;
					case '.' : // like a semicolon
						break;
					case ' ': // space nullifier
						break;
					default :
						throw new SoupSyntaxException(cache.charAt(i), i+1, lineNumber);
					}
				}
			} catch (NullPointerException ex) {
				System.exit(0);
			}
			lineNumber++;
			logic.setIndex(0);
		}
	}
	
	@SuppressWarnings("static-access")
	/**
	 * Did the corresponding function per character
	 * @param c
	 * @param i
	 * @param cache
	 * @throws NumberFormatException
	 * @throws SoupVariableException
	 * @throws SoupSyntaxException
	 */
	public static void parseFunc(char c, int i, String cache) throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		switch (c) {
			case '!' : // add two numbers
				logic.soupAdd(i, cache);
				break;
			case '@' : // subtract two numbers
				logic.soupSubtract(i, cache);
				break;
			case '#' : // multiply two numbers
				logic.soupMultiply(i, cache);			
				break;
			case '$' : // divide two numbers
				logic.soupDivide(i, cache);			
				break;
			case '%' : // pow one number
				logic.soupPow(i, cache);			
				break;
			case '^' : // basic logarithm
				logic.soupLog(i, cache);			
				break;
			case '[' : // basic if statement
				logic.soupIf(i, cache);
				break;
			case '&' : // print line
				logic.soupPrint(i, cache);
				break;
			case ';' : // extension of if
				logic.soupIfDo(i, cache);
				break;
			case ':' : // stores last result
				logic.soupStoreVar(i, cache);
			case 'v': // gets a variable
				logic.soupRetrieveVar(i, cache);
				break;
			case 'i': // gets var from user and stores it
				logic.soupStoreUserIn(i, cache);
				break;
			case '*': // calculate the quad formula
				logic.soupQuad(i, cache);
				break;
			case '+': // store a variable
				logic.soupStoreSingle(i, cache);
				break;
			case '.' : // like a semicolon
				break;
			default :
				throw new SoupSyntaxException(cache.charAt(i), i, lineNumber);
		}
	}
	
	/**
	 * Checks tokens
	 * @deprecated
	 * @param i
	 * @param cache
	 * @param c
	 * @throws NumberFormatException
	 * @throws SoupVariableException
	 * @throws SoupSyntaxException
	 */
	public static void checkToken(int i, String cache, char c) throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		for (int e = 0; e < lang.languageTokens.size(); e++) {
			if (cache.charAt(i) == lang.languageTokens.get(e)) {
				parseFunc(c, i, cache);
				//indexCache = logic.getIndex();
			}
		}
	}
}
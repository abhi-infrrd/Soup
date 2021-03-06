package xyz.amtstl.soup.testing;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

import xyz.amtstl.soup.Soup;
import xyz.amtstl.soup.exceptions.SoupFunctionNotDeclaredException;
import xyz.amtstl.soup.exceptions.SoupSyntaxException;
import xyz.amtstl.soup.exceptions.SoupVariableException;
import xyz.amtstl.soup.logic.VariableHandler;

public class TestLogicControllerSoup {
	private static Soup soup = new Soup();
	private static ByteArrayOutputStream stream;
	private static PrintStream printingStream;
	
	public TestLogicControllerSoup() {
		stream = new ByteArrayOutputStream();
		printingStream = new PrintStream(stream);
	}
	
	@Test
	public void testSoupAdd() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupSubtract(0, "_{10,5}");
		
		float rest = soup.logic.lastResult;
		
		Assert.assertEquals(5.0, rest, 0.0);
	}
	
	@Test
	public void testSoupSubtract() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupAdd(0, "+{4,5}");
		
		float res = soup.logic.lastResult;
		
		Assert.assertEquals(9.0, res, 0.0);
	}
	
	@Test
	public void testSoupMultiply() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupMultiply(0, "*{5,5}");
		
		float res = soup.logic.lastResult;
		
		Assert.assertEquals(25.0, res, 0.0);
	}
	
	@Test
	public void testSoupDivide() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupDivide(0, "%{10,5}");
		
		float res = soup.logic.lastResult;
		
		Assert.assertEquals(2.0, res, 0.0);
	}
	
	@Test
	public void testSoupPow() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupPow(0, "^{2,3}");
		
		float res = soup.logic.lastResult;
		
		Assert.assertEquals(8.0, res, 0.0);
	}
	
	@Test
	public void testSoupLog() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupLog(0, "#{2}");
		
		float res = soup.logic.lastResult;
		
		Assert.assertEquals(0.301029957, res, 0.100000000);
	}
	
	@Test
	public void testSoupTrig() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		/*
		 * Will need to test all iterations of the trig functions
		 */
		
		// sine
		soup.logic.soupTrig(0, "${s,3}");
		float sine = soup.logic.lastResult;
		Assert.assertEquals(0.1411200081, sine, 0.1000);
		
		// cosine
		soup.logic.soupTrig(0, "${c,3}");
		float cosine = soup.logic.lastResult;
		Assert.assertEquals(-0.9899924966, cosine, 0.1000);
		
		// tangent
		soup.logic.soupTrig(0, "${t,3}");
		float tangent = soup.logic.lastResult;
		Assert.assertEquals(-0.1425465431, tangent, 0.1000);
		
		// arcsine
		soup.logic.soupTrig(0, "${arcs,0.3}");
		float arcsine = soup.logic.lastResult;
		Assert.assertEquals(0.304692654, arcsine, 0.001);
		
		// arccosine
		soup.logic.soupTrig(0, "${arcc,0.3}");
		float arccosine = soup.logic.lastResult;
		Assert.assertEquals(1.266103673, arccosine, 0.001);
		
		// arctangent
		soup.logic.soupTrig(0, "${arct,0.3}");
		float arctangent = soup.logic.lastResult;
		Assert.assertEquals(0.2914567945, arctangent, 0.001);
	}
	
	@Test
	public void testSoupArea() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupArea(0, "A{s,4,5}");
		
		float square = soup.logic.lastResult;
		Assert.assertEquals(20.0, square, 0.0);
		
		// triangle
		soup.logic.soupArea(0, "A{tri,4,5}");
		
		float triangle = soup.logic.lastResult;
		Assert.assertEquals(10.0, triangle, 0.0);
		
		// trapezoid
		soup.logic.soupArea(0, "A{tra,1,5,7}");
		
		float trapezoid = soup.logic.lastResult;
		Assert.assertEquals(21.0, trapezoid, 0.0);
	}
	
	@Test
	public void testSoupAbs() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupAbs(0, "|{-347.4}");
		
		/*
		 * note that abs is accurate to 0.1
		 */
		float abs = soup.logic.lastResult;
		Assert.assertEquals(347.4, abs, 0.1);
	}
	
	@Test
	public void testSoupRound() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupRound(0, "?{5.6}");
		
		/*
		 * 100 percent accuracy
		 */
		float round = soup.logic.lastResult;
		Assert.assertEquals(6, round, 0.0);
	}
	
	@Test
	public void testSoupSquareRoot() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupSquareRoot(0, "&{5}");
		
		float squareroot = soup.logic.lastResult;
		
		Assert.assertEquals(2.236067977, squareroot, 0.01);
	}
	
	@Test
	public void testSoupRandom() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupRandomNum(0, "R{0,10}");
		
		float randnum = soup.logic.lastResult;
		boolean success = false;
		
		if (randnum < 10 && randnum > 0) {
			System.out.println("RANDNUM: " + String.valueOf(randnum));
			success = true;
		}
		
		Assert.assertTrue(success);
	}
	
	@Test
	public void testSoupIf() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupIf(0, "={4,4}");
		
		Assert.assertTrue(soup.logic.ifState);
		
		VariableHandler.insertVar((float) 4.3, 10);
		
		soup.logic.soupIf(0, "={v10,3}");
		
		Assert.assertFalse(soup.logic.ifState);
	}
	
	@Test
	public void testSoupTestIfLessThan() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupIfLessThan(0, "<{3,5}");
		
		Assert.assertTrue(soup.logic.ifState);
		
		soup.logic.soupIfLessThan(0, "<{6,5}");
		
		Assert.assertFalse(soup.logic.ifState);
	}
	
	@Test
	public void testSoupIfGreaterThan() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupIfGreaterThan(0, ">{5,4}");
		
		Assert.assertTrue(soup.logic.ifState);
		
		soup.logic.soupIfGreaterThan(0, ">{3,4}");
		
		Assert.assertFalse(soup.logic.ifState);
	}
	
	@Test
	public void testSoupIfDo() throws NumberFormatException, SoupVariableException, SoupSyntaxException, SoupFunctionNotDeclaredException {
		soup.logic.ifState = true;
		
		soup.logic.soupIfDo(0, ";(+{5,6}!_{5,4})");
		
		Assert.assertEquals(11.0, soup.logic.lastResult, 0.0);
		
		soup.logic.ifState = false;
		
		soup.logic.soupIfDo(0, ";(+{5,6}!_{5,4})");
		
		Assert.assertEquals(1.0, soup.logic.lastResult, 0.0);
	}
	
	@Test
	public void testSoupRetrieveVar() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupStoreSingle(0, "~{4,100}");
		System.out.println("Requested var is 4 at index:100");
		System.out.println("===Retrieved Var===");
		soup.logic.soupRetrieveVar(0, "V{100}");
	}
	
	@Test
	public void testSoupStoreVar() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupSubtract(0, "_{8,0.8}");
		soup.logic.soupStoreVar(0, ":{200}");
		Assert.assertEquals(7.2, soup.logic.lastResult, 0.1);
	}
	
	@Test
	public void testSoupStoreSingle() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupStoreSingle(0, "~{34.6,123}");
		
		Assert.assertEquals(34.6, VariableHandler.getVar(123), 0.1);
	}
	
	@Test
	public void testSoupStoreGetFunction() throws NumberFormatException, SoupVariableException, SoupSyntaxException, SoupFunctionNotDeclaredException {
		soup.logic.soupStoreFunction(0, "S(+{3,3}:{5}!0)");
		soup.logic.soupGetFunction(0, "F{0}");
		Assert.assertEquals(6, soup.logic.lastResult, 0.0);
	}
	
	@Test
	public void testSoupRefreshNumbers() throws NumberFormatException, SoupVariableException, SoupSyntaxException {
		soup.logic.soupRefreshNumbers(0, "+{3,4,6,7,78,90,14300}");
		
		String[] reals = new String[] {"3","4","6","7","78","90","14300"};
		
		for (int e = 0; e < soup.logic.ns.size(); e++) {
			Assert.assertEquals(reals[e], soup.logic.ns.get(e));
		}
	}
	
	@Test
	public void testSoupBreakLoop() {
		soup.logic.soupBreakLoop();
		
		Assert.assertTrue(soup.logic.isBreak);
	}
	
	@Test
	public void testSoupComment() throws NumberFormatException, SoupVariableException {
		String test = "+{5,4} // this is a comment";
		soup.logic.soupComment(0, test);
		
		Assert.assertEquals(test.length(), soup.logic.index);
	}
	
	/*
	 * Soup functions
	 */
	
	@Test
	public void testParseFunc() throws NumberFormatException, SoupVariableException, SoupSyntaxException, SoupFunctionNotDeclaredException {
		soup.parseFunc('_', 0, "_{5.3,5.2}");
		
		Assert.assertEquals(0.1, soup.logic.lastResult, 0.1);
	}
	
	@Test
	public void testCheckToken() throws NumberFormatException, SoupVariableException, SoupSyntaxException, SoupFunctionNotDeclaredException {
		soup.checkToken(0, "*{200,150}", '*');
		
		Assert.assertEquals(30000, soup.logic.lastResult, 0.0);
	}
}
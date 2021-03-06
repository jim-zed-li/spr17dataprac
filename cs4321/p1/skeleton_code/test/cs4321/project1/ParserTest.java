package cs4321.project1;

import static org.junit.Assert.*;

import org.junit.Test;

import cs4321.project1.tree.TreeNode;

public class ParserTest {

	/*
	 * This class depends on the correct functioning of PrintTreeVisitor(), which is provided for you.
	 */
			
	@Test
	public void testSingleNumber() {
		Parser p1 = new Parser("1.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("1.0", v1.getResult());

	}
	
	@Test
	public void testUnaryMinusSimple() {
		Parser p1 = new Parser("- 1.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(-1.0)", v1.getResult());

	}
	
	@Test
	public void testUnaryMinusComplex() {
		Parser p1 = new Parser("- - 1.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(-(-1.0))", v1.getResult());

	}
	@Test
	public void testParenthesesSimple() {
		Parser p1 = new Parser("( 1.0 )");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("1.0", v1.getResult());
		Parser p2 = new Parser("( ( ( 1.0 ) ) )");
		TreeNode parseResult2 = p2.parse();
		PrintTreeVisitor v2 = new PrintTreeVisitor();
		parseResult2.accept(v2);
		assertEquals("1.0", v2.getResult());

	}
	
	@Test
	public void testSimpleAddition() {
		Parser p1 = new Parser("1.0 + 2.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(1.0+2.0)", v1.getResult());

	}
	
	@Test
	public void testComplexAddition() {
		Parser p1 = new Parser("1.0 + 2.0 + 3.0 + 4.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(((1.0+2.0)+3.0)+4.0)", v1.getResult());

	}
	
	@Test
	public void testSimpleMultiplication() {
		Parser p1 = new Parser("1.0 * 2.0");
		TreeNode parseResult1 =  p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(1.0*2.0)", v1.getResult());

	}
	
	@Test
	public void testComplexMultiplication() {
		Parser p1 = new Parser("1.0 * 2.0 * 3.0 * 4.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(((1.0*2.0)*3.0)*4.0)", v1.getResult());

	}
	
	@Test
	public void testSimpleSubtraction() {
		Parser p1 = new Parser("1.0 - 2.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(1.0-2.0)", v1.getResult());

	}
	
	@Test
	public void testComplexSubtraction() {
		Parser p1 = new Parser("1.0 - 2.0 - 3.0 - 4.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(((1.0-2.0)-3.0)-4.0)", v1.getResult());

	}
	
	@Test
	public void testSimpleDivision() {
		Parser p1 = new Parser("1.0 / 2.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(1.0/2.0)", v1.getResult());

	}
	
	@Test
	public void testComplexDivision() {
		Parser p1 = new Parser("1.0 / 2.0 / 3.0 / 4.0");
		TreeNode parseResult1 =  p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(((1.0/2.0)/3.0)/4.0)", v1.getResult());

	}
	
	@Test
	public void testComplexExpression1() {
		Parser p1 = new Parser("1.0 + 2.0 * 3.0");
		TreeNode parseResult1 = null;
			parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(1.0+(2.0*3.0))", v1.getResult());

	}

	@Test
	public void testComplexExpression2() {
		Parser p1 = new Parser("- 2.0 - - 3.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("((-2.0)-(-3.0))", v1.getResult());

	}

	
	@Test
	public void testComplexExpression3() {
		Parser p1 = new Parser("5.0 + ( - 12.0 / ( 2.0 * 3.0 ) )");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(5.0+((-12.0)/(2.0*3.0)))", v1.getResult());

	}
	
	@Test
	public void testAdditionSimple() {
		Parser p1 = new Parser("1.0 + 2.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(1.0+2.0)", v1.getResult());
	}
	
	// added test
	@Test
	public void testAdditionNoParens() {
		Parser p1 = new Parser("1.0 + 2.0 + 3.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("((1.0+2.0)+3.0)", v1.getResult());
	}
	
	// added test
	@Test
	public void testAdditionComplex() {
		Parser p1 = new Parser("( 1.0 + 2.0 ) + 3.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("((1.0+2.0)+3.0)", v1.getResult());
	}
	
	// added test
	@Test
	public void testMultiplicationComplex() {
		Parser p1 = new Parser("( 1.0 * 2.0 ) * 3.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("((1.0*2.0)*3.0)", v1.getResult());
	}
	
	// added test
	@Test
	public void testParens() {
		Parser p1 = new Parser("( ( ( 1.0 + 2.0 ) ) )");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(1.0+2.0)", v1.getResult());
	}
	
	// added test
	@Test
	public void testComplex() {
		Parser p1 = new Parser("( 1.0 - ( - 2.0 ) ) * 3.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("((1.0-(-2.0))*3.0)", v1.getResult());
	}
	
	// added test
	@Test
	public void testVeryComplex() {
		Parser p1 = new Parser("( 1.0 + ( - ( - 2.0 ) ) ) * 3.0 / ( 5.0 - ( - 2.0 ) )");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(((1.0+(-(-2.0)))*3.0)/(5.0-(-2.0)))", v1.getResult());
	}

}

package cs4321.project1;

import static org.junit.Assert.*;

import org.junit.Test;

import cs4321.project1.list.*;

public class EvaluatePrefixListVisitorTest {
	
	private static final double DELTA = 1e-15;

	@Test
	public void testSingleNumberNode() {
		ListNode n1 = new NumberListNode(1.0);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n1.accept(v1);
		assertEquals(1.0, v1.getResult(), DELTA);
	}

	@Test
	public void testAdditionSimple() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new AdditionListNode();
		n3.setNext(n2);
		n2.setNext(n1);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n3.accept(v1);
		assertEquals(3.0, v1.getResult(), DELTA);
		
		ListNode n4 = new NumberListNode(1.0);
		ListNode n5 = new NumberListNode(2.0);
		ListNode n6 = new AdditionListNode();
		n6.setNext(n5);
		n5.setNext(n4);
		EvaluatePrefixListVisitor v2 = new EvaluatePrefixListVisitor();
		n6.accept(v2);
		assertEquals(3.0, v2.getResult(), DELTA);
	}
	
	@Test
	public void testAdditionMultipleInstances() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new AdditionListNode();
		ListNode n4 = new NumberListNode(3.0);
		ListNode n5 = new AdditionListNode();
		n5.setNext(n4);
		n4.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n1);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n5.accept(v1);
		assertEquals(6.0, v1.getResult(), DELTA);
	}
	
	@Test
	public void testMultiplicationMultipleInstances() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new MultiplicationListNode();
		ListNode n4 = new NumberListNode(3.0);
		ListNode n5 = new MultiplicationListNode();
		n5.setNext(n4);
		n4.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n1);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n5.accept(v1);
		assertEquals(6.0, v1.getResult(), DELTA);
	}

	@Test
	public void testSubtractionSimple() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new SubtractionListNode();
		n3.setNext(n1);
		n1.setNext(n2);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n3.accept(v1);
		assertEquals(-1.0, v1.getResult(), DELTA);
		
		ListNode n4 = new NumberListNode(1.0);
		ListNode n5 = new NumberListNode(2.0);
		ListNode n6 = new SubtractionListNode();
		n6.setNext(n5);
		n5.setNext(n4);
		EvaluatePrefixListVisitor v2 = new EvaluatePrefixListVisitor();
		n6.accept(v2);
		assertEquals(1.0, v2.getResult(), DELTA);
	}
	
	@Test
	public void testSubtractionMultipleInstances() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new SubtractionListNode();
		ListNode n4 = new NumberListNode(3.0);
		ListNode n5 = new SubtractionListNode();
		n5.setNext(n4);
		n4.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n1);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n5.accept(v1);
		assertEquals(2.0, v1.getResult(), DELTA);
	}
	
	@Test
	public void testDivisionSimple() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new DivisionListNode();
		n3.setNext(n1);
		n1.setNext(n2);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n3.accept(v1);
		assertEquals(0.5, v1.getResult(), DELTA);
		
		ListNode n4 = new NumberListNode(1.0);
		ListNode n5 = new NumberListNode(2.0);
		ListNode n6 = new DivisionListNode();
		n6.setNext(n5);
		n5.setNext(n4);
		EvaluatePrefixListVisitor v2 = new EvaluatePrefixListVisitor();
		n6.accept(v2);
		assertEquals(2.0, v2.getResult(), DELTA);
	}
	
	@Test
	public void testDivisionMultipleInstances() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new DivisionListNode();
		ListNode n4 = new NumberListNode(3.0);
		ListNode n5 = new DivisionListNode();
		n5.setNext(n4);
		n4.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n1);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n5.accept(v1);
		assertEquals(1.5, v1.getResult(), DELTA);
	}
	
	@Test
	public void testUnaryMinusNode() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new UnaryMinusListNode();
		ListNode n3 = new UnaryMinusListNode();
		n3.setNext(n2);
		n2.setNext(n1);
		
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n3.accept(v1);
		assertEquals(1.0, v1.getResult(), DELTA);
		
		EvaluatePrefixListVisitor v2 = new EvaluatePrefixListVisitor();
		n2.accept(v2);
		assertEquals(-1.0, v2.getResult(), DELTA);
	}
	
	@Test
	public void testComplexExpression(){
		ListNode n1 = new NumberListNode(12.0); 
		ListNode n2 = new NumberListNode(2.0); 
		ListNode n3 = new NumberListNode(3.0); 
		ListNode n4 = new UnaryMinusListNode();
		ListNode n5 = new AdditionListNode();
		ListNode n6 = new MultiplicationListNode();
		ListNode n7 = new DivisionListNode();
		ListNode n8 = new NumberListNode(5.0);
		n5.setNext(n8);
		n8.setNext(n7);
		n7.setNext(n4);
		n4.setNext(n1);
		n1.setNext(n6);
		n6.setNext(n2);
		n2.setNext(n3);	
		//expression is + 5 / ~ 12 * 2 3
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n5.accept(v1);
		assertEquals(3.0, v1.getResult(), DELTA);
	}
	
	// added test
	@Test
	public void testComplex1() {
		ListNode n1 = new MultiplicationListNode();
		ListNode n2 = new UnaryMinusListNode();
		ListNode n3 = new NumberListNode(2.0);
		ListNode n4 = new AdditionListNode();
		ListNode n5 = new NumberListNode(3.0);
		ListNode n6 = new NumberListNode(1.0);
		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		n4.setNext(n5); 
		n5.setNext(n6); // expression is * ~ 2 + 3 1
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n1.accept(v1);
		assertEquals(-8.0, v1.getResult(), DELTA);
	}
	
	// added test
	@Test
	public void testComplex2() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2_1 = new NumberListNode(2.0);
		ListNode n2_2 = new NumberListNode(2.0);
		ListNode n3 = new NumberListNode(3.0);
		ListNode n4 = new NumberListNode(4.0);
		ListNode n_um1 = new UnaryMinusListNode();
		ListNode n_um2 = new UnaryMinusListNode();
		ListNode n_um3 = new UnaryMinusListNode();
		ListNode n_a1 = new AdditionListNode();
		ListNode n_s1 = new SubtractionListNode();
		ListNode n_m1 = new MultiplicationListNode();
		ListNode n_d1 = new DivisionListNode();
		
		n_d1.setNext(n_m1);
		n_m1.setNext(n_a1);
		n_a1.setNext(n1);
		n1.setNext(n_um1);
		n_um1.setNext(n_um2);
		n_um2.setNext(n2_1);
		n2_1.setNext(n3);
		n3.setNext(n_s1);
		n_s1.setNext(n4);
		n4.setNext(n_um3);
		n_um3.setNext(n2_2);
		// expression is / * + 1 ~ ~ 2 3 - 4 ~ 2
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n_d1.accept(v1);
		assertEquals(1.5, v1.getResult(), DELTA);
	}
	
	

}

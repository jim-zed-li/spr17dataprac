package project2;
import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.InverseExpression;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;

public class ExpressionEvaluator implements ExpressionVisitor{
	private boolean result;
	private int leftVal;
	private int rightVal;
	private String isColumnName;
	private long isLongValue;
	Column[] schema;
	Tuple tuple;
	public ExpressionEvaluator(Column[] schema, Tuple tuple){
//		System.out.println("expression eval constructor");
		this.schema = schema;
		this.tuple = tuple;
		leftVal = 0;
		rightVal = 0;
		isColumnName = "";
		isLongValue = 0;
		result = true;
		
//		System.out.println("items in ");
//		for(Column col: schema){
//			System.out.print(" " + col.getColumnName());
//			
//		}
//		
//		System.out.println("\n" +tuple);
	}
		
	public boolean result(){
		return result;
	}
	@Override
	public void visit(LongValue longValue) {
		isLongValue = longValue.toLong();
	}

	/**
	 * TODO:
	 */
	@Override
	public void visit(AndExpression andExpression) {
		Expression leftExp = andExpression.getLeftExpression();
		Expression rightExp = andExpression.getRightExpression();
		
		leftExp.accept(this);
		rightExp.accept(this);		
	}
	
	/**
	 * TODO:
	 */
	@Override
	public void visit(EqualsTo equalsTo) {
		// TODO Auto-generated method stub
		Expression leftExp = equalsTo.getLeftExpression();
		Expression rightExp = equalsTo.getRightExpression();
		
		leftExp.accept(this);
		if(leftExp instanceof Column){
			leftVal = getNumericVal(isColumnName);
		}
		else if(leftExp instanceof LongValue){
			leftVal = (int)isLongValue;
		}
		rightExp.accept(this);
		if(rightExp instanceof Column){
			rightVal = getNumericVal(isColumnName);
		}
		else if(rightExp instanceof LongValue){
			rightVal = (int)isLongValue;
		}
		result = (leftVal == rightVal) && (result == true);
	}

	/**
	 * TODO:
	 */
	@Override
	public void visit(GreaterThan greaterThan) {
		// TODO Auto-generated method stub
		Expression leftExp = greaterThan.getLeftExpression();
		Expression rightExp = greaterThan.getRightExpression();
		
		leftExp.accept(this);
		if(leftExp instanceof Column){
			leftVal = getNumericVal(isColumnName);
		}
		else if(leftExp instanceof LongValue){
			leftVal = (int)isLongValue;
		}
		rightExp.accept(this);
		if(rightExp instanceof Column){
			rightVal = getNumericVal(isColumnName);
		}
		else if(rightExp instanceof LongValue){
			rightVal = (int)isLongValue;
		}
		result = (leftVal > rightVal) && (result == true);
	}

	/**
	 * TODO:
	 */
	@Override
	public void visit(GreaterThanEquals greaterThanEquals) {
		Expression leftExp = greaterThanEquals.getLeftExpression();
		Expression rightExp = greaterThanEquals.getRightExpression();
		
		leftExp.accept(this);
		if(leftExp instanceof Column){
			leftVal = getNumericVal(isColumnName);
		}
		else if(leftExp instanceof LongValue){
			leftVal = (int)isLongValue;
		}
		rightExp.accept(this);
		if(rightExp instanceof Column){
			rightVal = getNumericVal(isColumnName);
		}
		else if(rightExp instanceof LongValue){
			rightVal = (int)isLongValue;
		}
		result = ((leftVal > rightVal) || (leftVal == rightVal)) && (result == true);
	}

	public int getNumericVal(String colName){
//		System.out.println("the column name " + colName);
		int num = 0;
		for(int i = 0; i < schema.length; i++){
			if(schema[i].getColumnName().equals(colName)){
				String str = tuple.getData()[i];
//				System.out.println("the str = " + str);
				num =  Integer.parseInt(str);
				break;
			}
		}
		return num;		
	}
	/**
	 * TODO:
	 */
	@Override
	public void visit(MinorThan minorThan) {
		
		Expression leftExp = minorThan.getLeftExpression();
		Expression rightExp = minorThan.getRightExpression();
		
		leftExp.accept(this);
		if(leftExp instanceof Column){
			leftVal = getNumericVal(isColumnName);
		}
		else if(leftExp instanceof LongValue){
			leftVal = (int)isLongValue;
		}
		rightExp.accept(this);
		if(rightExp instanceof Column){
			rightVal = getNumericVal(isColumnName);
		}
		else if(rightExp instanceof LongValue){
			rightVal = (int)isLongValue;
		}
		result = (leftVal < rightVal) && (result == true);
	}
	
	/**
	 * TODO:
	 */
	@Override
	public void visit(MinorThanEquals minorThanEquals) {
		// TODO Auto-generated method stub
		Expression leftExp = minorThanEquals.getLeftExpression();
		Expression rightExp = minorThanEquals.getRightExpression();
		
		leftExp.accept(this);
		if(leftExp instanceof Column){
			leftVal = getNumericVal(isColumnName);
		}
		else if(leftExp instanceof LongValue){
			leftVal = (int)isLongValue;
		}
		rightExp.accept(this);
		if(rightExp instanceof Column){
			rightVal = getNumericVal(isColumnName);
		}
		else if(rightExp instanceof LongValue){
			rightVal = (int)isLongValue;
		}
		result = ((leftVal < rightVal) || (leftVal == rightVal)) && (result == true);
	}

	/**
	 * TODO:
	 */
	@Override
	public void visit(NotEqualsTo notEqualsTo) {
		// TODO Auto-generated method stub
		Expression leftExp = notEqualsTo.getLeftExpression();
		Expression rightExp = notEqualsTo.getRightExpression();
		
		leftExp.accept(this);
		if(leftExp instanceof Column){
			leftVal = getNumericVal(isColumnName);
		}
		else if(leftExp instanceof LongValue){
			leftVal = (int)isLongValue;
		}
		rightExp.accept(this);
		if(rightExp instanceof Column){
			rightVal = getNumericVal(isColumnName);
		}
		else if(rightExp instanceof LongValue){
			rightVal = (int)isLongValue;
		}
		result = (leftVal != rightVal) && (result == true);		
	}

	/**
	 * TODO:
	 */
	@Override
	public void visit(Column tableColumn) {
		isColumnName = tableColumn.getColumnName();
	}

	@Override
	public void visit(SubSelect subSelect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CaseExpression caseExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(WhenClause whenClause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ExistsExpression existsExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AllComparisonExpression allComparisonExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AnyComparisonExpression anyComparisonExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Concat concat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Matches matches) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BitwiseAnd bitwiseAnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BitwiseOr bitwiseOr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BitwiseXor bitwiseXor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InExpression inExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IsNullExpression isNullExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LikeExpression likeExpression) {
	}
	
	@Override
	public void visit(OrExpression orExpression) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Between between) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DateValue dateValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TimeValue timeValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TimestampValue timestampValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Parenthesis parenthesis) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StringValue stringValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Addition addition) {
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(Division division) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Multiplication multiplication) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Subtraction subtraction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NullValue nullValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Function function) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InverseExpression arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(JdbcParameter jdbcParameter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DoubleValue doubleValue) {
		// TODO Auto-generated method stub
		
	}

}

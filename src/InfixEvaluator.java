import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class InfixEvaluator extends PostfixEvaluator {
	public String convertToPostfix(String exp) throws ArithmeticException{
		StringBuilder post = new StringBuilder();
		Scanner scan = new Scanner(exp);
		Stack<String> stack = new Stack<String>();
		while(scan.hasNext()) {
			String c = scan.next();
			if(isNumeric(c)) {
				post.append(c);
				post.append(" ");
			}
			if(c.equals("(")) {
				stack.push(c);
			}
			else if(c.equals(")")) {
				while(!stack.isEmpty() && !stack.peek().equals("(")) {
					post.append(stack.pop());
					post.append(" ");
				}
				if(stack.isEmpty()) {
					throw new ArithmeticException("Missing (");
				}
				else {
					stack.pop();
				}
			}
			else if(isOperator(c)) {
				while(!stack.isEmpty() && checkPriority(c) <= checkPriority(stack.peek())) {
					post.append(stack.pop());
					post.append(" ");
				}
				stack.push(c);
			}
		}
		
		while(!stack.isEmpty()) {
			if(stack.peek().equals("(")) throw new ArithmeticException("Too many (");
			post.append(stack.pop());
			post.append(" ");
		}
		scan.close();
		String almost = post.toString();
		return almost.substring(0, almost.length()-1);
	}

	@Override
	public int evaluate(String expression) throws ArithmeticException {
		String nums = convertToPostfix(expression);
		return super.evaluate(nums);
	}
}

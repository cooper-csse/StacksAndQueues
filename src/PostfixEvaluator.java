import java.util.EmptyStackException;
import java.util.Stack;

public class PostfixEvaluator extends Evaluator {
	@Override
	public int evaluate(String expression) throws ArithmeticException {
		String[] array = expression.split(" ");
		Stack<Integer> stack = new Stack<>();
		for (String item : array) {
			if (Evaluator.isOperator(item)) {
				int right;
				int left;
				try {
					right = stack.pop();
					left = stack.pop();
				} catch (EmptyStackException e) {
					throw new ArithmeticException("Too few arguments before operand: \"" + item + "\"");
				}
				switch (item) {
					case "+":
						stack.push(left + right);
						break;
					case "-":
						stack.push(left - right);
						break;
					case "*":
						stack.push(left * right);
						break;
					case "/":
						stack.push(left / right);
						break;
					case "^":
						stack.push((int) Math.pow(left, right));
						break;
				}
			} else {
				try {
					stack.add(Integer.parseInt(item));
				} catch (NumberFormatException e) {
					throw new ArithmeticException(e.getLocalizedMessage());
				}
			}
		}
		if (stack.size() != 1)
			throw new ArithmeticException("Too few operands");
		return stack.pop();
	}
}

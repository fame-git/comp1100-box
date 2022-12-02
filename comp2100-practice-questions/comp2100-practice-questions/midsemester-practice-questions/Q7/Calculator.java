public class Calculator {

	public double add(double a, double b) {

		//START YOUR CODE
		Addition addi = new Addition(a,b);
		return addi.evaluate();

		//END YOUR CODE
	}

	public double subtract(double a, double b) {

		//START YOUR CODE
		Subtract sub = new Subtract(a,b);
		return sub.evaluate();

		//END YOUR CODE
	}

	public double multiply(double a, double b) {

		//START YOUR CODE
		Multiplication multi = new Multiplication(a,b);
		return multi.evaluate();

		//END YOUR CODE
	}

	public double divide(double a, double b) {

		//START YOUR CODE
		Division div = new Division(a,b);
		return div.evaluate();

		//END YOUR CODE
	}
}

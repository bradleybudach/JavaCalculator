/*
 * Class name: CS 234(Group Project)
 * Author:Bradly Budach, Pronob Kumar
 * Date: 11/07/2022
 * Problem: 
 * 		- Make a calculator
 * 		- Have the calculator popup in the GUI
 * 		- Calculator should have buttons
 * 		- Calculator should take inputs
 * 		- Use the calculator to calculate values
 * Goals:
 *      - Have the calculator popup in the GUI
 * 		- Calculator should have buttons
 * 		- Calculator should take inputs
 * 		- Use the calculator to calculate values
 * Inputs:
 *      - math problem 
 * Outputs:
 *      - answer of the problem 
 * Required packages:
 * 		- package calculator;
 * 		- import java.util.Stack;
 * Test cases:
 *      - If we put 5+5	the result will show 10
 *      - If we put 5-5 the result will show 0
 *      - If we put 5*5	the result will show 25
 *      - If we put 5/5	the result will show 1
 *      - If we put 5%5	the result will show 0
 *      - If we put 5^5	the result will show 3125
 *      - If we put (5+5)*2	the result will show 20
 * Pseudo code:
 *     Step 1: It's on you
 *     Step 2: 
 *     Step 3: 
 */
package calculator;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Calculator {

	/*
	 * This method implements a Shunting Yard algorithm (with changes made to suit this specific project) to evaluate a string containing a mathematical expression. Credit to Edsger Dijkstra: the inventor of the algorithm.
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public static Double evaluate(String expression) {
		boolean functionFound = false;
		Stack<String> layeredFunctions = new Stack<String>();
		Stack<String[]> outsideFunctions = new Stack<String[]>();
		String[] functions = {"sin", "cos", "tan", "ln", "log", "abs", "sqrt", "asin", "acos", "atan", "root"};
		layeredFunctions.push(expression);
		String outsideFunctionString = ""; 
		
		do {
			String currentExpression = layeredFunctions.peek();
			
			String firstOccuringFunction = "";
			int smallestIndex = currentExpression.length();
			for (String function : functions) {
				int index = currentExpression.indexOf(function);
				if (index >= 0 && index < smallestIndex) {
					smallestIndex = index;
					outsideFunctionString = function;
					firstOccuringFunction = function;
				}
			}
			
			if (!firstOccuringFunction.isEmpty()) {
				
				// Find the positions of the brackets in order to find the inside of the function.
				int startPosition = smallestIndex;
				int endPosition = -1;
				int closingBracketsRequired = 0;
				String functionParamater = ""; // stores parameter of function if one is found
				
				if (currentExpression.charAt(startPosition+outsideFunctionString.length()) == '(' || currentExpression.matches(".*" + outsideFunctionString + ".*\\(.*")) { // if the function has valid syntax either with no paramater or with paramater.
					Pattern pattern = Pattern.compile("(?<=" + outsideFunctionString + ")[^(]+"); // regex that finds any characters after the function but before the (
					Matcher matcher = pattern.matcher(currentExpression);
					if (matcher.find()) {
						String match = matcher.group(0); // stores paramater of the function
						if (currentExpression.indexOf(outsideFunctionString+match)+outsideFunctionString.length() == startPosition+outsideFunctionString.length()) { // ensures the match is in the same spot as the firstOccuringFunction, prevents errors with it matching a paramater with the same name and different paramater that is further in the expression 
							functionParamater = match;
						}
					} 
					
					for (int i = startPosition+outsideFunctionString.length()+functionParamater.length()+1; i < currentExpression.length(); i++) {
						Character currentChar = currentExpression.charAt(i);
						if (currentChar == '(') {
							closingBracketsRequired++;
						} else if (currentChar == ')') {
							if (closingBracketsRequired == 0) {
								endPosition = i;
								break;
							}
							closingBracketsRequired--;
						}
					}
				} else {
					throw new IllegalArgumentException("Invalid Function");
				}
				
				if (endPosition >= 0) { // if ending parenthesis is found
					String[] outsideFunctionData = {outsideFunctionString, functionParamater, Integer.toString(startPosition), Integer.toString(endPosition)};
					String evalString = currentExpression.substring(startPosition+firstOccuringFunction.length()+functionParamater.length()+1, endPosition);
					functionFound = true;
					layeredFunctions.push(evalString);
					outsideFunctions.push(outsideFunctionData);
				} else {
					throw new IllegalArgumentException("Function Missing Parenthesis");
				}
			} else {
				if (layeredFunctions.size() > 1) {
					String[] outsideFunctionData = outsideFunctions.pop();
					Double answer = solveFunction(outsideFunctionData[0], outsideFunctionData[1], solveExpression(layeredFunctions.pop())); // solves the expression inside the function and the function itself
					String outsideExpression = layeredFunctions.pop();
					outsideExpression = outsideExpression.substring(0, Integer.parseInt(outsideFunctionData[2])) + "(" + answer + ")" + outsideExpression.substring(Integer.parseInt(outsideFunctionData[3])+1);
					layeredFunctions.push(outsideExpression);
				} else {
					functionFound = false;
				}
			}
		} while (functionFound);
		
		return solveExpression(layeredFunctions.pop()); // solves the last layer, after all functions have been performed
	}
	
	public static Double solveFunction(String function, String paramater, Double insideValue) {
		// Replace constants with their values:
		paramater = paramater.replaceAll("e", Double.toString(Math.E));
		paramater = paramater.replaceAll("pi", Double.toString(Math.PI ));
		
		if (function.equals("sin")) { // Sine Function
			if (paramater.isEmpty()) { // normal sin
				return (double) Math.round(Math.sin(insideValue) * 1000000000) / 1000000000;
			} else if (paramater.equals("h")) { // hyperbolic sin
				return (double) Math.round(Math.sinh(insideValue) * 1000000000) / 1000000000;
			} else { // invalid paramater
				throw new IllegalArgumentException("Invalid Function Paramater");
			} 
		} 
		
		if (function.equals("cos")) { // Cosine Function
			if (paramater.isEmpty()) { // normal cos
				return (double) Math.round(Math.cos(insideValue) * 1000000000) / 1000000000;
			} else if (paramater.equals("h")) { // hyperbolic cos
				return (double) Math.round(Math.cosh(insideValue) * 1000000000) / 1000000000; 
			} else { // invalid paramater
				throw new IllegalArgumentException("Invalid Function Paramater");
			}
		} 
		
		if (function.equals("tan")) { // Tangent Function
			if (paramater.isEmpty()) { // normal tan
				return (double) Math.round(Math.tan(insideValue) * 1000000000) / 1000000000;
			} else if (paramater.equals("h")) { // hyperbolic tan
				return (double) Math.round(Math.tanh(insideValue) * 1000000000) / 1000000000;
			} else { // invalid paramater
				throw new IllegalArgumentException("Invalid Function Paramater");
			}
		}
		
		if (function.equals("ln")) { // Natural Log Function
			if (paramater.isEmpty()) {
				if (insideValue != 0) {
					return (double) Math.round(Math.log(insideValue) * 1000000000) / 1000000000;
				} else {
					throw new IllegalArgumentException("ln 0 = Undefined");
				}
			} else { // Invalid Paramater
				throw new IllegalArgumentException("ln Function Can't Use Paramaters");
			}
		}
		
		if (function.equals("log")) { // Log Function
			if (paramater.isEmpty() || paramater.equals("10")) { // Log base 10
				if (insideValue != 0) {
					return (double) Math.round(Math.log10(insideValue) * 1000000000) / 1000000000;
				} else {
					throw new IllegalArgumentException("log 0 = Undefined");
				}
			} else { // log with a different base
				try {
					Double base = Double.parseDouble(paramater);
					if (insideValue != 0) {
						if (base > 1) {
							return (double) Math.round((Math.log(insideValue) / Math.log(base)) * 1000000000) / 1000000000;
						} else {
							throw new IllegalArgumentException("log base must be > 1");
						}
					} else {
						throw new IllegalArgumentException("log 0 = Undefined");
					}
				} catch (NumberFormatException e) { // invalid base
					throw new IllegalArgumentException("log Base Must Be a Number");
				}
			}
		}
		
		if (function.equals("abs")) { // Absolute Value Function
			if (paramater.isEmpty()) {
				return Math.abs(insideValue);
			} else { // Invalid Paramater
				throw new IllegalArgumentException("abs Function Can't Use Paramaters");
			}
		}
		
		if (function.equals("sqrt")) { // Square Root Function
			if (paramater.isEmpty()) { 
				return Math.sqrt(insideValue);
			} else { // Invalid Paramater
				throw new IllegalArgumentException("sqrt Function Can't Use Paramaters");
			}
		}
		
		if (function.equals("root")) {
			if (!paramater.isEmpty()) {
				try { // nth root where n = paramater
					Double root = Double.parseDouble(paramater);
					return (double) Math.round(Math.pow(insideValue, (1/root)) * 1000000000) / 1000000000;
				} catch (Exception e) { // root is not a number
					throw new IllegalArgumentException("root Paramater Must Be a Number");
				}
			} else { // no root given
				throw new IllegalArgumentException("root Function Requires a Paramater");
			}
		}
		
		if (function.equals("asin")) { // Arcsin Function
			if (paramater.isEmpty()) {
				return (double) Math.round(Math.asin(insideValue) * 1000000000) / 1000000000;
			} else { // Invalid Paramater
				throw new IllegalArgumentException("asin Function Can't Use Paramaters");
			}
		}
		
		if (function.equals("acos")) { // Arccos Function
			if (paramater.isEmpty()) {
				return (double) Math.round(Math.acos(insideValue) * 1000000000) / 1000000000;
			} else { // Invalid Paramater
				throw new IllegalArgumentException("acos Function Can't Use Paramaters");
			}
		}
		
		if (function.equals("atan")) { // Arctan Function
			if (paramater.isEmpty()) {
				return (double) Math.round(Math.atan(insideValue) * 1000000000) / 1000000000;
			} else { // Invalid Paramater
				throw new IllegalArgumentException("atan Function Can't Use Paramaters");
			}
		}
		
		// If none of the above functions are matched:
		throw new UnsupportedOperationException("Unexpected Error: function argument does not match possible functions");
	}
	
	public static Double solveExpression(String expression) {
		
		// Replace constants with their values:
		expression = expression.replaceAll("e", "(" + Math.E + ")");
		expression = expression.replaceAll("pi", "(" + Math.PI + ")");
		
		expression = expression.replaceAll("(?<![\\d\\)])\\-(?=\\()", "-1"); // If there is a - before a parenthesis and it is not used for subtracting two numbers, turn it into a -1. ex. -(-10) = -1*(-19) or  (-(-(12))) = (-1*(-1*(12)))
		//If parenthesis or numbers are next to eachother, add a * symbol. ex: 2(10) = 2*10. (3)(2) = (3)*(2). (1)3 = (1)*3:
		expression = expression.replaceAll("\\)\\(", ")*("); // if there is a ")(" add a *
		expression = expression.replaceAll("(?<=\\d)\\(", "*("); // if there is a "number(" add a *
		expression = expression.replaceAll("\\)(?=\\d)", ")*"); // if there is a ")number" add a *
		
		char[] tokens = expression.toCharArray(); 	// Converts the expression into an array of characters.
		
		Stack<Double> numbers = new Stack<Double>(); 	// Creates a stack to hold a queue of numbers
		
		Stack<Character> operators = new Stack<Character>(); 	// Creates a stack to hold a queue of numbers
		
		for (int i = 0; i < tokens.length; i++) {
			char currentToken = tokens[i];
			
			if (Character.isDigit(currentToken)) { 		// If the current character is a number. 
				String number = "" + currentToken;
				
				// Checks to see if a number is being subtracted from another number or is a negative number
				if ((i-1) >= 0 && tokens[i-1] == '-') {
					if ((i-1) == 0) { 	// If a - sign is the first symbol then it is a negative number not subraction
						number = "-" + number; 		// Prepends the negative sign on a number
						operators.pop(); 		// Removes the - from operators as it is now part of a number
					} else if (!Character.isDigit(tokens[i-2]) && tokens[i-2] != ')') {		 // If the character before the - symbol is not a number or close parenthesis that means the number is negative, otherwise it will stay as subtraction
						number = "-" + number;	 // prepends the negative sign on a number
						operators.pop(); 	// Removes the - from operators as it is now part of a number
					}
				}
				
				
				while ((i+1) < tokens.length && (Character.isDigit(tokens[i+1]) || tokens[i+1] == '.')) { // if the character right after the last one is a number or decimal too, add that to an overall number.
					i++; // increase the i and add that digit to the overall number
					number += tokens[i];
				}
				
				currentToken = tokens[i]; 		// Need to update the current token as i may have changed in the while loop.
				numbers.push(Double.parseDouble(number)); 		// Add the number to the numbers stack.
			} else if (currentToken == '+' || currentToken == '-' || currentToken == '*' || currentToken == '/' || currentToken == '%' || currentToken == '^') { 		// if the current token is a character
				if (currentToken == '-' && i > 0 && (tokens[i-1] == '+' || tokens[i-1] == '-' || tokens[i-1] == '*' || tokens[i-1] == '/' || tokens[i-1] == '%' || tokens[i-1] == '^')) { 		// if there is a - sign next to another sign.
					operators.push(currentToken);
					continue; 		// skips the current loop if there is a negative next to another operator as that means the number is negative.
				}
				
				while (!operators.empty() && hasPrecedence(operators.peek(), currentToken)) {
					numbers.push(getResult(operators.pop(), numbers.pop(), numbers.pop())); 	// Finds the result of the operation on the last two numbers 
				}
				operators.push(currentToken);
			} else if (currentToken == '(') {
				operators.push(currentToken);
			} else if (currentToken == ')') {
				try {
					while (operators.peek() != '(') { 	// completes everything between the parenthesis if there is an expression inside.
						numbers.push(getResult(operators.pop(), numbers.pop(), numbers.pop()));
					}
					
					operators.pop(); // removes the remaining parentheses.
				} catch (Exception e) { // if it doesn't find a closing parenthesis
					throw new IllegalArgumentException("Missing Opening Parenthesis");
				}
			} else { // Invalid Input
				throw new IllegalArgumentException("Invalid Characters in Input");
			}
		}
		
		try {
			while (!operators.empty()) {
				numbers.push(getResult(operators.pop(), numbers.pop(), numbers.pop()));
			}
	
			return numbers.pop();
		} catch (Exception e) { // Invalid Input
			throw new IllegalArgumentException("Invalid Input");
		}
	}

	/*
	 * This method returns true if operationB has greater or equal precedence in the order of operations than operation
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	private static boolean hasPrecedence(char operationA, char operationB) {
		if (operationA == '(' || operationB == '(') {
			return false;
		}
		
		int precedenceA = 0;
		int precedenceB = 0;
		
		// All the if statement afterwards are for the order of operations 
		if (operationA == '+' || operationA == '-') {
			precedenceA = 0;
		} else if (operationA == '/' || operationA == '*' || operationA == '%') {
			precedenceA = 1;
		} else if (operationA == '^') {
			precedenceA = 2;
		}
		
		if (operationB == '+' || operationB == '-') {
			precedenceB = 0;
		} else if (operationB == '/' || operationB == '*' || operationB == '%') {
			precedenceB = 1;
		} else if (operationB == '^') {
			precedenceB = 2;
		}
		
		if (precedenceA >= precedenceB && operationB != '^') { 	// because ^ evaluates from right to left so it shouldn't have precedence. (there could be something like 10^2^3)
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * This method is for selecting math operator
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	private static double getResult(char operator, double b, double a) {
		
		if (operator == '*') {				// Use of math operator "*"
			return a * b;
		} else if (operator == '/') {		// Use of math operator "/"
			if (b != 0) {
				return a / b;
			} else { 		// Error : As any number can't be divide by 0
				throw new ArithmeticException("Can't divide by 0");
			}
		} else if (operator == '%') {		// Use of math operator "%"
			return a % b;
		} else if (operator == '+') {		// Use of math operator "+"
			return a + b;
		} else if (operator == '-') {		// Use of math operator "-"
			return a - b;
		} else if (operator == '^') {		// Use of math operator "^"
			return (double) Math.round(Math.pow(a, b) * 1000000000) / 1000000000;
		} else {
			throw new IllegalArgumentException("Invalid Input");
		}
	}
}

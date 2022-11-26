/* Class name: Calculator
 * Authors: Bradly Budach, Pronob Kumar
 * Date: 11/07/2022
 * Problem: 
 * 		- Create a Scientific Calculator
 * Goals:
 *      - Solve a mathmatatical expression
 *      - Account for correct oder of operations
 *      - Solve functions passed in the expression in the proper order
 *      - Describe any errors encountered
 * Inputs:
 *      - A mathmatical expression
 * Outputs:
 *      - The result of a given expression
 * Required packages:
 * 		- java.util.Stack; java.util.EmptyStackException; java.util.regex.Matcher; java.util.regex.Pattern;
 * Pseudo code:
 *     Step 1: Solve all functions in expression in the correct order
 *     Step 2: Replace all constants with their values
 *     Step 3: Solve the epxression taking order of operations into account
 *     Step 4: Throw any errors that were encountered
 */

package calculator;

import java.util.Stack;
import java.util.EmptyStackException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Calculator {

	/* Evaluates a given expression that may include functions
	 * 
	 * Inputs:
	 * 		- String expression 
     * Outputs: 
     * 		- The result of the expression
     * 
     * Pseudocode:
     *    1: Solve functions from inside out, finds the innermost function to solve first
     *    2: works backwards to solve all functions in the expression	
     *    3: once the expression is free of functions, evaluate it with respect to order of operations
	 */
	public static Double evaluate(String expression) {
		boolean functionFound = false; // tracks if it finds a function in the expression
		Stack<String> expressionLayers = new Stack<String>(); // stores each layer of the expression if there are functions inside of functions
		Stack<String[]> outsideFunctions = new Stack<String[]>(); // stores information about the function outside of the current one if there are layers of functions
		String[] functions = {"sin", "cos", "tan", "ln", "log", "abs", "sqrt", "asin", "acos", "atan", "root"}; // list of functions that the calculator supports
		
		expressionLayers.push(expression); // Adds the full expression to the stack of expressionLayers
		
		do {
			String currentExpression = expressionLayers.peek(); // the current expression becomes the most recent expression in the list of expressionLayers
			
			String firstOccuringFunction = "";
			int smallestIndex = currentExpression.length();
			
			for (String function : functions) { // goes through all the functions
				int index = currentExpression.indexOf(function); // index of the current function
				if (index >= 0 && index < smallestIndex) { // finds the function that occurs first (has the smallest index)
					smallestIndex = index;
					firstOccuringFunction = function;
				}
			}
			
			if (!firstOccuringFunction.isEmpty()) { // if a function was found in the currentExpression
				
				// Finds the positions of the brackets in order to find what value or expression is inside the function:
				int startPosition = smallestIndex; // smallestIndex will the indexOf the function that it found when it gets to this point
				int endPosition = -1; // default end position until it is updated
				int closingBracketsRequired = 0; // how many closing brackets it needs to find for it to know the end position
				
				String functionParamater = ""; // stores parameter of function if one is found
				
				if (currentExpression.charAt(startPosition+firstOccuringFunction.length()) == '(' || currentExpression.matches(".*" + firstOccuringFunction + ".*\\(.*")) { // if the function has valid syntax either with no paramater or with paramater.
					Pattern pattern = Pattern.compile("(?<=" + firstOccuringFunction + ")[^(]+"); // regex that finds any characters after the function but before the (. This is the paramater of the function
					Matcher matcher = pattern.matcher(currentExpression);
					
					if (matcher.find()) { // if there is a paramater
						String match = matcher.group(0); // stores paramater of the function
						
						// ensures the match is in the same spot as the firstOccuringFunction, prevents errors with it matching a paramater with the same name and different paramater that is further in the expression
						if (currentExpression.indexOf(firstOccuringFunction+match)+firstOccuringFunction.length() == startPosition+firstOccuringFunction.length()) {  
							functionParamater = match;
						}
					} 
					
					// Goes from the start position to the end of the string to find where the closing bracket of the function is:
					for (int i = startPosition+firstOccuringFunction.length()+functionParamater.length()+1; i < currentExpression.length(); i++) {
						Character currentChar = currentExpression.charAt(i);
						if (currentChar == '(') { // if it find a opening bracket inside the expression
							closingBracketsRequired++; // add another required closing bracket that it needs to find before it can find the end of the expression
						} else if (currentChar == ')') { // if it finds a closing bracket
							if (closingBracketsRequired == 0) { // if it doesn't need to find any other closing brackets
								endPosition = i; // set the end position to the position of the closing bracket
								break; // exit the loop
							}
							closingBracketsRequired--; // remove a required closing bracket because it found one
						}
					}
				} else { // invalid syntax for the function
					throw new IllegalArgumentException("Invalid Function");
				}
				
				if (endPosition >= 0) { // if ending parenthesis is found
					String[] outsideFunctionData = {firstOccuringFunction, functionParamater, Integer.toString(startPosition), Integer.toString(endPosition)}; // saves the name, paramater, start, and end position of the outside function
					String evalString = currentExpression.substring(startPosition+firstOccuringFunction.length()+functionParamater.length()+1, endPosition); // the expression inside of the function
					functionFound = true; 
					expressionLayers.push(evalString); // adds the inside expression to the list as it is a new layer
					outsideFunctions.push(outsideFunctionData); // stores the outside function data in the list
				} else { // if no end parenthesis is found
					throw new IllegalArgumentException("Function Missing Parenthesis");
				}
			} else { // if it does not find a function in the current expression
				if (expressionLayers.size() > 1) { // if there is an outside function to be solved yet
					String[] outsideFunctionData = outsideFunctions.pop(); // gets information about the outside function
					Double answer = solveFunction(outsideFunctionData[0], outsideFunctionData[1], solveExpression(expressionLayers.pop())); // solves the expression inside the function and the function itself
					String parentExpression = expressionLayers.pop(); // gets the full expression that the function was in
					parentExpression = parentExpression.substring(0, Integer.parseInt(outsideFunctionData[2])) + "(" + answer + ")" + parentExpression.substring(Integer.parseInt(outsideFunctionData[3])+1); // replaces the function string in the parent function with its answer
					expressionLayers.push(parentExpression); // adds the updated parent expression back to the expressionLayers list
				} else { // if all functions have been solved
					functionFound = false;
				}
			}
		} while (functionFound); // repeats loop if it keeps finding functions that need to be solved
		
		return solveExpression(expressionLayers.pop()); // solves the final expression which no longer has any functions in it
	}
	
	/* Evaluates a given function
	 * 
	 * Inputs:
	 * 		- String function
	 * 		- String paramater : a paramater for the function if there is one
	 * 		- Double insideValue : the number it is performing the function on
     * Outputs: 
     * 		- The result of the function
     * 
     * Pseudocode:
     *    1: checks which function the given one matches
     *    2: each function has a seperate implementation that performs some calculation on the insideValue - sin does sine, log does log base 10, etc
     *    3: if there is a function paramater, check if it is one of the paramaters that works with the function and do the calculation based on that paramater
     *    4: throw any errors if the paramater is incorrect, missing, or there shouldn't be one for the given function
	 */
	public static Double solveFunction(String function, String paramater, Double insideValue) {
		
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
				} else { // ln(0) returns undefined
					throw new IllegalArgumentException("ln(0) = Undefined");
				}
			} else { // Invalid Paramater
				throw new IllegalArgumentException("ln Function Can't Use Paramaters");
			}
		}
		
		if (function.equals("log")) { // Log Function
			if (paramater.isEmpty() || paramater.equals("10")) { // Log base 10
				if (insideValue != 0) {
					return (double) Math.round(Math.log10(insideValue) * 1000000000) / 1000000000;
				} else { // log(0) returns undefined
					throw new IllegalArgumentException("log(0) = Undefined");
				}
			} else { // log with a different base
				try {
					Double base = Double.parseDouble(paramater); // paramater is the base of the log
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
					Double root = Double.parseDouble(paramater); // root = paramater
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
	
	/* This method implements a Shunting Yard algorithm (with numerous changes made to allow for more functionality) to evaluate a string containing a mathematical expression. Credit to Edsger Dijkstra: the inventor of the algorithm.
	 * 
	 * Inputs:
	 * 		- String expression : A mathmatical expression without functions
     * Outputs: 
     * 		- The result of the expression as a double
     * 
     * Pseudocode:
     *     1: replaces any constants with their numerical values
     *     2: sanatizes special cases so the function will solve correctly. Replaces )( with )*(, number() with number*(), etc
     *     3: splits the epxression into a list of tokens
     *     4: traverses the list of tokens and adds operators to the operators stack and numbers to the numbers stack
     *     5: compares operator precidence to find order of operations, and solves the expression with higher order operations happening before lower order operations
     *     6: returns the final result
     *     7: throws possible errors in the calculations
	 */
	public static Double solveExpression(String expression) {
		
		// Expression Sanatization:
		// Replace constants with their values:
		expression = expression.replaceAll("e", "(" + Math.E + ")"); // e
		expression = expression.replaceAll("pi", "(" + Math.PI + ")"); // pi
		
		// Sanatize Expression to work in the algorithm below:
		expression = expression.replaceAll("(?<![\\d\\)])\\-(?=\\()", "-1"); // If there is a - before a parenthesis and it is not used for subtracting two numbers, turn it into a -1. ex. -(-10) = -1*(-19) or  (-(-(12))) = (-1*(-1*(12)))
		expression = expression.replaceAll("\\)\\(", ")*("); // if there is a ")(" add a *
		expression = expression.replaceAll("(?<=\\d)\\(", "*("); // if there is a "number(" add a *
		expression = expression.replaceAll("\\)(?=\\d)", ")*"); // if there is a ")number" add a *
		
		
		char[] tokens = expression.toCharArray(); // Converts the expression into an array of characters.
		
		Stack<Double> numbers = new Stack<Double>(); // Creates a stack to hold a queue of numbers
		
		Stack<Character> operators = new Stack<Character>(); // Creates a stack to hold a queue of operators
		
		for (int i = 0; i < tokens.length; i++) { // iterates over all tokens
			char currentToken = tokens[i]; // character of the current token
			
			if (Character.isDigit(currentToken)) { // If the current character is a number. 
				String number = "" + currentToken; // stores the full number
				
				// Checks to see if a number is being subtracted from another number or is a negative number
				if ((i-1) >= 0 && tokens[i-1] == '-') {
					if ((i-1) == 0) { // If a - sign is the first symbol then it is a negative number not subraction
						number = "-" + number; // Prepends the negative sign on a number
						operators.pop(); // Removes the - from operators as it is now part of a number
					} else if (!Character.isDigit(tokens[i-2]) && tokens[i-2] != ')') {	// If the character before the - symbol is not a number or close parenthesis that means the number is negative, otherwise it will stay as subtraction
						number = "-" + number; // prepends the negative sign on a number
						operators.pop(); // Removes the - from operators as it is now part of a number
					}
				}
				
				while ((i+1) < tokens.length && (Character.isDigit(tokens[i+1]) || tokens[i+1] == '.')) { // if the character right after the last one is a number or decimal too, add that to an overall number.
					i++; // increase the i and add that digit to the overall number
					number += tokens[i];
				}
				
				numbers.push(Double.parseDouble(number)); // Add the number to the numbers stack.
			} else if (currentToken == '+' || currentToken == '-' || currentToken == '*' || currentToken == '/' || currentToken == '%' || currentToken == '^') { // if the current token is an operator
				
				// if there is a - sign with another operator behind it:
				if (currentToken == '-' && i > 0 && (tokens[i-1] == '+' || tokens[i-1] == '-' || tokens[i-1] == '*' || tokens[i-1] == '/' || tokens[i-1] == '%' || tokens[i-1] == '^')) { 
					operators.push(currentToken);
					continue; // skips the current loop if there is a negative next to another operator as that means the number is negative.
				}
				
				// Checks order of operations:
				while (!operators.empty() && hasPrecedence(operators.peek(), currentToken)) { // while their is another operator and the last operator has precedence over the current one
					numbers.push(getResult(operators.pop(), numbers.pop(), numbers.pop())); // Finds the result of the operation on the last two numbers
				}
				
				operators.push(currentToken); // add the operator to the stack
			} else if (currentToken == '(') {
				operators.push(currentToken); // add the operator to the stack
			} else if (currentToken == ')') {
				try {
					while (operators.peek() != '(') { // completes everything between the parenthesis if there is an expression inside.
						numbers.push(getResult(operators.pop(), numbers.pop(), numbers.pop())); 
					}
					
					operators.pop(); // removes the remaining parentheses.
				} catch (Exception e) { // if it doesn't find an opening parenthesis
					throw new IllegalArgumentException("Missing Opening Parenthesis");
				}
			} else { // Invalid Input
				throw new IllegalArgumentException("Invalid Characters in Input");
			}
		}
		
		try {
			while (!operators.empty()) { // completes the remaining expression if there is anything to complete
				numbers.push(getResult(operators.pop(), numbers.pop(), numbers.pop()));
			}
	
			return numbers.pop(); // returns the result after the expression has been solved
		} catch (EmptyStackException e) { // invalid input
			throw new IllegalArgumentException("Invalid Input");
		}
	}

	/* This method compares two operators and returns true if the first one has a greater or equal precedence than the second one
	 * 
	 * Inputs:
	 * 		- Char operationA : first operator
	 * 		- Char operationB : second operator
     * Outputs: 
     * 		- True if A >= B
     * 		- False if A < B
     * 
     * Pseudocode:
     *     1: if one of the operators is an opening parenthesis return false
     *     2: + and - have a precedence of 0
     *     3: /, *, and % have a precedence of 1 
     *     4: ^ has a precedence of 2
     *     5: if A has a greater or equal precedence than B return true
     *     6: if B is a ^ return false becuase ^ evaluates from right to left instead of left to right
     *     7: else return false
	 */
	private static boolean hasPrecedence(char operationA, char operationB) {
		if (operationA == '(' || operationB == '(') { // cannot compare with a (
			return false; 
		}
		
		int precedenceA = 0;
		int precedenceB = 0;
		
		// Precedence for each operator:
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
		
		if (precedenceA >= precedenceB && operationB != '^') { 	// ^ evaluates from right to left so it should always be higher precedence than whatever is before it even if it is another ^ (there could be something like 10^2^3)
			return true;
		} else {
			return false;
		}
	}
	
	/* Performs a math operation on two values
	 * 
	 * Inputs:
	 * 		- char operator
	 * 		- double b : value after operator
	 * 		- double a : value before operator
     * Outputs: 
     * 		- the result of the operation as a double
     * 
     * Pseudocode:
     *     1: checks which operator it is
     *     2: performs that operation on a and b and returns it
     *     3: if it attemps to divide by 0, throw an error
     *     4: if the operator given does not match a supported operator, throw an error
	 */
	private static double getResult(char operator, double b, double a) {
		
		if (operator == '*') { // Use of math operator "*"
			return a * b;
		} else if (operator == '/') { // Use of math operator "/"
			if (b != 0) {
				return a / b;
			} else { // Error : As any number can't be divide by 0
				throw new ArithmeticException("Can't divide by 0");
			}
		} else if (operator == '%') { // Use of math operator "%"
			return a % b;
		} else if (operator == '+') { // Use of math operator "+"
			return a + b;
		} else if (operator == '-') { // Use of math operator "-"
			return a - b;
		} else if (operator == '^') { // Use of math operator "^"
			return (double) Math.round(Math.pow(a, b) * 1000000000) / 1000000000;
		} else { // if the given operator does not match a supported operation
			throw new UnsupportedOperationException("Unexpected Error: operator given does not match possible operators");
		}
	}
}

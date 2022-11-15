
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
	public static Double evaluateExpression(String expression) {
		char[] tokens = expression.toCharArray(); 	// Converts the expression into an array of characters.
		
		Stack<Double> numbers = new Stack<Double>(); 	// Creates a stack to hold a que of numbers
		
		Stack<Character> operators = new Stack<Character>(); 	// Creates a stack to hold a que of numbers
		
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
				
				
				while ((i+1) < tokens.length && (Character.isDigit(tokens[i+1]) || tokens[i+1] == '.')) { 		// if the character right after the last one is a number or decimal too, add that to an overall number.
						// increase the i and add that digit to the overall number
					i++;
					number = number + tokens[i];
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
				if ((i-1) >= 0 && (Character.isDigit(tokens[i-1]) || tokens[i-1] == ')')) { 	// if there is a previous digit and it was a number or parenthesis, add multiplication to the operators. 10(3+5) = 10*(3+5) or (10)(3+5) = 10*(3+5)
					while (!operators.empty() && hasPrecedence(operators.peek(), '*')) { 	// checks order of operations before it adds the *
						numbers.push(getResult(operators.pop(), numbers.pop(), numbers.pop())); 	// Finds the result of the operation on the last two numbers 
					}
					
					operators.push('*'); 	// if parenthesis are next to each other that means multiply
				}
				
				operators.push(currentToken);
			} else if (currentToken == ')') {
				while (operators.peek() != '(') { 	// completes everything between the parenthesis if there is an expression inside.
					numbers.push(getResult(operators.pop(), numbers.pop(), numbers.pop()));
				}
				
				operators.pop(); // removes the remaining parentheses.
			}
//			System.out.println(numbers);
//			System.out.println(operators);
		}
		
		while (!operators.empty()) {
			numbers.push(getResult(operators.pop(), numbers.pop(), numbers.pop()));
//			System.out.println(numbers);
//			System.out.println(operators);
		}
		
		return numbers.pop();
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
				System.out.println("Error: Can not divide number by 0");
				return 0.0;
			}
		} else if (operator == '%') {		// Use of math operator "%"
			return a % b;
		} else if (operator == '+') {		// Use of math operator "+"
			return a + b;
		} else if (operator == '-') {		// Use of math operator "-"
			return a - b;
		} else if (operator == '^') {		// Use of math operator "^"
			return Math.pow(a, b);
		} else {
			System.out.print("Invalid Operator");
			return 0.0; 	// Error
		}
	}
}

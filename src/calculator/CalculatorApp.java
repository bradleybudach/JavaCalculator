package calculator;
import java.util.Scanner;

public class CalculatorApp {

	public String readInputFromConsole() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter epxression: ");
		return in.nextLine();
	}

	public static void main(String[] args) {
		CalculatorApp app = new CalculatorApp();

		while (true) {
			String input = app.readInputFromConsole();
			System.out.println("The answer is: " + Calculator.evaluateExpression(input) + "\n");
		}
	}

}

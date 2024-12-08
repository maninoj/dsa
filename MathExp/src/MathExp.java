import java.util.Stack;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class MathExp {
    private Stack<String> expression = new Stack<>();

    MathExp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the expression in infix order");

        String input = scanner.nextLine();
        while (!isDigitOrNegativeNumber(input) && !input.equals("x") && !input.equals(")") && !input.equals("(")) {
            System.out.println("Infix expression should start with a number or variable. Please enter your input again.");
            input = scanner.nextLine();
        }

        int openParenthesesCount = 0;

        boolean prevIsDigit = false;
        while (!input.equals("END")) {
            if ((isDigitOrNegativeNumber(input) && !prevIsDigit) ||
                    (isOperator(input) && prevIsDigit) ||
                    (input.equals("x") && !prevIsDigit)) {
                expression.push(input);

                System.out.println("Enter the next element or END to exit");
                prevIsDigit = !prevIsDigit;

            } else if (input.equals("(")) {
                expression.push(input);
                openParenthesesCount++;
                System.out.println("Enter the next element or END to exit");
            } else if (input.equals(")")) {
                if (openParenthesesCount > 0) {
                    expression.push(input);
                    openParenthesesCount--;
                } else {
                    System.out.println("No open parentheses to close.");
                }

                System.out.println("Enter the next element or END to exit");
            } else if (isDigitOrNegativeNumber(input) && prevIsDigit) {
                System.out.println("After a number, closing parentheses, or variable, there must be an operator. Please enter the element again or END to exit.");
            } else if (isOperator(input) && !prevIsDigit) {
                System.out.println("After an operator or opening parentheses, there must be a number or variable. Please enter the element again or END to exit.");
            } else {
                System.out.println("Invalid input. Enter END if the expression is finished; otherwise, enter again.");
            }

            input = scanner.nextLine();
        }

        if (!prevIsDigit) {
            System.out.println("There was no operand after the last operator, so it was removed.");
            expression.pop();
        }

        if (openParenthesesCount > 0) {
            for (int i = 0; i < openParenthesesCount; i++) {
                expression.push(")");
            }
        }
        System.out.println("The expression is: " + expression);
    }

    private boolean isDigitOrNegativeNumber(String input) {
        if (input == null || input.isEmpty()) return false;
        if (input.charAt(0) == '-') input = input.substring(1);
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean isOperator(String input) {
        return "+-*/^".contains(input) && input.length() == 1;
    }

    public Stack<String> toPostFix() {
        Stack<String> postfixStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        Stack<String> expressionCopy = new Stack<>();
        expressionCopy.addAll(expression);

        while (!expressionCopy.isEmpty()) {
            String token = expressionCopy.remove(0);
            if (isDigitOrNegativeNumber(token) || token.equals("x")) {
                postfixStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    postfixStack.push(operatorStack.pop());
                }
                operatorStack.pop();
            } else {
                while (!operatorStack.isEmpty() && prec(token) <= prec(operatorStack.peek())) {
                    postfixStack.push(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixStack.push(operatorStack.pop());
        }

        return postfixStack;
    }

    public Stack<String> toPreFix() {
        Stack<String> operatorStack = new Stack<>();
        Stack<String> operandStack = new Stack<>();
        Stack<String> expressionCopy = new Stack<>();
        expressionCopy.addAll(expression);

        while (!expressionCopy.isEmpty()) {
            String token = expressionCopy.pop();

            if (isDigitOrNegativeNumber(token) || token.equals("x")) {
                operandStack.push(token);
            } else if (token.equals(")")) {
                operatorStack.push(token);
            } else if (token.equals("(")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals(")")) {
                    String operator = operatorStack.pop();
                    String operand1 = operandStack.pop();
                    String operand2 = operandStack.pop();
                    String prefix = operator + " " + operand1 + " " + operand2;
                    operandStack.push(prefix);
                }
                operatorStack.pop();
            } else {
                while (!operatorStack.isEmpty() && prec(token) < prec(operatorStack.peek())) {
                    String operator = operatorStack.pop();
                    String operand1 = operandStack.pop();
                    String operand2 = operandStack.pop();
                    String prefix = operator + " " + operand1 + " " + operand2;
                    operandStack.push(prefix);
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            String operator = operatorStack.pop();
            String operand1 = operandStack.pop();
            String operand2 = operandStack.pop();
            String prefix = operator + " " + operand1 + " " + operand2;
            operandStack.push(prefix);
        }

        Stack<String> resultStack = new Stack<>();
        for (String item : operandStack.pop().split(" ")) {
            resultStack.push(item);
        }

        return resultStack;
    }

    public double calculate() {
        Stack<String> postfix = toPreFix();
        Stack<Double> operands = new Stack<>();
        while (!postfix.empty()) {
            String token = postfix.pop();
            if (isDigitOrNegativeNumber(token)) {
                operands.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                double operand1 = operands.pop();
                double operand2 = operands.pop();
                switch (token) {
                    case "+":
                        operands.push(operand1 + operand2);
                        break;
                    case "-":
                        operands.push(operand1 - operand2);
                        break;
                    case "*":
                        operands.push(operand1 * operand2);
                        break;
                    case "/":
                        operands.push(operand1 / operand2);
                        break;
                    case "^":
                        operands.push(Math.pow(operand1, operand2));
                        break;
                    default:
                        break;
                }
            }
        }
        return operands.pop();
    }

    public double calculate(double value) {
        Stack<String> postfix = toPreFix();
        Stack<Double> operands = new Stack<>();
        while (!postfix.empty()) {
            String token = postfix.pop();
            if (isDigitOrNegativeNumber(token)) {
                operands.push(Double.parseDouble(token));
            } else if (token.equals("x")) {
                operands.push(value);
            } else if (isOperator(token)) {
                double operand1 = operands.pop();
                double operand2 = operands.pop();
                switch (token) {
                    case "+":
                        operands.push(operand1 + operand2);
                        break;
                    case "-":
                        operands.push(operand1 - operand2);
                        break;
                    case "*":
                        operands.push(operand1 * operand2);
                        break;
                    case "/":
                        operands.push(operand1 / operand2);
                        break;
                    case "^":
                        operands.push(Math.pow(operand1, operand2));
                        break;
                    default:
                        break;
                }
            }
        }
        return operands.pop();
    }

    void graph(){
        ArrayList<ArrayList<Double>> points = new ArrayList<>();
        for(double i = -50; i <= 50; i = i + 0.01){
            ArrayList<Double> temp = new ArrayList<>();
            temp.add(i);
            temp.add(calculate(i));
            points.add(temp);
        }
        Graph d = new Graph(points);
    }

    public int prec(String c) {
        if (c.equals("^"))
            return 3;
        else if (c.equals("/") || c.equals("*"))
            return 2;
        else if (c.equals("+") || c.equals("-"))
            return 1;
        else
            return -1;
    }
}

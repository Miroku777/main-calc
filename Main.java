import java.util.Scanner;

public class Main {
    public static final String PLUS = "+";
    public static final String SUBTRACTION = "-";
    public static final String MULTIPLICATION = "*";
    public static final String DIVISION = "/";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение в кaвычках: ");
        String expression = scanner.nextLine();
        calc(expression);
    }

    static String calc(String expression) throws Exception {

        String[] splitedExpressionBySpace = expression.split(" ");
        String operation = null;
        for (String s : splitedExpressionBySpace) {
            if (s.equals(PLUS) || s.equals(SUBTRACTION) || s.equals(MULTIPLICATION) || s.equals(DIVISION)) {
                operation = s;
            }
        }
        if (operation == null) {
            throw new Exception();
        }
        String operandFirst = null;
        String operandSecond = null;
        String result = "";
        String regexOperation = null;
        String[] splitedExpressionBySign = null;
        switch (operation) {

            case PLUS: {
                regexOperation = operation.replaceAll("\\+", "\\\\+");
                splitedExpressionBySign = expression.split(regexOperation);
                operandFirst = splitedExpressionBySign[0].trim();
                operandSecond = splitedExpressionBySign[1].trim();
                if (operandFirst.length() > 12 || operandSecond.length() > 12) {
                    throw new Exception();
                }
                operandFirst = operandFirst.substring(1, operandFirst.length() - 1);
                operandSecond = operandSecond.substring(1, operandSecond.length() - 1);
                result = operandFirst + operandSecond;
                break;
            }
            case SUBTRACTION: {
                splitedExpressionBySign = expression.split(" " + operation + " ");
                operandFirst = splitedExpressionBySign[0].trim();
                operandSecond = splitedExpressionBySign[1].trim();
                if (operandFirst.length() > 12 || operandSecond.length() > 12) {
                    throw new Exception();
                }
                operandFirst = operandFirst.substring(1, operandFirst.length() - 1);
                operandSecond = operandSecond.substring(1, operandSecond.length() - 1);
                result = operandFirst.replace(operandSecond, "");
                break;
            }
            case MULTIPLICATION: {
                regexOperation = operation.replaceAll("\\*", "\\\\*");
                splitedExpressionBySign = expression.split(regexOperation);
                operandFirst = splitedExpressionBySign[0].trim();
                operandSecond = splitedExpressionBySign[1].trim();
                if (operandFirst.length() > 12) {
                    throw new Exception();
                }
                operandFirst = operandFirst.substring(1, operandFirst.length() - 1);
                int intValue = Integer.parseInt(operandSecond);
                if (intValue <= 10 && intValue > 0) {
                    for (int i = 0; i < intValue; i++) {
                        result = result + operandFirst;
                    }
                } else {
                    throw new Exception();
                }
                break;
            }
            case DIVISION: {
                splitedExpressionBySign = expression.split(operation);
                operandFirst = splitedExpressionBySign[0].trim();
                operandSecond = splitedExpressionBySign[1].trim();
                if (operandFirst.length() > 12) {
                    throw new Exception();
                }
                operandFirst = operandFirst.substring(1, operandFirst.length() - 1);
                int intValue = Integer.parseInt(operandSecond);
                if (intValue <= 10 && intValue > 0) {
                    int numberLength = operandFirst.length();
                    int newNumberLength = numberLength / intValue;
                    result = operandFirst.substring(0, newNumberLength);
                } else {
                    throw new Exception();
                }
                break;
            }
            default:
                throw new Exception();
        }
        if (result.length() > 40) {
            result = result.substring(0, 39);
            result = result + "...";
        }
        System.out.println("Результат: " + "\"" + result + "\"");
        return operation;
    }
}
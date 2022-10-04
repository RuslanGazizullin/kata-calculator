import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        System.out.println(calc(inputString));
    }

    public static String calc(String input) {
        InputValidation inputValidation = new InputValidation(input);
        inputValidation.checkValidation();
        String[] inputArray = input.split(" ");
        int firstOperand;
        int secondOperand;
        boolean isFirstOperandRoman = isRomanNumerals(inputArray[0]);
        boolean isSecondOperandRoman = isRomanNumerals(inputArray[2]);
        if (!isFirstOperandRoman && !isSecondOperandRoman) {
            firstOperand = Integer.parseInt(inputArray[0]);
            secondOperand = Integer.parseInt(inputArray[2]);
            if (firstOperand > 10 || firstOperand < 1 || secondOperand > 10 || secondOperand < 1) {
                throw new InputValidationException("Числа должны быть в диапазоне от 1 до 10");
            }
            return String.valueOf(findResult(inputArray[1], firstOperand, secondOperand));
        } else if (!isFirstOperandRoman || !isSecondOperandRoman) {
            throw new InputValidationException("Используются одновременно разные системы счисления");
        } else {
            firstOperand = RomanNumerals.valueOf(inputArray[0]).ordinal() + 1;
            secondOperand = RomanNumerals.valueOf(inputArray[2]).ordinal() + 1;
            int result = findResult(inputArray[1], firstOperand, secondOperand);
            if (result < 1) {
                throw new RuntimeException("Результат операций над римскими числами не может быть отрицательный");
            }
            return RomanNumerals.values()[result - 1].name();
        }

    }

    public static int findResult(String operator, int firstOperand, int secondOperand) {
        return switch (operator) {
            case ("+") -> firstOperand + secondOperand;
            case ("-") -> firstOperand - secondOperand;
            case ("*") -> firstOperand * secondOperand;
            default -> firstOperand / secondOperand;
        };
    }

    public static boolean isRomanNumerals(String operand) {
        boolean isOperandRoman = false;
        for (int i = 0; i < RomanNumerals.values().length; i++) {
            if (RomanNumerals.values()[i].name().equals(operand)) {
                isOperandRoman = true;
                break;
            }
        }
        return isOperandRoman;
    }
}
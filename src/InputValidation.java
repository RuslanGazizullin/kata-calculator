class InputValidation {
    String inputString;

    InputValidation(String inputString) {
        this.inputString = inputString;
    }

    void checkValidation() {
        String acceptableValues = "1 2 3 4 5 6 7 8 9 10 I II III IV V VI VII VIII IX X";
        if (inputString.isBlank()) {
            throw new InputValidationException("Строка пустая");
        }
        if (!inputString.contains("+") && !inputString.contains("-") && !inputString.contains("*")
                && !inputString.contains("/")) {
            throw new InputValidationException("Введенная строка не является математической операцией");
        }
        if (!inputString.contains(" + ") && !inputString.contains(" - ") && !inputString.contains(" * ")
                && !inputString.contains(" / ")) {
            throw new InputValidationException("Неверный формат ввода - отсутствуют пробелы или неверный оператор");
        }
        String[] inputArray = inputString.split(" ");
        if (inputArray.length > 3) {
            throw new InputValidationException("Формат математической операции не удовлетворяет заданию -" +
                    " два операнда и один оператор (+, -, /, *)");
        }
        if (!acceptableValues.contains(inputArray[0]) || !acceptableValues.contains(inputArray[2])) {
            throw new InputValidationException("Недопустимые символы операндов или числа больше 10(X)");
        }
    }
}

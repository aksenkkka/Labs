public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        if (numbers.length() == 1) {
            return Integer.parseInt(numbers);
        }

        String delimiter = "[,\n]";
        String[] numberArray = numbers.split(delimiter);

        if (numberArray.length > 2) {
            throw new IllegalArgumentException("Input string should not contain more than two numbers");
        }

        int sum = 0;
        for (String number : numberArray) {
            if (!number.trim().isEmpty()) {
                sum += Integer.parseInt(number);
            }
        }
        return sum;
    }
}
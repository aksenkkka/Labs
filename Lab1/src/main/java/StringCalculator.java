import java.util.ArrayList;
import java.util.List;
public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String splitPattern = "[,\n]";
        int numberListStart = 0;

        if (numbers.startsWith("//")) {
            int delimiterEnd = numbers.indexOf("\n");
            if (delimiterEnd != -1) {
                char optSplitChar = numbers.charAt(2);
                splitPattern = "[,\n" + optSplitChar + "]";
                numberListStart = delimiterEnd + 1;
            }
            else {
                throwInvalidFormat();
            }
        }
        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList<>();
        for (String numberStr : numbers.substring(numberListStart).split(splitPattern, -1)) {
            if (numberStr.isEmpty()) {
                throwInvalidFormat();
            }
            int num = Integer.parseInt(numberStr);
            if (num < 0) {
                negativeNumbers.add(num);
            }
            else if (num <= 1000) {
                sum += num;
            }
        }
        if (!negativeNumbers.isEmpty()) {
            throwNegativeNumbersException(negativeNumbers);
        }
        return sum;
    }
    private void throwInvalidFormat() {
        throw new IllegalArgumentException("Invalid input format.");
    }
    private void throwNegativeNumbersException(List<Integer> negativeNumbers) {
        String message = "Negatives not allowed: " + negativeNumbers.toString();
        throw new IllegalArgumentException(message);
    }
}
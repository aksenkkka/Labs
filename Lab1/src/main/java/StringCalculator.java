import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String splitPattern = "[,\n]";
        int numberListStart = 0;
        if (numbers.startsWith("//")) {
            int delListEnd = numbers.indexOf('\n');
            if (delListEnd < 0) {
                throwInvalidFormat();
            }
            splitPattern = parseDelimiterList(numbers.substring(2, delListEnd));
            numberListStart = delListEnd + 1;
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
    private static String parseDelimiterList(String text) {
        if (text.length() == 1) {
            return "[,\n" + text.charAt(0) + "]";
        }
        if (text.charAt(0) != '[' || text.indexOf(']', 1) == -1) {
            throwInvalidFormat();
        }
        String delimiter = text.substring(1, text.indexOf(']', 1));
        if (delimiter.isEmpty()) {
            throwInvalidFormat();
        }
        return Pattern.quote(delimiter) + "|,|\n";
    }
    private static void throwInvalidFormat () {
        throw new IllegalArgumentException("Invalid input format.");
    }
    private void throwNegativeNumbersException (List < Integer > negativeNumbers) {
        String message = "Negatives not allowed: " + negativeNumbers.toString();
        throw new IllegalArgumentException(message);
    }
}
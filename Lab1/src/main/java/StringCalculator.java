import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
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
        Pattern pattern = Pattern.compile("\\[(.*?)]");
        Matcher matcher = pattern.matcher(text);
        StringBuilder delimiters = new StringBuilder();
        while (matcher.find()) {
            String delimiter = matcher.group(1);
            if (delimiter.isEmpty()) {
                throwInvalidFormat();
            }
            delimiters.append(Pattern.quote(delimiter)).append("|");
        }
        if (delimiters.isEmpty()) {
            throwInvalidFormat();
        }
        delimiters.deleteCharAt(delimiters.length() - 1);
        return delimiters.append("|,|\n").toString();
    }
    private static void throwInvalidFormat () {
        throw new IllegalArgumentException("Invalid input format.");
    }
    private void throwNegativeNumbersException (List < Integer > negativeNumbers) {
        String message = "Negatives not allowed: " + negativeNumbers.toString();
        throw new IllegalArgumentException(message);
    }
}
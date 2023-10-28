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
            } else {
                throwInvalidFormat();
            }
        }

        int sum = 0;

        for (String numberStr : numbers.substring(numberListStart).split(splitPattern, -1)) {
            if (numberStr.isEmpty()) {
                throwInvalidFormat();
            }

            sum += Integer.parseInt(numberStr);
        }

        return sum;
    }

    private void throwInvalidFormat() {
        throw new IllegalArgumentException("Invalid input format.");
    }
}
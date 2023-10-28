public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else if (numbers.contains(",")) {
            String[] numberParts = numbers.split("[,\n]",-1);
            int sum = 0;
            for (String num : numberParts) {
                sum += Integer.parseInt(num);
            }
            return sum;
        } else {
            return Integer.parseInt(numbers);
        }
    }
}
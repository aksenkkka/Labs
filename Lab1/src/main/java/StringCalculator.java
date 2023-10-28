public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            String delimiter = "[,\n]";
            String[] number = numbers.split(delimiter);
            int sum = 0;
            for (String num : number) {
                if (!num.isEmpty()) {
                    sum += Integer.parseInt(num);
                }
            }
            return sum;
        }
    }
}
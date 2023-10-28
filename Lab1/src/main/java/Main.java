import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        System.out.print("Numbers: ");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String numbers = reader.readLine().replace("\\n", "\n");
            int result = new StringCalculator().add(numbers);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
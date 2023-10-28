import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    final StringCalculator calc = new StringCalculator();


    // Крок 1
    @Test
    void ZeroToEmpty() {
        assertEquals(0, calc.add(""));
    }

    @Test
    void NumberForOneNumber() {
        assertEquals(3, calc.add("3"));
    }

    @Test
    void SumOfNumbers() {
        assertEquals(3, calc.add("1,2"));
        assertEquals(3, calc.add("2,1"));
        assertEquals(2, calc.add("1,1"));
    }
    //Крок 2
    @Test
    void SumOfALotNumbers() {
        assertEquals(6, calc.add("1,2,3"));
        assertEquals(6, calc.add("2,1,3"));
        assertEquals(10, calc.add("1,5,4"));
    }
    //Крок 3
    @Test
    void newLine(){
        assertEquals(6, calc.add("1\n2,3"));
        assertEquals(9, calc.add("1\n1,3,2,2"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("1,\n"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("1,\n,3"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("1,2,3\n4,5,"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("1,2,3\n5,3,"));
    }
    //Крок 4
    @Test
    void userDelimiters() {
        assertEquals(6, calc.add("//;\n1;2;3"));
        assertEquals(6, calc.add("//;\n1;2\n3"));
        assertEquals(107, calc.add("//!\n3!4!100"));
        assertEquals(211, calc.add("//!\n5!6\n200"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("//!\n5!6\n200!"));
    }
    //Крок 5
    @Test
    public void addFailOnSingleNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> new StringCalculator().add("-100"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("-1,-2,-3"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("1,2\n-3"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("//;\n-1;2;-3"));
    }
}


import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RunnerTest {

    @Test
    void singleCarRun() {
        ArrayList<String> singleCarRunTestInput = new ArrayList<>();
        singleCarRunTestInput.add("5 5");
        singleCarRunTestInput.add("3 2 E");
        singleCarRunTestInput.add("FF");
        String singleCarRunResult = Runner.singleCarRun(singleCarRunTestInput);
        assertEquals("4 2 E", singleCarRunResult);
    }

    @Test
    void multiCarRun() {
        ArrayList<String> multiCarRunTestInput = new ArrayList<>();
        multiCarRunTestInput.add("5 5");
        multiCarRunTestInput.add("A");
        multiCarRunTestInput.add("0 0 E");
        multiCarRunTestInput.add("FFLFF");
        multiCarRunTestInput.add("B");
        multiCarRunTestInput.add("4 4 S");
        multiCarRunTestInput.add("FFRFF");
        String multiCarRunResult = Runner.multiCarRun(multiCarRunTestInput);
        String expectedResult = "A B\n2 2\n5";
        assertEquals(expectedResult, multiCarRunResult);
    }
}
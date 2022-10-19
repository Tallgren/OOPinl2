import org.testng.annotations.Test;
import java.time.LocalDate;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class CustomerTest {

    @Test
    public void checkDateIfMemberTest() {
        LocalDate d1 = LocalDate.parse("2022-08-15");
        LocalDate d2 = LocalDate.parse("2022-07-01");

        assertTrue(d2.isBefore(d1));
        assertFalse(d1.isBefore(d2));
    }
}

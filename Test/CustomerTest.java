import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;

public class CustomerTest {

    Huvudprogram huvudprogramTest = new Huvudprogram();
    LocalDate ld = LocalDate.parse("2022-10-20");
    LocalDate ld2 = LocalDate.parse("2020-10-20");
    Customer customer = new Customer("9805241234", "Tobias Hallgren", ld);
    Customer customer2 = new Customer("9805241234", "Tobias Hallgren", ld2);
    @Test
    public void checkDateIfMemberTest() {
        assertTrue(customer.checkDateIfMember());
        assertFalse(customer2.checkDateIfMember());
    }

    @Test
    public void createCustomerListTest() {
        ArrayList<Customer> customerListTest = new ArrayList<>();
        String testPath = "Test/customersTest.txt";
        customerListTest = huvudprogramTest.createCustomerList(customerListTest,testPath);

        assertEquals(customerListTest.get(0).getName(),("Tobias Hallgren"));
        assertNotEquals(customerListTest.get(0).getName(),("Detta stämmer inte"));
        assertEquals(customerListTest.get(0).getLastTimePayed(), (LocalDate.of(2022, 10, 20)));
        assertNotEquals(customerListTest.get(0).getLastTimePayed(), (LocalDate.of(1998, 05, 23)));

    }

    @Test
    public void saveMemberData() {
        boolean isMember = true;
        Path path = Paths.get("Test/membershipDataTest.txt");
        String expected = "Tobias Hallgren 9805241234 2022-10-20";
        String notExpected = "Det här var inte expected";
        String result = "";

        try(FileWriter fileWriter = new FileWriter(path.toFile());
            BufferedWriter ut = new BufferedWriter(fileWriter)) {
            ut.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        customer.saveMemberData(isMember, path);

        try(Scanner in = new Scanner(new File("Test/membershipDataTest.txt"))) {
            result = in.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(result.equals(expected));
        assertFalse(result.equals(notExpected));
    }
}

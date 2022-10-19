import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class Customer {

    long socSecNumber;
    String name;
    LocalDate ld;

    boolean member;

    public Customer(String socSecNumber, String name, LocalDate ld) {
        this.socSecNumber = Long.parseLong(socSecNumber);
        this.name = name;
        this.ld = ld;
    }

    public boolean checkDateIfMember() {
        LocalDate lastYear = LocalDate.now().minusYears(1);
        if (ld.isBefore(lastYear)) {
            return false;
        }
        else return true;
    }

    public void saveMemberData(Customer c, boolean isMember) throws IOException {
        if (isMember) {
            FileWriter ut = new FileWriter(".idea/membershipData.txt", true);
            ut.write(c.getName() + " " + c.getSocSecNumber() + " " + LocalDate.now() + "\n");
            ut.close();
        }
    }

    public String getName() {
        return name;
    }

    public long getSocSecNumber() {
        return socSecNumber;
    }

    public void printMemberOrNot(boolean isMember) {
         if (isMember) {
             System.out.println("Välkommen till gymmet, du betalade medlemskap senast: " + ld);
         }
         else {
             System.out.println("Du är inte medlem! Din senaste betalning var: " + ld);
         }
    }
}

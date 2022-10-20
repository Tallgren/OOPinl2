import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Customer {
    long socSecNumber;
    String name;
    LocalDate lastTimePayed;

    public Customer(String socSecNumber, String name, LocalDate lastTimePayed) {
        this.socSecNumber = Long.parseLong(socSecNumber);
        this.name = name;
        this.lastTimePayed = lastTimePayed;
    }

    public void ifCustomerExistsInList() {
        boolean isMember = checkDateIfMember();
        Path path = Paths.get(".idea/membershipData.txt");
        saveMemberData(isMember, path);
        printMemberOrNot(isMember);
    }

    public boolean checkDateIfMember() {
        LocalDate lastYear = LocalDate.now().minusYears(1);
        if (lastTimePayed.isBefore(lastYear)) {
            return false;
        }
        else return true;
    }

    public void saveMemberData(boolean isMember, Path path) {
        if (isMember) {
            try(FileWriter fileWriter = new FileWriter(path.toFile(), true);
                BufferedWriter ut = new BufferedWriter(fileWriter)) {
                ut.write(name + " " + socSecNumber + " " + LocalDate.now() + "\n");
            } catch (FileNotFoundException e) {
                System.out.println("Filen kunde inte hittas vid sparning av data");
                e.printStackTrace();
            }
            catch (IOException f) {
                System.out.println("Något gick fel vid sparning av data");
                f.printStackTrace();
            }
        }
    }

    public void printMemberOrNot(boolean isMember) {
        if (isMember) {
            System.out.println("Välkommen till gymmet, du betalade medlemskap senast: " + lastTimePayed);
        }
        else {
            System.out.println("Du är inte medlem! Din senaste betalning var: " + lastTimePayed);
        }
    }

    public String getName() {
        return name;
    }
    public long getSocSecNumber() {
        return socSecNumber;
    }
    public LocalDate getLastTimePayed() {
        return lastTimePayed;
    }
}

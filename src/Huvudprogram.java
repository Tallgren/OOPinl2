import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Huvudprogram {
    ArrayList<Customer> costumers = new ArrayList<>();
    String path = ".idea/customers.txt";

    public ArrayList<Customer> createCustomerList(ArrayList<Customer> customerList, String path) {

        try(Scanner in = new Scanner(new File(path))) {

            String[] firstLine = new String[2];
            LocalDate secondLine = null;

            while (in.hasNextLine()) {
                firstLine = in.nextLine().split(",");
                if (in.hasNextLine()) {
                    secondLine = LocalDate.parse(in.nextLine().trim());
                }
                Customer c = new Customer(firstLine[0], firstLine[1].trim(), secondLine);

                customerList.add(c);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fil hittades inte");
        }
        return customerList;
    }

    public void isInputCustomer() {
        Scanner input = new Scanner(System.in);
        ArrayList<Customer> customerList = createCustomerList(costumers, path);

        while (true) {
            System.out.println("\nAnge Namn eller Personnummer:");
            boolean doesNotExistInList = true;

            //Kör om input är personnummer, kollar om personnummer finns i listan
            if (input.hasNextLong()) {
                long longInput = Long.parseLong(input.nextLine());
                for (Customer element : customerList) {
                    if (longInput == element.getSocSecNumber()) {
                        doesNotExistInList = false;
                        element.ifCustomerExistsInList();
                        break;
                    }
                }
            }
            //Kör om input är string, kollar om namn finns i listan
            else if (input.hasNext()){
                String stringInput = input.nextLine();
                for (Customer element : customerList) {
                    if (stringInput.equals(element.getName())) {
                        element.ifCustomerExistsInList();
                        doesNotExistInList = false;
                        break;
                    }
                }
            }

            if (doesNotExistInList) {
                System.out.println("Du har aldrig varit medlem på gymmet!");
            }
        }
    }

    public static void main(String[] args) {
    Huvudprogram huvudprogram = new Huvudprogram();
    huvudprogram.isInputCustomer();
    }
}
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Huvudprogram {

    public ArrayList<Customer> createCustomerList() throws FileNotFoundException {
        ArrayList<Customer> customerList = new ArrayList<>();
        Scanner in = new Scanner(new File(".idea/customers.txt"));
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
        return customerList;
    }

    public void isInputCustomer() throws IOException {
        Scanner input = new Scanner(System.in);
        ArrayList<Customer> customerList = createCustomerList();
        boolean customer = false;


        while (true) {
            int customerIndex = 0;

            System.out.println("Ange Namn eller Personnummer:");

            //Kör om input är personnummer, returnerar boolean om kund finns i listan
            if (input.hasNextLong()) {
                long longInput = input.nextLong();
                for (Customer element : customerList) {
                    if (longInput == element.getSocSecNumber()) {
                        customer = true;
                        customerIndex = customerList.indexOf(element);
                        break;
                    }
                }
            }
            //Kör om input är namn, returnerar boolean om kund finns i listan
            else if (input.hasNext()) {
                String stringInput = input.nextLine();
                for (Customer element : customerList) {
                    if (stringInput.equals(element.getName())) {
                        customer = true;
                        customerIndex = customerList.indexOf(element);
                        break;
                    }
                }
            }

            //Kör om kunden finns i textfilen
            if (customer) {
                Customer c = customerList.get(customerIndex);
                boolean isMember = c.checkDateIfMember();
                c.saveMemberData(c, isMember);
                c.printMemberOrNot(isMember);
            }
            else {
                System.out.println("Du har aldrig varit medlem hos oss");
            }
        }
    }

    public static void main(String[] args) throws IOException {
    Huvudprogram huvudprogram = new Huvudprogram();
    huvudprogram.isInputCustomer();
    }
}
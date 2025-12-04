import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

enum VisitPurpose {
    BUSINESS,
    TOURISM,
    DELIVERY,
    EDUCATION,
    MEETING,
    OTHER
}

class Validator {

    public static boolean isValidName(String name) {
        return name.matches("^[A-Za-z ]+$");
    }

    public static boolean isValidNumber(String num) {
        return num.matches("^\\d+$");
    }
}

class TicketWriterThread extends Thread {
    private Visitor visitor;
    private String fileName;

    public TicketWriterThread(Visitor visitor, String fileName) {
        this.visitor = visitor;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            System.out.println("Background Thread: Saving ticket...");
            Thread.sleep(1000);

            FileWriter writer = new FileWriter(fileName, true);
            writer.write(visitor.displayInfo());
            writer.close();

            System.out.println("Background Thread: Ticket saved successfully!\n");

        } catch (Exception e) {
            System.out.println("Thread Error: " + e.getMessage());
        }
    }
}

abstract class Person{
    private int id;
    private String name;



    // constructor
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }


    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String displayInfo();

}

// inheritance
class Visitor extends  Person {
    public int visitor_id;
    private VisitPurpose purpose;

    public Visitor(int id, String name, int visitor_id, VisitPurpose purpose) {
        super(id, name);
        this.visitor_id = visitor_id;
        this.purpose = purpose;
    }

    public int getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(int visitor_id) {
        this.visitor_id = visitor_id;
    }

    public VisitPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(VisitPurpose purpose) {
        this.purpose = purpose;
    }

    @Override
    public String displayInfo() {
        return "======== WELCOME TO OUR VISITOR MANAGEMENT SYSTEM (GIRI GIRI WUUUU 💕 WUUUU 🎉🏄) =======\n"
                + "Person ID: " + getId() + "\n"
                + "Name: " + getName() + "\n"
                + "Visitor ID: " + getVisitor_id() + "\n"
                + "Purpose of Visit: " + getPurpose() + "\n\n";
    }

//    String keepInfo = displayInfo();
}

public class Main {
    public static  void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        boolean isBooking = true;

        List<Visitor> visitorList = new ArrayList<>();


        while(isBooking){
            System.out.print("Do you want to book a ticket? (y/n): ");
            char book = scanner.next().charAt(0);
            scanner.nextLine();
        if(book == 'y' || book == 'Y'){
            try{
                String ticket = "tickets.txt";

                System.out.print("Enter person ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter name: ");
                String name = scanner.nextLine();

                if (!Validator.isValidName(name)) {
                    System.out.println("Names must contain letters only (no numbers allowed)!");
                    continue;
                }

                System.out.print("Enter visitor ID: ");
                int visitor_id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("\nChoose purpose of visit:");
                System.out.println("1. BUSINESS");
                System.out.println("2. TOURISM");
                System.out.println("3. DELIVERY");
                System.out.println("4. EDUCATION");
                System.out.println("5. MEETING");
                System.out.println("6. OTHER");

                System.out.print("Enter number: ");
                int purposeChoice = Integer.parseInt(scanner.nextLine());

                VisitPurpose purpose;

                switch (purposeChoice) {
                    case 1 -> purpose = VisitPurpose.BUSINESS;
                    case 2 -> purpose = VisitPurpose.TOURISM;
                    case 3 -> purpose = VisitPurpose.DELIVERY;
                    case 4 -> purpose = VisitPurpose.EDUCATION;
                    case 5 -> purpose = VisitPurpose.MEETING;
                    default -> purpose = VisitPurpose.OTHER;
                }


                Visitor v = new Visitor(id, name, visitor_id, purpose);

                visitorList.add(v);

                TicketWriterThread thread = new TicketWriterThread(v, ticket);
                thread.start();  // start writing in background

                System.out.println("\nMain Program: Your ticket is being processed...\n");

                BufferedReader reader = new BufferedReader(new FileReader(ticket));
                String line;
                while ((line = reader.readLine()) != null){
                    System.out.println(line);
                }
                reader.close();

            } catch (NumberFormatException nfe) {
                System.out.println("Invalid number entered. Please enter digits only!");
            }
            catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        else if (book == 'n' || book == 'N') {
            isBooking = false;
            System.out.println("Thank you for using our app bye chau chau 👋 Giri giri wuuuuuu");
        } else {
            System.out.println("Invalid choice. Enter 'y' or 'n'.");
        }

        }

    }

}

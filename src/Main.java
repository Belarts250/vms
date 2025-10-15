import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;

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
    private String purpose;

    public Visitor(int id, String name,  int visitor_id, String purpose) {
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
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

                System.out.print("Enter visitor ID: ");
                int visitor_id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter purpose of visit: ");
                String purpose = scanner.nextLine();

                Visitor v = new Visitor(id, name, visitor_id, purpose);



                FileWriter writer = new FileWriter(ticket, true);
                writer.write(v.displayInfo());
                writer.close();

                System.out.println("The ticket is saved in tickets.txt!");

                BufferedReader reader = new BufferedReader(new FileReader(ticket));

                String line;
                while ((line = reader.readLine()) != null){
                    System.out.println(line);
                }
                reader.close();

            } catch (Exception e){
                System.out.println("Error: Invalid input! " + e.getMessage());

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

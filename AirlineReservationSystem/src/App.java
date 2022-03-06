import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        boolean exit = false;
        while (exit != true) {// ways of exit

            // system user interaction

            System.out.println(
                    "...................wellcome to the Airline reservation system.........................\n");
            System.out.println(".............Today's journey details are..... ");
            System.out.println();
            ManageJourney journey = new ManageJourney();// display details of journey
            journey.displayJourneySchedule();
            System.out.println(".............Today's Available flight schedule are......");
            System.out.println();
            ScheduleFlight flight = new ScheduleFlight();// display details of avilable flight
            flight.displayFlightSchedule();
            System.out.println(".............Today's Available flight tickets are.......");
            System.out.println();
            ManageTicket ticket = new ManageTicket();// display details vailable flight tickets for the passenger
            ticket.displayAvailabeFlightTickets();

            /** declaring the necessary variable */
            Scanner input = new Scanner(System.in);
            String FirstName = null, LastName = null, Password = null, Email = null, Residence = null,
                    Nationality = null, role = null, Class = null, status = null, from = null, destination = null,
                    rout = null;
            String Sex = null;
            Date Age = null;
            int salary = 0, ticketID = 0, seat_no = 0, journeyId = 0, scheduleId = 0, SeatNumber = 0,
                    TicketId = 0, PhoneNumber = 0, no_of_seat = 0;
            Date DDate = null;
            Time departure = null, arrival = null;
            float cost = 0.0f;
            Passenger customer = new Passenger(FirstName, LastName, Password, Email, Residence, Nationality, Sex, Age,
                    PhoneNumber);
            Admin admin = new Admin(FirstName, LastName, Password, Email, Residence, Nationality, Sex, Age,
                    PhoneNumber,
                    salary, role);
            boolean logout = false;

            // main menu

            System.out.println(
                    "Enter 1 if you are new and want to get reservation for the above schedules please register here: ");
            System.out.println("Enter 2 to login in to your user account: ");
            System.out.println("Enter 3 to login in to Admin Account: ");
            System.out.println("Enter 4 to exit from the system: ");
            int choice;
            choice = input.nextInt();
            switch (choice) {
                // new passenger registratin
                case 1:
                    System.out.println(customer.Register());
                    break;
                case 2:

                    // user menu

                    boolean login = false;
                    login = customer.Login(PhoneNumber, Password);
                    while (logout != true) {
                        if (login == true) {// check weather the customer passowrd and phonenumber exist or not if
                                            // exist
                            // it
                            // returns true
                            while (logout != true) {
                                System.out.println("------Here is your dashbored choose what ever you want---------");
                                int ch;
                                System.out.println("Enter 1 to book ticket: ");
                                System.out.println("Enter 2 to cancel ticket: ");
                                System.out.println("Enter 3 to update your personal info: ");
                                System.out.println("Enter 4 to see your ticket info: ");
                                System.out.println("Enter 5 to see your personal info: ");
                                System.out.println("Enter 6 to logout: ");
                                ch = input.nextInt();
                                switch (ch) {
                                    case 1:
                                        customer.BookflightTicket(PhoneNumber, Password, journeyId,
                                                TicketId,
                                                scheduleId,
                                                Class,
                                                SeatNumber);
                                        break;
                                    case 2:
                                        System.out.println(customer.cancelTicket(PhoneNumber, TicketId));
                                        break;
                                    case 3:
                                        System.out.println(customer.UpdatePersonalInfo(PhoneNumber, Password));
                                        break;
                                    case 4:
                                        customer.ShowMyTicket(PhoneNumber, Password, TicketId);
                                        break;
                                    case 5:
                                        customer.showMyPersonalInfo(PhoneNumber, Password);
                                        break;
                                    case 6:
                                        System.out.println("logged out succssefuly!: ");
                                        logout = true;
                                        break;
                                    default:
                                        System.out.println("incorrect choice please try again");
                                        break;
                                }
                            }
                        } else {
                            System.out.println(
                                    "Please enetr 1 to try again or \n enter 2 to back in to main menu");
                            int loginChoice = input.nextInt();
                            if (loginChoice == 1) {
                                login = customer.Login(PhoneNumber, Password);
                            } else if (loginChoice == 2) {
                                logout = true;
                            }
                        }
                    }
                    break;
                case 3:

                    // Admin menu

                    login = false;
                    login = admin.Login(PhoneNumber, Password);// check weather the admin passowrd and phonenumber
                    while (logout != true) {
                        // exist
                        // or not if exist it returns true
                        if (login == true) {
                            while (logout != true) {
                                System.out.println("Enter 1 to view Flight information: ");
                                System.out.println("Enter 2 to update your information: ");
                                System.out.println("Enter 3 to Manage flight: ");
                                System.out.println("Enter 4 to Manage Journey: ");
                                System.out.println("Enter 5 to Manage Ticket: ");
                                System.out.println("Enter 6 to see your personal info: ");
                                System.out.println("Enter 7 to register an other Admin: ");
                                System.out.println("Enter 8 to logout from your account: ");

                                int Adminchoice = input.nextInt();

                                switch (Adminchoice) {
                                    case 1:
                                        admin.ViewFlightInformation();// display all information about the flight
                                                                      // reservation
                                        break;
                                    case 2:
                                        System.out.println(admin.UpdatePersonalInfo(PhoneNumber, Password));
                                        break;
                                    case 3:
                                        System.out.println("Enter 1 to Add flight schedule: ");
                                        System.out.println("Enter 2 to update flight schedule: ");
                                        System.out.println("Enter 3 to Delete flight schedule: ");
                                        Adminchoice = input.nextInt();
                                        switch (Adminchoice) {
                                            case 1:
                                                flight.AddFlightSchedule(scheduleId, journeyId, DDate, departure,
                                                        arrival,
                                                        no_of_seat);
                                                break;
                                            case 2:
                                                flight.updateFlightScheduld(scheduleId);
                                                break;
                                            case 3:
                                                flight.DeleteFlightSchedul(scheduleId);
                                                break;
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Enter 1 to Add journey schedule: ");
                                        System.out.println("Enter 2 to update journey schedule: ");
                                        System.out.println("Enter 3 to Delete journey schedule: ");
                                        Adminchoice = input.nextInt();
                                        switch (Adminchoice) {
                                            case 1:
                                                journey.Addjourney(journeyId, destination, rout, cost);
                                                break;
                                            case 2:
                                                journey.updatejourney(journeyId);
                                                break;
                                            case 3:
                                                journey.Deleteupdatejourney(journeyId);
                                                break;
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Enter 1 to Add Ticket schedule: ");
                                        System.out.println("Enter 2 to update Ticket schedule: ");
                                        System.out.println("Enter 3 to Delete Ticket schedule: ");
                                        Adminchoice = input.nextInt();
                                        switch (Adminchoice) {
                                            case 1:
                                                System.out.println(ticket.addTicket(ticketID, journeyId, Class, status,
                                                        scheduleId, seat_no));
                                                break;
                                            case 2:
                                                ticket.UpdateTicket(ticketID);
                                                break;
                                            case 3:
                                                ticket.DeleteTicket(ticketID);
                                                break;
                                        }
                                        break;
                                    case 6:
                                        admin.showMyPersonalInfo(PhoneNumber, Password);
                                        break;
                                    case 7:
                                        System.out.println(admin.Register());
                                        break;
                                    case 8:
                                        System.out.println("logged out succssefuly!");
                                        logout = true;
                                        break;
                                }
                            }
                        } else {
                            System.out.println(
                                    "Please enetr 1 to try again or \n enter 2 to back in to main menu");
                            int loginChoice = input.nextInt();
                            if (loginChoice == 1) {
                                login = admin.Login(PhoneNumber, Password);
                            } else if (loginChoice == 2) {
                                logout = true;
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Thank you for choosing us. We miss you! ");
                    exit = true;
                    break;

            }
        }

    }
}

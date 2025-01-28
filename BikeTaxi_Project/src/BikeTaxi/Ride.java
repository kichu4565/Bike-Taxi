package BikeTaxi;
import java.util.ArrayList;
import java.util.Scanner;

class Ride {
    private int rideId;
    private String customerName;
    private String pickupLocation;
    private String dropLocation;
    private double fare;
    private String status; 

    public Ride(int rideId, String customerName, String pickupLocation, String dropLocation, double fare) {
        this.rideId = rideId;
        this.customerName = customerName;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.fare = fare;
        this.status = "Booked";
    }

    public int getRideId() {
        return rideId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public double getFare() {
        return fare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ride ID: " + rideId + "\n" +
               "Customer: " + customerName + "\n" +
               "Pickup: " + pickupLocation + "\n" +
               "Drop: " + dropLocation + "\n" +
               "Fare: $" + fare + "\n" +
               "Status: " + status;
    }
}

class BikeTaxiService {
    private ArrayList<Ride> rides;
    private int nextRideId;

    public BikeTaxiService() {
        rides = new ArrayList<>();
        nextRideId = 1;
    }

    public void bookRide(String customerName, String pickupLocation, String dropLocation, double fare) {
        Ride newRide = new Ride(nextRideId, customerName, pickupLocation, dropLocation, fare);
        rides.add(newRide);
        System.out.println("Ride booked successfully! Ride ID: " + nextRideId);
        nextRideId++;
    }

    public void cancelRide(int rideId) {
        for (Ride ride : rides) {
            if (ride.getRideId() == rideId && ride.getStatus().equals("Booked")) {
                ride.setStatus("Cancelled");
                System.out.println("Ride ID " + rideId + " has been cancelled.");
                return;
            }
        }
        System.out.println("Ride ID not found or cannot be cancelled.");
    }

    public void viewStatus(int rideId) {
        for (Ride ride : rides) {
            if (ride.getRideId() == rideId) {
                System.out.println(ride);
                return;
            }
        }
        System.out.println("Ride ID not found.");
    }

    public void viewHistory(String customerName) {
        boolean found = false;
        for (Ride ride : rides) {
            if (ride.getCustomerName().equalsIgnoreCase(customerName)) {
                System.out.println(ride);
                System.out.println("---------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No ride history found for " + customerName + ".");
        }
    }

    public void viewRidesForRider() {
        for (Ride ride : rides) {
            if (ride.getStatus().equals("Booked")) {
                System.out.println(ride);
                System.out.println("---------------------------");
            }
        }
    }

    public void acceptRide(int rideId) {
        for (Ride ride : rides) {
            if (ride.getRideId() == rideId && ride.getStatus().equals("Booked")) {
                ride.setStatus("Accepted");
                System.out.println("Ride ID " + rideId + " has been accepted.");
                return;
            }
        }
        System.out.println("Ride ID not found or cannot be accepted.");
    }

    public void confirmRide(int rideId) {
        for (Ride ride : rides) {
            if (ride.getRideId() == rideId && ride.getStatus().equals("Accepted")) {
                ride.setStatus("Completed");
                System.out.println("Ride ID " + rideId + " has been completed.");
                return;
            }
        }
        System.out.println("Ride ID not found or cannot be confirmed.");
    }
}

 class BikeTaxiBooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BikeTaxiService bikeTaxiService = new BikeTaxiService();

        while (true) {
            System.out.println("\n--- Bike Taxi Booking System ---");
            System.out.println("1. Customer Login\n 2. Rider Login\n 3. Exit\n Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\n--- Customer Menu ---");
                    System.out.println("1. Book a Ride\n 2. Cancel a Ride\n 3. View Ride Status\n 4. View Ride History\n Enter your choice: ");
                    int customerChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (customerChoice) {
                        case 1:
                            System.out.print("Enter customer name: ");
                            String customerName = scanner.nextLine();
                            System.out.print("Enter pickup location: ");
                            String pickupLocation = scanner.nextLine();
                            System.out.print("Enter drop location: ");
                            String dropLocation = scanner.nextLine();
                            System.out.print("Enter fare: ");
                            double fare = scanner.nextDouble();
                            bikeTaxiService.bookRide(customerName, pickupLocation, dropLocation, fare);
                            break;
                        case 2:
                            System.out.print("Enter Ride ID to cancel: ");
                            int cancelRideId = scanner.nextInt();
                            bikeTaxiService.cancelRide(cancelRideId);
                            break;
                        case 3:
                            System.out.print("Enter Ride ID to view status: ");
                            int statusRideId = scanner.nextInt();
                            bikeTaxiService.viewStatus(statusRideId);
                            break;
                        case 4:
                            System.out.print("Enter your name: ");
                            String historyName = scanner.nextLine();
                            bikeTaxiService.viewHistory(historyName);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Rider Menu ---");
                    System.out.println(" 1. View Rides\n 2. Accept a Ride\n 3. Confirm a Ride\n Enter your choice: ");
                    int riderChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (riderChoice) {
                        case 1:
                            bikeTaxiService.viewRidesForRider();
                            break;
                        case 2:
                            System.out.print("Enter Ride ID to accept: ");
                            int acceptRideId = scanner.nextInt();
                            bikeTaxiService.acceptRide(acceptRideId);
                            break;
                        case 3:
                            System.out.print("Enter Ride ID to confirm: ");
                            int confirmRideId = scanner.nextInt();
                            bikeTaxiService.confirmRide(confirmRideId);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

import java.util.*;

class TicketBookingSystem {
    private final boolean[] seats;

    public TicketBookingSystem(int totalSeats) {
        this.seats = new boolean[totalSeats];
    }

    public synchronized boolean bookSeat(int seatNumber, String customerName) {
        if (seatNumber < 0 || seatNumber >= seats.length) {
            System.out.println("Invalid seat number!");
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customerName + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(customerName + " attempted to book seat " + seatNumber + " but it is already taken.");
            return false;
        }
    }
}

class Customer extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customerName;

    public Customer(TicketBookingSystem system, int seatNumber, String customerName, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5); // 5 seats available
        
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(system, 2, "VIP John", Thread.MAX_PRIORITY));
        customers.add(new Customer(system, 2, "Regular Mike", Thread.MIN_PRIORITY));
        customers.add(new Customer(system, 3, "VIP Alice", Thread.MAX_PRIORITY));
        customers.add(new Customer(system, 3, "Regular Bob", Thread.NORM_PRIORITY));
        customers.add(new Customer(system, 1, "VIP Emma", Thread.MAX_PRIORITY));

        for (Customer customer : customers) {
            customer.start();
        }
    }
}

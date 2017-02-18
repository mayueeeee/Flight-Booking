import java.util.ArrayList;

/**
 * Created by nutno on 18/2/2560.
 */
public class Ticket {
    private static int runningID;
    private int transactionID;
    private String[] name = new String[5];
    private int departure_flight;
    private int departure_day;
    private int return_flight;
    private int return_day;
    private int passenger;
    private Boolean status;

    public Ticket(int departure_day,int departure_flight,int return_day,int return_flight,int passenger) {
        this.transactionID = this.runningID++;
        this.departure_day = departure_day;
        this.departure_flight = departure_flight;
        this.return_day = return_day;
        this.return_flight = return_flight;
        this.passenger = passenger;
        this.status = true;
    }

    public int getTransactionID() {
        return transactionID;
    }
}

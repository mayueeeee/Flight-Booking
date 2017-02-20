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

    public Ticket(int departure_day,int departure_flight,int return_day,int return_flight,int passenger,String[] name) {
        this.transactionID = this.runningID++;
        this.departure_day = departure_day;
        this.departure_flight = departure_flight;
        this.return_day = return_day;
        this.return_flight = return_flight;
        this.passenger = passenger;
        this.status = true;
        this.name = name;
    }

    public String[] getName() {
        return name;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getDeparture_flight() {
        return departure_flight;
    }

    public void setDeparture_flight(int departure_flight) {
        this.departure_flight = departure_flight;
    }

    public void setDeparture_day(int departure_day) {
        this.departure_day = departure_day;
    }

    public void setReturn_flight(int return_flight) {
        this.return_flight = return_flight;
    }

    public void setReturn_day(int return_day) {
        this.return_day = return_day;
    }

    public Boolean getStatus() {
        return status;

    }

    public int getDeparture_day() {
        return departure_day;

    }

    public int getReturn_flight() {
        return return_flight;
    }

    public int getReturn_day() {
        return return_day;
    }

    public int getPassenger() {
        return passenger;
    }
}

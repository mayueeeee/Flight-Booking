/**
 * Created by Sarunyu Chankong on 12/2/2560.
 */
public class Flight {

    private String flight_name;
    private String departure_time;
    private String arrival_time;
    private int unbooking_seat;

    public Flight(String flight_name,String departure_time,String arrival_time){

        this.flight_name = flight_name;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.unbooking_seat = 5;
    }

    public void reserveSeat(int seat){
        this.unbooking_seat = this.unbooking_seat-seat;
    }

    public void unreserveSeat(int seat){
        this.unbooking_seat = this.unbooking_seat+seat;
    }
}

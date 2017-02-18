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

    public boolean canTaken(){
        if(this.unbooking_seat>0){
            return true;
        }
        else
            return false;
    }
    public boolean canTaken(int number){
        if(this.unbooking_seat-number>=0){
            return true;
        }
        else
            return false;
    }
    public int getUnbooking_seat(){
        return this.unbooking_seat;
    }

    public String getFlight_name() {
        return flight_name;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setUnbooking_seat(int unbooking_seat) {
        this.unbooking_seat -= unbooking_seat;
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /* Initialize Arrays of Flight */
    public static Flight[][] bkk_cnx = new Flight[7][7];
    public static Flight[][] cnx_bkk = new Flight[7][7];
    public static ArrayList<Ticket> ticket_data = new ArrayList<Ticket>();
    public static void main(String[] args) {


        for (int i = 0; i < 7; i++) {
            /* Initialize BKK to CNX flight data */
            bkk_cnx[i][0] = new Flight("PG 215","08:05","09:20");
            bkk_cnx[i][1] = new Flight("PG 270","10:00","11:15");
            bkk_cnx[i][2] = new Flight("PG 217","12:30","13:45");
            bkk_cnx[i][3] = new Flight("PG 906","14:35","15:50");
            bkk_cnx[i][4] = new Flight("PG 219","17:00","18:15");
            bkk_cnx[i][5] = new Flight("PG 268","19:00","20:15");
            bkk_cnx[i][6] = new Flight("PG 221","21:35","22:55");
            /* Initialize CNX to BKK flight data */
            cnx_bkk[i][0] = new Flight("PG 222","06:55","08:15");
            cnx_bkk[i][1] = new Flight("PG 216","10:10","11:30");
            cnx_bkk[i][2] = new Flight("PG 224","12:00","13:20");
            cnx_bkk[i][3] = new Flight("PG 907","14:40","16:00");
            cnx_bkk[i][4] = new Flight("PG 226","16:40","18:00");
            cnx_bkk[i][5] = new Flight("PG 220","19:00","20:20");
            cnx_bkk[i][6] = new Flight("PG 228","21:05","22:25");
        }

        Scanner input = new Scanner(System.in);
        int select;
        String destination;
        while (true)
        {
            //Show main menu
            System.out.println("Flight booking System");
            System.out.println("1: Show flights schedule");
            System.out.println("2: Booking a flight");
            System.out.println("3: Edit booking");
            System.out.println("4: Cancel booking");
            System.out.println("5: Exit");
            System.out.print("Enter a choice: ");
            select=input.nextInt();
            //Clear enter from nextInt
            input.nextLine();
            if(select==1){
                System.out.print("Please enter destination: (CNX,BKK) ");
                destination = input.nextLine();
                //System.out.println(destination);
                bkk_cnx[0][0].setUnbooking_seat(0);
                if(destination.equals("CNX")){
                    showFlightData(bkk_cnx);
                }
                else if(destination.equals("BKK")){
                    showFlightData(cnx_bkk);
                }
                else {
                    System.out.println("No data");
                }
            }
            else if(select==2){

                int fdepart=0,freturn=0,dpt_day=0,rtn_day=0,passenger=0;
                while (true){
                    System.out.print("Enter depart Flight: ");
                    fdepart = input.nextInt()-1;
                    //System.out.println("fdpt = "+fdepart);
                    if(fdepart>=0&fdepart<bkk_cnx.length)
                    {
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                while (true){
                    System.out.print("Enter depart day: ");
                    dpt_day = input.nextInt()-1;
                    if(dpt_day>=0&dpt_day<bkk_cnx.length)
                    {
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                while (true){
                    System.out.print("Enter return Flight: ");
                    fdepart = input.nextInt()-1;
                    if(fdepart>=0&fdepart<cnx_bkk.length)
                    {
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                while (true){
                    System.out.print("Enter return day: ");
                    dpt_day = input.nextInt()-1;
                    if(dpt_day>=0&dpt_day<cnx_bkk.length)
                    {
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                while (true){
                    System.out.print("Enter number of passengers: ");
                    passenger = input.nextInt();
                    if(passenger>0&passenger<=5)
                    {
                        break;
                    }
                    else{
                        System.out.println("Passenger per flight Exceed! Please try again.");
                    }
                }
                //cnx_bkk[3][3].setUnbooking_seat(3);

                if(canReserve(dpt_day,fdepart,rtn_day,freturn,passenger)){
                    Ticket flight = new Ticket(dpt_day,fdepart,rtn_day,freturn,passenger);
                    ticket_data.add(flight.getTransactionID(),flight);
                    //System.out.println(ticket_data.get(0).getTransactionID());
                    bkk_cnx[dpt_day][fdepart].setUnbooking_seat(passenger);
                    cnx_bkk[rtn_day][freturn].setUnbooking_seat(passenger);
                    System.out.println("You Ticket ID : "+flight.getTransactionID());
                    System.out.println("bkk cnx ubs: "+bkk_cnx[dpt_day][fdepart].getUnbooking_seat());
                    System.out.println("cnx bkk ubs: "+cnx_bkk[rtn_day][freturn].getUnbooking_seat());
                }
                else{
                    System.out.println("Nooooo");
                }




            }
            else if(select==3){



            }
            else if(select==4){
                //canBooking();

            }
            else if(select==5){
                System.out.println("Goodbye :)");
                break;
            }
            else{
                System.out.printf("Error! Please enter correct choice\n\n");
            }
        }

    }

    public static String getDayAsString(int x){
        switch (x){
            case 0 : return "Sunday";
            case 1 : return "Monday";
            case 2 : return "Tuesday";
            case 3 : return "Wednesday";
            case 4 : return "Thursday";
            case 5 : return "Friday";
            case 6 : return "Saturday";
            default : return "Error!";
        }

    }
    public static void showDay(){
        System.out.println("1.Sunday");
        System.out.println("2.Monday");
        System.out.println("3.Tuesday");
        System.out.println("4.Wednesday");
        System.out.println("5.Thursday");
        System.out.println("6.Friday");
        System.out.println("7.Saturday");
    }
    public static void showFlightData(Flight[][] flight_arr){
        for (int i = 0; i < flight_arr.length; i++) {
            System.out.println("Flight on "+getDayAsString(i));
            for (int j = 0; j < flight_arr[i].length ; j++) {
                //System.out.println("BBBBB");
                if(flight_arr[i][j].canTaken()){
                    System.out.print("Flight: "+flight_arr[i][j].getFlight_name()+"  ");
                    System.out.print("Time: "+flight_arr[i][j].getDeparture_time()+" - "+flight_arr[i][j].getArrival_time()+"  ");
                    System.out.println("Available : "+flight_arr[i][j].getUnbooking_seat());
                }
            }
            System.out.println("");
        }

    }
    public static boolean canReserve(int dpt_day,int dpt_flight,int rtn_day,int rtn_flight,int passenger){
        if(bkk_cnx[dpt_day][dpt_flight].canTaken(passenger)&cnx_bkk[rtn_day][rtn_flight].canTaken(passenger))
            return true;
        else
            return false;
    }
    /*public static boolean makeReserve(int dpt_day,int dpt_flight,int rtn_day,int rtn_flight,int passenger){

    }*/
}

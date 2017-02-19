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
        int select,ticket_id;
        /*Mockup data. Comment this for production*/
        for (int i = 0; i < 7; i++) {
            cnx_bkk[3][i].setUnbooking_seat(5);
        }
        for (int i = 0; i < 7; i++) {
            bkk_cnx[1][i].setUnbooking_seat(5);
        }
        for (int i = 0; i < 7; i++) {
            bkk_cnx[2][i].setUnbooking_seat(5);
        }
        /*for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                bkk_cnx[i][j].setUnbooking_seat(5);
            }
        }*/

        int destination;
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
                while(true){
                    System.out.print("Please enter destination [1.Bangkok(BKK) 2.Chiang Mai(CNX)]: ");
                    destination = input.nextInt();
                    //System.out.println(destination);
                    bkk_cnx[0][0].setUnbooking_seat(0);
                    if(destination==1||destination==2){
                        showFlightTable(destination);
                        break;
                    }
                    else {
                        System.out.println("Error! Please enter correct destination");
                    }
                }
            }
            else if(select==2){
                int fdepart=0,freturn=0,dpt_day=0,rtn_day=0,passenger=0;
                showFlightTable();
                //Enter value to variable
                while (true){
                    System.out.print("Enter depart day: ");
                    dpt_day = input.nextInt()-1;
                    if(dpt_day>=0&dpt_day<bkk_cnx.length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                while (true){
                    System.out.print("Enter depart Flight: ");
                    fdepart = input.nextInt()-1;
                    //System.out.println("fdpt = "+fdepart);
                    if(fdepart>=0&fdepart<bkk_cnx.length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                while (true){
                    System.out.print("Enter return day: ");
                    rtn_day = input.nextInt()-1;
                    if(rtn_day>=0&rtn_day<cnx_bkk.length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                while (true){
                    System.out.print("Enter return Flight: ");
                    freturn = input.nextInt()-1;
                    //System.out.println("fdepart = "+fdepart);
                    if(freturn>=0&freturn<cnx_bkk.length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }

                while (true){
                    System.out.print("Enter number of passengers: ");
                    passenger = input.nextInt();
                    if(passenger>0&passenger<=5){
                        break;
                    }
                    else{
                        System.out.println("Passenger per flight Exceed! Please try again.");
                    }
                }

                //System.out.println(">>>cnx_bkk[3][3].getUnbooking_seat: "+cnx_bkk[3][3].getUnbooking_seat());
                while (true){
                    if(canReserve(dpt_day,fdepart,rtn_day,freturn,passenger)){
                        input.nextLine();
                        String[] name = new String[passenger];
                        System.out.println("Please enter passenger name ");
                        for (int i = 0; i < name.length ; i++) {
                            System.out.print("Passenger "+(i+1)+": ");
                            name[i] = input.nextLine();
                        }
                        Ticket flight = new Ticket(dpt_day,fdepart,rtn_day,freturn,passenger,name);
                        ticket_data.add(flight.getTransactionID(),flight);
                        //System.out.println(ticket_data.get(0).getTransactionID());
                        bkk_cnx[dpt_day][fdepart].setUnbooking_seat(passenger);
                        cnx_bkk[rtn_day][freturn].setUnbooking_seat(passenger);
                        System.out.println("Booking Successful! Your ticket ID is "+flight.getTransactionID());
                        System.out.println("");
                        //System.out.println("You Ticket ID : "+flight.getTransactionID());
                       // System.out.println("bkk cnx ubs: "+bkk_cnx[dpt_day][fdepart].getUnbooking_seat());
                       // System.out.println("cnx bkk ubs: "+cnx_bkk[rtn_day][freturn].getUnbooking_seat());
                        break;
                    }
                    else{
                        System.out.println("Sorry. We can't booking this flight for you");
                        System.out.print("Reason : ");
                        if(!(bkk_cnx[dpt_day][fdepart].canTaken(passenger)&!cnx_bkk[rtn_day][freturn].canTaken(passenger))){
                            System.out.println("Depart & Return flight isn't enough");
                            showDepartFlight(dpt_day);
                            showReturnFlight(rtn_day);
                        }
                        else if(!bkk_cnx[dpt_day][fdepart].canTaken(passenger)){
                            System.out.println("Depart flight isn't enough");
                            showDepartFlight(dpt_day);
                        }
                        else if(!cnx_bkk[rtn_day][freturn].canTaken(passenger)){
                            System.out.println("Return flight isn't enough");
                            showReturnFlight(rtn_day);
                        }
                        break;
                    }
                }






            }
            else if(select==3){

                /*System.out.println("Please Enter ticket ID:");
                ticket_id = input.nextInt();*/

                showDepartFlight(6);





            }
            else if(select==4){
                System.out.print("Please Enter ticket ID:");
                ticket_id = input.nextInt();
                //ticket_data.get(ticket_id).

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
            boolean noflight = true;
            System.out.println("Flight on "+getDayAsString(i));
            for (int j = 0; j < flight_arr[i].length ; j++) {
                if(flight_arr[i][j].canTaken()){
                    System.out.print("Flight: "+flight_arr[i][j].getFlight_name()+"  ");
                    System.out.print("Time: "+flight_arr[i][j].getDeparture_time()+" - "+flight_arr[i][j].getArrival_time()+"  ");
                    System.out.println("Available : "+flight_arr[i][j].getUnbooking_seat());
                    noflight=false;

                }

            }
            if(noflight==true){
                System.out.println("No flight available");
            }
            System.out.println("");
        }

    }
    public static void showFlightTable(){
        for (int i = 0; i < 78; i++) {
            System.out.print(" ");
        }
        System.out.println("Flight form Bangkok To Chiang Mai");
        System.out.print("    ");
        for (int i = 0; i < bkk_cnx.length; i++) {
            //System.out.print("         "+(i+1)+"."+getDayAsString(i).length()+"          ");
            System.out.print(alignCenter((i+1)+"."+getDayAsString(i)));
            System.out.print("     ");
        }
        System.out.println();
        for (int i = 0; i < bkk_cnx.length; i++) {
            System.out.print("["+(i+1)+"]");
            for (int j = 0; j < bkk_cnx[i].length; j++) {
                System.out.print(" "+bkk_cnx[j][i].getFlight_name()+"|"+bkk_cnx[j][i].getDeparture_time()+" - "+bkk_cnx[j][i].getArrival_time()+"("+bkk_cnx[j][i].getUnbooking_seat()+")"+"   ");
                //bkk_cnx[j][i].getFlight_name();
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < 78; i++) {
            System.out.print(" ");
        }
        System.out.println("Flight form Chiang Mai To Bangkok");
        System.out.print("    ");
        for (int i = 0; i < cnx_bkk.length; i++) {
            //System.out.print("         "+(i+1)+"."+getDayAsString(i).length()+"          ");
            System.out.print(alignCenter((i+1)+"."+getDayAsString(i)));
            System.out.print("     ");
        }
        System.out.println();
        for (int i = 0; i < cnx_bkk.length; i++) {
            System.out.print("["+(i+1)+"]");
            for (int j = 0; j < cnx_bkk[i].length; j++) {
                System.out.print(" "+cnx_bkk[j][i].getFlight_name()+"|"+cnx_bkk[j][i].getDeparture_time()+" - "+cnx_bkk[j][i].getArrival_time()+"("+cnx_bkk[j][i].getUnbooking_seat()+")"+"   ");
                //bkk_cnx[j][i].getFlight_name();
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void showFlightTable(int destination){
        if(destination==2){
            for (int i = 0; i < 78; i++) {
                System.out.print(" ");
            }
            System.out.println("Flight form Bangkok To Chiang Mai");
            System.out.print("    ");
            for (int i = 0; i < bkk_cnx.length; i++) {
                //System.out.print("         "+(i+1)+"."+getDayAsString(i).length()+"          ");
                System.out.print(alignCenter((i+1)+"."+getDayAsString(i)));
                System.out.print("     ");
            }
            System.out.println();
            for (int i = 0; i < bkk_cnx.length; i++) {
                System.out.print("["+(i+1)+"]");
                for (int j = 0; j < bkk_cnx[i].length; j++) {
                    System.out.print(" "+bkk_cnx[j][i].getFlight_name()+"|"+bkk_cnx[j][i].getDeparture_time()+" - "+bkk_cnx[j][i].getArrival_time()+"("+bkk_cnx[j][i].getUnbooking_seat()+")"+"   ");
                    //bkk_cnx[j][i].getFlight_name();
                }
                System.out.println();
            }
            System.out.println();
        }
        else if(destination==1){
            for (int i = 0; i < 78; i++) {
                System.out.print(" ");
            }
            System.out.println("Flight form Chiang Mai To Bangkok");
            System.out.print("    ");
            for (int i = 0; i < cnx_bkk.length; i++) {
                //System.out.print("         "+(i+1)+"."+getDayAsString(i).length()+"          ");
                System.out.print(alignCenter((i+1)+"."+getDayAsString(i)));
                System.out.print("     ");
            }
            System.out.println();
            for (int i = 0; i < cnx_bkk.length; i++) {
                System.out.print("["+(i+1)+"]");
                for (int j = 0; j < cnx_bkk[i].length; j++) {
                    System.out.print(" "+cnx_bkk[j][i].getFlight_name()+"|"+cnx_bkk[j][i].getDeparture_time()+" - "+cnx_bkk[j][i].getArrival_time()+"("+cnx_bkk[j][i].getUnbooking_seat()+")"+"   ");
                    //bkk_cnx[j][i].getFlight_name();
                }
                System.out.println();
            }
            System.out.println();
        }

    }
    public static void showDepartFlight(int day){
        //Generate flight status Array
        Boolean[] no_flight = new Boolean[7];
        for (int i = 0; i < bkk_cnx.length ; i++) {
            for (int j = 0; j < bkk_cnx[i].length; j++) {
                if(bkk_cnx[i][j].canTaken()){
                    no_flight[i]=false;
                    break;
                }
                else {
                    no_flight[i] = true;
                }
            }
        }
        int count=0;
        //Check available flight
        for (int i = 0; i < 7; i++) {
            if(no_flight[day]==false){
                System.out.println("You can booking Depart flight on "+getDayAsString(day));
                break;
            }
            else if(i==6){
                System.out.println("No flight can booking at this moment");
                break;
            }
            else if(day==6){
                day=0;
            }
            else{
                day++;
            }
        }
       /* while (true){
            if(no_flight[day]==false){
                System.out.println("Show flight on "+getDayAsString(day));
                break;
            }
            else{
                if(day==6){
                    day=0;
                }
                else if(count==6){
                    System.out.println("No flight can reserve at this moment");
                    break;
                }
                else{
                    day++;
                    count++;
                    //System.out.println(count);
                }
            }
        }*/

    }

    public static void showReturnFlight(int day){
        //Generate flight status Array
        Boolean[] no_flight = new Boolean[7];
        for (int i = 0; i < cnx_bkk.length ; i++) {
            for (int j = 0; j < cnx_bkk[i].length; j++) {
                if(cnx_bkk[i][j].canTaken()){
                    no_flight[i]=false;
                    break;
                }
                else {
                    no_flight[i] = true;
                }
            }
        }
        int count=0;
        //Check available flight
        for (int i = 0; i < 7; i++) {
            if(no_flight[day]==false){
                System.out.println("You can booking return flight on "+getDayAsString(day));
                break;
            }
            else if(i==6){
                System.out.println("No flight can booking at this moment");
                break;
            }
            else if(day==6){
                day=0;
            }
            else{
                day++;
            }
        }
       /* while (true){
            if(no_flight[day]==false){
                System.out.println("Show flight on "+getDayAsString(day));
                break;
            }
            else{
                if(day==6){
                    day=0;
                }
                else if(count==6){
                    System.out.println("No flight can reserve at this moment");
                    break;
                }
                else{
                    day++;
                    count++;
                    //System.out.println(count);
                }
            }
        }*/

    }


    public static boolean canReserve(int dpt_day,int dpt_flight,int rtn_day,int rtn_flight,int passenger){
        //System.out.println(">>>dpt_day = "+dpt_day+" dpt_flight = "+dpt_flight+" rtn_day = "+rtn_day+" rtn_flight ="+rtn_flight);
        if(bkk_cnx[dpt_day][dpt_flight].canTaken(passenger)&cnx_bkk[rtn_day][rtn_flight].canTaken(passenger)){
            return true;
        }
        else
            return false;
    }

    public static String alignCenter(String text){
        int blank=(23-text.length())/2;
        String blankspace="";
        for (int i = 0; i < blank-1; i++) {
            blankspace = blankspace+" ";
        }
        if((23-text.length())%2!=0){
            return " "+blankspace+text+blankspace+" ";
        }
        else{
            return " "+blankspace+text+blankspace;
        }

    }

}

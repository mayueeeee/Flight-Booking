import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /* Initialize Arrays of Flight */
    private static Flight[][] bkk_cnx = new Flight[7][7];
    private static Flight[][] cnx_bkk = new Flight[7][7];
    private static ArrayList<Ticket> ticket_data = new ArrayList<Ticket>();
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
        /* End mockup data */

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
                //System.out.println("bkk_cnx = "+bkk_cnx[dpt_day][fdepart].canTaken(passenger));
                //System.out.println("CNX_BKK  = "+cnx_bkk[rtn_day][freturn].canTaken(passenger));

                //System.out.println(">>>cnx_bkk[3][3].getUnbooking_seat: "+cnx_bkk[3][3].getUnbooking_seat());
                while (true){
                    if(canReserve(dpt_day,fdepart,rtn_day,freturn,passenger)){
                        input.nextLine();
                        String[] name = new String[passenger];
                        //ArrayList<String>
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
                        if(!bkk_cnx[dpt_day][fdepart].canTaken(passenger) & !cnx_bkk[rtn_day][freturn].canTaken(passenger)){
                            System.out.println("Depart & Return flight isn't enough");
                            showDepartFlight(dpt_day);
                            showReturnFlight(rtn_day);
                        }
                        else if(!bkk_cnx[dpt_day][fdepart].canTaken(passenger) & cnx_bkk[rtn_day][freturn].canTaken(passenger)){
                            System.out.println("Depart flight isn't enough");
                            showDepartFlight(dpt_day);
                        }
                        else if(bkk_cnx[dpt_day][fdepart].canTaken(passenger) & !cnx_bkk[rtn_day][freturn].canTaken(passenger)){
                            System.out.println("Return flight isn't enough");
                            showReturnFlight(rtn_day);
                        }
                        break;
                    }
                }






            }
            else if(select==3){
                boolean break_status=false;
                while(true){
                    if(break_status){
                        break;
                    }
                    if(ticket_data.size()==0){
                        System.out.printf("Error! No data in database\n\n");
                        break;
                    }
                    else {
                        System.out.print("Please Enter ticket ID: ");
                        ticket_id = input.nextInt();
                        if(ticket_id>=0&ticket_id<ticket_data.size()){
                            if(!ticket_data.get(ticket_id).getStatus()){
                                System.out.printf("Sorry! This ticket has been already canceled from system\n\n");
                                break;
                            }
                            else{
                                showTicketData(ticket_id);
                                while (true){
                                    System.out.println("Pick a task");
                                    System.out.println("1. Edit departure day\n2. Edit departure flight");
                                    System.out.println("3. Edit return day\n4. Edit return flight\n5. Exit");
                                    System.out.print("Enter a choice: ");
                                    int choice = input.nextInt();
                                    if(choice == 1){

                                    }
                                    else if(choice == 2){

                                    }
                                    else if(choice == 3){

                                    }
                                    else if(choice == 4){

                                    }
                                    else if(choice == 5){
                                        System.out.println();
                                        break_status=true;
                                        break;

                                    }
                                    else{
                                        System.out.println("Please enter correct choice");
                                    }
                                }


                            }
                        }
                        else{
                            System.out.println("Input Error! Please try again.");
                        }
                    }
                }
            }
            else if(select==4){
                //System.out.println(ticket_data.size());
                while(true){
                    if(ticket_data.size()==0){
                        System.out.printf("Error! No data in database\n\n");
                        break;
                    }
                    else {
                        System.out.print("Please Enter ticket ID: ");
                        ticket_id = input.nextInt();
                        if(ticket_id>=0&ticket_id<ticket_data.size()){
                            if(!ticket_data.get(ticket_id).getStatus()){
                                System.out.printf("Sorry! This ticket has been already canceled from system\n\n");
                                break;
                            }
                            else{
                                //System.out.println(ticket_data.get(ticket_id).getName().length);
                                System.out.print("Passenger name : ");
                                for (int i = 0; i < ticket_data.get(ticket_id).getName().length; i++) {
                                    System.out.print(ticket_data.get(ticket_id).getName()[i]);
                                    if(i==ticket_data.get(ticket_id).getName().length-1){
                                        System.out.println();
                                    }
                                    else
                                        System.out.print(", ");
                                }
                                System.out.print("Are you sure to cancel this ticket? [1:Delete,0:Cancel]: ");
                                int choice = input.nextInt();
                                if(choice==1){
                                    deleteTicket(ticket_id);
                                    System.out.println();
                                    break;
                                }
                                else{
                                    System.out.println();
                                    break;
                                }
                            }
                        }
                        else{
                            System.out.println("Input Error! Please try again.");
                        }
                    }
                }
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

    public static void showFlightList(int destination,int day){
        //Destination 1=BKK 2=CNX
        if(destination==1){
            for (int j = 0; j < cnx_bkk[day].length ; j++) {
                if(cnx_bkk[day][j].canTaken()){
                    System.out.print((j+1)+". Flight: "+cnx_bkk[day][j].getFlight_name()+"  ");
                    System.out.print("Time: "+cnx_bkk[day][j].getDeparture_time()+" - "+cnx_bkk[day][j].getArrival_time()+"  ");
                    System.out.println("Available : "+cnx_bkk[day][j].getUnbooking_seat());
                }
            }
        }
        else if(destination==2){
            for (int j = 0; j < bkk_cnx[day].length ; j++) {
                if(bkk_cnx[day][j].canTaken()){
                    System.out.print((j+1)+". Flight: "+bkk_cnx[day][j].getFlight_name()+"  ");
                    System.out.print("Time: "+bkk_cnx[day][j].getDeparture_time()+" - "+bkk_cnx[day][j].getArrival_time()+"  ");
                    System.out.println("Available : "+bkk_cnx[day][j].getUnbooking_seat());
                }
            }
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
                showFlightList(2,day);
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
        System.out.println();
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
                showFlightList(1,day);
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
        System.out.println();
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
    public static void deleteTicket(int ticket_id){
        ticket_data.get(ticket_id).setStatus(false);
        int passenger = ticket_data.get(ticket_id).getPassenger();
        int departure_day = ticket_data.get(ticket_id).getDeparture_day();
        int departure_flight = ticket_data.get(ticket_id).getDeparture_flight();
        int return_day = ticket_data.get(ticket_id).getReturn_day();
        int return_flight = ticket_data.get(ticket_id).getReturn_flight();
        bkk_cnx[departure_day][departure_flight].cancelTicket(passenger);
        cnx_bkk[return_day][return_flight].cancelTicket(passenger);
        System.out.println("Your ticket has been deleted.");
    }

    public static void showTicketData(int ticket_id){
        int departure_day = ticket_data.get(ticket_id).getDeparture_day();
        int departure_flight = ticket_data.get(ticket_id).getDeparture_flight();
        int return_day = ticket_data.get(ticket_id).getReturn_day();
        int return_flight = ticket_data.get(ticket_id).getReturn_flight();

        System.out.print("Passenger name: ");
        for (int i = 0; i < ticket_data.get(ticket_id).getName().length; i++) {
            System.out.print(ticket_data.get(ticket_id).getName()[i]);
            if(i==ticket_data.get(ticket_id).getName().length-1){
                System.out.println();
            }
            else
                System.out.print(", ");
        }
        System.out.println("Departure day: "+getDayAsString(departure_day));
        System.out.println("Departure flight: "+bkk_cnx[departure_day][departure_flight].getFlight_name());
        System.out.println("Return day: "+getDayAsString(return_day));
        System.out.println("Return flight: "+cnx_bkk[return_day][return_flight].getFlight_name());
    }

}

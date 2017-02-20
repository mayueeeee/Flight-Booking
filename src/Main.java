import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /* Initialize Arrays of Flight */
    private static Flight[][] bkk_cnx = new Flight[7][7];
    private static Flight[][] cnx_bkk = new Flight[7][7];
    private static ArrayList<Ticket> ticket_data = new ArrayList<Ticket>();
    private static Scanner input = new Scanner(System.in);
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
                    System.out.print("Enter depart day [1-7]: ");
                    dpt_day = input.nextInt()-1;
                    if(dpt_day>=0&dpt_day<bkk_cnx.length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                while (true){
                    System.out.print("Enter depart Flight [1-7]: ");
                    fdepart = input.nextInt()-1;
                    if(fdepart>=0&fdepart<bkk_cnx.length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                while (true){
                    System.out.print("Enter return day [1-7]: ");
                    rtn_day = input.nextInt()-1;
                    if(rtn_day>=0&rtn_day<cnx_bkk.length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                while (true){
                    System.out.print("Enter return Flight [1-7]: ");
                    freturn = input.nextInt()-1;
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
                        bkk_cnx[dpt_day][fdepart].setUnbooking_seat(passenger);
                        cnx_bkk[rtn_day][freturn].setUnbooking_seat(passenger);
                        System.out.printf("SYSTEM> Booking Successful. Your ticket ID is %04d\n",flight.getTransactionID());
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
                        System.out.printf("SYSTEM> Error! No data in database\n\n");
                        break;
                    }
                    else {
                        System.out.print("Please Enter ticket ID: ");
                        ticket_id = input.nextInt();
                        if(ticket_id>=0&ticket_id<ticket_data.size()){
                            if(!ticket_data.get(ticket_id).getStatus()){
                                System.out.printf("SYSTEM> Sorry! This ticket has been already canceled from system\n\n");
                                break;
                            }
                            else{
                                showTicketData(ticket_id);
                                while (true){
                                    System.out.println("Pick a task");
                                    System.out.println("1. Edit departure\n2. Edit return\n3. Exit");
                                    //System.out.println("3. Edit return day\n4. Edit return flight\n5. Exit");
                                    System.out.print("Enter a choice: ");
                                    int choice = input.nextInt();
                                    //Flush "\n" from nextInt
                                    input.nextLine();
                                    if(choice == 1){
                                        editDeparture(ticket_id);
                                    }
                                    else if(choice == 2){
                                        editReturn(ticket_id);
                                    }
                                    else if(choice == 3){
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
                while(true){
                    if(ticket_data.size()==0){
                        System.out.printf("SYSTEM> Error! No data in database\n\n");
                        break;
                    }
                    else {
                        System.out.print("Please Enter ticket ID: ");
                        ticket_id = input.nextInt();
                        if(ticket_id>=0&ticket_id<ticket_data.size()){
                            if(!ticket_data.get(ticket_id).getStatus()){
                                System.out.printf("SYSTEM> Sorry! This ticket has been already canceled from system\n\n");
                                break;
                            }
                            else{
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
            System.out.print(alignCenter((i+1)+"."+getDayAsString(i)));
            System.out.print("     ");
        }
        System.out.println();
        for (int i = 0; i < bkk_cnx.length; i++) {
            System.out.print("["+(i+1)+"]");
            for (int j = 0; j < bkk_cnx[i].length; j++) {
                System.out.print(" "+bkk_cnx[j][i].getFlight_name()+"|"+bkk_cnx[j][i].getDeparture_time()+" - "+bkk_cnx[j][i].getArrival_time()+"("+bkk_cnx[j][i].getUnbooking_seat()+")"+"   ");
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
            System.out.print(alignCenter((i+1)+"."+getDayAsString(i)));
            System.out.print("     ");
        }
        System.out.println();
        for (int i = 0; i < cnx_bkk.length; i++) {
            System.out.print("["+(i+1)+"]");
            for (int j = 0; j < cnx_bkk[i].length; j++) {
                System.out.print(" "+cnx_bkk[j][i].getFlight_name()+"|"+cnx_bkk[j][i].getDeparture_time()+" - "+cnx_bkk[j][i].getArrival_time()+"("+cnx_bkk[j][i].getUnbooking_seat()+")"+"   ");
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
                System.out.print(alignCenter((i+1)+"."+getDayAsString(i)));
                System.out.print("     ");
            }
            System.out.println();
            for (int i = 0; i < bkk_cnx.length; i++) {
                System.out.print("["+(i+1)+"]");
                for (int j = 0; j < bkk_cnx[i].length; j++) {
                    System.out.print(" "+bkk_cnx[j][i].getFlight_name()+"|"+bkk_cnx[j][i].getDeparture_time()+" - "+bkk_cnx[j][i].getArrival_time()+"("+bkk_cnx[j][i].getUnbooking_seat()+")"+"   ");
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
                System.out.print(alignCenter((i+1)+"."+getDayAsString(i)));
                System.out.print("     ");
            }
            System.out.println();
            for (int i = 0; i < cnx_bkk.length; i++) {
                System.out.print("["+(i+1)+"]");
                for (int j = 0; j < cnx_bkk[i].length; j++) {
                    System.out.print(" "+cnx_bkk[j][i].getFlight_name()+"|"+cnx_bkk[j][i].getDeparture_time()+" - "+cnx_bkk[j][i].getArrival_time()+"("+cnx_bkk[j][i].getUnbooking_seat()+")"+"   ");
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
        System.out.println("SYSTEM> Your ticket has been canceled.");
    }

    public static void showTicketData(int ticket_id){
        int departure_day = ticket_data.get(ticket_id).getDeparture_day();
        int departure_flight = ticket_data.get(ticket_id).getDeparture_flight();
        int return_day = ticket_data.get(ticket_id).getReturn_day();
        int return_flight = ticket_data.get(ticket_id).getReturn_flight();
        System.out.println("Your ticket information");
        System.out.print(" >Passenger name: ");
        for (int i = 0; i < ticket_data.get(ticket_id).getName().length; i++) {
            System.out.print(ticket_data.get(ticket_id).getName()[i]);
            if(i==ticket_data.get(ticket_id).getName().length-1){
                System.out.println();
            }
            else
                System.out.print(", ");
        }
        System.out.println(" >Departure day: "+getDayAsString(departure_day));
        System.out.println(" >Departure flight: "+bkk_cnx[departure_day][departure_flight].getFlight_name());
        System.out.println(" >Return day: "+getDayAsString(return_day));
        System.out.println(" >Return flight: "+cnx_bkk[return_day][return_flight].getFlight_name());
    }

    public static void editDeparture(int ticket_id){
        int departure_day = ticket_data.get(ticket_id).getDeparture_day();
        int departure_flight = ticket_data.get(ticket_id).getDeparture_flight();
        int passenger = ticket_data.get(ticket_id).getPassenger();
        String day,flight;
        int dpt_day,dpt_flight;
        System.out.println("Current departure day: "+ getDayAsString(departure_day));
        System.out.println("Current departure flight: "+ bkk_cnx[departure_day][departure_flight].getFlight_name());
        while (true){
            while (true){
                System.out.print("New departure day(or leave blank if you don't want to change): ");
                day = input.nextLine();
                if(!day.equals("")){
                    dpt_day = Integer.parseInt(day)-1;
                    if(dpt_day>=0&dpt_day<bkk_cnx.length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                else{
                    dpt_day=departure_day;
                    break;
                }
            }
            while (true){
                System.out.print("New departure flight(or leave blank if you don't want to change): ");
                flight = input.nextLine();
                if(!flight.equals("")){
                    dpt_flight = Integer.parseInt(flight)-1;
                    if(dpt_flight>=0&dpt_flight<bkk_cnx[dpt_day].length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                else{
                    dpt_flight = departure_flight;
                    break;
                }
            }
            if(day.equals("")&&flight.equals("")){
                System.out.println("SYSTEM> Nothing change");
                break;
            }
            else{
                if(bkk_cnx[dpt_day][dpt_flight].canTaken()){
                    bkk_cnx[departure_day][departure_flight].cancelTicket(passenger);
                    bkk_cnx[dpt_day][dpt_flight].setUnbooking_seat(passenger);
                    ticket_data.get(ticket_id).setDeparture_day(dpt_day);
                    ticket_data.get(ticket_id).setDeparture_flight(dpt_flight);
                    System.out.println("SYSTEM> Update ticket successful!");
                    break;
                }
                else
                    System.out.println("SYSTEM> Your new flight can't booking. Please try again");
            }
        }
    }

    public static void editReturn(int ticket_id){
        int return_day = ticket_data.get(ticket_id).getReturn_day();
        int return_flight = ticket_data.get(ticket_id).getReturn_flight();
        int passenger = ticket_data.get(ticket_id).getPassenger();
        String day,flight;
        int rtn_day,rtn_flight;
        System.out.println("Current return day: "+ getDayAsString(return_day));
        System.out.println("Current return flight: "+ cnx_bkk[return_day][return_flight].getFlight_name());
        while (true){
            while (true){
                System.out.print("New return day(or leave blank if you don't want to change): ");
                day = input.nextLine();
                if(!day.equals("")){
                    rtn_day = Integer.parseInt(day)-1;
                    if(rtn_day>=0&rtn_day<cnx_bkk.length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                else{
                    rtn_day=return_day;
                    break;
                }
            }
            while (true){
                System.out.print("New return flight(or leave blank if you don't want to change): ");
                flight = input.nextLine();
                if(!flight.equals("")){
                    rtn_flight = Integer.parseInt(flight)-1;
                    if(rtn_flight>=0&rtn_flight<cnx_bkk[rtn_day].length){
                        break;
                    }
                    else{
                        System.out.println("Input Error! Please try again.");
                    }
                }
                else{
                    rtn_flight = return_flight;
                    break;
                }
            }
            if(day.equals("")&&flight.equals("")){
                System.out.println("SYSTEM> Nothing change");
                break;
            }
            else{
                if(cnx_bkk[rtn_day][rtn_flight].canTaken()){
                    cnx_bkk[return_day][return_flight].cancelTicket(passenger);
                    cnx_bkk[rtn_day][rtn_flight].setUnbooking_seat(passenger);
                    ticket_data.get(ticket_id).setReturn_day(rtn_day);
                    ticket_data.get(ticket_id).setReturn_flight(rtn_flight);
                    System.out.println("SYSTEM> Update ticket successful!");
                    break;
                }
                else
                    System.out.println("SYSTEM> Your new flight can't booking. Please try again");
            }
        }
    }

}

import java.util.Scanner;
import java.util.Vector;

public class Assignment5C{
    private static final Scanner Scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Vector<Vector<String>> bankDataBase = new Vector<>();
         
        
        appTitle("Welcome to Smart Banking App");
        printMenu();

        System.out.print("\tEnter an option to continue: ");
        int option = Scanner.nextInt();
        Scanner.nextLine();


        switch(option){
            case 1: bankDataBase=addAcount();break;
            case 2: bankDataBase=deposite(bankDataBase);break;
            case 3: withdraw();break;
            case 4: transfer();break;
            case 5: checkBalance();break;
            case 6: deleteAccount();break;
            case 7: System.out.println("\033[H\033[2J"); System.exit(0);
            default: ;
        }
        System.out.println(bankDataBase);

        

        //final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        //final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

    }
    public static void printMenu(){

        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[32;1m";
        final String COLOR_PINK="\033[35m";
        final String RESET = "\033[0m";

        System.out.println(COLOR_PINK+"[1]. Create New Account"+RESET);
        System.out.println("[2]. Deposite");
        System.out.println(COLOR_PINK+"[3]. Withdraw"+RESET);
        System.out.println("[4]. Transfer");
        System.out.println(COLOR_PINK+"[5]. Check Account Balance"+RESET);
        System.out.println("[6]. Delete Account");
        System.out.println(COLOR_PINK+"[7]. Exit\n"+RESET);
        

    }

    public static void appTitle(String screen){

        final String APP_TITLE = String.format("%s%s%s","\033[34;1m", screen, "\033[0m");
        System.out.println("\033[H\033[2J");
        System.out.println("\t" + APP_TITLE + "\n");
    }

    public static Vector<Vector<String>> addAcount(){
        Vector<Vector<String>> dumy = new Vector<>();
        boolean valid=false;
        String name;
        System.out.println();
        String initialBalance;
        int j=0;
   
        do{
            System.out.println("\033[H\033[2J");
            appTitle("Create New Account");
            String accountNumber =String.format("%s%03d","SDB-",(j+1));
            System.out.println("Account No:"+accountNumber);
            dumy.add(new Vector<String>());
            dumy.get(j).add(accountNumber);
            do{
            valid=false;
            System.out.print("Enter the Name :");
            name = Scanner.nextLine().strip();
            if(name.isBlank()){
                System.out.println("Name can't be empty");
                valid=true;
                continue;
            }
            for(int i=0;i<name.length();i++){
                if(!(Character.isLetter(name.charAt(i)))){
                    System.out.println("Invalid Name");
                    valid=true;
                    break;
                }

            }
            }while(valid);
            //dumy.add(new Vector<String>());
            dumy.get(j).add(name);

    
        
        
            do{
                valid=false;
                System.out.print("Enter Initial Deposit Value :");
                initialBalance = Scanner.nextLine();
                
            
            
                if(Integer.valueOf(initialBalance)<5000){
                    System.out.printf("Insufficient Deposit Amount");
                    valid=true;
                    continue;

                }

            }while(valid);

            //dumy.add(new Vector<String>());
            dumy.get(j).add(initialBalance);

            System.out.println("Name,Account no has been created sucessfully!!! \n");
            System.out.println("Do you want add another(Y/N) =>");
            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) {
                j++;
                continue;
            }
            break;
        }while(true);
        return dumy;
    }
    public static Vector<Vector<String>> deposite(Vector<Vector<String>> dumy2){
        boolean valid;
        int location;
        do{
            valid=true;
            String accountNum = Scanner.nextLine();
            if (accountNum.isEmpty()) {
                valid = false;
                System.out.printf( "ID Can't be empty");
                continue;
            }

            if (!accountNum.startsWith("SDB-") || accountNum.length() != 7) {
                valid = false;
                System.out.printf( "Invalid ID format");
                continue;
            }
            for(int i=0;i<8;i++){
                if(!dumy2.get(i).get(0).equals(accountNum)){
                    System.out.println("Account not found");
                    valid =false;
                }
                else location =i;
            }
        }
        return dumy2;


    }
    public static void withdraw(){}
    public static void transfer(){}
    public static void checkBalance(){}
    public static void deleteAccount(){}
    

    


}
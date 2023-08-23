import java.util.Scanner;
import java.util.Vector;

public class Assignment5C{
    private static final Scanner Scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Vector<Vector<String>> bankDataBase = new Vector<>();
         
        do{
            System.out.println("\033[H\033[2J");
           appTitle("Welcome to Smart Banking App");
        printMenu();

        System.out.print("\tEnter an option to continue: ");
        int option = Scanner.nextInt();
        Scanner.nextLine();


        switch(option){
            case 1: bankDataBase=addAcount();break;
            case 2: bankDataBase=deposite(bankDataBase);break;
            case 3: bankDataBase=withdraw(bankDataBase);break;
            case 4: bankDataBase=transfer(bankDataBase);break;
            case 5: checkBalance(bankDataBase);break;
            case 6: bankDataBase=deleteAccount(bankDataBase);break;
            case 7: System.out.println("\033[H\033[2J"); System.exit(0);
            default: ;
        } 
        }while(true);
    }
    //Error massege
    public static String ERROR_MSG(){
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0m";
        final String error_msg = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        return error_msg;
    }
    //Success massege
    public static String SUCCESS_MSG(){
        final String COLOR_GREEN_BOLD = "\033[32;1m";
        final String RESET = "\033[0m";
        final String success_msg = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);
        return success_msg;
    }

    //manu method
    public static void printMenu(){

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

    //title method
    public static void appTitle(String screen){
        
        final String APP_TITLE = String.format("%s%s%s","\033[34;1m", screen, "\033[0m");
        System.out.println("\033[H\033[2J");
        System.out.println("\t" + APP_TITLE + "\n");
    }

    //create bank account
    public static Vector<Vector<String>> addAcount(){
        Vector<Vector<String>> dumy = new Vector<>();
        boolean valid=false;
        String name;
        String initialBalance;
        int j=0;
   
        do{
            //create account number
            appTitle("Create New Account");
            String accountNumber =String.format("%s%03d","SDB-",(j+1));
            System.out.println("Account No:"+accountNumber);
            dumy.add(new Vector<String>());
            dumy.get(j).add(accountNumber);
            
            //name input
            do{
            valid=false;
            System.out.print("Enter the Name :");
            name = Scanner.nextLine().strip();
            if(name.isBlank()){
                System.out.printf(ERROR_MSG(),"Name can't be empty");
                valid=true;
                continue;
            }
            for(int i=0;i<name.length();i++){
                if(!(Character.isLetter(name.charAt(i)))){
                    System.out.printf(ERROR_MSG(),"Invalid Name");
                    valid=true;
                    break;
                }
            }
            }while(valid);
            dumy.get(j).add(name);

            // initial deposit
            do{
                valid=false;
                System.out.print("Enter initial Deposit");
                initialBalance=validBalance();
            
                if(Double.valueOf(initialBalance)<5000){
                    System.out.printf(ERROR_MSG(),"Insufficient Deposit Amount");
                    valid=true;
                    continue;

                }

            }while(valid);

            dumy.get(j).add(initialBalance);

            System.out.printf(SUCCESS_MSG(),"Name,Account no has been created sucessfully!!! \n");
            System.out.print("Do you want add another(Y/N) =>");
            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) {
                j++;
                continue;
            }
            break;
        }while(true);
        return dumy;
    }
    
    //Check the amount validity
    public static String validBalance(){
        boolean valid;
        String amount;
        do{
            int count =0;
            valid=false;
            System.out.print(" Amount :");
            amount = Scanner.nextLine();

            for(int i=0;i<amount.length();i++){
                if(Character.isLetter(amount.charAt(i))){
                    System.out.printf(ERROR_MSG(),"Invalid Entry");
                    System.out.print("Re Enter correct");
                    count =1;
                    break;
                }
            }
            if(count==1){
                valid=true;
                continue;
            }
            if(amount.isBlank()){
                System.out.printf(ERROR_MSG(),"Input can't empty");
                System.out.print("Re Enter correct");
                valid=true;
                continue;
            }

        }while(valid);
        return amount;
    }

    //validate input acount number
    public static Integer validateAccNum(Vector<Vector<String>> dumy2){
        boolean valid;
        int location=0;
        int position=0;
       do{
            valid=true;
            System.out.print("Enter the Acount number :");
            String accountNum = Scanner.nextLine();
            if (accountNum.isEmpty()) {
                valid = false;
                System.out.printf( ERROR_MSG(),"ID Can't be empty");
                continue;
            }

            if (!accountNum.startsWith("SDB-") || accountNum.length() != 7) {
                valid = false;
                System.out.printf(ERROR_MSG(), "Invalid ID format");
                continue;
            }
            for(int i=0;i<7;i++){
                if(dumy2.get(i).get(0).equals(accountNum)){
                    location=i;
                    valid=true;
                    position=1;
                    break;
                } 
                else{
                    valid=false;
                    position=2;
                    
                }

            }
            if(position==2){
                System.out.printf(ERROR_MSG(),"Account number not found");
            }
            
          }while(valid==false); 
          return location;
    }

    //Deposite Function
    public static Vector<Vector<String>> deposite(Vector<Vector<String>> dumy2){
        boolean valid;

        do{ 
          valid=false;  
          appTitle("Deposit"); 
          int location=validateAccNum(dumy2);
          System.out.println("Name :"+dumy2.get(location).get(1));
          System.out.println("Current Balance is:"+dumy2.get(location).get(2));
          System.out.print("Enter the Deposit");
          String deposite=validBalance();
          if(Integer.valueOf(deposite)<500){   //should develop
            System.out.printf(ERROR_MSG(),"Minimum deposite value is 500");
            valid=true;
            continue;

          }
          else{
            String myString = Double.toString((Double.valueOf(dumy2.get(location).get(2)))+(Integer.valueOf(deposite)));
            dumy2.remove(new Vector<String>());
            dumy2.get(location).remove(2);
            dumy2.get(location).add(myString);
            
          }
          System.out.printf(SUCCESS_MSG(),"Deposit Success");
          System.out.println("new Balance is :"+dumy2.get(location).get(2));
          System.out.printf(SUCCESS_MSG(),"Do you want to continue (Y/N) :");
          if (Scanner.nextLine().strip().toUpperCase().equals("Y")) {
                continue;
            }  
         break;
        }while(valid);
        return dumy2;
    }
    //withdrow method
    public static Vector<Vector<String>> withdraw(Vector<Vector<String>> dumy2){
     do{  
          appTitle("Withdraw");
          int location=validateAccNum(dumy2);
          System.out.println("Current Balance is:"+dumy2.get(location).get(2));
          if(Double.valueOf(dumy2.get(location).get(2))<500){
            System.out.println("your account balance insuficient to withdrawals");
            break;
          }
          else{
            System.out.print("Enter the Withdrow ");
            String withdraw = validBalance();
            if(Double.valueOf(withdraw)<100){
               System.out.printf(ERROR_MSG(),"Minimum withdrawal value is 100");
               continue;
            }
            else{
               String myString = Double.toString((Double.valueOf(dumy2.get(location).get(2)))-Double.valueOf(withdraw));
               dumy2.remove(new Vector<String>());
               dumy2.get(location).remove(2);
               dumy2.get(location).add(myString);
            }
            System.out.println("new Balance is :"+dumy2.get(location).get(2));
            System.out.printf(SUCCESS_MSG(),"Do you want to aontinue (Y/N):");
            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) {
                 continue;
            }  
            break;
        }
     
     }while(true);   
     return dumy2;
    }

    public static Vector<Vector<String>> transfer(Vector<Vector<String>> dumy2){
       do{
           appTitle("Transaction"); 
           System.out.print("Transfer from");
           int location1=validateAccNum(dumy2);
           System.out.println("Current Balance is from account:"+dumy2.get(location1).get(2));
           if(Double.valueOf(dumy2.get(location1).get(2))<500){
             System.out.printf(ERROR_MSG(),"your account balance insuficient to Transaction");
             break;
           }
           else{
            System.out.print("Transfer to");
            int location2=validateAccNum(dumy2);
            System.out.println("Current Balance is to account :"+ dumy2.get(location2).get(2));
            
            
            System.out.print("Enter Transfer");
            String transAmount = validBalance();
            if(Double.valueOf(transAmount)<100){
                System.out.printf(ERROR_MSG(),"Insufficient Tansfer Amount(minimun:100)");
                continue ;
            }
            else{
                System.out.println("Transation complete");
                double accNewToBalance =( Double.valueOf(dumy2.get(location1).get(2))-Double.valueOf(transAmount)-(Double.valueOf(transAmount)*0.02));
                dumy2.remove(new Vector<String>());
                dumy2.get(location1).remove(2);
                dumy2.get(location1).add(Double.toString(accNewToBalance));
                System.out.println("New Balance of From Account:"+dumy2.get(location1).get(2));
                
                double accNewFromBalance = (Double.valueOf(dumy2.get(location2).get(2))+Double.valueOf(transAmount));
                dumy2.remove(new Vector<String>());
                dumy2.get(location2).remove(2);
                dumy2.get(location2).add(Double.toString(accNewFromBalance));
                System.out.println("New Balance of to Account:"+dumy2.get(location2).get(2));
                System.out.printf(ERROR_MSG(),"Do you want to aontinue (Y/N):");
                 if (Scanner.nextLine().strip().toUpperCase().equals("Y")) {
                 continue;
                 }  
            break;
            }
           
        }
       } while(true);
       return dumy2;
    }
    public static void checkBalance(Vector<Vector<String>> dumy2){
        do{
           appTitle("Check Account"); 
           int location = validateAccNum(dumy2);
           System.out.println("Account holder name :"+dumy2.get(location).get(1));
           System.out.println("Current Account Balance is:"+dumy2.get(location).get(2));
           System.out.println("Avilable Withdrawal Balance :"+(Double.valueOf(dumy2.get(location).get(2))-500)); 
           System.out.println("Do you want to aontinue (Y/N):");
           if (Scanner.nextLine().strip().toUpperCase().equals("Y")) {
              continue;
           }  
           break;
        }while(true);
        
    }
    public static Vector<Vector<String>> deleteAccount(Vector<Vector<String>> dumy2){
        do{
            appTitle("Delete Account"); 
            int location = validateAccNum(dumy2);
            System.out.println("Name is :"+dumy2.get(location).get(1));
            System.out.println("Acount Balance is :"+dumy2.get(location).get(2)+"\n");
            System.out.printf(SUCCESS_MSG(),"Are sure to Delete (Y/N) :");
            if(Scanner.nextLine().strip().toUpperCase().equals("Y")){
                System.out.println("Acount no:"+dumy2.get(location).get(0));
                System.out.println("Name :"+dumy2.get(location).get(1));
                dumy2.remove(location);
                System.out.printf(SUCCESS_MSG(),"Succesfully Deleted!");
            } 
            else{
                break;
            } 
            System.out.printf(SUCCESS_MSG(),"Are you want to continue (Y/N):");
            if(Scanner.nextLine().strip().toUpperCase().equals("Y")){ 
                continue;
            }
            else{
                break;
            }
        
        }while(true);
        
    return dumy2;
    }
}
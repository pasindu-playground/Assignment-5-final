import java.util.ArrayList;
import java.util.Vector;

public class trial {

    public static void main(String[] args)throws Exception {
        
        Vector<Vector<String>> FLCP = new Vector<>();
        FLCP.add(new Vector<String>());
        FLCP.get(0).add("asasas");
        FLCP.get(0).add("ewwess");

        FLCP.add(new Vector<String>());
        FLCP.get(1).add("gffd");
        FLCP.get(1).add("jkjkj");
       
                

        
    
        System.out.println(FLCP.get(0).get(0));
    
    }
    
}

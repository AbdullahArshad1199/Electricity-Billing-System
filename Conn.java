package electricity.billing.systeme;
import java.sql.*;

public class Conn {

    static String getCustomerNameByMeterNo(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    Connection c;
    Statement s;
   Conn(){
       try{
     c =DriverManager.getConnection("jdbc:mysql:///electricity","root","abdmughal.1199");
     s =c.createStatement();  
       }
       catch(Exception e){ 
            e.printStackTrace();
            System.out.println("error");
        }  
       
   }

    

}

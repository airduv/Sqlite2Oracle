import java.sql.Connection;  
 import java.sql.DriverManager;  
 import java.sql.ResultSet;  
 import java.sql.Statement;  

 public class Main 
 {  
  public static void main(String[] args) 
  {  
     Connection connection = null;  
     ResultSet resultSet = null;  
     Statement statement = null;  

     try 
     {  
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:C:\\MM-Copie.DB");  
         statement = connection.createStatement();  
         resultSet = statement  
                 .executeQuery("SELECT COUNT(*) NB FROM SONGS");  
         while (resultSet.next()) 
         {  
             System.out.println("NB ROW : "+resultSet.getString("NB"));  
         }  
     } 
     catch (Exception e) 
     {  
         e.printStackTrace();  
     }
     finally 
     {  
         try 
         {  
             resultSet.close();  
             statement.close();  
             connection.close();  
         } 
         catch (Exception e) 
         {  
             e.printStackTrace();  
         }  
     }  
 }  
}  
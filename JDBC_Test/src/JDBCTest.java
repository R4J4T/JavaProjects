import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class JDBCTest {

    private final static java.lang.String url = "jdbc:mysql://localhost:3306/JDBCTEST";

    private final static java.lang.String userName = "root";
    private  final static java.lang.String password = "LxFeddGy@@1";

    public static void main(String [] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Class loaded successfully");
        } catch (ClassNotFoundException e){
            System.out.println("class not loaded");
        }
        try{
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection established successfully");
            //insertQuery(connection);
            //selectQuery(connection);
            //updateQuery(connection);
            deleteQuery(connection);
        }catch(SQLException e){
            System.out.println("Connection Failed.....");
        }
    }

   public static void insertQuery(Connection connection){
        try{
            java.lang.String query = "insert into person values(?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,101);
            ps.setString(2,"person1");
            ps.execute();
            System.out.println("Inserted successfully");
        }
        catch(Exception e){
            System.out.println("failed to insert");
        }
    }

    public static void selectQuery(Connection connection){
        try{
            String query = "select * from person";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1) + " : " + rs.getString(2));
            }
        }
        catch(Exception e){
            System.out.println("records not found");
        }
    }

    public static void updateQuery(Connection connection){
        try{
            String query = "Update person set name = ? where id =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,"Rajat");
            ps.setInt(2,101);
            ps.executeUpdate();
            System.out.println("record updated successfully");
        }
        catch(Exception e){
            System.out.println("record not update");
        }
    }

    public static void deleteQuery(Connection connection){
        try{
            String query = "Delete from person where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,101);
            ps.execute();
            System.out.println("record deleted successfully");
        }
        catch(Exception e){
            System.out.println("record not deleted");
        }
    }
}

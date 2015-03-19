/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanilaMailer;



import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Osama
 */
public class DBconnection {
    
    
    String url = "jdbc:mysql://localhost:3306/vanila";
    String user = "root";
    String pass = "";
    
    protected static  DBconnection instance = null;
    public Connection con;
    
    private DBconnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Can't find the driver");
        }
        try {
            con = DriverManager.getConnection(url,user,pass);
        } 
        catch (SQLException ex) {
            System.out.println("Can't connect database");
            JOptionPane.showConfirmDialog(null,"Can't Connect Database","Error",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DBconnection instance(){
        if(instance == null){
            instance = new DBconnection();
        }
        return instance;
    }
}

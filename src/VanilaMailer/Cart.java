/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanilaMailer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Osama
 */
public class Cart {
    private String id;
    private String userId;
    private int length;
    private String activeDate;
    
    private static Connection con = DBconnection.instance().con;
    
    public void setId(String newId){id = newId;}
    public String getId(){return id;}
    
    public void setUserId(String newUserId){userId = newUserId;}
    public String getUserId(){return userId;}
    
    public void setLength(int newLength){length = newLength;}
    public int getLength(){return length;}
    
    public void setActiveDate(String newActiveDate){activeDate = newActiveDate;}
    public String getActiveDate(){return activeDate;}
    
    
    public void updateCartStatus(){
        try {
            if(length==0){
                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM `cart_status` WHERE _id = '"+id+"'");
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean getCartsStatus(){
        boolean flag = true;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cart_status WHERE _id = '"+id+"'");
            while(rs.next()){
                if(rs.getInt("status")==1){
                    flag = false;
                }
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public void setCartStatus(){
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO `cart_status`(_id,status) VALUES ('"+id+"',1)");
        } 
        catch (SQLException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanilaMailer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Osama
 */
public class OffersRepository {
    
    private ArrayList<Offer> offersList;
    
    private static Connection con = DBconnection.instance().con;
    
    public void setOffersList(String url){
        
        ArrayList<Offer> offers = new ArrayList<>();
        JsonData newJson = new JsonData();
        newJson.setJsonArray(url);
        JSONArray offersArray = newJson.getJsonArray();
        JSONObject obj;
        try{
            for (int i = 0; i < offersArray.length(); i++) {
                Offer offer = new Offer();
                obj = offersArray.getJSONObject(i);
                offer.setId(obj.getString("_id"));
                offer.setDate(obj.getString("date"));
                offer.setDescription(obj.getString("description"));
                offers.add(i, offer);
            }
        } catch (JSONException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
        offersList = offers;
    
    }
    
    public ArrayList<Offer> getOffersList(){
        return offersList;
    }
    
    public ResultSet getSentOffers(){
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sent_offers");
            return rs;
        } 
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void setSentOffers(String email,String offerID){
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO sent_offers(email, offer_id) VALUES ('"+email+"','"+offerID+"')");
        } 
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

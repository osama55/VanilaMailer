/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanilaMailer;

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
public class CartsRepository {
    
    private ArrayList<Cart> cartsList;
    
    
    public void setCartsList(String url){
        ArrayList<Cart> carts = new ArrayList<Cart>();
        JsonData newJson = new JsonData();
        newJson.setJsonArray(url);
        JSONArray cartsArray = newJson.getJsonArray();
        JSONObject obj;
        try{
            for (int i = 0; i < cartsArray.length(); i++) {
                Cart cart = new Cart();
                obj = cartsArray.getJSONObject(i);
                cart.setId(obj.getString("_id"));
                cart.setUserId(obj.getString("user_id"));
                cart.setLength(obj.getJSONArray("items").length());
                cart.setActiveDate(obj.getString("active_date"));
                carts.add(i, cart);
            }
        } catch (JSONException ex) {
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
        }
        cartsList = carts;
    }
    
    public ArrayList<Cart> getCartsList(){
        return cartsList;
    }
}

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
public class StoresRepository {
    
    ArrayList<Store> storesList;
    
    public void setStoresList(String url){
        ArrayList<Store> stores = new ArrayList<Store>();
        JSONArray storesArray = JsonData.getJsonArray(url);
        JSONObject obj;
        try {
            for (int i = 0; i < storesArray.length(); i++) {    
                Store store = new Store();
                obj = storesArray.getJSONObject(i);
                store.setDomain((String) obj.getJSONArray("domains").get(0));
                store.setId(obj.getString("_id"));
                store.setReady(obj.getBoolean("ready"));
                stores.add(i, store);
            }
        }
        catch (JSONException ex) {
                Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
        }
        storesList = stores;
    }
    
    public ArrayList<Store> getStoresList(){
        return storesList;
    }
}

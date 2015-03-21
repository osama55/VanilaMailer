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
public class UsersRepository {
    
    private ArrayList<User> usersList;
    
    
    public void setUsersList(String url){
        ArrayList<User> users = new ArrayList<>();
        JsonData newJson = new JsonData();
        newJson.setJsonArray(url);
        JSONArray usersArray = newJson.getJsonArray();
        JSONObject obj;
        try{
            for (int i = 0; i < usersArray.length(); i++) {
                User user = new User();
                obj = usersArray.getJSONObject(i);
                user.setEmail(obj.getString("email"));
                user.setId(obj.getString("_id"));
                users.add(i, user);
            }
        } 
        catch (JSONException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        usersList = users;
    }
    
    public ArrayList<User> getUsersList(){
        return usersList;
    }

}

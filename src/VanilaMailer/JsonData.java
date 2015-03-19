/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanilaMailer;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;


/**
 *
 * @author Osama
 */
public class JsonData {
    
    private static String readAll(Reader rd)  {
        StringBuilder sb = new StringBuilder();
        try {
            int cp;
            while ((cp = rd.read()) != -1) {
              sb.append((char) cp);
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(VanilaMailer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
    
    
    public static JSONArray getJsonArray(String url){
        JSONArray json = null;
        try {
            InputStream is = null;
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            json = new JSONArray(jsonText);
        }
        catch (IOException | JSONException ex) {
            Logger.getLogger(JsonData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}

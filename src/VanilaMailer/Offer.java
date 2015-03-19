/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanilaMailer;

/**
 *
 * @author Osama
 */
public class Offer {
    
    private String id;
    private String date;
    private String description;
    
    public void setId(String newId){id = newId;}
    public String getId(){return id;}
    
    public void setDate(String newDate){date = newDate;}
    public String getDate(){return date;}
    
    public void setDescription(String newDescription){description = newDescription;}
    public String getDescription(){return description;}
}

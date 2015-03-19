/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanilaMailer;

/**
 *
 * @author Osama
 */
public class Store {
    
    private String domain;
    private String id;
    private boolean ready;
    
    public void setDomain(String newDomain){domain = newDomain;}
    public String getDomain(){return domain;}
    
    public void setId(String newId){id = newId;}
    public String getId(){return id;}
    
    public void setReady(boolean newReady){ready = newReady;}
    public boolean getReady(){return ready;}
}

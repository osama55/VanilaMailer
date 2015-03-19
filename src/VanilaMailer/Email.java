/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanilaMailer;

/**
 *
 * @author Osama
 */
public class Email {
    
    private String email;
    private String subject;
    private String body;
    
    public void setEmail(String newEmail){email = newEmail;}
    public String getEmail(){return email;}
    
    public void setSubject(String newSubject){subject = newSubject;}
    public String getSubject(){return subject;}

    public void setBody(String newBody){body = newBody;}
    public String getBody(){return body;}

    
    public void send(){
        System.out.println(email+"\n"+subject+"\n"+body);
    }
}

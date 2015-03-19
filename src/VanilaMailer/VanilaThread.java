/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanilaMailer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Osama
 */
public class VanilaThread extends Thread{
    
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        Engine newEngine = new Engine();
        try {
            while(true){
                newEngine.startEngine();
                System.out.println("here");
                Thread.sleep(120000);
            }
        } 
        catch (InterruptedException ex) {
            System.out.println("4546");
            Logger.getLogger(VanilaThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

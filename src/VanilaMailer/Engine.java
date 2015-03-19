/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanilaMailer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Osama
 */
public class Engine {
    
    String stores = "http://www.json-generator.com/api/json/get/ckDYrOagwO?indent=2";
    String users = "http://www.json-generator.com/api/json/get/bHWWIdNfqW?indent=2";
    String offers = "http://www.json-generator.com/api/json/get/bRxmyjTVYO?indent=2";
    String carts = "http://www.json-generator.com/api/json/get/bQFDbFAdxK?indent=2";
    
    StoresRepository newStores = new StoresRepository();
    UsersRepository newUsers = new UsersRepository();
    CartsRepository newCarts = new CartsRepository();
    OffersRepository newOffers = new OffersRepository();
    
    ArrayList<Store> storesList;
    ArrayList<User> usersList;
    ArrayList<Offer> offersList;
    ArrayList<Cart> cartsList;

    Store store;
    User user;
    Offer offer;
    Cart cart;
    
    public void startEngine(){
        newStores.setStoresList(stores);
        storesList = newStores.getStoresList();
        for (int i = 0; i < storesList.size(); i++) {
            store = storesList.get(i);
            if(store.getReady()){
                newUsers.setUsersList(users);
                usersList = newUsers.getUsersList();
                newOffers.setOffersList(offers);
                offersList = newOffers.getOffersList();
                newCarts.setCartsList(carts);
                cartsList = newCarts.getCartsList();
                compareAndSend(newUsers, newOffers);
                for (int j = 0; j < cartsList.size(); j++) {
                    cart = cartsList.get(j);
                    cart.updateCartStatus();
                    sendNotification(cart,usersList);
                }
            }
        }
    }
    
    private static String prepareToSend(Stack<String> dates,Stack<String> descriptions){
        String preparedEmail = "";
        while(!dates.empty()){
            preparedEmail = descriptions.pop()+"This offer is available till "+dates.pop()+"\n\n"+preparedEmail;
        }
        return preparedEmail;
    }
    
    private static void compareAndSend(UsersRepository users,OffersRepository offers){
        Stack<String> dates = new Stack<>();
        Stack<String> descriptions = new Stack<>();
        String email;
        String offerID;
        User user;
        Offer offer;
        ResultSet rs;
        boolean flag = false;
        for (int i = 0; i < users.getUsersList().size(); i++) {
            user = users.getUsersList().get(i);
            dates.setSize(0);
            descriptions.setSize(0);
            for (int j = 0; j < offers.getOffersList().size(); j++) {
                offer = offers.getOffersList().get(j);
                try {
                    rs = offers.getSentOffers();
                    while(rs.next()){
                        email = rs.getString("email");
                        offerID = rs.getString("offer_id");
                        if(email.equals(user.getEmail())&&offerID.equals(offer.getId())){
                            flag = false;
                        }
                    }
                    if(flag){
                        dates.push(offer.getDate());
                        descriptions.push(offer.getDescription());
                        offers.setSentOffers(user.getEmail(), offer.getId());
                    }
                } 
                catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(!dates.empty()){            
                Email newEmail = new Email();
                newEmail.setEmail(user.getEmail());
                newEmail.setSubject("New Offers");
                newEmail.setBody(prepareToSend(dates, descriptions));
                newEmail.send();
            }
            flag = true;
        }
    }
    
    private static void sendNotification(Cart cart, ArrayList<User> users){
        java.text.SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Date date;
        String userId;
        String email = null;
        if(cart.getLength()>0){
            try {
                date = (Date) fmt.parse(cart.getActiveDate());
                date = new Date(now.getTime()+2*3600*1000);
                if(date.before(now)){
                    if(cart.getCartsStatus()){
                        Email newEmail = new Email();
                        for (int i = 0; i < users.size(); i++) {
                            userId = users.get(i).getId();
                            if(userId.equals(cart.getUserId())){
                                email = users.get(i).getEmail();
                            }
                        }
                        if(!email.equals(null)){
                            newEmail.setEmail(email);
                            newEmail.setSubject("Notification");
                            newEmail.setBody("Nothing to send");
                            newEmail.send();
                            cart.setCartStatus();
                        }
                    }
                }
            }
            catch (ParseException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;
import org.academiadecodigo.socialsaver.persistence.model.Items;
import org.academiadecodigo.socialsaver.security.PasswordUtil;

import java.util.*;


public class DonerService {

    //private CustomerDao customerDao;

    private List<Doner> doners = new LinkedList<>();
    private Doner loggedDoner;

    public DonerService(){
        // constructor no longer seeds plaintext passwords; seeding should be performed by DataSeeder
    }


   // public void setCustomerDao(CustomerDao customerDao) {
       // this.customerDao = customerDao;
   // }

    public boolean login(String username, String password){
        for (Doner doner: doners) {
            if(doner.getName().equals(username) && PasswordUtil.matches(password, doner.getPassword())){
                loggedDoner = doner;
                return true;
            }
        }
        return false;
    }

    public Doner getLoggedDoner() {
        return loggedDoner;
    }

    public void add(Doner doner){
        // ensure password is stored hashed
        if (doner.getPassword() != null) {
            doner.setPassword(PasswordUtil.hash(doner.getPassword()));
        }
        doners.add(doner);
    }

    public void remove(Doner doner){
        doners.remove(doner);
    }

    public Doner getId(int id){
        return doners.get(id);
    }

   // public Doner get(Integer id) {
      //  return customerDao.findById(id);
    //}

    public void publishItem(Items items, Doner loggedDoner){
        loggedDoner.setToDonateItems(items);
    }

    public void donate(Receiver receiver, Items item){
        for(Items i :receiver.getNeededItems()){
            if(i.equals(item)){
                i.setDonated(true);
            }
        }
    }
}

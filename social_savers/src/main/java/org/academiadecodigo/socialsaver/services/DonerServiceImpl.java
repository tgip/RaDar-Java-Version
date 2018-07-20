package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;
import org.academiadecodigo.socialsaver.persistence.model.Items;

import java.util.*;


public class DonerServiceImpl {

    //private CustomerDao customerDao;

    private List<Doner> doners= new LinkedList<>();
    private Doner loggedDoner;


   // public void setCustomerDao(CustomerDao customerDao) {
       // this.customerDao = customerDao;
   // }

    public boolean verifyLogin(String username, String password){
        for (Doner doner:doners) {
            if(doner.getName().equals(username) && doner.getPassword().equals(password)){
                loggedDoner=doner;
              return true;
            }
        }
    return false;
    }


    public void add(Doner doner){
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


    public void donate(Receiver receiver, Items item){
        for(Items i :receiver.getNeededItems()){
            if(i.equals(item)){
                i.setDonated(true);
            }
        }
    }
}

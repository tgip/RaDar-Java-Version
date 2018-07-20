package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;
import org.academiadecodigo.socialsaver.persistence.dao.CustomerDao;
import org.academiadecodigo.socialsaver.persistence.model.Items;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * An {@link DonerService} implementation
 */
public class DonerServiceImpl implements DonerService {

    //private CustomerDao customerDao;

    private List<Doner> doners= new LinkedList<>();
    private ItemService itemService;

   // public void setCustomerDao(CustomerDao customerDao) {
       // this.customerDao = customerDao;
   // }


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
        itemService.donated(item);
    }
}

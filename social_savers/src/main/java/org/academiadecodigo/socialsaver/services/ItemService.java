package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Items;

import java.util.LinkedList;
import java.util.List;

public class ItemService {

    private List<Items> items=new LinkedList<>();


    public void add(Items item){
        items.add(item);
    }

    public void remove(Items item){
        items.remove(item);
    }

    public boolean donated(Items item){
      return item.isDonated();
    }
}

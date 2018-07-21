package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;
import org.academiadecodigo.socialsaver.persistence.model.Items;

import java.util.*;

public class ReceiverService {

    private List<Receiver> receivers= new LinkedList<>();
    private List<Items> allItems= new LinkedList<>();


    public ReceiverService() {
        Receiver receiver=new Receiver();
        receiver.setName("Caritas");
        receiver.setType("IPSS");
        Items items= new Items();
        items.setType("Batatas");
        add(receiver);
        publishItem(items,receiver);
        receivers.add(receiver);
    }

    public void add(Receiver receiver){
        receivers.add(receiver);
    }

    public void publishItem(Items items, Receiver loggedReceiver){
        loggedReceiver.setNeededItems(items);

    }

    public List<Items> listAllItems(){
        for(Receiver receiver: receivers){
            for(Items item:receiver.getNeededItems()){
                allItems.add(item);
            }
        }
        return allItems;
    }


}

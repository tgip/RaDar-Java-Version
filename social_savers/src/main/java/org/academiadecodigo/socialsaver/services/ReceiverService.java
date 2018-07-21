package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;
import org.academiadecodigo.socialsaver.persistence.model.Items;

import java.util.LinkedList;
import java.util.List;

public class ReceiverService {

	private List<Receiver> receivers = new LinkedList<>();
	private List<Items> allItems = new LinkedList<>();

	public ReceiverService() {
		Receiver receiver = new Receiver();
		receiver.setName("Caritas");
		//receiver.setNeededItems();
		receiver.setType("Badjoras");
		receiver.setEmail("sdfdfs");
		receiver.setPassword("asddasdas");
		add(receiver);
		Items items = new Items();
		items.setType("Batata");
		publishItem(items,receiver);
	}
	public void add(Receiver receiver) {
		receivers.add(receiver);
	}

	public void publishItem(Items items, Receiver loggedReceiver) {
		loggedReceiver.setNeededItems(items);
	}

	public List<Items> listAllItems() {
		for (Receiver receiver : receivers) {
			for (Items item : receiver.getNeededItems()) {
				allItems.add(item);
			}
		}
		return allItems;
	}
}

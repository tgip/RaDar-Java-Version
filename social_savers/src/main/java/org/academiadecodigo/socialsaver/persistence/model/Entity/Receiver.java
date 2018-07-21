package org.academiadecodigo.socialsaver.persistence.model.Entity;

import org.academiadecodigo.socialsaver.persistence.model.Items;

import java.util.LinkedList;
import java.util.List;

/**
 * The recipient model entity
 */

public class Receiver extends AbstractEntity {

	private List<Items> neededItems = new LinkedList<>();

	private String type = "receiver";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Items> getNeededItems() {
		return neededItems;
	}

	public void setNeededItems(Items items) {
		neededItems.add(items);
	}

	public void receiveItem(Items item) {
		neededItems.remove(item);
	}
}

package com.kevwong.few;

import android.view.View;

public class Avatar {
	
	String name;
	String element;
	int health;
	int armour;
	int[] attack;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getArmour() {
		return armour;
	}
	public void setArmour(int armour) {
		this.armour = armour;
	}
	public int[] getAttack() {
		return attack;
	}
	public void setAttack(int[] attack) {
		this.attack = attack;
	}
	
}
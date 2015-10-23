/*
 	Name:Justin Weisser
 	Date: 3/13/14
 	Assignment: Homework 2
 	Class: CS3330
 */
package jcwywb.cs3330.hw2;

//Updated
public class Player {
	
	private String name;
	private int health;
	private int money;
	
	public Player (String name) {
		this.setName(name);
		this.setHealth(100);
		this.setMoney(20000);
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	private void setHealth(int health) {
		this.health = health;
	}
	
	private void setMoney (int money) {
		this.money = money;
	}
	public String getName() {
		return this.name;
	}
	public int getHealth() {
		return this.health;
	}
	public int getMoney () {
		return this.money;
	}
	public void updateHealth(int health) {
		int new_health = this.getHealth() + health;
		this.setHealth(new_health);
	}
	
	public void updateMoney(int income) {
		int new_money = this.getMoney() + income;
		this.setMoney(new_money);
	}	
	public String toString() {
		if (this.getHealth() > 0) {
			return "Hacker " + this.name + " with health of " + this.health + " with " + this.money + " yen ";
		}
		else {
			return "Hacker " + this.name + " with health of " + 0 + " with " + this.money + " yen ";
		}
	}
}

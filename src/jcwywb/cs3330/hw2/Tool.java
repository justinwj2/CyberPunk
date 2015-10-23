/*
 	Name:Justin Weisser
 	Date: 3/13/14
 	Assignment: Homework 2
 	Class: CS3330
 */
package jcwywb.cs3330.hw2;

//Updated
public class Tool {
	private String name;
	private String type;
	private int cost;
	private int strength;
	
	public Tool (String name,String type,int cost,int strength) {
		this.setName(name);
		this.setStrength(strength);
		this.setType(type);
		this.setCost(cost);
	}
	
	//Setters
	private void setName(String name) {
		this.name = name;
	}
	private void setType(String type) {
		this.type = type;
	}
	private void setStrength(int strength) {
		this.strength = strength;
	}
	private void setCost(int cost) {
		this.cost = cost;
	}
	
	
	//Getters
	public String getName() {
		return this.name;
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	
	//Update Strength and cost
	public void updateStrength(int strength) {
		this.setStrength(this.getStrength() + strength);
	}
	
	public void updateCost(int cost) {
		this.setCost(this.getCost() + cost);
	}
	
	
	@Override
	public String toString() {
		return this.getType() + " tool named " + this.getName() + " with " + this.getStrength() + " strength and a cost of " + this.getCost();
	}
}

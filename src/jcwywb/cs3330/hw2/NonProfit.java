/*
 	Name:Justin Weisser
 	Date: 3/13/14
 	Assignment: Homework 2
 	Class: CS3330
 */
package jcwywb.cs3330.hw2;

//Complete
public class NonProfit extends Business{
	
	//Constructor
	public NonProfit(String name, String contact, Grid grid) {
		super(name, contact, grid);
	}
	
	@Override
	public String toString() {
		return "[Contact  " + this.getContact() + "]   Non Profit: " + this.getName();
	}
}

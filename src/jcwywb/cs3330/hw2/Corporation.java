/*
 	Name:Justin Weisser
 	Date: 3/13/14
 	Assignment: Homework 2
 	Class: CS3330
 */
package jcwywb.cs3330.hw2;

import java.util.Random;

//Completed!!
public class Corporation extends Business{
	
	//Constructor
	public Corporation(String name, String contact, Grid grid){
		super(name, contact, grid);
	}
	
	@Override
	protected int determineWorth() {
		Random randomGenerator = new Random();
		int worth = randomGenerator.nextInt(15000) + 20000;
		return worth;
	}
	
	@Override
	public String toString() {
		return "[Contact  " + this.getContact() + "]   Corporation: " + this.getName();
	}
	
}

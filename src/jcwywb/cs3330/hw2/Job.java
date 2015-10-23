/*
 	Name:Justin Weisser
 	Date: 3/13/14
 	Assignment: Homework 2
 	Class: CS3330
 	TA: Matthew England
 */
package jcwywb.cs3330.hw2;

//Completed
public class Job {
	private String target_address;
	private int reward;
	
	//Constructor
	public Job (String targ_address, int reward) {
		this.setTargetAddress(targ_address);
		this.setReward(reward);		
	}
	
	//Setters
	private void setReward(int reward){
		this.reward = reward;
	}
	
	private void setTargetAddress(String target_address) {
		this.target_address = target_address;
	}
	
	
	//Getters
	public int getReward() {
		return this.reward;
	}
	
	public String getTargetAddress() {
		return this.target_address;
	}
	
	@Override
	public String toString(){
		return "Job: " + this.getTargetAddress() + " worth " + this.getReward();
	}
}

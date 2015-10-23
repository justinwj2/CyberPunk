/*
 	Name:Justin Weisser
 	Date: 3/13/14
 	Assignment: Homework 2
 	Class: CS3330
 */
package jcwywb.cs3330.hw2;

import java.util.ArrayList;
import java.util.Random;

//Completed
public class Business {
	private String name;
	private String contacts_name;
	private ArrayList<Job> jobOffers;
	protected Grid grid;
	protected static int JOB_OFFER_SIZE = 10;
	
	//Constructor
	public Business(String name, String contact, Grid grid) {
		this.setBusinessName(name);
		this.setBusinessContact(contact);
		this.setGrid(grid);
		this.fillJobOffers();
	}
	
	//Setters
	private void setBusinessName(String name) {
		this.name = name;
	}
	
	private void setBusinessContact(String contact) {
		this.contacts_name = contact;
	}
	
	private void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	//Getters
	protected String getName() {
		return this.name;
	}
	
	protected String getContact() {
		return this.contacts_name;
	}
	
	protected void fillJobOffers(){
		Random randomGenerator = new Random();
		
		//Instantiate jobOffers
		this.jobOffers = new ArrayList<Job>(JOB_OFFER_SIZE);
		
		//Loop to fill random jobs
		for (int i = 0; i < JOB_OFFER_SIZE; i ++){
			int server_idx = randomGenerator.nextInt(this.grid.numberOfServers());
			jobOffers.add(new Job(this.grid.getServerName(server_idx), this.determineWorth()));			
		}
	}
	
	//Print out the jobOffers
	public void viewJobOffers() {
		int row = 1;
		for (Job job : jobOffers) {
			System.out.println(row +") "+ job.toString());
			++row;
		}
	}
	
	//Method to get the job offer
	public Job getJobOffer(int job_offer) {
		Job tempJob = this.jobOffers.get(job_offer);
		this.jobOffers.remove(tempJob);
		return tempJob;
	}
	
	//Method to get the size of the job offer array
	public int getJobOfferCount(){
		return jobOffers.size();
	}
	
	protected int determineWorth() {
		Random randomGenerator = new Random();
		int worth = randomGenerator.nextInt(15000) + 10000;
		return worth;
	}
	
}

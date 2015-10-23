/*
 	Name:Justin Weisser
 	Date: 3/13/14
 	Assignment: Homework 2
 	Class: CS3330
 */
package jcwywb.cs3330.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

//Updating...
public class Console {
	
	private static final Scanner input = new Scanner(System.in);
	Player player;
	ToolKit toolkit;
	Grid grid;
	private ArrayList<Job> jobs;
	private ArrayList<Business> contacts;
	private BlackMarket blackMarket;
	
	//Class constructor.
	public Console (Player player, Grid grid) {
		this.setPlayer(player);
		this.init();
	}
	
	//Setter for player.
	private void setPlayer(Player player) {
		this.player = player;
	}
	
	//Initalizes the TookKit and Grid attributes
	public void init() {
		this.toolkit = new ToolKit();
		this.grid    = new Grid();
		this.blackMarket = new BlackMarket("New Tokyo Subterranean Sewer", "Katsu Tanaka", grid, player);
		this.jobs = new ArrayList<Job>();
		this.loadContacts();
		
	}
	/**
	 * Method that runs the game 
	 * @return false when the player dies
	 */
	public boolean use() {
		
		int option;
		String address = null;
		int server_idx = 0;
		while(this.player.getHealth() > 0) {
			//Display player info and display options
			System.out.println("");
			System.out.println(player.toString());
			System.out.println("");
			option = this.displayOptionMenu();
			
			//Switch case situation
			switch (option){
				case 1: 
					boolean result;
					int index;
					Job jobRun;
					
					//Check if job exists, if it does not then state it else pull a job and run on the grid
					if (jobs.isEmpty()){
						System.out.println("You need to get a job");
					} else {
						//Get a job and address of the job
						jobRun = this.pullAJob();
						address = jobRun.getTargetAddress();
						
						//Get the index and run on the grid
						index = this.grid.search(address);
						result = this.runOnTheGrid(index, jobRun.getReward());
						
						//If run successful then delete the element from the jobs arraylist
						if (result == true) {
							jobs.remove(jobRun);
						} 
					}
					
					break;
					
				case 2:
					
					if (player.getMoney() > 1) {
						System.out.println(player.toString());
						System.out.println("");
						System.out.println("Welcome to the Black Market");
						this.updateToolKit();
					} else{
						System.out.println("No funds to visit the black market");
					}
					
					break;
					
				case 3:
					
					if (jobs.size() < 11) {
						this.findJobOffer();
					} else {
						System.out.println("Too many jobs left undone");
					}
					break;
					
				case 4:
					return true;
			}
			
		}
		System.out.println(player.toString() + "has FLATLINED");
		return false;
		
	}
	
	//Updates a picked from the toolkit arraylist 
	private void updateToolKit() {
		Tool newTool;
		
		//Get new tool from the black market
		newTool = this.blackMarket.buyATool();
		
		//Check if a tool was bought, if not then return nothing, else add the tool to the toolkit
		if (newTool == null){
			return;
		} else{
			this.toolkit.addOrUpdateTool(newTool);
		}	
	}
	/**
	 *
	 * @param target_server : int
	 * The method allow the player to attack a target server with a selected tool.
	 */
	private boolean runOnTheGrid (int target_server, int possible_income) {
		this.toolkit.displayTools();
		
		//Get tool number
		System.out.print("Pick your ice breaker: ");
		int tool_choice = Integer.parseInt(input.nextLine());
		--tool_choice;
		
		//Error check to get valid tool
		while (tool_choice < 0 || tool_choice > this.toolkit.getNumberOfTools() - 1) {
			System.out.print("Not a valid tool, enter your choice again: ");
			tool_choice = Integer.parseInt(input.nextLine());
			--tool_choice;
		}
		
		//Create tool variable
		System.out.println(" ");
		Tool tool = this.toolkit.getTool(tool_choice);
		
		//Run the attack and check success or failure
		int result = grid.attack(tool,target_server);
		if (result > 0) {
			System.out.println("RUN SUCCESSFUL");
			this.player.updateMoney(possible_income);
			return true;
		}
		else {
			System.out.println("RUN FAILURE");
			this.player.updateHealth(result);
			return false;
		}

	}

	
	
	
	//New methods
	private int displayOptionMenu(){
		int selection;
		
		//Display options block
		System.out.println("1) Run on the Grid");
		System.out.println("2) Visit the Black Market");
		System.out.println("3) Add a job to the list");
		System.out.println("4) Log off Console");
		
		//Grab selection 
		System.out.println("Selection: ");
		selection = Integer.parseInt(input.nextLine());
		
		//Error check for correct selection number
		while (selection < 1 || selection > 4) {
			System.out.println("Invalid option, enter new selection: ");
			selection = Integer.parseInt(input.nextLine());
		}
		
		//Return selection number
		return selection;
	}
	
	private void loadContacts() {
		//varibles to load in file
		File file = new File("contacts.txt");
		Scanner dataScanner = null;
		
		//Exception handling if file doesnt exist
		try {
			dataScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Contacts file not found.");
			System.exit(1);
		}
		
		//Create new instance of array list
		contacts = new ArrayList<Business>();
		String line;
		
		//Loop through the file and fill the contacts array list
		while (dataScanner.hasNext()) {
			line = dataScanner.nextLine();
			
			String[] parts = line.split(",");
			
			//If the line is corporation then greate new corporation 
			if (parts[0].equals("Corporation")){
				contacts.add(new Corporation(parts[1], parts[2], this.grid));
				
				//Else if non profit then create new non profit
			} else if(parts[0].equals("NonProfit")) {
				contacts.add(new NonProfit(parts[1], parts[2], this.grid));
			}
		}
		
		//Close the scanner
		dataScanner.close();
	}
	
	private Job pullAJob(){
		int row = 1, option;
		
		//Loop through to display jobs
		for (Job job : jobs)
		{
			System.out.println(row +") "+ job.toString());
			++row;
		}
		
		//Get selection
		System.out.println("Selection:");
		option = Integer.parseInt(input.nextLine());
		
		//Error check to make sure selection is within bounds
		while (option < 1 || option > jobs.size()){
			System.out.println("Invalid option, enter new selection: ");
			option = Integer.parseInt(input.nextLine());
		}
		
		//Subtract 1 to select correct array element
		option--;
		
		return jobs.get(option);
	}
	
	private void findJobOffer() {
		int row = 1, index;
		//Loop through to display jobs
		for (Business contact : contacts)
		{
			System.out.println(row +")  "+ contacts.get(row-1).toString());
			++row;
		}
		//Get selection
		System.out.println("Selection:");
		index = Integer.parseInt(input.nextLine());
		
		//Error check to make sure selection is within bounds
		while (index < 1 || index > contacts.size()){
			System.out.println("Invalid option, enter new selection: ");
			index = Integer.parseInt(input.nextLine());
		}
		
		//Subtract 1 to select correct array element
		index--;
				
		contacts.get(index).viewJobOffers();
		
		//Get selection
		System.out.println("Selection:");
		int option = Integer.parseInt(input.nextLine());
		
		//Error check to make sure selection is within bounds
		while (option < 1 || option > contacts.get(index).getJobOfferCount()){
			System.out.println("Invalid option, enter new selection: ");
			option = Integer.parseInt(input.nextLine());
		}
		
		//Subtract one to get correct index
		option--;
		
		//Send job offer and put selected job offer into jobs array list
		jobs.add(contacts.get(index).getJobOffer(option));
	}
}

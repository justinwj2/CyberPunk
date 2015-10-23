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

//Completed!!
public class BlackMarket extends Business{
	private ArrayList<Tool> tools;
	private static final Scanner input = new Scanner(System.in);
	private Player buyer;
	
	//Constructor
	public BlackMarket(String name, String contact, Grid grid, Player buyer) {
		super(name, contact, grid);
		this.setBuyer(buyer);
		this.loadTools();
	}
	
	//Setter
	private void setBuyer(Player buyer) {
		this.buyer = buyer;
	}
	
	private void loadTools(){
		//File to be read in
		File toolDataFile = new File("tools_to_buy.txt");
		Scanner dataScanner = null;
		
		//Try to read in the file
		try {
			dataScanner = new Scanner(toolDataFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Tools file not found.");
			System.exit(1);
		}
		
		//Instantiate the new tools array list
		this.tools = new ArrayList<Tool>();
		String input;
		
		// Read in the tools
		while(dataScanner.hasNext()) {
			// Read in each line, splitting the csv on the comma
			input = dataScanner.nextLine();
			String[] data = input.split(",");
			
			//Insert data into array list
			tools.add(new Tool(data[0],data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3])));
		}
		dataScanner.close();
	}
	
	public void viewToolsDatabase() {
		int row = 1;
		
		//Loop through to print out all the tools
		for (Tool tool : tools)
		{
			System.out.println("PN:" + row +" ["+ tool.toString() + "]");
			++row;
		}
	}
	
	public Tool buyATool() {
		this.viewToolsDatabase();
		System.out.println("To cancel purchase enter -1");
		System.out.println("Selection:");
		
		//Read in the choice
		int tool_choice = Integer.parseInt(input.nextLine());
		
		if (tool_choice == -1){
			System.out.println("No tool bought");
			System.out.println("");
			return null;
		}
		
		//Error check to make sure valid tool
		while (tool_choice < 1 || tool_choice > tools.size()) {
			System.out.println("Invalid choice, try again: ");
			tool_choice = Integer.parseInt(input.nextLine());
		}
		tool_choice--;
                
		//Check if the buyer has enough money, if so then return the tool, if not return null
		if(this.buyer.getMoney() > this.tools.get(tool_choice).getCost()){
			this.buyer.updateMoney(-this.tools.get(tool_choice).getCost());
			return this.tools.get(tool_choice);
		} else {
			System.out.println("Not enough money");
			return null;
		}
	}
	
	@Override
	public String toString(){
		return "";
	}

}

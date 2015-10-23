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

//Updated
public class Grid {
	
	private ArrayList<Server> servers;
	
	public Grid() {
		this.loadServers();
	}
	/**
	 * 
	 * @param name
	 * @return negative one if the server doesn't exist, else returns the found servers index
	 */
	public int search(String name) {
		int index = 0;
		for (Server server : servers)
		{
			if (server.getName().equals(name)){
				return index;
			}
			++index;
		}
		return -1;
	}
	
	
	//Get server name
	public String getServerName(int server_idx){
		return servers.get(server_idx).getName();
	}
	
	 // @return the size of the arraylist servers
	public int numberOfServers() {
		return servers.size();
	}
	/**
	 * 
	 * @param tool
	 * @param server
	 * @return the results of the attack, positive value if successful break in. 
	 * Negative value for failure to break in
	 */
	public int attack(Tool tool, int server) {
		int score = 0;
		int update = 3;
		System.out.println("ICE ENCOUNTERED, ATTEMPTING BREAK IN.....");
		System.out.println("SERVER INFO: " + servers.get(server).toString());
		if (tool.getType().equals("DECRYPTOR") && servers.get(server).getType().equals("ENCRYPTED")) {
			if (tool.getStrength() > servers.get(server).getStrength()) {
				score = 25000;
			}
			else {
				score = -20;
			}
			this.servers.get(server).updateStrength(update);
			return score;
		}
		if (tool.getType().equals("BASHER") && servers.get(server).getType().equals("UNENCRYPTED")) {
			if (tool.getStrength() > servers.get(server).getStrength()) {
				score = 10000;
			}
			else{
			 score = -10;
			}
			this.servers.get(server).updateStrength(update);
			return score;
		}
		this.servers.get(server).updateStrength(update);
		return -30;

	}
	/**
	 * Load the servers information from the servers.txt
	 */
	private void loadServers() {
		File serverDataFile = new File("servers.txt");
		Scanner dataScanner = null;
		try {
			dataScanner = new Scanner(serverDataFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Servers file not found.");
			System.exit(1);
		}
		this.servers = new ArrayList<Server>();
		String input;
		// Read in the tools
		while(dataScanner.hasNext()) {
			// Read in each line, splitting the csv on the comma
			input = dataScanner.nextLine();
			String[] data = input.split(",");
			servers.add(new Server(data[0],data[1],Integer.parseInt(data[2])));
		}
		dataScanner.close();
	}
}

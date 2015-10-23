/*
 	Name:Justin Weisser
 	Date: 3/13/14
 	Assignment: Homework 2
 	Class: CS3330
 */
package jcwywb.cs3330.hw2;
import java.util.Scanner;

public class CyberPunk {
	private static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("Enter your name: ");
		String name = input.nextLine();
		Player player = new Player(name);
		Grid grid = new Grid();
		System.out.println("The year is 2077");
		
		System.out.println("Starting Console");
		Console console = new Console(player,grid);
		if (console.use() == true) {
			System.out.println("LOGGING OFF");
		} else {
			System.out.println("Game over!");
		}
		input.close();
		System.exit(0);
			
	}

}

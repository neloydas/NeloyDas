package com.xf.mealselection;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Food Ordering System application
 *
 */
public class FoodOrderSystem {
	public static void main(String[] args) {

		// Getting all the menu choices from the inventory
		FoodChoices choice = new FoodChoices();
		ArrayList<Item> foodInventory = choice.getFoodChoices();
		ArrayList<Item> drinksInventory = choice.getDrinkChoices();

		// Showing the whole inventory on screen
		Iterator<Item> itrF = foodInventory.iterator();
		Iterator<Item> itrD = drinksInventory.iterator();

		System.out.println("Please select your menu from the below mentioned choices(Press Enter after each choice): ");
		int i, choices[] = new int[100];

		// Show the Food Items
		for (i = 1; itrF.hasNext(); i++) {
			Item itemF = (Item) itrF.next();
			System.out.format("%1d. Name: %-20sPrice: %-10.2f", i, itemF.getName(), itemF.getPrice());
			System.out.println("");
		}
		int foodItemsCount = i - 1;

		// Show the Drink Items
		for (; itrD.hasNext(); i++) {
			Item itemD = (Item) itrD.next();
			System.out.format("%1d. Name: %-20sPrice: %-10.2f", i, itemD.getName(), itemD.getPrice());
			System.out.println("");
		}

		System.out.println("Enter 0 to exit after selecting meal choices.");

		// Taking the order
		int counter = 0, a = 1;
		Map<Integer, Integer> drinkSelection = new HashMap<Integer, Integer>();

		Scanner scanner = new Scanner(System.in);
		for (counter = 0; a != 0; counter++) {
			try {
				a = scanner.nextInt();
			} catch (InputMismatchException ie) {
				System.out.println("Input is not a number. Please enter only numbers.");
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
			// validate the choice
			if (a > i) {
				System.out.println("You have entered an invalid choice. Please select again.");
				scanner.close();
				return;
			}
			if (a > foodItemsCount) {
				System.out.println("Do you want your drink with ice cubes or lemon?");
				System.out.println("Enter 1 for ice cube, 2 for lemon and 3 for without anything");
				int iceOrLemon = 3;
				try {
					iceOrLemon = scanner.nextInt();
				} catch (InputMismatchException ie) {
					System.out.println("Input is not a number. Please enter only numbers.");
					System.exit(0);
				} catch (Exception e) {
					System.exit(0);
				}
				// validate the choice
				if (iceOrLemon > 3 || iceOrLemon < 1) {
					System.out.println("Invalid choice. Please try again.");
					scanner.close();
					return;
				}
				System.out.println("Now please proceed with another selection from main menu. Or 0 to exit");
				drinkSelection.put(a, iceOrLemon);
			}
			choices[counter] = a;
		}
		scanner.close();
		// Order taken

		System.out.println("Thank you for your order. Please wait while we prepare it.");

		// Getting the ordered menu details from the whole bunch
		ArrayList<Item> menuchoice = new ArrayList<Item>();
		// setting the counter -2 as it is always preceding by one array index
		// starting from 0 and the last exit button 0 which is not in menu.
		counter = counter - 2;
		while (counter != -1) {
			if (choices[counter] <= foodItemsCount)
				menuchoice.add(foodInventory.get(choices[counter] - 1));
			if (choices[counter] > foodItemsCount)
				menuchoice.add(drinksInventory.get(choices[counter] - (foodItemsCount + 1)));
			counter--;
		}

		// Now Iterate through the choices to calculate the total bill
		double totalBill = 0;
		Iterator<Item> itr1 = menuchoice.iterator();
		while (itr1.hasNext()) {
			Item item = (Item) itr1.next();
			totalBill = totalBill + item.getPrice();
		}

		// Formatting the Bill to 2 digits after decimal and showing it
		DecimalFormat df = new DecimalFormat("###.##");
		System.out.println("Your total bill for today is: " + df.format(totalBill));
		System.out.println("Thank you for shopping. Enjoy your meal!  Have a nice day!");

	}
}

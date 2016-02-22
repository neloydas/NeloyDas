package com.xf.mealselection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.xf.mealselection.Item;

public class FoodChoices {
	// to get the menu items from a file
	public ArrayList<Item> getFoodChoices() {
		ArrayList<Item> menulist = new ArrayList<Item>();
		menulist = readFromFile("cuisine.txt");
		return menulist;
	}
	
	//get the drinks from file
	public ArrayList<Item> getDrinkChoices(){
		ArrayList<Item> menulist = new ArrayList<Item>();
		menulist = readFromFile("drinks.txt");
		return menulist;
		
	}

	private ArrayList<Item> readFromFile(String filename) {
		ArrayList<Item> menulist = new ArrayList<Item>();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream(filename)))) {

			String item;

			while ((item = br.readLine()) != null) {
				Item cuisine = new Item();
				String tmp[] = item.split(":");
				cuisine.setName(tmp[0]);
				cuisine.setPrice(Double.parseDouble(tmp[1]));
				menulist.add(cuisine);
			}

		} catch (IOException e) {
			System.out.println(
					"An Unexpected error happened while retrieving the menu list. Please check the file Cuisine.txt.");
			e.printStackTrace();
			System.exit(0);
		}
		return menulist;

	}

}

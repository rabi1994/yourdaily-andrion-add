package com.example.yourdailyproject;

public abstract class Food {
	protected String name ;
	protected double caloriesPer100 ;
	protected double protein ;
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected double getCalories() {
		return caloriesPer100;
	}
	protected void setCalories(double calories) {
		this.caloriesPer100 = calories;
	}
	protected double getProtein() {
		return protein;
	}
	protected void setProtein(double protein) {
		this.protein = protein;
	}
	public String toString() {
		return name +":\t";
	}

}

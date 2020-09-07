package com.example.yourdailyproject;

public class FromageBlanc extends Food {
	
	public FromageBlanc()
	{
		this.name= "FromageBlanc";

		this.caloriesPer100= 13.6;
		this.protein = 0.7;
	}
	public double mycalories()
	{
		return caloriesPer100 ;
	}

}

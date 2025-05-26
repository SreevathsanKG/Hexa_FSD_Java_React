package com.lms.enums;

public enum City {
	
	MUMBAI(10),PUNE(5),CHENNAI(6);
	
	City(int val){ //val = 10 | 5 | 6
		this.value = val; //value = 10 | 5 | 6
	}
	
	private int value;

	public int getValue() {
		return value;
	}
}

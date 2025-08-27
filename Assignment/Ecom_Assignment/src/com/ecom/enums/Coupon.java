package com.ecom.enums;

public enum Coupon {
	GREATINDIANFESTIVAL(20),
	PRIMEDAY(10),
	NEWYEAR(15);

	Coupon(int discount) {
		this.discount=discount;
	}
	
	public int getDiscount() {
		return discount;
	}

	private int discount;
	
}

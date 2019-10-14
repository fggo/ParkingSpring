package com.parking.api.model.vo;

import java.util.Objects;

public class Coupon {
	
	
	private String couponCode;
	private String userCode;
	private int discountRate;
	private int duration;
	private int expiredYn;
	
	
	
	public Coupon() {
		
	}
	
	
	public Coupon(String couponCode,String userCode)
	{
		super();
		this.couponCode = couponCode;
		this.userCode = userCode;
	}
	

	public Coupon(String couponCode, String userCode, int discountRate, int duration, int expiredYn) {
		super();
		this.couponCode = couponCode;
		this.userCode = userCode;
		this.discountRate = discountRate;
		this.duration = duration;
		this.expiredYn = expiredYn;
	}




	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}




	public int getDiscountRate() {
		return discountRate;
	}




	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}




	public int getDuration() {
		return duration;
	}




	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}




	public int getExpiredYn() {
		return expiredYn;
	}




	public void setExpiredYn(int expiredYn) {
		this.expiredYn = expiredYn;
	}

	
	@Override
	public boolean equals(Object obj) {
		Coupon c;
		if(obj instanceof Coupon)
		{
			c = (Coupon)obj;
			if(couponCode.equals(c.getCouponCode()))
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	
	
	@Override
	public String toString() {
		return "Coupon [couponCode=" + couponCode + ", userCode=" + userCode + ", discountRate=" + discountRate
				+ ", duration=" + duration + ", expiredYn=" + expiredYn + "]";
	}


	@Override
	public int hashCode()
	{
		return Objects.hash(couponCode);
	}	
}

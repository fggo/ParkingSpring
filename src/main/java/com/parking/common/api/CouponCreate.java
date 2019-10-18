package com.parking.common.api;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.parking.api.model.vo.Coupon;



public class CouponCreate {
	
	
	public CouponCreate() {
		// TODO Auto-generated constructor stub
	}
	
	public Set couponCreate()
	{
		int couponSize = 1;
		
		Set<Coupon> couponSet = new HashSet<Coupon>();
		
		char[] coupon = new char[62];
		
		char ascCode = 48;
		for (int i = 0; i < 62; i++) {
			if (i < 10) {
				coupon[i] = ascCode; // 0 1 2 3 4 5 6 7 8 9
			} // 0 1 2 3 4 5 6 7 8 9
			else if (i == 10) {
				ascCode = 65; // 10 A
				coupon[i] = ascCode;

			} else if (i > 10 && i < 36) // 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35
			{ // B C D E F G H I J K L N M O P Q R S T U V W X Y Z
				coupon[i] = ascCode;

			} else if (i == 36) {
				ascCode = 97; // 36 a
				coupon[i] = ascCode;
			} else if (i > 36 && i < 62) // 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61
			{ // b c d e f g h i j k l n m o p q r s t u v w x y z
				coupon[i] = ascCode;
			}
			ascCode++;
		}

		
		int couponCount = coupon.length;

		
		Random randomNumber = new Random();
		int index = 0;
		int j = 0;
		
		
		while (index < couponSize) {
			
			StringBuffer stringBuf = new StringBuffer(16);
			
			for (j = 16; j > 0; j--) {
			
				stringBuf.append(coupon[randomNumber.nextInt(couponCount)]);
			}

			String couponCode = stringBuf.toString();
			System.out.println(couponCode);
			
			couponSet.add(new Coupon(couponCode, null));
			index++;
		}
		
		return couponSet;
	}
			
}

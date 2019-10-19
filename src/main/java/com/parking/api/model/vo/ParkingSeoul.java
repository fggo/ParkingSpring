package com.parking.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkingSeoul {

	private String psParkingCode;
	private String psAddr;
	private String psName;
	private double psLatitude;
	private double psLongitude;
}

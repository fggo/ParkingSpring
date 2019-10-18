package com.parking.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkingSlot {
	private String ownerNo;
	private String ownerParkingCode;
	private String slotUserCode;
	private String slotBeginTime;
	private String slotEndTime;
	
}

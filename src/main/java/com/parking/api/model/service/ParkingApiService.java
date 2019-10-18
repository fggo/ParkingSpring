package com.parking.api.model.service;

import java.util.List;

import com.parking.api.model.vo.Parking;
import com.parking.api.model.vo.ParkingSlot;

public interface ParkingApiService {

	List<Parking> selectParkingList(String addrName);
	List<ParkingSlot> selectParkingSlotList();
	
}

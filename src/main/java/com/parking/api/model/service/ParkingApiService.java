package com.parking.api.model.service;

import java.util.List;
import java.util.Map;

import com.parking.api.model.vo.Parking;
import com.parking.api.model.vo.ParkingSlot;

public interface ParkingApiService {

	List<Parking> selectParkingList(String addrName);
	List<ParkingSlot> selectParkingSlotList();
	int insertparkingOwner(List<Parking> list);
	int insertParkingSeoul(List<Parking> list);
}

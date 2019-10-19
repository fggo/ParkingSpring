package com.parking.api.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.parking.api.model.vo.Parking;
import com.parking.api.model.vo.ParkingOwner;
import com.parking.api.model.vo.ParkingSeoul;
import com.parking.api.model.vo.ParkingSlot;

public interface ParkingApiDao {
	
	List<ParkingSlot> selectParkingSlotList(SqlSessionTemplate session);
	int insertparkingOwner(SqlSessionTemplate session, ParkingOwner p);
	int insertParkingSeoul(SqlSessionTemplate session, ParkingSeoul p);
	
}

package com.parking.api.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.parking.api.model.vo.ParkingSlot;

public interface ParkingApiDao {
	
	List<ParkingSlot> selectParkingSlotList(SqlSessionTemplate session);

}

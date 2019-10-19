package com.parking.api.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.api.dao.ParkingApiDao;
import com.parking.api.dao.ParkingApiDaoImpl;
import com.parking.api.model.vo.Coupon;
import com.parking.api.model.vo.Parking;
import com.parking.api.model.vo.ParkingOwner;
import com.parking.api.model.vo.ParkingSeoul;
import com.parking.api.model.vo.ParkingSlot;
import com.parking.common.api.ParseJsonSeoulParking;
import com.sun.org.apache.bcel.internal.generic.CPInstruction;


@Service
public class ParkingApiServiceImpl implements ParkingApiService {
	
	
	@Autowired
	ParkingApiDao dao;
	
	@Autowired
	SqlSessionTemplate session;
	
	
	public List<Parking> selectParkingList(String addrName)
	{
		ParseJsonSeoulParking seoulParking = new ParseJsonSeoulParking();
		List<Parking> list = new ArrayList<Parking>();
		// 할당될 페이지 수 cnt 1이고 뽑는 데이터가 1000개면 포문 한번돌고
		// 몇개를 뽑든간에 그 데이터에 관해 카운트로 for문을 돌린다
		// 추출해야 데이터가 많은량이면 카운트 수를 높인다.
		int cnt = 2;
		list = seoulParking.parseJsonSeoulParkingApi(addrName, cnt);
		
		return list;
	}
	
	@Override
	public List<ParkingSlot> selectParkingSlotList() {
		
		return dao.selectParkingSlotList(session);
	}
	
	
	// owner 테이블 
	@Override
	public int insertparkingOwner(List<Parking> list) {
		// TODO Auto-generated method stub
		int result = 0;
		int count = 0;
		ParkingOwner p = null;
		for(int i = 0; i < list.size(); i++)
		{					
			p = new ParkingOwner();
			p.setOwnerBusinessNo("p"+list.get(i).getParkingCode());
			p.setOwnerParkingCode(list.get(i).getParkingCode());
			
			dao.insertparkingOwner(session, p);
			count++;
			
		}
		
		

		System.out.println("count : "+  count);
		System.out.println("list size : "+ list.size());
		
		return 1;
	}
	
	@Override
	public int insertParkingSeoul(List<Parking> list) {
		int result = 0;
		int count = 0;
		ParkingSeoul p = null;
		
		for(int i = 0; i < list.size(); i++)
		{	
			p = new ParkingSeoul(list.get(i).getParkingCode(), list.get(i).getAddr(), list.get(i).getParkingName(), list.get(i).getLatitude(), list.get(i).getLongitude());
			
			dao.insertParkingSeoul(session, p);
			count++;
		}
		return 1;
	}
	
	
//	public List<ParkingSlot> selectParkingSlotList()
//	{
//		
//		List<ParkingSlot> list = dao.selectParkingSlotList(conn);
//		
//		
//		return list;
//
//	}
//	
//	public List<Coupon> selectCouponList()
//	{
//		
//		List<Coupon> list = dao.selectCouponList(conn);
//		
//		
//		return list;
//	}
//	
//	//쿠폰생성
//	public int insertCoupon(Coupon c)
//	{
//		
//		int result = dao.insertCoupon(conn,c);
//		
//		if(result> 0)
//			//commit(conn);
//		else
//			//rollback(conn);
//		
//		return result;
//	}
//	
//	public int insertParkingList(List list)
//	{
//		
//		int result = 0;
//		result = dao.insertParkingList(conn,list);
//		
// 
//		return result;
//	}
//	public int insertparkingOwner(List list)
//	{
//		
//		int result = 0;
//		result = dao.insertparkingOwner(conn,list);
//		
//		return result;
//	}
//	
//	public int insertParkingSlot(String pCode,String userCode,String beginT,String endT)
//	{
//		
//		int result = 0;
//		result = dao.insertParkingSlot(conn,pCode,userCode,beginT,endT);
//		
//		if(result > 0)
//		{
//			//commit(conn);
//		}else
//		{
//			
//		}
//		
//		
//		return result;
//	}
//	
//	public int insertParkingUserHistory(String pCode,String userCode,String ammount)
//	{
//		Connection conn = getConnection();
//		int result = 0;
//		result = dao.insertParkingUserHistory(conn,pCode,userCode,ammount);
//		
//		if(result > 0)
//		{
//			
//		}else
//		{
//			
//		}
//		
//		
//		return result;
//	}
//	
//	
//	
//	
//	public List<Parking> selectAutoCommit(String addrName)
//	{
//		
//		List<Parking> list = dao.selectAutoCommit(conn, addrName);
//		
//		
//		return list;
//	}
	

}

package com.parking.api.model.service;

import static com.parking.common.template.JDBCTemplate.close;
import static com.parking.common.template.JDBCTemplate.commit;
import static com.parking.common.template.JDBCTemplate.getConnection;
import static com.parking.common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import com.parking.api.dao.ParkingApiDao;
import com.parking.api.model.vo.Coupon;
import com.parking.api.model.vo.Parking;
import com.parking.api.model.vo.ParkingSlot;
import com.parking.common.api.ParseJsonSeoulParking;
import com.sun.org.apache.bcel.internal.generic.CPInstruction;

public class ParkingApiService {
	
	ParkingApiDao dao = new ParkingApiDao();
	
	
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
	public List<ParkingSlot> selectParkingSlotList()
	{
		Connection conn = getConnection();
		List<ParkingSlot> list = dao.selectParkingSlotList(conn);
		close(conn);
		
		return list;

	}
	
	public List<Coupon> selectCouponList()
	{
		Connection conn = getConnection();
		List<Coupon> list = dao.selectCouponList(conn);
		close(conn);
		
		return list;
	}
	
	//쿠폰생성
	public int insertCoupon(Coupon c)
	{
		Connection conn = getConnection();
		int result = dao.insertCoupon(conn,c);
		
		if(result> 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}
	
	public int insertParkingList(List list)
	{
		Connection conn = getConnection();
		int result = 0;
		result = dao.insertParkingList(conn,list);
		if (result > 0)
		     commit(conn);
		   else
		     rollback(conn);
		    
	    close(conn);
		    
		    
		return result;
	}
	public int insertparkingOwner(List list)
	{
		Connection conn = getConnection();
		int result = 0;
		result = dao.insertparkingOwner(conn,list);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		return result;
	}
	
	public int insertParkingSlot(String pCode,String userCode,String beginT,String endT)
	{
		Connection conn = getConnection();
		int result = 0;
		result = dao.insertParkingSlot(conn,pCode,userCode,beginT,endT);
		
		if(result > 0)
		{
			commit(conn);
		}else
		{
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int insertParkingUserHistory(String pCode,String userCode,String ammount)
	{
		Connection conn = getConnection();
		int result = 0;
		result = dao.insertParkingUserHistory(conn,pCode,userCode,ammount);
		
		if(result > 0)
		{
			commit(conn);
		}else
		{
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	
	
	
	public List<Parking> selectAutoCommit(String addrName)
	{
		Connection conn = getConnection();
		List<Parking> list = dao.selectAutoCommit(conn, addrName);
		close(conn);
		
		return list;
	}
	

}

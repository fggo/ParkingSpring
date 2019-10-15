package com.parking.api.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.parking.api.model.vo.Coupon;
import com.parking.api.model.vo.Parking;
import com.parking.api.model.vo.ParkingSlot;
import com.parking.member.model.vo.Member;

public class ParkingApiDao {
	
	private Properties prop = new Properties();
	
	public ParkingApiDao() {
		String path = ParkingApiDao.class.getResource("/sql/api/api-query.properties").getPath();
		try {
		      prop.load(new FileReader(path));
		    } catch(IOException e) {
		      e.printStackTrace();
		    }
		}
	
	
	public int insertParkingList(Connection conn, List list) {
	    PreparedStatement pstmt = null;
	    int result = 0;
	    String sql = prop.getProperty("insertparking_seoul");
	    
	    for(int i =0; i< list.size(); i++) {
	   
		    try {
	
		    	 pstmt = conn.prepareStatement(sql);
		    	  
		    	  pstmt.setString(1, ((Parking)list.get(i)).getParkingCode());
		    	  pstmt.setString(2, ((Parking)list.get(i)).getAddr());
		    	  pstmt.setString(3, ((Parking)list.get(i)).getParkingName());
		    	  pstmt.setDouble(4, ((Parking)list.get(i)).getLatitude());
		    	  pstmt.setDouble(5, ((Parking)list.get(i)).getLongitude());
		    	  result = pstmt.executeUpdate();
    		} 
	    	catch(SQLException e) 
		    {
		    
		      e.printStackTrace();
		    } finally {
		      close(pstmt);
		    }
	    }
	    return result; 
	  }
	
	
	
	public int insertparkingOwner(Connection conn, List list) {
	    PreparedStatement pstmt = null;
	    int result = 0;
	    String sql = prop.getProperty("insertparkingOwner");
	    
	    for(int i =0; i< list.size(); i++) {
	   
		    try {
	
		    	 pstmt = conn.prepareStatement(sql);
		    	  
		    	  pstmt.setString(1, "p"+((Parking)list.get(i)).getParkingCode());
		    	  pstmt.setString(2, ((Parking)list.get(i)).getParkingCode());
		    	  result = pstmt.executeUpdate();
    		} 
	    	catch(SQLException e) 
		    {
		    
		      e.printStackTrace();
		    } finally {
		      close(pstmt);
		    }
	    }
	    return result; 
	  }
	
	
	
	
	
	
	
	public int insertParkingSlot(Connection conn, String pCode,String userCode,String beginT,String endT)
	{
		PreparedStatement pstmt = null;
	    int result = 0;
	    String sql = prop.getProperty("insertparking_slot");
	    try
	    {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setString(1, "p"+pCode);
	    	pstmt.setString(2, userCode);
	    	pstmt.setString(3, beginT);
	    	pstmt.setString(4, endT);
	    	result = pstmt.executeUpdate();
	    }catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }finally
	    {
	    	close(pstmt);
	    }
	    return result;
	}
	
	
	public int insertParkingUserHistory(Connection conn, String pCode, String userCode,String ammount)
	{
		PreparedStatement pstmt = null;
	    int result = 0;
	    String sql = prop.getProperty("insertParkingUserHistory");
	    try
	    {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setString(1, userCode);
	    	pstmt.setString(2, pCode);
	    	pstmt.setString(3, ammount);
	    	result = pstmt.executeUpdate();
	    }catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }finally
	    {
	    	close(pstmt);
	    }
	    return result;
	}
	
	public int insertCoupon(Connection conn, Coupon c)
	{
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertCoupon");
				   
		    try {
		    	 pstmt = conn.prepareStatement(sql);
		    	  
		    	  pstmt.setString(1,c.getCouponCode());
		    	  pstmt.setString(2,c.getUserCode());
		    	  pstmt.setInt(3,c.getDiscountRate());
		    	  pstmt.setInt(4,c.getDuration());
		    	  pstmt.setInt(5,c.getExpiredYn());
		    	  result = pstmt.executeUpdate();
    		} 
	    	catch(SQLException e) 
		    {		    
		      e.printStackTrace();
		    } finally {
		      close(pstmt);
		    }
    
	    return result; 
	}
	
	
	public List<Parking> selectAutoCommit(Connection conn, String addrName)
	{
		PreparedStatement pstmt=null;      
		ResultSet rs=null;
		List<Parking> list = new ArrayList();
		Parking p = null;
		String sql=prop.getProperty("selectListView");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+addrName+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				p = new Parking();
				p.setAddr(rs.getString("psaddr"));
				p.setParkingName(rs.getString("psname"));
				list.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<ParkingSlot> selectParkingSlotList(Connection conn)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ParkingSlot> list = new ArrayList<ParkingSlot>();
		ParkingSlot ps = null;
		String sql = prop.getProperty("selectParkingSlot");
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ps = new ParkingSlot();
				ps.setOwnerNo(rs.getString("ownerbusinessno"));
				ps.setOwnerParkingCode(rs.getString("ownerparkingcode"));
				ps.setSlotUserCode(rs.getString("slotusercode"));
				ps.setSlotBeginTime(rs.getString("slotbegintime"));
				ps.setSlotEndTime(rs.getString("slotendtime"));
				list.add(ps);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	public List<Coupon> selectCouponList(Connection conn)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Coupon> list = new ArrayList<Coupon>();
		Coupon c = null;
		String sql = prop.getProperty("selectCouponList");
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				c = new Coupon();
				c.setCouponCode(rs.getString("couponcode"));
				c.setUserCode(rs.getString("couponusercode"));
				c.setDiscountRate(rs.getInt("coupondiscountrate"));
				c.setDuration(rs.getInt("couponduration"));
				c.setExpiredYn(rs.getInt("couponexpiredyn"));
				list.add(c);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
	
	
	
}

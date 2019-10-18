package com.parking.common.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.parking.api.model.vo.Parking;

public class ParseJsonSeoulParking {
	Properties prop = null;

	public ParseJsonSeoulParking() {
		prop = new Properties();
		
		try
		{
			prop.load(new FileReader(ParseJsonSeoulParking.class.getResource("config.properties").getPath()));
		}catch(IOException e) 
		{
			e.printStackTrace();
		}
	}

	public List<Parking> parseJsonSeoulParkingApi(String addrName, int cnt)
	{
		
		

		List<Parking> list = new ArrayList<Parking>();
		String serviceKey = prop.getProperty("serviceKey");
		JSONObject obj = null;
		Parking p = null;
		String urlStr= null;
		JSONArray arr = null;

		try {
			//카운트 만큼 1~부터 시작해서 카운트 수만큼 반복돌기
			for(int count = 1; count <= cnt; count++) {
				int start_index = 1;
				int end_index = 1000;

				if(count > 1)
				{
					urlStr = "http://openapi.seoul.go.kr:8088/" + serviceKey 
							+ "/json/GetParkInfo/"+((end_index*(count-1))+1) +"/"+end_index*count+"/"+addrName;
				}else
				{
					urlStr = "http://openapi.seoul.go.kr:8088/" + serviceKey 
							+ "/json/GetParkInfo/"+start_index +"/"+end_index+"/"+addrName;
				}


				BufferedReader br = null;
				URL url = new URL(urlStr);
				HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
				urlconnection.setRequestMethod("GET");
				br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

				String result = "";
				String line;
				while((line = br.readLine()) != null) {
					result += (line + "\n");
				}

				//System.out.println(result);
				obj = new JSONObject(result);
				try {
				  arr = obj.getJSONObject("GetParkInfo").getJSONArray("row");
				} catch(JSONException e) {
				  e.printStackTrace();
				  System.out.println("인덱스 /" + start_index +"/"+end_index + "/ 에 해당하는 데이터가 없습니다....");
				  arr=null;
				}

				//System.out.println("Parsing List Num # = " + arr.length());

				if(count == 1)
				{

					for(int i = 0; i < arr.length(); i++) 
					{
						if(i==0)
						{
							p = new Parking();
							p.setParkingCode(arr.getJSONObject(i).getString("PARKING_CODE"));
							p.setParkingName(arr.getJSONObject(i).getString("PARKING_NAME"));
							p.setAddr(arr.getJSONObject(i).getString("ADDR"));
							p.setParkingType(arr.getJSONObject(i).getString("PARKING_TYPE"));
							p.setParkingTypeNm(arr.getJSONObject(i).getString("PARKING_TYPE_NM"));
							p.setOperationRule(arr.getJSONObject(i).getInt("OPERATION_RULE"));
							p.setOperationRuleNm(arr.getJSONObject(i).getString("OPERATION_RULE_NM"));
							p.setTel(arr.getJSONObject(i).getString("TEL"));
							p.setQueStatus(arr.getJSONObject(i).getInt("QUE_STATUS"));
							p.setQueStatusNm(arr.getJSONObject(i).getString("QUE_STATUS_NM"));
							p.setCapacity(arr.getJSONObject(i).getInt("CAPACITY"));
							p.setCurParking(arr.getJSONObject(i).getInt("CUR_PARKING"));
							
							p.setCurParkingTime(arr.getJSONObject(i).getString("CUR_PARKING_TIME"));
							p.setPayYn(arr.getJSONObject(i).getString("PAY_YN"));
							p.setPayNm(arr.getJSONObject(i).getString("PAY_NM"));
							p.setNightFreeOpen(arr.getJSONObject(i).getString("NIGHT_FREE_OPEN"));
							p.setNightFreeOpenNm(arr.getJSONObject(i).getString("NIGHT_FREE_OPEN_NM"));
							p.setWeekdayBeginTime(arr.getJSONObject(i).getString("WEEKDAY_BEGIN_TIME"));
							p.setWeekdayEndTime(arr.getJSONObject(i).getString("WEEKDAY_END_TIME"));
							p.setWeekendBeginTime(arr.getJSONObject(i).getString("WEEKEND_BEGIN_TIME"));
							p.setWeekendEndTime(arr.getJSONObject(i).getString("WEEKEND_END_TIME"));
							p.setHolidayBeginTime(arr.getJSONObject(i).getString("HOLIDAY_BEGIN_TIME"));
							p.setHolidayEndTime(arr.getJSONObject(i).getString("HOLIDAY_END_TIME"));
							p.setSyncTime(arr.getJSONObject(i).getString("SYNC_TIME"));
							p.setSaturdayPayYn(arr.getJSONObject(i).getString("SATURDAY_PAY_YN"));
							p.setSaturdayPayNm(arr.getJSONObject(i).getString("SATURDAY_PAY_NM"));
							p.setHolidayPayNm(arr.getJSONObject(i).getString("HOLIDAY_PAY_YN"));
							p.setHolidayPayYn(arr.getJSONObject(i).getString("HOLIDAY_PAY_NM"));
							p.setFulltimeMonthly(arr.getJSONObject(i).getString("FULLTIME_MONTHLY"));
							p.setGrpParkNm(arr.getJSONObject(i).getString("GRP_PARKNM"));
							p.setRates(arr.getJSONObject(i).getInt("RATES"));
							p.setTimeRate(arr.getJSONObject(i).getInt("TIME_RATE"));
							p.setAddRates(arr.getJSONObject(i).getInt("ADD_RATES"));
							p.setAddTimeRate(arr.getJSONObject(i).getInt("ADD_TIME_RATE"));
							p.setBusRates(arr.getJSONObject(i).getInt("BUS_RATES"));
							p.setBusTimeRate(arr.getJSONObject(i).getInt("BUS_TIME_RATE"));
							p.setBusAddTimeRate(arr.getJSONObject(i).getInt("BUS_ADD_TIME_RATE"));
							p.setBusAddRate(arr.getJSONObject(i).getInt("BUS_ADD_RATES"));
							p.setDayMaximum(arr.getJSONObject(i).getInt("DAY_MAXIMUM"));
							p.setLatitude(arr.getJSONObject(i).getDouble("LAT"));
							p.setLongitude(arr.getJSONObject(i).getDouble("LNG"));
							p.setAssignCode(arr.getJSONObject(i).getString("ASSIGN_CODE"));
							p.setAssignCodeNm(arr.getJSONObject(i).getString("ASSIGN_CODE_NM"));

							list.add(p);
						}
						else
						{
							p = new Parking();
							p.setParkingCode(arr.getJSONObject(i).getString("PARKING_CODE"));
							p.setParkingName(arr.getJSONObject(i).getString("PARKING_NAME"));
							p.setAddr(arr.getJSONObject(i).getString("ADDR"));
							p.setParkingType(arr.getJSONObject(i).getString("PARKING_TYPE"));
							p.setParkingTypeNm(arr.getJSONObject(i).getString("PARKING_TYPE_NM"));
							p.setOperationRule(arr.getJSONObject(i).getInt("OPERATION_RULE"));
							p.setOperationRuleNm(arr.getJSONObject(i).getString("OPERATION_RULE_NM"));
							p.setTel(arr.getJSONObject(i).getString("TEL"));
							p.setQueStatus(arr.getJSONObject(i).getInt("QUE_STATUS"));
							p.setQueStatusNm(arr.getJSONObject(i).getString("QUE_STATUS_NM"));
							p.setCapacity(arr.getJSONObject(i).getInt("CAPACITY"));
							p.setCurParking(arr.getJSONObject(i).getInt("CUR_PARKING"));
							p.setCurParkingTime(arr.getJSONObject(i).getString("CUR_PARKING_TIME"));
							p.setPayYn(arr.getJSONObject(i).getString("PAY_YN"));
							p.setPayNm(arr.getJSONObject(i).getString("PAY_NM"));
							p.setNightFreeOpen(arr.getJSONObject(i).getString("NIGHT_FREE_OPEN"));
							p.setNightFreeOpenNm(arr.getJSONObject(i).getString("NIGHT_FREE_OPEN_NM"));
							p.setWeekdayBeginTime(arr.getJSONObject(i).getString("WEEKDAY_BEGIN_TIME"));
							p.setWeekdayEndTime(arr.getJSONObject(i).getString("WEEKDAY_END_TIME"));
							p.setWeekendBeginTime(arr.getJSONObject(i).getString("WEEKEND_BEGIN_TIME"));
							p.setWeekendEndTime(arr.getJSONObject(i).getString("WEEKEND_END_TIME"));
							p.setHolidayBeginTime(arr.getJSONObject(i).getString("HOLIDAY_BEGIN_TIME"));
							p.setHolidayEndTime(arr.getJSONObject(i).getString("HOLIDAY_END_TIME"));
							p.setSyncTime(arr.getJSONObject(i).getString("SYNC_TIME"));
							p.setSaturdayPayYn(arr.getJSONObject(i).getString("SATURDAY_PAY_YN"));
							p.setSaturdayPayNm(arr.getJSONObject(i).getString("SATURDAY_PAY_NM"));
							p.setHolidayPayNm(arr.getJSONObject(i).getString("HOLIDAY_PAY_YN"));
							p.setHolidayPayYn(arr.getJSONObject(i).getString("HOLIDAY_PAY_NM"));
							p.setFulltimeMonthly(arr.getJSONObject(i).getString("FULLTIME_MONTHLY"));
							p.setGrpParkNm(arr.getJSONObject(i).getString("GRP_PARKNM"));
							p.setRates(arr.getJSONObject(i).getInt("RATES"));
							p.setTimeRate(arr.getJSONObject(i).getInt("TIME_RATE"));
							p.setAddRates(arr.getJSONObject(i).getInt("ADD_RATES"));
							p.setAddTimeRate(arr.getJSONObject(i).getInt("ADD_TIME_RATE"));
							p.setBusRates(arr.getJSONObject(i).getInt("BUS_RATES"));
							p.setBusTimeRate(arr.getJSONObject(i).getInt("BUS_TIME_RATE"));
							p.setBusAddTimeRate(arr.getJSONObject(i).getInt("BUS_ADD_TIME_RATE"));
							p.setBusAddRate(arr.getJSONObject(i).getInt("BUS_ADD_RATES"));
							p.setDayMaximum(arr.getJSONObject(i).getInt("DAY_MAXIMUM"));
							p.setLatitude(arr.getJSONObject(i).getDouble("LAT"));
							p.setLongitude(arr.getJSONObject(i).getDouble("LNG"));
							p.setAssignCode(arr.getJSONObject(i).getString("ASSIGN_CODE"));
							p.setAssignCodeNm(arr.getJSONObject(i).getString("ASSIGN_CODE_NM"));

							System.out.println(p);
							if(p.getParkingName().equals(list.get(list.size()-1).getParkingName()))
							{
								p=null;

							}else
							{
								list.add(p);
							}
						}
					}
				}
				else
				{					
				  if(arr != null && arr.length() >0) {
				    int number = 0;
				    for(int i = end_index; i < end_index+arr.length(); i++) 
				    {
				      
				      p = new Parking();
				      p.setParkingCode(arr.getJSONObject(i-end_index).getString("PARKING_CODE"));
				      p.setParkingName(arr.getJSONObject(i-end_index).getString("PARKING_NAME"));
				      p.setAddr(arr.getJSONObject(i-end_index).getString("ADDR"));
				      p.setParkingType(arr.getJSONObject(i-end_index).getString("PARKING_TYPE"));
				      p.setParkingTypeNm(arr.getJSONObject(i-end_index).getString("PARKING_TYPE_NM"));
				      p.setOperationRule(arr.getJSONObject(i-end_index).getInt("OPERATION_RULE"));
				      p.setOperationRuleNm(arr.getJSONObject(i-end_index).getString("OPERATION_RULE_NM"));
				      p.setTel(arr.getJSONObject(i-end_index).getString("TEL"));
				      p.setQueStatus(arr.getJSONObject(i-end_index).getInt("QUE_STATUS"));
				      p.setQueStatusNm(arr.getJSONObject(i-end_index).getString("QUE_STATUS_NM"));
				      p.setCapacity(arr.getJSONObject(i-end_index).getInt("CAPACITY"));
				      p.setCurParking(arr.getJSONObject(i-end_index).getInt("CUR_PARKING"));
				      p.setCurParkingTime(arr.getJSONObject(i-end_index).getString("CUR_PARKING_TIME"));
				      p.setPayYn(arr.getJSONObject(i-end_index).getString("PAY_YN"));
				      p.setPayNm(arr.getJSONObject(i-end_index).getString("PAY_NM"));
				      p.setNightFreeOpen(arr.getJSONObject(i-end_index).getString("NIGHT_FREE_OPEN"));
				      p.setNightFreeOpenNm(arr.getJSONObject(i-end_index).getString("NIGHT_FREE_OPEN_NM"));
				      p.setWeekdayBeginTime(arr.getJSONObject(i-end_index).getString("WEEKDAY_BEGIN_TIME"));
				      p.setWeekdayEndTime(arr.getJSONObject(i-end_index).getString("WEEKDAY_END_TIME"));
				      p.setWeekendBeginTime(arr.getJSONObject(i-end_index).getString("WEEKEND_BEGIN_TIME"));
				      p.setWeekendEndTime(arr.getJSONObject(i-end_index).getString("WEEKEND_END_TIME"));
				      p.setHolidayBeginTime(arr.getJSONObject(i-end_index).getString("HOLIDAY_BEGIN_TIME"));
				      p.setHolidayEndTime(arr.getJSONObject(i-end_index).getString("HOLIDAY_END_TIME"));
				      p.setSyncTime(arr.getJSONObject(i-end_index).getString("SYNC_TIME"));
				      p.setSaturdayPayYn(arr.getJSONObject(i-end_index).getString("SATURDAY_PAY_YN"));
				      p.setSaturdayPayNm(arr.getJSONObject(i-end_index).getString("SATURDAY_PAY_NM"));
				      p.setHolidayPayNm(arr.getJSONObject(i-end_index).getString("HOLIDAY_PAY_YN"));
				      p.setHolidayPayYn(arr.getJSONObject(i-end_index).getString("HOLIDAY_PAY_NM"));
				      p.setFulltimeMonthly(arr.getJSONObject(i-end_index).getString("FULLTIME_MONTHLY"));
				      p.setGrpParkNm(arr.getJSONObject(i-end_index).getString("GRP_PARKNM"));
				      p.setRates(arr.getJSONObject(i-end_index).getInt("RATES"));
				      p.setTimeRate(arr.getJSONObject(i-end_index).getInt("TIME_RATE"));
				      p.setAddRates(arr.getJSONObject(i-end_index).getInt("ADD_RATES"));
				      p.setAddTimeRate(arr.getJSONObject(i-end_index).getInt("ADD_TIME_RATE"));
				      p.setBusRates(arr.getJSONObject(i-end_index).getInt("BUS_RATES"));
				      p.setBusTimeRate(arr.getJSONObject(i-end_index).getInt("BUS_TIME_RATE"));
				      p.setBusAddTimeRate(arr.getJSONObject(i-end_index).getInt("BUS_ADD_TIME_RATE"));
				      p.setBusAddRate(arr.getJSONObject(i-end_index).getInt("BUS_ADD_RATES"));
				      p.setDayMaximum(arr.getJSONObject(i-end_index).getInt("DAY_MAXIMUM"));
				      p.setLatitude(arr.getJSONObject(i-end_index).getDouble("LAT"));
				      p.setLongitude(arr.getJSONObject(i-end_index).getDouble("LNG"));
				      p.setAssignCode(arr.getJSONObject(i-end_index).getString("ASSIGN_CODE"));
				      p.setAssignCodeNm(arr.getJSONObject(i-end_index).getString("ASSIGN_CODE_NM"));
				      
				      
//						if(p.getParkingName().equals(list.get(list.size()-1).getParkingName()))
				      if(list.contains(p))
				      {
				        
				        p = null;
				        
				        
				      }else
				      {							
				        list.add(p);				
				      }	
				    }
				    
				  }
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getLatitude() == 0 || list.get(i).getLongitude() == 0)
			{
				list.remove(i);
			}
//			System.out.println(list.get(i));
		}
//		System.out.println(list.size());
		
		
		

		return list;
	}


}

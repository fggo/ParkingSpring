package com.parking.api.model.vo;

import java.util.Date;

public class Parking {
	private String parkingCode; 		// 주차장 코드
	private String parkingName; 		// 주차장명
	private String addr; 				// 주소
	private String parkingType;			// 주차장종류 
	private String parkingTypeNm;		// 주차장 종류명
	private int operationRule;			// 주차장 운영구분
	private String operationRuleNm;		// 운영 구분
	private String tel;					// 전화번호
	private int queStatus;				// 주차현황 정보 제공여부
	private String queStatusNm;			// 주차현황 정보 제공여부
	private int capacity;				// 주차 면(주차 가능 차량 수)
	private int curParking;				// 현재 주차중인 대수
	private String curParkingTime; 		// 현재 주차 차량 업데이트 시간
	private	String payYn; 				// 유무료 구분
	private String payNm;				// 유무료 구분명
	private String nightFreeOpen;		// 야간무료 개방여부
	private String nightFreeOpenNm; 	// 야간무료 개방여부명
	private String weekdayBeginTime;	// 평일운영시작시각(hhmm)
	private String weekdayEndTime;		// 평일 운영종료시각(hhmm)
	private String weekendBeginTime;	// 주말운영시작시각(hhmm)
	private String weekendEndTime;		// 주말운영종료시각(hhmm)
	private String holidayBeginTime;	// 공휴일 운영시작시각
	private String holidayEndTime;		// 공휴일 운영종료시각
	private String syncTime;			// 최종데이터 동기화 시각
	private String saturdayPayYn;		// 토요일 유 무료 구분
	private String saturdayPayNm;		// 토요일 유 무료 구분명
	private String holidayPayYn;		// 공휴일 유 무료 구분
	private String holidayPayNm;		// 공휴일 유 무료 구분명
	private String fulltimeMonthly;		// 월 정기권 금액
	private String grpParkNm;				// 노상 주차장 관리그룹번호
	private int rates;					// 기본 주차 요금
	private int timeRate;				// 기본주차 시간(분 단위)
	private int addRates; 				// 추가 단위 요금
	private int addTimeRate;			// 추가 단위 요금
	private int busRates;				// 버스 기본 주차 요금
	private int busTimeRate;			// 버스 기본 주차 시간(분 단위)
	private int busAddTimeRate;			// 버스 추가 단위 시간(분 단위)
	private int busAddRate;				// 버스 추가 단위 요금
	private int dayMaximum;				// 일최대요금
	private double latitude; 			// 위도 
	private double longitude; 			// 경도longitude
	private String assignCode;			// 배정코드
	private String assignCodeNm;		// 배정코드명
	
	
	
	
	private String insttNm;
	
	
	//https://data.seoul.go.kr/dataList/datasetView.do?infId=OA-13122&srvType=S&serviceKind=1&currentPageNo=1
	
	public Parking() {
		// TODO Auto-generated constructor stub
	}
	
	// 초기에 만들어진 덤프 생성자
	public Parking(String parkingCode, String parkingName, String addr, String parkingType, String parkingTypeNm,
			int operationRule, String operationRuleNm, String tel, int queStatus, String queStatusNm, int capacity,
			int curParking) {
		super();
		this.parkingCode = parkingCode;
		this.parkingName = parkingName;
		this.addr = addr;
		this.parkingType = parkingType;
		this.parkingTypeNm = parkingTypeNm;
		this.operationRule = operationRule;
		this.operationRuleNm = operationRuleNm;
		this.tel = tel;
		this.queStatus = queStatus;
		this.queStatusNm = queStatusNm;
		this.capacity = capacity;
		this.curParking = curParking;
	}
	
	
	
	
	

	
	
public Parking(String parkingCode, String parkingName, String addr, String parkingType, String parkingTypeNm,
			int operationRule, String operationRuleNm, String tel, int queStatus, String queStatusNm, int capacity,
			int curParking, String curParkingTime, String payYn, String payNm, String nightFreeOpen,
			String nightFreeOpenNm, String weekdayBeginTime, String weekdayEndTime, String weekendBeginTime,
			String weekendEndTime, String holidayBeginTime, String holidayEndTime, String syncTime,
			String saturdayPayYn, String saturdayPayNm, String holidayPayYn, String holidayPayNm, String fulltimeMonthly,
			String grpParkNm, int rates, int timeRate, int addRates, int addTimeRate, int busRates, int busTimeRate,
			int busAddTimeRate, int busAddRate, int dayMaximum, double latitude, double longitude, String assignCode,
			String assignCodeNm, String insttNm) {
		super();
		this.parkingCode = parkingCode;
		this.parkingName = parkingName;
		this.addr = addr;
		this.parkingType = parkingType;
		this.parkingTypeNm = parkingTypeNm;
		this.operationRule = operationRule;
		this.operationRuleNm = operationRuleNm;
		this.tel = tel;
		this.queStatus = queStatus;
		this.queStatusNm = queStatusNm;
		this.capacity = capacity;
		this.curParking = curParking;
		this.curParkingTime = curParkingTime;
		this.payYn = payYn;
		this.payNm = payNm;
		this.nightFreeOpen = nightFreeOpen;
		this.nightFreeOpenNm = nightFreeOpenNm;
		this.weekdayBeginTime = weekdayBeginTime;
		this.weekdayEndTime = weekdayEndTime;
		this.weekendBeginTime = weekendBeginTime;
		this.weekendEndTime = weekendEndTime;
		this.holidayBeginTime = holidayBeginTime;
		this.holidayEndTime = holidayEndTime;
		this.syncTime = syncTime;
		this.saturdayPayYn = saturdayPayYn;
		this.saturdayPayNm = saturdayPayNm;
		this.holidayPayYn = holidayPayYn;
		this.holidayPayNm = holidayPayNm;
		this.fulltimeMonthly = fulltimeMonthly;
		this.grpParkNm = grpParkNm;
		this.rates = rates;
		this.timeRate = timeRate;
		this.addRates = addRates;
		this.addTimeRate = addTimeRate;
		this.busRates = busRates;
		this.busTimeRate = busTimeRate;
		this.busAddTimeRate = busAddTimeRate;
		this.busAddRate = busAddRate;
		this.dayMaximum = dayMaximum;
		this.latitude = latitude;
		this.longitude = longitude;
		this.assignCode = assignCode;
		this.assignCodeNm = assignCodeNm;
		this.insttNm = insttNm;
	}



//				<CUR_PARKING_TIME>2019-07-14 22:56:50</CUR_PARKING_TIME>
//				<PAY_YN>N</PAY_YN>
//				<PAY_NM>무료</PAY_NM>
//				<NIGHT_FREE_OPEN>N</NIGHT_FREE_OPEN>
//				<NIGHT_FREE_OPEN_NM>야간 미개방</NIGHT_FREE_OPEN_NM>
//				<WEEKDAY_BEGIN_TIME>0000</WEEKDAY_BEGIN_TIME>
//				<WEEKDAY_END_TIME>0000</WEEKDAY_END_TIME>
//				<WEEKEND_BEGIN_TIME>0000</WEEKEND_BEGIN_TIME>
//				<WEEKEND_END_TIME>0000</WEEKEND_END_TIME>
//				<HOLIDAY_BEGIN_TIME>0000</HOLIDAY_BEGIN_TIME>
//				<HOLIDAY_END_TIME>0000</HOLIDAY_END_TIME>
//				<SYNC_TIME>2019-06-10 10:01:23</SYNC_TIME>
//				<SATURDAY_PAY_YN>Y</SATURDAY_PAY_YN>
//				<SATURDAY_PAY_NM>유료</SATURDAY_PAY_NM>
//				<HOLIDAY_PAY_YN>Y</HOLIDAY_PAY_YN>
//				<HOLIDAY_PAY_NM>유료</HOLIDAY_PAY_NM>
//				<FULLTIME_MONTHLY>0</FULLTIME_MONTHLY>
//				<GRP_PARKNM>1111013800003</GRP_PARKNM>
//				<RATES>0</RATES>
//				<TIME_RATE>0</TIME_RATE>
//				<ADD_RATES>0</ADD_RATES>
//				<ADD_TIME_RATE>0</ADD_TIME_RATE>
//				<BUS_RATES>0</BUS_RATES>
//				<BUS_TIME_RATE>0</BUS_TIME_RATE>
//				<BUS_ADD_TIME_RATE>0</BUS_ADD_TIME_RATE>
//				<BUS_ADD_RATES>0</BUS_ADD_RATES>
//				<DAY_MAXIMUM>0</DAY_MAXIMUM>
//				<LAT>37.57134087</LAT>
//				<LNG>126.98776281</LNG>
//				<ASSIGN_CODE>PIS02</ASSIGN_CODE>
//				<ASSIGN_CODE_NM>미배정,미점유</ASSIGN_CODE_NM>
	
	
  @Override
  public boolean equals(Object obj) {
    Parking p = (Parking)obj;
    return this.getParkingCode().equals(p.getParkingCode());
  }

	public String getParkingCode() {
	return parkingCode;
}

public void setParkingCode(String parkingCode) {
	this.parkingCode = parkingCode;
}

public String getParkingName() {
	return parkingName;
}

public void setParkingName(String parkingName) {
	this.parkingName = parkingName;
}

public String getAddr() {
	return addr;
}

public void setAddr(String addr) {
	this.addr = addr;
}

public String getParkingType() {
	return parkingType;
}

public void setParkingType(String parkingType) {
	this.parkingType = parkingType;
}

public String getParkingTypeNm() {
	return parkingTypeNm;
}

public void setParkingTypeNm(String parkingTypeNm) {
	this.parkingTypeNm = parkingTypeNm;
}

public int getOperationRule() {
	return operationRule;
}

public void setOperationRule(int operationRule) {
	this.operationRule = operationRule;
}

public String getOperationRuleNm() {
	return operationRuleNm;
}

public void setOperationRuleNm(String operationRuleNm) {
	this.operationRuleNm = operationRuleNm;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public int getQueStatus() {
	return queStatus;
}

public void setQueStatus(int queStatus) {
	this.queStatus = queStatus;
}

public String getQueStatusNm() {
	return queStatusNm;
}

public void setQueStatusNm(String queStatusNm) {
	this.queStatusNm = queStatusNm;
}

public int getCapacity() {
	return capacity;
}

public void setCapacity(int capacity) {
	this.capacity = capacity;
}

public int getCurParking() {
	return curParking;
}

public void setCurParking(int curParking) {
	this.curParking = curParking;
}

public String getCurParkingTime() {
	return curParkingTime;
}

public void setCurParkingTime(String curParkingTime) {
	this.curParkingTime = curParkingTime;
}

public String getPayYn() {
	return payYn;
}

public void setPayYn(String payYn) {
	this.payYn = payYn;
}

public String getPayNm() {
	return payNm;
}

public void setPayNm(String payNm) {
	this.payNm = payNm;
}

public String getNightFreeOpen() {
	return nightFreeOpen;
}

public void setNightFreeOpen(String nightFreeOpen) {
	this.nightFreeOpen = nightFreeOpen;
}

public String getNightFreeOpenNm() {
	return nightFreeOpenNm;
}

public void setNightFreeOpenNm(String nightFreeOpenNm) {
	this.nightFreeOpenNm = nightFreeOpenNm;
}

public String getWeekdayBeginTime() {
	return weekdayBeginTime;
}

public void setWeekdayBeginTime(String weekdayBeginTime) {
	this.weekdayBeginTime = weekdayBeginTime;
}

public String getWeekdayEndTime() {
	return weekdayEndTime;
}

public void setWeekdayEndTime(String weekdayEndTime) {
	this.weekdayEndTime = weekdayEndTime;
}

public String getWeekendBeginTime() {
	return weekendBeginTime;
}

public void setWeekendBeginTime(String weekendBeginTime) {
	this.weekendBeginTime = weekendBeginTime;
}

public String getWeekendEndTime() {
	return weekendEndTime;
}

public void setWeekendEndTime(String weekendEndTime) {
	this.weekendEndTime = weekendEndTime;
}

public String getHolidayBeginTime() {
	return holidayBeginTime;
}

public void setHolidayBeginTime(String holidayBeginTime) {
	this.holidayBeginTime = holidayBeginTime;
}

public String getHolidayEndTime() {
	return holidayEndTime;
}

public void setHolidayEndTime(String holidayEndTime) {
	this.holidayEndTime = holidayEndTime;
}

public String getSyncTime() {
	return syncTime;
}

public void setSyncTime(String syncTime) {
	this.syncTime = syncTime;
}

public String getSaturdayPayYn() {
	return saturdayPayYn;
}

public void setSaturdayPayYn(String saturdayPayYn) {
	this.saturdayPayYn = saturdayPayYn;
}

public String getSaturdayPayNm() {
	return saturdayPayNm;
}

public void setSaturdayPayNm(String saturdayPayNm) {
	this.saturdayPayNm = saturdayPayNm;
}

public String getHolidayPayYn() {
	return holidayPayYn;
}

public void setHolidayPayYn(String holidayPayYn) {
	this.holidayPayYn = holidayPayYn;
}

public String getHolidayPayNm() {
	return holidayPayNm;
}

public void setHolidayPayNm(String holidayPayNm) {
	this.holidayPayNm = holidayPayNm;
}

public String getFulltimeMonthly() {
	return fulltimeMonthly;
}

public void setFulltimeMonthly(String fulltimeMonthly) {
	this.fulltimeMonthly = fulltimeMonthly;
}

public String getGrpParkNm() {
	return grpParkNm;
}

public void setGrpParkNm(String grpParkNm) {
	this.grpParkNm = grpParkNm;
}

public int getRates() {
	return rates;
}

public void setRates(int rates) {
	this.rates = rates;
}

public int getTimeRate() {
	return timeRate;
}

public void setTimeRate(int timeRate) {
	this.timeRate = timeRate;
}

public int getAddRates() {
	return addRates;
}

public void setAddRates(int addRates) {
	this.addRates = addRates;
}

public int getAddTimeRate() {
	return addTimeRate;
}

public void setAddTimeRate(int addTimeRate) {
	this.addTimeRate = addTimeRate;
}

public int getBusRates() {
	return busRates;
}

public void setBusRates(int busRates) {
	this.busRates = busRates;
}

public int getBusTimeRate() {
	return busTimeRate;
}

public void setBusTimeRate(int busTimeRate) {
	this.busTimeRate = busTimeRate;
}

public int getBusAddTimeRate() {
	return busAddTimeRate;
}

public void setBusAddTimeRate(int busAddTimeRate) {
	this.busAddTimeRate = busAddTimeRate;
}

public int getBusAddRate() {
	return busAddRate;
}

public void setBusAddRate(int busAddRate) {
	this.busAddRate = busAddRate;
}

public int getDayMaximum() {
	return dayMaximum;
}

public void setDayMaximum(int dayMaximum) {
	this.dayMaximum = dayMaximum;
}

public double getLatitude() {
	return latitude;
}

public void setLatitude(double latitude) {
	this.latitude = latitude;
}

public double getLongitude() {
	return longitude;
}

public void setLongitude(double longitude) {
	this.longitude = longitude;
}

public String getAssignCode() {
	return assignCode;
}

public void setAssignCode(String assignCode) {
	this.assignCode = assignCode;
}

public String getAssignCodeNm() {
	return assignCodeNm;
}

public void setAssignCodeNm(String assignCodeNm) {
	this.assignCodeNm = assignCodeNm;
}

public String getInsttNm() {
	return insttNm;
}

public void setInsttNm(String insttNm) {
	this.insttNm = insttNm;
}

@Override
public String toString() {
	return "Parking [parkingCode=" + parkingCode + ", parkingName=" + parkingName + ", addr=" + addr + ", parkingType="
			+ parkingType + ", parkingTypeNm=" + parkingTypeNm + ", operationRule=" + operationRule
			+ ", operationRuleNm=" + operationRuleNm + ", tel=" + tel + ", queStatus=" + queStatus + ", queStatusNm="
			+ queStatusNm + ", capacity=" + capacity + ", curParking=" + curParking + ", curParkingTime="
			+ curParkingTime + ", payYn=" + payYn + ", payNm=" + payNm + ", nightFreeOpen=" + nightFreeOpen
			+ ", nightFreeOpenNm=" + nightFreeOpenNm + ", weekdayBeginTime=" + weekdayBeginTime + ", weekdayEndTime="
			+ weekdayEndTime + ", weekendBeginTime=" + weekendBeginTime + ", weekendEndTime=" + weekendEndTime
			+ ", holidayBeginTime=" + holidayBeginTime + ", holidayEndTime=" + holidayEndTime + ", syncTime=" + syncTime
			+ ", saturdayPayYn=" + saturdayPayYn + ", saturdayPayNm=" + saturdayPayNm + ", holidayPayYn=" + holidayPayYn
			+ ", holidayPayNm=" + holidayPayNm + ", fulltimeMonthly=" + fulltimeMonthly + ", grpParkNm=" + grpParkNm
			+ ", rates=" + rates + ", timeRate=" + timeRate + ", addRates=" + addRates + ", addTimeRate=" + addTimeRate
			+ ", busRates=" + busRates + ", busTimeRate=" + busTimeRate + ", busAddTimeRate=" + busAddTimeRate
			+ ", busAddRate=" + busAddRate + ", dayMaximum=" + dayMaximum + ", latitude=" + latitude + ", hardness="
			+ longitude + ", assignCode=" + assignCode + ", assignCodeNm=" + assignCodeNm + ", insttNm=" + insttNm + "]";
}



}

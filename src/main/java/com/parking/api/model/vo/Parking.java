package com.parking.api.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
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
	
	@Override
	  public boolean equals(Object obj) {
	    Parking p = (Parking)obj;
	    return this.getParkingCode().equals(p.getParkingCode());
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
	
}

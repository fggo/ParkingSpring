package com.parking.api.model.vo;

public class ParkingSlot {
	private String ownerNo;
	private String ownerParkingCode;
	private String slotUserCode;
	private String slotBeginTime;
	private String slotEndTime;
	
	public ParkingSlot() {
		// TODO Auto-generated constructor stub
	}

	public ParkingSlot(String ownerNo, String ownerParkingCode, String slotUserCode, String slotBeginTime,
			String slotEndTime) {
		super();
		this.ownerNo = ownerNo;
		this.ownerParkingCode = ownerParkingCode;
		this.slotUserCode = slotUserCode;
		this.slotBeginTime = slotBeginTime;
		this.slotEndTime = slotEndTime;
	}

	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public String getOwnerParkingCode() {
		return ownerParkingCode;
	}

	public void setOwnerParkingCode(String ownerParkingCode) {
		this.ownerParkingCode = ownerParkingCode;
	}

	public String getSlotUserCode() {
		return slotUserCode;
	}

	public void setSlotUserCode(String slotUserCode) {
		this.slotUserCode = slotUserCode;
	}

	public String getSlotBeginTime() {
		return slotBeginTime;
	}

	public void setSlotBeginTime(String slotBeginTime) {
		this.slotBeginTime = slotBeginTime;
	}

	public String getSlotEndTime() {
		return slotEndTime;
	}

	public void setSlotEndTime(String slotEndTime) {
		this.slotEndTime = slotEndTime;
	}

	@Override
	public String toString() {
		return "ParkingSlot [ownerNo=" + ownerNo + ", ownerParkingCode=" + ownerParkingCode + ", slotUserCode="
				+ slotUserCode + ", slotBeginTime=" + slotBeginTime + ", slotEndTime=" + slotEndTime + "]";
	}
	
}

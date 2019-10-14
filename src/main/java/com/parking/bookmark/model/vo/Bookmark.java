package com.parking.bookmark.model.vo;

public class Bookmark {
  private int bookmarkNo;
  private String bookmarkUserCode;
  private String bookmarkParkingCode;

  public Bookmark() {
    // TODO Auto-generated constructor stub
  }

  public Bookmark(int bookmarkNo, String bookmarkUserCode, String bookmarkParkingCode) {
    super();
    this.bookmarkNo = bookmarkNo;
    this.bookmarkUserCode = bookmarkUserCode;
    this.bookmarkParkingCode = bookmarkParkingCode;
  }
  
  @Override
  public String toString() {
    return "Bookmark [bookmarkNo=" + bookmarkNo + ", bookmarkUserCode=" + bookmarkUserCode + ", bookmarkParkingCode="
        + bookmarkParkingCode + "]";
  }

  @Override
  public boolean equals(Object b) {
    Bookmark cmp = (Bookmark)b;
    boolean userEquals = this.bookmarkUserCode.equals(cmp.getBookmarkUserCode());
    boolean parkingEquals = this.bookmarkParkingCode.equals(cmp.getBookmarkParkingCode());

    return userEquals && parkingEquals;
  }

  public int getBookmarkNo() { return bookmarkNo; }
  public void setBookmarkNo(int bookmarkNo) { this.bookmarkNo = bookmarkNo; }
  public String getBookmarkUserCode() { return bookmarkUserCode; }
  public void setBookmarkUserCode(String bookmarkUserCode) { this.bookmarkUserCode = bookmarkUserCode; }
  public String getBookmarkParkingCode() { return bookmarkParkingCode; }
  public void setBookmarkParkingCode(String bookmarkParkingCode) { this.bookmarkParkingCode = bookmarkParkingCode; }
}

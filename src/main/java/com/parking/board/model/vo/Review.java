package com.parking.board.model.vo;

import java.sql.Date;

public class Review {
  int reviewNo;
  int reviewUserHistoryNo;
  String reviewTitle;
  String reviewContent;
  Date reviewCreatedDate;
  int reviewRating;
  private int reviewReadcount;

  public Review() {
    // TODO Auto-generated constructor stub
  }

  public Review(int reviewUserHistoryNo, String reviewTitle, String reviewContent, int reviewRating) {
    super();
    this.reviewUserHistoryNo = reviewUserHistoryNo;
    this.reviewTitle = reviewTitle;
    this.reviewContent = reviewContent;
    this.reviewRating = reviewRating;
  }

  public Review(int reviewNo, int reviewUserHistoryNo, String reviewTitle, String reviewContent, Date reviewCreatedDate,
      int reviewRating, int reviewReadcount) {
    super();
    this.reviewNo = reviewNo;
    this.reviewUserHistoryNo = reviewUserHistoryNo;
    this.reviewTitle = reviewTitle;
    this.reviewContent = reviewContent;
    this.reviewCreatedDate = reviewCreatedDate;
    this.reviewRating = reviewRating;
    this.reviewReadcount = reviewReadcount;
  }

  @Override
  public String toString() {
    return "Review [reviewNo=" + reviewNo + ", reviewUserHistoryNo=" + reviewUserHistoryNo + ", reviewTitle="
        + reviewTitle + ", reviewContent=" + reviewContent + ", reviewCreatedDate=" + reviewCreatedDate
        + ", reviewRating=" + reviewRating + ", reviewReadcount=" + reviewReadcount + "]";
  }

  public int getReviewNo() { return reviewNo; }
  public void setReviewNo(int reviewNo) { this.reviewNo = reviewNo; }
  public int getReviewUserHistoryNo() { return reviewUserHistoryNo; }
  public void setReviewUserHistoryNo(int reviewUserHistoryNo) { this.reviewUserHistoryNo = reviewUserHistoryNo; }
  public String getReviewTitle() { return reviewTitle; }
  public void setReviewTitle(String reviewTitle) { this.reviewTitle = reviewTitle; }
  public String getReviewContent() { return reviewContent; }
  public void setReviewContent(String reviewContent) { this.reviewContent = reviewContent; }
  public Date getReviewCreatedDate() { return reviewCreatedDate; }
  public void setReviewCreatedDate(Date reviewCreatedDate) { this.reviewCreatedDate = reviewCreatedDate; }
  public int getReviewRating() { return reviewRating; }
  public void setReviewRating(int reviewRating) { this.reviewRating = reviewRating; }
  public int getReviewReadcount() { return reviewReadcount; } 
  public void setReviewReadcount(int reviewReadcount) { this.reviewReadcount = reviewReadcount; }
}

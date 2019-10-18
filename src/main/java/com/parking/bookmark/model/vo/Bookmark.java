package com.parking.bookmark.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bookmark {
  private int bookmarkNo;
  private String bookmarkUserCode;
  private String bookmarkParkingCode;
}

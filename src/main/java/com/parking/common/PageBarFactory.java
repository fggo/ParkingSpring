package com.parking.common;

public class PageBarFactory {
  public static String getPageBar(int totalCount, int cPage, int numPerPage, String url) {
    String pageBar= "";
    int pageBarSize = 5;
    int pageNo = ((cPage-1)/pageBarSize )*pageBarSize + 1;
    int pageEnd =pageNo+ pageBarSize -1;

    int totalPage = (int)Math.ceil((double)totalCount/numPerPage);
    pageBar += "<ul class='pagination justify-content-center pagination-sm'";

    
    
    
    
    return pageBar;
  }

}

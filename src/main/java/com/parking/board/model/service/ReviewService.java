package com.parking.board.model.service;

import static com.parking.common.template.JDBCTemplate.close;
import static com.parking.common.template.JDBCTemplate.commit;
import static com.parking.common.template.JDBCTemplate.getConnection;
import static com.parking.common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.parking.board.model.dao.ReviewDao;
import com.parking.board.model.vo.Review;

public class ReviewService {
  private ReviewDao dao = new ReviewDao();

  public List<Review> selectReviewList(int cPage, int numPerPage){
    Connection conn = getConnection();
    
    List<Review> list = dao.selectReviewList(conn, cPage, numPerPage);
    
    close(conn);
    
    return list;
  }

  public Review selectReview(int no){
    Connection conn = getConnection();
    
    Review r = dao.selectReview(conn, no);
    
    close(conn);
    
    return r;
  }
  public int selectCountReview() {
    Connection conn = getConnection();
    
    int count = dao.selectCountReview(conn);
    
    close(conn);

    return count;
  }
  
  public int insertReview(Review r) {
    Connection conn = getConnection();
    
    int result = dao.insertReview(conn, r);
    
    if(result > 0)
      commit(conn);
    else
      rollback(conn);

    close(conn);

    return result;
  }
}

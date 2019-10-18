package com.parking.board.model.service;

import static com.parking.common.template.JDBCTemplate.close;
import static com.parking.common.template.JDBCTemplate.commit;
import static com.parking.common.template.JDBCTemplate.getConnection;
import static com.parking.common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.parking.board.model.dao.QnaBoardDao;
import com.parking.board.model.vo.QnaBoard;

public class QnaBoardService {
  private QnaBoardDao dao = new QnaBoardDao();

  public List<QnaBoard> selectQnaBoardList(int cPage, int numPerPage){
    Connection conn = getConnection();
    
    List<QnaBoard> list = dao.selectQnaBoardList(conn, cPage, numPerPage);
    
    close(conn);
    
    return list;
  }

  public QnaBoard selectQnaBoard(int qnaNo) {
    Connection conn = getConnection();
    
    QnaBoard q = dao.selectQnaBoard(conn, qnaNo);
    
    close(conn);

    return q;
  }
  
  public int selectCountQnaBoard() {
    Connection conn = getConnection();
    
    int count = dao.selectCountQnaBoard(conn);
    
    close(conn);

    return count;
  }
  

  public int insertQnaBoard(QnaBoard b) {
    Connection conn = getConnection();

    int result = dao.insertQnaBoard(conn, b);
    
    if(result > 0)
      commit(conn);
    else
      rollback(conn);
    
    close(conn);

    return result;
  }


  
  public List<QnaBoard> selectPopularBoard()
  {
	  Connection conn = getConnection();
	  List<QnaBoard> list = dao.selectPopularBoard(conn);
	  
	  close(conn);
	  return list;	  
  }
  

}

package com.parking.common.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
* <h1>JDBCTemplate</h1>
* <p>
* <b>Note:</b> Define template to connect java application 
* with the Oracle database using JDBC library
* 
* @author fggo
* @version 1.0
* @since   2019-08-15
*/
public class JDBCTemplate {

  // to read .properties
  private static Properties prop = new Properties();


  /**
   * getConnection() method is used to return Connection object
   * @return Connection connection object
   * @exception IOException On input error
   * 
   * @exception ClassNotFoundException
   * @exception SQLException
   * 
   */
  public static Connection getConnection() {
    Connection conn = null;
    String path = JDBCTemplate.class.getResource("./driver.properties").getPath();

    try {
      prop.load(new FileReader(path)); //reads a property list (key, value pairs)
      Class.forName(prop.getProperty("driver")); //register driver class(oracle.jdbc.driver.OracleDriver)
      conn = DriverManager.getConnection(prop.getProperty("url"), //establish connection with the database
                                         prop.getProperty("id"),
                                         prop.getProperty("pw"));
      conn.setAutoCommit(false);

    } catch(IOException e) {
      e.printStackTrace();
    } catch(ClassNotFoundException e) {
      e.printStackTrace();
    } catch(SQLException e) {
      e.printStackTrace();
    }

    return conn;
  }
  

  /**
   * close() method is used to close parameters(ResultSet or Statement) automatically
   * close() method of Connection interface is used to close the connection
   * closes the connection and Releases a JDBC resources immediately
   * @param conn this parameter contains connection data
   * @exception SQLException
   */
  public static void close(Connection conn) {
    try {
      if(conn != null && !conn.isClosed())
        conn.close();
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }

  public static void close(Statement stmt) {
    try {
      if(stmt != null && !stmt.isClosed())
        stmt.close();
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static void close(ResultSet rs) {
    try {
      if(rs != null && !rs.isClosed())
        rs.close();
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * commit() method is used to save the changes made since the previous commit/rollback permanent
   * @param conn this parameter contains connection data
   * @exception SQLException
   */
  public static void commit(Connection conn) {
    try {
      if(conn != null && !conn.isClosed())
        conn.commit();
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * commit() method is used to drops all changes made since the previous commit/rollback
   * @param conn this parameter contains connection data
   * @exception SQLException
   */
  public static void rollback(Connection conn) {
    try {
      if(conn != null && !conn.isClosed())
        conn.rollback();
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }
}

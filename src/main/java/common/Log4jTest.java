package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {
  //log4j를 적용하기 위해서는 Logger객체를 이용한다.
//  private Logger logger = Logger.getLogger(Log4jTest.class);
  private static Logger logger=LoggerFactory.getLogger(Log4jTest.class);
  
  public static void main(String[] args) {
    //log4j를 적용하기 위해서는 Logger객체를 이용한다
    Log4jTest.test();
  }
  
  public static void test() {
    logger.debug("DEBUG 이야!");
    logger.info("INFO 이야!");
    logger.warn("WARN 이야!");
    logger.error("ERROR 이야!");
  }
}

package common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect {
  private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

  //특정 메소드 실행 전, 후, 전/후에 실행할 로직
  public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
    
//  around(전후처리) * 전처리만, 후처리만
    //특정 매소드 실행전|후|전후에 실행할 로직
    //around방식(전후처리) * 전처리만, 후처리만 가능
    
    Signature sig = joinPoint.getSignature();

    logger.debug("[signature] " + sig);

    String type = sig.getDeclaringTypeName();
    String methodName = sig.getName();
    String componentName = "";
    if(type.indexOf("Controller") > -1) {
      componentName = "Controller";
    }
    else if(type.indexOf("Service") > -1) {
      componentName = "ServiceImpl";
    }
    else if(type.indexOf("Dao") > -1) {
      componentName = "DaoImpl";
    }
    
    logger.debug("[Before] " + componentName + type + "." + methodName + "()");
//    return joinPoint.proceed();
    //전처리만 하고 끝냄


    //전처리
    Object o = joinPoint.proceed(); //이 메소드를 기준으로 전처리 후처리가 나눠짐
    //후처리시작
    logger.debug("[After] " + componentName + type + "." + methodName + "()");
    
    return o;
  }

  //전처리 메소드
  public void before(JoinPoint joinpoint) {
    joinpoint.getSignature(); // controller->dao 넘어가는 메소드 명 등을 확인
    Object[] olist = joinpoint.getArgs(); //이전단계에서 넘어오는 파라미터값 확인; session값 등등
    for(Object o : olist) {
    }
    logger.debug("*before*전처리 전용");
  }

}

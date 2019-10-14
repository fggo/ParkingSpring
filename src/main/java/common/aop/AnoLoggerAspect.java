package common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//controller service도 아니고, 사용할 수 있는 객체
@Component //aop config in aspect-context.xml
@Aspect //<bean id> in aspect-context.xml
public class AnoLoggerAspect {
  private Logger logger = LoggerFactory.getLogger(AnoLoggerAspect.class);
  
  // 1. pointcut 실행대상 메소드 표현식 적용

//  @Pointcut("execution(* com.parking..*(..))")
  @Pointcut("execution(* com.parking..insert*(..))")
  public void beforeAop() {}

  // 2. advice 등록
  @Before("beforeAop()")
  public void testAop(JoinPoint joinpoint) {
    Signature s = joinpoint.getSignature();
    
    logger.debug("[before]" + s.getName());
  }

  @Around("execution(* com.parking..*(..))")
  public Object loggerAround(ProceedingJoinPoint joinpoint) throws Throwable {
    logger.debug("[before] Guncheol!!!");
    Object obj = joinpoint.proceed();
    logger.debug("[after] 파이널 화이팅~");
    return obj;
  }

}

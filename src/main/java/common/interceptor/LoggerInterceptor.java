package common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

  private Logger logger=LoggerFactory.getLogger(LoggerInterceptor.class);

  //전 처리용 매소드
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    logger.debug("======================start======================");
    logger.debug(request.getRequestURI());
    logger.debug("-------------------------------------------------");
    //login시에만 dev등록 및 목록조회하도록 수정: request에서 로그인 여부 확인
    if(request.getSession().getAttribute("loginMember") == null){
      logger.debug("LOGGER interceptor: 'You're NOT logged in! BACK to home!");
      request.setAttribute("msg", "Please log in!");
      request.setAttribute("loc", "/");
      request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
      return false;
    } else{
      logger.debug("LOGGER interceptor: 'You're logged in! Ok to proceed!");
      return super.preHandle(request, response, handler);
    }


  }
  
  //후 처리용 매소드
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
     ModelAndView modelAndView) throws Exception {
    logger.debug("======================Mapping method 실행======================");
   // TODO Auto-generated method stub
    logger.debug("-------------------------------------------------");
    super.postHandle(request, response, handler, modelAndView);
  }
  

}

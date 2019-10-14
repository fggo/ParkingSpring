<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <!-- API -->

 <link rel="stylesheet" href="<%=request.getContextPath() %>/css/pwdresetstart.css">

<div class="container sns">
  <div class="d-flex justify-content-center h-100">
    <div class="card">
      <div class="card-header">
        <h3>Reset Your Password</h3>
        <p>Don't worry! You may have forgotten your password, but we can help you out. Enter your email below and we'll email you a link to reset your password.</p>
      </div>
      <div class="card-body">
        <form action="<%=request.getContextPath() %>/member/emailpwdresetstart" method="post">
          <div class="input-group form-group">
            <input type="email" class="form-control" name="email" id="email" placeholder="Email">
          </div>
          <div class="form-group">
            <input type="submit" value="Email me!" class="btn float-right submit_btn">
          </div>
        </form>
      </div>
      <div class="card-footer">
        <div class="d-flex justify-content-center">
          <a href="<%=request.getContextPath() %>/views/member/loginView.jsp">Log in &nbsp;</a>
          <a href="<%=request.getContextPath() %>/views/member/memberEnroll.jsp">Sign Up</a>
        </div>
      </div>
    </div>
  </div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>
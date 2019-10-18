<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ page import="web.email.PwdMailSend" %>
<% String code = request.getParameter("code"); 
String email = request.getParameter("email");
%>

<!DOCTYPE html>

<html>

<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <!-- API -->

 <link rel="stylesheet" href="<%=request.getContextPath() %>/css/pwdresetend.css">


</head>


<body>

<div class="container sns">
  <div class="d-flex justify-content-center h-100">
    <div class="card">
      <div class="card-header">
        <h3>Change Your Password</h3>
      </div>
      <div class="card-body">
        <form action="<%=request.getContextPath() %>/member/emailpwdresetend" method="post">
          <div>
            <input type="hidden" name=code value='<%=code%>'/>
            <input type="hidden" name=email value='<%=email%>'/>
          </div>
          <div class="input-group form-group">
            <input type="password" class="form-control" placeholder="Password" id="pwEnroll" name="newPw">
          </div>
          <div class="input-group form-group">
            <input type="password" class="form-control" placeholder="Confirm password" id="pwEnrollChk" name="newPwChk">
          </div>
          <div class="form-group">
            <input type="submit" value="Change Password" class="btn float-right submit_btn">
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

<script>
  /* password regex check :
     min 6-char, at least one letter and one number 
     may contain special characters
   */
   var result = regexCheckPw($('#pwEnrollChk').val());
   if(result != "ok" && $('#snsAccount').val() == "N/A"){
    $('#pwEnrollChk').val("");
    $('#pwEnroll').val("").focus();
    alert(result);
    return false;
   }
   return true;

  //password regex check
  function regexCheckPw(pw) {
   if (pw.length < 6)
     return("password too short");
   else if (pw.length > 20)
     return("password too long");
   else if (pw.search(/\d/) == -1)
     return("password must include number!");
   else if (pw.search(/[a-zA-Z]/) == -1)
     return("password must include letter!");
   else if (pw.search(/[^a-zA-Z0-9\!\@\#\$\%\^\&\*\(\)\_\+\.\,\;\:]/) != -1)
     return("password includes bad character!");

   return("ok");
  }

  </script>
</body>

</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="web.email.MailSend" %>
<% String code = request.getParameter("code"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

  div#verfemail-container{
    text-align: center;
    padding-top:50px;
  }
  span#duplicated{
    color: slategray;
    font-weight: bold;
  }

</style>
</head>
<body>
<form action="<%=request.getContextPath() %>/emailverification" method="post">
  <div id="verfemail-container">
      Please click the button to continue!
      <input type="hidden" name=code value= '<%=code %>'/>
      <br><br>
   <input type="submit" value="confirm"/>  
   </div>
</form>

</body>
</html>
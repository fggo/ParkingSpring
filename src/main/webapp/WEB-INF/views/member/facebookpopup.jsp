<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="web.email.MailSend" %>
<% String useremail = request.getParameter("userEmail"); %>
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
<form action="<%=request.getContextPath() %>/FacebookServlet" method="post">
  <div id="verfemail-container">
     You are logging into your Facebook account!
      <input type="hidden" name=useremail value= '<%=useremail%>'/>
      <br><br>
   <input type="submit" value="confirm" />  
   </div>
</form>

</body>
</html>
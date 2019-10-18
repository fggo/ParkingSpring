<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
  boolean isUseable = (boolean)request.getAttribute("isUseable");
  String userEmail = (String)request.getAttribute("userEmail");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Email Duplicate</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<style>
  div#checkemail-container{
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
  <div id="checkemail-container">
    <% if(isUseable){ %>
      [<span><%=userEmail %></span>] is OK to use!
      <br><br>
      <button type="button" onclick="setEmail()">close</button>
    <% } else{ %>
      [<span id="duplicated"><%=userEmail %></span>] is already is use!
      <br><br>
      <form action="<%=request.getContextPath() %>/checkEmailDuplicate"
        method="post" name="checkEmail">
        <input type="email" name="userEmail" id="userEmail"
          placeholder="Type new email"/>&nbsp;&nbsp;
        <button type="button" onclick="checkEmailDuplicate();">Check for duplication</button>
      </form>
    <% } %>
  </div>

  <script>
    function checkEmailDuplicate(){
      var userEmail = $('#userEmail').val();

      //update form tag
      checkEmail.userEmail.value = userEmail.trim();
      checkEmail.submit();
    }

    function setEmail(){
      opener.document.getElementById("userEmail").value = "<%= userEmail %>";
      // opener.document.getElementById("userEmail").setAttribute("disabled", "true");
      opener.document.getElementById("pwEnroll").focus();
      self.close();
    }
  </script>

</body>
</html>
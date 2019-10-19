<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="com.parking.member.model.vo.Member"%>
  
  <% 
  	String userEmail = (String)request.getAttribute("userEmail");
  	String snsAccount = (String)request.getAttribute("snsAccount");
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="EmailPopUp-container">
	    <form name="EmailPopUpFrm" action="" method="post">
	    <h2>sns로 가입을 진행하시겠습니까?</h2>
	      <table>    
	        <tr>
	          <td>
	            <input type="button" id="enrolltrue" onclick="emailPopUpEmailCheck()" value="확인"/>
	            <input type="button" onclick="self.close();" value="닫기" />
	          </td>
	        </tr>
	      </table> 
	    </form>
	  </div>

  <script>

  	function emailPopUpEmailCheck()
  	{  		
  		console.log("들어옴2");
  		window.opener.document.getElementById("kakao-email").value='<%=userEmail%>';
  		window.opener.loginViewEmailCheck('<%=userEmail%>', '<%=snsAccount%>');
  		self.close();
  	}
  	
  </script>

</body>
</html>
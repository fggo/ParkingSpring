<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!-- header -->
<jsp:include page="/WEB-INF/views/common/header.jsp">
  <jsp:param name="pageTitle" value="Login" />
</jsp:include>

<c:set var="path" value="${pageContext.request.contextPath}" />

<style>

  div#verfemail-container{
    text-align: center;
    padding-top:70px;
  }
  span#duplicated{
    color: slategray;
    font-weight: bold;
  }

</style>

<form action="${path}/emailverification" method="post" class="form card">
  <div id="verfemail-container" class="container">
    <p>Please click the button to continue!</p>
    <input type="hidden" name="code" value= '${code}' />
    <input type="hidden" name="email" value= '${email}' />
    <input type="submit" value="confirm" class="form-control btn btn-sm btn-primary" />  
  </div>
</form>

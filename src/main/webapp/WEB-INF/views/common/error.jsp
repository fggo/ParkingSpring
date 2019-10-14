<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <!-- CSS -->
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/error.css">

  <title>404 PARKING ERROR</title>
</head>
<body>

  <main class="bsod container">
    <h1 class="neg title"><span class="bg">Parking Error - 404</span></h1>

    <p>An error has occured, to continue:</p>
    <p>* Return to our homepage.<br />
    * Send us an e-mail about this error and try later.</p>

    <nav class="nav">
      <a href="<%=request.getContextPath() %>" class="link">Back to <i class="fa fa-home"></i></a>&nbsp;|&nbsp;<a href="mailto: jnuho@outlook.com" class="link">webmaster <i class="fa fa-user-secret"></i></a>
    </nav>


    <!-- web.xml에 모든 exception에 대한 공통 에러페이지 등록가능 -->
    <%-- <h1 style="color:red;">"<%=exception.getMessage() %>" EXCEPTION!</h1> --%>
    
    <!-- <script>
      setTimeout(function(){
        location.href="<%=request.getContextPath() %>/index.jsp";
      }, 1800);
    </script> -->
  </main>
  

</body>  
</html>
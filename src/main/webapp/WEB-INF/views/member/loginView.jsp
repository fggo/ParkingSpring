
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%
  Cookie[] cookies = request.getCookies();
  String saveEmail = null;

  if(cookies != null){
    for(Cookie c : cookies){
      String key= c.getName();
      String value = c.getValue();
      if(key.equals("saveEmail"))
        saveEmail = value;
    }
  }
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <!-- API -->

  <link rel="stylesheet" href="${path }/resources/css/login.css">

  <div class="container sns ">
    <div class="d-flex justify-content-center h-100">
      <div class="card">
        <div class="card-header">
          <h3>Sign In</h3>
          <div class="d-flex justify-content-end social_icon">

     
 
           <span><i class="fa fa-facebook-official" name="facebook" id="loginBtn"></i></span> 
                     <div id="response"></div>
                    
                    
              <span id="googleSignIn"><i class="fa fa-google-plus-square"></i></span>
             <!--   <span onclick="loginWithKakao()"><img src="<%=request.getContextPath() %>/images/kakaobutton.png" class="kakaobutton"></span>-->
              <input type="hidden" id="kakao-email" >
              
              <!-- <div class="fa fa-google-plus-square" data-onsuccess="onSignIn" data-theme="dark" id="myP"></div> -->
          </div>

          <div class="d-flex justify-content-center">
            <img id="myImg"><br>
            <div id="name" class="text-white"></div>
            <!-- <div id="status"></div> -->
            <!-- <button id="googleLogoutBtn" onclick="googleLogout()" class="btn btn-sm btn-outline-light mt-1 mr-1">Google Log Out</button> -->
          </div>
        </div>
        <div class="card-body">
          <form class="form-signin" action="${path}/member/loginEnd.do" method="post"
            onsubmit="return validateLogin()">

            <div class="input-group form-group">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-user"></i></span>
              </div>
              <input type="useremail" class="form-control" placeholder="Email" name="useremail" id="useremail"
                     value='<%=saveEmail != null? saveEmail : "" %>' />
            </div>
            <div class="input-group form-group">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-key"></i></span>
              </div>
              <input type="password" class="form-control" placeholder="Password" name="userpw" id="userpw">
            </div>
            <div class="row align-items-center remember">
              <label for="saveEmail">
                <input type="checkbox" name="saveEmail" id="saveEmail" <%=saveEmail!=null? "checked":"" %> />Remember Me!
              </label>
            </div>
            <div class="form-group">
              <input type="submit" value="Log In" class="btn btn-outline-dark float-right login_btn">
            </div>

            
          </form>
        </div>
        <div class="card-footer">
          <div class="d-flex justify-content-center links">
            Don't have an account?<a href="${path }/member/memberEnroll">Sign up</a>
          </div>
          <div class="d-flex justify-content-center">
            <a href="${path }/member/pwdresetstart">Forgot your password?</a>

          </div>
        </div>
      </div>
    </div>
  </div>
  
  <script src="//connect.facebook.net/en_US/all.js"></script>


 
    <script>
  
  /* facebook login */
  window.fbAsyncInit = function(){
    	//SDK loaded, initialize it
    	FB.init({
    		appId      : '650760722099588',
    		xfbml      : true,
    		version    : 'v4.0'
    	});

    	//check user session and refresh it
    	FB.getLoginStatus(function(response) {
    		if (response.status === 'connected') {
    			//user is authorized
    			/* document.getElementById('loginBtn').style.display = 'none'; */
    			getUserData();
    		} else {
    			//user is not authorized
    		}
    	});
    };

    //load the JavaScript SDK
    (function(d, s, id) {
    	var js, fjs = d.getElementsByTagName(s)[0];
    	if (d.getElementById(id)) {return;}
    	js = d.createElement(s); js.id = id;
    	js.src = "//connect.facebook.com/en_US/sdk.js";
    	fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    //add event listener to login button
    document.getElementById('loginBtn').addEventListener('click', function()  {
    	//do the login
    	FB.login(function(response) {
    		if (response.authResponse) {
    			//user just authorized your app
    			/* document.getElementById('loginBtn').style.display = 'none'; */
    			getUserData();
    		}
    	}, {scope: 'email,public_profile', return_scopes: true});
        

    	
    }, false);
  
  
    function getUserData() {
    	FB.api('/me', {fields: 'name,email'}, function(response) {
    /* 		document.getElementById('response').innerHTML = 'Hello ' + response.name; */
    		console.log(response.email); 
    		var facebookaccount = response.email;
    		facebookdb(facebookaccount);
			
    	});

   }
	
     function facebookdb(facebookaccount){ 
 	     var url ="<%=request.getContextPath()%>/views/member/facebookpopup.jsp?userEmail="+facebookaccount;
     	 var title="facebook login";
   		open(url,title,status);
    	
    } 
 
       
 	/* 카카오계정로그인 */
  	var kakao_email = null;
  	Kakao.init('e6a59ff7a98afa5e25a62d40b484f3d6');
    function loginWithKakao() {
      // 로그인 창을 띄웁니다.
      Kakao.Auth.login({
        success: function(authObj) {
        	
        	Kakao.API.request({
        		
        		url:'/v1/user/me',       		
        		success: function(res){      			
	        		//alert(JSON.stringify(res));
	        		
	        		// 로그찍으면 res.kaccount_email 은 계정에 대한 이메일이 찍힌다.
	        		console.log(res.id);   		
	        		console.log(res.kaccount_email);
	        		
	        		console.log(res.properties['nickname']);
	        		//임의로 만든 인풋창 안에 이메일 값을 넣어두었다가 확인용으로 사용 (나중에 hidden으로 변경예정)
	        		$("#kakao-email").val(res.kaccount_email);
	        		//Ajax형식으로 콜백 함수 활용 계정에 대한 이메일 값을 넣고 값확인
	        		AjaxEmailCheck($("#kakao-email").val(), 'K');
        		}
        	})
          	/* alert(JSON.stringify(authObj));
          	consol.log(authObj.id); */      
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
      });
    }
      
      //계정이 없다면 팝업을 이용
      function emailPopUp(snsEmail,snsAccount)
      {
      	var url="<%=request.getContextPath()%>/member/EmailPopUp?userEmail="+snsEmail + "&snsAccount=" + snsAccount;
  		var title="signUpPopUp";
  		var status="left=500px, top=200px, width=400px, height=210px";
  		window.open(url,title,status);
      }
      
      function loginViewEmailCheck(snsEmail, snsAccount)
      {
      	location.href="${path}/memberEnroll?userEmail="+snsEmail +"&snsAccount=" + snsAccount;
      }
      
      function indexPage()
      {
      	location.href="${path}/member/checktrueEmail";
      }
  
    function validateLogin(){
      if($('#useremail').val().length==0){
        alert("Please type Email for login");
        $('#useremail').focus();
        return false; //prevent form submit
      }
    	if($('#userpw').val().length==0){
        alert("Please type Password for login");
        $('#userpw').focus();
        return false; //prevent form submit
      }
    	return true;
    }
    </script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
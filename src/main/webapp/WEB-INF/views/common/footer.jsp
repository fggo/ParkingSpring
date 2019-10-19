<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}" />

<!-------------------------
'footer.jsp' contains :
  * <footer> element
-------------------------->
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
  <hr class="featurette-divider">

  <footer class="container py-0">
    <div class="row">
      <div class="col-6 col-md">
        <a class="navbar-brand " href="${path}" >
          <img src="${path}/resources/images/logo_black.png">
        </a>
        <small class="d-block mb-3 text-muted">&copy; 2019.09</small>
      </div>
      <div class="col-6 col-md">
      <p class='h5'>District</p>
      <ul class="list-unstyled text-small">
        <li><a class="text-muted" href="#">Seocho-gu</a></li>
        <li><a class="text-muted" href="#">Seodaemun-gu</a></li>
        <li><a class="text-muted" href="#">Seongbuk-gu</a></li>
        <li><a class="text-muted" href="#">Seongdong-gu</a></li>
        <li><a class="text-muted" href="#">Songpa-gu</a></li>
        <li><a class="text-muted" href="#">Yangcheon-gu</a></li>
      </ul>
      </div>
      <div class="col-6 col-md">
      <p class='h5'>Help</p>
      <ul class="list-unstyled text-small">
        <li><a class="text-muted" href="#">Resource</a></li>
        <li><a class="text-muted" href="#">Resource name</a></li>
        <li><a class="text-muted" href="#">Another resource</a></li>
        <li><a class="text-muted" href="#">Final resource</a></li>
      </ul>
      </div>
      <div class="col-6 col-md">
      <p class='h5'>About Us</p>
      <ul class="list-unstyled text-small">
        <li><a class="text-muted" href="#">Team</a></li>
        <li><a class="text-muted" href="#">Locations</a></li>
        <li><a class="text-muted" href="#">Privacy</a></li>
        <li><a class="text-muted" href="#">Terms</a></li>
      </ul>
      </div>
      <div class="col-6 col-md">
      <p class='h5'>Contact Us</p>
      <ul class="list-unstyled text-small">
        <li><a class="text-muted" href="#">010-1234-1234</a></li>
        <li><a class="text-muted" href="#">120 East 12th Street, Manhattan, 1003, NY USA</a></li>
        <li>
          <div class="row social-list">
            <div class="col-sm-3 col-xs-2 col-sm-offset-0 col-xs-offset-2 px-0">
              <a href="" class="">
                <span class="fa-stack fa-lg" href="">
                  <i class="fa fa-linkedin fa-stack-1x"></i>
                </span>
              </a>
            </div>
            <div class="col-sm-3 col-xs-2 px-0">
              <a href="" class="">
                <span class="fa-stack fa-lg">
                  <i class="fa fa-instagram fa-stack-1x"></i>
                </span>
              </a>
            </div>
            <div class="col-sm-3 col-xs-2 px-0">
              <a href="" class="">
                <span class="fa-stack fa-lg">
                  <i class="fa fa-twitter fa-stack-1x"></i>
                </span>
              </a>
            </div>
            <div class="col-sm-3 col-xs-2 px-0">
              <a href="" class="">
                <span class="fa-stack fa-lg">
                  <i class="fa fa-facebook-official fa-stack-1x"></i>
                </span>
              </a>
            </div>
          </div>
        </li>
      </ul>
      </div>
    </div>
  </footer>

  <!-- JQUERY -->
  <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

  <!-- JAVASCRIPT -->
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

  <!-- GOOGLE LOGIN -->
  <script src="https://apis.google.com/js/platform.js" async defer></script>

  <!-- trigger google btn click -->
  <script src="https://apis.google.com/js/platform.js?onload=onLoadGoogleCallback" async defer></script>

  <script>
    //Ajax로 sns계정이 DB에 있는지 확인
    function AjaxEmailCheck(snsEmail, snsAccount){
      $.ajax ({
        type:"POST",
        url: "${path}/member/JsonMemberEmailcheck",
        dataType: "json",
        data: {userEmail: snsEmail},
        success: function(data) {
          for(var d in data) {
            console.log(data[d]);
            console.log(data[d]["userEmail"]);
            console.log(data[d]["userSnsAccount"]);
            console.log(data);
            //들어온 Data 값에 대해 snsEmail에 대한 이메일과 ajax에서 가져온 데이터 값을 주고
            //비교후 같다면 바로 로그인 진행
            //아니면 팝업을 이용해 sns가입을 권유 modal을 띄워줌
            if(data[d]["userEmail"] == snsEmail) {
              console.log("userEmail : " + data[d]["userEmail"]);
              console.log("userSnsAccount : " + data[d]["userSnsAccount"]);

              var frm = $('<form>').attr({"action": "${path}/member/snsAutoLogin.do",
                                          "method": "POST", });
              var input1 = $('<input>').attr({"value": snsEmail, "name": "userEmail"});
              frm.append(input1);
              $(document.body).append(frm);
              frm.submit();
              // location.href="${path}/member/MemberEmailcheck?userEmail=" + snsEmail 
              //             + "&snsAccount=" + snsAccount;
            }else{
              //modal
              var accountType =  snsEmail.substring(snsEmail.lastIndexOf("@") +1,
                                                    snsEmail.lastIndexOf("."));
              $('#snsModal').modal();
              $('#snsAccountType').attr({"value": accountType});
              $('#snsEmailTxt').html(snsEmail);
            }
          }
        }
      });
    }

    function onLoadGoogleCallback(){
      gapi.load('auth2', function() {
        auth2 = gapi.auth2.init({
          client_id: '847270498494-dj5o04t83q5uja6ccp6eekr3en7qcb8p.apps.googleusercontent.com',
          cookiepolicy: 'single_host_origin',
          scope: 'profile'
        });
        
        element = document.getElementById('googleSignIn');

        auth2.attachClickHandler(element, {},
          function(googleUser) {
            if(googleUser!=null){
              var profile = googleUser.getBasicProfile();
              console.log("ID: " + profile.getId()); // Don't send this directly to your server!
              console.log('Full Name: ' + profile.getName());
              console.log('Given Name: ' + profile.getGivenName());
              console.log('Family Name: ' + profile.getFamilyName());
              console.log("Image URL: " + profile.getImageUrl());
              console.log("Email: " + profile.getEmail());
              
              var id_token = googleUser.getAuthResponse().id_token;
              console.log("ID Token: " + id_token);

              // $('#myImg').attr({"src": profile.getImageUrl(),
              //                   "style": "width:31px; heght:31px"});
              // $('#name').html("Hi! " + profile.getName());
              $('#googleLotoutBtn').toggle();

              AjaxEmailCheck(profile.getEmail(), 'G');
            }

            //document.getElementById("status").innerHTML = 'Welcome '+name+"!<a href=success.jsp?email='+email+'&name='+name+'/>Continue with Google login</a></p>";
            // document.getElementById("status").innerHTML = 'Hello! '+name;
            // var googleUserEntity = {};
            // googleUserEntity.Id = profile.getId();
            // googleUserEntity.Name = profile.getName();
            
            // //Store the entity object in sessionStorage where it will be accessible from all pages of your site.
            // sessionStorage.setItem('googleUserEntity',JSON.stringify(googleUserEntity));
            // if(sessionStorage.getItem('googleUserEntity') == null){
            //   //Redirect to login page, no user entity available in sessionStorage
            //   // window.location.href='Login.html';
            //   console.log("google logged out");
            //   $('#googleLogoutBtn').hide();
            // } else {
            //   //User already logged in
            //   console.log("google logged in");
            //   googleUserEntity = JSON.parse(sessionStorage.getItem('googleUserEntity'));
            // }
          },
          function(error) {
            console.log('onLoadGoogleCallback : Sign-in error', error);
          }
        );
      });

      // if(document.getElementById('googleSignIn') != null){
      //   element = document.getElementById('googleSignIn');
      // }
      // else{
      //   element = null;
      //   console.log("googleSignIn button is in loginView.jsp!");
      //   return;
      // }
    }

    // function googleLogin(){
    //   $( "#googleBtn" ).trigger( "click" );
    // }

    function googleLogout() {
      gapi.auth2.getAuthInstance().disconnect();
      sessionStorage.clear();
      $('#googleLotoutBtn').toggle();
      location.reload();
      console.log("google logged out");
    }

  </script>

  <script>

    $(function(){
      $(window).scroll(function () {
        if ($(this).scrollTop() > 40) {
          $('#back-to-top').fadeIn();
        } else {
          $('#back-to-top').fadeOut();
        }
      });

      $('#back-to-top').click(function() {
        $('body,html').animate({
          scrollTop: 0
        }, 300);
        return false;
      });

      $("#main-search").keyup(function(){
        $.ajax({
          url:"${path}/ajax/parkingAutoCommit", 
          type:"post",
          data:{ addr:$("#main-search").val() },
          dataType:"json",
          success:function(data){
              console.log(data);
            
            /*  $("#searchData").html("");
              var datas=data.split(",");  */      
              for(var d in data)
              {
                  var option=$("<option>").attr("value",data[d]["addr"]+"("+data[d]["parkingName"]+")");
                  $("#searchData").append(option);
              }
              
              //for(var i=0; i<datas.length; i++){
                /*  var option=$("<option>").attr("value",datas[i]).html(datas[i]);
                $("#searchData").append(option); */
              // var option=$("<option>").attr("value",data[i]["addr"]+"("+data[i]["parkingName"]+")").html(data[i]["addr"]);
                  //$("#searchData").append(option);
            //  }                  
          }
        });            
      });
    });

    $(window).resize(function(){
      navbar_visible = $("#collapseItems").is(":visible");

      if (navbar_visible){
        $("#nav-searchbar").removeClass (function (index, className) {
          return (className.match (/mx-./g) || []).join(' ');
        });
        if($(window).width()>1200){
          $("#nav-searchbar").addClass('mx-5');
        }
        else if($(window).width()>992){
          $("#nav-searchbar").addClass('mx-3');
          // $('.navbar-brand').css({"display": "inline"});

        }
        else if($(window).width() >768){
          $("#nav-searchbar").addClass('mx-0');

          $("#navbar-logo").removeClass("col-md-2");
          $("#nav-searchbar").removeClass("col-md-8");
          $("#navCollapsible").removeClass("col-md-2");
        }
        else{
          $("#navbar-logo").addClass("col-md-2");
          $("#nav-searchbar").addClass("col-md-8");
          $("#navCollapsible").addClass("col-md-2");
        }

      }
      else{
        $("#nav-searchbar").removeClass (function (index, className) {
          return (className.match (/mx-./g) || []).join(' ');
        });
        $("#nav-searchbar").addClass('mx-0');
      }
    });

  </script>

</body>
</html>

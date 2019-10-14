<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}" />

  <!-- header -->
  <jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="pageTitle" value="Homepage" />
  </jsp:include>

  <main role="main" id="page-container">
    <!-- search area -->
    <div class="jumbotron align-items-center justify-content-center" id="main-search-area">
      <div class="container">
        <p class="h3 text-white row justify-content-center font-weight-bold">SEARCH FOR PARKING</p>
        <p class="h7 text-white row justify-content-center font-weight-bold">Around 23 Seoul Districts!</p>
        <div class="mx-auto col-md-9 ">
          <form id="main-searchbar" action="${path }/map/mapListView" class="" role="form">
            <div class="input-group" id="main-searchbar">
              <input type="search" class="form-control" name="search" placeholder="   Where do you need parking?" aria-describedby="button-addon5" id="main-search" list="searchData">
              <datalist id="searchData"></datalist>
              <div class="input-group-append">
                <button id="button-addon5" class="btn btn-primary" id="main-searchbar-btn"><i class="fa fa-search"></i></button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>

    <!-- carousel -->
    <div id="carousel_review" class="carousel slide" data-ride="carousel">
      <div class="text-center mt-4">
        <p class="h4 font-weight-bold">WHAT USERS ARE SAYING</p>
      </div>
      <ol class="carousel-indicators">
        <li data-target="#carousel_review" data-slide-to="0" class="active"></li>
        <li data-target="#carousel_review" data-slide-to="1"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <div class="container text-center py-4">
            <div class="row">
              <div class="col-lg-3">
                <img class="profile-picture" src="${path }/resources/images/profile.png" alt="Generic placeholder image">
                <div class="form-group">
                  <label for="exampleFormControlTextarea1">lions</label>
                  <textarea readonly class="form-control review_content" id="exampleFormControlTextarea1" cols="4" rows="7">This Illegal Parking website is fantastic! Looking forward to use this often!</textarea>
                </div>
                <p>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star-o"></i></span>
                  <span><i class="text-warning fa fa-star-o"></i></span>
                </p>
              </div>
              <div class="col-lg-3">
                <img class="profile-picture" src="${path }/resources/images/profile.png" alt="Generic placeholder image">
                <div class="form-group">
                  <label for="exampleFormControlTextarea1">tigers</label>
                  <textarea readonly class="form-control review_content" id="exampleFormControlTextarea1" cols="4" rows="7">Bought a new car last month. I was very afraid of the public parking space. Illegal Parking gives me assurance!</textarea>
                </div>
                <p>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star-o"></i></span>
                </p>
              </div>
              <div class="col-lg-3">
                <img class="profile-picture" src="${path }/resources/images/profile.png" alt="Generic placeholder image">
                <div class="form-group">
                  <label for="exampleFormControlTextarea1">bears</label>
                  <textarea readonly class="form-control review_content" id="exampleFormControlTextarea1" cols="4" rows="7">There are literally thousands of parking lots near my company. This website allows me to compare the cost! what a site!</textarea>
                </div>
                <p>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star-o"></i></span>
                  <span><i class="text-warning fa fa-star-o"></i></span>
                </p>
              </div>
              <div class="col-lg-3">
                <img class="profile-picture" src="${path }/resources/images/profile.png" alt="Generic placeholder image">
                <div class="form-group">
                  <label for="exampleFormControlTextarea1">Park you</label>
                  <textarea readonly class="form-control review_content" id="exampleFormControlTextarea1" cols="4" rows="7">Give me your money! I don't believe in angels!</textarea>
                </div>
                <p>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <div class="container text-center py-4">
            <div class="row">
              <div class="col-lg-3">
                <img class="profile-picture" src="${path }/resources/images/profile.png" alt="Generic placeholder image">
                <div class="form-group">
                  <label for="exampleFormControlTextarea1">user_05</label>
                  <textarea readonly class="form-control review_content" id="exampleFormControlTextarea1" cols="4" rows="7"></textarea>
                </div>
                <p>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star-o"></i></span>
                  <span><i class="text-warning fa fa-star-o"></i></span>
                </p>
              </div>
              <div class="col-lg-3">
                <img class="profile-picture" src="${path }/resources/images/profile.png" alt="Generic placeholder image">
                <div class="form-group">
                  <label for="exampleFormControlTextarea1">user_06</label>
                  <textarea readonly class="form-control review_content" id="exampleFormControlTextarea1" cols="4" rows="7"></textarea>
                </div>
                <p>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star-o"></i></span>
                </p>
              </div>
              <div class="col-lg-3">
                <img class="profile-picture" src="${path }/resources/images/profile.png" alt="Generic placeholder image">
                <div class="form-group">
                  <label for="exampleFormControlTextarea1">user_07</label>
                  <textarea readonly class="form-control review_content" id="exampleFormControlTextarea1" cols="4" rows="7"></textarea>
                </div>
                <p>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star-o"></i></span>
                  <span><i class="text-warning fa fa-star-o"></i></span>
                </p>
              </div>
              <div class="col-lg-3">
                <img class="profile-picture" src="${path }/resources/images/profile.png" alt="Generic placeholder image">
                <div class="form-group">
                  <label for="exampleFormControlTextarea1">user_08</label>
                  <textarea readonly class="form-control review_content" id="exampleFormControlTextarea1" cols="4" rows="7"></textarea>
                </div>
                <p>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                  <span><i class="text-warning fa fa-star"></i></span>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#carousel_review" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carousel_review" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
      </a>
    </div>


    <!-- carousel -->
    <div id="carousel_about" class="carousel slide carousel_review-target" data-ride="carousel">

      <ol class="carousel-indicators">
        <li data-target="#carousel_about" data-slide-to="0" class="active"></li>
        <li data-target="#carousel_about" data-slide-to="1"></li>
        <li data-target="#carousel_about" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img class="" width="100%" height="100%" src="${path }/resources/images/about1.jpg" alt="">
          <div class="container">
            <div class="carousel-caption text-center text-dark">
              <p class='h2 font-weight-bold'>About Us</p><br>
              <p class='about-text'>The leading provider of parking reservations. Drivers plan and commute smarter by booking guaranteed parking in advance. </p>
              <p class='text-left'>
                <button class="learn-more" onclick="location.href='${path }/views/aboutView.jsp'" >
                  <div class="circle">
                    <span class="icon arrow"></span>
                  </div>
                  <p class="button-text">About Us</p>
                </button>
              </p>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <img class="" width="100%" height="100%" src="${path }/resources/images/about2.jpg" alt="">
          <div class="container">
            <div class="carousel-caption text-center text-dark">
              <p class='h2 font-weight-bold'>About Us</p><br>
              <p class='about-text'>Customers are empowered with the ability to search and compare thousands of 
                parking options and prices in 25 areas.
              </p>
              <p class='text-left'>
                <button class="learn-more" onclick="location.href='${path }/views/aboutView.jsp'" >
                  <div class="circle">
                    <span class="icon arrow"></span>
                  </div>
                  <p class="button-text">About Us</p>
                </button>
              </p>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <img class="" width="100%" height="100%" src="${path }/resources/images/about3.jpg" alt="">
          <div class="container">
            <div class="carousel-caption text-center text-dark">
              <p class='h2 font-weight-bold'>About Us</p><br>
              <p class='about-text'>Drivers can easily book a spot ahead of time at parking lots, garages, and valets in their city, or they can reserve on-the-go with the company’s award-winning mobile app for iOS and Android.</p>
              <p class='text-left'>
                <button class="learn-more" onclick="location.href='${path }/views/aboutView.jsp'" >
                  <div class="circle">
                    <span class="icon arrow"></span>
                  </div>
                  <p class="button-text">About Us</p>
                </button>
              </p>
            </div>
          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#carousel_about" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carousel_about" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
      </a>
    </div>

    <!-- help section -->
    <div class="help jumbotron justify-content-center align-items-center" style="height: 420px;">
      <div class="container">
        <div class="mx-auto col-lg-8 pt-5">
          <p class="h3 mb-5 text-white row justify-content-center font-weight-bold">How can we help?</p>
          <form id="main-searchbar" action="${path }/views/helpView.jsp" class="" role="form">
            <div class="input-group mb-4">
              <div class="input-group-prepend">
                <button id="button-addon7" type="submit" class="btn btn-light"><i class="fa fa-search"></i></button>
              </div>
              <input type="search" placeholder="Ask Us any Questions" aria-describedby="button-addon7" class="form-control">
            </div>
          </form>
        </div>

        <button class="learn-more" onclick="location.href='${path }/board/helpBoardList'" >
          <div class="circle"><span class="icon arrow"></span></div>
          <p class="button-text">Help</p>
        </button>
      </div>
    </div>
  </main>

  <!-- JQUERY -->
  <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

  <script>
    
      //테스트 자신의 위치 자겨오기
      // navigator.geolocation.getCurrentPosition(function(pos)
      // {
      //   var latitude = pos.coords.latitude;
      //   var longitude = pos.coords.longitude;
      //   alert("현재위치 : " + latitude + "," + longitude);
      //   console.log("위도 : " + latitude);
      //   console.log("경도 : " + longitude);
        
      // });
    $(function(){
      $('#nav-searchbar').hide(); // hidden by default

      var hasBeenTriggered = false;
    
      /* nav-searchbar and main-searchbar are toggled
        based on scroll pixel amounts from the top */
      $(window).scroll(function(){
        var winPos= $(window).scrollTop();
        var mainSearchbarPos= $('#main-searchbar').offset().top;
        var diff = winPos - mainSearchbarPos;
        
      
        // var navpos = $('#nav-searchbar').scrollTop();
        // var mainpos = $('#main-searchbar').scrollTop();
        // console.log(navpos);
        // console.log(mainpos);

        if($(this).scrollTop()>=280 && !hasBeenTriggered){
        // if(navpos < mainpos && mainpos - navpos <50 ) {
        // if(diff < 0 && diff > -100 && !hasBeenTriggered) {
          // $('#navbar-logo').hide();
          var mainSearchTxt = $('#main-search').val();
          $('#nav-search').val(mainSearchTxt);

          hasBeenTriggered = true;
          $('#nav-searchbar').fadeToggle();
          $('#main-searchbar').fadeToggle();
        }
        // else if(navpos > mainpos && navpos - mainpos <50 ) {
        // else if(diff > 0 && diff <100 && hasBeenTriggered) {
        else if($(this).scrollTop()<280 && hasBeenTriggered){
          // $('#navbar-logo').show();
          var navSearchTxt = $('#nav-search').val();
          $('#main-search').val(navSearchTxt);

          hasBeenTriggered = false;
          $('#nav-searchbar').fadeToggle();
          $('#main-searchbar').fadeToggle();
        }
      });
    });


  </script>

  <!-- footer -->
  <jsp:include page="/WEB-INF/views/common/footer.jsp" />
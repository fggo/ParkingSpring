<%@page import="com.parking.api.model.vo.Parking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.parking.member.model.vo.Member"%>
<%@ page import="com.parking.board.model.vo.QnaBoard"%>

<jsp:include page="/WEB-INF/views/common/header.jsp">
  <jsp:param name="pageTitle" value="Search" />
</jsp:include>

<% /* List<Parking> list = (List)request.getAttribute("list"); */
  String search = (String)request.getAttribute("search");
%>
<!-- overflow:auto 사용하여 list 내용이 잘릴때 스크롤 사용  -->


  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  <c:set var="path" value="${pageContext.request.contextPath}" />

  <!-- JQUERY -->
  <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

  <!-- JAVASCRIPT -->
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

  <!-- API -->
  <script>

    $(function(){
      <c:choose>
        <c:when test="${search !=null}">
          $.ajax({
            url: "<%=request.getContextPath()%>/ajax/searchAddr.do",
            type: "post",
            data: { addr: "<%=search%>" },
            dataType: "json",
            success: function (data) {
            
              console.log("들어오냐?");
              console.log(data);
      
              /*    for(var d in data) {
                  console.log(data[d]);
                        
                  console.log(typeof data[d]);
                }  */
              navigator.geolocation.getCurrentPosition(function (pos) {
                //latitude = pos.coords.latitude;
              //longitude = pos.coords.longitude;
              $('#listScroll').empty();

                parkingList(data);
                parkingMarker(data, pos);
                // console.log("위도 : " + latitude);
                //console.log("경도 : " + longitude);
              });
            },
            error: function (data) { // 데이터 통신에 실패한 것
              console.log("서버 전송 실패");
            }
    
          });
        </c:when>
      
        <c:otherwise>
          return;
        </c:otherwise>
      </c:choose>
      
    });
    
    function googleTranslateElementInit() {
      new google.translate.TranslateElement(
        {
          pageLanguage: 'en',
          includedLanguages: 'en,es,ja,ko,pt,ru,zh-CN,zh-TW',
          layout: google.translate.TranslateElement.InlineLayout.SIMPLE,
          autoDisplay: false
        }, 'google_translate_element'
      );
    }
  </script>


  <style>
      *{
    font-family: "Nanum Gothic";
  }
  
  #pName{
    font-size: 18px;
    font-weight: bold;
  }
  #Pcur{
    font-size: 13px;
    
  }
    .goog-te-gadget-simple {
      border: 1px solid rgba(255, 255, 255, .0);
    }

    .nav-item {
      color: #B8C8D6 !important;
    }

    .align-left {
      text-align: left;
    }

    .float-left {
      float: left;
    }

    .float-right {
      float: right;
    }

    .my-1 {
      margin-top: 1em;
      margin-bottom: 1em;
    }

    .mt-3 {
      margin-top: 3em;
    }

    ul li {
      list-style-type: none;
    }

    .pr-1 {
      padding-right: -em;
    }

    /*가로 카드박스 중간 1em*/
    .pl-1 {
      padding-left: 1em;
    }

    /*가로 카드박스 중간 1em*/
    .pb-1 {
      padding-bottom: 1em;
    }

    .pt-1 {
      padding-top: 1em;
    }

    .overflow-hidden {
      overflow: hidden;
    }

    .width {
      width: 80px;
    }

    #listScroll {
      overflow: scroll;
    }

    .style_width {
      width: 100%;
    }
  <%for(int i = 1; i < 1000; i++){%>
    object#daum\:roadview\:<%=i%>
    {
      position: relative !important;
    }
  <%}%>
    
  
    #mapbtn{
      z-index: 2;
      position: absolute;
      left: 15px;
      border-radius: 3px;
      box-shadow: rgba(0, 0, 0, 0.15) 0px 2px 2px 0px;
      background-color: #3396ff;
      opacity: 1;
    }
    #loadviewbtn{
      z-index: 2;
      position: absolute;
      left: 15px;
      opacity: 1;
      border-radius: 3px;
      box-shadow: rgba(0, 0, 0, 0.15) 0px 2px 2px 0px;
      background-color: #3396ff;
    }
    #realLocBtn{
      z-index: 2;
      position: absolute;
      left: 90px;
      opacity: 1;
      border-radius: 3px;
      box-shadow: rgba(0, 0, 0, 0.15) 0px 2px 2px 0px;
      background-color: #3396ff;
    }
    #mapbtn:hover{
      opacity: .8;      
    }
    #realLocBtn:hover{
      opacity: .8;    
    }
    #loadviewbtn:hover{
      opacity: .8;    
    }
    
  #distanceModal {
   position: absolute;
   top: -200px;
   right: 0px;
   bottom: 0;
   left: 0;
   z-index: 10040;
   overflow: auto;
   overflow-y: auto;
}
#maincon
{
  height: 100vh !important;
  display: flex !important;
}
  
    
  

  </style>



  <div class="container-fluid" id="maincon">


    <!-- row h-100 mt-1 pt-2 -->
    <div class="card-deck text-center container-fluid" style="margin-top: 51px;" id="cardmap">

      <!--상단div_1-->
      <div class="shadow-sm col-sm-4">

        <!--card / shadow-sm -->
        <div class="card-body align-left">
          <!-- <label for="firstName">From</label> -->
          <!--From-->
          <!-- <input type="text" class="form-control" id="datepicker_start">
          <select name="" class="custom-select d-block w-100">
            <option class="">Start Time</option>
          </select> <label for="firstName" class="mt-3">To</label> -->
          <!--To-->
          <!-- <input type="text" class="form-control" id="datepicker_end" size="20"> 
          <select name="" class="custom-select d-block w-100">
            <option>End Time</option>
          </select>  -->
          <label class="mt-3">Sort By</label> <select name="sort by" class="custom-select d-block w-100">
            <option value="Distace">Distance</option>
            <option>Price(low to high)</option>
            <option>Price(high to low)</option>
            <option>Recommended</option>
          </select> 
          <label class="mt-3">Filter By</label> <select name="Filter By" class="custom-select d-block w-100">
            <option value="Amenities">Amenities</option>
            <option>Covered Parking</option>
            <option>Electric Vehicle Charging</option>
            <option>Free Shuttle Service</option>
            <option>In and Out Previleges</option>
            <option>On-Site Staff</option>
            <option>Open 24/7</option>
            <option>Overnight Parking</option>
            <option>Illigal Scan</option>
            <option>Valet</option>
            <option>Wheelchair Accessible</option>
          </select> <span class="mt-3 pb-5" id="parkingLength"></span>
        </div>

        <!--하단 list-->
        <div class="card shadow-sm text-left" id="listScroll" style="height:470px">
          
          
          <!--
          <a class="list-group-item list-group-item-action">
            </span>
          </a> 
          <a class="list-group-item list-group-item-action"> 
            <span>
              <span>Hyatt Place - Uncovered Self Park</span>
               <span>110.9miles away</span>
                <span>
                    <input type="button" class="btn btn-info more-info" onclick="parkingInfoPopup()" value="More Info">    
                    <input type="button" class="btn btn-info more-info" onclick="" value="Pay">           
              </span>
            </span>
          </a> 
          <a class="list-group-item list-group-item-action"><span>
              <span>Hyatt Place - Uncovered Self Park</span> <span>110.9
                miles away</span> <span><button>$14.70</button>
                <button>Reserve</button></span>
            </span> </a>
            -->
    </div>
    <script>
      $('#listScroll').click(function(e){
        console.log($(e.target).children().first().attr('id'));
        idValue = $(e.target).children().first().attr('id');       
        var data = JSON.parse(localStorage.getItem("parkingList"));
        console.log(data);
        for(var d in data)
        {
          
          if(d == idValue)
          {
            if($("#loadview").css("display") != 'none')
            {
              loadView(data[d]["latitude"],data[d]["longitude"],data[idValue]["longitude"]);
              idValue = window.localStorage.setItem("selectNum", idValue);
            }else{
              
              window.localStorage.setItem("selectNum", idValue);
              mapCopy.setCenter(new kakao.maps.LatLng(data[idValue]["latitude"], data[idValue]["longitude"]));      
              mapCopy.setLevel(4);
            }
            //window.localStorage.setItem("realLat",JSON.data[idValue]["latitude"]);
            //window.localStorage.setItem("realLon",data[idValue]["longitude"]);
            //window.localStorage.setItem("pName",data[d]["parkingName"]);
            //loadView(data[idValue]["latitude"],data[idValue]["longitude"],data[d]["parkingName"]);  
          }
        }
      
      });
      
      
      
      function loadView(lat,lon,pName)
      {
        var load = $("#loadview");
        //var roadview = $("#loadview");
        //var placePosition = mapCopy.setCenter(new kakao.maps.LatLng(lat, lon));      
        //mapCopy.setLevel(2);
        
        if($("#loadview") != null)
        {
          $("#loadview").empty();
        }
        
            mapTaginit(2);
                
        var roadviewContainer = document.getElementById('loadview'); //로드뷰를 표시할 div
        var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
        var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

        var position = new kakao.maps.LatLng(lat, lon);

        // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
          roadviewClient.getNearestPanoId(position, 800, function(panoId) {
          roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
        });
        
      }

      function realLocClick()
      {
        idValue = -1;
        window.localStorage.setItem("selectNum",idValue);
        navigator.geolocation.getCurrentPosition(function (pos) {
            mapCopy.setCenter(new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude));      
            mapCopy.setLevel(2);
            });
           
      }

      function loadviewClick()
      {   
        idValue = localStorage.getItem("selectNum");     
        console.log("id value : " + idValue);
        var data = JSON.parse(localStorage.getItem("parkingList"));
        if(idValue == -1)
        {
            navigator.geolocation.getCurrentPosition(function (pos) {
              loadView(pos.coords.latitude, pos.coords.longitude, "내현재위치");
          
            });

            //idValue = window.localStorage.setItem("selectNum", idValue);
          
        }else{
          for(var d in data)
          {
            if(d == Number(idValue))
            {
              //window.localStorage.setItem("realLat",JSON.data[idValue]["latitude"]);
              //window.localStorage.setItem("realLon",data[idValue]["longitude"]);
              //window.localStorage.setItem("pName",data[d]["parkingName"]);
              loadView(data[d]["latitude"], data[d]["longitude"], data[d]["parkingName"]);
              idValue = window.localStorage.setItem("selectNum", idValue);
            }
          }
        }
        
        
      }

      function mapClick()
      {
        var idValue = localStorage.getItem("selectNum");     
        var data = JSON.parse(localStorage.getItem("parkingList"));

        for(var d in data)
        {
          if(d == Number(idValue))
          {
            //window.localStorage.setItem("realLat",JSON.data[idValue]["latitude"]);
            //window.localStorage.setItem("realLon",data[idValue]["longitude"]);
            //window.localStorage.setItem("pName",data[d]["parkingName"]);
            window.localStorage.setItem("selectNum", idValue);
            mapCopy.setCenter(new kakao.maps.LatLng(data[idValue]["latitude"], data[idValue]["longitude"]));      
            mapCopy.setLevel(2);
           
          }
        }
        mapTaginit(1);
      }

  
      
      
    </script>


      </div>

      <!--지도 API-->
    <div class="shadow-sm col-sm-8" id="map">
      
    </div>
    <div class="shadow-sm col-sm-8" id="loadview">
      
      </div>
      <!-- style="width:950px;height:500px;" -->
      <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=002ce24c1581207f304dfb0ead53db42"></script>

    </div>
  </div>

<script>

  $("#button-addon5").click(function () {
    $.ajax({
      url: "<%=request.getContextPath()%>/ajax/searchAddr.do",
      type: "post",
      data: { addr: $("#searchAddr").val() },
      dataType: "json",
      success: function (data) {
        console.log("들어오냐?");
        console.log(data);

        /*    for(var d in data) {
            console.log(data[d]);
                  
            console.log(typeof data[d]);
          }  */

        navigator.geolocation.getCurrentPosition(function (pos) {
          //latitude = pos.coords.latitude;
          //longitude = pos.coords.longitude;

          $('#listScroll').empty();
          parkingList(data);
          //주차장 마커찍기
          parkingMarker(data, pos);
         
        });
      },
      error: function (data) { // 데이터 통신에 실패한 것
        console.log("서버 전송 실패");
      }

    });
    // return false;
  });

  
  // 1-현재 위치 or 지도,
  // 2-로드뷰
  function mapTaginit(num)
  {
    var rbtn = $("#realLocBtn").length;
    var mbtn = $("#mapbtn").length;
    var lVbtn = $("#loadviewbtn").length;

    console.log("rbtn :" +rbtn);
    console.log("lVbtn :" +lVbtn);
    console.log("mbtn :" +mbtn);
    
    // map 이 위로 올라오고 현위치버튼 로드뷰 버튼이 위로 올라온다
    if(rbtn == 0 && num == 1)
    {
      rbtn = null;
      rbtn = $("<input type='button' id='realLocBtn' class='btn btn-primary' value='현위치' onclick='realLocClick()'>"); //현위치 버튼
      $("#map").append(rbtn);
      console.log("여기왔음");

      lVbtn = null;
      lVbtn = $("<input type='button' id='loadviewbtn' class='btn btn-primary' value='로드뷰' onclick='loadviewClick()'>"); // 로드뷰 버튼
      $("#map").append(lVbtn);
  
      $("#loadview").hide();
      
    }else if(rbtn == 1 && num == 1)
    {    
      $("#loadview").hide();
      $("#map").show();
    }
    
    if(num == 2)
    {
      mbtn = null;
      mbtn = $("<input type='button' id='mapbtn' class='btn btn-primary' value='지도' onclick='mapClick()'>"); // 지도 버튼
      console.log("여기왔음");
      $("#loadview").append(mbtn);
      $("#loadview").show();
      $("#map").hide();  
      
        // /$('#search\\_form\\:expression')
        //var obj = $("#loadview").find($("#daum\\:roadview\\:2"));
        //console.log("obj:"+obj);
        //$("object[id^='daum\\:roadview\\:^\d+$']")
        //var name = $("div[id*='daum\\:roadview\\:']").attr("id");
        //var name = document.childNodes;
        
        //var name = $("#loadview")['prevObject'].find("object[id^='daum\\:roadview\\:^\d+$']").attr("id");
      //  console.log(name);
        // for(var i = 0; i < name.prevObject.length(); i++)
        // {
        //   console.log(name.prevObject[i].children[i]);        
        // }
        // for(var i = 0; i < name.length; i++)
        // {
        //    console.log(i+"번째 : "+ name[i].);
        // }
        // console.log(name);
        // if($("#daum\\:roadview\\:2").length)
        // {
        //   console.log("찾음");
        //   //obj = null;
        //   $("#daum\\:roadview\\:2").attr("daum\\:roadview\\:1");
        // }
            //$("#loadviewbtn").attr("disabled","disabled");
      
    }
    
  }

  function parkingMarker(data, pos) {
    //테스트 자신의 위치 자겨오기
   
    var obj = data;
    if($("#map") != null)
    {
      $("#map").empty();
    }
    //1-현위치버튼,2-지도,3-로드뷰
    mapTaginit(1);
    //window.localStorage.setItem("realLat",pos.coords.latitude);
    //window.localStorage.setItem("realLon",pos.coords.longitude);
    //window.localStorage.setItem("pName","현재 내 위치");
    var mapContainer = document.getElementById('map'),
    //var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
      mapOption = {
        center: new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude), // 지도의 중심좌표
        level: 3,
        /* mapTypeId: new kakao.maps.MapTypeId(SKYVIEW) */
        // 지도의 확대 레벨
      };
    mapCopy = null;
    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    mapCopy = map;
    mapCopyMarker = [];
    infowindowCopy = [];
    // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    var mapTypeControl = new kakao.maps.MapTypeControl(); 

    // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
    // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      map.addControl(mapTypeControl,
      kakao.maps.ControlPosition.TOPRIGHT);

    // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
    var zoomControl = new kakao.maps.ZoomControl();
    
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
 
  var realLoc = [];
  var realiwContens=[];
  var positions = [];
  var iwContents = [];
  
  positions.push({
    title: "현재내위치",
    latlng: new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude),
    //clickable:true
  });
  
  iwContents.push({
    iwContent : '<div style="padding:2px;">'+"현재내위치"+'</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
  // iwPosition : new kakao.maps.LatLng(data[d]['latitude'], data[d]['longitude']), //인포윈도우 표시 위치입니다
    iwRemoveable : true // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
  });    
  
    for (var d in data) {
      positions.push({
        title: data[d]['parkingName'],
        latlng: new kakao.maps.LatLng(data[d]['latitude'], data[d]['longitude']),
        //clickable:true
      });
        
      iwContents.push({
        iwContent : '<div style="padding:2px;">'+data[d]['parkingName']+'</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
      // iwPosition : new kakao.maps.LatLng(data[d]['latitude'], data[d]['longitude']), //인포윈도우 표시 위치입니다
        iwRemoveable : true // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
      });      
  }
    
  /*   var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다 */

    for (var i in data) {
      console.log(data[d]);
    }

    // 마커를 표시할 위치와 title 객체 배열입니다 
    /*  var positions = [
       {
         title: '카카오',
         latlng: new kakao.maps.LatLng(33.450705, 126.570677)
       },
       {
         title: '생태연못',
         latlng: new kakao.maps.LatLng(33.450936, 126.569477)
       },
       {
         title: '텃밭',
         latlng: new kakao.maps.LatLng(33.450879, 126.569940)
       },
       {
         title: '근린공원',
         latlng: new kakao.maps.LatLng(33.451393, 126.570738)
       }
     ]; */

    // 마커 이미지의 이미지 주소입니다
    //var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    for (var i = 0; i < positions.length; i++) {

      // 마커 이미지의 이미지 크기 입니다
      //var imageSize = new kakao.maps.Size(24, 35);

      // 마커 이미지를 생성합니다    
      //var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        //image: markerImage // 마커 이미지 
      });
      mapCopyMarker.push(marker);
      marker.setClickable(true);
      //marker.setMap(map);
      
      // 인포윈도우를 생성하고 지도에 표시합니다
    /*   var infowindow = new kakao.maps.InfoWindow({
          //map: map, // 인포윈도우가 표시될 지도
          position : iwContents[i].iwPosition, 
          content : iwContents[i].iwContent,
          removable : iwContents[i].iwRemoveable
      }); */
      
        var infowindow  = new kakao.maps.InfoWindow({
          content: iwContents[i].iwContent, // 인포윈도우 내부에 들어갈 컨텐츠 입니다.
          removable : iwContents[i].iwRemoveable
      });
      infowindowCopy.push(infowindow);
      // if(marker.getTitle() == "현재내위치")
      // {
      // infowindow.open(map,marker);
      // }
      infowindow.open(map,marker);
        
      kakao.maps.event.addListener(marker,'click',makeOverListener(map, marker, infowindow));
        //kakao.maps.event.addListener(marker,'click',makeOutListener(infowindow));     
        //infowindow.open(map, marker); // 지도에 올리면서, 두번째 인자로 들어간 마커 위에 올라가도록 설정합니다.
      }
      //var togglebtn = $("<input id='mapbtn' type='button' value='현위치'>");
      //var togglebtn = $("<input id='mapbtn' type='button' value='현위치'>");
      //var togglebtn = $("<input id='mapbtn' type='button' value='현위치'>");
      //$("#map").append(togglebtn);
      //$("#map").append(togglebtn);
      //$("#map").append(togglebtn);
      map.setCenter(new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude));      
      map.setLevel(6);
      
  }
  
  function makeOverListener(map, marker, infowindow)
  {
    return function(){
      alert(marker.getTitle());
      infowindow.open(map,marker);
      if(marker.getTitle() != "현재내위치")
      {
          var linePath = null; 
          navigator.geolocation.getCurrentPosition(function (pos) {
                
              linePath =[
              new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude),
              marker.getPosition(),
              ];

              // 지도에 표시할 선을 생성합니다
              var polyline = new kakao.maps.Polyline({
                  path: linePath, // 선을 구성하는 좌표배열 입니다
                  strokeWeight: 5, // 선의 두께 입니다
                  strokeColor: '#5858FA', // 선의 색깔입니다
                  strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                  strokeStyle: 'solid' // 선의 스타일입니다
              });

              // 지도에 선을 표시합니다 
              polyline.setMap(mapCopy);
              polylineCopy = polyline;
              var dataMarker = []; // 마커
              var dataInfo = []; // infowindow
              //mapCopyMarker;
              for(var i = 0; i < mapCopyMarker.length; i++)
              {
                dataMarker.push(mapCopyMarker[i]);
              }

              for(var j = 0; j < infowindowCopy.length; j++)
              {
                dataInfo.push(infowindowCopy[j]);
              }

              for(var dm in dataMarker)
              {
                if(dataMarker[dm].getTitle() == "현재내위치")
                {
                  continue;
                }else if(dataMarker[dm].getTitle() == marker.getTitle())
                {
                  continue;
                }
                else{
                  dataMarker[dm].setOpacity(.2);
                }
              }

              for(var di in dataInfo)
              {        
                if(dataInfo[di].getContent() == "<div style=\"padding:2px;\">"+marker.getTitle()+"</div>")
                {
                  continue;
                }else if(dataInfo[di].getContent() == "<div style=\"padding:2px;\">"+"현재내위치"+"</div>")
                {
                  continue;
                }
                else{
                  console.log("infowindow : " + dataInfo[di].getContent());
                  dataInfo[di].close();
                }
              }

              //marker.setOpacity(1);
              //길이반환 단위(m)미터
              console.log("거리 : " + polyline.getLength());
              
            
            
              console.log(marker.getPosition());
              
              beforMarkerLat = marker.getPosition()["Ha"];
              beforMarkerLon = marker.getPosition()["Ga"];
              
              $.ajax({
              url: "<%=request.getContextPath()%>/ajax/centerLatLon.do",
              type: "post",
              data: { lat1:  pos.coords.latitude,
                      lon1 : pos.coords.longitude,
                      lat2 : marker.getPosition()["Ha"],
                      lon2 : marker.getPosition()["Ga"]
              },
              dataType: "json",
              success: function (data) {
                var lat = 0;
                var lon = 0;
                for(var d in data)
                {
                  if(d == 0)
                    lat = data[d];
                  if(d==1)
                    lon = data[d];      
                }
                console.log(lat);
                
                mapCopy.setCenter(new kakao.maps.LatLng(lat,lon));
                
                // if(Math.floor(polyline.getLength()*0.001) < 4)
                // {
                //   mapCopy.setLevel(3);
                // }
                // else if(Math.floor((polyline.getLength()*0.001)) < 8)
                // {
                //   mapCopy.setLevel(4);
                // }
                // else if(Math.floor((polyline.getLength()*0.001)) < 14)
                // {
                //   mapCopy.setLevel(6);
                // }
                // else if(Math.floor((polyline.getLength()*0.001)) > 18)
                // {
                //   mapCopy.setLevel(8);
                // }
                      mapCopy.setLevel(8);
                $("#distanceModal").modal("show");
                //$("#distanceModal").modal({backdrop: 'static'});
                $("#distanceModal-body").html("<h3>about<br>"+(polyline.getLength()*0.001).toFixed(3)+" kilometers from current location.</h3><br>"+"<h6>Please press OK to close it.</h6>");
              },
              error: function (data) { // 데이터 통신에 실패한 것
                console.log("서버 전송 실패");
              }      
            });              
          });    
      }else{
          
          beforMarkerLat = 0;
          $("#distanceModal").modal("show");
          //$("#distanceModal").modal({backdrop: 'static'});
          $("#distanceModal-body").html("<h3>Don't click on me....</h3><br>"+"<h6>Please press OK to close it.</h6>");    
      }
    };
  }
  
  function distanceMoalClose()
  {
      
      if(beforMarkerLat == 0)
      {
          navigator.geolocation.getCurrentPosition(function (pos) {
            mapCopy.setCenter(new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude));
            mapCopy.setLevel(3);
          });
      }
      else{
          var dataMarker = []; // 마커
          var dataInfo = []; // infowindow
          //mapCopyMarker;
          polylineCopy.setMap(null);
          for(var i = 0; i < mapCopyMarker.length; i++)
          {
            dataMarker.push(mapCopyMarker[i]);
          }

          for(var j = 0; j < infowindowCopy.length; j++)
          {
            dataInfo.push(infowindowCopy[j]);
          }

          for(var dm in dataMarker)
          {    
              dataMarker[dm].setOpacity(1);
          }

          for(var di in dataInfo)
          {        
            dataInfo[di].open(mapCopy,dataMarker[di]);
          }
          mapCopy.setCenter(new kakao.maps.LatLng(beforMarkerLat,beforMarkerLon));
          mapCopy.setLevel(3);        
      }
  }
  
  

  // 아직 안쓰는 함수
  function makeOutListener(infowindow)
  {
    return function(){
      infowindow.close();
    };
  }
  

  //왼쪽 리스트 출력
  function parkingList(data)
  {
    window.localStorage.setItem("parkingList",JSON.stringify(data));
    window.sessionStorage.setItem("parkingList",JSON.stringify(data));

    for(var d in data)
    {
      var listScroll = $("#listScroll");
      var aTag = $("<a class='list-group-item list-group-item-action'>");
      var span0 = $("<span id="+d+">");
      var span1 = $("<span id='pName'>").html((Number(d)+1)+". "+data[d]["parkingName"]+"<br>");
    var span2 = $("<span>").html("&nbsp;&nbsp;&nbsp;&nbsp;"+data[d]["addr"]+"<br>");
      var span3 = $("<span id='Pcur'>").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;전체 주차 대수 : "+data[d]["capacity"]+"<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;현재 주차 대수 : "+data[d]["curParking"] +"<br>");
      var div = $("<div class='mt-2'>");
      // var btnStr = "<button class='btn btn-sm btn-outline-info mr-1' "
      //                       // + "data-toggle='modal' "
      //                       // + "data-target='#myModal' "
      //                       + "onclick='parkingListPopup();' "
      //                       + ">More Info</button>";
      var btnStr = "<button class='btn btn-sm btn-outline-info mr-1' "
                            + "id='parking" + d + "'>"
                            + "More Info</button>";
      var infoBtn = $(btnStr);
      var payForm = $("<form id='payform' method='post' action='<%=request.getContextPath()%>/payment/paymentView'>");
      var payinput1=$("<input type='hidden' name='parkingNum' id='inputparkingnum'>");
      // var payinput2=$("<input type='hidden' name='parkingName' id='inputparkingname'>");
      var input2 = $("<input type='submit' class='btn btn-sm btn-outline-info pay' value='Pay' "+"id='pay"+d+"'>");
      payForm.append(payinput1).append(input2);
      div.append(infoBtn).append(payForm);
      span0.append(span1).append(span2).append(span3).append(div);
      aTag.append(span0);
      listScroll.append(aTag);  
      //private int capacity;        // 주차 면(주차 가능 차량 수)
      //private int curParking;        // 현재 주차중인 대수
      payinput1.val(d);

      <c:choose>
        <c:when test="${loginMember != null}">
          input2.show();
        </c:when>
        <c:otherwise>
          input2.hide();
        </c:otherwise>
      </c:choose>
    }

    $("#parkingLength").html("Result : "+data.length+" locations");
    
    // + "onclick='parkingListPopup();' "
    $(function(){

      /* read  */
      $.each(data, function(d, item) {
        // console.log(item);
        $('#parking' + d).click(function(){
          var parkingName = data[d]["parkingName"];
          var addr = data[d]["addr"];
          var operationRuleNm = data[d]["operationRuleNm"];
          var parkingTypeNm = data[d]["parkingTypeNm"];
          var tel = data[d]["tel"];
          var capacity = data[d]["capacity"];
          var curParking = data[d]["curParking"];
          var latitude = data[d]["latitude"];
          var longitude = data[d]["longitude"];
          var parkingCode = data[d]["parkingCode"];
          console.log(parkingName);

          var parkingCodeArr = []; //bookmarked list of parking code
          var isBookmarked = false;
          if($("#popRoadView").val() != null)
          {
            $("#popRoadView").empty();
          }
          listPopRoadView(latitude,longitude); // 팝업로드뷰 생성
          if(localStorage.hasOwnProperty("bookmarkList")){
            var bookmarkData = JSON.parse(localStorage.getItem("bookmarkList"));

            for(var b in bookmarkData){
              var pcode = parseInt(bookmarkData[b]["bookmarkParkingCode"], 10);
              parkingCodeArr.push(pcode);
              if(pcode == parseInt(parkingCode,10))
                isBookmarked = true;
            }
          }
          else{
            <c:if test="${bookmarkList != null}">
              <c:forEach var="b" items="${bookmarkList}" varStatus="status">
                parkingCodeArr.push("${b.bookmarkParkingCode}");
                if(parkingCode == "${b.bookmarkParkingCode}")
                  isBookmarked = true;
              </c:forEach>
            </c:if>
          }

          if(parkingCodeArr == null || parkingCodeArr.length == 0)
          console.log("Both localStorage and bookmarkList are null!");
          
          // var isBookmarked = parkingCodeArr.includes(parseInt(parkingCode, 10));

          if(isBookmarked){
            if($('i#bookmarkIcon').hasClass("fa-star-o")){
              $('i#bookmarkIcon').removeClass("fa-star-o").addClass("fa-star");
            }
          }
          else{
            if($('i#bookmarkIcon').hasClass("fa-star")){
              $('i#bookmarkIcon').removeClass("fa-star").addClass("fa-star-o");
            }
          }
          
          $('#myModal').modal('show');

          $('input#parkingCode').val(parkingCode);
          $('#modalLabelParkingName').html(parkingName);
          $('#parkingName').html(parkingName);
          $('#addr').html(addr);
          $('#operationRuleNm').html(operationRuleNm);
          $('#parkingTypeNm').html(parkingTypeNm);
          $('#tel').html(tel);
          $('#capacity').html(capacity);
          $('#curParking').html(curParking);
         

        });
        // alert(data[i]);
      });
    });
                  /*
                  <div class="card shadow-sm text-left" id="listScroll">
                    <a class="list-group-item list-group-item-action"> 
                          <span>
                            <span>Hyatt Place - Uncovered Self Park</span>
                            <span>110.9miles away</span>
                              <span>
                                  <input type="button" class="btn btn-info more-info" onclick="parkingInfoPopup()" value="More Info">    
                                  <input type="button" class="btn btn-info more-info" onclick="" value="Pay">           
                            </span>
                          </span>
                        </a> 
                  */
  }

  //팝업 로드뷰생성
  function listPopRoadView(lat, lon)
  {              
        var roadviewContainer = document.getElementById('popRoadView'); //로드뷰를 표시할 div
        var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
        var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

        var position = new kakao.maps.LatLng(lat, lon);

        // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
          roadviewClient.getNearestPanoId(position, 800, function(panoId) {
          roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
        });
  }
  
  function payment(e)
  {    
    console.log($(e.target).attr("id"));
    //location.href="<%=request.getContextPath()%>/payment/paymentView?parkingnum="+el;
  }

  





  /*  $(document).ready(
     function () {
       $("#datepicker_start").datepicker(
         {
           numberOfMonths: 1,
           minDate: 0,
           onSelect: function (selected) {
             $("#datepicker_end").datepicker("option", "minDate", selected)
           }
         });
       $("#datepicker_end").datepicker(
         {
           numberOfMonths: 1,
           minDate: 0,
           onSelect: function (selected) {
             $("#datepicker_start").datepicker("option", "maxDate", selected)
           }
         });
     }); */
</script>

  <!-- 거리 나오는 모달 -->
  <div class="modal fade hide in" id="distanceModal" tabindex="-1" role="dialog" data-keyboard="false" data-backdrop="static" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-target="#distanceModalTitle">
    <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLongTitle">Distance</h5>
          <button type="button" data-dismiss="modal">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body" id="distanceModal-body">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="distanceMoalClose()">Ok</button>
        </div>
      </div>
    </div>
  </div>




<!-- Bootstrap Modal -->
<!-- This modal is for showing detailed information of each parking lot 
  since each list of parking lot is dynamically created, click button event is assigned 
  at the time of creation.


  
  
 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="modalLabelParkingName" aria-hidden="true" data-keyboard="false" data-backdrop="static">
  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title ml-auto" id="modalLabelParkingName"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

        <div class="card mb-4 rounded">
          <div class="social-card-header align-middle text-center bg-light rounded border"
               style="height:400px;" id="popRoadView">
              <!-- <img class="mr-3" src="" alt="" width="48" height="48"> -->
              <!-- <img src="<%=request.getContextPath() %>/images/qna.png" class="mr-3" width="60px"> -->
              <!-- <div class="lh-100">
                <p class="h5 mb-0 text-white lh-100">&nbsp;</p>
                 <small>Since 2019.09</small> 
              </div> -->
          </div>
          <div class="card-body text-center">
            <div class="row">
              <div class="col border-right">
                <span class="text-muted" id="toggleTooltip"><a href="javascript:;" id="bookmarkToggleBtn">
                  <i class="fa fa-star-o text-twitter" id="bookmarkIcon">&nbsp;&nbsp;</i>Bookmark</a></span>
                <div class="font-weight-bold"></div>

                <script>
                  $(function(){
                    <c:choose>
                      <c:when test="${loginMember == null}">
                        $('#toggleTooltip').attr({"data-toggle" : "tooltip",
                                                  "title" : "login required!"});
                      </c:when>
                      <c:otherwise>
                        $('#toggleTooltip').removeAttr("data-toggle");
                        $('#toggleTooltip').removeAttr("title");
                      </c:otherwise>
                    </c:choose>
                    $('#bookmarkToggleBtn').click(function(){
                      var toggleOption = "";
                      <c:if test="${loginMember != null}">
                        if($('i#bookmarkIcon').hasClass("fa-star")){
                          toggleOption = "delete";
                          $('i#bookmarkIcon').removeClass("fa-star").addClass("fa-star-o");
                        }
                        else if($('i#bookmarkIcon').hasClass("fa-star-o")){
                          toggleOption = "insert";
                          $('i#bookmarkIcon').removeClass("fa-star-o").addClass("fa-star");
                        }

                        $.ajax({
                          url: "${path}/bookmark/toggleBookmark",
                          type: "POST",
                          dataType: "JSON",
                          data: {"toggleOption" : toggleOption,
                                  userCode: "${loginMember.userCode}",
                                  parkingCode: $('input#parkingCode').val() },
                          success: function (data) {
                            console.log("bookmark toggle successful!");
                            if(localStorage.hasOwnProperty("bookmarkList")){
                              localStorage.removeItem("bookmarkList");
                            }
                            localStorage.setItem("bookmarkList", JSON.stringify(data));
                            // var data = JSON.parse(localStorage.getItem("parkingList"));
                          },
                          error: function(data){
                            console.log("bookmark toggle failed!");
                          }
                        });
                      </c:if>

                      //enable tooltips on the page
                      $('[data-toggle="tooltip"]').tooltip();
                    });
                  });

                </script>

              </div>
              <div class="col">
                <span class="text-muted"><a href="#"><i class="fa fa-edit text-twitter">&nbsp;&nbsp;</i>Review</a></span>
                <div class="font-weight-bold">1K</div>
              </div>
            </div>
          </div>
        </div>
        <div class="card mb-4 rounded">
          <div class="card-body">
            <!-- <h3 class="mr-auto text-center my-4">Information</h3> -->
            <input type="hidden" name="parkingCode" id="parkingCode" value="" />

            <div class="row my-2">
              <div class="col-md-4 border-right"><i class="fa fa-map-signs">&nbsp;&nbsp;</i>Parking lot Name</div>
              <div class="col-md-8" id="parkingName"><i class=""></i></div>
            </div>
            <div class="row my-2">
              <div class="col-md-4 border-right"><i class="fa fa-map-marker">&nbsp;&nbsp;</i>Address</div>
              <div class="col-md-8" id="addr"></div>
            </div>
            <div class="row my-2">
              <div class="col-md-4 border-right"><i class="fa fa-hourglass">&nbsp;&nbsp;</i>Operation Time</div>
              <div class="col-md-8" id="operationTime"></div>
            </div>
            <div class="row my-2">
              <div class="col-md-4 border-right"><i class="fa fa-info-circle">&nbsp;&nbsp;</i>Operation Rule Name</div>
              <div class="col-md-8" id="operationRuleNm"></div>
            </div>
            <div class="row my-2">
              <div class="col-md-4 border-right"><i class="fa fa-exclamation-triangle">&nbsp;&nbsp;</i>Parking Type Name</div>
              <div class="col-md-8" id="parkingTypeNm"></div>
            </div>
            <div class="row my-2">
              <div class="col-md-4 border-right"><i class="fa fa-globe">&nbsp;&nbsp;</i>Web Link</div>
              <div class="col-md-8 "><a href="#">https://example.com</a></div>
            </div>
            <div class="row my-2">
              <div class="col-md-4 border-right"><i class="fa fa-car">&nbsp;&nbsp;</i>Capacity</div>
              <div class="col-md-8" id="capacity"></div>
            </div>
            <div class="row my-2">
              <div class="col-md-4 border-right"><i class="fa fa-car">&nbsp;&nbsp;</i>Current Parking</div>
              <div class="col-md-8" id="curParking"></div>
            </div>
            <div class="row my-2">
              <div class="col-md-4 border-right"><i class="fa fa-phone">&nbsp;&nbsp;</i>Tel</div>
              <div class="col-md-8" id="tel"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div>
  </div>
</div>

  <script>
    $('#myModal').on('shown.bs.modal', function () {
      $('#myInput').trigger('focus')
    })
  </script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/mypageHeader.jsp">
  <jsp:param name="pageTitle" value="Bookmark" />
</jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}" />

  <section class="py-4 subMenu-container">

    <!-- CSS -->
    <style>
      <%for(int i = 1; i < 1000; i++){%>
        object#daum\:roadview\:<%=i%> {
          position: relative !important;
        }
      <%}%>
    </style>

    <div class="card card-fluid">
      <h6 class="card-header">Bookmark</h6>
      <!-- .card-body -->
      <div class="card-body">
        <!-- .media -->
        <div class="media mb-2">
          <div class="media-body pl-3">
            <h3 class="card-title">Bookmark list</h3>
            <h6 class="card-subtitle text-muted">Saved Parking locations</h6>
            <p class="card-text">
              <small>You have saved <b id="parkingNum"></b>&nbsp;parking spaces.</small>
            </p>
            <!-- The avatar upload progress bar -->
            <!-- <div id="progress-avatar" class="progress progress-xs fade">
              <div class="progress-bar progress-bar-striped progress-bar-animated bg-success" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
            </div> -->
            <!-- /avatar upload progress bar -->
          </div>
          <!-- /.media-body -->
        </div>
        <!-- /.media -->
        <!-- form -->


        <!-- 카카오 Map container-->
        <div class="container map-container" id="map-container-google-2" style="height: 500px">

          <button class="btn btn-sm btn-outline-primary mt-4 mb-3" onclick="loadMapInfo();"><i class="fa fa-map-marker">&nbsp;&nbsp;</i>Where Am I?</button>
          <div id="map" class="card shadow-sm row mx-auto" style="width:100%;height:400px;" style="border:0;"></div>
        </div>

        <style>
          .map-container-2{
            overflow:hidden;
            padding-bottom:56.25%;
            position:relative;
            height:0;
          }
          .map-container-2 iframe{
            left:0;
            top:0;
            height:100%;
            width:100%;
            position:absolute;
          }
        </style>

        <!-- manipulate data -->
        <script>
          $(function(){
            $('#listScroll').show();
            $('#listScrollTitle').show();
            $('#listScrollTitle').html('<i class="fa fa-bookmark"></i>&nbsp;&nbsp;My Bookmarks');

            loadMapInfo();
          });
          
          /* Parking locations : 
             1. current geolocation 
             2. bookmarked parking list & geolocation */
          function loadMapInfo(){
            navigator.geolocation.getCurrentPosition(function(pos){

              loadParkingList(pos);

            }, geo_error, geo_options);
          }

          function geo_error() {
            alert("[ERROR] Geolocation api cannot use geolocation data.");
          }

          //geo options
          var geo_options = {
            enableHighAccuracy: true, 
            maximumAge        : 30000, 
            timeout           : 27000
          };

          function loadParkingList(pos){
            // marker : bookmarked locations
            var positions = [];

            $.ajax({
              url: "${path}/bookmark/bookmarkList",
              type: "POST",
              data: { "userCode": "${loginMember.userCode}" },
              dataType: "JSON",
              success: function (data) {
                var listScroll = $("#listScroll");
                console.log(data);

                if(listScroll != null)
                  listScroll.empty();

                $('#parkingNum').html(Object.keys(data).length);

                // for(var d in data) {
                $.each(data, function(d,item) {

                  var aTag = $("<a class='list-group-item list-group-item-action'>");
                  var span0 = $("<span id="+d+">");
                  var span1 = $("<span id='pName'>").html((Number(d)+1)+". "+data[d]["parkingName"]+"<br>");
                  var span2 = $("<span>").html("&nbsp;&nbsp;&nbsp;&nbsp;Addr. : "+data[d]["addr"]+"<br>");
                  var span3 = $("<span>").html("&nbsp;&nbsp;&nbsp;&nbsp;Parking Code : "+data[d]["parkingCode"]+"<br>");
                  var span4 = $("<span>").html("&nbsp;&nbsp;&nbsp;&nbsp;LAT. : "+data[d]["latitude"]+"<br>");
                  var span5 = $("<span>").html("&nbsp;&nbsp;&nbsp;&nbsp;LNG. : "+data[d]["longitude"]+"<br>");
                  var div = $("<div class='mt-2'>");
                  // var btnStr = "<button class='btn btn-sm btn-outline-info mr-1' "
                  //                       // + "data-toggle='modal' "
                  //                       // + "data-target='#myModal' "
                  //                       + "onclick='parkingListPopup();' "
                  //                       + ">More Info</button>";
                  var btnStr = "<button class='btn btn-sm btn-outline-info mx-1' "
                                        + "id='parking" + d + "'>"
                                        + "Details</button>";
                  var infoBtn = $(btnStr);
                  var input = $("<button type='button' class='btn btn-sm btn-info pay' onclick='location.href=\"${path}/board/reviewList\"'"  
                                      + "value=''><i class='fa fa-chevron-right'>&nbsp;Review"
                              + "</button>");
                  div.append(infoBtn).append(input);
                  span0.append(span1).append(span2).append(span3).append(span4).append(span5).append(div);
                  aTag.append(span0);
                  listScroll.append(aTag);	

                  //Marker : ParkingList
                  var lat = data[d]["latitude"];
                  var lng = data[d]["longitude"];

                  positions.push({title: data[d]["parkingName"], latlng: new kakao.maps.LatLng(lat, lng)});

                  $('#parking'+d).click(function(){
                    var parkingName = data[d]["parkingName"];
                    var addr = data[d]["addr"];
                    var parkingCode = data[d]["parkingCode"];
                    var latitude = data[d]["latitude"];
                    var longitude = data[d]["longitude"];


                    //show bootstrap modal

                    if($('#popRoadView').val() != null)
                      $('#popRoadView').empty();

                    listPopRoadView(latitude,longitude); // 팝업로드뷰 생성

                    $('#myModal').modal('show');

                    $('#parkingName').html(parkingName);
                    $('#addr').html(addr);
                    $('#parkingCode').val(parkingCode);
                    $('#latitude').html(latitude);
                    $('#longitude').html(longitude);

                  });
                });

                localStorage.setItem("positions", JSON.stringify(positions));

                if(data.length ==0){
                  listScroll.append("<div class='card card-text text-center' style='height:70px;'><br>"
                    + "Bookmarks are Empty!<br></div>");
                  localStorage.clear();
                }

                loadKakaoMap(pos);
              },
              error: function (data) { // 데이터 통신에 실패
                console.log("서버 전송 실패");
              }
            });
          }

          function listPopRoadView(lat, lon) {
            var roadviewContainer = document.getElementById('popRoadView'); //로드뷰를 표시할 div
            var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
            var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

            var position = new kakao.maps.LatLng(lat, lon);

            // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
            roadviewClient.getNearestPanoId(position, 800, function(panoId) {
              roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
            });
          }

         /* Show Kakao Map */
          function loadKakaoMap(pos){
            if($("#map") != null)
              $("#map").empty();

            var lat = pos.coords.latitude;
            var lng = pos.coords.longitude;

            console.log("LAT: " +lat + " LNG : " + lng)

            var mapContainer = document.getElementById('map'); // 지도를 표시할 container
            var mapOption = { 
                center: new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표
                level: 9 // 지도의 확대 레벨
            };

            var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성
  
            // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성
            // 지도 타입 컨트롤을 지도에 표시
            var mapTypeControl = new kakao.maps.MapTypeControl();
            map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

            // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
            var zoomControl = new kakao.maps.ZoomControl();
            map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
  

            //Markers
            var positions = [];

            if(localStorage.hasOwnProperty("positions"))
              positions = JSON.parse(localStorage.getItem("positions"));

            positions.push({title: "curLoc", latlng: new kakao.maps.LatLng(lat, lng)});
            console.log(positions);
              

            // Prepare Marker information
            var imageSrc, // 마커이미지의 주소입니다    
                imageSize, // 마커이미지의 크기입니다
                imageOption = {offset: new kakao.maps.Point(0, 0)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
            var markerPosition = new kakao.maps.LatLng(lat, lng); // 마커가 표시될 위치입니다
            var marker;
            for (var i = 0; i < positions.length; i ++) {
              // 마커 이미지를 생성합니다    
              if(positions[i].title == "curLoc"){
                imageSrc = '${path}/resources/images/imhere.png', // 마커이미지의 주소입니다    
                imageSize = new kakao.maps.Size(42, 42); 
                markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
              }
              else{
                imageSrc = "${path}/resources/images/sign.png";
                imageSize = new kakao.maps.Size(30, 30); 
                markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
              }
              
              // 마커를 생성합니다
              marker = new kakao.maps.Marker({
                  map: map, // 마커를 표시할 지도zz
                  position: new kakao.maps.LatLng(positions[i].latlng.Ha, positions[i].latlng.Ga), // 마커를 표시할 위치
                  title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                  image : markerImage // 마커 이미지 
              });
              marker.setMap(map);
              // var infowindow = new kakao.maps.InfoWindow({
              //     content: positions[i].content // 인포윈도우에 표시할 내용
              // });
              // kakao.maps.event.addListener(locMarker, 'mouseover', makeOverListener(map, locMarker, infowindow));
              // kakao.maps.event.addListener(locMarker, 'mouseout', makeOutListener(infowindow));

            }
            // 마커 이벤트
            // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
            // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            var iwContent = '<div style="padding:2px;" class="card">I\'m Here!</div>';

            // 인포윈도우를 생성합니다
            var infowindow = new kakao.maps.InfoWindow({
                content : iwContent
            });

            // // 마커에 마우스오버 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseover', function() {
              // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
                infowindow.open(map, marker);
            });

            // 마커에 마우스아웃 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseout', function() {
                // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
                infowindow.close();
            });



            /* LOCATION INFO */
            var center = map.getCenter(); // 지도의 현재 중심좌표를 얻어옵니다 
            var level = map.getLevel(); // 지도의 현재 레벨을 얻어옵니다
            var mapTypeId = map.getMapTypeId(); // 지도타입을 얻어옵니다
            var bounds = map.getBounds(); // 지도의 현재 영역을 얻어옵니다 
            var swLatLng = bounds.getSouthWest(); // 영역의 남서쪽 좌표를 얻어옵니다 
            var neLatLng = bounds.getNorthEast(); // 영역의 북동쪽 좌표를 얻어옵니다 
            var boundsStr = bounds.toString(); // 영역정보를 문자열로 ((남,서), (북,동)) 형식 조회
            var message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
            message += '경도 ' + center.getLng() + ' 이고 <br>';
            message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
            message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
            message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
            message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';
            
            console.log(message); // 개발자도구를 통해 직접 message 내용을 확인
          }
          // 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
          function makeOverListener(map, marker, infowindow) {
            return function() {
              infowindow.open(map, marker);
            };
          }

          // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
          function makeOutListener(infowindow) {
            return function() {
              infowindow.close();
            };
          }
        </script>
            
      </div>
      <!-- /.card-body -->
    </div>

    <!-- Modal : 'Details' -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="modalLabelParkingName" aria-hidden="true" data-keyboard="false" data-backdrop="static">
      <script>
        $(function(){
          <% for(int i =0 ; i<1000; i++) { %>
            $('object#daum\\:roadview\:<%=i %>').css({"position": "relative !important"});
          <% } %>

        });
      </script>
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
              </div>
              <div class="card-body text-center">
                <div class="row">
                  <div class="col border-right">
                    <span class="text-muted" id="toggleTooltip"><a href="javascript:;" id="bookmarkToggleBtn">
                      <i class="fa fa-star-o text-twitter" id="bookmarkIcon">&nbsp;&nbsp;</i>Bookmark</a></span>
                    <div class="font-weight-bold"></div>

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
                  <div class="col-md-8" id="parkingName"></div>
                </div>
                <div class="row my-2">
                  <div class="col-md-4 border-right"><i class="fa fa-map-marker">&nbsp;&nbsp;</i>Address</div>
                  <div class="col-md-8" id="addr"></div>
                </div>
                <!-- <div class="row my-2">
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
                </div> -->
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

  </section>

<jsp:include page="/WEB-INF/views/common/mypageFooter.jsp" />
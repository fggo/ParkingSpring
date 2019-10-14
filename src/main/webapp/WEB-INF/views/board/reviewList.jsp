<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.parking.board.model.vo.Review" %>
<%@ page import="com.parking.history.model.vo.UserHistory" %>

<%
  List<Review> list = (ArrayList<Review>)request.getAttribute("reviewList");
  List<UserHistory> userHistoryList = (ArrayList<UserHistory>)request.getAttribute("userHistoryList");
  String pageBar = (String)request.getAttribute("pageBar");
  int cPage = (Integer)request.getAttribute("cPage");
%>

<%@ include file="/WEB-INF/views/common/mypageHeader.jsp" %>
  <section class="py-4 subMenu-container">

    <!-- CSS -->
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/review.css">

    <style>
      <%for(int i = 1; i < 1000; i++){%>
        object#daum\:roadview\:<%=i%> {
          position: relative !important;
        }
      <%}%>
    </style>

    <div class="card card-fluid">

      <h6 class="card-header">
        <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-primary rounded shadow-sm">
          <!-- <img class="mr-3" src="" alt="" width="48" height="48"> -->
          <!-- <img src="<%=request.getContextPath() %>/images/qna.png" class="mr-3" width="60px"> -->
          <i class="fa fa-edit text-white mr-3 my-2" style="font-size:42px;"></i>
          <div class="lh-100 ml-2">
            <p class="h5 mb-0 text-white lh-100">&nbsp;&nbsp;Review</p>
            <small>Since 2019.09</small>
          </div>
        </div>
      </h6>

      <div class="card-body">
        <!-- .media -->
        <div class="media mb-2">
          <!-- <div class="user-avatar user-avatar-xl fileinput-button">
            <div class="fileinput-button-label">Change photo</div>
            <img src="<%=request.getContextPath() %>/images/profile.png" alt="User Avatar">
            <input id="fileupload-avatar" type="file" name="avatar"> </div> -->
          <!-- .media-body -->
          <div class="media-body pl-3 my-0 py-0">
            <h3 class="card-title">Review list</h3>
            <h6 class="card-subtitle text-muted">Reviews by <b><%=loginMember.getUserName() %></b></h6>
            <p class="card-text">
              <small>You have visited <b id="parkingNum"></b>&nbsp; parking lots.</small>
            </p>
          </div>
          <div class="row d-flex mt-5">
            <% if(loginMember != null){ %>
              <!-- <input type="button" value="write" class= "btn btn-sm btn-outline-primary mr-4 mb-1" id="write-add" onclick=""> -->
            <%} %>
          </div>
          <!-- /.media-body -->
        </div>
        <!-- /.media -->
        <!-- form -->
        <table class="table table-sm" id='qna_table' style="font-size:14px;">
          <thead>
            <tr>
              <th class="text-center">Hist.NO.</th>
              <th class="text-center">Author</th>
              <th class="text-center">Title</th>
              <th class="text-center">Content</th>
              <!-- <th class="text-center">Picture</th> -->
              <th class="text-center">Rating</th>
            </tr>
          </thead>
          <tbody>
            
            <script>
            </script>
            <% for(Review r : list){ %>
            <tr>
              <td class="text-center"><%=r.getReviewUserHistoryNo() %> </td>
              <td class="text-center">
                <div class="media text-muted pt-3">
                  <% if(loginMember.getUserRenamedFilename() != null) { %>
                  <img class="profile-pic" src="<%=request.getContextPath()%>/upload/member/<%=loginMember.getUserRenamedFilename() %>" width="32" height="32" style="border-radius: 20%; border:1px solid white;" />
                  <% } else { %>
                  <svg class="bd-placeholder-img mr-2 rounded " width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"> <title>Placeholder</title><rect width="100%" height="100%" fill="#132535" class="bg-secondary"></rect>
                    <text x="50%" y="50%" fill="#132535" dy=".3em">32x32</text>
                  </svg>
                  <% } %>
                  <!-- <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray"> -->
                  <p class="media-body pb-4 mb-0 small lh-125">
                    <strong class="d-block text-gray-dark"><%=loginMember.getUserName() %></strong>
                  </p>
                </div>
              </td>
              <td class="text-center">
                <a href="<%=request.getContextPath() %>/board/reviewContentView?reviewNo=<%=r.getReviewNo() %>">
                  <%=r.getReviewTitle() %>
                </a>
              </td>
              <script>
                // function ajaxViewReviewContent(){
                //   $.ajax({
                //     type: "POST",
                //     url: "<%=request.getContextPath() %>/board/reviewContentView",
                //     dataType: "JSON",
                //     data: {"no": "<%=r.getReviewNo()%>"},
                //     success: function(data){
                //       var html = $('<div>').html(data);

                //       $('div#mypage-container').html(html.find('section#subMenu-container'));
                //     },
                //     error: function (data) { // 데이터 통신에 실패
                //       console.log("JSON data failed to retrieve!");
                //     }
                //   });
                // }
              </script>
              <td>
                  <%= r.getReviewContent().substring(0, java.lang.Math.min(80,r.getReviewContent().length())) %>
                </a>
              </td>
              <!-- <td> -->
                <%-- <% if(r.getReviewOriginalFile() != null){ %>
                  <img src="<%=request.getContextPath() %>/images/file.png" width="16px">
                <% } %> --%>
              <!-- </td> -->
              <td class="text-center">
                  <!-- String []checkedArr = new String[5];
                  int rating = r.getReviewRating();
                  System.out.println(rating);

                  for(int i=0; i < checkedArr.length; i++){
                    if(i < rating-1)
                      checkedArr[i]="";
                    else
                      checkedArr[i]="checked";
                    System.out.print(checkedArr[i] + " ");
                  }
                    System.out.println(); -->
                  <%=r.getReviewRating() %>
            <!-- star ratings -->
                  <%-- <span class="starrating risingstar d-flex justify-content-center flex-row-reverse">
                    <input type="radio" id="star5" name="reviewRating" value="5" <%=checkedArr[4]%> disabled readonly /><label for="star5" title="5 star"></label>
                    <input type="radio" id="star4" name="reviewRating" value="4" <%=checkedArr[3]%> disabled readonly /><label for="star4" title="4 star"></label>
                    <input type="radio" id="star3" name="reviewRating" value="3" <%=checkedArr[2]%> disabled readonly /><label for="star3" title="3 star"></label>
                    <input type="radio" id="star2" name="reviewRating" value="2" <%=checkedArr[1]%> disabled readonly /><label for="star2" title="2 star"></label>
                    <input type="radio" id="star1" name="reviewRating" value="1" <%=checkedArr[0]%> disabled readonly /><label for="star1" title="1 star"></label>
                  </span>	 --%>
              </td>
                
            </tr>
            <% } %>
          </tbody>
        </table>
        <script>
          function getRandomColor() {
            var letters = '0123456789ABCDEF';
            var color = '#';
            for (var i = 0; i < 6; i++) {
              color += letters[Math.floor(Math.random() * 16)];
            }
            return color;
          }

        </script>

        <small class="d-block text-right mt-3"><a href="#">All updates</a></small>

        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
            <%=pageBar %>
          </ul>
        </nav>
          <!-- <a class="dropdown-item" href="javascript:;" onclick="ajaxRequestPage();"><i class="fa fa-question-circle-o">&nbsp;&nbsp;</i>Q&A Board</a> -->
        <script>
          function ajaxRequestPage(pageNo){
            var url = "<%=request.getContextPath() %>/board/reviewList";
            $.ajax({
              type: "POST",
              url: url,
              dataType: "html",
              data: {cPage: pageNo},
              success: function(data){
                html = $('<div>').html(data);
                console.log(html.find('div#review-container'));
                // var tag = $("<h3>").html(data).css("color", "blue");
                // $('#content').append(tag);
                // $('#qna_table tbody')
                  // location.href="<%=request.getContextPath() %>/board/qnaBoardList?cPage=" + pageNo;

                $('main#review-main').html(html.find('div#review-container'));
              },
              error: function(request, status, error){
                console.log("error 함수 실행!");
                console.log(request);
                console.log(status);
                console.log(error);
              },
            });
          }

          $(function(){
            $('#listScroll').show();
            $('#listScrollTitle').show();
            $('#listScrollTitle').html('<i class="fa fa-history"></i>&nbsp;&nbsp;My History');

            loadParkingList("userHistoryParkingList");
          });

          function loadParkingList(mapping){
            $.ajax({
              url: "<%=request.getContextPath()%>/history/" + mapping,
              type: "POST",
              data: { "userCode": "<%=loginMember.getUserCode()%>" },
              dataType: "JSON",
              success: function (data) {
                var listScroll = $("#listScroll");

                if(listScroll != null)
                  listScroll.empty();

                $('#parkingNum').html(Object.keys(data).length);

                // marker : bookmarked locations
                var positions_review = [];

                $.each(data, function(d, item) {

                  var aTag = $("<a class='list-group-item list-group-item-action'>");
                  // if(mapping == "userHistoryParkingList"){ }
                  // if(mapping == "userHistoryList"){ }
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
                  var input = $("<button type='button' class='btn btn-sm btn-info pay'"
                                + "onclick='writeReview(" + data[d]["parkingCode"]+ ")' value=''><i class='fa fa-edit'>&nbsp;</i>Review</button>");
                  div.append(infoBtn).append(input);
                  span0.append(span1).append(span2).append(span3).append(span4).append(span5).append(div);
                  aTag.append(span0);

                  listScroll.append(aTag);	

                  //Marker : ParkingList
                  var lat = data[d]["latitude"];
                  var lng = data[d]["longitude"];

                  positions_review.push({title: data[d]["parkingName"], latlng: new kakao.maps.LatLng(lat, lng)});

                  $('#parking'+d).click(function(){
                    var parkingName = data[d]["parkingName"];
                    var addr = data[d]["addr"];
                    var parkingCode = data[d]["parkingCode"];
                    var latitude = data[d]["latitude"];
                    var longitude = data[d]["longitude"];

                    if($("#popRoadView").val() != null) {
                      $("#popRoadView").empty();
                    }

                    listPopRoadView(latitude,longitude); // 팝업로드뷰 생성


                    //show bootstrap modal
                    $('#myModal').modal('show');

                    $('#parkingName').html(parkingName);
                    $('#addr').html(addr);
                    $('#parkingCode').val(parkingCode);
                    $('#latitude').html(latitude);
                    $('#longitude').html(longitude);

                  });
                });

                if(localStorage.hasOwnProperty("positions_review"))
                  localStorage.removeItem("positions_review");

                localStorage.setItem("positions_review", positions_review);

                if(data.length ==0){
                  listScroll.append("<div class='card card-text text-center' style='height:70px;'><br>"
                    + "No Reviews from you!<br></div>");
                }

              },
              error: function (data) { // 데이터 통신에 실패
                console.log("서버 전송 실패");
              }
            });
          }

          function writeReview(parkingCode){
            var url = "<%=request.getContextPath() %>/board/reviewWrite";
            $.ajax({
              type: "POST",
              url: url,
              dataType: "html",
              data: {userCode: "<%=loginMember.getUserCode() %>",
                     parkingCode: parkingCode},
              success: function(data){
                html = $('<div>').html(data);

                $('div#mypage-container').html(html.find('section.subMenu-container'));

                // <div id="rw-modal-container"></div>
                // $('div#rw-modal-container').html(
                //   html.find('section.subMenu-container > div#reviewWriteModel')
                // );

                $('#reviewWriteModal').modal('show');

              },
              error: function(request, status, error){
                console.log("error 함수 실행!");
                console.log(request);
                console.log(status);
                console.log(error);
              },
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
        </script>
        <script>
          // use REGEX to match 'roadview block' and
          // change style attribute!!!
          // <object id="daum:roadview:1"></object>

          $(function(){
            // id="daum:roadview:1"
            // $('object').filter(function() {
            //   return this.id.match(/^daum:roadview:\d.$/);
            // })
            // .css({"position": "relative !important", });
            $('object').css({"position": "relative !important", });
          });
        </script>


        <!-- Review List container-->
        <!-- <div class="container review-container" id="" style="height: 500px">
        </div> -->
      </div>
    </div>
  </section>

  <!-- <script>
    $('#myModal').on('shown.bs.modal', function () {
      $('#myInput').trigger('focus')
    })
  </script> -->


  <!-- Modal : 'Review' -->
  <div id="rw-modal-container"></div>

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

<%@ include file="/WEB-INF/views/common/mypageFooter.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.parking.api.model.vo.Parking" %>
<%@ page import="com.parking.history.model.vo.UserHistory" %>
<%@ page import="com.parking.board.model.vo.Review" %>

<%@ include file="/WEB-INF/views/common/mypageHeader.jsp" %>

<%
  Parking parking = (Parking)request.getAttribute("parking");
  UserHistory userhistory = (UserHistory)request.getAttribute("userhistory");
  Review r = (Review)request.getAttribute("review");

  int rating = r.getReviewRating();
  String []checkedArr = new String[5];

  for(int i=0; i < checkedArr.length; i++){
    if(i < rating-1)
      checkedArr[i]="";
    else
      checkedArr[i]="checked";
  }
%>

  <section class="py-4 subMenu-container">

    <!-- CSS -->
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/review.css">

    <div class="card card-fluid">
      <h6 class="card-header">Review Content</h6>
      <!-- .card-body -->
      <div class="card-body">
          <!-- <h3 class="mr-auto text-center my-4">Information</h3> -->
        <form id="updateFrm" method="POST">
        <!-- enctype="multipart/form-data" > -->

          <div class="media mb-3">
            <!-- avatar -->
            <div class="avatar-wrapper my-0 mx-3">
              <% if(loginMember.getUserRenamedFilename() != null) { %>
              <img class="profile-pic" src="<%=request.getContextPath()%>/upload/member/<%=loginMember.getUserRenamedFilename() %>" />
              <% } else { %>
              <img class="profile-pic" src="" />
              <% } %>

              <!-- <div class="upload-button">
                <i class="fa fa-camera" aria-hidden="true"></i>
              </div> -->
              <!-- <input class="file-upload form-control" type="file" accept="image/*" name="new_up_file" /> -->
              <input class="" type="hidden" name="old_up_file_ori" value="<%=loginMember.getUserOriginalFilename() %>" />
              <input class="" type="hidden" name="old_up_file_re" value="<%=loginMember.getUserRenamedFilename() %>" />
            </div>

            <!-- .media-body -->
            <div class="media-body pl-3">
              <h4 class="card-title"><%=loginMember.getUserName() %>'s Review on '<%=parking.getParkingName()%>'</h4>
              <p class="card-text">
                <small class="card-subtitle text-muted">visited <%=userhistory.getUserHistoryParkingDate() %>.
                </small>
              </p>
              <!-- The avatar upload progress bar -->
              <div id="progress-avatar" class="progress progress-xs fade">
                <div class="progress-bar progress-bar-striped progress-bar-animated bg-success" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
              </div>
              <!-- /avatar upload progress bar -->
            </div>
          </div>

          <!-- form row -->
          <input type="hidden" name="parkingCode" id="parkingCode" value="<%=parking.getParkingCode() %>" />
          <input type="hidden" name="userCode" id="userCode" value="<%=loginMember.getUserCode() %>" />

          <div class="form-row">
            <label for="parkingName" class="col-md-3"><i class="fa fa-map-signs">&nbsp;&nbsp;</i>Parking lot Name</label>
            <div class="col-md-9 mb-3">
              <input type="text" class="form-control" name="name" id="parkingName" value="<%=parking.getParkingName() %>" readonly/>
            </div>
          </div>
          <div class="form-row">
            <label for="addr" class="col-md-3"><i class="fa fa-map-marker">&nbsp;&nbsp;</i>Address</label>
            <div class="col-md-9 mb-3">
              <input type="text" class="form-control" name="name" id="addr" value="<%=parking.getAddr() %>" readonly/>
            </div>
          </div>
          <div class="form-row">
            <!-- form column -->
            <label for="name" class="col-md-3"><i class="fa fa-user">&nbsp;&nbsp;</i>User Name</label>
            <!-- /form column -->
            <!-- form column -->
            <div class="col-md-9 mb-3">
              <input type="text" class="form-control" name="userName" id="userName" value="<%=loginMember.getUserName() %>" readonly/>
            </div>
            <!-- /form column -->
          </div>
          <div class="form-row">
            <!-- form column -->
            <label for="reviewTitle" class="col-md-3"><i class="fa fa-title">&nbsp;&nbsp;</i>Review Title</label>
            <!-- /form column -->
            <!-- form column -->
            <div class="col-md-9 mb-3">
              <input type="text" class="form-control" name="reviewTitle" id="userName" value="<%=r.getReviewTitle()%>" readonly/>
            </div>
            <!-- /form column -->
          </div>
          <div class="form-row">
            <label for="content" class="col-md-3"><i class="fa fa-edit">&nbsp;&nbsp;</i>Review Content</label>
            <div class="col-md-9 ">
              <textarea type="text" class="form-control" id="reviewContent" name="reviewContent" rows="3" style="resize:none;" placeholder="write review..." readonly><%=r.getReviewContent()%></textarea>
              <small class="text-muted">300 chars max.</small>
            </div>

            <!-- star ratings -->
            <div class="mx-auto">
              Ratings 
              <div class="starrating risingstar d-flex justify-content-center flex-row-reverse">
                  <input type="radio" id="star5" name="reviewRating" value="5" <%=checkedArr[4]%> disabled readonly /><label for="star5" title="5 star"></label>
                  <input type="radio" id="star4" name="reviewRating" value="4" <%=checkedArr[3]%> disabled readonly /><label for="star4" title="4 star"></label>
                  <input type="radio" id="star3" name="reviewRating" value="3" <%=checkedArr[2]%> disabled readonly /><label for="star3" title="3 star"></label>
                  <input type="radio" id="star2" name="reviewRating" value="2" <%=checkedArr[1]%> disabled readonly /><label for="star2" title="2 star"></label>
                  <input type="radio" id="star1" name="reviewRating" value="1" <%=checkedArr[0]%> disabled readonly /><label for="star1" title="1 star"></label>
              </div>
            </div>	
            <!-- /form column -->
          </div>
          <hr>
          <div class="form-actions row justify-content-center">
            <button type="button" class="btn btn-outline-secondary mx-1" id="cancelBtn">Back to List</button>
            <!-- <button type="button" class="btn btn-outline-danger mr-auto" id="deleteBtn">Delete Account</button> -->
          </div>
            <!-- /.form-actions -->
        </form>
      </div>
    </div>
    <!-- /.card-body -->

    <script>
      $(function(){

        $('button#cancelBtn').on('click', function(){
          console.log("hello cancel");
          location.href= "<%=request.getContextPath() %>/board/reviewList";
        })

      });

    </script>

  </section>


  <%@ include file="/WEB-INF/views/common/mypageFooter.jsp" %>
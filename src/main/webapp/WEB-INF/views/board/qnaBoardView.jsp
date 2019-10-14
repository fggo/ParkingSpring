<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%@ page import="com.parking.board.model.vo.QnaBoard" %>

<% 
  QnaBoard q = (QnaBoard)request.getAttribute("qnaboard");
%>

  <section class="py-4 subMenu-container container mt-5 pt-5">
  <!-- JQUERY -->
  <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

  <!-- JAVASCRIPT -->
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


    <!-- CSS -->
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/avatar.css">

    <div class="card card-fluid">
      <h6 class="card-header">Question Content</h6>
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
            </div>

            <!-- .media-body -->
            <div class="media-body pl-3">
              <h3 class="card-title">UserCode <%=q.getUserCode() %>'s Question</h3>
              <p class="card-text">
                <small class="card-subtitle text-muted">questioned on <%=q.getQnaCreatedDate() %>.
                </small>
              </p>
            </div>
          </div>

          <!-- form row -->
          <input type="hidden" name="userCode" id="userCode" value="<%=q.getUserCode() %>" />

          <div class="form-row">
            <!-- form column -->
            <label for="qnaTitle" class="col-md-3"><i class="fa fa-title">&nbsp;&nbsp;</i>Question Title</label>
            <!-- /form column -->
            <!-- form column -->
            <div class="col-md-9 mb-3">
              <input type="text" class="form-control" name="qnaTitle" id="qnaTitle" value="<%=q.getQnaTitle()%>" readonly/>
            </div>
            <!-- /form column -->
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
            <label for="content" class="col-md-3"><i class="fa fa-edit">&nbsp;&nbsp;</i>Question Content</label>
            <div class="col-md-9 ">
              <textarea type="text" class="form-control" id="qnaContent" name="qnaContent" rows="3" style="resize:none;" placeholder="" readonly><%=q.getQnaContent()%></textarea>
              <small class="text-muted">300 chars max.</small>
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

        $('button#cancelBtn').click(function(){
          location.href= "<%=request.getContextPath() %>/board/qnaBoardList";
        })

      });

    </script>

  </section>


  <%@ include file="/WEB-INF/views/common/mypageFooter.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.parking.member.model.vo.Member" %>

<%@ include file="/WEB-INF/views/common/mypageHeader.jsp" %>

<%
  String smsChk = loginMember.getUserSmsYn() ==1? "checked": "";
  String emailChk = loginMember.getUserEmailYn() ==1? "checked": "";
%>

  <section class="py-4 subMenu-container">
    <div class="card card-fluid">
      <h6 class="card-header">Account Settings</h6>
      <!-- .card-body -->
      <div class="card-body">

        <form action="<%=request.getContextPath() %>/member/memberUpdate" method="POST" >

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
              <!-- <input class="file-upload form-control" type="file" accept="image/*" name="new_up_file" />
              <input class="" type="hidden" name="old_up_file_ori" value="<%=loginMember.getUserOriginalFilename() %>" />
              <input class="" type="hidden" name="old_up_file_re" value="<%=loginMember.getUserRenamedFilename() %>" /> -->
            </div>
            <!-- /avatar -->

            <!-- .media-body -->
            <div class="media-body pl-3">
              <h3 class="card-title"><%=loginMember.getUserName() %>'s Profile</h3>
              <p class="card-text">
                <!-- <small class="card-subtitle text-muted"> Click the avatar to change your photo. <br>
                  JPG, GIF or PNG 400x400, &lt; 2 MB.</small> -->
              </p>
              <!-- The avatar upload progress bar -->
              <div id="progress-avatar" class="progress progress-xs fade">
                <div class="progress-bar progress-bar-striped progress-bar-animated bg-success" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
              </div>
              <!-- /avatar upload progress bar -->
            </div>
          </div>
          <!-- form row -->
          <div class="form-row">
            <!-- form column -->
            <label for="input02" class="col-md-3">Phone</label>
            <!-- /form column -->
            <!-- form column -->
            <div class="col-md-9 mb-3">
              <input type="text" class="form-control" name="phone" value="<%=loginMember.getUserPhone() %>" readonly>
            </div>
            <!-- /form column -->
          </div>
          <div class="form-row">
            <!-- form column -->
            <label for="input02" class="col-md-3">User Name</label>
            <!-- /form column -->
            <!-- form column -->
            <div class="col-md-9 mb-3">
              <input type="text" class="form-control" name="name" value="<%=loginMember.getUserName() %>" readonly>
            </div>
            <!-- /form column -->
          </div>
          <div class="form-row">
            <!-- form column -->
            <label for="input03" class="col-md-3" >Address</label>
            <!-- /form column -->
            <!-- form column -->
            <div class="col-md-9 mb-3">
              <textarea type="text" class="form-control" id="full-addr" rows="3" style="resize:none;" readonly><%=loginMember.getUserAddr() %></textarea>
              <small class="text-muted">Appears on your profile page, 300 chars max.</small>
              <input type="hidden" name="addr" id="addr" value="return getTextareaAddr();">
              <script>
                function getTextareaAddr(){
                  return $('textarea#full-addr').val();
                }
              </script>
            </div>
            <!-- /form column -->
          </div>
          <div class="form-row">
            <label class="col-md-3">SMS</label>
            <div class="col-md-9 mb-3">
              <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" name="smsYn" id="smsYn" disabled readonly <%=smsChk %> />
                <label class="custom-control-label" for="smsYn">Subscribe to SMS</label>
              </div>
            </div>
          </div>
          <div class="form-row">
            <label class="col-md-3">Email</label>
            <div class="col-md-9 mb-3">
              <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" name="emailYn" id="emailYn" disabled readonly <%=emailChk %> />
                <label class="custom-control-label" for="emailYn">Subscribe to Email</label>
              </div>
            </div>
          </div>
          <hr>
          <div class="form-actions row" >
            <button type="submit" class="btn btn-primary mx-auto">Update Profile</button>
          </div>
          <!-- /.form-actions -->
        </form>
        <!-- /form -->
      </div>
      <!-- /.card-body -->
    </div>

  </section>

  <script>
    $(function(){
      $('input#addr').val($('textarea#full-addr').val());
    })
  </script>

  <%@ include file="/WEB-INF/views/common/mypageFooter.jsp" %>
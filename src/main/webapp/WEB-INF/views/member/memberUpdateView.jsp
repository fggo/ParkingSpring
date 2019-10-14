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

        <form id="updateFrm" method="POST" enctype="multipart/form-data">

          <div class="media mb-3">
            <!-- avatar -->
            <div class="avatar-wrapper my-0 mx-3">
              <% if(loginMember.getUserRenamedFilename() != null) { %>
              <img class="profile-pic" src="<%=request.getContextPath()%>/upload/member/<%=loginMember.getUserRenamedFilename() %>" />
              <% } else { %>
              <img class="profile-pic" src="" />
              <% } %>

              <div class="upload-button">
                <i class="fa fa-camera" aria-hidden="true"></i>
              </div>
              <input class="file-upload form-control" type="file" accept="image/*" name="new_up_file" />
              <input class="" type="hidden" name="old_up_file_ori" value="<%=loginMember.getUserOriginalFilename() %>" />
              <input class="" type="hidden" name="old_up_file_re" value="<%=loginMember.getUserRenamedFilename() %>" />
            </div>
            <!-- /avatar -->

            <!-- .media-body -->
            <div class="media-body pl-3">
              <h3 class="card-title"><%=loginMember.getUserName() %>'s Profile</h3>
              <p class="card-text">
                <small class="card-subtitle text-muted"> Click the avatar to change your photo. <br>
                  JPG, GIF or PNG 400x400, &lt; 2 MB.</small>
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
            <label for="phone" class="col-md-3">Phone</label>
            <!-- /form column -->
            <!-- form column -->
            <div class="col-md-9 mb-3">
              <input type="text" class="form-control" name="phone" id="phone" value="<%=loginMember.getUserPhone() %>">
            </div>
            <!-- /form column -->
          </div>
          <div class="form-row">
            <!-- form column -->
            <label for="name" class="col-md-3">User Name</label>
            <!-- /form column -->
            <!-- form column -->
            <div class="col-md-9 mb-3">
              <input type="text" class="form-control" name="name" id="name" value="<%=loginMember.getUserName() %>">
            </div>
            <!-- /form column -->
          </div>
          <div class="form-row">
            <!-- form column -->
            <label for="addr" class="col-md-3" >Address</label>
            <!-- /form column -->
            <!-- form column -->
            <div class="col-md-9 mb-3">
              <textarea type="text" class="form-control" id="addr" name="addr" rows="3" style="resize:none;"><%=loginMember.getUserAddr() %></textarea>
              <small class="text-muted">Appears on your profile page, 300 chars max.</small>
            </div>
            <!-- /form column -->
          </div>
          <div class="form-row">
            <label class="col-md-3">SMS</label>
            <div class="col-md-9 mb-3">
              <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" name="smsYn" id="smsYn" <%=smsChk %> />
                <label class="custom-control-label" for="smsYn">Subscribe to SMS</label>
              </div>
            </div>
          </div>
          <div class="form-row">
            <label class="col-md-3">Email</label>
            <div class="col-md-9 mb-3">
              <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" name="emailYn" id="emailYn" <%=emailChk %> />
                <label class="custom-control-label" for="emailYn">Subscribe to Email</label>
              </div>
            </div>
          </div>
          <!-- <div class="form-row">
            <label for="updatePw" class="col-md-3">Password</label>
            <div class="col-md-9 mb-3">
              <input type="password" class="form-control" name="updatePw" id="updatePw" />
            </div>
          </div>
          <div class="form-row">
            <label for="updatePwChk" class="col-md-3">Password Check</label>
            <div class="col-md-9 mb-3">
              <input type="password" class="form-control" name="updatePwChk" id="updatePwChk" />
            </div>
          </div> -->
          <hr>
          <div class="form-actions row">
            <button type="button" class="btn btn-secondary " id="cancelBtn">Cancel</button>
            <button type="button" class="btn btn-outline-primary ml-auto mr-3" id="confirmBtn">Confirm Update</button>
            <button type="button" class="btn btn-outline-danger mr-auto" id="deleteBtn">Delete Account</button>
          </div>
          <!-- /.form-actions -->
        </form>
      </div>
        <!-- /form -->
    </div>
      <!-- /.card-body -->

    <script>
      $(function(){
        var readURL = function(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('.profile-pic').attr('src', e.target.result);
                }
        
                reader.readAsDataURL(input.files[0]);
            }
        }
      
        $(".file-upload").on('change', function(){
            readURL(this);
        });
        
        $(".upload-button").on('click', function() {
          $(".file-upload").click();
        });


        $('button#confirmBtn').on('click', function(){
          var frm = $('form#updateFrm');

          if(confirm("Are you sure to Update?")){
            var url="<%=request.getContextPath() %>/member/memberUpdateEnd";
            frm.attr({"action" : url});
            frm.submit();
          }
        })
        $('button#deleteBtn').on('click', function(){
          var frm = $('form#updateFrm');

          if(confirm("Are you sure to DELETE Your Account?")){
            var url="<%=request.getContextPath() %>/member/memberDelete";
            frm.attr({"action" : url});
            frm.submit();
          }
        })

        $('button#cancelBtn').on('click', function(){
          location.href="<%=request.getContextPath() %>/member/memberView";
        })
      });

    </script>

  </section>

  <%@ include file="/WEB-INF/views/common/mypageFooter.jsp" %>
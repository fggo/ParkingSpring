<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <!-- JQUERY -->
  <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>


  <!-- CSS -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/avatar.css">
  <style>
    #listScroll{
      font-size: 13px;
      max-height: 400px;
    }
  </style>
 

  <div class="container pt-5">
    <div class="row">
      <div class="col-lg-3">
        <div class="list-group mt-4 mb-3">
          <a href="javascript:;" class="list-group-item" onclick="ajaxMypageContentLoad('/member/memberView');"><i class="fa fa-cog">&nbsp;&nbsp;</i>Settings</a>
          <a href="javascript:;" class="list-group-item" onclick="location.href='<%=request.getContextPath() %>/bookmark/bookmarkView'"><i class="fa fa-bookmark">&nbsp;&nbsp;</i>Bookmark</a>
          <a href="javascript:;" class="list-group-item" onclick="location.href='<%=request.getContextPath() %>/board/reviewList'"><i class="fa fa-edit">&nbsp;&nbsp;</i>My Reviews</a>
          <% if(loginMember.getUserEmail().equals("admin@com")){ %>
            <a href="javascript:;" class="list-group-item" onclick="ajaxMypageContentLoad('/admin/memberList');"><i class="fa fa-list">&nbsp;&nbsp;</i>Member List</a>
          <%} else { %>
            <!-- <a href="javascript:;" class="list-group-item" onclick="ajaxMypageContentLoad('');"><i class="fa fa-credit-card">&nbsp;&nbsp;</i>Payment Methods</a> -->
            <!-- <a href="javascript:;" class="list-group-item" onclick="ajaxMypageContentLoad('');"><i class="fa fa-calendar">&nbsp;&nbsp;</i>Reservations</a> -->
            <!-- <a href="javascript:;" class="list-group-item" onclick="ajaxMypageContentLoad('');"><i class="fa fa-car">&nbsp;&nbsp;</i>My Vehicle</a> -->
            <!-- <a href="javascript:;" class="list-group-item" onclick="ajaxMypageContentLoad('');"><i class="fa fa-won">&nbsp;&nbsp;</i>Credit Balance</a> -->
          <% } %>
        </div>
        
        <div class="card-text text-center overflow-auto mb-1" id="listScrollTitle" >
        </div>
        <div class="card-text shadow-sm text-left overflow-auto" id="listScroll" >
        </div>

      </div>

      <script>
        function ajaxMypageContentLoad(urlMapping){
          if($('#listScroll') != null)
            $('#listScroll').empty();

          if($('#listScrollText') != null)
            $('#listScrollText').empty();

          if(urlMapping == '/bookmark/bookmarkView'
              || urlMapping == '/board/reviewList'
              || urlMapping == '/board/reviewContentView') {
            $('#listScroll').show();
            $('#listScrollTitle').show();
          }
          else{
            $('#listScroll').hide();
            $('#listScrollTitle').hide();
          }

          $.ajax({
            type: "POST",
            url: "<%=request.getContextPath() %>" + urlMapping,
            data:{userCode: "<%=loginMember.getUserCode() %>"},
            dataType: "html",
            success: function(data){
              html = $('<div>').html(data);
              console.log(html.find('section.subMenu-container'));
              $('div#mypage-container').html(html.find('section.subMenu-container'));
            }
          });
        }
      </script>

      <div class="col-lg-9" id="mypage-container">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp">
  <jsp:param name="pageTitle" value="MyPage - ${subpage}" />
</jsp:include>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  <c:set var="path" value="${pageContext.request.contextPath}" />

  <!-- CSS -->
  <link rel="stylesheet" href="${path}/resources/css/avatar.css">
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
          <a href="javascript:;" class="list-group-item" onclick="location.href='${path}/bookmark/bookmarkView'"><i class="fa fa-bookmark">&nbsp;&nbsp;</i>Bookmark</a>
          <a href="javascript:;" class="list-group-item" onclick="location.href='${path}/board/reviewList'"><i class="fa fa-edit">&nbsp;&nbsp;</i>My Reviews</a>

          <c:if test="${loginMember.userCode == 'admin@com'}">
          <a href="javascript:;" class="list-group-item" onclick="ajaxMypageContentLoad('/admin/memberList');"><i class="fa fa-list">&nbsp;&nbsp;Member List</i>
            </a>
          </c:if>
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
            url: "${path}" + urlMapping,
            data:{userCode: "${loginMember.userCode}"},
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.parking.board.model.vo.QnaBoard" %>

<%
  List<QnaBoard> list = (ArrayList<QnaBoard>)request.getAttribute("qnalist");
  String pageBar = (String)request.getAttribute("pageBar");
  int cPage = (Integer)request.getAttribute("cPage");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <style>
    body { padding-top: 56px; }
    #menus{font-size:12px;}

  </style>

  <main role="main" class="flex-shrink-0" id='qna-main'>
    <div class="container" id='qna-container'>

      <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-primary rounded shadow-sm">
        <!-- <img class="mr-3" src="" alt="" width="48" height="48"> -->
        <img src="<%=request.getContextPath() %>/images/qna.png" class="mr-3" width="60px">
        <div class="lh-100">
          <p class="h5 mb-0 text-white lh-100">&nbsp;Q&A Board</p>
          <small>Since 2019.09</small>
        </div>
      </div>
  
      <div class="my-3 p-3 bg-white rounded shadow-sm">
        <section id="board-container">
          <div class="row d-flex">
            <h6 class="border-bottom border-gray pb-2 mb-0 mr-auto">&nbsp;&nbsp;Questions</h6>
            <c:if test="${!loginMember empty}">
            <%-- <% if(loginMember != null){ %> --%>
              <input type="button" value="write" class= "btn btn-outline-primary mr-3 mb-2" id="qna-add" onclick="location.href='<%=request.getContextPath() %>/board/qnaboardWrite'">
           <%--  <%} %> --%>
            </c:if>
          </div>

          <table class="table table-sm" id='qna_table'>
            <thead>
              <tr>
                <th class="text-center">NO.</th>
                <th class="text-center">Author</th>
                <th class="text-center">Title</th>
                <th class="text-center">Views</th>
              </tr>
            </thead>
            <tbody>
              <% for(QnaBoard b : list){ %>
              <tr>
                <td class="text-center"><%=b.getQnaNo() %> </td>
                <td class="text-center">
                  <div class="media text-muted pt-3">
                    <svg class="bd-placeholder-img mr-2 rounded " width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32">
                      <title>Placeholder</title><rect width="100%" height="100%" fill="#132535" class="bg-secondary"></rect>
                      <text x="50%" y="50%" fill="#132535" dy=".3em">32x32</text>
                    </svg>
                    <!-- <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray"> -->
                    <p class="media-body pb-4 mb-0 small lh-125">
                      <strong class="d-block text-gray-dark">@<%=b.getUserCode() %></strong>
                    </p>
                  </div>
                </td>
                <td class="text-center">
                  <a href="<%=request.getContextPath() %>/board/qnaBoardView?no=<%=b.getQnaNo() %>">
                    <%=b.getQnaTitle() %>
                  </a>
                </td>
                <td class="text-center"><%=b.getQnaReadcount() %></td>
      
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
                  var url = "<%=request.getContextPath() %>/board/qnaBoardList?cPage=" + pageNo;
                  $.ajax({
                    type: "POST",
                    url: url,
                    dataType: "html",
                    success: function(data){
                      html = $('<div>').html(data);
                      console.log(html.find('div#qna-container'));
                      // var tag = $("<h3>").html(data).css("color", "blue");
                      // $('#content').append(tag);
                      // $('#qna_table tbody')
                        // location.href="<%=request.getContextPath() %>/board/qnaBoardList?cPage=" + pageNo;

                      $('main#qna-main').html(html.find('div#qna-container'));
                    },
                    error: function(request, status, error){
                      console.log("error 함수 실행!");
                      console.log(request);
                      console.log(status);
                      console.log(error);
                    },
                  });
                }
              </script>

        </section>
      </div>
    </div>
  </main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
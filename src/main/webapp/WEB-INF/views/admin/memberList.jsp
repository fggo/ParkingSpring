<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList, com.parking.member.model.vo.Member"%>

<%
  List<Member> members=(ArrayList<Member>)request.getAttribute("members");
  int cPage=(int)request.getAttribute("cPage");
  int numPerPage=(int)request.getAttribute("numPerPage");
  String pageBar = (String)request.getAttribute("pageBar");
	String searchType=(String)request.getAttribute("searchType");
	String searchKeyword=(String)request.getAttribute("searchKeyword");
%>

<%@ include file="/WEB-INF/views/common/mypageHeader.jsp" %>
  <style>
    #search-user_Email{display: inline-block;}
    #search-user_Name{display: none;}
  </style>

    <section class="py-4 subMenu-container" id="memberList-container">
      <div class="d-flex align-items-center p-3 mt-0 mb-3 text-white-50 bg-primary rounded shadow-sm">
        <!-- <img class="mr-3" src="" alt="" width="48" height="48"> -->
        <img src="<%=request.getContextPath() %>/images/list.png" class="mr-3" width="30px">
        <div class="lh-100">
          <p class="h5 mb-0 text-white lh-100">&nbsp;Member List</p>
          <small>Since 2019.09</small>
        </div>
      </div>
      <div id="neck-container" class="my-2">
        <div id="search-container">
          SEARCH TYPE : 
          <select id="searchType">
            <option value="userEmail" <%="userEmail".equals(searchType)?"selected":"" %>>Email</option>
            <option value="userName" <%="userName".equals(searchType)?"selected":"" %>>UserName</option>
          </select>
          <div id="search-userEmail">
            <form action="<%=request.getContextPath() %>/admin/memberFinder">
              <input type="hidden" name="searchType" value="userEmail"/>
              <input type="hidden" name="cPage" value="<%=cPage%>"/>
              <input type="text" placeholder="Search Email" name="searchKeyword"
                     value='<%="userEmail".equals(searchType)? searchKeyword:"" %>' size="25"/>
              <button type="submit" class="btn btn-sm btn-outline-primary mb-1">SEARCH</button>
            </form>
          </div>
          <div id="search-userName">
            <form action="<%=request.getContextPath() %>/admin/memberFinder">
              <input type="hidden" name="searchType" value="userName"/>
              <input type="hidden" name="cPage" value="<%=cPage%>"/>
              <input type="text" placeholder="Search User Name" name="searchKeyword"
                     value='<%="userName".equals(searchType)? searchKeyword: ""%>' size="25"/>
              <button type="submit" class="btn btn-sm btn-outline-primary mb-1">SEARCH</button>
            </form>
          </div>
        </div>
      </div>
      <script>
        $(function(){
          var sEmail=$("#search-userEmail");
          var sUserName=$("#search-userName");
          var searchType=$("#searchType"); //<select>
  
          searchType.change(function(){ //add 'change' event on searchType <select>
            sEmail.hide();
            sUserName.hide();
            $('#search-' + this.value).css("display", "inline-block");
          });
          searchType.trigger("change"); // trigger change event
        });
      </script>
      <table class="table table-sm">
        <thead>
          <tr>
            <th class="text-center" scope="col">NO.</th>
            <th class="text-center" scope="col">User#</th>
            <th class="text-center" scope="col">Email</th>
            <th class="text-center" scope="col">Name</th>
            <th class="text-center" scope="col">Created</th>
            <th class="text-center" scope="col">Logged</th>
            <!-- <th scope="col">SMS(Y/N)</th>
            <th scope="col">Email(Y/N)</th> -->
            <th class="text-center" scope="col">Verified(Y/N)</th>
            <th class="text-center" scope="col">SNS</th>
          </tr>
        </thead>
        <tbody>
        <% if(members != null && !members.isEmpty()) {
          int count =0;
          for(Member m : members){ %>
            <tr>
              <th scope="row"><%= (cPage-1) * numPerPage + (++count)%></th>
              <td>
                <a href="#">
                  <%=m.getUserCode() %>
                </a>
              </td>
              <td><%=m.getUserEmail() %></td>
              <td><%=m.getUserName() %></td>
              <td><%=m.getUserCreatedDate() %></td>
              <td><%=m.getUserLoginDate() %></td>
              <!-- <td><%=m.getUserSmsYn()==1? 'Y':'N' %></td>
              <td><%=m.getUserEmailYn()==1? 'Y':'N' %></td> -->
              <td><%=m.getUserEmailVerified()==1? 'Y':'N' %></td>
              <td><%=m.getUserSnsAccount() %></td>
            </tr>
        <% } } %>
        </tbody>
      </table>
      
      <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <%=pageBar %>
        </ul>
      </nav>
      <script>
        function ajaxReqList(pageNo){
          var url = "<%=request.getContextPath() %>/admin/memberList?cPage=" + pageNo;
          $.ajax({
            type: "POST",
            url: url,
            dataType: "html",
            success: function(data){
              html = $('<div>').html(data);
              console.log(html);
              console.log(html.find('section#memberList-container'));
              // var tag = $("<h3>").html(data).css("color", "blue");
              // $('#content').append(tag);
              // $('#qna_table tbody')
                // location.href="<%=request.getContextPath() %>/board/qnaBoardList?cPage=" + pageNo;
              $('div#mypage-container').html(html.find('section#memberList-container'));
            },
            error: function(request, status, error){
              console.log("error 함수 실행!");
              console.log(request);
              console.log(status);
              console.log(error);
            },
          });
        }
        function ajaxReqSearchList(pageNo, searchType, searchKeyword){
          var url;
          console.log(searchType);
          console.log(searchKeyword);
          url = "<%=request.getContextPath() %>/admin/memberFinder?cPage=" + pageNo
                                                            + "&searchType="+searchType 
                                                            + "&searchKeyword=" +searchKeyword;

          $.ajax({
            type: "POST",
            url: url,
            dataType: "html",
            success: function(data){
              html = $('<div>').html(data);
              console.log(html);
              console.log(html.find('section#memberList-container'));
              $('div#mypage-container').html(html.find('section#memberList-container'));
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


<%@ include file="/WEB-INF/views/common/footer.jsp" %>
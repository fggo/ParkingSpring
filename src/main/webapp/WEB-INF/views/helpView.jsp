<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./common/header.jsp" %>
<%
	List<QnaBoard> list = (List)request.getAttribute("list");

%>

  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/card-flip.css">

  <style>
    #help-search-area{
      /* background-color: #0672E5; */
      height: 58vh !important;
      display: flex !important;
      margin-bottom: 0px;
      /* text-shadow:
        1.5px 1.5px 0px #132535; */
    }
  </style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
  <script>
    
      $(function(){
        var firstQna = $("#firstPopular");
        var secondQna = $("#secondPopular");
        var qnaCreate = null;
        var qnaCreateA = null;
        <%for(int i = 0; i < 6; i++){%>
          <% if(list.size() <= i) { break;}%>
          <% if(i < 3){%>
            qnaCreate = $("<li>");
            qnaCreateA = $("<a class='text-muted' href='<%=request.getContextPath()%>/board/qnaBoardList'>"); 
            qnaCreateA.html('<%=list.get(i).getQnaTitle()%>');
            qnaCreate.append(qnaCreateA);
            firstQna.append(qnaCreate);
          <%} else{%>
            qnaCreate = $("<li>");
            qnaCreateA = $("<a class='text-muted' href='<%=request.getContextPath()%>/board/qnaBoardList'>"); 
            qnaCreateA.html('<%=list.get(i).getQnaTitle()%>');
            qnaCreate.append(qnaCreateA);
            secondQna.append(qnaCreate);
          <%}%>
        <%}%>

      })
    
    </script>

  <!-- cover -->
  <div class="jumbotron align-items-center bg-info" id="help-search-area" style="height:55vh">
    <div class="container">

      <p class="h3 row justify-content-center text-white font-weight-bold">SEARCH FOR HELPFUL ARTICLES</p>
      <p class="h7 row justify-content-center text-white font-weight-bold">IT WILL HELP YOU USE THE APP!</p>
      <div class="mx-auto col-lg-8">
        <form id="main-searchbar" action="" class="" role="form">
          <div class="input-group mb-4">
            <div class="input-group-prepend">
              <button id="button-addon7" type="submit" class="btn btn-light"><i class="fa fa-search"></i></button>
            </div>
            <input type="search" placeholder="Search Help Articles" aria-describedby="button-addon7" class="form-control">
          </div>
        </form>
      </div>
    </div>
  </div>

  <div class="album py-5 bg-light">
    <div class="container">
      <div class="row">

        <div class="col-md-6">
          <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
            <div class="mainflip">
                <div class="frontside">
                    <div class="card">
                        <div class="card-body text-center">
                            <p><img class="img-fluid helpUserIcon" src="../images/driverhelp.png" alt="card image"></p>
                            <h4 class="card-title">Driver</h4>
                            <p class="card-text">This is basic card with image on top, title, description and button.</p>
                        </div>
                    </div>
                </div>
                <div class="backside">
                  <div class="card">
                      <div class="card-body text-center mt-4">
                        <h4 class="card-title">Driver Help</h4>
                        <p class="card-text">This is basic card with image on top, title, description and button.This is basic card with image on top, title, description and button.This is basic card with image on top, title, description and button.</p>
                        <a href="<%=request.getContextPath() %>/board/qnaBoardList" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i></a>
                    </div>
                  </div>
                </div>
              </div>
          </div>
        </div>

        <div class="col-md-6">

          <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
            <div class="mainflip">
              <div class="frontside">
                <div class="card">
                  <div class="card-body text-center">
                    <p><img class="img-fluid" src="../images/ownerhelp.png" alt="card image"></p>
                    <h4 class="card-title">Owner</h4>
                    <p class="card-text">This is basic card with image on top, title, description and button.</p>
                  </div>
                </div>
              </div>
              <div class="backside">
                <div class="card">
                  <div class="card-body text-center mt-4">
                    <h4 class="card-title">Owner Help</h4>
                    <p class="card-text">This is basic card with image on top, title, description and button.This is basic card with image on top, title, description and button.This is basic card with image on top, title, description and button.</p>
                    <a href="<%=request.getContextPath() %>/board/qnaBoardList" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i></a>
                    <!-- <button class="learn-more" onclick="location.href='<%=request.getContextPath() %>/board/qnaBoardList'" >
                      <div class="circle"><span class="icon arrow"></span></div>
                      <p class="button-text">Help</p>
                    </button> -->
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="album py-5 bg-light">
    <div class="container">
      <div class="row">
        <div class="col-6 col-md">
          <p class="h5">
            <a href="<%=request.getContextPath() %>/board/qnaBoardList">
              <span><i class="text-warning fa fa-star"></i>&nbsp;&nbsp;Popular Thread</span>
            </a>
          </p>
          <ul class="list-unstyled text-small" id="firstPopular">
            <!-- <li><a class="text-muted" href="<%=request.getContextPath()%>/board/qnaBoardList">How do I make a long-term booking?</a></li>
            <li><a class="text-muted" href="<%=request.getContextPath()%>/board/qnaBoardList">How do I book a parking space?</a></li>
            <li><a class="text-muted" href="<%=request.getContextPath()%>/board/qnaBoardList">How do I contact a space owner</a></li> -->
          </ul>
        </div>
        <div class="col-6 col-md">
        <ul class="list-unstyled text-small" id="secondPopular">
          <p class="h5">&nbsp;</p>
          <!-- <li><a class="text-muted" href="<%=request.getContextPath() %>/board/qnaBoardList">How do I find a space for large vehicles?</a></li>
          <li><a class="text-muted" href="<%=request.getContextPath() %>/board/qnaBoardList">How do I change the vehicle on a booking?</a></li>
          <li><a class="text-muted" href="<%=request.getContextPath() %>/board/qnaBoardList">How do I reset my password?</a></li> -->
        </ul>
        </div>
      </div>
    </div>
  <script type="text/javascript">
var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
(function(){
var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
s1.async=true;
s1.src='https://embed.tawk.to/5d8753dac22bdd393bb71c8a/default';
s1.charset='UTF-8';
s1.setAttribute('crossorigin','*');
s0.parentNode.insertBefore(s1,s0);
})();
</script>
  </div>



    
  
<%@ include file="./common/footer.jsp" %>
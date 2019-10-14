<%@page import="com.parking.api.model.vo.Coupon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/WEB-INF/views/common/header.jsp" %> <% String parkingNum =(String)request.getAttribute("parkingNum");
String today = (String)request.getAttribute("today");
List<Coupon> list = (List)request.getAttribute("CouponList");
%>

<style>
    .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    @media (min-width: 768px) {
        .bd-placeholder-img-lg {
            font-size: 3.5rem;
        }
    }
    #detailes {
        float: right;
        top: 30px;
    }

    input {
        border: none;
    }
</style>
<!-- <link href="pricing.css" rel="stylesheet"> -->
<head>
		<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
</head>
<<body>
		<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
			<ol class="breadcrumb">
				<li><a href="#">Parking</a>/</li>
				<li class="active" id="fromAddr">Payment</li>
			</ol>
		</div>
	
		<div class="container">
			<div class="card-deck list-group pl-md-5 pl-5"> <!-- text-center -->
			
				<div class="card shadow-sm">
					<div class="card-header">
						<h4 class="my-0 font-weight-normal"><b>1.Review Reservation Details</b></h4>
					</div>
					<div class="card-body row">
					
					<!--div class="row">
					   <input type="text" id="" value="From">
					   <input type="text" id="fromDay" value="2019 - 9 - 22">
					   <input type="text" id="fromTime" value="19시 34분 9초" >
					</div-->
				   
					
					
					
					
						<ul class="list-unstyled mt-3 mb-4 col-md-12">
							<input type="text" id="" value="From" >
							<input type="text" id="fromDay" value="2019 - 1 - 01">
							<input type="text" id="fromTime" value="00시 00분 0초" >
							<!-- <input type="text" id="fromDuration" value="약15km" readonly /> -->
							
							Selct Time
							<select name="addTime" id="addTime" onchange="selectTime()">                            
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
							</select>                           
						</ul>
						
						
						 
						
						
	
						<ul class="list-unstyled mt-3 mb-4 col-md-12">
						   <input type="text" id="" value="To" readonly >               
						   <input type="text" id="toDay" value="2019 - 9 - 22" readonly />
							<input type="text" id="toTime" value="19시 34분 9초" readonly />
							<input type="text" id="toPrice" value="" readonly />
						</ul>
						
						
						
						
						
						
						
						<!-- <button type="button" class="btn btn-lg btn-block btn-outline-primary">Sign up for free</button> -->
					</div>
				</div>
				<div class="card shadow-sm">
					<div class="card-header">
						<h4 class="my-0 font-weight-normal"><b>2.Account Info</b></h4>
					</div>
						 <ul class="list-unstyled mt-3 mb-4 col-md-6">
							<div class="col form-group">
							   E-mail<input type="text" id="loginMember" class="form-control" readonly>
							</div>
							<div class="col form-group">   
							   Phone<input type="text" id="loginPhone" class="form-control"  readonly>
							 </div>  
						  </ul>
						<!-- <button type="button" class="btn btn-lg btn-block btn-primary">
							Get started
						</button> -->
				</div>
				<div class="card shadow-sm">
					<div class="card-header">
						<h4 class="my-0 font-weight-normal"><b>3.Payment Info</b></h4>
					</div>
					<div class="card-body row">
						<ul class="list-unstyled mt-3 mb-4 col-md-6">
							<select name="" id="payName" class="custom-select d-block">
								<option value="kakaoPay">kakaoPay</option>
								<option value="inicisPay">inicisPay</option>
							</select>
							<select name="" id="coupon" class="custom-select d-block mt-1">
							<option value="">선택</option>
							</select>                        
						</ul>
						
						<button
								type="button"
								class="btn btn-lg btn-block btn-primary mt-3 mb-4 col-md-12"                            
								onclick="openKpay()"
							>
								Payment
							</button>
					</div>
				</div>
			</div>
			<!-- 디테일 -->
			<!-- <div class="card-deck text-center list-group col-md-4 pl-md-5">
				<div class="card shadow-sm text-left col-sm-8">
					<a class="list-group-item list-group-item-action">
						<span id="0">
							<span id="pName">1. 압구정 428 공영주차장(구)<br /></span
							><span>&nbsp;&nbsp;&nbsp;&nbsp;강남구 압구정동 428-0<br /></span
							><span id="Pcur"
								>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;전체 주차 대수 : 343<br />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;현재 주차 대수 : 325<br
							/></span>
							<div class="mt-2">
								<button class="btn btn-sm btn-outline-info mr-1" id="parking0">
									More Info</button
								><input
									type="button"
									class="btn btn-sm btn-outline-info pay"
									onclick="payment()"
									value="Pay"
								/></div></span
					></a>
				</div>
			</div> -->
		</div>
	</body>

<script>

		$(function(){
			
			originPay = null;
			// 새로추가 할인
			var selectCoupon = $('#coupon');
			if(<%=loginMember != null%>)
			{
				<% for(int i = 0; i < list.size(); i++){%>
					<%if(loginMember.getUserCode().equals(list.get(i).getUserCode())) {%>			
						var op = $("<option>").html("<%=list.get(i).getDiscountRate()%>"+"%");
						selectCoupon.append(op);
					<%}%>
				<%}%>
			}
			
			var data = JSON.parse(sessionStorage.getItem("parkingList"));
			var addt = $('#addTime').val();
			for(var d in data){			
				if(Number(<%=parkingNum%>) == d)
				{
					var pay = parseInt((data[d]["rates"]/ data[d]["timeRate"]))*60;
					$("#toPrice").val(pay*Number(addt));
					originPay = $("#toPrice").val(pay*Number(addt));
					console.log(addt);
					// if($("#toPrice").val() === NaN)
					// {
					// 	$("#toPrice").html("0");
					// }
				}
			}
			
		});
		

     function selectTime()
     {
		var data = JSON.parse(sessionStorage.getItem("parkingList"));
		var tDay = new Date();
		var addt = $('#addTime').val();
		 $('#toDay').val(tDay.getFullYear()+" - "+(tDay.getMonth()+1)+" - "+tDay.getDate());
		 if( Number(addt)+Number(tDay.getHours()) >= 24 )
		 {
			 $('#addTime').val($("#addTime").children().first().val());
			 $("#toTime").val(tDay.getHours()+"시 "+(tDay.getMinutes())+"분 "+tDay.getSeconds()+"초");
			 alert("시간초과 다시 설정해주세요");
		 }else{
			 $('#toTime').val((Number(tDay.getHours())+Number(addt))+"시 "+(tDay.getMinutes())+"분 "+tDay.getSeconds()+"초");
			 
			 for(var d in data)
			 {
				 if( Number(<%=parkingNum%>) == d )
				 {
					 console.log("파킹넘");
					 var pay = parseInt((data[d]["rates"]/ data[d]["timeRate"]))*60;
					 $("#toPrice").val(pay* Number(addt));				 }
			 }
		 }
     }



            function openKpay()
            {
        			var pgName= null;
        			var pgCode = null;
        			var pName = null;
        			
        			var data = JSON.parse(sessionStorage.getItem("parkingList"));
        			
        			 for(var d in data)
        			 {
        				 if( Number(<%=parkingNum%>) == d )
        				 {
        					 pName = data[d]["parkingName"];
        									 }
        			 }

          if($('#payName').val() == 'kakaoPay')
        {
        	pgName = 'kakao';
        	pgCode ='imp22743453';
        		}else{
        	pgName = 'html5_inicis';
        	pgCode ='imp84822201';
        }

            	var IMP = window.IMP; // 생략가능
              IMP.init(pgCode);  // 가맹점 식별 코드

              IMP.request_pay({
                 pg : pgName, // 결제방식
                  pay_method : 'card',	// 결제 수단
                  merchant_uid : 'merchant_' + new Date().getTime(),
                  name : pName+"테스트 결제",	// order 테이블에 들어갈 주문명 혹은 주문 번호
                  amount : '100',	// 결제 금액
                  buyer_email : '<%=loginMember.getUserEmail()%>',	// 구매자 email
                	buyer_name :  '<%=loginMember.getUserName()%>',	// 구매자 이름
                  buyer_tel :  '<%=loginMember.getUserPhone()%>',	// 구매자 전화번호
                  buyer_addr :  '<%=loginMember.getUserAddr()%>',	// 구매자 주소
                  buyer_postcode :  '',	// 구매자 우편번호
                  m_redirect_url : '/khx/payEnd.action'	// 결제 완료 후 보낼 컨트롤러의 메소드명
              }, function(rsp) {
            if ( rsp.success ) { // 성공시
            	var msg = '결제가 완료되었습니다.';
            	msg += '고유ID : ' + rsp.imp_uid;
            	msg += '상점 거래ID : ' + rsp.merchant_uid;
            	msg += '결제 금액 : ' + rsp.paid_amount;
					msg += '카드 승인번호 : ' + rsp.apply_num;
					alert(msg);
					
					successPage();
           console.log(msg);
            } else { // 실패시
            	var msg = '결제에 실패하였습니다.';
            	msg += '에러내용 : ' + rsp.error_msg;
           console.log(msg);
            }
            	});
              
              
				}
				
							
				function successPage()
				{
					
					var data = JSON.parse(sessionStorage.getItem("parkingList"));
					var tDay = new Date();
					var addt = $('#addTime').val();
	       			 for(var d in data)
	       			 {
	       				 if( Number(<%=parkingNum%>) == d )
	       				 {
	       						       									 
							var form = document.createElement("form");
		
							form.setAttribute("charset", "UTF-8");
							form.setAttribute("method", "Post");  //Post 방식
							form.setAttribute("action", "<%=request.getContextPath()%>/paymentEnd"); //요청 보낼 주소
		
							var hiddenField = document.createElement("input");
							hiddenField.setAttribute("type", "hidden");
							hiddenField.setAttribute("name", "pCode");
							hiddenField.setAttribute("value", data[d]["parkingCode"]);
							form.appendChild(hiddenField);
		
							hiddenField = document.createElement("input");
							hiddenField.setAttribute("type", "hidden");
							hiddenField.setAttribute("name", "beginTime");
							var beginT = tDay.getFullYear()+"-"+(tDay.getMonth()+1)+"-"+tDay.getDate()+"/"+tDay.getHours()+":"+(tDay.getMinutes())+":"+tDay.getSeconds();
							hiddenField.setAttribute("value", String(beginT));
							form.appendChild(hiddenField);
							
							hiddenField = document.createElement("input");
							hiddenField.setAttribute("type", "hidden");
							hiddenField.setAttribute("name", "endTime");
							var EndT = tDay.getFullYear()+"-"+(tDay.getMonth()+1)+"-"+tDay.getDate()+"/"+(Number(tDay.getHours())+Number(addt))+":"+(tDay.getMinutes())+":"+tDay.getSeconds();
							hiddenField.setAttribute("value", String(EndT));
							form.appendChild(hiddenField);
							
							hiddenField = document.createElement("input");
							hiddenField.setAttribute("type", "hidden");
							hiddenField.setAttribute("name", "amount");
							hiddenField.setAttribute("value", $("#toPrice").val());
							form.appendChild(hiddenField);
							var addt = $('#addTime').val();
							
							hiddenField = document.createElement("input");
							hiddenField.setAttribute("type", "hidden");
							hiddenField.setAttribute("name", "addTime");
							hiddenField.setAttribute("value", addt);
							form.appendChild(hiddenField);
													
							hiddenField = document.createElement("input");
							hiddenField.setAttribute("type", "hidden");
							hiddenField.setAttribute("name", "userCode");
							hiddenField.setAttribute("value", <%=loginMember.getUserCode()%>);
							form.appendChild(hiddenField);
							
							if($("#coupon").val() == "선택")
							{
								hiddenField = document.createElement("input");
							hiddenField.setAttribute("type", "hidden");
							hiddenField.setAttribute("name", "discountCoupon");
							hiddenField.setAttribute("value", "선택");
							}
							else{
								hiddenField = document.createElement("input");
								hiddenField.setAttribute("type", "hidden");
								hiddenField.setAttribute("name", "discountCoupon");
								hiddenField.setAttribute("value", $("#coupon").val());
							}
							
							
							form.appendChild(hiddenField);
							document.body.appendChild(form);
		
							form.submit();
	       				 }
	       			 }
					
				}
				
				
				
				

                      $(function() {
                          var data = JSON.parse(sessionStorage.getItem("parkingList"));
                      		 var tDay = new Date();
                     	$('#fromDay').val(tDay.getFullYear()+" - "+(tDay.getMonth()+1)+" - "+tDay.getDate());
                    	$('#fromTime').val(tDay.getHours()+"시 "+(tDay.getMinutes())+"분 "+tDay.getSeconds()+"초");
						$('#toDay').val(tDay.getFullYear()+" - "+(tDay.getMonth()+1)+" - "+tDay.getDate());
						$('#toTime').val((tDay.getHours()+1)+"시 "+(tDay.getMinutes())+"분 "+tDay.getSeconds()+"초");
                    	<%if(loginMember != null) {%>
                    		$("#loginMember").val("Email : " + "<%=loginMember.getUserEmail()%>");
                    		$("#loginPhone").val("Phone : " + "<%=loginMember.getUserPhone()%>");

               <%}%>

                          for (var d in data) {
                     if(<%=parkingNum%> == d){
                     	$('#fromAddr').html(data[d]["addr"]);
                     }
                          }
                      });
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

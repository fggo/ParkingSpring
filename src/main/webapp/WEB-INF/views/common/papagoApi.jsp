<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>

  <style>
      div#papago-container{
        padding-top: 55px;
      }
      textarea{
        resize:none;
      }
  </style>
  

  <script>
  function googleSectionalElementInit() {
    new google.translate.SectionalElement({
      sectionalNodeClassName: 'translate',
      controlNodeClassName: 'translate_control',
      background: '#f4fa58'
    }, 'google_sectional_element');
  }
  </script>
  <script src="//translate.google.com/translate_a/element.js?cb=googleSectionalElementInit&ug=section&hl=en"></script>
  <div class="container ">

    <div id="papago-container" class="container align-items-center justify-content-center row">
      <textarea id="send_text" class="form-control col-4" name="content" cols="40" rows="4" placeholder="send text" ></textarea>
      <button id="jsonConvertStringSend" type="button" class="btn btn-primary mx-3">Translate</button>
      <textarea id="result_text" class="form-control col-4" name="content" cols="40" rows="4" placeholder="result text" readonly></textarea>
    </div>
  

  <script>
    //번역을 위해서 button 이벤트를 위해서 사용하는 것
    $('#jsonConvertStringSend').click(function () {
        //번역할 object를 생성 
        console.log("clicked!");
        var test = {
            "original_str": $("#send_text").val(),
        };
        console.log(test);
        jsonSend(test);
    });
    
    function jsonSend(test) {
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath() %>/common/papagoNMTApi",
            data: test, //json을 보내는 방법
            dataType: "text",
            success: function (data) { //서블렛을 통한 결과 값을 받을 수 있습니다.
                console.log(data);
                //alert(data);

                //string의 값을 object 형식으로 변환합니다.
                var result_obj = JSON.parse(data);
                console.log(data);
                console.log(result_obj);
                
                //결과값을 textarea에 넣기 위해서
                $("#result_text").val(result_obj.message.result.translatedText);
            },
            error: function (e) {
                console.log(e);
                alert('실패했습니다.');
            }
        });
    }
  </script>

<!-- footer -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
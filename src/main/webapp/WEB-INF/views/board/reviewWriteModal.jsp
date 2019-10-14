<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.parking.api.model.vo.Parking" %>
<%@ page import="com.parking.history.model.vo.UserHistory" %>

<%@ include file="/WEB-INF/views/common/mypageHeader.jsp" %>

<%
  Parking parking = (Parking)request.getAttribute("parking");
  UserHistory userhistory = (UserHistory)request.getAttribute("userhistory");
%>

  <section class="py-4 subMenu-container">
    <!-- Modal -->
    <div class="modal fade" id="reviewWriteModal" tabindex="-1" role="dialog" aria-labelledby="modalLabelParkingName" aria-hidden="true" data-keyboard="false" data-backdrop="static">
      <script>
        $(function(){
          <% for(int i =0 ; i<1000; i++) { %>
            $('object#daum\\:roadview\:<%=i %>').css({"position": "relative !important"});
          <% } %>
  
        });
      </script>
      <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title ml-auto" id="modalLabelParkingName"></h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
  
            <div class="card mb-4 rounded">
              <div class="social-card-header align-middle text-center bg-light rounded border"
                  style="height:400px;" id="popRoadView">
              </div>
              <div class="card-body text-center">
                <div class="row">
                  <div class="col border-right">
                    <span class="text-muted" id="toggleTooltip"><a href="javascript:;" id="bookmarkToggleBtn">
                      <i class="fa fa-star-o text-twitter" id="bookmarkIcon">&nbsp;&nbsp;</i>Bookmark</a></span>
                    <div class="font-weight-bold"></div>
  
                  </div>
                  <div class="col">
                    <span class="text-muted"><a href="#"><i class="fa fa-edit text-twitter">&nbsp;&nbsp;</i>Review</a></span>
                    <div class="font-weight-bold">1K</div>
                  </div>
                </div>
              </div>
            </div>
            <div class="card mb-4 rounded">
              <div class="card-body">
                <!-- <h3 class="mr-auto text-center my-4">Information</h3> -->
                <input type="hidden" name="parkingCode" id="parkingCode" value="" />
  
                <div class="row my-2">
                  <div class="col-md-4 border-right"><i class="fa fa-map-signs">&nbsp;&nbsp;</i>Parking lot Name</div>
                  <div class="col-md-8" id="parkingName"></div>
                </div>
                <div class="row my-2">
                  <div class="col-md-4 border-right"><i class="fa fa-map-marker">&nbsp;&nbsp;</i>Address</div>
                  <div class="col-md-8" id="addr"></div>
                </div>
                <div class="row my-2">
                  <div class="col-md-4 border-right"><i class="fa fa-hourglass">&nbsp;&nbsp;</i>Operation Time</div>
                  <div class="col-md-8" id="operationTime"></div>
                </div>
                <div class="row my-2">
                  <div class="col-md-4 border-right"><i class="fa fa-info-circle">&nbsp;&nbsp;</i>Operation Rule Name</div>
                  <div class="col-md-8" id="operationRuleNm"></div>
                </div>
                <div class="row my-2">
                  <div class="col-md-4 border-right"><i class="fa fa-exclamation-triangle">&nbsp;&nbsp;</i>Parking Type Name</div>
                  <div class="col-md-8" id="parkingTypeNm"></div>
                </div>
                <div class="row my-2">
                  <div class="col-md-4 border-right"><i class="fa fa-globe">&nbsp;&nbsp;</i>Web Link</div>
                  <div class="col-md-8 "><a href="#">https://example.com</a></div>
                </div>
                <div class="row my-2">
                  <div class="col-md-4 border-right"><i class="fa fa-car">&nbsp;&nbsp;</i>Capacity</div>
                  <div class="col-md-8" id="capacity"></div>
                </div>
                <div class="row my-2">
                  <div class="col-md-4 border-right"><i class="fa fa-car">&nbsp;&nbsp;</i>Current Parking</div>
                  <div class="col-md-8" id="curParking"></div>
                </div>
                <div class="row my-2">
                  <div class="col-md-4 border-right"><i class="fa fa-phone">&nbsp;&nbsp;</i>Tel</div>
                  <div class="col-md-8" id="tel"></div>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
          </div>
        </div>
      </div>
    </div>
  </section>


  <%@ include file="/WEB-INF/views/common/mypageFooter.jsp" %>
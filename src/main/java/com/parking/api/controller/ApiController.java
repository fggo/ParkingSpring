package com.parking.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.parking.api.model.service.ParkingApiService;
import com.parking.api.model.service.ParkingApiServiceImpl;
import com.parking.api.model.vo.Parking;
import com.parking.api.model.vo.ParkingSlot;
import com.parking.bookmark.model.service.BookmarkService;
import com.parking.bookmark.model.vo.Bookmark;

@Controller
public class ApiController {
	
	@Autowired
	ParkingApiService parkingApiService;
	
	@Autowired
	BookmarkService bookmarkService;
	
	@RequestMapping("/map/mapListView")
	public ModelAndView MapListView(Parking parking, HttpServletRequest request, HttpServletResponse response)
	{
		//1 주소를 토대로한 글자를 가져오고
	    String search = request.getParameter("search");
	    String userCode = request.getParameter("userCode");

	    System.out.println(search);
	    System.out.println(userCode);
//	    System.out.println("서블릿 :" + search);

	    //ParkingApiService parkingService = new ParkingApiService();
	    //주소 네임을 가져와서 서비스로 넘겨주고 데이터를 담는다
	    //List<Parking> list = service.selectParkingList(search);
	    //System.out.println("리스트 사이즈 : " +list.size());
	   
	    
//	    if(list.get(0) != null)
//	    {
//	      request.setAttribute("list", list);  
//	      request.getRequestDispatcher("/views/map/mapListView.jsp").forward(request, response);      
//	    }else
//	    {
//	      request.setAttribute("msg", "Search Failed!");
//	      request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//	    }

	    //retrieve parking lot Bookmark list of a user w/ 'userCode'
	    List<Bookmark> bookmarkList = bookmarkService.selectBookmarkList(userCode);
//	    System.out.println(bookmarkList.size());

	    if (bookmarkList == null)
	      bookmarkList = new ArrayList<Bookmark>();

	    
	    String msg="Search Failed!";
		String loc="/map/mapListView";
//		if(result>0) {
//			msg="입력성공";
//		}else {
//			msg="입력실패";
//		}
		ModelAndView mv= new ModelAndView();
				
		mv.addObject("msg", msg);
		mv.addObject("loc",loc);
		
	    
	    if(search != null) {
	      request.setAttribute("search", search);  
	     // request.setAttribute("bookmarkList", bookmarkList);
	      //request.getRequestDispatcher("/views/map/mapListView.jsp").forward(request, response);
	      mv.setViewName("/map/mapListView");
	      return mv;
	    }
	    else {
	      request.setAttribute("msg", "Search Failed!");
	      //request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	      mv.setViewName("common/msg");
	      return mv;
	    }
	}
	
	@RequestMapping("/ajax/searchAddr.do")
	public void searchAddr(HttpServletRequest request, HttpServletResponse response)
	{
		String addr = request.getParameter("addr");
		
//		System.out.println(" search 서블릿 :" + addr);

		//ParkingApiServiceImpl parkingService = new ParkingApiServiceImpl();
		//주소 네임을 가져와서 서비스로 넘겨주고 데이터를 담는다
		List<Parking> list = parkingApiService.selectParkingList(addr);
		System.out.println("parking 사이즈" + list.size());
		
//		Map<String,Object> ownerList = new HashMap<String, Object>();
//		ownerList.put("list", list);
		
		//사업자등록 리스트
//		int resultCeo = parkingApiService.insertparkingOwner(list);
//		
//		if(resultCeo > 0)
//			System.out.println("사업자등록완료");
//		
//		System.out.println("searchAddr 리스트 사이즈 : " +list.size());
		
		
		
		List<ParkingSlot> slotList = parkingApiService.selectParkingSlotList();
		
		if(slotList.isEmpty())
			slotList = new ArrayList<ParkingSlot>();
		
		Calendar curTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
		
		int sysMonth = Integer.parseInt(sdf.format(curTime.getTime()).substring(5, 7));
		int sysDay = Integer.parseInt(sdf.format(curTime.getTime()).substring(8, 10));
		int sysTime = Integer.parseInt(sdf.format(curTime.getTime()).substring(11, 13));
		int sysMin = Integer.parseInt(sdf.format(curTime.getTime()).substring(14, 16));
		
		System.out.println("시간 : " + sysTime);
		System.out.println("분 : " + sysMin);
		
		System.out.println(slotList.size());
		
		if(!slotList.isEmpty())
		{
			System.out.println("비어 있지않음 slot들어옴");
			for(int i = 0; i < slotList.size(); i++)
			{
			  System.out.println(curTime.getTime());
			  Calendar rsrvBegin = createCalObj(slotList.get(i).getSlotBeginTime());
			  Calendar rsrvEnd = createCalObj(slotList.get(i).getSlotEndTime());

			  System.out.println(rsrvBegin.getTime());
			  System.out.println(rsrvEnd.getTime());

				if(curTime.after(rsrvBegin) && curTime.before(rsrvEnd)) {
				  System.out.println("예약 시간에 포함 : 리스트 삭제안됨");
				}
				else {
				  System.out.println("예약 시간에 포함 안됨: 리스트 삭제됨");
				  slotList.remove(i);
				}
				
			}
			
			for(int j = 0; j < slotList.size(); j++)
			{
				System.out.println("slot size" + slotList.size());
				for(int p = 0; p < list.size(); p++)
				{
					if(list.get(p).getParkingCode().equals(slotList.get(j).getOwnerParkingCode()))
					{
						System.out.println("현재 대수 : " + list.get(p).getCurParking());
						list.get(p).setCurParking(list.get(p).getCurParking()+1);
						
						System.out.println(list.get(p).getParkingName() + "현재 차대수 증가");
						System.out.println("바뀐 대수 : " +list.get(p).getCurParking());
					}
				}
			}
			
			for(int c = 0; c < list.size(); c++)
			{
				if(list.get(c).getCapacity() <= list.get(c).getCurParking())
				{
					list.remove(c);
					
				}
			}	
		}
		
		//데이터 저장된 상태니 건들지 말것
//		int result = 0;
//		
//		result = parkingApiService.insertParkingSeoul(list);
//		
//		if(result > 0)
//			System.out.println("값 insert OK");
		
		
		try
		{
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			new Gson().toJson(list, response.getWriter());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	   * createCalObj() method returns Calendar Object by parsing date string
	   * @param dateStr String object in "yyyy-MM-dd/HH:mm:ss" format
	   * @return Calendar object
	   */
	  public Calendar createCalObj(String dateStr) {
	    Calendar cal= Calendar.getInstance();
	    cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));
	    cal.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(5, 7))-1);
	    cal.set(Calendar.DATE, Integer.parseInt(dateStr.substring(8, 10)));
	    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateStr.substring(11, 13)));
	    cal.set(Calendar.MINUTE, Integer.parseInt(dateStr.substring(14, 16)));
	    cal.set(Calendar.SECOND, Integer.parseInt(dateStr.substring(17, 18)));

	    return cal;
	  }

}

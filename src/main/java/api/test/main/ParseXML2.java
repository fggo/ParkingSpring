package api.test.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.parking.api.model.vo.Parking;

public class ParseXML2 {
  private static String getTagValue(String tag, Element eElement) {
      NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
      Node nValue = (Node) nlList.item(0);
      if(nValue == null) 
          return null;
      return nValue.getNodeValue();
  }

  public static void main(String[] args) {
    Properties prop = new Properties();
    try {
      //prop.load(new FileReader("resources/config.properties"));
    	String path = Parking.class.getResource("./config.properties").getPath();
    	prop.load(new FileReader(path));
    } catch(IOException e) {
      e.printStackTrace();
    }
    String serviceKey = prop.getProperty("serviceKey2");

    String ckanResceId = "prkplce-info-std";

//    String instt_nm = "서울특별시"+ "%20" +"강남구";
//    String instt_nm = "서울특별시성동구도시관리공단";
    String instt_nm = "송파구시설관리공단";

    String urlStr = "http://api.data.go.kr/openapi/";
    urlStr += ckanResceId;
    urlStr += "?serviceKey=" + serviceKey;
    urlStr += "&type=" + "xml";
    urlStr += "&s_page=0";
    urlStr += "&s_list=1";
    urlStr += "&instt_nm=" + instt_nm;

    DocumentBuilderFactory dbFactory = null;
    DocumentBuilder dBuilder = null;
    Document doc = null;

    BufferedReader br = null;
//    Parking p = null;

    try {
      URL url = new URL(urlStr);

      HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
      urlconnection.setRequestMethod("GET");
      br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

      dbFactory = DocumentBuilderFactory.newInstance();
      dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.parse(urlStr);
      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("item");

      System.out.println("Parsing List Num # = " + nList.getLength());

      for(int i = 0; i< nList.getLength(); i++) {
        Node nNode = nList.item(i);
        
        if(nNode.getNodeType() == Node.ELEMENT_NODE) {
//          p = new Parking();

          Element eElement = (Element) nNode;
          System.out.println(getTagValue("prkplceNo", eElement));

//          prkplceNo  138-1-000001  주차장관리번호
//          prkplceNm  해운대구청 주변 공영  주차장명
//          prkplceSe  공영  주차장구분
//          prkplceType  노상  주차장유형
//          rdnmadr  부산광역시 해운대구 중동1로 37번길 일원  소재지도로명주소
//          lnmadr  부산광역시 해운대구 중1동 1372-3번지 일원  소재지지번주소
//          prkcmprt  27  주차구획수
//          feedingSe  1  급지구분
//          enforceSe  미시행  부제시행구분
//          operDay  평일+토요일+공휴일  운영요일
//          weekdayOperOpenHhmm  10:00  평일운영시작시각
//          weekdayOperColseHhmm  22:00  평일운영종료시각
//          satOperOperOpenHhmm  10:00  토요일운영시작시각
//          satOperCloseHhmm  22:00  토요일운영종료시각
//          holidayOperOpenHhmm  10:00  공휴일운영시작시각
//          holidayCloseOpenHhmm  22:00  공휴일운영종료시각
//          parkingchrgeInfo  유료  요금정보
//          basicTime  10  주차기본시간
//          basicCharge  500  주차기본요금
//          addUnitTime  10  추가단위시간
//          addUnitCharge  500  추가단위요금
//          dayCmmtktAdjTime  300  1일주차권요금적용시간
//          dayCmmtkt  15000  1일주차권요금
//          monthCmmtkt    월정기권요금
//          metpay  현금+카드  결제방법
//          spcmnt    특기사항
//          institutionNm  부산광역시 해운대구  관리기관명
//          phoneNumber  051-749-4555  전화번호
//          latitude  35.1617554732  위도
//          hardness  129.162538632  경도
//          referenceDate  2018-08-10  데이터기준일자
//          insttCode  3330000  제공기관코드
//          insttNm  부산광역시 해운대구  제공기관명
//          numOfRows  100  한 페이지 결과 수
//          pageNo  1  페이지 번호
//          totalCount  1000  전체 응답 row수

//          p.setParkingCode(getTagValue("PARKING_CODE", eElement));
//          ...
//          p.setCurParking(Integer.valueOf(getTagValue("CUR_PARKING", eElement)));
//          
//          System.out.println(p);
        }
      }
      
    } catch(IOException e) {
      e.printStackTrace();
    }
    catch(ParserConfigurationException e) {
      e.printStackTrace();
    } catch(SAXException e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
      
  }
}

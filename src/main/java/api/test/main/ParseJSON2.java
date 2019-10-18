package api.test.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import com.parking.api.model.vo.Parking;
import com.parking.common.template.JDBCTemplate;

public class ParseJSON2 {

  private static Properties prop = new Properties();

  public ParseJSON2() {
    try {
      //prop.load(new FileReader("resources/config.properties"));
    	String path = Parking.class.getResource("./config.properties").getPath();
    	prop.load(new FileReader(path));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    try {
      //prop.load(new FileReader("resources/config.properties"));
    	String path = Parking.class.getResource("./config.properties").getPath();
    	prop.load(new FileReader(path));
    } catch(IOException e) {
      e.printStackTrace();
    }

    String ckanResceId = "prkplce-info-std";
    String serviceKey2 = prop.getProperty("serviceKey2");
    String instt_nm1 = "서울특별시"+ "%20" +"강남구";
    //String instt_nm1 = "서울특별시"+ "%20" +" ";
    //String instt_nm = "서울특별시성동구도시관리공단";
//    String instt_nm = "송파구시설관리공단";

//    KOR -> UTF-8 변환
//    byte[] byteArr = instt_nm.getBytes(StandardCharsets.UTF_8);
//    ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(instt_nm);
//    instt_nm = new String(byteArr, StandardCharsets.UTF_8);

    String urlStr = "http://api.data.go.kr/openapi/";
    urlStr += ckanResceId;
    urlStr += "?serviceKey=" + serviceKey2;
    urlStr += "&type=" + "json";
//    urlStr += "&s_page=0";
//    urlStr += "&s_list=1";
    //urlStr += "&instt_nm=" + instt_nm;
    urlStr += "&instt_nm=" + instt_nm1;
    urlStr += "&pageNo=1";
    urlStr += "&numOfRows=100";
    //urlStr += "&totalCount=100";
//    case1
//    3150000  서울특별시 강서구
//    3200000  서울특별시 관악구
//    3210000  서울특별시 서초구
//    3110000  서울특별시 은평구
//    3160000  서울특별시 구로구
//    3190000  서울특별시 동작구
//    3220000  서울특별시 강남구
//    3140000  서울특별시 양천구
//             서울특별시 종로구
//    3070000  서울특별시 성북구
//    3080000  서울특별시 강북구
//             서울특별시 중구
//    3060000  서울특별시 중랑구
//    3170000  서울특별시 금천구
//    3240000  서울특별시 강동구
//    3180000  서울특별시 영등포구
//    3230000  서울특별시 송파구
//    3040000  서울특별시 광진구
//             서울특별시 동대문구    
//    case2    
//    B553774  서울시설공단(노원구 도봉구 동대문구 마포구 서대문구 용산구 [위에 없는주소] +
//                      강남구 강동구 강북구 강서구 관악구 광진구 구로구 금천구 동작구 서초구 성동구 
//                      송파구 양천구 영등포구 은평구 종로구 중구)
//    B551897  서울특별시성동구도시관리공단 (성동구)
//    B553766  서울교통공사 (서초구 성동구)
//    B552067  서울특별시중구시설관리공단 (중구)
    
    
    
//    3030000  서울특별시 성동구 - 없음
//    B551282  강북구도시관리공단 (강북구) - 없음

    BufferedReader br = null;
    try {
      URL url = new URL(urlStr);
      URLConnection urlConnection = url.openConnection();
      HttpURLConnection connection = null;
      if(urlConnection instanceof HttpURLConnection) {
        connection = (HttpURLConnection) urlConnection;
      }
      else {
        System.out.println("error");
        return;
      }
      br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
      String result = "";
      String line;
      while((line = br.readLine()) != null) {
        result += line+"\n";
      }
      System.out.println(result);

      JSONObject obj = new JSONObject(result);
      JSONArray arr = obj.getJSONObject("response").getJSONObject("body").getJSONArray("items");
      Parking p = null;

      System.out.println("Parsing List Num # = " + arr.length());

      for(int i =0; i<arr.length(); i++) {
        p = new Parking();

        //p.setParkingCode(arr.getJSONObject(i).getString("PARKING_CODE"));
        p.setParkingName(arr.getJSONObject(i).getString("prkplceNm"));
        p.setAddr(arr.getJSONObject(i).getString("lnmadr"));
        p.setLatitude(arr.getJSONObject(i).getDouble("latitude"));
        p.setLongitude(arr.getJSONObject(i).getDouble("longitude"));
        p.setInsttNm(arr.getJSONObject(i).getString("insttNm"));
       // p.setParkingType(arr.getJSONObject(i).getString("PARKING_TYPE"));
        //p.setParkingTypeNm(arr.getJSONObject(i).getString("PARKING_TYPE_NM"));
        //p.setOperationRule(arr.getJSONObject(i).getInt("OPERATION_RULE"));
        //p.setOperationRuleNm(arr.getJSONObject(i).getString("OPERATION_RULE_NM"));
        //p.setTel(arr.getJSONObject(i).getString("TEL"));
        //p.setQueStatus(arr.getJSONObject(i).getInt("QUE_STATUS"));
        //p.setQueStatusNm(arr.getJSONObject(i).getString("QUE_STATUS_NM"));
        //p.setCapacity(arr.getJSONObject(i).getInt("CAPACITY"));
        //p.setCurParking(arr.getJSONObject(i).getInt("CUR_PARKING"));
        
        System.out.println(p.getParkingName()+" " + p.getAddr() + " " + "관리공단 : " + p.getInsttNm() +"  institutionNm : " + arr.getJSONObject(i).getString("institutionNm").toString());
      }
    } catch(IOException e) {
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

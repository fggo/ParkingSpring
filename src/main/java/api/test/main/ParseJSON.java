package api.test.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import com.parking.api.model.vo.Parking;

public class ParseJSON {
  public static void main(String[] args) {
    Properties prop = new Properties();
    try {
      String path = ParseJSON.class.getResource("config.properties").getPath();
      prop.load(new FileReader(path));
    } catch(IOException e) {
      e.printStackTrace();
    }

    List<Parking> list = new ArrayList<Parking>();
    String serviceKey = prop.getProperty("serviceKey");
    JSONObject obj = null;
    Parking p = null;
    int cc = 0;
    String urlStr= null;
    JSONArray arr = null;

    try {
      for(int count = 1; count <= 2; count++) {
        int start_index = 1;
        int end_index = 100;

        if(count > 1)
        {
          urlStr = "http://openapi.seoul.go.kr:8088/" + serviceKey 
              + "/json/GetParkInfo/"+((end_index*(count-1))+1) +"/"+end_index*count+"/강남";
        }else
        {
          urlStr = "http://openapi.seoul.go.kr:8088/" + serviceKey 
              + "/json/GetParkInfo/"+start_index +"/"+end_index+"/강남";
        }
        //      urlStr = "http://openapi.seoul.go.kr:8088/" + serviceKey 
        //          + "/json/GetParkInfo/"+start_index*count +"/"+end_index*count+"/강남";

        BufferedReader br = null;
        URL url = new URL(urlStr);
        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
        urlconnection.setRequestMethod("GET");
        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

        String result = "";
        String line;
        while((line = br.readLine()) != null) {
          result += (line + "\n");
        }

        //System.out.println(result);
        obj = new JSONObject(result);
        arr = obj.getJSONObject("GetParkInfo").getJSONArray("row");

        cc += arr.length();

        //System.out.println("Parsing List Num # = " + arr.length());

        if(count == 1)
        {

          for(int i = 0; i < arr.length(); i++) 
          {
            if(i==0)
            {
              p = new Parking();
              p.setParkingCode(arr.getJSONObject(i).getString("PARKING_CODE"));
              p.setParkingName(arr.getJSONObject(i).getString("PARKING_NAME"));
              p.setAddr(arr.getJSONObject(i).getString("ADDR"));
              p.setParkingType(arr.getJSONObject(i).getString("PARKING_TYPE"));
              p.setParkingTypeNm(arr.getJSONObject(i).getString("PARKING_TYPE_NM"));
              p.setOperationRule(arr.getJSONObject(i).getInt("OPERATION_RULE"));
              p.setOperationRuleNm(arr.getJSONObject(i).getString("OPERATION_RULE_NM"));
              p.setTel(arr.getJSONObject(i).getString("TEL"));
              p.setQueStatus(arr.getJSONObject(i).getInt("QUE_STATUS"));
              p.setQueStatusNm(arr.getJSONObject(i).getString("QUE_STATUS_NM"));
              p.setCapacity(arr.getJSONObject(i).getInt("CAPACITY"));
              p.setCurParking(arr.getJSONObject(i).getInt("CUR_PARKING"));

              list.add(p);
            }
            else
            {
              p = new Parking();
              p.setParkingCode(arr.getJSONObject(i).getString("PARKING_CODE"));
              p.setParkingName(arr.getJSONObject(i).getString("PARKING_NAME"));
              p.setAddr(arr.getJSONObject(i).getString("ADDR"));
              p.setParkingType(arr.getJSONObject(i).getString("PARKING_TYPE"));
              p.setParkingTypeNm(arr.getJSONObject(i).getString("PARKING_TYPE_NM"));
              p.setOperationRule(arr.getJSONObject(i).getInt("OPERATION_RULE"));
              p.setOperationRuleNm(arr.getJSONObject(i).getString("OPERATION_RULE_NM"));
              p.setTel(arr.getJSONObject(i).getString("TEL"));
              p.setQueStatus(arr.getJSONObject(i).getInt("QUE_STATUS"));
              p.setQueStatusNm(arr.getJSONObject(i).getString("QUE_STATUS_NM"));
              p.setCapacity(arr.getJSONObject(i).getInt("CAPACITY"));
              p.setCurParking(arr.getJSONObject(i).getInt("CUR_PARKING"));

              if(p.getParkingName().equals(list.get(list.size()-1).getParkingName()))
              {
                p=null;

              }else
              {
                list.add(p);
              }
            }
          }
        }
        else
        {
          System.out.println("2번째 길이 : " + arr.length());
          int number = 0;
          for(int i = end_index; i < end_index+arr.length(); i++) 
          {

            p = new Parking();
            p.setParkingCode(arr.getJSONObject(i-end_index).getString("PARKING_CODE"));
            p.setParkingName(arr.getJSONObject(i-end_index).getString("PARKING_NAME"));
            p.setAddr(arr.getJSONObject(i-end_index).getString("ADDR"));
            p.setParkingType(arr.getJSONObject(i-end_index).getString("PARKING_TYPE"));
            p.setParkingTypeNm(arr.getJSONObject(i-end_index).getString("PARKING_TYPE_NM"));
            p.setOperationRule(arr.getJSONObject(i-end_index).getInt("OPERATION_RULE"));
            p.setOperationRuleNm(arr.getJSONObject(i-end_index).getString("OPERATION_RULE_NM"));
            p.setTel(arr.getJSONObject(i-end_index).getString("TEL"));
            p.setQueStatus(arr.getJSONObject(i-end_index).getInt("QUE_STATUS"));
            p.setQueStatusNm(arr.getJSONObject(i-end_index).getString("QUE_STATUS_NM"));
            p.setCapacity(arr.getJSONObject(i-end_index).getInt("CAPACITY"));
            p.setCurParking(arr.getJSONObject(i-end_index).getInt("CUR_PARKING"));


            if(p.getParkingName().equals(list.get(list.size()-1).getParkingName()))
            {
              
              p = null;
              

            }else
            {
              System.out.println("여기옴");
              list.add(p);
              number++;
              System.out.println(list.get(list.size()-1));
            }
            
          }
          System.out.println("number" + number);    
        }
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
    
    System.out.println(cc);
    




    System.out.println(list.size());
    for(Parking pk : list)
    {
      System.out.println(pk);
    }

  }
}

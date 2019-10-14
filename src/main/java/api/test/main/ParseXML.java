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

public class ParseXML {
  private static String getTagValue(String tag, Element eElement) {
    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
    Node nValue = (Node) nlList.item(0);
    if(nValue == null) 
      return null;
    return nValue.getNodeValue();
  }

  public static void main(String[] args) {
    Properties prop = new Properties();
    String serviceKey = "";
    try {
      prop.load(new FileReader("resources/config.properties"));
      serviceKey = prop.getProperty("serviceKey");
    } catch(IOException e) {
      e.printStackTrace();
    }

    DocumentBuilderFactory dbFactory = null;
    DocumentBuilder dBuilder = null;
    Document doc = null;

    BufferedReader br = null;
    Parking p = null;

    try {
      String urlStr = "http://openapi.seoul.go.kr:8088/" +serviceKey+ "/xml/GetParkInfo/1/5/";
      URL url = new URL(urlStr);

      HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
      urlconnection.setRequestMethod("GET");
      br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

      dbFactory = DocumentBuilderFactory.newInstance();
      dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.parse(urlStr);
      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("row");

      System.out.println("Parsing List Num # = " + nList.getLength());

      for(int i = 0; i< nList.getLength(); i++) {
        Node nNode = nList.item(i);
        
        if(nNode.getNodeType() == Node.ELEMENT_NODE) {
          p = new Parking();

          Element eElement = (Element) nNode;

          p.setParkingCode(getTagValue("PARKING_CODE", eElement));
          p.setParkingName(getTagValue("PARKING_NAME", eElement));
          p.setAddr(getTagValue("ADDR", eElement));
          p.setParkingType(getTagValue("PARKING_TYPE", eElement));
          p.setParkingTypeNm(getTagValue("PARKING_TYPE_NM", eElement));
          p.setOperationRule(Integer.valueOf(getTagValue("OPERATION_RULE", eElement)));
          p.setOperationRuleNm(getTagValue("OPERATION_RULE_NM", eElement));
          p.setTel(getTagValue("TEL", eElement));
          p.setQueStatus(Integer.valueOf(getTagValue("QUE_STATUS", eElement)));
          p.setQueStatusNm(getTagValue("QUE_STATUS_NM", eElement));
          p.setCapacity(Integer.valueOf(getTagValue("CAPACITY", eElement)));
          p.setCurParking(Integer.valueOf(getTagValue("CUR_PARKING", eElement)));
          
          System.out.println(p);
        }
      }
      
    } catch(IOException e) {
      e.printStackTrace();
    } catch(ParserConfigurationException e) {
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

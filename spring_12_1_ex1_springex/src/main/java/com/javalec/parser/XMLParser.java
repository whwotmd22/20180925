package com.javalec.parser;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.javalec.ex.DBConnection;

public class XMLParser {

	public static void main(String[] args) {
		

    	
		ArrayList<String> placeInfos = SelectPlaceInfo();
//		System.out.println(placeInfos);
		
		for(int j=0; j< placeInfos.size();j++){
			System.out.println("placeInfo " + j + " : "+placeInfos.get(j));
		}
		BufferedReader br = null;
        //DocumentBuilderFactory ����
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        
        try {
        	String LAWD_CD = "null";
        	String DEAL_YMD = "null";
        	
        	String TradePrice ="null";
        	String EstimateDt= "null";
        	String Est= "null";
        	String AddressDong= "null";
        	String ApartName= "null";
        	String TradeMonth= "null";
        	String TradeDt= "null";
        	String ApartSize= "null";
        	String AddressNum= "null";	
        	String AddressCode= "null";
        	String ApartHt= "null";
        	
        	
        	for(int y=2015;y<2019;y++){
        		for(int m=1;m<13;m++){
        			System.out.println(Integer.toString(y)+Integer.toString(m));
//        			for(int k=0; k< placeInfos.size();k++){
//                		LAWD_CD = placeInfos.get(k);
                		LAWD_CD = "41465";
        				if(m<10)
        				{
        					DEAL_YMD = Integer.toString(y) + "0" + Integer.toString(m);
        				}else{
        					DEAL_YMD = Integer.toString(y) + Integer.toString(m);
        				}
        				
	        			//OpenApiȣ��
			            String urlstr = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade?"
			            		+ "LAWD_CD=" + LAWD_CD
			            		+ "&DEAL_YMD=" + DEAL_YMD
			            		+ "&serviceKey=41gaKXzwt36if%2Fk8ay8tv%2F4Flh74z29cR4NZ2glfitAvn1HD%2BfGPoA%2FRh1gs5IjVM7k6g%2FGsgh8p0aVeaaRUvg%3D%3D";
			            URL url = new URL(urlstr);
			            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			            System.out.println(url);
			            //���� �б�
			            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			            String result = "";
			            String line;
			            while ((line = br.readLine()) != null) {
			                result = result + line.trim();// result = URL�� XML�� ���� ��
			            }
			            
			            // xml �Ľ��ϱ�
			            InputSource is = new InputSource(new StringReader(result));
			            builder = factory.newDocumentBuilder();
			            doc = builder.parse(is);
			            XPathFactory xpathFactory = XPathFactory.newInstance();
			            XPath xpath = xpathFactory.newXPath();
			            // XPathExpression expr = xpath.compile("/response/body/items/item");
			            XPathExpression expr = xpath.compile("//items/item");
			            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			            for (int i = 0; i < nodeList.getLength(); i++) {
			                NodeList child = nodeList.item(i).getChildNodes();
			                for (int j = 0; j < child.getLength(); j++) {
			                    Node node = child.item(j);
			                    if(node.getNodeName() == "�ŷ��ݾ�")
			                    	TradePrice = node.getTextContent();
			                    if(node.getNodeName() == "����⵵")
			                    	EstimateDt = node.getTextContent();
			                    if(node.getNodeName() == "��")
			                    	Est = node.getTextContent();
			                    if(node.getNodeName() == "������")
			                    	AddressDong = node.getTextContent();
			                    if(node.getNodeName() == "����Ʈ")
			                    	ApartName = node.getTextContent();
			                    if(node.getNodeName() == "��")
			                    	TradeMonth = node.getTextContent();
			                    if(node.getNodeName() == "��")
			                    	TradeDt = node.getTextContent();
			                    if(node.getNodeName() == "�������")
			                    	ApartSize = node.getTextContent();
			                    if(node.getNodeName() == "����")
			                    	AddressNum = node.getTextContent();
			                    if(node.getNodeName() == "�����ڵ�")
			                    	AddressCode = node.getTextContent();
			                    if(node.getNodeName() == "��")
			                    	ApartHt = node.getTextContent();	                    
			                }
		                   SaveData(TradePrice, EstimateDt, Est, AddressDong, ApartName, TradeMonth, TradeDt, AddressNum, AddressCode, ApartHt, ApartSize);
		
			            }
			        }
        		}
//        	}
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	public static void SaveData(String TradePrice,String EstimateDt,String Est,String AddressDong,String ApartName,String TradeMonth,String TradeDt,String AddressNum,String AddressCode,String ApartHt, String ApartSize){
		Connection conn = null; 		 // DB����� ����(����)�� ���� ��ü
        PreparedStatement pstm = null;	 // SQL ���� ��Ÿ���� ��ü
        String query = null;

		try {
	        conn = DBConnection.getConnection();
			query = "INSERT INTO APART_TRADE_MST ( "
					+ "TradePrice "
					+ ",EstimateDt "
					+ ",Est "
					+ ",AddressDong "
					+ ",ApartName "
					+ ",TradeMonth "
					+ ",TradeDt "
					+ ",ApartSize "
					+ ",AddressNum "
					+ ",AddressCode "
					+ ",ApartHt"
					+ ",CREATE_USER"
					+ ",CREATE_DATE"
					+ ",UPDATE_USER"
					+ ",UPDATE_DATE) Values ("
					+ "?,?,?,?,?,?,?,?,?,?,?," 
					+ "'JSCHO'" 
					+ ",sysdate,"
					+ "'JSCHO'"
					+ ",sysdate)";
            pstm = conn.prepareStatement(query);
            
            pstm.setString(1, TradePrice);
            pstm.setString(2, EstimateDt);
            pstm.setString(3, Est);
            pstm.setString(4, AddressDong);
            pstm.setString(5, ApartName);
            pstm.setString(6, TradeMonth);
            pstm.setString(7, TradeDt);
            pstm.setString(8, ApartSize);
            pstm.setString(9, AddressNum);
            pstm.setString(10, AddressCode);
            pstm.setString(11, ApartHt);
            pstm.executeUpdate();
            pstm.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public static ArrayList<String> SelectPlaceInfo() {
		Connection conn = null; 		 // DB����� ����(����)�� ���� ��ü
        PreparedStatement pstm = null;	 // SQL ���� ��Ÿ���� ��ü
        ResultSet rs = null; 			 // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
        String query = null;

		ArrayList<String> placeinfos = new ArrayList<String>();
		
		try {
			

	        conn = DBConnection.getConnection();
			query = "SELECT DISTINCT(A)AS PLACE_DISTINCT_CODE FROM ("
					+ "select  SUBSTR(PLACE_CODE, 1, 5) AS A "
					+ "from PLACE_COMM_CODE) ORDER BY A ASC";
            pstm = conn.prepareStatement(query);
            System.out.println(query);
            rs = pstm.executeQuery();
            
			while (rs.next()) {
//				String PLACE_CODE = rs.getString("PLACE_CODE");
//				String PLACE_NAME = rs.getString("PLACE_NAME");
//				String USE_YN = rs.getString("USE_YN");
//				
//				PlaceInfo placeinfo = new PlaceInfo(PLACE_CODE, PLACE_NAME, USE_YN);
				
				String PLACE_CODE = rs.getString("PLACE_DISTINCT_CODE");
				
				placeinfos.add(PLACE_CODE);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return placeinfos;
		
		
	}

}

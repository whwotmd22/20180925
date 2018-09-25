package com.javalec.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String home(Locale locale, Model model) {
		Connection conn = null; 		 // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null;	 // SQL 문을 나타내는 객체
        ResultSet rs = null; 			 // 쿼리문을 날린것에 대한 반환값을 담을 객체
        String query = null;
        
        Gson gson = new Gson();
        JsonArray jArray = new JsonArray();
		ArrayList<MatroInfo> matroInfos = new ArrayList<MatroInfo>();
		try {
			

	        conn = DBConnection.getConnection();
			
			query = "SELECT * FROM MATRO_MASTER_INFO";
            pstm = conn.prepareStatement(query);
            System.out.println(query);
            rs = pstm.executeQuery();
            
			while (rs.next()) {
				String line_num = rs.getString("line_num");
				String cyber_st_code = rs.getString("cyber_st_code");
				String xpoint_wgs = rs.getString("xpoint_wgs");
				String ypoint = rs.getString("ypoint");
				String ypoint_wgs = rs.getString("ypoint_wgs");
				String xpoint = rs.getString("xpoint");
				String station_cd = rs.getString("station_cd");
				String station_nm = rs.getString("station_nm");
				String fr_code = rs.getString("fr_code");
				
				MatroInfo matroInfo = new MatroInfo(line_num, cyber_st_code, xpoint_wgs, ypoint, ypoint_wgs, xpoint, station_cd, station_nm, fr_code);
				matroInfos.add(matroInfo);
				

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
		
		String json = gson.toJson(matroInfos);
		
		return json;
		
		
	}

}

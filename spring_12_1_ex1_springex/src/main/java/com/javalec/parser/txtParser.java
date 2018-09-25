package com.javalec.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.javalec.ex.DBConnection;

public class txtParser {
    public static void main(String[] args){
        try{
            //파일 객체 생성
            File file = new File("C:\\Users\\paenj\\Desktop\\txtParser\\entrc_seoul.txt");
            //입력 스트림 생성 
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            String[] array = null;
            
            while((line = bufReader.readLine()) != null){
                array = line.split(",");
                SaveData( array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11], array[12],array[13], array[14], array[15], array[16], array[17]);
            }
            
            //.readLine()은 끝에 개행문자를 읽지 않는다.            
            bufReader.close();
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public static void SaveData(String A,String B,String C,String D,String E,String F,String G,String H,String I,String J,String K,String L,String M,String N,String O,String P,String Q,String R){
		Connection conn = null; 		 // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null;	 // SQL 문을 나타내는 객체
        String query = null;

        if(R != null || R != "")
		try {
	        conn = DBConnection.getConnection();
			query = "INSERT INTO BUILDING_LOC_INFO ( "
					+ "시군구코드 "
					+ ",출입구일련번호 "
					+ ",법정동코드 "
					+ ",시도명 "
					+ ",시군구명 "
					+ ",읍면동명 "
					+ ",도로명코드 "
					+ ",도로명 "
					+ ",지하여부 "
					+ ",건물본번 "
					+ ",건물부번"
					+ ",건물명"
					+ ",우편번호"
					+ ",건물용도분류"
					+ ",건물군여부"
					+ ",관할행정동"
					+ ",X좌표"
					+ ",Y좌표"
					+ ",CREATE_USER"
					+ ",CREATE_DATE"
					+ ",UPDATE_USER"
					+ ",UPDATE_DATE) Values ("
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" 
					+ ",'JSCHO'" 
					+ ",sysdate"
					+ ",'JSCHO'"
					+ ",sysdate)";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, A);
            pstm.setString(2, B);
            pstm.setString(3, C);
            pstm.setString(4, D);
            pstm.setString(5, E);
            pstm.setString(6, F);
            pstm.setString(7, G);
            pstm.setString(8, H);
            pstm.setString(9, I);
            pstm.setString(10, J);
            pstm.setString(11, K);
            pstm.setString(12, L);
            pstm.setString(13, M);
            pstm.setString(14, N);
            pstm.setString(15, O);
            pstm.setString(16, P);
            pstm.setString(17, Q);
            pstm.setString(18, R);
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
}
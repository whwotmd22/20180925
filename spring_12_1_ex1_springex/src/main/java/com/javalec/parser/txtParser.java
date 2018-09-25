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
            //���� ��ü ����
            File file = new File("C:\\Users\\paenj\\Desktop\\txtParser\\entrc_seoul.txt");
            //�Է� ��Ʈ�� ���� 
            FileReader filereader = new FileReader(file);
            //�Է� ���� ����
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            String[] array = null;
            
            while((line = bufReader.readLine()) != null){
                array = line.split(",");
                SaveData( array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11], array[12],array[13], array[14], array[15], array[16], array[17]);
            }
            
            //.readLine()�� ���� ���๮�ڸ� ���� �ʴ´�.            
            bufReader.close();
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public static void SaveData(String A,String B,String C,String D,String E,String F,String G,String H,String I,String J,String K,String L,String M,String N,String O,String P,String Q,String R){
		Connection conn = null; 		 // DB����� ����(����)�� ���� ��ü
        PreparedStatement pstm = null;	 // SQL ���� ��Ÿ���� ��ü
        String query = null;

        if(R != null || R != "")
		try {
	        conn = DBConnection.getConnection();
			query = "INSERT INTO BUILDING_LOC_INFO ( "
					+ "�ñ����ڵ� "
					+ ",���Ա��Ϸù�ȣ "
					+ ",�������ڵ� "
					+ ",�õ��� "
					+ ",�ñ����� "
					+ ",���鵿�� "
					+ ",���θ��ڵ� "
					+ ",���θ� "
					+ ",���Ͽ��� "
					+ ",�ǹ����� "
					+ ",�ǹ��ι�"
					+ ",�ǹ���"
					+ ",�����ȣ"
					+ ",�ǹ��뵵�з�"
					+ ",�ǹ�������"
					+ ",����������"
					+ ",X��ǥ"
					+ ",Y��ǥ"
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
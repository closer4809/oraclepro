/* 메소드 파라미터를 Vo사용 */

package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PhoneDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@3.36.114.215:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	
	
    private void getConnection() {
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
		    // 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		    // 3. SQL문 준비 / 바인딩 / 실행
		    
		    // 4.결과처리
		
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
			} 
    	}   
		    // 5. 자원정리
		private void close() {
			try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
		
		}
		//펄 리스트 가져오기
		
		public List<PersonVo> getPhoneList() {
		// DB값을 가져와서 ArrayList로 전달

		// 리스트 생성
		List<PersonVo> phoneList = new ArrayList<PersonVo>();

		
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " SELECT  phone_id, ";
			query += "         name, ";
			query += "         hp, ";
			query += "         company ";
			query += " from    person ";
		
			
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while (rs.next()) {
				int phoneId = rs.getInt("phone_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
		
				PersonVo personVo = new PersonVo(phoneId, name, hp, company);

				phoneList.add(personVo);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		close();

		return phoneList;

	}
	
	//삭제하기
	public int phoneDelete(int phoneId) {
		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from person ";
			query += " where phone_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, phoneId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.close();

		return count;
	}
	
	public int phoneUpdate(PersonVo personVo) {

		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " UPDATE person ";
			query += " SET    name = ?, ";
			query += "        hp = ?, ";
			query += "        company = ? ";
			query += " where  phone_id = ? ";
			
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPhoneId());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 4.결과처리
		System.out.println(count + "건 수정");

		close();

		return count;
	}

	public int phoneInsert(PersonVo personVo) {

		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into person ";
			query += " values(seq_phone_id.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		close();

		return count; // 성공갯수 리턴
	}
	
	public List<PersonVo> getPersonList(String search) {
		
		//리스트 생성
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		this.getConnection();

		try {
		  
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    query += " select  phone_id, ";
		    query += "         name, ";
	        query += "         hp, ";
	        query += "         company ";
		    query += " from    person ";
		    query += " where (name || hp || company) like " + "'%" + search + "%' ";
		 
		    
		    pstmt = conn.prepareStatement(query);
		    
		    rs = pstmt.executeQuery();
			
		    // 4.결과처리
			while(rs.next()) {
				int phoneId = rs.getInt("phone_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo personVo = new PersonVo(phoneId, name, hp, company);
				
				personList.add(personVo);
			}
		    
		    

		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		this.close();
		
		return personList;
		
	}
	
	


}
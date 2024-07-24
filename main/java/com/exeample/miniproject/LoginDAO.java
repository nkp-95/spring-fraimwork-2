package com.exeample.miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LoginDAO {
	Connection conn = null;
	PreparedStatement pstmt;
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		
		try {
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//회원 추가
	public void insert(Login l) {
		open();
		String sql = "INSERT INTO student(username, userIdCard, phone, email, address, id, password) values(?, ?, ?, ?, ?, ?, ?)";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, l.getUserName());
			pstmt.setInt(2, l.getUserIdCard());
			pstmt.setInt(3, l.getPhone());
			pstmt.setString(4, l.getEmail());
			pstmt.setString(5, l.getAddress());
			pstmt.setString(6, l.getId());
			pstmt.setString(7, l.getPassword());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	//전체회원
	public List<Login> getAll(){
		open();
		List<Login> login = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from login");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Login l = new Login();
				l.setSerialNum(rs.getInt("serialNum"));
				l.setUserName(rs.getString("userName"));
				l.setUserIdCard(rs.getInt("userIdCard"));
				l.setPhone(rs.getInt("phone"));
				l.setEmail(rs.getString("email"));
				l.setAddress(rs.getString("address"));
				l.setId(rs.getString("id"));
				l.setPassword(rs.getString("password"));
				
				login.add(l);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return login;
	}
	
	
	
}

package com.koreait.pjt.db;

//insert, update, delete에 씀  sql문에서 달라지는 것만 주입 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplate {
	
	///////////insert, update,delete
	public static int executeUpdate(String sql, JdbcUpdateInterface jdbc) {
		int result=0;                           //UserDAO에서 메소드가 구현 
		Connection con=null;
		PreparedStatement ps=null;
		
	try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			jdbc.update(ps);//			
			result= ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
		
    return result;
	}
	
	
	//////SELECT////////////////////////////////////
	public static int executeQuery(String sql, JdbcSelectInterface jdbc) {
		int result=0;                           //UserDAO에서 메소드가 구현 
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			con=DbCon.getCon();
			ps=con.prepareStatement(sql);
			jdbc.prepared(ps);
			rs=ps.executeQuery();
			result= jdbc.executeQuery(rs);//	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps, rs);
		}
		
    return result;
	}
		


}
	



package com.koreait.matzip.db;

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
			con=DbManager.getCon();
			ps=con.prepareStatement(sql);
			jdbc.update(ps);//			
			result= ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.close(con, ps);
		}
		
    return result;
	}
	
	
	//////SELECT////////////////////////////////////
	public static void executeQuery(String sql, JdbcSelectInterface jdbc) {
                    //UserDAO에서 메소드가 구현 
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			con=DbManager.getCon();
			ps=con.prepareStatement(sql);
			jdbc.prepared(ps);
			rs=ps.executeQuery();
			jdbc.executeQuery(rs);//	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.close(con, ps, rs);
		}

	}
		


}
	



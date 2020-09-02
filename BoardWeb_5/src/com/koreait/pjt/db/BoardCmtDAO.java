package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.vo.BoardCmtVO;
import com.koreait.vo.BoardVO;

public class BoardCmtDAO {
	public static int insCmt(BoardCmtVO param) {
		String sql=" INSERT INTO t_board5_cmt( "
				  +" i_cmt, i_board, i_user, cmt)"
				  +" VALUES"
				  +" (seq_board5_cmt.nextval, ?, ?, ?) ";
	
	
	return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

		@Override
		public void update(PreparedStatement ps) throws SQLException {
			ps.setInt(1, param.getI_board());
			ps.setInt(2, param.getI_user());
			ps.setNString(3, param.getCmt());
			}
			
		});
	
	}
	
	public static List<BoardCmtVO> selCmtList(int i_board){
		 List<BoardCmtVO> list = new ArrayList();
		String sql= "SELECT a.i_cmt, b.i_user, B.profile_img,"
				+ " a.cmt, b.nm, TO_CHAR(A.r_dt, 'YYYY/MM/DD HH24:MI') as r_dt,"
				+ " TO_CHAR(A.m_dt, 'YYYY/MM/DD HH24:MI') as m_dt"
				+ " from t_board5_cmt a"
				+ " inner join t_user b "
				+ " on a.i_user=b.i_user"
				+ " where a.i_board= ? "
				+ " ORDER BY a.i_cmt ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
		
				
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					BoardCmtVO param =new BoardCmtVO();					
					int i_cmt=rs.getInt("i_cmt");
					String cmt=rs.getNString("cmt");
					String r_dt=rs.getNString("r_dt");
					String m_dt=rs.getNString("m_dt");
					int i_user=rs.getInt("i_user");
					String nm=rs.getNString("nm");
					String profile_img=rs.getNString("profile_img");
					
					param.setI_cmt(i_cmt);
					param.setCmt(cmt);
					param.setM_dt(m_dt);
					param.setR_dt(r_dt);
					param.setNm(nm);
					param.setI_user(i_user);
					param.setProfile_img(profile_img);
					

					
					list.add(param);
				}
				return 1;
			}
			
		});
				
		return list;
	}
		

	
	

     public static int updCmt(BoardCmtVO param) {
    	 String sql="UPDATE t_board5_cmt set cmt=?, m_dt=sysdate WHERE i_cmt=? ";
    	 
    	 
    	 return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getCmt());
				ps.setInt(2, param.getI_cmt());
				
			}
			
    	 });	 
	    
     }
	
	
	 public static int delcmt(BoardCmtVO param) {		
       String sql=" DELETE FROM t_board5_cmt WHERE i_cmt=? and i_user=? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
		
				ps.setInt(1, param.getI_cmt());
				ps.setInt(2, param.getI_user());

			}
		});
	
	}

}

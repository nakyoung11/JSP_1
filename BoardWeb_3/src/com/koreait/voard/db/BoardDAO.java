package com.koreait.voard.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.koreait.board.vo.BoardVO;

public class BoardDAO {

	public static List<BoardVO> selBoardList() {
		List<BoardVO> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = " SELECT i_board, title, i_student FROM t_board ORDER BY i_board DESC";

		try {
			con = DBCon.getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				//값가져오기
				int i_board = rs.getInt("i_board");
				String title = rs.getNString("title");
				int i_student = rs.getInt("i_student");
				
				//작은 서랍 !
				BoardVO vo = new BoardVO();
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setI_student(i_student);
			
				//큰서랍!
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCon.close(con, ps, rs);
		}
		return list;

	}
	
	//////////////////////////////////////
	public static BoardVO selBoard(BoardVO param) {
		BoardVO vo=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs= null;
		
	
		String sql=" SELECT i_board, title, ctnt, i_student FROM t_board WHERE i_board=? ";
		
		
		try {
			con=DBCon.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			rs=ps.executeQuery();
			
			if(rs.next()) {
				String title= rs.getNString("title");
		    	String ctnt= rs.getNString("ctnt");
		    	int i_student=rs.getInt("i_student"); 
		    	int i_board=rs.getInt("i_board");
		    
		    	vo= new BoardVO();
		    	vo.setI_board(i_board);
		    	vo.setTitle(title); 
		    	vo.setCtnt(ctnt);
		    	vo.setI_student(i_student);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBCon.close(con, ps, rs);
		}
		
		return vo;
		
		
	}
	
//////////////////////////////////////////////////////////////////////////
	public static void selBoardWrite(BoardVO param) {
		
	BoardVO vo=null;
	Connection con=null;
	PreparedStatement ps=null;
	
	
	String sql= " INSERT INTO t_board(i_board, title, ctnt, i_student)"
	            +" SELECT nvl(max(i_board),0)+1, ?, ?, ? FROM t_board ";
	
	try {

		con=DBCon.getCon();
		ps=con.prepareStatement(sql);
		ps.setNString(1, param.getTitle());
		ps.setNString(2, param.getCtnt());
		ps.setInt(3,param.getI_student());		
		ps.executeUpdate();
		
	
		
	}catch(Exception e) {
		
	}finally {
		DBCon.close(con, ps);
	}
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

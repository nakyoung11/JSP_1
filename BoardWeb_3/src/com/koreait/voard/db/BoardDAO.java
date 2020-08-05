package com.koreait.voard.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}

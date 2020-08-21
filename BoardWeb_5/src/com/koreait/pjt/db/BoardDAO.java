package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.koreait.vo.BoardVO;

import javafx.css.PseudoClass;

public class BoardDAO {
	public static List<BoardVO> selBoardList() {
		final List<BoardVO> list = new ArrayList();
		// 레퍼런스 상수는 주소값을 고정!! 객체에 추가를 막는것은 아니다.

		String sql = " SELECT A.i_board, A.title, A.hits, B.nm, A.r_dt, A.m_dt FROM t_board5 A " + " inner Join t_user B"
				+ " on A.i_user = B.i_user " + " ORDER BY i_board DESC ";

		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while (rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					int hits = rs.getInt("hits");
					String nm = rs.getNString("nm");
					String r_dt = rs.getNString("r_dt");
					String m_dt = rs.getNString("m_dt");

					BoardVO vo = new BoardVO();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setNm(nm);
					vo.setR_dt(r_dt);
					vo.setM_dt(m_dt);

					list.add(vo);
				}

				return 1;
			}

		});

		return list;
	}

	/// 글쓰기 /////////////

	public static int insBoardWrite(BoardVO param) {

		String sql = " INSERT INTO t_board5(i_board, title, ctnt, i_user) " + " values (seq_board.nextval, ?, ?, ? ) ";

		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());

			}
		});

	}

	///////////////////////// 상세글
	public static BoardVO selBoard(BoardVO param) {
		BoardVO vo = new BoardVO();

		String sql = " SELECT A.i_board, A.title, A.ctnt, A.hits, B.nm, " 
		        + " A.r_dt, A.m_dt, A.hits," 
		        + " A.i_user FROM t_board5 A "
				+ " inner Join t_user B" + " on A.i_user = B.i_user " + " WHERE i_board= ? ";

		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				int rsult = 0;
				if (rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					int hits = rs.getInt("hits");
					String nm = rs.getNString("nm");
					String ctnt = rs.getNString("ctnt");
					String r_dt = rs.getNString("r_dt");
					String m_dt=rs.getNString("m_dt");
					int i_user = rs.getInt("i_user");
					

					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setNm(nm);
					vo.setCtnt(ctnt);
					vo.setR_dt(r_dt);
					vo.setI_user(i_user);
					vo.setM_dt(m_dt);

				}
				return 1;
			}

		});
		return vo;

	}
//////////////////////////////////

	public static int DelBoard(BoardVO param) {

		String sql = " DELETE FROM t_board5 WHERE i_board = ?";

		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());

			}
		});

	}

/////////////////////////////////////
	public static int updateBoard(BoardVO param) {


		String sql = " UPDATE t_board5 SET title=?, ctnt=?, m_dt=sysdate WHERE i_board=? ";

		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());

			}
		});

	}
	
	public static int addHits(int param) {
		
		String sql = " UPDATE t_board5 SET hits=hits+1 WHERE i_board=? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param);
				
			}
	});

}
}

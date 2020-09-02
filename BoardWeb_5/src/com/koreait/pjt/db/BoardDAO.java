package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.koreait.vo.BoardLikeVO;
import com.koreait.vo.BoardVO;
import javafx.css.PseudoClass;

public class BoardDAO {
	public static List<BoardVO> selBoardList(BoardVO param) {
		final List<BoardVO> list = new ArrayList();
		// 레퍼런스 상수는 주소값을 고정!! 객체에 추가를 막는것은 아니다.

		String sql =
			" SELECT A.* FROM  " + 
			" 	( SELECT ROWNUM as RNUM, A.* FROM " + 
			" 		( SELECT A.i_board, A.title, A.hits, A.i_user, D.nm, D.profile_img, nvl(B.cnt, 0) as cnt, " + 
			" 			 nvl(C.cmtCnt, 0) as cmtCnt, DECODE(E.i_board, null, 0, 1) as yn_like, " + 
			"			 TO_CHAR(A.r_dt, 'YYYY/MM/DD HH24:MI') as r_dt, " + 
			" 			 TO_CHAR(A.m_dt, 'YYYY/MM/DD') as m_dt FROM t_board5 A  " + 
			" 		     inner Join t_user D  " + 
			" 			 on A.i_user = D.i_user " + 
			" 			 LEFT JOIN( " + 
			" 			 	SELECT i_board, count(i_board) as cnt FROM t_board5_like group by i_board " + 
			" 				) B  " + 
			" 			 ON A.i_board = B.i_board " + 
			" 			 LEFT JOIN( " + 
			" 				SELECT i_board, count(i_board) as cmtCnt FROM t_board5_cmt group by i_board " + 
			" 				)C " + 
			" 			 ON A.i_board=C.i_board " + 
			" 			 LEFT JOIN( " + 
			" 				SELECT i_board FROM t_board5_like WHERE i_user= ? " + 
			" 				)E " + 
			" 			 ON A.i_board=E.I_board " + 
			" 			 WHERE " ;
		
			switch(param.getSearchType()) {
			case "a":
				sql+= " A.title like ? ";
				break;
			case "b":
				sql+= " A.ctnt like ? ";
				break;
			case "c":
				sql+= " (A.ctnt like ? or A.title like ?) ";
				break;
			}
		
			sql +=" 			 ORDER BY i_board DESC " + 
			" 		)A WHERE ROWNUM <= ? " + 
			" )A WHERE A.RNUM > ? ";
							
				
				
				
				
				
				
				
				
//				" SELECT A.*FROM "
//				+" ( SELECT ROWNUM as RNUM, A.*FROM "
//				+" ( SELECT A.i_board, A.title, A.hits, B.nm, B.profile_img, "
//				+" TO_CHAR(A.r_dt, 'YYYY/MM/DD HH24:MI') as r_dt, "
//				+" TO_CHAR(A.m_dt, 'YYYY/MM/DD') as m_dt FROM t_board5 A "
//				+" inner Join t_user B "
//				+" on A.i_user = B.i_user " 
//				+" WHERE A.title like ? " 
//			    +" ORDER BY i_board DESC "
//				+" )A WHERE ROWNUM <= ?"
//			    +" )A WHERE A.RNUM > ?" ;
				
				
			/*	" SELECT A.i_board, A.title, A.hits, B.nm, "
				+ "TO_CHAR(A.r_dt, 'YYYY/MM/DD HH24:MI') as r_dt, "
				+ "TO_CHAR(A.m_dt, 'YYYY/MM/DD') as m_dt FROM t_board5 A " 
				+ " inner Join t_user B"
				+ " on A.i_user = B.i_user "
				+ " ORDER BY i_board DESC ";*/

		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				int seq=1;
				ps.setInt(seq, param.getI_user());
				ps.setNString(++seq, param.getSearchText());
				if("c".equals(param.getSearchType())){
					ps.setNString(++seq, param.getSearchText());
				}	
				ps.setInt(++seq, param.getEidx());
				ps.setInt(++seq, param.getSidx());
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
					String profile_img=rs.getNString("profile_img");
					int yn_like=rs.getInt("yn_like");
					int cnt =rs.getInt("cnt");
					int cntCmt=rs.getInt("cmtCnt");
					int i_user=rs.getInt("i_user");
					
					
				

					BoardVO vo = new BoardVO();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setNm(nm);
					vo.setR_dt(r_dt);
					vo.setM_dt(m_dt);
					vo.setProfile_img(profile_img);
					vo.setCnt(cnt);
					vo.setCntCmt(cntCmt);
					vo.setYn_like(yn_like);
					vo.setI_user(i_user);
				

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

		String sql = " SELECT A.i_board, A.title, A.ctnt, A.hits, B.nm, B.profile_img," 
		        + " A.r_dt, A.m_dt, A.hits," 
		        + " A.i_user, DECODE(C.i_user, null, 0, 1)as yn_like,"
		        + " (SELECT COUNT(*) FROM t_board5_like WHERE i_board= ?) as count,"
		        + " (SELECT COUNT(*) FROM t_board5_cmt WHERE i_board= ?) as cmtCount "
		        + " FROM t_board5 A "
				+ " inner Join t_user B" 
		        + " on A.i_user = B.i_user "
		        + " LEFT JOIN t_board5_like C"
		        + " ON A.i_board=C.i_board"
		        + " AND C.i_user= ?"
				+ " WHERE A.i_board= ? ";  

		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_board());
				ps.setInt(3, param.getI_user());
				ps.setInt(4, param.getI_board());
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
					int i_user = rs.getInt("i_user"); //작성자 
					int yn_like=rs.getInt("yn_like");
					int count = rs.getInt("count");
					int cmtCount=rs.getInt("cmtCount");
					String profile_img=rs.getNString("profile_img");
					
					

					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setNm(nm);
					vo.setCtnt(ctnt);
					vo.setR_dt(r_dt);
					vo.setI_user(i_user);
					vo.setM_dt(m_dt);
					vo.setYn_like(yn_like);
					vo.setCount(count);
					vo.setCmtCount(cmtCount);
					vo.setProfile_img(profile_img);
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
	
	////////////////////////////////
	
	public static int addHits(int param) {
		
		String sql = " UPDATE t_board5 SET hits=hits+1 WHERE i_board=? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param);
				
			}
	});

}
	
	////////////////////////////////////
	
	public static int insLike(BoardVO param) {

		String sql=" INSERT INTO t_board5_like ( "
			  + " i_user, i_board "
			  + " ) "
			  +	" VALUES( ?, ?) ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			
    		}
		});
		
	}
		
	
		public static int dleLike(BoardVO param) {
		String sql=" DELETE FROM t_board5_like WHERE i_board=? ";
			return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

				@Override
				public void update(PreparedStatement ps) throws SQLException {
			
					ps.setInt(1, param.getI_board());
				

				}
			});
		
	}
	

	
	
	
////////////////////////////////////////////////////////////////////////////////
//페이징

	public static int selPagingCnt(BoardVO param) {
		
		String sql=" SELECT ceil(count(i_board)/ ?) FROM t_board5  WHERE ";
		
		switch(param.getSearchType()) {
		case "a":
			sql+= " title like ? ";
			break;
		case "b":
			sql+= " ctnt like ? ";
			break;
		case "c":
			sql+= " (ctnt like ? or title like ?) ";
			break;
		}
	
	
		
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getRecord_cnt());
				ps.setNString(2, param.getSearchText());
				if("c".equals(param.getSearchType())) {
					ps.setNString(3, param.getSearchText());
				}
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				
				if(rs.next()) {
					return rs.getInt(1); //스칼라값: 행도 열도 1개인 값! 숫자로 있다면 인덱스로 ""이라면 컬럼명
				}
				
				
				return 0;
			}
		});
		
	}
	
	
	
}


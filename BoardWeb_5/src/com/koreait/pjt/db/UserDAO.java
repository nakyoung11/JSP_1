package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.koreait.vo.UserVO;

public class UserDAO {
	/////// insert
	public static int insUser(UserVO param) {

		String sql = " INSERT INTO t_user " + " (i_user, user_id, user_pw, nm, email) " + " VALUES "
				+ " (seq_user.nextval,?,?,?,?) ";

		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override // 익명클래스(인터페이스를 상속받은 클래스를 간략화해서 객체화 한것)
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getNm());
				ps.setNString(4, param.getEmail());

			}

		});
	}

	// select
	public static int login(UserVO param) {

		String sql = " SELECT i_user, user_pw, nm FROM t_user WHERE user_id=?";

		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override // 익명클래스(인터페이스를 상속받은 클래스를 간략화해서 객체화 한것)
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());

			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if (rs.next()) {
					String dbpw = rs.getNString("user_pw");
					if (dbpw.equals(param.getUser_pw())) { // 로그인 성공
						int i_user = rs.getInt("i_user");
						String nm = rs.getNString("nm");
						// param에 담기~
						param.setUser_pw(null);
						param.setI_user(i_user);
						param.setNm(nm);
						return 1;
					} else {
						return 2; // 비밀번호 틀림
					}
				} else { // 아이디 없음
					return 3;
				}

			}

		});
	}
//	

}

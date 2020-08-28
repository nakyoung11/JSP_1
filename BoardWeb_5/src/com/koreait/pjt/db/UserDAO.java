package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.koreait.vo.UserLoginHistoryVO;
import com.koreait.vo.UserVO;

import oracle.net.aso.e;

public class UserDAO {

	public static int insUserLoginHistory(UserLoginHistoryVO param) {
		
	String sql="  INSERT INTO t_visit_loginhistory "+ 
			" (i_history, i_user, ip_addr, os, browser) " + 
			" VALUES (seq_userloghistory.nextval, ? , ?, ? , ?) ";
	return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
		public void update(PreparedStatement ps) throws SQLException {
			ps.setInt(1, param.getI_user());
			ps.setNString(2, param.getIp_addr());
			ps.setNString(3, param.getOs());
			ps.setNString(4, param.getBrowser());
		}
	});
		
		
	}
	
		
	/////// insert
	public static int insUser(UserVO param) {

		String sql = " INSERT INTO t_user " 
		        + " (i_user, user_id, user_pw, nm, email) "
				+ " VALUES "
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
//프로필
	public static UserVO selUser(final int i_user) {
		String sql=" SELECT user_id, nm, profile_img, email, r_dt "
				+" FROM t_user WHERE i_user =? ";
		
		UserVO result =new UserVO();
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_user);
				
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
			
				if(rs.next()) {
					result.setUser_id(rs.getNString("user_id"));
					result.setNm(rs.getNString("nm"));
					result.setProfile_img(rs.getNString("profile_img"));
					result.setR_dt(rs.getNString("r_dt"));
					result.setEmail(rs.getNString("email"));
					
				}
				return 1;
				
			}
		});
		return result;
		
	}

}

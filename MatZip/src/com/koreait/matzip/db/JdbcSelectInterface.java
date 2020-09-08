package com.koreait.matzip.db;

import java.sql.*;

public interface JdbcSelectInterface {
	void prepared(PreparedStatement ps) throws SQLException;
	void executeQuery(ResultSet rs) throws SQLException;

}

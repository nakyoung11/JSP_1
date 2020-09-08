package com.koreait.matzip.db;

import java.sql.*;

//인터페이스는 부모역할만 가능, 가르킬수 있는것은 자식! 
//인터페이스는 객체화가 안됨 ~ 추상클래스(추상메소드를 가짐)도 마찬가지!
public interface JdbcUpdateInterface {
	public abstract void update(PreparedStatement ps) throws SQLException;
	// 추상메소드  선언
}

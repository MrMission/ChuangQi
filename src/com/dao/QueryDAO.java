package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class QueryDAO extends BaseDAO {

	public int isExist(String idfa) { //
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("select * from yunzhijia where idfa = ?");

			pstmt.setString(1, idfa);
			rst = pstmt.executeQuery();

			if (rst.next()) {
				return 1;
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}

		return 0;
	}
	
	public void record(String idfa, int query) { //
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("insert into youmi_yzj values(null, '595672427',?,?,null)");

			pstmt.setString(1, idfa);
			pstmt.setInt(2, query);
			pstmt.executeUpdate();

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		
	}

}

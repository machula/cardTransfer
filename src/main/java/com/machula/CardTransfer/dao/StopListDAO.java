package com.machula.CardTransfer.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.machula.CardTransfer.database.DataBaseUtil;

public class StopListDAO {
	private DataBaseUtil dbUtil;
	Connection connection;
	Statement statement;

	public StopListDAO() {
		dbUtil = new DataBaseUtil();
		try {
			connection = dbUtil.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean findByName(String name) {
		try {
			String query = "select * from stop_list "
					+ "left join credit_card cc on stop_list.credit_card = cc.id where card_number = ?";
			PreparedStatement s = connection.prepareStatement(query);
			s.setString(1, name);
			if (!s.executeQuery().isBeforeFirst()) // not find in stop list
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void closeConnection() throws SQLException {
		this.connection.close();
		this.statement.close();
	}
}

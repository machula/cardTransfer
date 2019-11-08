package com.machula.CardTransfer.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.machula.CardTransfer.database.DataBaseUtil;
import com.machula.CardTransfer.entity.CreditCard;

public class CreditCardDAO {
	private DataBaseUtil dbUtil;
	private Connection connection;
	private Statement statement;

	public CreditCardDAO() {
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

	public List<CreditCard> findAll() {
		return null;
	}

	public CreditCard findByName(String name) {
		try {
			String query = "select cr.* from credit_card cr where cr.card_number = ?";
			PreparedStatement s = connection.prepareStatement(query);
			s.setString(1, name);
			ResultSet resultSet = s.executeQuery();

			while (resultSet.next()) {
				return new CreditCard(resultSet.getInt("id"), resultSet.getString("card_number"),
						resultSet.getString("exp_date"), resultSet.getInt("cvc2"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection() throws SQLException {
		this.connection.close();
		this.statement.close();
	}
}

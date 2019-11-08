package com.machula.CardTransfer.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.machula.CardTransfer.database.DataBaseUtil;
import com.machula.CardTransfer.entity.Transaction;

public class TransactionDAO {

	private DataBaseUtil dbUtil;
	private Connection connection;
	private Statement statement;

	public TransactionDAO() {
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

	public void save(Transaction transaction) {
		try {
			String query = "insert into transactions(sender_card, recipient_card, amount) values(?,?,?)";
			PreparedStatement s = connection.prepareStatement(query);
			s.setInt(1, transaction.getCreditCard().getId());
			s.setString(2, transaction.getResipientCard());
			s.setInt(3, transaction.getAmount());

			int result = s.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnection() throws SQLException {
		this.connection.close();
		this.statement.close();
	}

}

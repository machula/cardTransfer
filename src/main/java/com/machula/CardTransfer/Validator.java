package com.machula.CardTransfer;

import java.sql.SQLException;

import com.machula.CardTransfer.dao.CreditCardDAO;
import com.machula.CardTransfer.dao.StopListDAO;
import com.machula.CardTransfer.entity.CreditCard;

public class Validator {
	private StopListDAO stopListDAO;
	private CreditCardDAO creditCardDAO;

	public Validator() {
		this.stopListDAO = new StopListDAO();
		this.creditCardDAO = new CreditCardDAO();
	}

	public Result validate(CreditCard creditCard, int amount) {

		CreditCard creditCardCheck = creditCardDAO.findByName(creditCard.getCardNumber());

		// TODO need override equals()
		if (creditCardCheck == null || !creditCard.getExpDate().equals(creditCardCheck.getExpDate())
				|| creditCard.getCvc2() != creditCardCheck.getCvc2())

			return new Result(3, "Card data is incorrect", null);

		if (stopListDAO.findByName(creditCard.getCardNumber()))
			return new Result(1, "The card is in the stop list", null);

		if (amount > 1000)
			return new Result(2, "Amount > 1000", null);

		creditCard.setId(creditCardCheck.getId());
		
		try {
			stopListDAO.closeConnection();
			creditCardDAO.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Result(0, "OK", null);
	}

}

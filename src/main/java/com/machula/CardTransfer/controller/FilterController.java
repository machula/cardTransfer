package com.machula.CardTransfer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.machula.CardTransfer.PdfGenerator;
import com.machula.CardTransfer.Result;

import com.machula.CardTransfer.Validator;
import com.machula.CardTransfer.dao.TransactionDAO;
import com.machula.CardTransfer.entity.CreditCard;
import com.machula.CardTransfer.entity.Transaction;

@RestController
public class FilterController {

	private CreditCard creditCard;
	private Transaction transaction;
	private Result result;

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public Result filter(HttpServletResponse response, @RequestBody Map<String, String> params) throws IOException {

		creditCard = new CreditCard(params.get("senderCard"), params.get("expDate"),
				Integer.parseInt(params.get("cvc2")));

		int amount = Integer.parseInt(params.get("amount"));

		Validator validator = new Validator();
		result = validator.validate(creditCard, amount);

		if (result.getStatus() != 0)
			return result;
		else {
			transaction = new Transaction(creditCard, amount, params.get("resipientCard"));
			TransactionDAO transactionDAO = new TransactionDAO();
			transactionDAO.save(transaction);
			try {
				transactionDAO.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("/receipt");
		}
		return null;
	}

	@RequestMapping(value = "/receipt", method = RequestMethod.GET)
	public Result receipt() {
		result.setData(transaction);
		return result;
	}

	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	public void getPdf(HttpServletResponse response) throws IOException {
		PdfGenerator pdfGenerator = new PdfGenerator(this.transaction);
		try {
			pdfGenerator.createTemplate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream inputStream = new FileInputStream(new File(pdfGenerator.getFilename()));
		IOUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
	}

}

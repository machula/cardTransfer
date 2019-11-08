package com.machula.CardTransfer;

import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Stream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.machula.CardTransfer.entity.Transaction;

public class PdfGenerator {
	static int FONT_SIZE_SMALL = 16;
	static int FONT_SIZE_BIG = 32;
	static int OFFSET = 40;

	private String filename;
	private Transaction transaction;

	public PdfGenerator(Transaction transaction) {
		this.transaction = transaction;

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
		String formattedDate = myDateObj.format(myFormatObj);
		filename = "files//" + transaction.getCreditCard().getCardNumber() + ' ' + formattedDate + ".pdf";
	}

	public void createTemplate() throws Exception {
		Document document = new Document();

		Font font1 = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_BIG, Font.BOLD);
		Font font2 = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_SMALL, Font.ITALIC | Font.UNDERLINE);

		PdfWriter.getInstance(document, new FileOutputStream(filename));

		document.open();

		// отцентрированный параграф
		Paragraph title = new Paragraph("Receipt", font1);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(FONT_SIZE_BIG);
		document.add(title);

		PdfPTable table = new PdfPTable(3);
		Stream.of("Amount", "Fee", "Recipient Card").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(1);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
		table.addCell(Integer.toString(transaction.getAmount()));
		table.addCell(Integer.toString(transaction.getFee()));
		table.addCell(transaction.getResipientCard());
		document.add(table);
		document.close();
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}

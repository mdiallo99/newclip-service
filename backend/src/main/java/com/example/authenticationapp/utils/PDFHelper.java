package com.example.authenticationapp.utils;


import com.example.authenticationapp.model.sylobe.Article;
import com.example.authenticationapp.model.sylobe.Voucher;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PDFHelper {

    private static final Logger logger = LoggerFactory.getLogger(PDFHelper.class);
    private Voucher voucher;
    private final   Document document = new Document(PageSize.A4);

//    @Autowired
//    private static JavaMailSender mailSender;

    public PDFHelper(Voucher voucher) {
        this.voucher = voucher;
    }
//
//    public Document getDocument() {
//        return document;
//    }
//
//
//    public void sendEmail(){
//        String from = "mdiallopro14@gmail.com";
//        String to = "dialloakh@gmail.com";
//
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject("This is a plain text email");
//        message.setText("Hello guys! This is a plain text email.");
//
////        mailSender.send(message);
//    }
//
//    public static ByteArrayInputStream voucherReport(Voucher voucher) throws DocumentException {
//        Document document = new Document();
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        PdfPTable table = new PdfPTable(3);
//        table.setWidthPercentage(60);
//        table.setWidths(new int[]{3, 3, 3});
//
//        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//
//        PdfPCell hcell;
//        hcell = new PdfPCell(new Phrase("Name", headFont));
//        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(hcell);
//
//        hcell = new PdfPCell(new Phrase("Label", headFont));
//        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(hcell);
//
//        hcell = new PdfPCell(new Phrase("SocialReason", headFont));
//        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(hcell);
//
//        for(Article article: voucher.getArticles()){
//            PdfPCell cell;
//
//            cell = new PdfPCell(new Phrase(article.getName()));
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase(article.getLabel()));
//            cell.setPaddingLeft(5);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase(String.valueOf(article.getSocialReason())));
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell.setPaddingRight(5);
//            table.addCell(cell);
//        }
//
//        PdfWriter.getInstance(document, out);
//        document.open();
//        document.add(table);
//
//        document.close();
//
//        return new ByteArrayInputStream(out.toByteArray());
//    }
//
//    private void writeTableHeader(PdfPTable table) {
//        PdfPCell cell = new PdfPCell();
//        cell.setBackgroundColor(BaseColor.BLUE);
//        cell.setPadding(5);
//
//        Font font = FontFactory.getFont(FontFactory.HELVETICA);
//        font.setColor(BaseColor.WHITE);
//
//        cell.setPhrase(new Phrase("Référence", font));
//
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Numéro de lot", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Quantité", font));
//        table.addCell(cell);
//    }
//
//    private void writeTableData(PdfPTable table) {
//        for (Article article : voucher.getArticles()) {
//            table.addCell(article.getName());
//            table.addCell(article.getNumber());
//            table.addCell(String.valueOf(article.getQuantity()));
//        }
//    }
//
//    public Document export(HttpServletResponse response) throws DocumentException, IOException {
//
//        PdfWriter.getInstance(document, response.getOutputStream());
//
//        document.open();
//        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//        font.setSize(18);
//        font.setColor(BaseColor.BLUE);
//
//        Paragraph p = new Paragraph("Bon de régularisation", font);
//        p.setAlignment(Paragraph.ALIGN_CENTER);
//
//        document.add(p);
//
//        PdfPTable table = new PdfPTable(3);
//        table.setWidthPercentage(100f);
//        table.setWidths(new float[] {4f, 4f, 4f});
//        table.setSpacingBefore(10);
//
//        writeTableHeader(table);
//        writeTableData(table);
//
//        document.add(table);
//
//        document.close();
//
//        return document;
//    }


    public void email() {
        String smtpHost = "gmail.com"; //replace this with a valid host
        int smtpPort = 587; //replace this with a valid port

        String sender = "mdiallopro14@gmail.com";
        String recipient = "dialloakh@gmail.com";
        String content = "dummy content";
        String subject = "dummy subject";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        Session session = Session.getDefaultInstance(properties, null);

        ByteArrayOutputStream outputStream = null;

        try {
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);

            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream);
            byte[] bytes = outputStream.toByteArray();

            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf");

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(recipient);

            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);

            //send off the email
            Transport.send(mimeMessage);

            System.out.println("sent from " + sender +
                    ", to " + recipient +
                    "; server = " + smtpHost + ", port = " + smtpPort);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            //clean off
            if(null != outputStream) {
                try { outputStream.close(); outputStream = null; }
                catch(Exception ex) { }
            }
        }
    }
    public void writePdf(OutputStream outputStream) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("hello!"));
        document.add(paragraph);
        document.close();
    }
}

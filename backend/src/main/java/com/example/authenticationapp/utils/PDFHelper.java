package com.example.authenticationapp.utils;


import com.example.authenticationapp.model.sylobe.Article;
import com.example.authenticationapp.model.sylobe.Voucher;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFHelper {

    /**
     * This class create a pdf file for a Voucher
     */
    private static final Logger logger = LoggerFactory.getLogger(PDFHelper.class);
    private Voucher voucher;
    private final   Document document = new Document(PageSize.A4);


    public PDFHelper(Voucher voucher) {
        this.voucher = voucher;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStream stream = new FileOutputStream("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\voucher.pdf");
        PdfWriter.getInstance(document, stream);

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(20);
        font.setColor(BaseColor.RED);

        Image logo = Image.getInstance("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\nct.PNG");
        logo.setAlignment(3);

        Image range = Image.getInstance("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\gamme.png");
        range.setAlignment(3);


        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        Paragraph date = new Paragraph("DATE : "+currentDateTime+"\n");
        date.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph company = new Paragraph("ETABLISSEMENT : "+voucher.getEditor().getCompany().getSocialReason()+"\n");
        company.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph editor = new Paragraph("Chirurgien: "+voucher.getEditor().getFirstName()+" "+voucher.getEditor().getLastName()+"\n \n");
        editor.setAlignment(Paragraph.ALIGN_CENTER);



        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 2.5f, 0.5f, 0.5f});
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);


        writeTableHeader(table);
        writeTableData(table);


        document.add(logo);
        document.add(range);
        document.add(date);
        document.add(company);
        document.add(editor);

        document.add(table);

        document.close();

    }

    private Article writeTableData(PdfPTable table) throws BadElementException, IOException {

        Article a = null;
        for (Article article : voucher.getArticles()) {

            Image image = null;
            try {
                image = Image.getInstance("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\webapp\\src\\images\\"+article.getArticleCode()+".png");
            }catch (Exception e){
                image = Image.getInstance("C:\\Users\\mdiallo\\Desktop\\regularization-form-bis\\backend\\src\\main\\java\\com\\example\\authenticationapp\\proxyWS\\data\\nct.PNG");
            }
            a = article;
            table.addCell(image);
            table.addCell(article.getArticleCode()+" \n"+article.getName());
            table.addCell(article.getNumber());
            table.addCell(String.valueOf(article.getQuantity()));
        }
        return  a;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.RED);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        cell.setPhrase(new Phrase("Plaques", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Référence", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("N° lot", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Qté", font));
        table.addCell(cell);
    }

}

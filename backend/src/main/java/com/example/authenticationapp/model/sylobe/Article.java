package com.example.authenticationapp.model.sylobe;

import lombok.Data;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Data
@Document(collection = "article")
/**
 * This class represents an article from Newclip ERP
 */
public class Article {

    private String articleCode;
    private String number;
    private String index;
    private double quantity;
    private String validityDate;
    private String label;
    private String articleStatus;
    private String codeClient;
    private String socialReason;
    private String name;

    /**
     * Constructor
     * @param articleCode Article code
     * @param number Article number
     * @param index Article index
     * @param quantity The available quantity
     * @param validityDate Validity date
     * @param label the label
     * @param articleStatus article status
     * @param codeClient the client code
     * @param socialReason the company social reason (name)
     */
    public Article(String articleCode, String number, String index, double quantity, String validityDate, String label, String articleStatus, String codeClient, String socialReason, String name) throws IOException {
        this.articleCode = articleCode;
        this.number = number;
        this.index = index;
        this.quantity = quantity;
        this.validityDate = validityDate;
        this.label = label;
        this.articleStatus = articleStatus;
        this.codeClient = codeClient;
        this.socialReason = socialReason;
        this.name = name;
//        this.loadPhoto();
    }

    public Article(){

    }

    public String getArticleCode() {
        return articleCode;
    }

    public String getNumber() {
        return number;
    }

    public String getIndex() {
        return index;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public String getLabel() {
        return label;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public String getName() {
        return name;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Artcle : {\n"+
                "Code: "+this.getArticleCode()+"\n"+
                "Number: "+this.getNumber()+"\n"+
                "Index: "+this.getIndex()+"\n"+
                "Quantity: "+this.getQuantity()+"\n"+
                "Validity date: "+this.getValidityDate()+"\n"+
                "Label: "+this.getLabel()+"\n"+
                "Company Status: "+this.getArticleStatus()+"\n"+
                "Client Code: "+this.getCodeClient()+"\n"+
                "Social Reason: "+this.getSocialReason()+"\n"+
                "Name: "+this.getName()+"\n"+
                "}";
    }

    /**
     * Compare two articles
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Article article = (Article) obj;

        return this.getArticleCode().equals(article.getArticleCode()) &&
                this.getNumber().equals(article.getNumber()) &&
                this.getIndex().equals(article.getIndex()) &&
                this.getQuantity() == article.getQuantity() &&
                this.getValidityDate().equals(article.getValidityDate()) &&
                this.getLabel().equals(article.getLabel()) &&
                this.getArticleStatus().equals(article.getArticleStatus()) &&
                this.getCodeClient().equals(article.getCodeClient()) &&
                this.getSocialReason().equals(article.getSocialReason());
    }

}

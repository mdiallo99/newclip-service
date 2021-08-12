package com.example.authenticationapp.model.sylobe;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "kit")
/**
 * This class represents a kit from Newclip ERP
 */
public class Kit {

    private String label;
    private String kitStatus;
    private String clientCode;
    private String socialReason;
    private Set<Article> articles;

    /**
     * Constructor
     * @param label kit label
     * @param kitStatus kit status
     * @param clientCode client code
     * @param socialReason social reason
     * Every kit contains 0 or n articles
     */
    public Kit(String label, String kitStatus, String clientCode, String socialReason) {
        this.label = label;
        this.kitStatus = kitStatus;
        this.clientCode = clientCode;
        this.socialReason = socialReason;
        this.articles = new HashSet<>();
    }

    public String getLabel() {
        return label;
    }

    public String getKitStatus() {
        return kitStatus;
    }

    public String getClientCode() {
        return clientCode;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setKitStatus(String kitStatus) {
        this.kitStatus = kitStatus;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public void addArticle(Article article){
        this.articles.add(article);
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString(){
        return "Kit : {\n"+
                "Label: "+this.getLabel()+"\n"+
                "Status: "+this.getKitStatus()+"\n"+
                "Client code: "+this.getClientCode()+"\n"+
                "Social reason: "+this.getSocialReason()+"\n"+
                "Content: "+this.getArticles()+"\n"+
                "}";
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Kit kit = (Kit) obj;

        return this.getLabel().equals(kit.getLabel()) &&
                this.getKitStatus().equals(kit.getKitStatus()) &&
                this.getClientCode().equals(kit.getClientCode()) &&
                this.getSocialReason().equals(kit.getSocialReason()) &&
                this.getArticles() == kit.getArticles();
    }
}

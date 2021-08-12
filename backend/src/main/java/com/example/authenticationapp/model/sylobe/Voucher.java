package com.example.authenticationapp.model.sylobe;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Data
@Document(collection = "voucher")
/**
 * This class represents a regularization voucher
 */
public class Voucher {

    private final UUID id;
    private String description;
    private Set<Article> articles;


    /**
     * Constructor
     * @param description Voucher description
     * Each Voucher contains a several articles
     */
    public Voucher(String description) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.articles = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void addArticle(Article article){
        this.articles.add(article);
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString(){
        return "Voucher: {\n"+
                "Id: "+this.getId()+"\n"+
                "Description: "+this.getDescription()+"\n"+
                "Articles: "+this.getArticles()+"\n"+
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

        Voucher voucher = (Voucher) obj;

        return  this.getId() == voucher.getId() &&
                this.getDescription().equals(voucher.getDescription()) &&
                this.getArticles() == voucher.getArticles();
    }
}


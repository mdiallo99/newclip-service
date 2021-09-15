package com.example.authenticationapp.model.sylobe;

import com.example.authenticationapp.model.user.User;
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
    private Set<Article> articles;
    private User editor;


    /**
     * Constructor
     * Each Voucher contains a several articles
     */
    public Voucher() {
        this.id = UUID.randomUUID();
        this.articles = new HashSet<>();
        editor = null;
    }

    public UUID getId() {
        return id;
    }


    public Set<Article> getArticles() {
        return articles;
    }

    public User getEditor() {
        return editor;
    }

    public void addArticle(Article article){
        this.articles.add(article);
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    @Override
    public String toString(){
        return "Voucher: {\n"+
                "Id: "+this.getId()+"\n"+
                "Articles: "+this.getArticles()+"\n"+
                "Editor: "+this.getEditor()+"\n"+
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
                this.getArticles() == voucher.getArticles() &&
                this.getEditor().equals(voucher.getEditor())
                ;
    }
}


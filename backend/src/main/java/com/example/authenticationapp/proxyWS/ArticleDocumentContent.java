package com.example.authenticationapp.proxyWS;

import com.example.authenticationapp.model.sylobe.Article;

import java.util.HashSet;
import java.util.Set;

public class ArticleDocumentContent {

    private Set<Article> articles;

    public ArticleDocumentContent() {
        this.articles = new HashSet<>();
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article article){
        this.articles.add(article);
    }
}

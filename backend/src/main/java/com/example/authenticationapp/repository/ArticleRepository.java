package com.example.authenticationapp.repository;

import com.example.authenticationapp.model.sylobe.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    @Query("{'articleCode': ?0}")
    void deleteArticleByArticleCode(String articleCode);
}

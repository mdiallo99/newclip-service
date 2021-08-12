package com.example.authenticationapp.model.sylobe;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Data
@Document(collection = "photos")
public class Photo {

    @Id
    private String id;
    private String name;
    private Binary image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Binary getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(Binary image) {
        this.image = image;
    }
}

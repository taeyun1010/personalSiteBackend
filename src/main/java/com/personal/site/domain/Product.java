package com.personal.site.domain;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Product {
    @Id
    private String id;
    @Indexed(unique=true)
    private String name;
    private String username;
    private Long price;
    private String description;
//    @JsonIgnore
    private Binary image;

    public Product(String name, String username, Long price, String description) {
        this.name = name;
        this.username = username;
        this.price = price;
        this.description = description;
    }

//    @JsonIgnore
//    public Binary getImage() {
//        return image;
//    }
}

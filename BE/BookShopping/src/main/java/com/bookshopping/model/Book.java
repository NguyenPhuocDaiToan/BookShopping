package com.bookshopping.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String avatar;
    @Column(length = 10000)
    private String description;
    private String language;
    private int page;
    private String author;
    private String coverForm;
    private String publisher;
    private String yearPublisher;
    private String supplier;
    private int weight;
    private String packagingSize;
    private int amount;
    private int pointReward;
    private int price;
    private int numberRating;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<OrderItem> orderItems;
}

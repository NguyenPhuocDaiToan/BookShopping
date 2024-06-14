package com.bookshopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    private User userId;
    @ManyToOne(targetEntity = Book.class)
    private Book bookId;

    private boolean isUserRating = false;

    private float ratingRecommendation;
    private String comment = "";
}

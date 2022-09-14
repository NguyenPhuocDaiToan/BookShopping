package com.bookshopping.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int amount;

    @ManyToOne(targetEntity = Cart.class)
    @JsonBackReference("cart_cart_item")
    private Cart cart;

    @ManyToOne(targetEntity = Book.class)
    private Book book;
}

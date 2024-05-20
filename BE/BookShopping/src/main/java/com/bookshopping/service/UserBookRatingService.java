package com.bookshopping.service;

import com.bookshopping.model.UserBookRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserBookRatingService {
    boolean existsByUserIdAndBookId(int userId, int bookId);

//    Optional<UserBookRating> findByUserIdAndBookId(int userId, int bookId);

    UserBookRating addRating(int userId, int bookId, int ratingRecommendation);

    UserBookRating updateRating(int userId, int bookId, int newRatingRecommendation);

    Page<UserBookRating> paginate(Pageable page);
}

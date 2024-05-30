package com.bookshopping.service;

import com.bookshopping.model.UserBookRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserBookRatingService {
    UserBookRating findByUserIdAndBookId(int userId, int bookId);

//    Optional<UserBookRating> findByUserIdAndBookId(int userId, int bookId);

    UserBookRating addRating(int userId, int bookId, float ratingRecommendation);

    UserBookRating updateRating(int userId, int bookId, float newRatingRecommendation);

    Page<UserBookRating> paginate(Pageable page);
}

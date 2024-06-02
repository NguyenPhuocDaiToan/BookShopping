package com.bookshopping.repository;

import com.bookshopping.model.UserBookRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserBookRatingRepository  extends JpaRepository<UserBookRating, Integer> {

    boolean existsByUserIdAndBookId(int userId, int bookId);

    Optional<UserBookRating> findByUserIdAndBookId(int userId, int bookId);

    Page<UserBookRating> findAll(Pageable page);

    @Query(value = "SELECT book_id FROM user_book_rating WHERE user_id = :userId and rating_recommendation >= 3  ORDER BY id DESC LIMIT :limit", nativeQuery = true)
    List<Integer> getBooksCare(int userId, int limit);
}

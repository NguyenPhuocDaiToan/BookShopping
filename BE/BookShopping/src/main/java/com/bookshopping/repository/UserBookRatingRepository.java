package com.bookshopping.repository;

import com.bookshopping.model.UserBookRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBookRatingRepository  extends JpaRepository<UserBookRating, Integer> {

    boolean existsByUserIdAndBookId(int userId, int bookId);

    Optional<UserBookRating> findByUserIdAndBookId(int userId, int bookId);

    Page<UserBookRating> findAll(Pageable page);
}

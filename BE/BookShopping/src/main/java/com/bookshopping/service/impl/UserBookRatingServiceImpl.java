package com.bookshopping.service.impl;

import com.bookshopping.model.UserBookRating;
import com.bookshopping.repository.UserBookRatingRepository;
import com.bookshopping.service.UserBookRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBookRatingServiceImpl implements UserBookRatingService {
    @Autowired
    UserBookRatingRepository userBookRatingRepository;

    @Override
    public boolean existsByUserIdAndBookId(int userId, int bookId) {
        return userBookRatingRepository.existsByUserIdAndBookId(userId, bookId);
    }

    @Override
    public UserBookRating addRating(int userId, int bookId, int ratingRecommendation) {
        UserBookRating newRating = new UserBookRating();
        newRating.setUserId(userId);
        newRating.setBookId(bookId);
        newRating.setRatingRecommendation(ratingRecommendation);

        return userBookRatingRepository.save(newRating);
    }

    @Override
    public UserBookRating updateRating(int userId, int bookId, int newRatingRecommendation) {
        Optional<UserBookRating> optionalRating = userBookRatingRepository.findByUserIdAndBookId(userId, bookId);

        if (optionalRating.isPresent()) {
            UserBookRating existingRating = optionalRating.get();
            existingRating.setRatingRecommendation(newRatingRecommendation);

            return userBookRatingRepository.save(existingRating);
        } else {
            throw new IllegalArgumentException("Rating does not exist for this user and book.");
        }
    }

    @Override
    public Page<UserBookRating> paginate(Pageable page) {
        return userBookRatingRepository.findAll(page);
    }
}

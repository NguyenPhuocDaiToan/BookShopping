package com.bookshopping.controller;

import com.bookshopping.model.UserBookRating;
import com.bookshopping.payload.request.UserBookRatingRequest;
import com.bookshopping.payload.response.ResponseMessage;
import com.bookshopping.service.UserBookRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating-recommendation")
public class UserBookRatingController {

    @Autowired
    UserBookRatingService userBookRatingService;

    @PostMapping("/modify")
    public ResponseEntity<ResponseMessage> addRatingRecommendation(@RequestBody UserBookRatingRequest data) {

        List<Integer> bookIds = data.getBookIds();
        List<Float> ratingRecommendations = data.getRatingRecommendations();
        int userId = data.getUserId();

        for (int i = 0; i < bookIds.size(); i++) {
            UserBookRating oldRating = userBookRatingService.findByUserIdAndBookId(userId, bookIds.get(i));

            // if exist rating
            if (oldRating != null) {
                // if user rated => ignore
                if (!oldRating.isUserRating()) {
                    userBookRatingService.updateRating(userId, bookIds.get(i), ratingRecommendations.get(i));
                }
            } else {
                userBookRatingService.addRating(userId, bookIds.get(i), ratingRecommendations.get(i));
            }
        }

        return new ResponseEntity<>(new ResponseMessage("OK"), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<UserBookRating>> paginate(@PageableDefault(size = 48) Pageable pageable) {
        return new ResponseEntity<>(userBookRatingService.paginate(pageable), HttpStatus.OK);
    }

    @GetMapping("/getBooksCare")
    public ResponseEntity<List<Integer>> getBooksCare(@RequestParam Integer userId, @RequestParam(defaultValue = "50") Integer limit) {
        return new ResponseEntity<>(userBookRatingService.getBooksCare(userId, limit), HttpStatus.OK);
    }
}

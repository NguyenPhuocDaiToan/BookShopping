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

@RestController
@RequestMapping("/api/rating-recommendation")
public class UserBookRatingController {

    @Autowired
    UserBookRatingService userBookRatingService;

    @PostMapping("/modify")
    public ResponseEntity<ResponseMessage> addRatingRecommendation(@RequestBody UserBookRatingRequest data) {
        boolean isExist = userBookRatingService.existsByUserIdAndBookId(data.getUserId(), data.getBookId());

        if (isExist) {
            userBookRatingService.updateRating(data.getUserId(), data.getBookId(), data.getRatingRecommendation());
        } else {
            userBookRatingService.addRating(data.getUserId(), data.getBookId(), data.getRatingRecommendation());
        }

        return new ResponseEntity<>(new ResponseMessage("OK"), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<UserBookRating>> paginate(@PageableDefault(size = 48) Pageable pageable) {
        return new ResponseEntity<>(userBookRatingService.paginate(pageable), HttpStatus.OK);
    }
}

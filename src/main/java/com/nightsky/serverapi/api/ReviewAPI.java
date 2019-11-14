package com.nightsky.serverapi.api;

import com.nightsky.serverapi.domain.Review;
import com.nightsky.serverapi.repository.ReviewRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("dbReview")
public class ReviewAPI {

    @Autowired
    private ReviewRepo reviewRepo;

    @GetMapping
    public List<Review> getAllReviews(){
        return reviewRepo.findAll();
    }

    @GetMapping("{id}")
    public Review getReviewById(@PathVariable("id") Review review){
        return review;
    }

    @PostMapping
    public Review createReview(@RequestBody Review review){
        review.setLocalDate(LocalDate.now());
        return reviewRepo.save(review);
    }

    @PutMapping("{id}")
    public Review modifyReview(@PathVariable("id") Review reviewFromDb, @RequestBody Review review){
        BeanUtils.copyProperties(review, reviewFromDb, "id");
        return reviewRepo.save(reviewFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteReview(@PathVariable("id") Review review){
        reviewRepo.delete(review);
    }
}

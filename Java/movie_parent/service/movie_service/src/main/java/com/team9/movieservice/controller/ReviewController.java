package com.team9.movieservice.controller;


import com.team9.commonutils.R;
import com.team9.movieservice.entity.Review;
import com.team9.movieservice.entity.frontVo.ReviewShowVo;
import com.team9.movieservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PastOrPresent;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
@RestController
@CrossOrigin
@RequestMapping("/movieservice/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("GetReviewByMovieId/{mid}")
    public R GetReviewByMovieId(@PathVariable Integer mid)
    {
        List<ReviewShowVo> reviews = reviewService.GetReviewByMovieId(mid);
        return R.ok().data("reviews",reviews);
    }

    @PostMapping("addReview")
    public R addReview(@RequestBody Review review){

        reviewService.addOrUpdateReview(review);
        return R.ok();
    }

}


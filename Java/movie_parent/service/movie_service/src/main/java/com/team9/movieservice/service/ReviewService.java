package com.team9.movieservice.service;

import com.team9.movieservice.entity.Review;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team9.movieservice.entity.frontVo.ReviewShowVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
public interface ReviewService extends IService<Review> {

    List<ReviewShowVo> GetReviewByMovieId(Integer mid);

    void addOrUpdateReview(Review review);
}

package com.team9.movieservice.mapper;

import com.team9.movieservice.entity.Review;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team9.movieservice.entity.frontVo.ReviewShowVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
public interface ReviewMapper extends BaseMapper<Review> {

    List<ReviewShowVo> getReviewsByMovieId(Integer mid);
}

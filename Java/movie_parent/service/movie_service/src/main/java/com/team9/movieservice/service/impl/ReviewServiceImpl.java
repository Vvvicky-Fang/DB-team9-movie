package com.team9.movieservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team9.commonutils.R;
import com.team9.movieservice.entity.Review;
import com.team9.movieservice.entity.User;
import com.team9.movieservice.entity.frontVo.ReviewShowVo;
import com.team9.movieservice.mapper.ReviewMapper;
import com.team9.movieservice.service.ReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team9.movieservice.service.UserService;
import com.team9.servicebase.exceptionhandler.EasyPcException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {
    @Autowired
    private UserService userService;

    @Override
    public List<ReviewShowVo> GetReviewByMovieId(Integer mid) {
        List<ReviewShowVo> reviews = baseMapper.getReviewsByMovieId(mid);

        return reviews;
    }

    @Override
    public void addOrUpdateReview(Review review) {
        if(StringUtils.isEmpty(review.getUserID())){
            throw new EasyPcException(20001, "Please login in");
        }
        if(StringUtils.isEmpty(review.getRating())){
            throw new EasyPcException(20001, "Please rate");
        }
        if(StringUtils.isEmpty(review.getContent())){
            throw new EasyPcException(20001, "The content can not be empty");
        }
        String UserId = review.getUserID();
        Integer movieId = review.getMovieId();

        QueryWrapper<Review> wrapper = new QueryWrapper<>();
        wrapper.eq("MovieId",movieId);
        wrapper.eq("UserID",UserId);
        Integer count = baseMapper.selectCount(wrapper);
        Date date = new Date();
        review.setReviewTime(date);
        System.out.println(review);
        if(count > 0){
            Review review1 = baseMapper.selectOne(wrapper);
            review.setReviewID(review1.getReviewID());
            Integer affect = baseMapper.updateById(review);
            if(affect == 0){
                throw new EasyPcException(20001, "Failed");
            }
        }else{
            System.out.println("執行新增review");
            Integer affect = baseMapper.insert(review);
            if(affect == 0){
                throw new EasyPcException(20001, "Failed");
            }
        }


    }
}

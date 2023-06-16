package com.team9.movieservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team9.movieservice.entity.Movie;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team9.movieservice.entity.MovieGenre;
import com.team9.movieservice.entity.frontVo.MovieFrontVo;
import com.team9.movieservice.entity.frontVo.MovieShowVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
public interface MovieService extends IService<Movie> {

    List<String> getTopBanner();

    Map<String, Object> getCourseFrontList(Page<Movie> pageCourse, MovieFrontVo movieFrontVo);

    MovieShowVo getMovieInfoById(String id);

    List<Movie> getNewMoviesByNum(Integer num);

    List<Movie> getCollectionByUserId(String uid);
}

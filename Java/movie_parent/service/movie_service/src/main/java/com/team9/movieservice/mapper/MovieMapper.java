package com.team9.movieservice.mapper;

import com.team9.movieservice.entity.Actor;
import com.team9.movieservice.entity.Movie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team9.movieservice.entity.MovieGenre;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
public interface MovieMapper extends BaseMapper<Movie> {

    List<Actor> selectActorsByMovieId(Integer movieId);

    List<MovieGenre> selectGenresByMovieId(Integer movieId);

    List<Movie> getLatestMovies(Integer num);

    List<Movie> getCollectionByUserId(String uid);
}

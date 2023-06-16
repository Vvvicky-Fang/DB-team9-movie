package com.team9.movieservice.mapper;

import com.team9.movieservice.entity.Actor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team9.movieservice.entity.Movie;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
public interface ActorMapper extends BaseMapper<Actor> {

    public List<Movie> getMoviesByActorID(String actorID);

    public List<Actor> getTopActorsByMovieCount(Integer num);
}

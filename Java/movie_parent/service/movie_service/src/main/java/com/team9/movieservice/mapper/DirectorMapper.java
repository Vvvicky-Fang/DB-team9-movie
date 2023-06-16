package com.team9.movieservice.mapper;

import com.team9.movieservice.entity.Director;
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
public interface DirectorMapper extends BaseMapper<Director> {

    List<Movie> getMoviesByDirectorID(String id);
}

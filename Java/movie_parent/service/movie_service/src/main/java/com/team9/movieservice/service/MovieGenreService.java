package com.team9.movieservice.service;

import com.team9.movieservice.entity.MovieGenre;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
public interface MovieGenreService extends IService<MovieGenre> {

    List<MovieGenre> getAllGenre();
}

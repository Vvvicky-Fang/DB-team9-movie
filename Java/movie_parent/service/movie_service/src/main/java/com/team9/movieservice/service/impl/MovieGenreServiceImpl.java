package com.team9.movieservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team9.movieservice.entity.MovieGenre;
import com.team9.movieservice.mapper.MovieGenreMapper;
import com.team9.movieservice.service.MovieGenreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class MovieGenreServiceImpl extends ServiceImpl<MovieGenreMapper, MovieGenre> implements MovieGenreService {

    @Override
    public List<MovieGenre> getAllGenre() {
        QueryWrapper<MovieGenre> wrapper = new QueryWrapper<>();
        List<MovieGenre> list = baseMapper.selectList(wrapper);
        return list;
    }
}

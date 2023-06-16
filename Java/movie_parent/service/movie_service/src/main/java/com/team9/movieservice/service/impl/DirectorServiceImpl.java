package com.team9.movieservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team9.movieservice.entity.Actor;
import com.team9.movieservice.entity.Director;
import com.team9.movieservice.entity.Movie;
import com.team9.movieservice.entity.frontVo.ActorInfoVo;
import com.team9.movieservice.entity.frontVo.DirectorInfoVo;
import com.team9.movieservice.mapper.DirectorMapper;
import com.team9.movieservice.service.DirectorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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
public class DirectorServiceImpl extends ServiceImpl<DirectorMapper, Director> implements DirectorService {

    @Override
    public DirectorInfoVo getDirectorInfoById(String id) {
        Director director = baseMapper.selectById(id);
        DirectorInfoVo directorInfoVo = new DirectorInfoVo();
        BeanUtils.copyProperties(director,directorInfoVo);

        List<Movie> movieList = baseMapper.getMoviesByDirectorID(id);
        directorInfoVo.setMovieList(movieList);
        return directorInfoVo;
    }
}

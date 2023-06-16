package com.team9.movieservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team9.movieservice.entity.*;
import com.team9.movieservice.entity.frontVo.MovieFrontVo;
import com.team9.movieservice.entity.frontVo.MovieShowVo;
import com.team9.movieservice.mapper.MovieMapper;
import com.team9.movieservice.service.DirectorService;
import com.team9.movieservice.service.MovieActorService;
import com.team9.movieservice.service.MovieAndGenreService;
import com.team9.movieservice.service.MovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    private MovieAndGenreService movieAndGenreService;

    @Autowired
    private MovieActorService movieActorService;

    @Autowired
    private DirectorService directorService;

    @Override
    public List<String> getTopBanner() {
        List<String> picList = new ArrayList<>();
        return picList;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<Movie> pageParam, MovieFrontVo movieFrontVo) {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        //先按照分類查詢所有符合要求的movie
        List<Integer> list = new ArrayList<>();
        if(!StringUtils.isEmpty(movieFrontVo.getGenreID())){
            QueryWrapper<MovieAndGenre> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("GenreID",movieFrontVo.getGenreID());
            List<MovieAndGenre> movieList = movieAndGenreService.list(queryWrapper);
            for (int i = 0; i < movieList.size(); i++) {
                list.add(movieList.get(i).getMovieID());
            }
        }
        wrapper.in("MovieID",list);

        //再按照更加細節的條件去排序
        if(!StringUtils.isEmpty(movieFrontVo.getRateAvg())){
            wrapper.orderByDesc("RateAvg");
        }
        if(!StringUtils.isEmpty(movieFrontVo.getGmtCreateSort())){
            wrapper.orderByDesc("ReleaseDate");
        }
        if(!StringUtils.isEmpty(movieFrontVo.getRateCount())){
            wrapper.orderByDesc("RateCount");
        }
        baseMapper.selectPage(pageParam, wrapper);

        List<Movie> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size= pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        //把数据放到map集合中

        Map<String, Object> map = new HashMap<>();
        map.put("items",records);
        map.put("current",current);
        map.put("pages",pages);
        map.put("size",size);
        map.put("total",total);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }

    @Override
    public MovieShowVo getMovieInfoById(String id) {
        MovieShowVo movie = new MovieShowVo();
        Movie movie1 = baseMapper.selectById(id);
        BeanUtils.copyProperties(movie1,movie);
        Integer movieId = movie1.getMovieID();

        List<Actor> actorList = baseMapper.selectActorsByMovieId(movieId);
        List<MovieGenre> movieGenreList = baseMapper.selectGenresByMovieId(movieId);

        Director director = directorService.getById(movie1.getDirectorID());

        movie.setDirector(director);
        movie.setActorList(actorList);
        movie.setGenreList(movieGenreList);
        return movie;
    }

    @Override
    public List<Movie> getNewMoviesByNum(Integer num) {
        List<Movie> movieList= baseMapper.getLatestMovies(num);
        return movieList;
    }

    @Override
    public List<Movie> getCollectionByUserId(String uid) {
        List<Movie> list = baseMapper.getCollectionByUserId(uid);
        return list;
    }
}

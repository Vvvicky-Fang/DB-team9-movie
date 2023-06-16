package com.team9.movieservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team9.movieservice.entity.Actor;
import com.team9.movieservice.entity.Movie;
import com.team9.movieservice.entity.MovieActor;
import com.team9.movieservice.entity.frontVo.ActorInfoVo;
import com.team9.movieservice.mapper.ActorMapper;
import com.team9.movieservice.service.ActorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team9.movieservice.service.MovieActorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor> implements ActorService {

    @Autowired
    private MovieActorService movieActorService;

    @Override
    public Map<String, Object> getAllActors(Page<Actor> pageParam, String name) {
        QueryWrapper<Actor> wrapper = new QueryWrapper<>();
//        if(!StringUtils.isEmpty(name)){
//            wrapper.like("Name",name);
//        }
        baseMapper.selectPage(pageParam, wrapper);

        List<Actor> records = pageParam.getRecords();
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
    public ActorInfoVo getActorInfoById(String id) {
        Actor actor = baseMapper.selectById(id);
        ActorInfoVo actorInfoVo = new ActorInfoVo();
        BeanUtils.copyProperties(actor,actorInfoVo);
        List<Movie> movieList = baseMapper.getMoviesByActorID(id);
        actorInfoVo.setMovieList(movieList);
        return actorInfoVo;
    }

    @Override
    public List<Actor> GetTopActors(Integer num) {
        List<Actor> list = baseMapper.getTopActorsByMovieCount(num);
        return list;
    }

//    @Override
//    public List<Actor> getAllActors(String name) {
//        QueryWrapper<Actor> wrapper = new QueryWrapper<>();
//        if(!StringUtils.isEmpty(name)){
//            wrapper.like("Name",name);
//        }
//        List<Actor> list = baseMapper.selectList(wrapper);
//        return list;
//    }
}

package com.team9.movieservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team9.movieservice.entity.Actor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team9.movieservice.entity.frontVo.ActorInfoVo;

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
public interface ActorService extends IService<Actor> {


    Map<String, Object> getAllActors(Page<Actor> actorPage,String name);

    ActorInfoVo getActorInfoById(String id);

    List<Actor> GetTopActors(Integer num);
}

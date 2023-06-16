package com.team9.movieservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team9.commonutils.R;
import com.team9.movieservice.entity.Actor;
import com.team9.movieservice.entity.Movie;
import com.team9.movieservice.entity.frontVo.ActorInfoVo;
import com.team9.movieservice.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
@RestController
@CrossOrigin
@RequestMapping("/movieservice/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("GetAllActors/{page}/{limit}")
    private R getAllActors(@PathVariable long page, @PathVariable long limit, @RequestParam(required = false) String name){
        Page<Actor> actorPage = new Page<>(page, limit);

        Map<String, Object> actorList = actorService.getAllActors(actorPage,name);
        return R.ok().data(actorList);
    }

    @GetMapping("GetActorInfo/{id}")
    public R getActorInfo(@PathVariable String id){
        ActorInfoVo actorInfoVo = actorService.getActorInfoById(id);
        return R.ok().data("actor",actorInfoVo);
    }

    @GetMapping("GetTopActors/{num}")
    public R getTopActors(@PathVariable Integer num)
    {
        List<Actor> list = actorService.GetTopActors(num);
        return R.ok().data("actorList",list);

    }


}


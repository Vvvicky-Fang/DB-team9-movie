package com.team9.movieservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team9.commonutils.R;
import com.team9.movieservice.entity.UserMovieFavorite;
import com.team9.movieservice.entity.frontVo.MovieFrontVo;
import com.team9.movieservice.service.UserMovieFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/movieservice/user-movie-favorite")
public class UserMovieFavoriteController {

    @Autowired
    private UserMovieFavoriteService userMovieFavoriteService;

    @PostMapping("addCollect")
    public R addCollect(@RequestBody UserMovieFavorite userMovieFavorite){

        userMovieFavoriteService.insertNewCollect(userMovieFavorite);
        return R.ok();
    }

    @DeleteMapping("cancelCollect")
    public R cancelCollect(@RequestBody UserMovieFavorite userMovieFavorite)
    {
        userMovieFavoriteService.removeCollect(userMovieFavorite);
        return R.ok();
    }

    @GetMapping("isCollect/{uid}/{mid}")
    public R isCollect(@PathVariable String uid,@PathVariable Integer mid){
        QueryWrapper<UserMovieFavorite> wrapper = new QueryWrapper<>();
        wrapper.eq("UserID",uid);
        wrapper.eq("MovieID",mid);
        Integer isCollect = userMovieFavoriteService.count(wrapper);
        return R.ok().data("isCollect",isCollect);
    }

}


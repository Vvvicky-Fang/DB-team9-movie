package com.team9.movieservice.controller;


import com.team9.commonutils.R;
import com.team9.movieservice.entity.frontVo.ActorInfoVo;
import com.team9.movieservice.entity.frontVo.DirectorInfoVo;
import com.team9.movieservice.service.ActorService;
import com.team9.movieservice.service.DirectorService;
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
@RequestMapping("/movieservice/director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping("GetDirectorInfo/{id}")
    public R getActorInfo(@PathVariable String id){
        DirectorInfoVo directorInfoVo= directorService.getDirectorInfoById(id);
        return R.ok().data("director",directorInfoVo);
    }

}


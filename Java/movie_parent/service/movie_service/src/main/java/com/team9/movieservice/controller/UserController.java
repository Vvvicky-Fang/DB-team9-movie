package com.team9.movieservice.controller;


import com.team9.commonutils.JwtUtils;
import com.team9.commonutils.R;
import com.team9.movieservice.entity.User;
import com.team9.movieservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/movieservice/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public R Login(@RequestBody User user){
        String token = userService.login(user);
        return R.ok().data("token",token);
    }
    @PostMapping("register")
    public R registerUser(@RequestBody User user){
        userService.register(user);
        return R.ok();
    }

    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println(memberId);
        User member = userService.getById(memberId);
        System.out.println(member);
        return R.ok().data("userInfo", member);
    }

}


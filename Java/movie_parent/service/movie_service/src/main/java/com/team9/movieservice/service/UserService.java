package com.team9.movieservice.service;

import com.team9.movieservice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
public interface UserService extends IService<User> {

    String login(User user);

    void register(User user);
}

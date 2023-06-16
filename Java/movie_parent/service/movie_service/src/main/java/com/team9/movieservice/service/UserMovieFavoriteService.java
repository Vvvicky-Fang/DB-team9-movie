package com.team9.movieservice.service;

import com.team9.movieservice.entity.UserMovieFavorite;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
public interface UserMovieFavoriteService extends IService<UserMovieFavorite> {

    void insertNewCollect(UserMovieFavorite userMovieFavorite);

    void removeCollect(UserMovieFavorite userMovieFavorite);
}

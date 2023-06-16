package com.team9.movieservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team9.movieservice.entity.UserMovieFavorite;
import com.team9.movieservice.mapper.UserMovieFavoriteMapper;
import com.team9.movieservice.service.UserMovieFavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
@Service
public class UserMovieFavoriteServiceImpl extends ServiceImpl<UserMovieFavoriteMapper, UserMovieFavorite> implements UserMovieFavoriteService {

    @Override
    public void insertNewCollect(UserMovieFavorite userMovieFavorite) {
        baseMapper.insert(userMovieFavorite);
    }

    @Override
    public void removeCollect(UserMovieFavorite userMovieFavorite) {
        QueryWrapper<UserMovieFavorite> wrapper = new QueryWrapper<>();
        wrapper.eq("UserID",userMovieFavorite.getUserID());
        wrapper.eq("MovieID",userMovieFavorite.getMovieID());
        baseMapper.delete(wrapper);
    }
}

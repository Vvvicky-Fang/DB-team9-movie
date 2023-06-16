package com.team9.movieservice.service;

import com.team9.movieservice.entity.Director;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team9.movieservice.entity.frontVo.DirectorInfoVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
public interface DirectorService extends IService<Director> {

    DirectorInfoVo getDirectorInfoById(String id);
}

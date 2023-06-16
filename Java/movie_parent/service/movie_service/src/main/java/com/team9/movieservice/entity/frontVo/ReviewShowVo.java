package com.team9.movieservice.entity.frontVo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReviewShowVo {
    @TableId(value = "ReviewID", type = IdType.ID_WORKER_STR)
    private Integer ReviewID;

    @TableField("MovieId")
    private Integer MovieId;

    @TableField("Rating")
    private BigDecimal Rating;

    @TableField("Content")
    private String Content;

    @TableField("UserID")
    private String UserID;

    @TableField("ReviewTime")
    private Date ReviewTime;

    private String Username;

    private String AvatarPath;


}

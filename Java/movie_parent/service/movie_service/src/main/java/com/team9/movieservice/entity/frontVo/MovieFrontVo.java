package com.team9.movieservice.entity.frontVo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MovieFrontVo {

    @ApiModelProperty(value = "收藏數量")
    int CollectionNum;

    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;

    @TableField("RateAvg")
    private String RateAvg;

    @TableField("RateCount")
    private String RateCount;

    @TableId(value = "GenreID")
    private String GenreID;

}

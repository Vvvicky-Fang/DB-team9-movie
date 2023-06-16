package com.team9.movieservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Movie")
@ApiModel(value="Movie对象", description="")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "MovieID", type = IdType.ID_WORKER_STR)
    private Integer MovieID;

    @TableField("Title")
    private String Title;

    @TableField("DirectorID")
    private Integer DirectorID;

    @TableField("ReleaseDate")
    private Date ReleaseDate;

    @TableField("Duration")
    private Integer Duration;

    @TableField("RateAvg")
    private Integer RateAvg;

    @TableField("RateCount")
    private Integer RateCount;

    @TableField("PicUrl")
    private String PicUrl;

    @TableField("Overview")
    private String Overview;

    @TableField("BannerPath")
    private String BannerPath;


}

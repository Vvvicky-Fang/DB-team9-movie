package com.team9.movieservice.entity.frontVo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team9.movieservice.entity.Actor;
import com.team9.movieservice.entity.Director;
import com.team9.movieservice.entity.MovieGenre;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MovieShowVo {
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

    @TableField("Actors")
    private List<Actor> ActorList;

    @TableField("Genre")
    private List<MovieGenre> GenreList;

    @TableField("Director")
    private Director director;
}

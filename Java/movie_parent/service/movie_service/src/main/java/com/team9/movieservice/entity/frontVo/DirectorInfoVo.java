package com.team9.movieservice.entity.frontVo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team9.movieservice.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class DirectorInfoVo {
    @TableId(value = "DirectorID", type = IdType.ID_WORKER_STR)
    private Integer DirectorID;

    @TableField("Name")
    private String Name;

    @TableField("Nationality")
    private String Nationality;

    @TableField("NotableWorks")
    private String NotableWorks;

    @TableField("ImagePath")
    private String ImagePath;

    @TableField("biography")
    private String biography;
    private List<Movie> movieList;

}

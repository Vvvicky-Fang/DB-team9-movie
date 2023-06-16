package com.team9.movieservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("MovieAndGenre")
@ApiModel(value="MovieAndGenre对象", description="")
public class MovieAndGenre implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "MovieID", type = IdType.ID_WORKER_STR)
    private Integer MovieID;

    @TableField("GenreID")
    private Integer GenreID;


}

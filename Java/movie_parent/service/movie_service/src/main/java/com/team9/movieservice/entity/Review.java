package com.team9.movieservice.entity;

import java.math.BigDecimal;
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
@TableName("Review")
@ApiModel(value="Review对象", description="")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ReviewID", type = IdType.AUTO)
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


}

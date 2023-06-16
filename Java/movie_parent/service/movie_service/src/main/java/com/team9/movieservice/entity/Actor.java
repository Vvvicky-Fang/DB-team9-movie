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
@TableName("Actor")
@ApiModel(value="Actor对象", description="")
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ActorID", type = IdType.ID_WORKER_STR)
    private Integer ActorID;

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


}

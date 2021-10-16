package com.example.blogstudy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zj_sorts")
@ApiModel(value = "ZjSorts对象", description = "分类表")
public class ZjSorts extends Model<ZjSorts> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //分类ID
    @ApiModelProperty(value = "分类ID")
    @TableField("sort_id")
    private Long sortId;

    //分类名称
    @ApiModelProperty(value = "分类名称")
    @TableField("sort_name")
    private String sortName;

    //分类别名
    @ApiModelProperty(value = "分类别名")
    @TableField("sort_alias")
    private String sortAlias;

    //分类描述
    @ApiModelProperty(value = "分类描述")
    @TableField("sort_description")
    private String sortDescription;

    //父分类ID
    @ApiModelProperty(value = "父分类ID")
    @TableField("parent_sort_id")
    private Long parentSortId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

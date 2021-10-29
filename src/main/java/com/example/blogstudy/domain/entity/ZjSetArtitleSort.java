package com.example.blogstudy.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 文章类别表
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zj_set_artitle_sort")
@ApiModel(value = "ZjSetArtitleSort对象", description = "文章类别表")
public class ZjSetArtitleSort extends Model<ZjSetArtitleSort> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //文章ID
    @ApiModelProperty(value = "文章ID")
    @TableField("article_id")
    private Long articleId;

    //分类ID
    @ApiModelProperty(value = "分类ID")
    @TableField("sort_id")
    private Long sortId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

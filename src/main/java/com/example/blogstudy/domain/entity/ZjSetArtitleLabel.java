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
 * 文章标签对应表
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zj_set_artitle_label")
@ApiModel(value = "ZjSetArtitleLabel对象", description = "文章标签对应表")
public class ZjSetArtitleLabel extends Model<ZjSetArtitleLabel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //文章ID
    @ApiModelProperty(value = "文章ID")
    @TableField("article_id")
    private Long articleId;

    @TableField("label_id")
    private Long labelId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

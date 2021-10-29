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
 * 标签表
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zj_labels")
@ApiModel(value = "ZjLabels对象", description = "标签表")
public class ZjLabels extends Model<ZjLabels> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //标签ID
    @ApiModelProperty(value = "标签ID")
    @TableField("label_id")
    private Long labelId;

    //标签名称
    @ApiModelProperty(value = "标签名称")
    @TableField("label_name")
    private String labelName;

    //标签别名
    @ApiModelProperty(value = "标签别名")
    @TableField("label_alias")
    private String labelAlias;

    //标签描述
    @ApiModelProperty(value = "标签描述")
    @TableField("label_description")
    private String labelDescription;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

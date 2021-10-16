package com.example.blogstudy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
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
 * 评论表
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zj_comments")
@ApiModel(value = "ZjComments对象", description = "评论表")
public class ZjComments extends Model<ZjComments> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //评论ID
    @ApiModelProperty(value = "评论ID")
    @TableField("comment_id")
    private Long commentId;

    //发表用户ID
    @ApiModelProperty(value = "发表用户ID")
    @TableField("user_id")
    private Long userId;

    //评论博文ID
    @ApiModelProperty(value = "评论博文ID")
    @TableField("article_id")
    private Long articleId;

    //点赞数
    @ApiModelProperty(value = "点赞数")
    @TableField("comment_like_count")
    private Long commentLikeCount;

    //评论日期
    @ApiModelProperty(value = "评论日期")
    @TableField("comment_date")
    private Date commentDate;

    //评论内容
    @ApiModelProperty(value = "评论内容")
    @TableField("comment_content")
    private String commentContent;

    //父评论ID
    @ApiModelProperty(value = "父评论ID")
    @TableField("parent_comment_id")
    private Long parentCommentId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package com.example.blogstudy.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

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
 * 文章表
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zj_articles")
@ApiModel(value = "ZjArticles对象", description = "文章表")
public class ZjArticles extends Model<ZjArticles> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //博文ID
    @ApiModelProperty(value = "博文ID")
    @TableField("article_id")
    private Long articleId;

    //发表用户ID
    @ApiModelProperty(value = "发表用户ID")
    @TableField("user_id")
    private Long userId;

    //博文标题
    @ApiModelProperty(value = "博文标题")
    @TableField("article_title")
    private String articleTitle;

    //博文内容
    @ApiModelProperty(value = "博文内容")
    @TableField("article_content")
    private String articleContent;

    //浏览量
    @ApiModelProperty(value = "浏览量")
    @TableField("article_views")
    private Long articleViews;

    //评论总数
    @ApiModelProperty(value = "评论总数")
    @TableField("article_comment_count")
    private Long articleCommentCount;

    //发表时间
    @ApiModelProperty(value = "发表时间")
    @TableField("article_date")
    private Date articleDate;

    @TableField("article_like_count")
    private Long articleLikeCount;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

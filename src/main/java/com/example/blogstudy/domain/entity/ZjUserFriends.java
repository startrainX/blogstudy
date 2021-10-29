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
 * 好友表
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zj_user_friends")
@ApiModel(value = "ZjUserFriends对象", description = "好友表")
public class ZjUserFriends extends Model<ZjUserFriends> {

    private static final long serialVersionUID = 1L;

    //标识ID
    @ApiModelProperty(value = "标识ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //用户ID
    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    //好友ID
    @ApiModelProperty(value = "好友ID")
    @TableField("user_friends_id")
    private Long userFriendsId;

    //好友备注
    @ApiModelProperty(value = "好友备注")
    @TableField("user_note")
    private String userNote;

    //好友状态
    @ApiModelProperty(value = "好友状态")
    @TableField("user_status")
    private String userStatus;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

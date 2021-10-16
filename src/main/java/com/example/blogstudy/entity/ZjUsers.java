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
 * 用户表
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zj_users")
@ApiModel(value = "ZjUsers对象", description = "用户表")
public class ZjUsers extends Model<ZjUsers> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //用户ID
    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    //用户IP
    @ApiModelProperty(value = "用户IP")
    @TableField("user_ip")
    private String userIp;

    //用户名
    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    //用户密码
    @ApiModelProperty(value = "用户密码")
    @TableField("user_password")
    private String userPassword;

    //用户邮箱
    @ApiModelProperty(value = "用户邮箱")
    @TableField("user_email")
    private String userEmail;

    //用户头像
    @ApiModelProperty(value = "用户头像")
    @TableField("user_profile_photo")
    private String userProfilePhoto;

    //注册时间
    @ApiModelProperty(value = "注册时间")
    @TableField("user_registration_time")
    private Date userRegistrationTime;

    //用户生日
    @ApiModelProperty(value = "用户生日")
    @TableField("user_birthday")
    private Date userBirthday;

    //用户年龄
    @ApiModelProperty(value = "用户年龄")
    @TableField("user_age")
    private Integer userAge;

    //用户手机号
    @ApiModelProperty(value = "用户手机号")
    @TableField("user_telephone_number")
    private Integer userTelephoneNumber;

    //用户昵称
    @ApiModelProperty(value = "用户昵称")
    @TableField("user_nickname")
    private String userNickname;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package io.github.chengmboy.cloudrs.uc.pojo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cheng_mboy
 */
@ApiModel
@Data
public class UserRegisterDTO {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("邮件")
    private String email;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("登录名")
    @NotNull
    @Size(max = 20)
    private  String loginName;

    @ApiModelProperty("密码")
    @Size(min = 6)
    private String password;

}

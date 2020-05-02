package com.zh.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("用户实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @ApiModelProperty("用户id")
    private int uid;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("密码")
    private String pwd;

}

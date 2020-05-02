package com.zh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private Integer dId;
    private String dName;

}

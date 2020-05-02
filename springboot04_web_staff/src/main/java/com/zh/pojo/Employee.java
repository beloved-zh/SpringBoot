package com.zh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 员工表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer eId;
    private String eName;
    private String email;
    private Integer sex; //0 女  1 男
    private Department department;
    private Date birth;

    public Employee(Integer eId, String eName, String email, Integer sex, Department department) {
        this.eId = eId;
        this.eName = eName;
        this.email = email;
        this.sex = sex;
        this.department = department;
        //默认的自动创建时间
        this.birth = new Date();
    }
}








































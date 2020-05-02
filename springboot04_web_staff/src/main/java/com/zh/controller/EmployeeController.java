package com.zh.controller;

import com.zh.mapper.DepartmentMapper;
import com.zh.mapper.EmployeeMapper;
import com.zh.pojo.Department;
import com.zh.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeMapper emapper;
    @Autowired
    private DepartmentMapper dmapper;

    @RequestMapping("/emps")
    public String list(Model model){

        Collection<Employee> employees = emapper.findAll();

        model.addAttribute("emps",employees);

        return "emp/list";
    }

    @GetMapping("/gotoAddEmp")
    public String gotoAddEmp(Model model){

        //查询所有部门
        Collection<Department> departments = dmapper.findAll();

        model.addAttribute("departments",departments);

        return "emp/addemp";
    }

    @PostMapping("/addemp")
    public String addemp(Employee employee,Model model){

        emapper.save(employee);

        return "redirect:/emps";
    }


    @GetMapping("/emp/{eid}")
    public String gotoUpdate(@PathVariable("eid") Integer eid, Model model){

        //查出原来的数据
        Employee employee = emapper.findById(eid);
        model.addAttribute("employee",employee);

        //查询所有部门
        Collection<Department> departments = dmapper.findAll();
        model.addAttribute("departments",departments);

        return "emp/update";
    }

    @GetMapping("/delete/{eid}")
    public String gotoUpdate(@PathVariable("eid") Integer eid){

        emapper.delete(eid);

        return "redirect:/emps";
    }

}




































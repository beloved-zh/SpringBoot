package com.zh.mapper;

import com.zh.pojo.Department;
import com.zh.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeMapper {

    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;

    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001,"AA","A1425279634@qq.com",1,new Department(101,"教学部")));
        employees.put(1002, new Employee(1002,"BB","B1425279634@qq.com",0,new Department(102,"市场部")));
        employees.put(1003, new Employee(1003,"CC","C1425279634@qq.com",1,new Department(103,"运营部")));
        employees.put(1004, new Employee(1004,"DD","D1425279634@qq.com",0,new Department(104,"开发部")));
        employees.put(1005, new Employee(1005,"EE","E1425279634@qq.com",1,new Department(105,"后勤部")));

    }

    //主键自增
    private static Integer  eid = 1006;


    //添加一个员工
    public void save(Employee employee){
        if (employee.getEId() == null){
            employee.setEId(eid++);
        }

        employee.setDepartment(DepartmentMapper.findById(employee.getDepartment().getDId()));

        employees.put(employee.getEId(),employee);
    }


    //获得全部员工
    public Collection<Employee> findAll(){
        return employees.values();
    }


    //通过id查询
    public Employee findById(Integer eId){
        return employees.get(eId);
    }


    //删除员工
    public void delete(Integer eId){
        employees.remove(eId);
    }
}























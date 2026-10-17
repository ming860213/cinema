package com.cinema.service;


import com.cinema.dto.EmployeeDTO;
import com.cinema.dto.EmployeeLoginDTO;
import com.cinema.dto.EmployeePageQueryDTO;
import com.cinema.entity.Employee;
import com.cinema.result.PageResult;
import com.cinema.result.Result;
import com.github.pagehelper.Page;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
     PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}

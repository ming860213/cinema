package com.cinema.service;


import com.cinema.dto.EmployeeLoginDTO;
import com.cinema.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

}

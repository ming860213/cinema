package com.cinema.service;


import com.cinema.dto.EmployeeDTO;
import com.cinema.dto.EmployeeLoginDTO;
import com.cinema.dto.EmployeePageQueryDTO;
import com.cinema.dto.PasswordEditDTO;
import com.cinema.entity.Employee;
import com.cinema.result.PageResult;

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

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * 启用或禁用员工
     * @param status
     */
    void startOrStop(Integer status,Long id);

    /**
     * 员工修改密码
     * @param passwordEditDTO
     */
    void updatePassword(PasswordEditDTO passwordEditDTO);

    /**
     * 编辑员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}

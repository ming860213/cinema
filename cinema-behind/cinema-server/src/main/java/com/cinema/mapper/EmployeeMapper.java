package com.cinema.mapper;

import com.cinema.annotation.AutoFill;
import com.cinema.dto.EmployeePageQueryDTO;
import com.cinema.entity.Employee;
import com.cinema.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 通过用户名查找
     * @param username
     * @return
     */
    @Select("select * from employee where username =#{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee
     */
    @AutoFill(value = OperationType.INSERT)
    void save(Employee employee);

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @Select("select * from employee where id =#{id}")
    Employee getById(Long id);

    /**
     * 更新员工信息
     * @param employee
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);
}

package com.cinema.mapper;

import com.cinema.annotation.AutoFill;
import com.cinema.entity.Employee;
import com.cinema.enumeration.OperationType;
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
}

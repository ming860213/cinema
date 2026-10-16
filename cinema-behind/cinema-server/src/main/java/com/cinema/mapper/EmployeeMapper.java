package com.cinema.mapper;

import com.cinema.entity.Employee;
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
}

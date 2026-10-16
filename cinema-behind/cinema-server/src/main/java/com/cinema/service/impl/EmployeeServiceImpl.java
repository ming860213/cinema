package com.cinema.service.impl;

import com.cinema.constant.MessageConstant;
import com.cinema.constant.PasswordConstant;
import com.cinema.dto.EmployeeLoginDTO;
import com.cinema.entity.Employee;
import com.cinema.exception.AccountNotFoundException;
import com.cinema.exception.PasswordErrorException;
import com.cinema.mapper.EmployeeMapper;
import com.cinema.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
         String username = employeeLoginDTO.getUsername();
         String password = employeeLoginDTO.getPassword();

         Employee employee = employeeMapper.getByUsername(username);

         //处理异常（用户名不存在、密码不对、账号被锁定）
         if(employee == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
         }
         password= DigestUtils.md5DigestAsHex(password.getBytes());
         if(!password.equals(employee.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
         }
         if(employee.getStatus() == 0){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
         }
         //返回实体对象
         return employee;
    }
}

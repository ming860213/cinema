package com.cinema.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.cinema.constant.MessageConstant;
import com.cinema.constant.PasswordConstant;
import com.cinema.constant.StatusConstant;
import com.cinema.dto.EmployeeDTO;
import com.cinema.dto.EmployeeLoginDTO;
import com.cinema.dto.EmployeePageQueryDTO;
import com.cinema.dto.PasswordEditDTO;
import com.cinema.entity.Employee;
import com.cinema.exception.AccountNotFoundException;
import com.cinema.exception.PasswordErrorException;
import com.cinema.mapper.EmployeeMapper;
import com.cinema.result.PageResult;
import com.cinema.result.Result;
import com.cinema.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
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

    /**
     * 新增员工
     * @param employeeDTO
     */
    public void save(EmployeeDTO employeeDTO) {
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        employee.setStatus(StatusConstant.ENABLE);
        employeeMapper.save(employee);
    }

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());

        Page<Employee> page=employeeMapper.pageQuery(employeePageQueryDTO);
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    public Employee getById(Long id) {
        Employee employee =employeeMapper.getById(id);
        employee.setPassword("********");
        return employee;
    }

    /**
     * 启用或禁用员工
     * @param status
     */
    public void startOrStop(Integer status,Long id) {
        Employee employee=Employee.builder()
                .id(id)
                .status(status)
                .build();
        employeeMapper.update(employee);
    }

    /**
     * 员工修改密码
     * @param passwordEditDTO
     */
    public void updatePassword(PasswordEditDTO passwordEditDTO) {
        //取得数据库中数据
        Employee employee= employeeMapper.getById(passwordEditDTO.getEmpId());
        //校验密码是否新旧一样
        String password= passwordEditDTO.getOldPassword();
        if(password.equals(passwordEditDTO.getNewPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_REPEAT_FAILED);
        }
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        //校验旧密码是否与数据库一致
        if(!password.equals(employee.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //取新密码md5加密
        String pwd= DigestUtils.md5DigestAsHex(passwordEditDTO.getNewPassword().getBytes());
        //将密码传入mapper层
        Employee emp =Employee.builder()
                .id(passwordEditDTO.getEmpId())
                .password(pwd)
                .build();
        employeeMapper.update(emp);
    }

    /**
     * 修改员工信息
     * @param employeeDTO
     */
    public void update(EmployeeDTO employeeDTO) {
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        employeeMapper.update(employee);
    }
}

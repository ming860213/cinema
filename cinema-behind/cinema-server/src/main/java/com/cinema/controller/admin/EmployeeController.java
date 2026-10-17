package com.cinema.controller.admin;

import com.cinema.annotation.AutoFill;
import com.cinema.constant.JwtClaimsConstant;
import com.cinema.dto.EmployeeDTO;
import com.cinema.dto.EmployeeLoginDTO;
import com.cinema.dto.EmployeePageQueryDTO;
import com.cinema.dto.PasswordEditDTO;
import com.cinema.entity.Employee;
import com.cinema.properties.JwtProperties;
import com.cinema.result.PageResult;
import com.cinema.result.Result;
import com.cinema.service.EmployeeService;
import com.cinema.utils.JwtUtil;
import com.cinema.vo.EmployeeLoginVO;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/employee")
@Slf4j
@Api(tags ="员工相关接口")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;


    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录接口")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录:{}",employeeLoginDTO);
        Employee employee=employeeService.login(employeeLoginDTO);

        //生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "员工退出")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增员工接口")
    public Result save(@RequestBody EmployeeDTO employeeDTO){
        log.info("成功新增员工:{}",employeeDTO);
        employeeService.save(employeeDTO);
        return  Result.success();
    }

    @GetMapping
    @ApiOperation(value = "员工分页查询")
    public Result<PageResult> page(@RequestBody EmployeePageQueryDTO employeePageQueryDTO){
        PageResult PageQuery= employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(PageQuery);
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value="根据id查询员工")
    public Result getById(@PathVariable Long id){
        Employee employee= employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 启用或禁用员工
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){
        employeeService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 员工修改密码
     * @param passwordEditDTO
     * @return
     */
    @PutMapping("/editPassword")
    @ApiOperation(value = "员工修改密码接口")
    public Result updatePassword(@RequestBody PasswordEditDTO passwordEditDTO){
        log.info("修改密码:{}",passwordEditDTO);
        employeeService.updatePassword(passwordEditDTO);
        return Result.success();
    }

    /**
     * 编辑员工信息
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation(value = "编辑员工信息")
    public Result update(EmployeeDTO employeeDTO){
        log.info("编辑员工信息:{}",employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }
}

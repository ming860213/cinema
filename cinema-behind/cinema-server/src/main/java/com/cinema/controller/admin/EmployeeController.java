package com.cinema.controller.admin;

import com.cinema.service.EmployeeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/employee")
@Slf4j
@Api(tags ="员工相关接口")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
}

package com.cinema.controller.admin;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "影院相关接口")
@Slf4j
@RequestMapping("/admin/cinema")
public class CinemaController {
}

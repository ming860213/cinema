package com.cinema.controller.admin;

import com.cinema.constant.MessageConstant;
import com.cinema.result.Result;
import com.cinema.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Api(tags="通用接口")
@Slf4j
public class CommonController {

    private final AliOssUtil aliOssUtil;

    public CommonController(AliOssUtil aliOssUtil) {
        this.aliOssUtil = aliOssUtil;
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Result<String> upload(MultipartFile file) {

        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件的后缀名
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //生成随机文件名加后缀
            String objectName = UUID.randomUUID().toString() + extension;

            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.info("文件上传失败: {}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
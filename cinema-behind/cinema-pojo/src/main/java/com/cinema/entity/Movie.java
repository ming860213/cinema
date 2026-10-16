package com.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    //id
    private Long id;
    //电影名称
    private String name;
    //电影分类id
    private Long categoryId;
    //电影图片
    private String img;
    //描述信息
    private String description;
    //出版号
    private String publishNumber;
    //出版公司
    private String publishCompany;
    //上映时间
    private LocalDateTime releaseTime;
    //状态（0 下映 1 上映中）
    private Integer status;

    private LocalDateTime createTime;    // 创建时间（首次录入时间）
    private LocalDateTime updateTime;    // 修改时间
    private Long createUser;             // 最先负责让该电影上映的人（员工id）
    private Long updateUser;             // 后续修改的人（员工id）
}

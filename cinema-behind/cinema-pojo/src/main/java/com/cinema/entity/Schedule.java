package com.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule implements Serializable {
    //id
    private Long id;
    //电影id
    private Long movieId;
    //影厅id
    private Long hallId;
    //开场时间
    private LocalDateTime beginTime;
    //散场时间
    private LocalDateTime endTime;
    //本场基础票价
    private BigDecimal basePrice;
    //语言版本（国语/英语/IMAX等）
    private String language;
    //状态（0 取消 1 正常）
    private Integer status;

    private LocalDateTime createTime;    // 创建时间（排片时间）
    private LocalDateTime updateTime;    // 修改时间
    private Long createUser;             // 创建人id
    private Long updateUser;             // 修改人id
}

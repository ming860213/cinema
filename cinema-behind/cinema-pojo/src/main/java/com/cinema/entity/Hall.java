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
public class Hall {
    //id
    private Long id;
    //影厅名称
    private String name;
    //影厅类型（1 普通厅 2 IMAX厅 3 杜比厅）
    private Integer type;
    //排数
    private Integer rowCount;
    //每排座位数
    private Integer colCount;
    //状态（0 停用 1 启用）
    private Integer status;

    private LocalDateTime createTime;    // 创建时间
    private LocalDateTime updateTime;    // 修改时间
    private Long createUser;             // 创建人id
    private Long updateUser;             // 修改人id
}

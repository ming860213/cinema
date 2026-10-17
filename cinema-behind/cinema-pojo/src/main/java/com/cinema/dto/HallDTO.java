package com.cinema.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class HallDTO implements Serializable {
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

}

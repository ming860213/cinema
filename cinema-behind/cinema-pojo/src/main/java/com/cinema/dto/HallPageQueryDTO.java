package com.cinema.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class HallPageQueryDTO implements Serializable {
    private int page;

    private int pageSize;

    private Integer status;

    private Integer type;
}

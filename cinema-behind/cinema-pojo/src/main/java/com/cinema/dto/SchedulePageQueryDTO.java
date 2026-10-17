package com.cinema.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SchedulePageQueryDTO implements Serializable {
    private int page;

    private int pageSize;

    private Long movidId;

    private Long hallId;

    private Integer status;

    private LocalDateTime beginTimeStart;

    private LocalDateTime beginTimeEnd;
}

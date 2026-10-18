package com.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    //商品id
    private Long goodId;

    //口味名称
    private String name;

    //口味数据list
    private String value;
}

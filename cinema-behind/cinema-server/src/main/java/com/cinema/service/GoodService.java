package com.cinema.service;

import com.cinema.dto.GoodDTO;

public interface GoodService {

    /**
     * 新增商品
     * @param goodDTO
     */
    void save(GoodDTO goodDTO);
}

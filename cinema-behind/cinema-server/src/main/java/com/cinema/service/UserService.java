package com.cinema.service;

import com.cinema.dto.UserLoginDTO;
import com.cinema.entity.User;

public interface UserService {

    User login(UserLoginDTO userLoginDTO);
}

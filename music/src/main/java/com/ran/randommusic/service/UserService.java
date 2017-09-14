package com.ran.randommusic.service;


import com.ran.randommusic.model.User;

import java.util.List;

/**
 * Created by ran on 17/7/30.
 */
public interface UserService {

    List<User> getAllUser();

    User getUserByPhoneOrEmail(String emailOrPhone, Short state);

    User getUserById(Long userId);
}

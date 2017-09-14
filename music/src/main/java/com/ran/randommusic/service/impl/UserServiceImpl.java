package com.ran.randommusic.service.impl;
import com.ran.randommusic.dao.UserDao;
import com.ran.randommusic.model.User;
import com.ran.randommusic.service.UserService;
import com.ran.randommusic.util.JedisUtil;
import com.ran.randommusic.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ran on 17/7/30.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Resource
    private UserDao userDao;

    public User getUserById(Long userId) {
        User user ;
        Jedis jedisClient = JedisUtil.getJedisResource();
        String userJson = jedisClient.get(User.class.getName() + userId);
        if(userJson != null){
            user = JsonUtil.convertToObject(userJson, User.class);
            logger.debug("get user from redis");
        }else{
            user = userDao.selectUserById(userId);
            jedisClient.set(user.getClass().getName() + userId, JsonUtil.getJsonStr(user));
            logger.debug("get user from mysql");
        }
        return user;
    }

    public User getUserByPhoneOrEmail(String emailOrPhone, Short state) {
        return userDao.selectUserByPhoneOrEmail(emailOrPhone,state);
    }

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }
}
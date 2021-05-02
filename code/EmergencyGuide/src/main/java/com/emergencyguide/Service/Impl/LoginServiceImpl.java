package com.emergencyguide.Service.Impl;

import com.emergencyguide.Dao.UserDao;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDao userDao;
    public User doLogin(User user){
        return userDao.queryLogin(user);
    }

}

package com.dingxianginc.captcha.demo.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {


    public boolean validLogin(String username, String password) {

        // 这里模拟的是校验登录的业务流程，在实际场景中也可以是其他业务流程

        return true;
    }

}

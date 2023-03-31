package com.dingxianginc.captcha.demo.controller;

import com.dingxianginc.captcha.demo.service.CaptchaService;
import com.dingxianginc.captcha.demo.service.UserService;
import com.dingxianginc.captcha.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login(String username, String password, String captchaToken) {

        if ( !captchaService.verifyToken(captchaToken) ) {
            return ResultUtil.getFailedResult("验证码token无效");
        }

        userService.validLogin(username, password);

        return ResultUtil.getSuccessResult("登录成功");

    }


}

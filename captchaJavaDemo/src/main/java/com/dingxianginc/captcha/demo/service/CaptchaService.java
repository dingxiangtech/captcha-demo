package com.dingxianginc.captcha.demo.service;

import com.dingxianginc.ctu.client.CaptchaClient;
import com.dingxianginc.ctu.client.model.CaptchaResponse;
import org.springframework.stereotype.Service;

@Service
public class CaptchaService {


    public boolean verifyToken(String token){

        boolean res = false;

        /**构造入参为appId和appSecret
         * appId和前端验证码的appId保持一致，appId可公开
         * appSecret为秘钥，请勿公开
         * token在前端完成验证后可以获取到，随业务请求发送到后台，token有效期为两分钟
         * ip 可选，提交业务参数的客户端ip
         **/
        try {
            String appId = "appId";         // 顶象控制台应用管理或应用配置模块获取
            String appSecret = "appSecret";      // 顶象控制台应用管理或应用配置模块获取
            CaptchaClient captchaClient = new CaptchaClient(appId,appSecret);
            captchaClient.setCaptchaUrl("https://cap.dingxiang-inc.com/api/tokenVerify");
            //指定服务器地址，saas可在控制台，应用管理页面最上方获取
            CaptchaResponse response = captchaClient.verifyToken(token);
            //针对一些token冒用的情况，业务方可以采集客户端ip随token一起提交到验证码服务，验证码服务除了判断token的合法性还会校验提交业务参数的客户端ip和验证码颁发token的客户端ip是否一致
            System.out.println(response.getCaptchaStatus());
            //确保验证状态是SERVER_SUCCESS，SDK中有容错机制，在网络出现异常的情况会返回通过
            //验证码服务采集到的客户端ip
            if (response.getResult()) {
                /**token验证通过，继续其他流程**/
                res = true;
            } else {
                /**token验证失败，业务系统可以直接阻断该次请求或者继续弹验证码**/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }


}

# captcha-demo
顶象验证码Demo

## 验证码工作流程
![image](https://user-images.githubusercontent.com/35217413/225861273-eb1c5670-e64e-4450-b1f0-c03cb7968d62.png)

## demo运行教程

### captchaUIdemo

文件夹说明 ：模拟了web端登录流程接入验证码的场景，对应图中的验证码初始化和验证码验证两个阶段

使用流程 ：

1. 下载该文件夹到电脑中，下载完成后，文件夹的内容如下图所示：
  
    <img width="200" alt="image" src="https://user-images.githubusercontent.com/35217413/229447375-4032af4b-ad7c-4c1c-9527-5ef930fbcb4e.png">
    
2. 打开js文件夹中的config.js文件，修改其中的appId配置，appId配置可以在顶象控制台应用管理或应用配置模块获取

    <img width="839" alt="image" src="https://user-images.githubusercontent.com/35217413/229447844-ba352fb9-d1c4-4023-90d8-a869913e6850.png">

3. 修改完毕后点击保存。


### captchaJavaDemo 

文件夹说明 ：模拟了后台业务流程接入验证码的场景，主要是模拟登录场景下的验证码token验证，对应业务请求携带验证码安全凭据和验证码安全凭据核验两个步骤

使用流程 ：

1. 下载该文件夹到电脑中，下载完成后，文件夹的内容如下图所示：

  ![image](https://user-images.githubusercontent.com/35217413/229449613-af0b8bbf-6cc2-4596-8b22-a984d584af1c.png)
  
2. 将项目导入至IDEA中，等待IDEA把项目加载完毕，编辑CaptchaService类，修改其appId和appSecret的配置

  ![image](https://user-images.githubusercontent.com/35217413/229450179-78d2986f-f41d-4220-8749-a852477c3508.png)

3. 修改后点击保存，在IDEA中运行这个springboot项目。运行成功如下图所示：

  ![image](https://user-images.githubusercontent.com/35217413/229451094-56d82fd5-749e-4a1a-b61b-e78a45240cbb.png)


### 打开index.html

1. 在浏览器中打开 captchaUIdemo 文件夹中的index.html文件，如下图所示：
  
  ![image](https://user-images.githubusercontent.com/35217413/229451625-6216c502-534c-47ce-bf08-b1860c5e7506.png)

2. 输入用户名和密码后，点击登录，会弹出验证码进行验证

  ![image](https://user-images.githubusercontent.com/35217413/229451780-42aad34f-f097-492d-b929-97925ac80ac0.png)

3. 验证通过后，会提示登录成功

  ![image](https://user-images.githubusercontent.com/35217413/229451893-694228db-87d8-441d-a92d-857c3af173d4.png)


注意：其中两个项目中的appid都需要修改，appid和appsecret可以通过顶象控制台的产品管理进行获取。


## 用户集成手册
  
  其他各个语言和各个端的详细配置和接入流程可以参考顶象官网验证码用户手册：https://www.dingxiang-inc.com/docs/detail/captcha

# captcha-demo
顶象验证码Demo
- [captcha-demo](#captcha-demo)
  * [1 客户端集成](#1-客户端集成)
    + [1.1 web](#11-web)
    + [1.2 Android](#12-android)
    + [1.3 IOS](#13-ios)
    + [1.4 微信小程序](#14-微信小程序)
      - [1.4.1 插件式接入](#141-插件式接入)
      - [1.4.2 跳转式接入](#142-跳转式接入)
    + [1.5 支付宝小程序](#15-支付宝小程序)
    + [1.6 百度小程序](#16-百度小程序)
  * [2 客户端通用参数说明](#2-客户端通用参数说明)
    + [2.1 常规参数](#21-常规参数)
    + [2.2 个性化参数](#22-个性化参数)
    + [2.3 高级参数](#23-高级参数)
    + [2.4 基础类型](#24-基础类型)
    + [2.5 方法](#25-方法)
    + [2.6 事件](#26-事件)
    + [2.7 自定义样式](#27-自定义样式)
    + [2.8 自定义语言](#28-自定义语言)
  * [3 服务端集成](#3-服务端集成)
    + [3.1 接口参数说明](#31-接口参数说明)
    + [3.2 Java](#32-java)
    + [3.3 PHP](#33-php)
    + [3.4 C#](#34-c)
    + [3.5 Python](#35-python)
    + [3.6 Node.js](#36-nodejs)
    + [3.7 Golang](#37-golang)

验证码工作流程
![image](https://user-images.githubusercontent.com/35217413/225861273-eb1c5670-e64e-4450-b1f0-c03cb7968d62.png)

## 1 客户端集成
### 1.1 web

环境要求: 兼容IE8+，Chrome，Firefox，360浏览器，QQ浏览器等主流浏览器。

引入 JS
~~~javascript
<script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/v5/index.js" crossorigin="anonymous" id="dx-captcha-script"></script>
~~~

- Javascript 示例

  假设页面上有一个 `<div id="c1"></div>`，则可以像下面这样初始化验证码。
  ~~~javascript
  var myCaptcha = _dx.Captcha(document.getElementById('c1'), {
      appId: 'appId', //appId，在顶象控制台应用管理或应用配置模块获取
      apiServer: 'https://xxx.dingxiang-inc.com',  // apiServer域名地址在控制台页面->无感验证->应用管页面左上角获取，必须填写完整包括https://。
      success: function (token) {
        // console.log('token:', token)
        // 获取验证码token，用于后端校验，注意获取token若是sl开头的字符串，则是前端网络不通生成的降级token,请检查前端网络及apiServer地址。
      }
  })
  ~~~
  初始化完成后，验证码组件会被插入到<div id="c1"></div> 中。

- React 示例

  假设页面上有一个 `<div id="demo"></div>`，则可以像下面这样初始化验证码：
  // 类组件使用componentDidMount
  ~~~javascript
  useEffect(() => {
      _dx.Captcha(document.getElementById('demo'), {
        appId: 'appId', //appId，在控制台应用管理或应用配置模块获取
        apiServer: 'https://xxx.dingxiang-inc.com', // apiServer域名地址在控制台页面->无感验证->应用管页面左上角获取，必须填写完整包括https://。
        success: token => {
          // 获取验证码token，用于后端校验，注意获取token若是sl开头的字符串，则是前端网络不通生成的降级token,请检查前端网络及apiServer地址。
          console.log(token);
        }
      });
  }, [])
  ~~~
  可点击查看 [React完整示例代码](https://codesandbox.io/s/sweet-paper-qdflf7?file=/src/App.js)，初始化完成后，验证码组件会被插入到 `<div id="demo"></div>`中。

- Vue 示例

  假设页面上有一个 `<div ref="demo"></div>`，则可以像下面这样初始化验证码：
  ~~~javascript
  mounted() {
    _dx.Captcha(this.$refs.demo, {
      // appId, 在控制台应用管理或应用配置模块获取
      appId: "appId",
      apiServer: 'https://xxx.dingxiang-inc.com', // apiServer域名地址在控制台页面->无感验证->应用管页面左上角获取，必须填写完整包括https://。
      success: token => {
        // 获取验证码token，用于后端校验，注意获取token若是sl开头的字符串，则是前端网络不通生成的降级token,请检查前端网络及apiServer地址。
        console.log(token);
      }
    });
  }
  ~~~
  可点击查看 [Vue完整示例代码](https://codesandbox.io/s/crazy-williams-195gl?file=/src/components/HelloWorld.vue) ，初始化完成后，验证码组件会被插入到 `<div ref="demo"></div>`中。

### 1.2 Android

- 环境要求
  | 条目        | 说明                                                         |
  | ----------- | ------------------------------------------------------------ |
  | 开发目标    | Android 4.0+， 推荐5.0+                                      |
  | 开发环境    | Android Studio, 推荐 Gradle 7.0.2, Android Gradle plugin 7.0.4 |
  | CPU架构     | armeabi-v7a arm64-v8a x86 x86_64                             |
  | SDK三方依赖 | 无                                                           |
  > 顶象的服务器基于安全考虑已经禁用不安全的TLS协议，包括Android 4.0-4.4需要的`Tls1.0`、`Tls1.1`。 Android 4.1-4.4的`webview`组件支持`Tlsv1.2`，但并未默认启动。 若您需要支持Android 4.0-4.4，请联系顶象技术以获取支持。 目前（2023-02-03）， android 5.0及以上占比为99.3%。

- 集成SDK

  1.导入依赖

    - 无感验证需要同时集成[dx-captcha SDK](https://cdn.dingxiang-inc.com/files/47299/dx-captcha-v5.1.5r.f872c30c.aar)和 [dx-risk SDK](https://cdn.dingxiang-inc.com/files/39072/dx-risk-v6.1.7.1r.e53c8e0c.aar)

  2.Android Studio 集成

    - 请将aar文件集成到项目对应的libs下（x.x.x表示为sdk版本号）
    - [点击下载Demo](https://cdn.dingxiang-inc.com/files/39072/dx-captcha-demo-v5.1.2r.2b9af507.zip)(仅做代码配置演示使用，其中appId请在顶象后台申请，SDK需要替换为链接中下载的SDK)
    - [点击下载React Native 接入文档](https://cdn.dingxiang-inc.com/files/10062/DXCaptcha-SDK-for-Android-RN.pdf)
    - [点击下载React Native Demo](https://cdn.dingxiang-inc.com/files/10062/dx-captcha-android-demo-react-native.7z)(仅做代码配置演示使用，其中appId请在顶象后台申请，SDK需要替换为链接中下载的SDK)

    Demo工程结构如下

      ```
      .
      ├── app
      │   ├── build.gradle
      │   ├── demo.p12
      │   ├── libs
      │   │   ├── dx-captcha-v5.1.3r.3e8bc1ca.aar
      │   │   └── dx-risk-v6.1.7.1r.e53c8e0c.aar
      │   ├── proguard-rules.pro
      │   └── src
      │       └── main
      ├── build.gradle
      ├── gradle
      ├── gradle.properties
      ├── gradlew
      ├── gradlew.bat
      ├── local.properties
      └── settings.gradle
      ```

    在该Module的build.gradle中如下配置:

      ```
      android {

        packagingOptions {
            doNotStrip "**/lib*Risk*.so"
            doNotStrip "**/lib*Captcha*.so"
        }
      }

      dependencies {
          implementation fileTree(dir: 'libs', include: ['*.aar'])
      }
      ```

  （可选）添加SDK所需权限

      ```xml
      <uses-permission android:name="android.permission.INTERNET"/>
      <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
      <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
      <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
      <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
      <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
      <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
      <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
      <uses-permission android:name="android.permission.BLUETOOTH"/>
      ```

  3.如果有使用Proguard混淆，请添加如下配置，保证SDK的相关代码不被混淆:

    ```
    -keep class com.dx.mobile.captcha.**{*;}
    -keep class org.json.**{*;}

    -dontwarn com.dx.mobile.**
    -dontwarn *.com.dx.mobile.**
    -dontwarn *.com.mobile.strenc.**
    -keep class com.dx.mobile.risk.**{*;}
    -keep class com.security.inner.**{*;}
    -keep class *.com.dx.mobile.**{*;}
    -keep class *.com.mobile.strenc.**{*;}
    }
    ```
- 初始化

  1.xml方式接入`无感验证`组件

    ```xml
    <com.dx.mobile.captcha.DXCaptchaViewV5
        android:id="@+id/dxCaptcha"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
    ```

  2.初始化，需传`appId`（控制台无感验证-应用管理获取）

    ```java
    DXCaptchaView dxCaptcha = (DXCaptchaView) findViewById(R.id.dxVCodeView);
    dxCaptcha.init(appId);
    ```

  3.自定义配置（可选）

    ```java
    Map<String, Object> config = new HashMap<String, Object>();

    // 二级属性
    Map<String, Object> customStyle = new HashMap<String, Object>();
    customStyle.put("bgColor", "#FFFFFF");

    // 二级属性
    Map<String, Object> customLanguage = new HashMap<String, Object>();
    customLanguage.put("init_inform", "shwo_拖动一下");

    // 一级属性
    config.put("customStyle", customStyle);
    config.put("customLanguage", customLanguage);

    //默认不加是中文，添加以下配置语言调整为英文
    //config.put("language", "en");

    // 是否开启无感验证，默认为开启状态
    config.put("cacheStorage", true);

     //若您是SaaS客户，keyURL填控制台->无感验证->应用管理左上角显示的接入域名
     //若您是私有化客户，keyURL填自己的服务器地址（指将应用在本地服务器部署的地址）
    config.put("keyURL", "http://ip:port/udid/m1");

    //若您是SaaS客户，以下参数无需填写，不可自定义
    //若您是私有化客户，需填写以下私有化配置参数，如有疑问直接沟通技术支持。
    // config.put("logoServer", "http://ip:port/logo");           // 私有化必填，自定义无感验证logo服务器地址
    // config.put("ua_js", "http://ip:port/xxxxxx/greenseer.js"); // 私有化必填，自定义ua脚本地址
    // config.put("captchaJS", "http://ip:port/xxxxxx/index.js"); // 私有化必填，自定义无感验证js地址
    // config.put("keyBackup", true);                             // 私有化必填，该项设置为true时，私有化的场景下SDK会同时上报一份采集到的客户端运行信息至顶象云服务，以帮助顶象了解SDK的稳定运行情况；设置为false则关闭同步
    // config.put("isSaaS", false);                               // 私有化必填，不走SaaS
    // config.put("corsBaseURL", "http://xxxxxx");                // 私有化选填, 自定义请求源地址, 与服务端跨域配置一致, 防止由于服务端跨域配置导致验证码无法加载

    dxCaptcha.initConfig(config);
    ```

  4.开始加载“无感验证”组件

    ```java
    dxCaptcha.startToLoad(new DXCaptchaListener() {

        @Override
        public void handleEvent(View view, DXCaptchaEvent event, Map<String, String> args) {

            switch(event) {
                case DXCAPTCHA_SUCCESS: // 验证成功的回调
                    String token = args.get("token"); // 成功时会传递验证码token参数，用于后端校验，注意获取token若是sl开头的字符串，则是前端网络不通生成的降级token，请检查前端网络及apiServer地址。
                    Log.i(TAG, "Verify Success. token: " + token);
                    break;
                case DXCAPTCHA_FAIL:	  // 验证失败回调
                    Log.i(TAG, "Verify Failed.");
                    break;
                default:
                    break;
            }
        }
    });
    ```

  5.使用结束，调用`destroy`方法释放资源

    ```java
    dxCaptcha.destroy();
    ```
- 参数说明

  初始化有若干参数，详情可查阅客户端通用说明章节

  其中在 `Android SDK` 中

    - `style` 固定为 `embed` ，若指定为其他值会被忽略
    - `width` 参数会被忽略，实际值会根据 `frame` 进行自适应调整
    - `success` 和 `fail` 回调参数会被忽略，请在 `delegate` 中处理回调
    - `constID_js` 、 `constIDServer` 和 `constID_options` 配置不生效，无需配置
    
- 事件说明

  验证码SDK `v5.1.5r`对接口进行优化, 新增`startToLoad`的重载,允许监听额外的[事件](https://www.dingxiang-inc.com/docs/detail/captcha#event)回调.

  ```java
  /**
   * extraEvents 额外监听的事件
   */
  public void startToLoad(DXCaptchaListener listener, String ... extraEvents);

  // sdk 内部已经监听如下事件, 您无需添加到extraEvents中:
  // "success",
  // "fail",
  // "render",
  // "loadFail",
  // "dragStart",
  // "dragEnd",
  // "verify",
  // "verifyDone",
  // "verifyFail",
  // "passByServer",
  // "loadTooMuch",
  // "loadData",
  // "ready",
  // "renderLoading",
  // "twoStepEnd",
  // "twoStepStart",
  // "verifySuccess",
  ```

  | event     | 旧版枚举(v5.2前, 不推荐) | 描述             | 平台    | 备注 |
  | --------- | ------------------------ | ---------------- | ------- | ---- |
  | "success" | DXCAPTCHA_SUCCESS        | 验证成功(整体)   | android |      |
  | "fail"    | DXCAPTCHA_FAIL           | 验证失败（整体） | android |      |

  其余的[事件列表,请见这里](https://www.dingxiang-inc.com/docs/detail/captcha#event), 每个事件都额外对应一个before, after事件. 比如: `render`相关事件, 就包含`"rander", "before_rander", "after_rander"` 三个事件, 对应旧版枚举`DXCAPTCHA_RENDER, DXCAPTCHA_BEFORE_RENDER, DXCAPTCHA_AFTER_RENDER`

  **注**：

  - `Android SDK` 中不触发 `dragging` 事件
  - `Android SDK` 中不触发 `show/hide` 系列的事件

### 1.3 IOS

(v1.5.x）
- 环境要求

  | 条目     | 说明                                                         |
  | -------- | ------------------------------------------------------------ |
  | 兼容平台 | iOS 8.0+                                                     |
  | 开发环境 | XCode 4.0 +                                                  |
  | CPU架构  | armv7, arm64, i386, x86_64                                   |
  | SDK依赖  | libz, libresolv, libc++ , SystemConfiguration.framework , CoreLocation.framework , CoreTelephony.framework |

- 集成SDK

  1.集成无感验证
    需要同时集成风控SDK5.0.0或以上版本（如项目本身已接入顶象风控且确认版本无误则可直接跳过此步），下载顶象风控 [dx-risk SDK](https://cdn.dingxiang-inc.com/files/39072/dx-risk-iOS-v6.0.8r.16b681cc.zip) ，解压得以下几个文件

      - dx-risk-iOS-x.x.x-xxxxxxx目录 DXRisk sdk
        - DXRisk.framework 不带idfa获取逻辑的Dynamic Library Framework
        - DXRiskWithIDFA.framework 带idfa获取逻辑的Dynamic Library Framework
        - DXRiskStatic.framework 不带idfa获取逻辑的Static Library Framework
        - DXRiskStaticWithIDFA.framework 带idfa获取逻辑的Static Library Framework

     若在项目中添加`DXRisk.framework`或者`DXRiskWithIDFA.framework`其中之一，选择`Target` -> `General`，在`Frameworks,Libraries,and Embedded Content`中，将`DXRisk.framework`或者`DXRiskWithIDFA.framework` 对应的 Embed 切换到Embed & Sign。如下图：
      
     ![image](https://user-images.githubusercontent.com/35217413/225843827-53df0ee0-93d3-4d81-9caf-7bb1a8d09e5b.png)
    若在项目中添加`DXRiskStatic.framework`或者`DXRiskStaticWithIDFA.framework`其中之一，需要在`Build Settings -> Other Linker Flags` 设置 `-ObjC` 如下图：
    
     ![image](https://user-images.githubusercontent.com/35217413/225843923-b933b5b5-c02c-430f-9757-c61d88d4b6a1.png)
     
  2.下载顶象无感验证 [dx-captcha SDK ](https://cdn.dingxiang-inc.com/files/39072/DingxiangCaptchaSDK_iOS_1.7.0_4853a52.zip),解压得到 `DXCaptchaSDK` 文件夹，内含以下文件
  
      - `DXCaptchaSDK` CaptchaFramework
        - `DXCaptcha.bundle` 无感验证资源包
        - `DingxiangCaptchaSDK.framework` Dynamic Library Framework
        - `DingxiangCaptchaSDKStatic.framework` Static Library Framework
      - `DXCaptchaDemo` 集成demo

  3.将 `DingxiangCaptchaSDK.framework` 或者 `DingxiangCaptchaSDKStatic.framework` 文件夹以及 `DXCaptcha.bundle` 资源文件一起拖入工程根目录
    - 若在项目中添加`DingxiangCaptchaSDK.framework`，则需要选择`Target` -> `General`，在`Frameworks，Libraries，and Embedded Content`中，将`DingxiangCaptchaSDK.framework` 对应的 Embed 切换到Embed & Sign
    - 若在项目中添加`DingxiangCaptchaSDKStatic.framework`，需要在`Build Settings -> Other Linker Flags` 设置 `-ObjC` 。
    - [点击下载无感验证iOS Demo](https://cdn.dingxiang-inc.com/files/39072/DXCaptchaDemo.zip)
    - [点击下载React Native 接入文档](https://cdn.dingxiang-inc.com/files/10062/DXCaptcha-SDK-for-iOS-RN.pdf)
    - [点击下载React Native demo](https://cdn.dingxiang-inc.com/files/10348/ReactNative调用原生DXCaptcha-Demo_iOS.zip)(仅做代码配置演示使用，其中appId请在顶象后台申请，SDK需要替换为链接中下载的SDK)

  4.配置打包脚本
    以下的操作仅限导入`DingxiangCaptchaSDK.framework动态库`
    此步骤主要是解决上传Store架构不符合的问题，如项目中已配置过Carthage或有其他相关的打包Framework调整脚本，可略过此步自行调整
    
    - 选择`Target` -> `Build Phases`，点击`+`按钮，选择`New Run Script Phase`, 添加如下脚本：


    ```objective-c
    APP_PATH="${TARGET_BUILD_DIR}/${WRAPPER_NAME}"

    export PATH=$PATH:/usr/libexec
    # This script loops through the frameworks embedded in the application and
    # removes unused architectures.
    find "$APP_PATH" -name '*.framework' -type d | while read -r FRAMEWORK; do
        FRAMEWORK_EXECUTABLE_NAME=$(PlistBuddy -c "Print :CFBundleExecutable" "$FRAMEWORK/Info.plist")
        FRAMEWORK_EXECUTABLE_PATH="$FRAMEWORK/$FRAMEWORK_EXECUTABLE_NAME"
        echo "Executable is $FRAMEWORK_EXECUTABLE_PATH"

        EXTRACTED_ARCHS=()

        for ARCH in $ARCHS; do
            echo "Extracting $ARCH from $FRAMEWORK_EXECUTABLE_NAME"
            lipo -extract "$ARCH" "$FRAMEWORK_EXECUTABLE_PATH" -o "$FRAMEWORK_EXECUTABLE_PATH-$ARCH"
            EXTRACTED_ARCHS+=("$FRAMEWORK_EXECUTABLE_PATH-$ARCH")
        done

        echo "Merging extracted architectures: ${ARCHS}"
        lipo -o "$FRAMEWORK_EXECUTABLE_PATH-merged" -create "${EXTRACTED_ARCHS[@]}"
        rm "${EXTRACTED_ARCHS[@]}"

        echo "Replacing original executable with thinned version"
        rm "$FRAMEWORK_EXECUTABLE_PATH"
        mv "$FRAMEWORK_EXECUTABLE_PATH-merged" "$FRAMEWORK_EXECUTABLE_PATH"

    done
    ```
- 初始化

    假设在 `ViewController` 中添加无感验证，首先引入头文件

    ```objective-c
    #import <DingxiangCaptchaSDK/DXCaptchaView.h>
    #import <DingxiangCaptchaSDK/DXCaptchaDelegate.h>
    ```

    然后实现 `DXCaptchaDelegate` 协议中的 `captchaView:didReceiveEvent:arg:` 方法，以接收验证结果回调

    ```objective-c
    @interface ViewController () <DXCaptchaDelegate>

    @end

    @implementation ViewController

    - (void) captchaView:(DXCaptchaView *)view didReceiveEvent:(DXCaptchaEventType)eventType arg:(NSDictionary *)dict {
        switch(eventType) {
            case DXCaptchaEventSuccess: { // 验证成功的回调
                NSString *token = dict[@"token"]; // 成功时会传入验证码token参数，用于后端校验，注意获取token若是sl开头的字符串，则是前端网络不通生成的降级token，请检查前端网络及apiServer地址。
                [[[UIAlertView alloc]initWithTitle:@"Verify Success" message:token delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
                break;
            }
            case DXCaptchaEventFail: // 验证失败的回调
                [[[UIAlertView alloc]initWithTitle:@"Verify Failed" message:@"DXCaptcha Verify Failed!" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
                break;
            default:
                break;
        }
    }

    @end
    ```

    最后创建无感验证组件，需要传入 `appId`， `delegate`(验证回调的代理)， `frame`(位置及尺寸)

    ```objective-c
    CGRect frame = CGRectMake(self.view.center.x - 150, self.view.center.y - 100, 300, 200);
    DXCaptchaView *captchaView = [[DXCaptchaView alloc] initWithAppId:@"your appId" delegate:self frame:frame];
    [self.view addSubview:captchaView];
    ```

    如果除 `appId` 以外还需要配置其他的初始化参数，可调用 `initWithConfig:delegate:frame` 接口，Saas客户参考以下配置

    ```objective-c
    NSDictionary *config = @{@"appId": @"your appId", @"language": @"en"};//在控制台应用管理可查看appId，language默认不传是中文，按此设置为英文
    NSDictionary *customLanguageConfig = @{@"pass_by_server": @"验证成功"}; //可设置参数参考文档
    [config setObject:customLanguageConfig forKey:@"customLanguage"];
    DXCaptchaView *captchaView = [[DXCaptchaView alloc] initWithConfig:config delegate:self frame:frame];
    ```

    私有化客户配置参数示例代码如下，apiServer为必填项，ua_js、captchaJS、keyURL、keyBackup如需手动设置请咨询技术支持

    ```objective-c
    NSMutableDictionary *config = [NSMutableDictionary dictionary];
    //私有化参数配置
    [config setObject:@"your appId" forKey:@"appId"];  //在控制台应用管理可查看appId
    [config setObject:@"your apiServer" forKey:@"apiServer"];  //私有化必填，无感验证自定义本地服务器地址
    [config setObject:@"your logoServer" forKey:@"logoServer"]; //私有化必填，自定义无感验证logo服务器地址
    [config setObject:@"your ua_js" forKey:@"ua_js"];  //私有化必填，自定义ua脚本地址
    [config setObject:@"your captchaJS" forKey:@"captchaJS"];  //私有化必填，自定义无感验证js地址
    [config setObject:@NO forKey:@"isSaaS"];  //私有化必填，不走SaaS
    [config setObject:@"your riskServerAddress" forKey:@"keyURL"];  //私有化必填，设备指纹私有化服务器地址
    // [config setObject:@"http://xxxxxx" forKey:@"corsBaseURL"];  // 私有化选填, 自定义请求源地址, 与服务端跨域配置一致, 防止由于服务端跨域配置导致验证码无法加载

    [config setObject:@YES forKey:@"keyBackup"];  //私有化必填，该项设置为true时，私有化的场景下SDK会同时上报一份采集到的客户端运行信息至顶象云服务，以帮助顶象了解SDK的稳定运行情况；设置为false则关闭同步

    DXCaptchaView *captchaView = [[DXCaptchaView alloc] initWithConfig:config delegate:self frame:frame];
    ```
- 参数说明

    初始化有若干参数，详情可查阅客户端通用参数说明

    其中在 `iOS SDK` 中

    - `style` 固定为 `embed` ，若指定为其他值会被忽略
    - `width` 参数会被忽略，实际值会根据 `frame` 进行自适应调整，建议尺寸为`300x200`
    - `success` 和 `fail` 回调参数会被忽略，请在 `delegate` 中处理回调
    - `constID_js` 、 `constIDServer` 和 `constID_options` 配置不生效
    - 增加 `userId` 参数，用于传递给 `ConstID` 模块作用户标识
    - 增加 `captchaJS` 参数，用于设置私有化验证码脚本
    - 增加 `keyBackup` 参数，用于设置是否备份设备指纹私有化
    - 增加 `keyURL` 参数，用于设置设备指纹私有化服务器地址

- 事件说明

    回调的事件类型，请查阅客户端通用参数说明及 `DXCaptchaDelegate.h` 头文件

    注：iOS SDK 中不触发 `show/hide` 系列的事件。如SDK无效且运行过程中出现`NOT FOUND DXRISK-SDK`日志，请检查是否遗漏顶象风控SDK的集成

### 1.4 微信小程序

#### 1.4.1 插件式接入

  1）添加插件
    
  用管理员身份登录微信公众平台，请使用需要接入小程序的相关账号（微信公众平台采用不同账号区分，公众号的后台和小程序的后台分别为不同账号），依次点击：设置-第三方服务-添加插件，然后输入关键字“顶象”搜索，选择“顶象验证码”进行申请，申请成功后即可开始接入，如下图所示：
    ![image](https://user-images.githubusercontent.com/35217413/225851835-c12660ad-0deb-403f-8564-8a1299b519d1.png)
   
    
  2）获取密钥
    
  未注册用户可在顶象官网进行账号注册，创建应用获取应用密钥AppID和AppSecret。 已注册用户，可直接在顶象控制台->无感验证->应用管理页面获取对应的AppID和AppSecret。

  3）集成插件
  
  声明插件：在app.json中声明插件
  ```
  {
    "plugins": {
      "captcha": {
        "version": "1.3.2", //验证码微信小程序插件版本号，后续更新只需更改版本号
        "provider": "wxbf8483dfc5ac6817" //唯一值，小程序插件id，不可更改
      }
    }
  }
  ```
  在页面.json中引入自定义组件
  ```
  {
    "usingComponents": {
      "basic": "plugin://captcha/basic",
      "oneclick": "plugin://captcha/oneclick", //请注意⚠️，1.3.*版本中不支持oneclick，可以省略掉本行内容
    }
  }
  ```
 
 4）使用插件
  
  一、点击式（**请注意**：1.3.x版本不支持`oneclick`）

    在页面.wxml中使用插件

    ```
    <oneclick bindsuccess='captchaSuccess' bindhide='captchaHide' oneclickReload='{{captchaReload}}' captchaShow='{{captchaShow}}' options='{{options}}'/>
    <button bindtap='login'>登陆</button>
    ```

    在页面.js中监听事件

    ```
    Page({
      data: {
        options: {
            appId: '这里填写你在顶象官网申请到的appId', //控制台应用管理页面进行获取
            style: 'oneclick'
        },
        captchaShow: false,
        captchaReload: false
      },

      login: function () {
        // captchaReload用来重置验证码
        this.setData({
          captchaReload: true
        })
      },

      // 验证码成功回调
      captchaSuccess: function (data) {
        console.log('验证码成功回调token:', data.detail)  //获取验证码token，用于后端验证码校验
      },

      // 验证码关闭回调
      captchaHide: function () {
        console.log('captcha_hide')
           this.setData({
             captchaShow: false
        })
      }
    })
    ```

  二、弹出式 在页面.wxml中使用插件

    ```
    <basic bindsuccess='captchaSuccess' bindhide='captchaHide' captchaReload='{{captchaReload}}' captchaShow='{{captchaShow}}' options='{{options}}'/>
    <button bindtap='login'>登陆</button>
    ```

    在页面.js中监听事件

    ```
    Page({
      data: {
        options: {
            appId: '这里填写你在顶象官网申请到的appId',  //控制台应用管理页面进行获取
            style: 'popup'
        },
        captchaShow: false,
        captchaReload: false
      },

      login: function () {
        // captchaShow 用于弹出验证码
        // captchaReload 用于重置验证码
        this.setData({
         captchaReload: true,
         captchaShow: true
        })
      },

      // 验证码成功回调
      captchaSuccess: function (data) {
        // 验证成功返回的 token
        console.log('验证码成功回调token:', data.detail) //获取验证码token，用于后端验证码校验
        this.setData({
         captchaShow: false
        })
      },

      // 验证码关闭回调
      captchaHide: function () {
       console.log('captcha_hide')
       this.setData({
         captchaShow: false
       })
      }
    })
    ```

  5）小程序接入问题排查及注意事项
  小程序侧有以下注意事项：

  - 首次触发验证码验证方式只支持滑块验证码；
  - 首次验证不通过，二次触发验证码验证方式只支持图文点选；
  - 需要在控制台->无感验证->应用管理，确认验证类型，如无权限请联系技术支持。


#### 1.4.2 跳转式接入

1）关联小程序

  用管理员身份登录微信公众平台，请使用需要公众号的相关账号（微信公众平台采用不同账号区分，公众号的后台和小程序的后台分别为不同账号），依次点击：小程序-小程序管理-添加-关联小程序，然后输入小程序AppID“wx82db4a59175bdfdf”并搜索，如下图所示：

  ![image](https://user-images.githubusercontent.com/35217413/225852065-9dbedb43-085e-4c3e-95b1-900afe388b7c.png)
  ![image](https://user-images.githubusercontent.com/35217413/225852108-d7f039a0-15fb-44dd-8589-87a9525f0e6c.png)

2）获取密钥

  未注册用户可在顶象官网进行账号注册，创建应用获取应用密钥AppID和AppSecret。 已注册用户，可直接在顶象控制台->无感验证->应用管理页面获取对应的AppID和AppSecret。

3）小程序集成

  1.通过navigator组件跳转至顶象验证码。

  ```.wxml
  <navigator target="miniProgram"
    app-id="wx82db4a59175bdfdf" //唯一值，小程序插件id，不可更改
    path="/pages/captcha/captcha"
    extra-data="{{options}}">
      <button>登陆</button>
  </navigator>
  Page({
        data: {
          options: {
            appId: '这里填写在顶象官网申请到的appId',  //控制台应用管理页面进行获取
            customStyle: {
              panelBg: '',
              captchaBgColor: ''
            }
          }
        }
  })
  ```

  参数说明：

  | 参数        | 值     | 说明                                                         |
  | ----------- | ------ | ------------------------------------------------------------ |
  | appId       | String | 这里填写在顶象官网申请到的appId，注意必须填写正确，否则会报：功能无法使用 |
  | customStyle | Object | 自定义样式对象，panelBg为整个页面的background属性，captchaBgColor为验证码的background-color属性 |
  
  2.在顶象验证码中验证，图片如下所示：
  
  ![image](https://user-images.githubusercontent.com/35217413/225853170-7e907353-c86d-4639-9770-99ee71b10f24.png)

  3.验证成功后，验证结果会在调用方app.js中顶onShow生命周期方法中取到。

  ```
  if (options.scene === 1038 && options.referrerInfo.appId === 'wx82db4a59175bdfdf') {
        const result = options.referrerInfo.extraData;
        if (result) {
          console.log('返回结果:', result)
        } else {
          // 用户点击右上角关闭了验证码
        }
  }
  ```

  验证结果说明：

    | 参数    | 值      | 说明                               |
    | ------- | ------- | ---------------------------------- |
    | success | Boolean | 验证成功                           |
    | token   | String  | 验证成功才有：token                |
    | msg     | String  | 验证失败或参数不合法才有：错误信息 |
  
  [微信小程序更新记录](https://www.dingxiang-inc.com/docs/detail/captcha-xcx-changelog)

### 1.5 支付宝小程序

1）添加订购插件

  [点击订购](https://open.alipay.com/plugin/order-page?serviceCode=MP2021032200100382) （订购页面）

  ![image](https://user-images.githubusercontent.com/35217413/225854018-59d04344-9b51-44b1-a5d3-1b919bd2ee6d.png)

2）获取密钥

  未注册用户可在顶象官网进行账号注册，创建应用获取应用密钥AppID和AppSecret。

3）集成插件

  1.在app.json中声明插件（目前小程序仅支持滑动验证，请确保配置正确✅）

  ```json
    "plugins": {
      "captcha": {
        "version": "*",
        "provider": "2021002132671964"
      }
    },
  ```

  2.在页面.json 如：index.json 下引入插件

  ```js
  {
    "usingComponents": {
      "basic":"plugin://captcha/basic",
      "oneclick": "plugin://captcha/oneclick"
    }
  }
  ```

4 ）使用插件

  **一 弹出式**

  在 页面 .axml 中使用

  ```vue
   <view class="captcha">
      <basic onSuccess='captchaSuccess' onHide='captchaHide' captchaReload='{{captchaReload}}' 		captchaShow='{{captchaShow}}' options='{{options}}' />
    </view>
    <view >
      <button type="primary" onTap='toLogin'>登录</button>
    </view>
  ```

  在.js脚本中监听事件

  ```javascript
  Page({
    data:{
      options:{},
      captchaShow:false,
      captchaReload:false,
    },
    onLoad() {
      this.setData({
        options:{
          appId:'这里填写你在顶象官网申请到的appId',
          style: 'popup', //弹出式
        },
      })
    },
    onReady() {
      // 页面加载完成
    },
    onShow() {
      // 页面显示
    },
    toLogin(){
      // captchaShow 弹出验证码
      // captchaReload 用于重置验证码
      this.setData({
        captchaShow: true,
        captchaReload: true,
      })
    },
     // 验证码成功回调
    captchaSuccess:function(token){
      console.log('成功',token);
      this.setData({
       captchaShow: false，
       captchaReload: false, //配置后可重置验证码
      })
      // 在此执行验证成功后的任务
    },
    // 验证码关闭回调
    captchaHide: function(){
      this.setData({
        captchaShow: false,
      })
      console.log('关闭回调了')
    }
  });
  ```

  **二 点击式**

  在 页面 .axml 中使用

  ```vue
    <view class="captcha">
      <oneclick onSuccess='captchaSuccess' onHide='captchaHide' oneclickReload='{{captchaReload}}' captchaShow='{{captchaShow}}' options='{{options}}'/>
    </view>
      <view >
      <button type="primary" onTap='toLogin'>登录</button>
    </view>
  ```

  在.js脚本中监听事件

  ```js
  Page({
    data:{
      options:{},
      captchaShow:false,
      captchaReload:false,
    },
    onLoad() {
      this.setData({
        options:{
          appId:'这里填写你在顶象官网申请到的appId',
          style: 'oneclick', //点击式
        },
      })
    },
    onReady() {
      // 页面加载完成
    },
    onShow() {
      // 页面显示
    },
    toLogin(){
       // captchaReload 用于重置验证码
      this.setData({
        captchaReload: true
      })
    },
     // 验证码成功回调
    captchaSuccess:function(token){
      console.log('成功',token);
       this.setData({
       captchaShow: false，
       captchaReload: false, //配置后可重置验证码
      })
    },
    // 验证码关闭回调
    captchaHide: function(){
      this.setData({
        captchaShow: false
      })
      console.log('关闭回调了')
    }
  });
  ```

5) 支付宝小程序验证码如何采集设备指纹
    
    在`options`中指定`constIDServer: 'https://constid.dingxiang-inc.com/udid/w1'`即可。如果未指定则不会采集。

6) 支付宝小程序注意事项
    
    支付宝小程序暂时仅支持首次滑块验证，请确保在控制台的安全配置仅开启了首次滑块验证。

### 1.6 百度小程序

百度小程序目前通过组件方式接入

1 )获取密钥

  未注册用户可在顶象官网进行账号注册，创建应用获取应用密钥AppID和AppSecret。

2 )复制提供的components.zip 到项目根目录下并解压；

3 )在`pages/index/index.json`中声明引入验证码插件，加入下面的内容：

  ```
  "usingComponents": {
     "basic": "/components/basic/basic",
     "oneclick": "/components/oneclick/oneclick"
  }
  ```

4 )使用插件

  一 弹出式

  .swan 中使用引入组件

  ```
  <basic bindsuccess='captchaSuccess' bindhide='captchaHide' captchaReload='{{captchaReload}}' captchaShow='{{captchaShow}}' options='{{options}}'/>
  ```

  在.js脚本中监听事件

  ```
   Page({
    data: {
      options: {
          appId: '这里填写你在顶象官网申请到的appId',
          style: 'popup'
      },
      captchaShow: false,
      captchaReload: false
    },

    login: function () {
      // captchaShow 用于弹出验证码
      // captchaReload 用于重置验证码
      this.setData({
       captchaReload: true
       captchaShow: true
      })
    },

    // 验证码成功回调
    captchaSuccess: function (token) {
      // 验证成功返回的 token
      console.log('token:', token)
      this.setData({
       captchaShow: false
      })
    },

    // 验证码关闭回调
    captchaHide: function () {
     console.log('captcha_hide')
     this.setData({
       captchaShow: false
     })
    }
  })
  ```

## 2 客户端通用参数说明
### 2.1 常规参数

  初始化时有若干参数，其中 `appId` 是必填的。

  | 参数名                | 必填 | 类型     | 说明                                                         |
  | --------------------- | ---- | -------- | ------------------------------------------------------------ |
  | appId                 | 是   | String   | 当前应用的唯一标识                                           |
  | type                  | 否   | String   | 类型，目前只有一个选项 `basic`，默认值为 `basic`。           |
  | style                 | 否   | String   | 样式，可选项有 `embed`（默认）、`inline`、`popup`、`oneclick` 四种，具体效果可参见[线上demo](https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/demo/)。 |
  | inlineFloatPosition   | 否   | String   | 浮动层位置，仅当 `style` 的值为 `inline` 时有效，可选项有 `up`（默认）、`down` 两种，具体效果可参见[线上demo](https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/demo/)。 |
  | oneClickFloatPosition | 否   | String   | 浮动层位置，仅当 `style` 的值为 `oneclick` 时有效，可选项有 `up`，当`style`值为`oneclick`且不传此参数，默认为弹出形式。 |
  | width                 | 否   | Number   | 控件（滑动条）宽度，单位为像素，默认为 `300`。               |
  | language              | 否   | String   | 语言，可选项有 `cn`（默认，中文）、`en`（英文）。            |
  | success               | 否   | Function | 验证成功时的回调函数，会传入一个参数，值为本次验证的 `token:constId`，接入方需在后续的验证中传回此项值。注：此回调函数包含无感验证成功及滑动验证成功。 |
  | fail                  | 否   | Function | 验证失败时的回调函数，会传入一个参数，值为出错信息。         |

### 2.2 个性化参数

  这部分参数可以控制一些自定义信息。

  | 参数名         | 必填 | 类型   | 说明                                                         |
  | -------------- | ---- | ------ | ------------------------------------------------------------ |
  | customStyle    | 否   | Object | 自定义样式，详情参见[自定义样式说明](https://www.dingxiang-inc.com/docs/detail/captcha#custom-style)。 |
  | customLanguage | 否   | Object | 自定义语言，详情参见[自定义语言说明](https://www.dingxiang-inc.com/docs/detail/captcha#custom-lang)。 |

### 2.3 高级参数

  这部分参数通常而言不需要设置，除非你确切知道它们的含义。

  | 参数名          | 必填 | 类型   | 说明                                                         | 版本      | 平台        |
  | --------------- | ---- | ------ | ------------------------------------------------------------ | --------- | ----------- |
  | ua_js           | 否   | String | 自定义 ua 脚本地址，格式形如 `https://cdn.dingxiang-inc.com/ctu-group/ctu-greenseer/greenseer.js`。 |           |             |
  | constID_js      | 否   | String | 自定义 constID 脚本地址，格式形如 `https://cdn.dingxiang-inc.com/ctu-group/constid-js/index.js`。 |           |             |
  | constIDServer   | 否   | String | 自定义 constID 服务地址，格式形如 `http://constid.dingxiang-inc.com/udid/c.png`。 |           |             |
  | apiServer       | 否   | String | 自定义 API 服务地址，格式形如 `http://123.207.220.181:9090` 或 `https://api.dingxiang-inc.com`。 |           |             |
  | logoServer      | 否   | String | 自定义 无感验证 logo 服务地址，格式形如 `http://123.207.220.181:9090/logo`。 |           |             |
  | protocol        | 否   | String | 请求协议，可选项有 `http:`、`https:`，默认为 `https:`。      |           |             |
  | timeout         | 否   | Number | 超时时间，单位为毫秒，默认值为 6000。注意，只有部分请求可设置超时时间。 |           |             |
  | constID_options | 否   | Object | constID 初始化配置参数，参见 [ConstID 文档](https://www.dingxiang-inc.com/docs/detail/const-id)，如此项中的参数与外部参数冲突（比如其中也定义了 `appId` 且与外部的 `appId` 不同，则以此处的为准。 |           | web         |
  | uid             | 否   | String | 用于标识用户的唯一ID，在后端调用tokenVerify时返回            |           |             |
  | corsBaseURL     | 否   | String | 针对私有化部署客户，当服务端安全策略限制了跨域请求时（CORS），请设置此值。格式形如 `http://cdn.dingxiang-inc.com`。*SAAS客户无需设置*。 | v5.1.3r + | android,ios |

### 2.4 基础类型

  初始化时，如果省略 `type` 参数，或传入的值为 `basic`，则启用基础类型验证码。

  例：

  ```js
  var myCaptcha = _dx.Captcha(document.getElementById('c1'), {
      appId: 'appId',
      type: 'basic', // <-- 指定为"基础类型"，此参数可省略
      style: 'embed', // 可省略
      width: 300 // 可省略
  })
  ```

  参数说明亦可参考参数。
  
### 2.5 方法

  验证码实例具备以下方法：

  - `reload()` 重置当前验证码（**注意！**，请不要在`success`回调里调用`reload`，因为在开启无感验证的情况下会重复调用`success`回调。）

    **示例：**

    ```js
    myCaptcha.reload()
    ```

  - `show()` 显示当前验证码。

    `style` 为 `popup` 的验证码默认是隐藏的，需要接入方根据页面逻辑调用 `show()` 方法将其显示。

    **示例：**

    ```js
    myCaptcha.show()
    ```

  - `hide()` 隐藏当前验证码

    **示例：**

    ```js
    myCaptcha.hide()
    ```

### 2.6 事件

  验证码可以使用以下方式监听事件：

  ```js
  myCaptcha.on('ready', function () {
    // console.log('captcha is ready!')
  })

  myCaptcha.on('verifySuccess', function (token) {
    // console.log('token is: ' + token)
  })

  myCaptcha.on('hide', function () {
    // console.log('验证码控件被隐藏了。')
  })
  ```

  基础类型的验证码支持以下事件：

  初始化阶段

  验证码在初始化阶段按顺序触发以下事件：

  | 事件名       | 说明                                                         |
  | ------------ | ------------------------------------------------------------ |
  | render       | 渲染事件，验证码开始渲染时触发                               |
  | passByServer | 【无感验证】通过，服务端判定本次验证可直接通过，无需用户交互。如果此事件触发，则验证码直接显示为验证通过状态，将没有后面的`用户交互阶段`。此事件带一个参数 `token`。 |
  | ready        | 验证码准备就绪，可以接受用户输入时触发。注意：【无感验证】通过则不会触发此事件，直接进入passByServer事件 |

  如果加载失败，则会按顺序触发以下事件：

  | 事件名   | 说明                           |
  | -------- | ------------------------------ |
  | render   | 渲染事件，验证码开始渲染时触发 |
  | loadFail | 加载失败时触发                 |

  用户交互阶段

  | 事件名        | 说明                                                       |
  | ------------- | ---------------------------------------------------------- |
  | dragStart     | 用户开始拖动滑块                                           |
  | dragging      | 用户拖动滑块过程中多次触发                                 |
  | dragEnd       | 用户释放滑块，结束拖动                                     |
  | verify        | 向校验接口提交数据进行校验                                 |
  | verifyDone    | 校验接口已返回数据                                         |
  | verifySuccess | 校验接口已返回数据，且结果为成功，此事件带一个参数 `token` |
  | verifyFail    | 校验接口已返回数据，且结果为失败                           |

  其他事件

  | 事件名 | 说明                 |
  | ------ | -------------------- |
  | show   | 验证码控件显示时触发 |
  | hide   | 验证码控件隐藏时触发 |

  说明：所有事件，都有对应的 `before_*` 事件和 `after_*` 事件，分别在该事件之前和之后触发。

  例如 `load` 事件对应三个事件：`before_load`、`load`、`after_load`。

### 2.7 自定义样式

  如果你熟悉 CSS，你可以直接编写自己的 CSS 来覆盖验证码组件的样式。或者也可以在验证码初始化时传入一个 `customStyle` 参数，对某些样式进行自定义。

  示例

  ```js
    var appId = '【你的 appID】'
    var demo_1 = _dx.Captcha(document.getElementById('demo'), {
      appId: appId,

      // 下面开始自定义样式
      customStyle: {
        bgColor: '#cccc00' // <-- 自定义控件背景色
      },

      success: function (token) {
        window.console && console.log('success, token:', token)
      }
    })
  ```

  参数详情

  `customStyle` 目前支持以下参数，所有参数都是**可选**的：

  | 参数    | 类型   | 说明                                                       |
  | ------- | ------ | ---------------------------------------------------------- |
  | bgColor | String | 控件背景色，CSS 颜色格式，例如 `#ff0000`、`rgb(255, 0, 0)` |

  > 注意：不同类型验证方式支持的自定义样式不同，上面只是一个简单的通用自定义样式属性示例。

  滑动验证自定义样式示例

  ```javascript
  bar: { // 滑块及提示区域
    normalTextColor: 'pink', // bar-inform中 .basic_lang 处的文字颜色 eg：加载中等文字 lang
    normalBgColor: 'blue', // 滑块背景色
    normalBdColor: '' // 滑块边框色
    normalTextColor: 'red', // 滑块字体颜色
    slidingBgColor: 'lime', // 滑动区域的背景颜色
    slidingBdColor: 'red', // 滑动区域的边框颜色
    hoverHideText: true, // hover时隐藏滑块上的提示文字 eg: 拖动左侧滑块至正确缺口 ie8及以下不支持
    successTextColor: 'red' // 验证成功时提示文字的颜色
    successIcon: '', // 验证成功时的图片提示 默认从container里取
    successBgColor: '', // 验证成功时 bar的背景颜色
    successBdColor: '', // 验证成功时 bar的边框颜色
    failBgColor: '', // 验证失败后滑动后区域的背景颜色
    failBdColor: '', // 验证失败后滑动后区域的边框颜色
  }

  bgColor: '', // box的背景颜色 即验证码图片和滑块的容器

  slider: { // 滑块
    normalBgSrc: '', // container.img_slider_normal
    hoverBgSrc: '', // img_slider_hover
    focusBgSrc: '', // img_slider_focus
    loadingBgSrc: '', // img_slider_loading_bg
    errorBgSrc: '', // img_slider_error
    loadingGifSrc: '', // img_slider_loading_gif
  }
  ```


### 2.8 自定义语言

  示例

  ```js
    var appId = '【你的 appID】'
    var demo_1 = _dx.Captcha(document.getElementById('demo'), {
      appId: appId,

      // 下面开始自定义语言
      customLanguage: {
        init_inform: '拖动一下', // <-- 初始化时的提示文案
        slide_inform: '向右向右' // <-- 滑块中的提示文案
      },

      success: function (token) {
        window.console && console.log('success, token:', token)
      }
    })
  ```

  参数详情

  `customLanguage` 目前包含以下语句：

  注意：Android端`load_fail`内容格式为：`"加载失败，<a href=\\\"#\\\">请重试</a>！"`

  | 语句名                  | 参考值                                                       | 说明                 |
  | ----------------------- | ------------------------------------------------------------ | -------------------- |
  | load_fail               | 加载失败，<a href="#">请点击重试</a>！                       | 加载失败             |
  | load_too_much           | 操作太频繁，<a href="#">请点击重试</a>                       | 加载次数过多         |
  | two_step_load_too_much  | 操作太频繁，请重新验证                                       | 二次验证加载次数过多 |
  | loading                 | 加载中...                                                    | 加载中               |
  | pass_by_server          | 顶象无感验证成功                                             | 无感验证成功         |
  | reload_captcha          | 刷新                                                         | 刷新                 |
  | smart_checking          | 智能检测中                                                   | 智能检测             |
  | verify                  | 验 证                                                        | 验证                 |
  | verify_fail             | 验证失败，<a href="#">请重试</a>！                           | 验证失败             |
  | verify_success          | 验证成功                                                     | 验证成功             |
  | verifying               | 验证中……                                                     | 验证中               |
  | init_inform             | 拖动下方的滑块                                               | 滑动验证拖动提示     |
  | slide_inform            | 请<span style="color:#F56A00;">拖动</span>左侧滑块至正确缺口 | 滑动验证滑动提示     |
  | rotate_inform           | 请<span style="color:#F56A00;">拖动<span/>左侧滑块旋转至正确位置 | 旋转验证旋转提示     |
  | jigsaw_init_inform      | 验证：请<span style="color:#F56A00;;">拖动</span>图片完成正确拼图 | 拼图验证码初始化提示 |
  | jigsaw_verify_success   | 验证成功                                                     | 拼图验证验证成功     |
  | jigsaw_verify_fail      | 验证失败，请正确拼图                                         | 拼图验证验证失败     |
  | jigsaw_try_too_much     | 尝试次数过多，请重新验证                                     | 拼图验证失败过多     |
  | jigsaw_fail_inform      | 请正确拼图                                                   | 拼图验证验证失败     |
  | scratch_init_inform     | 验证：请使用<span style="color:#F56A00;">尽量少</span>的区域，刮出<span style="color:#F56A00;;">完整</span>图案 | 刮刮卡提示           |
  | scratch_verify_success  | 验证成功                                                     | 刮刮卡验证成功       |
  | scratch_verify_fail     | 验证失败，请刮出完整图案                                     | 刮刮卡验证失败       |
  | scratch_try_too_much    | 尝试次数过多，请重新验证                                     | 刮刮卡验证次数过多   |
  | scratch_total_cost      | 总计耗时                                                     | 刮刮卡耗时           |
  | scratch_seconds         | 秒                                                           |                      |
  | scratch_show            | 演示                                                         | 刮刮卡演示           |
  | hint_msg                | 验证：请<span class="dx_captcha_em">依次</span>点击          | 点选验证提示         |
  | hint_order_msg          | 验证：请按<span class="dx_captcha_em">语序</span>依次点击文字 | 点选验证顺序提示     |
  | hint_msg2               | 验证：请点击                                                 | 点选验证点击提示     |
  | hint_reclick            | 再次点击序号可取消并重选                                     | 点选验证再次点击提示 |
  | hint_fail               | 验证失败，请按提示重新点击                                   | 点选验证验证失败提示 |
  | hit_too_little          | 请继续点击字符                                               | 点选验证继续点击提示 |
  | verify_fail_click_again | 验证失败，请按提示重新单击                                   | 点选验证验证失败提示 |

## 3 服务端集成

  访问顶象官网，注册账号后登录控制台，访问“无感验证”模块，申请开通后系统会分配一个唯一的AppId、AppSecret。

  顶象提供后端SDK来校验token(即安全凭据)是否合法 ，目前支持JAVA、PHP、C#、Python、Node.js、Golang。

### 3.1 接口参数说明

 token验证api返回数据

| 字段名  | 数据类型 | 描述                    |
| ------- | -------- | ----------------------- |
| success | Boolean  | 是否成功，true/false    |
| msg     | String   | 错误信息                |
| ip      | String   | token产生时，客户端的ip |
| code    | String   | 错误code                |

错误code说明

| code | 描述                                                         |
| ---- | ------------------------------------------------------------ |
| 1001 | 错误的APPID                                                  |
| 1002 | 签名错误，请核对APPID和APPSECRET是否正确                     |
| 1003 | token不合法或者已经过期（token是一次性使用并且只有两分钟有效期） |
| 1004 | 参数错误，请核对后端使用的APPID是否和前端页面一致            |
| 1005 | verifyToken传入ip时，校验不通过，即token产生的ip和业务请求ip不一致 |

### 3.2 Java

- **JAVA7及以上版本SDK下载 [点击下载](https://cdn.dingxiang-inc.com/files/10348/ctu-client-sdk-jar-with-dependencies(1.7).jar) ，如需其他版本SDK请联系官网在线客服。**
- maven项目引入依赖

  ```
  <dependency>
      <groupId>com.dingxiang-inc</groupId>
      <artifactId>ctu-client-sdk</artifactId>
      <version>2.4</version>
  </dependency>
  ```

- 当用户滑动验证码通过后，验证码服务会生成一个token，用户的业务请求带上这个验证码token，业务系统再去请求验证码服务验证token，验证码服务会返回token的合法性和采集到的客户端ip。如果业务系统可以采集提交业务参数的客户端ip，可以随token一并提交，验证码服务除了校验token的合法性还会校验客户端验证码验证和提交业务参数的ip是否一致。

  ```java
  /**构造入参为appId和appSecret
   * appId和前端验证码的appId保持一致，appId可公开
   * appSecret为秘钥，请勿公开
   * token在前端完成验证后可以获取到，随业务请求发送到后台，token有效期为两分钟
   * ip 可选，提交业务参数的客户端ip
   **/
  String appId = "appId";
  String appSecret = "appSecret";
  CaptchaClient captchaClient = new CaptchaClient(appId,appSecret);
  captchaClient.setCaptchaUrl("https://xxx.dingxiang-inc.com/api/tokenVerify");
  //指定服务器地址，saas可在控制台，应用管理页面最上方获取
  CaptchaResponse response = captchaClient.verifyToken(token);
  //CaptchaResponse response = captchaClient.verifyToken(token, ip);
  //针对一些token冒用的情况，业务方可以采集客户端ip随token一起提交到验证码服务，验证码服务除了判断token的合法性还会校验提交业务参数的客户端ip和验证码颁发token的客户端ip是否一致
  System.out.println(response.getCaptchaStatus());
  //确保验证状态是SERVER_SUCCESS，SDK中有容错机制，在网络出现异常的情况会返回通过
  //System.out.println(response.getIp());
  //验证码服务采集到的客户端ip
  if (response.getResult()) {
      /**token验证通过，继续其他流程**/
  } else {
      /**token验证失败，业务系统可以直接阻断该次请求或者继续弹验证码**/
  }
  ```

### 3.3 PHP

**PHP版本SDK下载： [点击下载](https://cdn.dingxiang-inc.com/files/10348/php-sdk.zip)**

  ```php
  include ("CaptchaClient.php");
  /**构造入参为appId和appSecret
   * appId和前端验证码的appId保持一致，appId可公开
   * appSecret为秘钥，请勿公开
   * token在前端完成验证后可以获取到，随业务请求发送到后台，token有效期为两分钟**/
  $appId = "appId";
  $appSecret = "appSecret";
  $client = new CaptchaClient($appId,$appSecret);
  $client->setTimeOut(2);      //设置超时时间，默认2秒
  $client->setCaptchaUrl("https://xxx.dingxiang-inc.com/api/tokenVerify");
  //指定服务器地址，saas可在控制台，应用管理页面最上方获取
  $response = $client->verifyToken(token);  //token指的是前端传递的值，即验证码验证成功颁发的token
  echo $response->serverStatus;
  //确保验证状态是SERVER_SUCCESS，SDK中有容错机制，在网络出现异常的情况会返回通过
  if($response->result){
      echo "true";
      /**token验证通过，继续其他流程**/
  }else{
      echo "false";
      /**token验证失败**/
  }
  ```

### 3.4 C#

**C#版本SDK下载： [点击下载](https://cdn.dingxiang-inc.com/files/10348/DxCsharpSDK.zip)**

  ```C-sharp
  // 依赖 .NET Framework 4.0
  // 在您的项目中添加DxCsharpSDK.dll引用
  String appId = "appId";
  String appSecret = "appSecret";
  DxCsharpSDK.Captcha captcha = new DxCsharpSDK.Captcha(appId,appSecret);
  captcha.SetCaptchaUrl("https://xxxx.dingxiang-inc.com/api/tokenVerify");
  //指定服务器地址，saas可在控制台，应用管理页面最上方获取
  //captcha.SetTimeOut(2000);
  //设置超时时间，单位毫秒，默认2秒
  DxCsharpSDK.CaptchaResponse response = captcha.VerifyToken("token");
  Response.Write(response.captchaStatus);
  //确保验证状态是SERVER_SUCCESS，SDK中有容错机制，在网络出现异常的情况会返回通过
  if (response.result) {
      /**token验证通过，继续其他流程**/
  } else {
      /**token验证失败，业务系统可以直接阻断该次请求或者继续弹验证码**/
  }
  ```

### 3.5 Python

**Python版本SDK下载： [点击下载](https://cdn.dingxiang-inc.com/public-service/downloads/SDK/ctu/Python-SDK.zip)**

  ```python
  from CaptchaClient import CaptchaClient

  class TokenDemo:
      def __init__(self):
          pass

      APP_ID = '12610axxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'
      APP_SECRET = 'a3e56cxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'

  if __name__ == '__main__':
      captchaClient = CaptchaClient(APP_ID, APP_SECRET)
      captchaClient.setTimeOut(2)
      # 设置超时时间，默认2秒
      captchaClient.setCaptchaUrl("https://xxx.dingxiang-inc.com/api/tokenVerify")
      # 指定服务器地址，saas可在控制台，应用管理页面最上方获取
      response = captchaClient.checkToken("token")
      print response['serverStatus']
      #确保验证状态是SERVER_SUCCESS，SDK中有容错机制，在网络出现异常的情况会返回通过
      print response['result']
      if response['result']:
          # token验证通过，继续其他流程
          pass
      else:
          # token验证失败，业务系统可以直接阻断该次请求或者继续弹验证码
          pass
  ```

### 3.6 Node.js

  ~~~
  npm install dx-captcha-sdk --save
  ~~~

  ~~~js
  const CaptchaSDK = require('dx-captcha-sdk')

  const sdk = new CaptchaSDK('您的appId', '您的appSecret')

  sdk.verifyToken('验证码token', {
    timeout: 3000,
    params: {
      ip: '透传IP',
      sceneCode: '场景code'
    }
  }).then(({ captchaStatus, detail }) => {
    console.log('验证成功')
  }).catch(({ detail }) => {
    console.log('验证失败')
  })
  ~~~

### 3.7 Golang

[SDK 下载地址](https://cdn.dingxiang-inc.com/files/72/captcha-client.zip)

  ```go
  // 版本 Go 1.13

  import "./captcha-client"

  /*
  构造入参为appId和appSecret
  appId和前端验证码的appId保持一致，appId可公开
  appSecret为秘钥，请勿公开
  token在前端完成验证后可以获取到，随业务请求发送到后台，token有效期为两分钟
  ip 可选，提交业务参数的客户端ip
  */
  appId := "appId"
  appSecret := "appSecret"
  captchaClient := captcha_client.NewCaptchaClient(appId, appSecret)
  //captchaClient.SetTimeout(2000)
  //设置超时时间，单位毫秒，默认2秒
  captchaClient.SetCaptchaUrl("https://xx.dingxiang-inc.com/api/tokenVerify")
  //指定服务器地址，saas可在控制台，应用管理页面最上方获取
  captchaResponse := captchaClient.VerifyToken(token)
  //captchaResponse := captchaClient.VerifyTokenAndIP(token, ip)
  //针对一些token冒用的情况，业务方可以采集客户端ip随token一起提交到验证码服务，验证码服务除了判断token的合法性还会校验提交业务参数的客户端ip和验证码颁发token的客户端ip是否一致
  //fmt.Println(captchaResponse.CaptchaStatus)
  //确保验证状态是SERVER_SUCCESS，SDK中有容错机制，在网络出现异常的情况会返回通过
  //fmt.Println(captchaResponse.Ip)
  //验证码服务采集到的客户端ip
  if captchaResponse.Result {
      /*token验证通过，继续其他流程*/
  } else {
      /*token验证失败，业务系统可以直接阻断该次请求或者继续弹验证码*/
  }
  ```

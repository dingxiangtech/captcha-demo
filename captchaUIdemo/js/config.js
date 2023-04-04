function getConfigs () {
  // 公共配置，所有验证类型option都会有以下属性。
  var baseOption = {
    isSaaS: true,
    apiServer: 'https://cap.dingxiang-inc.com',  // 需要做修改。请登录顶象控制台，访问验证码的应用管理，获取页面上 apiServer 值
    constID_js: 'https://cdn.dingxiang-inc.com/ctu-group/constid-js/index.js',
    feedback: true,
    ua_js: 'https://cdn.dingxiang-inc.com/ctu-group/ctu-greenseer/greenseer.js',
    constIDServer: 'https://constid.dingxiang-inc.com/udid/c1'
  };

  var style = 'popup';

  var configs = {
    // ----------------------滑动验证各验证形式配置参数----------------------
    basic: {
      id: 'demo0',
      option: $.extend({}, baseOption, {
        style: style,showVoice:true,
        appId: '',  //appId，需要做修改。请登录顶象控制台，访问验证码的应用管理或应用配置模块获取
        // 验证成功时下发token， 需要传递到业务系统中做token校验
        success: function (token) {
          window.console && console.log('success t1, token:', token);

          var username = $('#name').val()
          console.log(username)

          var password = $('#password').val()

          $.ajax({
            url: 'http://localhost:8080/user/login',
            method: 'GET', 
            data: { 
              username: username,
              password: password,
              captchaToken: token
            },
            success: function(response) {
              window.demo.hide()
              alert("登录成功")
            },
            error: function(xhr, status, error) {
              console.log(status + ': ' + error);
            }
          });

        }
      })
    }
  }

  return configs
}

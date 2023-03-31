;(function() {
  var configs = getConfigs();
  $(init);

  function init() {
    renderContent("basic")
    bindEvent()
  }

  function renderContent(type) {
    console.log('renderContent:' + type)
    setHTML(type)
    initCaptcha(type)
  }

  function setHTML(type) {
    var config = configs[type]

    config.btnId = config.id + "-btn"
    var template = renderTemplate(config.id)
    $(".content-body ul").append(template)
  }

  function bindEvent() {
    $(".menu").on("click", function(e) {
      var target = e.target
      var className = "menu-selected"

      if (target.tagName === "LI" && target.className.indexOf(className) < 0) {
        $(target).addClass(className)
        $(target)
          .siblings()
          .removeClass(className)

        var title = $(target).text() + "Demo"
        $(".content-header h1").text(title)

        $(".content-body ul").html("")
        renderContent($(target).attr("data-type"))
      }
    })
  }

  function renderTemplate(captchaId) {
    var template = [
      '<li class="content-body-section">',
        '<div class="login-box">',
          '<div class="login-title">',
            '<div class="title-en">Sign in</div>',
            '<div class="title-ch">登录</div>',
          '</div>',
          '<div class="login-form">',
            '<div class="input">',
              '<div class="input-legend">用户名</div>',
              '<input id="name" placeholder="请输入用户名" />',
            '</div>',
            '<div class="input">',
              '<div class="input-legend">密码</div>',
              '<input id="password" type="password" placeholder="请输入密码" />',
            '</div>',
            '<button class="login-btn" id="' + captchaId + '-btn">登录</button>',
          '</div>',
        '</div>',
        '<div id="' + captchaId + '"></div>',
      "</li>",
    ].join('')

    var $template = $(template)
    return $template
  }

  function initCaptcha(type) {
    var config = configs[type]

    window.demo = _dx.Captcha(document.getElementById(config.id), config.option)
    document.getElementById(config.btnId).onclick = function() {
      demo.reload()
      demo.show()
    }
  }
})()

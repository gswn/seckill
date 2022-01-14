<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp"%><!--静态包含-->

</head>
<body>

<div class="container">
    <div class="panel panel-default text-center">
        <div class="pannel-heading">
            <h1>${seckill.name}</h1>
        </div>

        <div class="panel-body">
            <h2 class="text-danger">
                <!-- 显示time图标 -->
                <span class="glyphicon glyphicon-time"></span>
                <!-- 展示倒计时 -->
                <span class="glyphicon" id="seckill-box"></span>
            </h2>
        </div>
    </div>
</div>

<!-- 登录弹出层，输入电话 -->
<div id="killPhoneModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"></span>秒杀电话：
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="test" name="killPhone" id="killPhoneKey"
                               placeholder="填手机号^o^" class="form-control"/>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <!-- 验证信息 -->
                <span id="killPhoneMessage" class="glyphicon"></span>
                <button type="button" id="killPhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                    Submit
                </button>
            </div>
        </div>
    </div>
</div>


</body>

<!-- 新 Bootstrap 核心 CSS 文件-->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>


<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="https://code.jquery.com/jquery.js"></script>


<!-- 使用CDN 获取公共js http://www.bootcdn.cn/ -->
<!-- JQuery cookie操作插件
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script> -->
<script src="https://cdn.bootcdn.net/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<!-- JQuery countDown倒计时插件
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>-->
<script src="https://cdn.bootcdn.net/ajax/libs/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery.countdown/2.2.0/jquery.countdown.js"></script>
<link href="https://cdn.bootcdn.net/ajax/libs/jquery-countdown/2.1.0/css/jquery.countdown.css" rel="stylesheet">

<!-- 开始编写交互逻辑 -->
<script src="/resources/script/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
        seckill.detail.init({
            seckillId: "${seckill.seckillId}",
            startTime: ${seckill.startTime.time},//转换成毫秒时间，方便js解析
            endTime: ${seckill.endTime.time}
        });
    });
</script>


<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- 包括所有已编译的插件
<script src="js/bootstrap.min.js"></script>-->

</html>
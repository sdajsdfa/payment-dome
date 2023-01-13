<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2022/12/1
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>
        入口
    </title>
    <link rel="icon" href="../../assets/favicon.gif" type="image/gif">
    <script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <!--<script type="text/javascript" src="assets/js/jQuery/jquery-2.1.4.min.js"></script>-->
    <script type="text/javascript" src="../../assets/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../assets/bootstrap-table/js/bootstrap-table.js"></script>
    <script type="text/javascript" src="../../assets/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>

    <link rel="stylesheet" type="text/css" href="../../assets/bootstrap/css/bootstrap.min.css">
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }
        .container {
            height: 300px;
        }
        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
        .tips.bg-danger {
            background-color: #c96065;
        }
        .tips.bg-success {
            background-color: #68c98c;
        }
        .tips {
            color: #fff;
            padding: 5px 5px;
            text-align: center;
        }
    </style>
</head>
<body style>
<div class="container">
    <div class="loginTips"></div>
    <form class="form-signin form-horizontal">
        <h2 class="form-signin-heading">登录系统</h2>
        <input type="text" name="username" class="input-block-level" placeholder="账号" value="">
        <input type="password" name="password" class="input-block-level" placeholder="密码" value="">
        <p>
            <input type="button" class="btn btn-large btn-primary" id="submitBtn" value="登录"></p>
    </form>
    <form method="post" action=".." id="form1"
          style="text-align: center; position: fixed; float: right; bottom: 2px; right: 5px;">
        <div class="aspNetHidden">
            <input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE"
                   value="/wEPDwULLTE3NDk5NjYwMTIPZBYCZg9kFgICAw9kFgICAQ9kFgQCAQ8PFgIeBFRleHQFDTU5LjE3Mi4xNDYuODRkZAIDDw8WAh8ABRMyMDIyLTEyLTAyIDEwOjUxOjI3ZGRkrork9HPxhsNuhhpf7+R9OvupdgH51ztfcwr8FqxW100=">
        </div>

        <div class="aspNetHidden">

            <input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="C714824B">
        </div>
        CurrentIP:<span id="ContentPlaceHolder1_ip">59.172.146.84</span>
        &nbsp;&nbsp;ServerTime:<span id="ContentPlaceHolder1_time">2022-12-02 10:51:27</span>
        &nbsp;&nbsp;LocalTime:<span id="localtime"></span>
    </form>
</div>
<script type="text/javascript" src="../../assets/js/jQuery/md5.js"></script>
<script>
    $(function () {
        $('#submitBtn').click(function () {
            login();
            return false;
        });
        $("#localtime").html(new Date().Format("yyyy-MM-dd hh:mm:ss"));
    });

    function login() {
        var username = $("input[name='username']").val();
        var password = $("input[name='password']").val();
        if (username == "" || password == "") {
            alert('用户名或密码不能为空！');
        }
        // var uname = md5(username);
        var pwd = md5(password);
        $.ajax({
            url: "/login",
            dataType: 'json',
            async: true,
            data: {"userName": username, "passWord": pwd},
            type: "POST",
            withCredentials: true,
            // crossDomain: true,
            success: function (reg) {
                if (reg.code == 200) {
                    var successStr = tipsInfo(1, reg.msg);
                    $('.loginTips').html(successStr);
                    setTimeout(function () {
                        top.location.href = "main"
                    }, 1500)
                    sessionStorage.setItem("login_name",reg.data.userName);
                } else {
                    var errorStr = tipsInfo(0, reg.msg);
                    $('.loginTips').html(errorStr);
                }
            },
            error: function () {
                var errorStr = tipsInfo(0, '登录失败');

                $('.loginTips').html(errorStr);

            }
        });
    }

    //提示信息
    function tipsInfo(type, msg) {
        var tipsStr = "";
        if (type == 1) {
            tipsStr += '<p class="tips bg-success">';
        } else if (type == 0) {
            tipsStr += '<p class="tips bg-danger">';
        }
        tipsStr += '<span class="error">' + msg + '</span>';
        tipsStr += '<button type="button" class="close" aria-label="Close"><span class="colseTips" aria-hidden="true">&times;</span></button>';
        tipsStr += '</p>';
        return tipsStr;
    }

    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1,                 //月份
            "d+": this.getDate(),                    //日
            "h+": this.getHours(),                   //小时
            "m+": this.getMinutes(),                 //分
            "s+": this.getSeconds(),                 //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
</script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2022/12/2
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>
        主页
    </title>
    <link rel="icon" href="../../assets/favicon.gif" type="image/gif">
    <script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <!--<script type="text/javascript" src="assets/js/jQuery/jquery-2.1.4.min.js"></script>-->
    <script type="text/javascript" src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="assets/bootstrap-table/js/bootstrap-table.js"></script>
    <script type="text/javascript" src="assets/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>

    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css">
    <script charset="utf-8" async="" src="assets/js/common/main-min.js"></script>
</head>
<body style="">

<div class="header">
    <div class="dl-title">
        <!--<img src="">-->
    </div>
    <div class="dl-log">
        欢迎您，<span class="dl-log-user" id="user"></span><a href="logout" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform">
            <div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div>
        </div>
        <ul id="J_Nav" class="nav-list ks-clear">
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-home">系统管理</div>
                <div class="nav-item-mask"></div>
            </li>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
        <li class="dl-tab-item">
            <div class="dl-second-nav" style="height: 1221px;">
                <div class="dl-second-tree" id="J_1Tree">
                    <ul class="bui-side-menu bui-menu" aria-disabled="false" style="height: 1221px;"
                        aria-pressed="false">
                        <li class="bui-menu-item menu-second" aria-disabled="false" data-id="menu-item1"
                            aria-pressed="false">
                            <div class="bui-menu-title"><s></s><span class="bui-menu-title-text">服务器管理</span></div>
                            <ul class="bui-menu" aria-disabled="false" aria-pressed="false">
                                <li class="bui-menu-item menu-leaf bui-menu-item-selected" aria-disabled="false"
                                    data-id="4" aria-pressed="false"><a href="AppIndex"><em>App管理</em></a></li>

                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="dl-second-slib-con">
                    <div class="dl-second-slib"></div>
                </div>
            </div>
            <div class="dl-inner-tab" id="J_1Tab">
                <div class="bui-nav-tab" aria-disabled="false" style="height: 1221px;" aria-pressed="false">
                    <div class="tab-nav-bar"><s class="tab-nav-arrow arrow-left"></s>
                        <div class="tab-nav-wrapper">
                            <div class="tab-nav-inner">
                                <ul class="tab-nav-list" style="width: 280px; left: 0px;">
                                    <li class="bui-nav-tab-item tab-nav-actived" aria-disabled="false" title="App管理"
                                        style="" aria-pressed="false"><span class="tab-item-title">App管理</span><s
                                            class="tab-item-close"></s></li>
                                    <li class="bui-nav-tab-item" aria-disabled="false" title="接口服务列表" style=""><span
                                            class="tab-item-title">接口服务列表</span><s class="tab-item-close"></s></li>
                                </ul>
                            </div>
                        </div>
                        <s class="tab-nav-arrow arrow-right"></s></div>
                    <div class="tab-content-container" style="height: 1200px;">
                        <div class="tab-content" style="">
                            <iframe src="AppIndex" width="100%" height="100%" frameborder="0">
                            </iframe>
                        </div>
                    </div>
                </div>
            </div>
        </li>
    </ul>
</div>
<script type="text/javascript" src="assets/js/jQuery/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="assets/js/bui-min.js"></script>
<script type="text/javascript" src="assets/js/common/main-min.js"></script>
<script type="text/javascript" src="assets/js/config-min.js"></script>
<script>
    BUI.use('common/main', function () {
        var config = [
            {
                id: '1', menu: [
                    {
                        text: '服务器管理',
                        items: [
                            {id: '4', text: 'App管理', href: 'AppIndex'},
                        ]
                    }
                ]
            }
        ];
        new PageUtil.MainPage({
            modulesConfig: config
        });
    });
    (function () {
        var login_name = sessionStorage.getItem("login_name");
        $('#user').html(login_name);
    })();
</script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2022/12/6
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>
        App信息管理
    </title>
    <script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="/assets/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/assets/bootstrap-table/js/bootstrap-table.js"></script>
    <script type="text/javascript" src="/assets/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
    <script src="/assets/bootstrap/js/fileinput.min.js"></script>
    <script src="/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript" src="/assets/bootstrap/js/zh.js"></script>

    <link rel="stylesheet" type="text/css" href="/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/assets/bootstrap-table/css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="/assets/bootstrap/css/style.css">
    <link rel="stylesheet" href="/assets/bootstrap/css/fileinput.min.css"/>



    <style>
        .appList .col-sm-3 {
            width: 20%;
        }

        .appList .col-sm-10 {
            width: 79%;
        }

        body {
            background-color: #f9f9f9;
        }
    </style>
    <link href="assets/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
    <style id="tsbrowser_video_independent_player_style" type="text/css">
        /*必填项前加红色星号*/
        .form-group .control-label:before {
            color: red;
            position: absolute;
            margin-left: -15px;
        }
        /************jQuery.Validate插件样式开始********************/
        label.error {
            color: Red;
            padding-left: 0px;
        }
        input.error {
            border: dashed 1px red;
        }
        select.error {
            border: dashed 1px red;
        }
        textarea.error {
            border: dashed 1px red;
        }

        [tsbrowser_force_max_size] {
            width: 100% !important;
            height: 100% !important;
            left: 0px !important;
            top: 0px !important;
            margin: 0px !important;
            padding: 0px !important;
            transform: none !important;
        }

        [tsbrowser_force_fixed] {
            position: fixed !important;
            z-index: 9999 !important;
            background: black !important;
        }

        [tsbrowser_force_hidden] {
            opacity: 0 !important;
            z-index: 0 !important;
        }

        [tsbrowser_hide_scrollbar] {
            overflow: hidden !important;
        }

        [tsbrowser_display_none] {
            display: none !important;
            visibility: hidden !important;
            opacity: 0 !important;
        }

        [tsbrowser_force_show] {
            display: black !important;
            visibility: visible !important;
            opacity: 0;
        }</style>
</head>
<body>

<div class="container-fluid m10">
    <div class="row appList">
        <div class="col-sm-3 ">
            <div class="box box-primary" style="height: 1151px;">
                <div class="box-header with-border">APP列表</div>
                <div class="box-body">
                    <div class="bootstrap-table">
                        <div class="fixed-table-toolbar">
                            <div class="bs-bars pull-left">
                                <div class="btn-group" role="group" aria-label="..." id="toolbar">
                                    <button type="button" class="btn btn-success" id="addBtn"><span
                                            class="glyphicon glyphicon-plus" style="margin-right: 5px;"></span>新增
                                    </button>
                                    <button type="button" class="btn btn-info" id="editBtn"><span
                                            class="glyphicon glyphicon-pencil" style="margin-right: 5px;"></span>编辑
                                    </button>
                                    <button type="button" class="btn btn-danger" id="delBtn"><span
                                            class="glyphicon glyphicon-trash" style="margin-right: 5px;"></span>删除
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="fixed-table-container" style="padding-bottom: 0px;">
                            <div class="fixed-table-header" style="display: none;">
                                <table></table>
                            </div>
                            <div class="fixed-table-body">
<%--                                <div class="fixed-table-loading" style="top: 42px;">正在努力地加载数据中，请稍候……</div>--%>
                                <table class="table table-bordered table-striped table-hover definewidth m10"
                                       id="tableL04">
                                    <thead>
                                    <tr>
                                        <th style="" data-field="AppName">
                                            <div class="th-inner ">App名称</div>
                                            <div class="fht-cell"></div>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                            <div class="fixed-table-footer" style="display: none;">
                                <table>
                                    <tbody>
                                    <tr></tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="fixed-table-pagination" style="display: none;"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <div class="col-sm-10">
            <div class="box box-primary" style="height: 1151px;">
                <div class="box-header with-border">APP版本列表</div>
                <div class="box-body" style="background-color: #fff">
                    <div class="bootstrap-table">
                        <div class="fixed-table-toolbar">
                            <div class="bs-bars pull-left">
                                <div class="btn-group" role="group" aria-label="..." id="toolbar2">
                                    <button type="button" class="btn btn-success" id="addBtn2"><span
                                            class="glyphicon glyphicon-plus" style="margin-right: 5px;"></span>新增
                                    </button>
                                    <button type="button" class="btn btn-info" id="editBtn2"><span
                                            class="glyphicon glyphicon-pencil" style="margin-right: 5px;"></span>编辑
                                    </button>
                                    <button type="button" class="btn btn-danger" id="delBtn2"><span
                                            class="glyphicon glyphicon-trash" style="margin-right: 5px;"></span>删除
                                    </button>
<%--                                    <button type="button" class="btn btn-warning" id="serviceBtn2"><span--%>
<%--                                            class="glyphicon glyphicon-globe" style="margin-right: 5px;"></span>接口管理--%>
<%--                                    </button>--%>
                                </div>
                            </div>
                            <div class="columns columns-right btn-group pull-right">
                                <button class="btn btn-default" type="button" name="refresh" aria-label="refresh"
                                        title="刷新"><i class="glyphicon glyphicon-refresh icon-refresh"></i></button>
                            </div>
                            <div class="pull-right search"><input class="form-control" type="text" placeholder="搜索">
                            </div>
                        </div>
                        <div class="fixed-table-container" style="padding-bottom: 0px;">
                            <div class="fixed-table-header" style="display: none;">
                                <table></table>
                            </div>
                            <div class="fixed-table-body">
<%--                                <div class="fixed-table-loading" style="top: 42px;">正在努力地加载数据中，请稍候……</div>--%>
                                <table class="table table-bordered table-striped table-hover definewidth m10"
                                       id="tableL05">
                                    <thead>
                                    <tr>
                                        <th style="width: 20px; " data-field="">
                                            <div class="th-inner sortable both">序号</div>
                                            <div class="fht-cell"></div>
                                        </th>
                                        <th style="width: 20%; " data-field="AppName">
                                            <div class="th-inner ">APP名称</div>
                                            <div class="fht-cell"></div>
                                        </th>
                                        <th style="width: 20%; " data-field="AppVer">
                                            <div class="th-inner ">APP版本</div>
                                            <div class="fht-cell"></div>
                                        </th>
                                        <th style="" data-field="AppDate">
                                            <div class="th-inner ">APP上线日期</div>
                                            <div class="fht-cell"></div>
                                        </th>
                                        <th style="" data-field="RegularFile">
                                            <div class="th-inner ">APP普通文件</div>
                                            <div class="fht-cell"></div>
                                        </th>
                                        <th style="text-align: center; width: 30px; " data-field="AppFlag">
                                            <div class="th-inner ">App状态</div>
                                            <div class="fht-cell"></div>
                                        </th>
                                        <th style="width: 100px; " data-field="remark">
                                            <div class="th-inner ">备注</div>
                                            <div class="fht-cell"></div>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                            <div class="fixed-table-footer" style="display: none;">
                                <table>
                                    <tbody>
                                    <tr></tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
<%--                        <div class="fixed-table-pagination" style="display: block;">--%>
<%--                            <div class="pull-left pagination-detail"><span class="pagination-info"></span><span--%>
<%--                                    class="page-list" style="display: none;">每页显示 <span class="btn-group dropup"><button--%>
<%--                                    type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span--%>
<%--                                    class="page-size">15</span> <span class="caret"></span></button><ul--%>
<%--                                    class="dropdown-menu" role="menu"><li role="menuitem" class="active"><a--%>
<%--                                    href="#">15</a></li></ul></span> 条记录</span></div>--%>
<%--                            <div class="pull-right pagination" style="display: none;">--%>
<%--                                <ul class="pagination">--%>
<%--                                    <li class="page-item page-pre"><a class="page-link" aria-label="上一页"--%>
<%--                                                                      href="javascript:void(0)">上一页12</a></li>--%>
<%--                                    <li class="page-item active"><a class="page-link" aria-label="第1页"--%>
<%--                                                                    href="javascript:void(0)">1</a></li>--%>
<%--                                    <li class="page-item page-next"><a class="page-link" aria-label="下一页"--%>
<%--                                                                       href="javascript:void(0)">下一页</a></li>--%>
<%--                                </ul>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

        </div>
    </div>
</div>
<!--按钮组-->
<!--APP分类管理-->
<!--添加模态框-->
<div class="modal fade" id="AppModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="addModalLabel">添加APP信息</h4>
            </div>
            <div class="modal-body">
                <div class="AppTips">
                </div>
                <form class="form-horizontal" id="Appform">
<%--                    <div class="form-group">--%>
<%--                        <label for="AppId" class="col-sm-2 control-label"><span class="c-red">*</span>AppId:</label>--%>
<%--                        <div class="col-sm-10">--%>
<%--                            <input type="text" class="form-control" name="AppId">--%>
<%--                        </div>--%>
<%--                    </div>--%>
                    <div class="form-group">
                        <label for="AppName" class="col-sm-2 control-label"><span class="c-red">*</span>App名称:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="AppName" name="AppName">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="save_submit()">提交</button>
            </div>
        </div>
    </div>
</div>

<!--按钮组-->
<!--APP分类管理-->
<!--编辑模态框-->
<div class="modal fade" id="editAppModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="editModalLabel">编辑APP信息</h4>
            </div>
            <div class="modal-body">
                <div class="AppTips">
                </div>
                <form class="form-horizontal" id="editAppform">
                    <div class="form-group">
                        <label for="AppId" class="col-sm-2 control-label"><span class="c-red">*</span>AppId:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="AppId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="AppName" class="col-sm-2 control-label"><span class="c-red">*</span>App名称:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="AppName" name="AppName">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="edit_submit()">提交</button>
            </div>
        </div>
    </div>
</div>


<!--删除弹框-->
<div class="modal fade bs-example-modal-sm" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="waringLabel">删除操作</h4>
            </div>
            <div class="modal-body">
                真的要删除吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal" id="delConfirm">确定</button>
            </div>
        </div>
    </div>
</div>

<!--提示弹框-->
<div class="modal fade bs-example-modal-sm" id="waringModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="waringLabel">警告信息</h4>
            </div>
            <div class="modal-body">
                请选择一条记录！
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<!--APP版本管理-->
<!--添加app版本-->
<div class="modal fade bs-example-modal-lg" id="AppVerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="addAPPModalLabel">添加APP版本信息</h4>
            </div>
            <div class="modal-body">
                <div class="AppVerTips">
                </div>
                <form class="form-horizontal" id="AppVerform" >
                    <div class="form-group">
                        <label for="AppId" class="col-sm-2 control-label"><span class="c-red">*</span>APP名称:</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="AppId" id="AppId">
<%--                                <option value="">--请选择--</option>--%>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="AppVer" class="col-sm-2 control-label"><span class="c-red">*</span>App版本:</label>
                        <div class="col-sm-10">
                            <input type="text"  onkeyup="value=value.replace(/[^\d^\.]+/g,'')"  class="form-control" id="AppVer" name="AppVer">
                            <input type="hidden" class="form-control" id="Id" name="Id">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="AppDate" class="col-sm-2 control-label"><span class="c-red">*</span>App上线日期:</label>
                        <div class="col-sm-10">
                            <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="form-control Wdate"
                                   name="AppDate">

                        </div>
                    </div>
<%--                    <div class="form-group">--%>
<%--                        <label for="DownPath" class="col-sm-2 control-label"><span--%>
<%--                                class="c-red">*</span>App下载路径:</label>--%>
<%--                        <div class="col-sm-10">--%>
<%--                            <input type="text" class="form-control" id="DownPath" name="DownPath">--%>
<%--                        </div>--%>
<%--                    </div>--%>
                    <div class="form-group">
                        <label for="RegularFile" class="col-sm-2 control-label"><span
                                class="c-red">*</span>APP普通文件:</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" name="RegularFile" id="RegularFile" multiple="multiple"  >
                            <span id="birthRegularFile"></span>
                            <span id="birthRegularFileHttp"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="JsonFile" class="col-sm-2 control-label"><span
                                class="c-red">*</span>APPjson文件:</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" name="JsonFile" id="JsonFile" multiple="multiple"    >
                            <span id="birthJsonFile"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="AppFlag" class="col-sm-2 control-label"><span class="c-red">*</span>App状态:</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" checked="checked" name="AppFlag" id="AppFlag" value="1">启动
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="AppFlag" id="AppFlag" value="0">
                                禁止
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-2 control-label"><span class="c-red">*</span>备注信息:</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="3" name="remark" id="remark"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="saveAppSev()">提交</button>
            </div>
        </div>
    </div>
</div>

<!--APP版本管理-->
<!--编辑app版本-->
<div class="modal fade bs-example-modal-lg" id="editAAppVerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="editAAPPModalLabel">编辑APP版本信息</h4>
            </div>
            <div class="modal-body">
                <div class="AppVerTipsA">
                </div>
                <form class="form-horizontal" id="editAAppPerform">
                    <div class="form-group">
                        <label for="AppIds" class="col-sm-2 control-label"><span class="c-red">*</span>APP名称:</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="AppIds" id="AppIds">
<%--                                <option value="">--请选择--</option>--%>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="AppVer" class="col-sm-2 control-label"><span class="c-red">*</span>App版本:</label>
                        <div class="col-sm-10">
                            <input type="text"  onkeyup="value=value.replace(/[^\d^\.]+/g,'')"  class="form-control" id="AppVer" name="AppVer">
                            <input type="hidden" class="form-control" id="Id" name="Id">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="AppDate" class="col-sm-2 control-label"><span class="c-red">*</span>App上线日期:</label>
                        <div class="col-sm-10">
                            <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="form-control Wdate"
                                   name="AppDate">

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="AppFlag" class="col-sm-2 control-label"><span class="c-red">*</span>App状态:</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="AppFlag" id="AppFlag" value="1">启动
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="AppFlag" id="AppFlag" value="0">
                                禁止
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-2 control-label"><span class="c-red">*</span>备注信息:</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="3" name="remarks" id="remarks"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="editAppSev()">提交</button>
            </div>
        </div>
    </div>
</div>

<!--删除APP版本-->
<div class="modal fade bs-example-modal-sm" id="delVerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="waringLabel">删除操作</h4>
            </div>
            <div class="modal-body">
                真的要删除吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal" id="delVerconfirm">确定</button>
            </div>
        </div>
    </div>
</div>

<!--APP接口管理-->
<div class="modal fade bs-example-modal-lg" id="ServerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="addModalLabel">APP接口管理</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="box box-primary" id="serviceAdd">
                            <div class="box-header">添加接口</div>
                            <div class="box-body">
                                <div class="SerVerTips"></div>
                                <form class="form-horizontal" id="SerVerform">
                                    <div class="form-group">
                                        <label for="AppId" class="col-sm-2 control-label"><span class="c-red">*</span>接口名称:</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" name="ServiceId" id="ServiceId">
                                                <option>--请选择--</option>
                                                <option value="1">地基基础管理系统WebService</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="AppId" class="col-sm-2 control-label"><span class="c-red">*</span>接口版本:</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" name="ServiceVerId" id="ServiceVerId">
                                            </select>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="box-footer clearfix">
                                <div class="pull-right">
                                    <button type="button" class="btn btn-default">取消</button>
                                    <button type="button" class="btn btn-primary" id="AppServiceBtn">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box box-primary" id="serviceList">
                    <div class="box-header with-border">
                        接口列表
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped table-hover definewidth m10" id="tableL06">
                        </table>
                    </div>
                </div>
                <div class="box-footer with-border"></div>
            </div>
        </div>
    </div>
</div>

<!--删除弹框-->
<div class="modal fade bs-example-modal-sm" id="AppdelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="waringLabel">删除操作</h4>
            </div>
            <div class="modal-body">
                真的要删除吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal" id="AppSerDelConfirm">确定</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="assets/My97DatePicker/WdatePicker.js"></script>

<script>
    var rowsInfo;
    let dateIndex = "";

    $("#radio").prop('checked', true);//radio

    function btn_add(){
        $("#myModalLabel").text("添加版本");
        $('#myModal').modal();

    }
    $("#JsonFile").fileinput({
        language: 'zh', //设置语言
        uploadUrl: "/appversionlist/apk_upload", //上传的地址
        allowedFileExtensions: ['json'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : false, //显示移除按钮
        showPreview : false, //是否显示预览
        showCaption: true,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:false,
        uploadExtraData:function(){
            return {"id":rowsInfo.AppId}
        }
    });
    let jsonFile="";
    //异步上传返回结果处理
    $("#JsonFile").on("fileuploaded", function (event, data, previewId, index) {

        var response = data.response;
        if(response.code=200){

            jsonFile = response.JsonFile;
            // $("#JsonFile").remove();
            // $("#JsonFile").val(jsonFile)

            // $("#JsonFile").val(jsonFile);
            // $("#fileMd5").val(response.fileMd5);
            // $("#version").val(response.newVersionName);
            // $("#url").val(response.filePath);
            // this.data.target.value = ""
            if(jsonFile!=null || jsonFile!="") {
                $("#birthJsonFile").text("");
            }
        }
    });
    //上传前
    $('#JsonFile').on('filepreupload', function(event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
    });
    var picArr = [];
    $("#RegularFile").fileinput({
        language : 'zh', //设置语言
        uploadUrl: "/appversionlist/apk_upload", //上传的地址
        allowedFileExtensions: ['apk'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : false, //显示移除按钮
        showPreview : false, //是否显示预览
        showCaption: true,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:false,
        uploadExtraData:function(){
            return {"id":rowsInfo.AppId}
        }

    });
    let regularFile="";
    //异步上传返回结果处理
    $("#RegularFile").on("fileuploaded", function (event, data, previewId, index) {

        const response = data.response;
        if(response.code=200){
            // alert(response.RegularFile);
            regularFile = response.RegularFile;
           // $("#RegularFile").remove();
           // $("#RegularFile").val(regularFile)

           // console.log($("#RegularFile").val()) ;
            // $("#fileMd5").val(response.fileMd5);
            // $("#version").val(response.newVersionName);
            // $("#url").val(response.filePath);
            if(regularFile!=null || regularFile!="") {
                $("#birthRegularFile").text("");
                // alert($('#AppVerform input[name="RegularFile"]').val());
                $("#birthRegularFileHttp").css("color", "red");
                $("#birthRegularFileHttp").text(regularFile);
            }
        }
    });
    //上传前

    $('#RegularFile').on('filepreupload', function(event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
    });

    $(function () {
        var data;
        $.ajax({
            url: '/appname/queryAllAppname',
            type: 'GET',
            dataType: 'json',
            async: false,
            success: function (reg) {
                if (reg.code == 200) {
                    //数据深拷贝
                    data = reg.data.concat();
                } else {
                    alert(reg.msg);
                }
            },
            error: function () {
                alert('请求出错了！');
            }
        });
        var tableHeight = $(document).height() - 52;
        $('.appList .box').height(tableHeight);

        //初始化加载table
        InitTable(data);
        var $table, $table2;

        function InitTable(data) {
            $('#tableL04').bootstrapTable('destroy');
            $table = $('#tableL04').bootstrapTable({
                // toolbar: '#toolbar',
                exportDataType: "all",
                pagination: false,
                sidePagination: 'client',
                pageNumber: 1, //如果设置了分页，首页页码
                pageSize: 15,
                pageList: [15],
                paginationPreText: '上一页',
                paginationNextText: '下一页',
                uniqueId: "AppId",
                //模拟数据
                columns: [
                    {
                        field: 'AppName', title: 'App名称', formatter: function (value, row, index) {
                            return '[' + row.AppId + '] ' + value;
                        }
                    }
                ],
                data: data,
                onLoadSuccess: function () {
                    alert("数据加载成功！");
                },
                onLoadError: function () {
                    alert("数据加载失败！");
                }
            });
        }



        //单击行
        $table.on('click-row.bs.table', function (e, row, $element) {
            $('.selected').removeClass('selected');
            sessionStorage.rowsInfo = JSON.stringify(row);

            $($element).addClass('selected');

            AppSerTable()
            function AppSerTable() {
                rowsInfo = JSON.parse(sessionStorage.rowsInfo);
                $.ajax({
                    url: '/appversionlist/queryAppName',
                    type: 'POST',
                    dataType: 'json',
                    async: true,
                    data: rowsInfo,
                    success: function (reg) {
                        if (reg.code == 200) {
                            $('#tableL05').bootstrapTable('load', reg.data);
                        }
                    },
                    error: function () {
                        alert('请求出错了！');
                        top.location.href = "appLogin"
                    }
                });
            }

            //过滤数据
            // $table2.bootstrapTable('filterBy', {AppId: [1,2,3]});
        });

        //新增
        $('#toolbar').on('click', '#addBtn', function () {
            $('#AppModal').modal('show');
            //清空表单
            $('#Appform')[0].reset();
            $(".tips").addClass('hidden');
            $('#Appform input[name=AppId]').attr('disabled', false);
        });

        //编辑
        $('#toolbar').on('click', '#editBtn', function () {
            if (isSelected('#tableL04')) {
                $('#editAppModal').modal('show');
                $('#editAppform input[name=AppId]').attr('disabled', true);
                var rowsInfo = JSON.parse(sessionStorage.rowsInfo);
                $(".tips").addClass('hidden');
                $("#editAppform input[name='AppId']").val(rowsInfo.AppId);
                $("#editAppform input[name='AppName']").val(rowsInfo.AppName);
            }
        });

        //删除
        $('#toolbar').on('click', '#delBtn', function () {
            if (isSelected('#tableL04')) {
                $('#delModal').modal('show');
            }
        });

        //确认删除
        $('#delConfirm').click(function () {
            var rowsInfo = JSON.parse(sessionStorage.rowsInfo);
            $.ajax({
                url: '/appname/deleteAppname',
                dataType: 'json',
                async: true,
                type: "POST",
                data: {'AppId': rowsInfo.AppId, 'AppName': rowsInfo.AppName},
                success: function (reg) {
                    $table.bootstrapTable('removeByUniqueId', rowsInfo.AppId);
                    // sessionStorage.AppverInfo = JSON.stringify(reg.list2);
                    if(reg.appNameList!=null){
                        $("#tableL04 tr[data-uniqueid="+reg.appNameList.AppId+"]").addClass("selected");
                        sessionStorage.rowsInfo = JSON.stringify(reg.appNameList);
                        rowsInfo = JSON.parse(sessionStorage.rowsInfo);
                    }
                    $('#tableL05').bootstrapTable('load', reg.list2);
                    var verStr = "<option value=''>--请选择--</option>";
                    $.each(reg.AppnameOne, function (n, v) {
                        verStr += '<option value="' + v.AppId + '">' + v.AppName + '</option>';
                    });
                    $('#AppId').html(verStr);
                    $('#AppIds').html(verStr);

                }
            });

        });

        //是否选择了一条记录
        function isSelected(el) {
            var elm = $(el);
            var rowdata = elm.find('.selected');
            var id = rowdata.attr('data-uniqueid');
            if (id == undefined || id == "") {
                $('#waringModal').modal('show');
                return false;
            } else {
                return id;
            }
        }

        //关闭错误提示
        $('.modal-body').on('click', '.close', function () {
            $(this).parent('p').addClass('hidden');
        });


        /**************APP版本管理***************/
            //获取APP版本信息
        var verData;
        (function () {
            $.ajax({
                url: '/appversionlist/queryAllServerlist',
                type: 'GET',
                dataType: 'json',
                async: false,
                success: function (reg) {
                    if (reg.code == 200) {
                        //数据深拷贝
                        verData = reg.list.concat();
                        if(reg.appname!=undefined){
                            $("#tableL04 tr[data-uniqueid="+reg.appname.AppId+"]").addClass("selected");
                            sessionStorage.rowsInfo = JSON.stringify(reg.appname);
                            rowsInfo = JSON.parse(sessionStorage.rowsInfo);
                        }
                        if(reg.list!=undefined) {
                            AppVerTable(reg.list);
                        }
                    }
                },
                error: function () {
                    alert('请求出错了！');
                }
            });
        })();


        //select下拉
        (function () {
            //debugger;
            var verStr = "<option value=''>--请选择--</option>";
            $.each(data, function (n, v) {
                verStr += '<option value="' + v.AppId + '">' + v.AppName + '</option>';
            });
            // $('#editAAppVerform input[name="AppId"]').html(verStr);
            // $('#AppVerform input[name="AppId"]').html(verStr);
            $('#AppId').html(verStr);
            $('#AppIds').html(verStr);
        })();


        function AppVerTable(data) {
            $('#tableL05').bootstrapTable('destroy');
            $table2 = $('#tableL05').bootstrapTable({
                // toolbar: '#toolbar2',
                exportDataType: "all",
                // showRefresh: true, //是否显示刷新按钮
                pagination: true,
                sidePagination: 'client',
                pageNumber: 1, //如果设置了分页，首页页码
                pageSize: 15,
                pageList: [15],
                paginationPreText: '上一页',
                paginationNextText: '下一页',
                // search: true,
                uniqueId: "AppId"
                //模拟数据
                , columns: [
                    {
                        field: '', title: '序号', sortable: true, width: '20px', formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },
                    {field: 'AppName', title: 'APP名称'},
                    {field: 'AppVer', title: 'APP版本', width: '20%'},
                    {field: 'AppDate', title: 'APP上线日期'},
                    {
                        field: 'RegularFile', title: 'APP下载', formatter: function (value, row, index) {
                            return '<a href="'+value+'" >点击下载</a>';
                        }
                    },
                    {
                        field: 'AppFlag',
                        title: 'App状态',
                        width: '30px',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (value == 0) {
                                return '<span class="label label-danger">无效</span>'
                            } else if (value == 1) {
                                return '<span class="label label-success">有效</span>'
                            } else {
                                return '<span class="label label-default">未知</span>'
                            }
                        }
                    },
                    {field: 'remark', title: '备注'}
                ],
                data: data,
                onLoadSuccess: function () {
                    alert("数据加载成功！");
                },
                onLoadError: function () {
                    alert("数据加载失败！");
                }
            });

        }

        //单击表格
        $table2.on('click-row.bs.table', function (e, row, $element) {
            $('.selected').removeClass('selected');
            sessionStorage.AppverInfo = JSON.stringify(row);
            $($element).addClass('selected');
            dateIndex = $($element).attr('data-index');
        });

        //添加APP版本信息
        $('#toolbar2').on('click', '#addBtn2', function () {
            // $("#addAPPModalLabel").text('添加APP版本信息');
            $('#AppVerModal').modal('show');
            //清空表单
            $(".tips").addClass('hidden');
            $('#AppVerform')[0].reset();
            $('#Id').val("");
            $('#birthRegularFileHttp').text("");
        });

        //编辑APP版本信息
        $('#toolbar2').on('click', '#editBtn2', function () {
            $("#editAAppPerform input:radio[value=0]").prop("checked",false);
            $("#editAAppPerform input:radio[value=1]").prop("checked",false);
            if (isSelected('#tableL05')) {
                $('#editAAppVerModal').modal('show');
                //清空表单
                $(".tips").addClass('hidden');
                //表单加载数据
                var AppverInfo = JSON.parse(sessionStorage.AppverInfo);
                $.each(AppverInfo, function (key, v) {
                    //debugger;
                    if (key != 'AppFlag') {
                        if(key == 'AppId'){
                           $('#editAAppPerform  *[name=' + "AppIds" + ']').val(v);
                        }
                        if(key == 'remark'){
                            $('#editAAppPerform  *[name=' + "remarks" + ']').val(v);
                        }
                        $('#editAAppPerform  *[name=' + key + ']').val(v);
                    } else {
                        if (key == 'AppFlag') {
                            $("#editAAppPerform input:radio[value='" + v + "']").prop('checked', true);
                        }
                    }
                });
            }
        });

        //删除
        $('#toolbar2').on('click', '#delBtn2', function () {
            if (isSelected('#tableL05')) {
                $('#delVerModal').modal('show');
            }
        });

        //确认删除
        $('#delVerconfirm').click(function () {
            var AppverInfo = JSON.parse(sessionStorage.AppverInfo);
            $.ajax({
                url: '/appversionlist/deleteAppversionlist',
                dataType: 'json',
                async: true,
                type: "POST",
                data: {'Id': AppverInfo.Id, 'AppId': AppverInfo.AppId},
                success: function (reg) {
                    $table2.bootstrapTable('removeByUniqueId', AppverInfo.AppId);
                    $('#tableL05').bootstrapTable('load', reg.data);
                    $("#tableL04 tr[data-uniqueid="+AppverInfo.AppId+"]").addClass("selected");
                }
            });

        });

        function click() {

            $.ajax({

                type: "get",

                url: "url",

                dateType: "json",

                //data: "",

                success: function (data) {

                    if (data.status == 1) {

                        return true;

                    }

                },

                error: function () {

                    return false;

                },

                async: false

            });

        }



        //接口管理
        (function () {
            // var $table3;
            // var AppverInfo = JSON.parse(sessionStorage.AppverInfo);
            //
            // $('#toolbar2').on('click', '#serviceBtn2', function () {
            //     var AppVerId = isSelected('#tableL05');
            //     if (AppVerId) {
            //         $('#ServerModal').modal('show');
            //         SerAppTable(AppverInfo.Id, AppverInfo.AppId);
            //     }
            // });


            // //获取接口
            // $.ajax({
            //     url: '/appversionlist/queryAllServerlist',
            //     type: 'GET',
            //     dataType: 'json',
            //     async: false,
            //     success: function (reg) {
            //         if (reg.code == 200) {
            //             var serStr = "<option>--请选择--</option>";
            //             $.each(reg.data, function (key, val) {
            //                 //debugger;
            //                 serStr += '<option value=' + val.ServiceId + '>' + val.ServiceName + '</option>'
            //             });
            //
            //             $('#ServiceId').html(serStr);
            //
            //         } else {
            //             alert(reg.msg);
            //         }
            //     },
            //     error: function () {
            //         alert('请求出错了！1231');
            //     }
            // });

            //获取接口版本
            $('#ServiceId').change(function () {
                var ServiceId = $(this).val();

                //获取接口
                $.ajax({
                    url: '/ashx/servicever.ashx?action=list',
                    type: 'GET',
                    dataType: 'json',
                    async: false,
                    success: function (reg) {
                        if (reg.code == 0) {
                            var serVerStr = "<option>--请选择--</option>";
                            $.each(reg.data, function (k1, v1) {
                                if (v1.ServiceId == ServiceId) {
                                    serVerStr += '<option value=' + v1.Id + '>' + v1.ServiceVer + '</option>';
                                }
                                $('#ServiceVerId').html(serVerStr);

                            });
                        }
                    },
                    error: function () {
                        alert('请求出错了！');
                    }
                });

            });


            $('#AppServiceBtn').click(function () {
                AddAppSer();
            });

            //提交接口
            function AddAppSer() {
                var data = new Object();
                data['AppVerId'] = AppverInfo.Id;
                data['ServiceVerId'] = $('#ServiceVerId').val();
                $.ajax({
                    url: '/ashx/appver.ashx?action=serviceadd',
                    type: 'POST',
                    dataType: 'json',
                    data: data,
                    async: true,
                    success: function (reg) {
                        if (reg.code == 0) {
                            var successStr = tipsInfo(1, reg.msg);
                            $('.SerVerTips').html(successStr);
                            setTimeout(function () {
                                SerAppTable(AppverInfo.Id, AppverInfo.AppId);
                                $('.bg-success').hide();
                                $('#SerVerform')[0].reset();

                            }, 1000);
                        } else {
                            var errorStr = tipsInfo(0, reg.msg);

                            $('.SerVerTips').html(errorStr);
                        }
                    },
                    error: function () {
                        var errorStr = tipsInfo(0, '提交失败！');
                        $('.SerVerTips').html(errorStr);

                    }
                });
            }


            function SerAppTable(AppVerId, AppId) {
                var serviceData;
                $.ajax({
                    url: '/ashx/appver.ashx?action=servicelist&AppVerId=' + AppVerId + '&AppId=' + AppId,
                    type: 'GET',
                    dataType: 'json',
                    async: false,
                    success: function (reg) {
                        if (reg.code == 200) {
                            //数据深拷贝
                            serviceData = reg.data.concat();
                        }
                    },
                    error: function () {
                        alert('请求出错了！');
                    }
                });
                $('#tableL06').bootstrapTable('destroy');
                $table3 = $('#tableL06').bootstrapTable({
                    exportDataType: "all",
                    showRefresh: false, //是否显示刷新按钮
                    pagination: false,
                    sidePagination: 'client',
                    pageNumber: 1, //如果设置了分页，首页页码
                    pageSize: 15,
                    pageList: [15],
                    paginationPreText: '上一页',
                    paginationNextText: '下一页',
                    search: false,
                    uniqueId: "ServiceVerId"
                    //模拟数据
                    , columns: [
                        {
                            field: '',
                            title: '序号',
                            sortable: true,
                            width: '20px',
                            formatter: function (value, row, index) {
                                return index + 1;
                            }
                        },
                        {field: 'ServiceName', title: '接口名称'},
                        {field: 'ServiceVer', title: '接口版本'},
                        {
                            field: 'ServiceVerFlag',
                            title: '接口状态',
                            width: '30px',
                            align: 'center',
                            formatter: function (value, row, index) {
                                if (value == 0) {
                                    return '<span class="label label-danger">无效</span>'
                                } else if (value == 1) {
                                    return '<span class="label label-success">有效</span>'
                                } else {
                                    return '<span class="label label-default">未知</span>'
                                }
                            }
                        },
                        {
                            field: '',
                            title: '操作',
                            width: '100px;',
                            align: 'center',
                            formatter: function (value, row, index) {
                                return '<span class="glyphicon glyphicon-trash delAppSer" style="color:red" title="删除"></span>';
                            }
                        }
                    ],
                    data: serviceData,
                    onLoadSuccess: function () {
                        alert("数据加载成功！");
                    },
                    onLoadError: function () {
                        alert("数据加载失败！");
                    }
                });
                //单击表格
                $table3.on('click-row.bs.table', function (e, row, $element) {
                    sessionStorage.AppSerInfo = JSON.stringify(row);
                });

            }


            $('#tableL06').on('click', '.delAppSer', function () {

                $('#AppdelModal').modal('show');
            });


            //确认删除
            $('#AppSerDelConfirm').click(function () {
                var AppSerInfo = JSON.parse(sessionStorage.AppSerInfo);
                console.log(AppSerInfo);
                $.ajax({
                    url: '/ashx/appver.ashx?action=servicedelete',
                    dataType: 'json',
                    async: true,
                    type: "POST",
                    data: {'AppVerId': AppSerInfo.AppVerId, 'ServiceVerId': AppSerInfo.ServiceVerId},
                    success: function () {
                        $table3.bootstrapTable('removeByUniqueId', AppSerInfo.ServiceVerId);
                        alert('删除成功！');
                    }
                });

            });


        })();

    });

    $("#Appform").validate({
        rules: {
            AppName:{
                required: true, // 设为必填项
            },
        },
    });


    //提交
    function save_submit() {
        let flag = $("#Appform").valid();
        if (!flag) {
            //没有通过验证，就不进行下面的ajax提交了
            return;
        }
        var data = new Object();
        data['AppName'] = $("#Appform input[name='AppName']").val();
        $.ajax({
            url: '/appname/addAppname',    //请求的url地址
            dataType: "json",
            async: true,
            data: data,
            type: "POST",
            success: function (reg) {
                if (reg.code == 200) {
                    var successStr = tipsInfo(1, reg.msg);
                    $('.AppTips').html(successStr);
                    setTimeout(function () {
                        $('#AppModal').modal('hide');
                    });
                    $('#tableL04').bootstrapTable('load', reg.listOne);
                    $("#tableL04 tr[data-uniqueid="+reg.AppOne.AppId+"]").addClass("selected");
                    sessionStorage.rowsInfo = JSON.stringify(reg.AppOne);
                    rowsInfo = JSON.parse(sessionStorage.rowsInfo);
                    $('#tableL05').bootstrapTable('load', reg.appversionlists);

                    var verStr = "<option value=''>--请选择--</option>";
                    $.each(reg.listOne, function (n, v) {
                        verStr += '<option value="' + v.AppId + '">' + v.AppName + '</option>';
                    });
                    $('#AppId').html(verStr);
                    $('#AppIds').html(verStr);
                } else {
                    var errorStr = tipsInfo(0, reg.msg);
                    $('.AppTips').html(errorStr);
                }
            },
            error: function () {
                var errorStr = tipsInfo(0, '提交失败！');
                $('.AppTips').html(errorStr);
            }
        });
    }

    $("#editAppform").validate({
        rules: {
            AppName:{
                required: true, // 设为必填项
            },
        },
    });

    //提交
    function edit_submit() {
        let flag = $("#editAppform").valid();
        if (!flag) {
            //没有通过验证，就不进行下面的ajax提交了
            return;
        }
        var data = new Object();
        data['AppId'] = $("#editAppform input[name='AppId']").val();
        data['AppName'] = $("#editAppform input[name='AppName']").val();
        $.ajax({
            url: '/appname/updateAppname',    //请求的url地址
            dataType: "json",
            async: true,
            data: data,
            type: "POST",
            success: function (reg) {
                if (reg.code == 200) {
                    var successStr = tipsInfo(1, reg.msg);
                    $('.AppTips').html(successStr);
                    setTimeout(function () {
                        $('#editAppModal').modal('hide');
                    });
                    $('#tableL04').bootstrapTable('load', reg.listapp);
                    $('#tableL05').bootstrapTable('load', reg.appversionlists);
                    sessionStorage.rowsInfo = JSON.stringify(reg.appnameAppId);
                    var app = $("#editAppform input[name='AppId']").val();
                    $("#tableL04 tr[data-uniqueid="+app+"]").addClass("selected");

                    var verStr = "<option value=''>--请选择--</option>";
                    $.each(reg.listapp, function (n, v) {
                        verStr += '<option value="' + v.AppId + '">' + v.AppName + '</option>';
                    });
                    $('#AppId').html(verStr);
                    $('#AppIds').html(verStr);


                } else {
                    var errorStr = tipsInfo(0, reg.msg);
                    $('.AppTips').html(errorStr);
                }
            },
            error: function () {
                var errorStr = tipsInfo(0, '提交失败！');
                $('.AppTips').html(errorStr);
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
    $("#AppVerform").validate({
    rules: {
        AppVer: {
            required: true, // 设为必填项
        },
        remark: {
            required: true,
        },
        AppId:{
            required: true, // 设为必填项
        },
        AppName:{
            required: true, // 设为必填项
        },
        // RegularFile:{
        //     required: true, // 设为必填项
        // },
        // JsonFile:{
        //     required: true, // 设为必填项
        // },
        AppDate:{
            required: true, // 设为必填项
            // dateISO:true
        },
    },
    // messages: {
    //     RegularFile: "没有上传文件",
    //     JsonFile:"没有上传文件"
    // },

    submitHandler: function (form) {
        $(form).ajaxSubmit();
    },

    });
    //提交版本信息
    function saveAppSev() {
        let flag = $("#AppVerform").valid();
        if (!flag) {
            //没有通过验证，就不进行下面的ajax提交了
            return;
        }
        if(regularFile==null || regularFile=="") {
            $("#birthRegularFile").css("color", "red");
            // $("#birthRegularFile").css("position", "absolute");
            // $("#birthRegularFile").css(" margin-left", "-15px");
            $("#birthRegularFile").text("没有上传文件");
            return false;
        }
        if(jsonFile==null || jsonFile=="") {
            $("#birthJsonFile").css("color", "red");
            $("#birthJsonFile").text("没有上传文件");
            return false;
        }
        var data = new Object();
        data['AppId'] = $('#AppVerform select[name="AppId"]').val();
        // data['Id'] = $('#AppVerform input[name="Id"]').val();
        data['AppName'] = $("#AppId option:selected").text();
        data['AppVer'] = $('#AppVerform input[name="AppVer"]').val();
        data['AppDate'] = $('#AppVerform input[name="AppDate"]').val();
        // data['DownPath'] = $('#AppVerform input[name="DownPath"]').val();
        data['RegularFile'] = regularFile;
        data['JsonFile'] = jsonFile;
        data['AppFlag'] = $('#AppVerform input[name="AppFlag"]:checked').val();
        data['remark'] = $('#remark').val();
        $.ajax({
            url: '/appversionlist/addAppversionlist',
            dataType: "json",
            async: true,
            data: data,
            type: "POST",
            success: function (reg) {
                if (reg.code == 200) {
                    var successStr = tipsInfo(1, reg.msg);
                    $('.AppVerTips').html(successStr);
                    setTimeout(function () {
                        $('#AppVerModal').modal('hide');
                    });
                    $('#tableL05').bootstrapTable('load', reg.data);
                    $('.selected').removeClass('selected');
                    $("#tableL04 tr[data-uniqueid="+reg.data[0].AppId+"]").addClass("selected");
                } else {
                    var errorStr = tipsInfo(0, reg.msg);
                    console.log(errorStr);
                    $('.AppVerTips').html(errorStr);
                }
            },
            error: function () {
                var errorStr = tipsInfo(0, '提交失败！');
                $('.AppVerTips').html(errorStr);
            }
        });

    }

    $("#editAAppPerform").validate({
        rules: {
            AppVer: {
                required: true, // 设为必填项
            },
            remark: {
                required: true,
            },
            AppId:{
                required: true, // 设为必填项
            },
            AppName:{
                required: true, // 设为必填项
            }
        },
    });

    //提交版本信息
    function editAppSev() {
        let flag = $("#editAAppPerform").valid();
        if (!flag) {
            //没有通过验证，就不进行下面的ajax提交了
            return;
        }
        var data = new Object();
        data['AppId'] = $('#editAAppPerform select[name="AppIds"]').val();
        data['Id'] = $('#editAAppPerform input[name="Id"]').val();
        data['AppName'] = $("#AppIds option:selected").text();
        data['AppVer'] = $('#editAAppPerform input[name="AppVer"]').val();
        data['AppDate'] = $('#editAAppPerform input[name="AppDate"]').val();
        data['AppFlag'] = $('#editAAppPerform input[name="AppFlag"]:checked').val();
        data['remark'] =  $('#remarks').val();
        $.ajax({
            url: '/appversionlist/updateAppversionlist',
            dataType: "json",
            async: true,
            data: data,
            type: "POST",
            success: function (reg) {
                if (reg.code == 200) {
                    var successStr = tipsInfo(1, reg.msg);
                    $('.AppVerTipsA').html(successStr);
                    setTimeout(function () {
                        $('#editAAppVerModal').modal('hide');
                    });
                    $('#tableL05').bootstrapTable('load', reg.listapp);

                    sessionStorage.AppverInfo = JSON.stringify(reg.aversion);

                    $("#tableL05 tr[data-index="+dateIndex+"]").addClass("selected");
                } else {
                    var errorStr = tipsInfo(0, reg.msg);
                    console.log(errorStr);
                    $('.AppVerTipsA').html(errorStr);
                }
            },
            error: function () {
                var errorStr = tipsInfo(0, '提交失败！');
                $('.AppVerTipsA').html(errorStr);
            }
        });
    }
</script>
</body>
</html>

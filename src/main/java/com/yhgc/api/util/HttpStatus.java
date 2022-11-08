package com.yhgc.api.util;

public class HttpStatus {
    /**
     * 请求成功（常用）
     */
    public static final int SUCCESS = 200;

    /**
     * 记录已存在
     */
    public static final int EXIST = 201;

    /**
     * 参数验证码错误
     */
    public static final int PARAMETER_ERROR = 400;

    /**
     * 登录验证码错误
     */
    public static final int LOGON_ERROR = 401;

    /**
     * 无操作权限
     */
    public static final int NO_POWER = 403;

    /**
     * 记录不存在
     */
    public static final int NO_EXIST = 404;

    /**
     * 资源没有被修改
     */
    public static final int RESOURCE_NO_ALTER = 500;
}

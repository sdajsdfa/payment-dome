package com.yhgc.api.config;
 
import com.alibaba.fastjson.JSONObject;
import com.yhgc.api.util.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
 
 
@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public R handleException(RuntimeException e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        if (msg.equals("无token，请重新登录")||msg.equals("token失效，请重新登录")||msg.equals("用户不存在，请重新登录")||msg.equals("token验证失败")) {
            return R.errorToken(msg);
        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("message", msg);
//        jsonObject.put("status",500);
        return R.error(500,msg);
    }
}
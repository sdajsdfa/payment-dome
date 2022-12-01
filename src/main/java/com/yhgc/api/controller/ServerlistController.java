package com.yhgc.api.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhgc.api.entity.Serverlist;
import com.yhgc.api.service.ServerlistService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 服务器列表 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-11-07
 */
@Api(tags = "服务器列表")
@RestController
@RequestMapping("/serverlist")
public class ServerlistController {

    private static final String SERVERS_IP_1 = "rsonline.net.cn";
    private static final String SERVERS_IP_2 = "whjiace.com";

    private static String ServersDownloadIP = SERVERS_IP_1;

    @Resource
    private ServerlistService serverlistService;

    /**
     *查询所有服务器列表
     * @return
     */
    @ApiOperation("查询所有服务器列表")
    @GetMapping(value = "/queryAllServerlist")
    public List queryAllServerlist() {
        List<Serverlist>  list = serverlistService.list();
        return list;
    }

    /**
     *查询所有服务器列表
     * @return
     */
    @ApiOperation("修改服务器列表")
    @PostMapping (value = "/updateServerlist")
    public R updateServerlist(@RequestBody Serverlist serverlist) {
        QueryWrapper<Serverlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", serverlist.getId());
        Boolean sl = serverlistService.update(serverlist,queryWrapper);
        if (!sl) {
            return R.error("修改失败");
        }
        return R.ok();
    }

    /**
     * 添加服务器列表
     * @param serverlist
     * @return
     */
    @ApiOperation("添加服务器列表")
    @PostMapping("/saveServerlist")
    public R saveServerlist(@RequestBody Serverlist serverlist) {
        Boolean si = serverlistService.save(serverlist);
        if (!si) {
            return R.error("添加失败");
        }
        return R.ok(serverlist);
    }

    /**
     * 删除服务器列表
     * @param id
     * @return
     */
    @ApiOperation("删除服务器列表")
    @GetMapping  (value = "/deleteServerlist")
    public R deleteServerlist(Integer id) {
        Boolean pi =  serverlistService.removeById(id);
        if (!pi) {
            return R.error("删除服务器列表");
        }
        return R.ok();
    }

    /**
     * 更新服务器列表
     * @return
     */
    @ApiOperation("更新服务器列表")
    @PostMapping (value = "/downloadFromJc")
    @Scheduled(cron = "0 0 2 ? * *") // cron表达式：每天 0:0:2 执行
    public R downloadFromJc() throws JsonProcessingException {
        SoapObject soapObject = new SoapObject("http://rsonline.net.cn/", "ServerShortInfoJsonListV3");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        String endPoint = "http://update." + ServersDownloadIP + "/rsservice.asmx";
        envelope.dotNet = false;
        HttpTransportSE transportSE = new HttpTransportSE(endPoint, 8000);

        //调用
        try {
            transportSE.call("http://" + ServersDownloadIP + "/ServerShortInfoJsonListV3", envelope);
        } catch (Exception e) {
            e.printStackTrace();
            if (ServersDownloadIP.equals(SERVERS_IP_1)) {
                ServersDownloadIP = SERVERS_IP_2;
                return downloadFromJc();
            } else {
                return null;
            }
        }

        Object obj = envelope.bodyIn;
        if (obj instanceof SoapFault) {
            return null;
        }
        SoapObject o = (SoapObject) obj;
        SoapPrimitive o2 = (SoapPrimitive) o.getProperty("ServerShortInfoJsonListV3Result");

        try {
            QueryWrapper<Serverlist> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("mark", 0);
            List<Serverlist> serverlist = serverlistService.list(queryWrapper);
            for (Serverlist sl:serverlist) {
                serverlistService.removeById(sl.getId());
            }
            JSONArray jsonArray = JSONArray.fromObject(o2.getValue());
            ObjectMapper objm = new ObjectMapper();
            JavaType javaType = objm.getTypeFactory().constructParametricType(List.class, Serverlist.class);
            List<Serverlist> list=objm.readValue(jsonArray.toString(),javaType);
            serverlistService.saveBatch(list);
        } catch (Exception e) {
            throw new RuntimeException("添加失败");
        }
        return R.ok();
    }
}


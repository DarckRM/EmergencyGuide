package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Entity.WeChat;
import com.emergencyguide.Service.Community.Impl.CustomerServiceImpl;
import com.emergencyguide.Service.Wechat.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create with Intellij IDEA
 *
 * @Author: WangJinPing
 * @Date: 2021/02/28/3:04
 * DESC:安全验证API控制
 */
@RestController
@RequestMapping("api/security")
@Api(value = "安全验证接口Controller", tags = { "安全验证API接口" })
@Slf4j
public class SecurityApiController {

    Logger logger = LoggerFactory.getLogger(SecurityApiController.class);

    @Autowired
    private WechatService wechatService;
    /**
     * 返回广告信息
     *
     * @return
     */
    @PostMapping("/getWeChatSession")
    @ResponseBody
    @ApiOperation(value="根据Code获取微信Session信息",response = WeChat.class)
    @ApiImplicitParam(name = "jsonStr", value = "{\"js_code\":\"****\"}",  paramType = "body", required = true, dataType =  "string")
    public String getWeChatSession(@RequestBody String jsonStr) {
        logger.info("-------------请求接口根据Code获取微信Session信息-----------"+jsonStr);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String resData = wechatService.getWeChatSession(jsonObject.getString("js_code"));
        return resData;
    }
}


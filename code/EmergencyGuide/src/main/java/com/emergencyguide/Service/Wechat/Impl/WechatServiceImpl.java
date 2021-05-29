package com.emergencyguide.Service.Wechat.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Config.ContextConfig;
import com.emergencyguide.Controller.Util.WeChatConfig;
import com.emergencyguide.Entity.WeChat;
import com.emergencyguide.Service.Wechat.WechatService;
import com.emergencyguide.Utils.HttpClientHelper;
import com.emergencyguide.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author DarckLH
 * @date 2021/5/27 19:12
 * @Description
 */
@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    private WeChatConfig config;
    @Autowired
    private RedisUtil redisUtil;

    public String getWeChatSession(String js_code) {
        StringBuilder requestUrl = new StringBuilder (config.getJsCode2SessionUrl());
        requestUrl.append("appid=");
        requestUrl.append(config.getAppId());
        requestUrl.append("&secret=");
        requestUrl.append(config.getSecret());
        requestUrl.append("&js_code=");
        requestUrl.append(js_code);
        requestUrl.append("&grant_type=");
        requestUrl.append(config.getGrantType());
        String res = HttpClientHelper.HttpGet(requestUrl.toString());
        WeChat data = JSONObject.parseObject(res, WeChat.class);
        if(data.getOpenid()!=null) {
            String token = DigestUtils.md5DigestAsHex(data.getOpenid().getBytes());
            // 将Token放置缓存
            redisUtil.set(token,data.getOpenid(), ContextConfig.TOKEN_EXPIRE);
            data.setToken(token);
        }
        return data.toString();
    }
}

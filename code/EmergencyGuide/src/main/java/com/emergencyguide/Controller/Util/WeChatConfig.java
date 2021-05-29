package com.emergencyguide.Controller.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author DarckLH
 * @date 2021/5/27 18:59
 * @Description
 */
@PropertySource(value = {"classpath:wechat.properties"})
@Component
public class WeChatConfig {

    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.grantType}")
    private String grantType;
    // 微信鉴权地址
    @Value("${wechat.jsCode2SessionUrl}")
    private String jsCode2SessionUrl;
    @Value("${wechat.payDomain}")
    private String payDomain;
    @Value("${wechat.serial_no}")
    private String serial_no;
    @Value("${wechat.tokenUrl}")
    private String tokenUrl;
    @Value("${wechat.codeUrl}")
    private String codeUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getJsCode2SessionUrl() {
        return jsCode2SessionUrl;
    }

    public void setJsCode2SessionUrl(String jsCode2SessionUrl) {
        this.jsCode2SessionUrl = jsCode2SessionUrl;
    }

    public String getPayDomain() {
        return payDomain;
    }

    public void setPayDomain(String payDomain) {
        this.payDomain = payDomain;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}

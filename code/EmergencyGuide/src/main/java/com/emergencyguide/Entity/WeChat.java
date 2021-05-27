package com.emergencyguide.Entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author DarckLH
 * @date 2021/5/27 19:14
 * @Description
 */
@ApiModel(value="微信登录凭证校验")
public class WeChat implements Serializable {
    @ApiModelProperty("用户唯一标识")
    private String openid;
    @ApiModelProperty("会话密钥")
    private String session_key;
    @ApiModelProperty("用户在开放平台的唯一标识符")
    private String uniond;
    @ApiModelProperty("错误码(-1:系统繁忙、0：请求成功、40029：code 无效、45011：频率限制)")
    private String errcode;
    @ApiModelProperty("错误信息")
    private String errmsg;
    @ApiModelProperty("身份令牌")
    private String token;
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUniond() {
        return uniond;
    }

    public void setUniond(String uniond) {
        this.uniond = uniond;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        if(openid!=null&&errcode==null){
            setErrcode("0");
            setErrmsg("获取成功");
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(this).toString();
    }

}

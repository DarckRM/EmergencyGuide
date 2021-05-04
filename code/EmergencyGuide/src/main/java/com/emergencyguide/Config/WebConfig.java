package com.emergencyguide.Config;

import com.emergencyguide.Utils.InterceptorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author DarckLH
 * @date 2021/5/5 3:01
 * @Description 在web配置文件中，实例化登录拦截器，添加规则
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    public void addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(new InterceptorUtils()).addPathPatterns("/index");

    }

}

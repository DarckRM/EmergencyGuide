package com.emergencyguide.Config;

import com.emergencyguide.Utils.InterceptorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String projectDir = System.getProperty("user.dir");
        String fileDir = "file:" + projectDir + "\\file/images\\";
//        logger.info("FileDir：" + fileDir);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        //配置访问图片时的对应请求，
        registry.addResourceHandler("file/images/**")//访问上传的图片
                .addResourceLocations(fileDir);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}

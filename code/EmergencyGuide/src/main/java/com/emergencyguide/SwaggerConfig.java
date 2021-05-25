package com.emergencyguide;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Create with Intellij IDEA
 *
 * @Author: WangJinPing
 * @Date: 2021/02/27/14:25
 * DESC:
 */
@Configuration
@EnableSwagger2
//是否开启swagger，正式环境一般是需要关闭的（避免不必要的漏洞暴露！），可根据springboot的多环境配置进行设置
public class SwaggerConfig {
    // swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {
        //添加head参数配置start

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                 .select()
                .apis(RequestHandlerSelectors.basePackage("com.emergencyguide.Controller.api"))
                .paths(PathSelectors.any())
                .build();

    }
    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("拼命小组")
                // 创建人信息
                .contact(new Contact("拼命小组",  "chenjie.ink",  "123@163.com"))
                // 版本号
                .version("1.0")
                // 描述
                .description("前端小程序所需相关API接口")
                .build();
    }
}

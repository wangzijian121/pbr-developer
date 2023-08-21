package com.zlht.pbr.algorithm.developer.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30) // v2 不同
                .apiInfo(Developer())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zlht.pbr.algorithm.developer.api")) // 设置扫描路径
                .build();
    }

    private ApiInfo Developer() {
        return new ApiInfoBuilder()
                .title("AI体态识别系统-算法开发者") // 设置文档标题
                .description("AI body recognition system-algorithm- developer interface") // 设置文档描述
                .version("1.0.0") // 设置文档版本
                .build();
    }
}
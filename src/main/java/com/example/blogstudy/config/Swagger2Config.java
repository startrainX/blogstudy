package com.example.blogstudy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author startRain
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${api.version:V1.0}")
    private String apiVersion = "V1.0";

    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("swagger-ui")
                .description("swagger")
                .version(apiVersion)
                .contact(new Contact("MySwagger", "127.0.0.1", ""))
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("MS-INFORMATION")
                .enable(true)
                // 扫描com.example.blogstudy.controller包下的所有接口
                .select().apis(RequestHandlerSelectors.basePackage("com.example.blogstudy.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }
}

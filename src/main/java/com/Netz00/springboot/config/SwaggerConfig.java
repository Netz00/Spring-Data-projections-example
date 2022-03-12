package com.Netz00.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {


    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30).select().paths(regex("/api/v1.*") ).build()
                .apiInfo(getInfo());
    }

    private ApiInfo getInfo (){
        return new ApiInfoBuilder().title("University Api Documentation").description("Spring boot application").license("Education").version("0.0.1").build();
    }


}

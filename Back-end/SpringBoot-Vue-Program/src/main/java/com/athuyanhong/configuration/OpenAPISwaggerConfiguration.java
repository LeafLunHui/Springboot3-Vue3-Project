package com.athuyanhong.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-09-0009 21:28
 * @Package_Name com.athuyanhong.configuration
 * @Project_Name SpringBoot-Vue-Program
 */

@Configuration
public class OpenAPISwaggerConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SpringBoot Vue API")
                        .description("This is an application that uses springboot and vue\n" +
                                "\n")
                        .version("v1.0.1")
                        .license(new License().name("Apache 2.0").url("")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot Wiki Documentation")
                        .url(""));
    }

    @Bean
    public GroupedOpenApi articleApi() {
        return GroupedOpenApi.builder()
                .group("ArticleController")
                .pathsToMatch("/article/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userApi(){
        return GroupedOpenApi.builder()
                .group("UserController")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi CategoryApi(){
        return GroupedOpenApi.builder()
                .group("CategoryController")
                .pathsToMatch("/category/**")
                .build();
    }



}

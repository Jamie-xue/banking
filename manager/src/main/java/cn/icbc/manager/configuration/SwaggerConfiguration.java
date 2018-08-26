package cn.icbc.manager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: asus
 * @Date: 2018/8/25 18:13
 */
//@EnableSwagger2
//@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket controllerApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("itheima")
                .apiInfo(apiInfo())
                .select()
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("HTTP de API")
                .description("管理端接口")
                .termsOfServiceUrl("http://cn.icbc.io")
                .contact("icbc")
                .license("Uplooking")
                .licenseUrl("https://github.com/jack")
                .version("3.1.0")
                .build();
    }
}

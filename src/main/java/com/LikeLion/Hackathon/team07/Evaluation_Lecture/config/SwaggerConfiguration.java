package com.LikeLion.Hackathon.team07.Evaluation_Lecture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.LikeLion.Hackathon.team07.Evaluation_Lecture"))
                // 스웨거가 RestController를 전부 스캔을 한다.
                // basePackage => 어디를 범위로 스캔을 할 것인지 작성
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("강의 평가 서비스")
                .description("강의 평가 서비스 입니다")
                .version("0.0.0")
                .termsOfServiceUrl("https://catholic.ac.kr/")
                .license("LICENSE")
                .licenseUrl("")
                .build();
    }

    // 완료가 되었으면 오른쪽 URL 로 접속 => http://localhost:8090/swagger-ui.html
    //참고 - https://antstudy.tistory.com/251
}
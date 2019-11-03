package com.wwl.template;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.singletonList;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 配置
 */

@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger-doc")
public class SwaggerConfig implements WebMvcConfigurer {

//	@Bean
//	public Docket createRestApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(apiInfo()).select()
//				.apis(RequestHandlerSelectors
//						.withMethodAnnotation(ApiOperation.class))
//				.paths(PathSelectors.any())
//				.build();
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/static/swagger/");
	}

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 加了ApiOperation注解的method，才生成接口文档
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				// 加了RestController注解的class，才生成接口文档
//				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				// 包下的类，才生成接口文档
				// .apis(RequestHandlerSelectors.basePackage("cn.iclass.controller"))
				.paths(PathSelectors.any()).build().securitySchemes(security())
				.securityContexts(Arrays.asList(securityContext()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("APIS").description("APIS").termsOfServiceUrl("APIS")
				.version("1.0.0-SNAPSHOT").build();
	}

	private List<ApiKey> security() {
		return newArrayList(new ApiKey("token", "token", "header"));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return singletonList(new SecurityReference("token", authorizationScopes));
	}

}
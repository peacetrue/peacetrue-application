package com.github.peacetrue.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMVC 应用配置。
 *
 * @author peace
 **/
@Configuration(proxyBeanMethods = false)
public class PeacetrueWebMvcConfiguration {

    /**
     * 开发环境允许跨域。
     *
     * @return 跨域配置
     */
    @Bean
    @Profile("dev")
    public WebMvcConfigurer corsWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }


}

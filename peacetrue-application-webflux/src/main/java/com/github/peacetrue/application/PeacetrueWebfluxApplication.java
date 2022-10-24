package com.github.peacetrue.application;

import com.github.peacetrue.spring.formatter.date.AutomaticDateFormatter;
import com.github.peacetrue.spring.formatter.date.AutomaticLocalDateFormatter;
import com.github.peacetrue.spring.formatter.date.AutomaticLocalDateTimeFormatter;
import com.github.peacetrue.spring.formatter.date.AutomaticTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.data.web.ReactiveSortHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

/**
 * WebFlux 应用。
 *
 * @author peace
 */
@SpringBootApplication
public class PeacetrueWebfluxApplication {

    /**
     * 程序启动入口。
     *
     * @param args 应用参数
     */
    public static void main(String[] args) {
        SpringApplication.run(PeacetrueWebfluxApplication.class, args);
    }

    /**
     * 开发环境允许跨域。
     *
     * @return 跨域配置
     */
    @Bean
    @Profile("dev")
    public WebFluxConfigurer corsWebFluxConfigurer() {
        return new WebFluxConfigurer() {
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

    /**
     * 支持解析分页参数，Spring 未自动装配。
     *
     * @return 分页参数解析配置
     */
    @Bean
    public WebFluxConfigurer pageableWebFluxConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
                configurer.addCustomResolver(new ReactivePageableHandlerMethodArgumentResolver());
                configurer.addCustomResolver(new ReactiveSortHandlerMethodArgumentResolver());
            }
        };
    }

    /**
     * 添加日期格式化器。
     *
     * @return 日期格式化器配置
     */
    @Bean
    public WebFluxConfigurer dateFormatterWebFluxConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addFormatter(new AutomaticDateFormatter());
                registry.addFormatter(new AutomaticTimeFormatter());
                registry.addFormatter(new AutomaticLocalDateFormatter());
                registry.addFormatter(new AutomaticLocalDateTimeFormatter());
            }
        };
    }

}

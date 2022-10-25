package com.github.peacetrue.application;

import com.github.peacetrue.spring.formatter.date.AutomaticLocalDateFormatter;
import com.github.peacetrue.spring.formatter.date.AutomaticLocalDateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.data.web.ReactiveSortHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

/**
 * WebFlux 应用配置。
 *
 * @author peace
 **/
@Configuration(proxyBeanMethods = false)
public class PeacetrueWebFluxConfiguration {

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
    public WebFluxConfigurer localDateFormatterWebFluxConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addFormatter(new AutomaticLocalDateFormatter());
                registry.addFormatter(new AutomaticLocalDateTimeFormatter());
            }
        };
    }

}

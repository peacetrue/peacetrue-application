package com.github.peacetrue.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author peace
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = {
                JacksonAutoConfiguration.class,
                HttpMessageConvertersAutoConfiguration.class,
                ErrorMvcAutoConfiguration.class,
                DispatcherServletAutoConfiguration.class,
                WebMvcAutoConfiguration.class,
                ServletWebServerFactoryAutoConfiguration.class,
                WebMvcArgumentResolverTest.DateController.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Slf4j
@ActiveProfiles({"test", "date-format"})
class WebMvcArgumentResolverTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wrapper {
        private LocalDateTime localDateTime;
    }

    @Slf4j
    @RestController
    public static class DateController {

        @GetMapping("/localDateTime")
        public Wrapper resolveLocalDateTimeForm(Wrapper wrapper) {
            log.info("wrapper: {}", wrapper);
            return wrapper;
        }

        @PostMapping(value = "/localDateTime", consumes = MediaType.APPLICATION_JSON_VALUE)
        public Wrapper resolveLocalDateTimeJson(@RequestBody Wrapper wrapper) {
            log.info("wrapper: {}", wrapper);
            return wrapper;
        }
    }

    /**
     * @see org.springframework.web.method.annotation.RequestParamMethodArgumentResolver
     * @see org.springframework.web.method.support.InvocableHandlerMethod
     * @see org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory
     * @see org.springframework.boot.autoconfigure.web.format.WebConversionService
     * @see org.springframework.core.convert.support.ObjectToObjectConverter
     * @see org.springframework.core.convert.support.GenericConversionService.Converters
     * @see org.springframework.format.datetime.standard.DateTimeConverters
     * @see org.springframework.format.annotation.DateTimeFormat
     */
    @Test
    void localDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String now = localDateTime.toString();
        log.info("now: {}", now);
        Wrapper responseData = this.restTemplate.getForObject("/localDateTime?localDateTime={0}", Wrapper.class, now);
        log.info("responseData: {}", responseData);
        Assertions.assertEquals(now, responseData.localDateTime.toString());

        Wrapper wrapper = new Wrapper(localDateTime);
        responseData = this.restTemplate.postForObject("/localDateTime", new HttpEntity<>(wrapper), Wrapper.class);
        log.info("responseData: {}", responseData);
        Assertions.assertEquals(wrapper, responseData);
    }
}

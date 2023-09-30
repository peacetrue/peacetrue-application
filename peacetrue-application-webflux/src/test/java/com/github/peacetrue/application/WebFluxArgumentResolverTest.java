package com.github.peacetrue.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author peace
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = {
                WebFluxAutoConfiguration.class,
                HttpHandlerAutoConfiguration.class,
                ReactiveWebServerFactoryAutoConfiguration.class,
                PeacetrueWebFluxConfiguration.class,
                WebFluxArgumentResolverTest.DateController.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Slf4j
@ActiveProfiles("unittest")
class WebFluxArgumentResolverTest {

    @Autowired
    private WebTestClient webClient;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wrapper {
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private LocalDateTime localDateTime;
    }

    @Slf4j
    @RestController
    public static class DateController {

        @GetMapping("/localDateTime")
        public Wrapper resolveLocalDateTime(Wrapper wrapper) {
            log.info("wrapper: {}", wrapper);
            return wrapper;
        }

        @PostMapping(value = "/localDateTime", consumes = MediaType.APPLICATION_JSON_VALUE)
        public Wrapper resolveLocalDateTimeBody(@RequestBody Wrapper wrapper) {
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
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = localDateTime.toString().split("\\.")[0];// 去掉秒后面的部分
        log.info("now: {}", now);

        webClient.get()
                .uri("/localDateTime?localDateTime={0}", now)
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(result -> {
                    log.info("result: {}", new String(Objects.requireNonNull(result.getResponseBody())));
                });

        Wrapper wrapper = new Wrapper(localDateTime);
        webClient.post()
                .uri("/localDateTime")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(wrapper))
                .exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(result -> {
                    log.info("result: {}", new String(Objects.requireNonNull(result.getResponseBody())));
                });
    }

}

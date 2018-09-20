//: guru.springframework.restclientapp.infrastructure.config.RestTemplateConfig.java


package guru.springframework.restclientapp.infrastructure.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {

        return restTemplateBuilder.build();
    }

}///~
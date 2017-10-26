//: guru.springframework.spring5didemo.config.PropertyConfig.java

package guru.springframework.spring5didemo.config;


import guru.springframework.spring5didemo.examplebeans.FakeDataSource;
import guru.springframework.spring5didemo.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;


/*
 * The @PropertySource annotation, as a convenient mechanism for adding
 * property sources to the environment. This annotation is to be used in
 * conjunction with Java based configuration and the @Configuration annotation
 *
 * The Java @PropertySource annotation does not automatically register a
 * PropertySourcesPlaceholderConfigurer with Spring. Instead, the bean must be
 * explicitly defined in the configuration to get the property resolution
 * mechanism working
 *
 * Injecting a property with the @Value annotation is straightforward
 */
@Configuration
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:jms.properties")
})
public class PropertyConfig {

    @Autowired
    Environment env;

    @Value("${guru.username}")
    String user;

    @Value("${guru.password}")
    String password;

    @Value("${guru.dburl}")
    String url;

    @Value("${guru.jms.username}")
    String jmsUsername;

    @Value("${guru.jms.password}")
    String jmsPassword;

    @Value("${guru.jms.url}")
    String jmsUrl;

    @Bean
    public FakeDataSource fakeDataSource() {

        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUser(this.env.getProperty("USERNAME"));
        fakeDataSource.setPassword(this.env.getProperty("PASSWORD"));
        fakeDataSource.setUrl(this.url);
        return fakeDataSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer =
                new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker(this.jmsUsername,
                this.jmsPassword, this.jmsUrl);
        return fakeJmsBroker;
    }

}///~
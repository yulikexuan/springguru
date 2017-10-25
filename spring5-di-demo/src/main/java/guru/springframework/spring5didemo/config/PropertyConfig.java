//: guru.springframework.spring5didemo.config.PropertyConfig.java

package guru.springframework.spring5didemo.config;


import guru.springframework.spring5didemo.examplebeans.FakeDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:datasource.properties")
public class PropertyConfig {

    @Autowired
    Environment env;

    @Value("${guru.username}")
    String user;

    @Value("${guru.password}")
    String password;

    @Value("${guru.dburl}")
    String url;

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

}///~
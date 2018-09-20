//: guru.springframework.spring5didemo.config.PropertyConfig.java

package guru.springframework.spring5didemo.config;


import guru.springframework.spring5didemo.examplebeans.FakeDataSource;
import guru.springframework.spring5didemo.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;


@Configuration
@PropertySource("classpath:example.yml")
public class PropertyConfig {

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

    /*
     * YAML files can’t be loaded via the @PropertySource annotation. So in the
     * case that you need to load values that way, you need to use a properties
     * file.
     */
    @Value("${names}")
    List<String> names;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public FakeDataSource fakeDataSource() {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUser(this.user);
        fakeDataSource.setPassword(this.password);
        fakeDataSource.setUrl(this.url);
        return fakeDataSource;
    }

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        return new FakeJmsBroker(this.jmsUsername, this.jmsPassword, this.jmsUrl);
    }

}///~
//: guru.springframework.spring5didemo.config.PropertyConfig.java

package guru.springframework.spring5didemo.config;


import guru.springframework.spring5didemo.examplebeans.FakeDataSource;
import guru.springframework.spring5didemo.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
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
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker(this.jmsUsername,
                this.jmsPassword, this.jmsUrl);
        return fakeJmsBroker;
    }

}///~
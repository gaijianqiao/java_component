package gc.information.getImageInfo.springboot.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
@Configuration
public class BeanConfiguration {
    @Bean
    @ConfigurationProperties(prefix ="gc")
    public Properties pageHelperProperties() {
        return new Properties();
    }
}

package gc.information.getImageInfo.springboot.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MyAutoProperties.class)
public class MyConfiguration {
    @Autowired
    MyAutoProperties properties;

    public String getName(){
        return properties.getName();
    }
}

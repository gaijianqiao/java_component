package gc.information.getImageInfo;


import gc.information.getImageInfo.springboot.autoconfig.MyAutoProperties;
import gc.information.getImageInfo.springboot.autoconfig.MyConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

@SpringBootTest
class SpringBootLearningTest {

    @Autowired private MyConfiguration properties;

    @Autowired
    @Qualifier("pageHelperProperties")
    private Properties pro;
    @Test
    public void testAutoProperties(){
        String name = properties.getName();
        String name1 = (String) pro.get("name");
        System.out.println();
    }
}

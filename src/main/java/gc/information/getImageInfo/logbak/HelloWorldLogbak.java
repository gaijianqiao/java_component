package gc.information.getImageInfo.logbak;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Slf4j
public class HelloWorldLogbak {
    public static void main(String[] args) throws InterruptedException {
        while (true){
            Thread.sleep(30);
            log.info("hello world");
        }

//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        StatusPrinter.print(lc);
    }
}

package gc.information.getImageInfo.threadloacal;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TestThreadLocal {

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                log.info("current thead id:{} getId:{}",Thread.currentThread().getName().toString(),ThreadId.get());
                log.info("current thead id:{} getId:{}",Thread.currentThread().getName().toString(),ThreadId.get());
                log.info("current thead id:{} getId:{}",Thread.currentThread().getName().toString(),ThreadId.get());
            }).start();
        }
    }

}

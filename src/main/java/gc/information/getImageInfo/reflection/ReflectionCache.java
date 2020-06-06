package gc.information.getImageInfo.reflection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionCache {
    public static void main(String[] args) {
        try {
            Class<? extends MyCache> cacheClass = (Class<? extends MyCache>) Class.forName("gc.information.getImageInfo.reflection.MyPersonCache");
            MyCache myCache = cacheClass.newInstance();
            myCache.setValByKey("k1", "v1");
            log.info(myCache.getByKey("k1").toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}

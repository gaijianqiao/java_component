package gc.information.getImageInfo.reflection;

import java.util.concurrent.ConcurrentHashMap;

public class MyPersonCache implements MyCache<String,String> {
    private ConcurrentHashMap<String,String> myCache = new ConcurrentHashMap<>();
    @Override
    public String getByKey(String s) {
        return myCache.get(s);
    }

    @Override
    public void setValByKey(String s, String s2) {
        myCache.put(s,s2);
    }
}

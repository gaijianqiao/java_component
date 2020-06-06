package gc.information.getImageInfo.reflection;

public interface MyCache<K, V> {
    V getByKey(K k);

    void setValByKey(K k, V v);
}

package gc.information.getImageInfo.util;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 这是一个循环队列，队循环队列的操作需要保证线程安全
 * <p>
 * 在生成实例的时候，需要传入，满足队列的列表。
 * <p>
 * 队列中的顺序是随机分配的
 *
 * @param <T>
 */
public class LoopQueue<T> {

    private ConcurrentLinkedQueue<T> queue = new ConcurrentLinkedQueue();

    public LoopQueue(List<T> list) {
        Collections.shuffle(list);
        queue.addAll(list);
    }

    synchronized public T getCurrentNode() {
        T t = queue.poll();
        queue.add(t);
        return t;
    }
}

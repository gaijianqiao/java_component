package gc.information.getImageInfo.clazz;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TestClassComponent {
    public static void main(String[] args) {

        String[] strs = new String[2] ;
//        component 的意思是代表了一个集合中的一个元素的类型
//        这个componetType的方法，实际上是判断一个对象是否是数组的依据，如果是一个数组的话，那么会有返回值的。
        Class cla = strs.getClass().getComponentType();
        log.info("array class:{}",cla);

        String str = "abcd";
        Class claStr = str.getClass().getComponentType();
        log.info("String class:{}",claStr);
    }
}

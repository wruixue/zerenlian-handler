package zhizelianorder.handler;

import java.io.Serializable;

/**
 * Created by wangziqing on 16/9/29.
 */
public class HandlerContext<T> implements Serializable {
    private T t;

    public T get(){
        return t;
    }

    public HandlerContext(T t){
        this.t = t;
    }

    public static <T> HandlerContext<T> build(T t){
        return new HandlerContext(t);
    }

    @Override
    public String toString() {
        return "HandlerContext{" +
                "t=" + t +
                '}';
    }
}

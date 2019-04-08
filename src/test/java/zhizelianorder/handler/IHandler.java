package zhizelianorder.handler;

/**
 *
 * @author wangziqing
 * @date 16/9/29
 */
public interface IHandler<T>  {
    HandlerContext<T> handle(HandlerContext<T> context);
}

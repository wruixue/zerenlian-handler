package zhizelianorder.ordercreate.group;

import org.springframework.stereotype.Service;
import zhizelianorder.handler.HandlerContext;
import zhizelianorder.handler.IHandler;
import zhizelianorder.ordercreate.OrderCreateContext;

/***
 * 订单创建错误
 *
 * 创建失败_作废订单逻辑 state=FALSE
 *
 * @author xiongchuang
 * @date 2018-01-15
 */
@Service
public class OrderCreateErrorHandler implements IHandler<OrderCreateContext> {


    protected void cancelOrder(HandlerContext<OrderCreateContext> context) {
        //只有return 没有异常返回
        //使订单state-false

    }

    @Override
    public HandlerContext<OrderCreateContext> handle(HandlerContext<OrderCreateContext> context) {
        this.cancelOrder(context);
        return context;
    }
}

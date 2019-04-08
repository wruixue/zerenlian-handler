package zhizelianorder.ordercreate.group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhizelianorder.handler.HandlerContext;
import zhizelianorder.handler.IHandler;
import zhizelianorder.ordercreate.OrderCreateContext;

/***
 * 订单创建完成  报错 可以让订单创建失败
 * @author xiongchuang
 * @date 2018-01-15
 */
@Service
public class OrderCreateCompleterHandler implements IHandler<OrderCreateContext> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreateCompleterHandler.class);

    @Override
    public HandlerContext<OrderCreateContext> handle(HandlerContext<OrderCreateContext> context) {
        OrderCreateContext orderCreateContext = context.get();
        System.out.println("OrderCreateCompleterHandler:订单创建完成  报错 可以让订单创建失败" + orderCreateContext.toString());


        return context;
    }
}

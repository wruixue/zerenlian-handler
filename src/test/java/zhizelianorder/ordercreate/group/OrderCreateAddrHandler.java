package zhizelianorder.ordercreate.group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhizelianorder.handler.HandlerContext;
import zhizelianorder.handler.IHandler;
import zhizelianorder.ordercreate.OrderCreateContext;

/***
 * 创建订单状态
 * @author xiongchuang
 * @date 2018-01-15
 */
@Service
public class OrderCreateAddrHandler implements IHandler<OrderCreateContext> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreateAddrHandler.class);

    @Override
    public HandlerContext<OrderCreateContext> handle(HandlerContext<OrderCreateContext> context) {
        //获取链条参数
        OrderCreateContext orderCreateContext = context.get();


        return context;
    }

}

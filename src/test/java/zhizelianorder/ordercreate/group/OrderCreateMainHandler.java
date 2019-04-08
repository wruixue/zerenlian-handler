package zhizelianorder.ordercreate.group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhizelianorder.handler.HandlerContext;
import zhizelianorder.handler.IHandler;
import zhizelianorder.ordercreate.OrderCreateContext;

/***
 * 创建订单主要信息
 * @author xiongchuang
 * @date 2018-01-15
 */
@Service
public class OrderCreateMainHandler implements IHandler<OrderCreateContext> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreateMainHandler.class);

    protected static final String DELIVERY_DESC_FORMAT = "%s-%s";
    protected static final String EXPECTFETCH_DATETIME_FORMAT = "%s %s:00";


    @Override
    public HandlerContext<OrderCreateContext> handle(HandlerContext<OrderCreateContext> context) {
        OrderCreateContext OrderCreateContext = context.get();





        return context;
    }
}

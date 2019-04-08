package zhizelianorder.ordercreate;

import zhizelianorder.handler.Handler;
import zhizelianorder.handler.HandlerStart;
import zhizelianorder.ordercreate.group.OrderCreateAddrHandler;
import zhizelianorder.ordercreate.group.OrderCreateCompleterHandler;
import zhizelianorder.ordercreate.group.OrderCreateErrorHandler;
import zhizelianorder.ordercreate.group.OrderCreateMainHandler;

/**
 * @author wangrx
 * @date 2019/4/4
 */
public interface Group {

    /**
     * 创建订单默认职责链
     */
    HandlerStart<OrderCreateContext> DEFAULT = Handler.handler(OrderCreateAddrHandler.class)
            .nextHandler(OrderCreateCompleterHandler.class)
            .nextHandler(OrderCreateErrorHandler.class)
            .nextHandler(OrderCreateMainHandler.class)
            .end();

    /**
     * 另一个订单默认职责链
     */
    HandlerStart<OrderCreateContext> BALANCE_ORDER = Handler.handler(OrderCreateAddrHandler.class)
            .nextHandler(OrderCreateCompleterHandler.class)
            .nextHandler(OrderCreateErrorHandler.class)
            .nextHandler(OrderCreateMainHandler.class)
            .end();


}

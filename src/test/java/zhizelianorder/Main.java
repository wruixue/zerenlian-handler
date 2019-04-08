package zhizelianorder;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zhizelianorder.context.OrderConstants;
import zhizelianorder.handler.HandlerContext;
import zhizelianorder.handler.HandlerStart;
import zhizelianorder.ordercreate.Group;
import zhizelianorder.ordercreate.OrderCreateContext;
import zhizelianorder.ordercreate.group.OrderCreateErrorHandler;

/**
 * @author wangrx
 * @date 2019/4/4
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Autowired
    private OrderCreateErrorHandler orderCreateErrorHandler;

    @Test
    public void mainTest() {
        OrderCreateContext OrderCreateContext = new OrderCreateContext();
        OrderCreateContext.setUserId("userId");

        OrderCreateContext.setRequest("request");
        OrderCreateContext.setOperatorUserId(OrderConstants.OperatorUserId.MOBILE_USER + "");
        OrderCreateContext.setMchId("MerchId");

        //TODO: 2019/4/4 11:44 wrx 入口
        OrderCreateContext = this.save(OrderCreateContext, Group.DEFAULT);


    }

    /**
     * 订单创建的成功事件
     *
     * @param context
     */
    private void orderCreateSuccess(OrderCreateContext context) {

    }


    private OrderCreateContext save(OrderCreateContext OrderCreateContext, HandlerStart<OrderCreateContext> handlerStart) {


        LOGGER.info("创建订单参数：{}", JSON.toJSONString(OrderCreateContext.getRequest()));

        HandlerContext<zhizelianorder.ordercreate.OrderCreateContext> context = HandlerContext.build(OrderCreateContext);

        try {
            context = handlerStart.start(context);
        } catch (Exception e) {
            if (OrderCreateContext != null && OrderCreateContext.getExpressOrderEntity() != null) {
                //TODO: 2019/4/4 10:04 wrx
                //应该像这个处理id数据 OrderCreateContext.getOrderEntity().getId()
                //暂时是先处理拟造数据
                Long orderId = 1L;
                LOGGER.error("订单创建成功事件 出错 orderId:{}", orderId, e);
            } else {
                LOGGER.error("创建订单 出错", e);
            }

            orderCreateErrorHandler.handle(context);
            throw e;
        }

        try {
            this.orderCreateSuccess(OrderCreateContext);
        } catch (Exception e) {
            if (OrderCreateContext != null && OrderCreateContext.getExpressOrderEntity() != null) {
                //TODO: 2019/4/4 10:04 wrx
                //应该像这个处理id数据 OrderCreateContext.getOrderEntity().getId()
                //暂时是先处理拟造数据
                Long orderId = 1L;
                LOGGER.error("订单创建成功事件 出错 orderId:{}", orderId, e);
            } else {
                LOGGER.error("订单创建成功事件 出错", e);
            }
        }

        return context == null ? null : context.get();
    }


}

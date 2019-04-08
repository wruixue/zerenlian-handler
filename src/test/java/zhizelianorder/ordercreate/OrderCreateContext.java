package zhizelianorder.ordercreate;

import lombok.Data;

import java.util.List;

/***
 *
 * @author xiongchuang
 * @date 2018-01-15
 */
@Data
public class OrderCreateContext {
    //传入参数
    private String userId;
    private String operatorUserId;
    private String request;

    private String mchId;


    //链条参数
    private String expressShopInfo;
    private String expressShopDestAddr;
    private String expressShopFromAddr;
    private String addrDistance;
    private String deliveryFromDesc;
    private String freight;
    private String expressOrderDiscountCreate;
    private String expressOrderSerial;

    private String expressOrderEntity;
    private String expressOrderAddrFromEntity;
    private String expressOrderAddrDestEntity;
    private String expressOrderStatusEntity;

    private String expressOrderProxyEntity;
    private List<String> expressOrderProxyDetailEntities;

    private String expressOrderDiscountEntity;

    private String expressOrderTradeEntity;
    private String tradeResult;

    //ExpressBalanceOrder
    private String balancePayResponse;

}

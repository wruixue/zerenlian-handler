package zhizelianorder.context;


public enum OrderExceptionConstants {
//public enum ExpressOrderExceptionConstants implements BaseExceptionConstants {

    ORDER_CANCLED_ERR(1, "订单取消失败"),
    ORDER_STATUS_NOT_FIND(2, "订单状态更改错误，当前更改状态不存在!"),
    ORDER_CANCLED_STATUS_NOT_ANCLED(3, "订单取消失败，订单不能取消"),
    ORDER_STATUS_NOT_FIND_ORDER_STATUS(4, "订单状态更改错误，当前更改状态不存在!"),
    ORDER_CANCLED_STATUS_NO_CREATED(5, "订单取消失败，订单不是待支付订单"),
    ORDER_CANCLED_REFUNED_ERR(6, "订单取消失败,退款失败"),
    ORDER_CREATE_NO_FROM_ADDR(7, "下单失败，您还没有添加寄件地址"),
    ORDER_NOT_ALLOW_ALLOT(8, "订单不允许分单"),
    COURIER_MAX_CARRIED_NUM(9, "超过快递最大携带单量"),
    ORDER_CREATE_FETCHDATETIME_ERR(10, "下单失败，您预约的时间已过当前时间"),
    ORDER_NOT_ALLOW_UPDATE_COURIER(11, "订单未送达，请用手动分单修改快递员"),
    ORDER_NOT_ALLOW_SWITCH_COURIER(12, "配送中订单，已完成订单不能转单"),
    ORDER_ALLOT_TO_THIS_COURIER(13, "已分配给此快递员"),
    ORDER_CREATE_NO_IN_MCH_TIME(14, "该时间内配送站未营业，请另选时间"),
    ORDER_CREATE_THIRD_ORDER_IS_CREATED(15, "该第三方订单号已下过订单，请勿重复下单"),
    ORDER_CREATE_PROXY_NO_PARAM(16, "跑腿单参数错误"),
    ORDER_CREATE_PROXY_ADDR_ERR(17, "门店地址code错误"),
    ORDER_CREATE_SHOP_NO_EXAMINED(18, "您的信息暂未通过审核，暂时不能下单"),
    ORDER_FAST_INFO_OLD(19, "订单不是待录单状态，请刷新后重试"),
    ORDER_FAST_CREATE_NO_BALANCE(20, "余额可用额度不足，一键下单失败"),
    ORDER_CREATE_NEED_CLEAR_DEBT(21, "您有余额欠款，请还清欠款后下单"),
    ORDER_CREATE_NO_CAN_FAST(22, "您未开通“一键下单”功能，如有需要，请联系站点申请开通"),
    ORDER_IS_WAIT_ACCEPT(23, "订单已经申请转单,请勿重复转单"),
    ORDER_TURN_INFO_NO_EXIST(24, "转单信息已经不存在,去完成其他订单吧~"),
    ORDER_TURN_STATUS_ERROR_REVOCATION(25, "订单不是申请转单状态无需撤销"),
    ORDER_TURN_TO_MYSELF(26, "转单申请不能转给自己"),
    ORDER_TURN_NOT_ACCEPT(27, "订单正在申请转单,无法接单,请先撤销申请"),
    ORDER_TURN_SAVE_FALL(28, "转单数据保存失败"),
    ORDER_STATUS_NOT_CREATED(29, "订单不是待支付状态，不能创建交易"),
    ORDER_TRADE_STATUS_HAS_PAID(29, "订单已支付，不能创建交易"),
    ORDER_FROM_ADDR_ERROR(50, "取货地址错误"),
    ORDER_DEST_ADDR_ERROR(51, "收货地址错误"),
    EXPRESS_SHOP_NO_EXAMINED(52, "店铺还未注册，或还未通过审核"),
    SETTLE_BATCH_NOT_OPEN(53, "暂未开通后付权限"),
    SETTLE_BATCH_OVER_END_TIME(54, "结算周期已到，请先还款后再使用后付"),
    SETTLE_BATCH_OVER_MAX_DEBT(55, "已达到欠款额度上限"),
    SETTLE_BATCH_QUOTA_NOT_ENOUGH(56, "可用后付额度不足"),
    SETTLE_BILL_NOT_UNPAID(57, "账单不是待支付状态，不能支付"),
    SETTLE_BILL_PAID_CANNOT_REFUND(58, "已结算账单不能退款"),
    ILLEGAL_DEST_ADDRESS_ID(59, "配送地址不存在"),
    NOT_OWN_DEST_ADDRESS_ID(60, "配送地址不属于该用户"),
    ADDRESS_IS_DELETE(61, "配送地址不存在"),
    ORDER_IS_NOT_OWN(62, "此订单不属于您"),
    HAS_FETCHEC_CANNOT_REFUND(63, "已经取货不能申请退单"),
    CANCEL_HAS_APPLY_REFUND(64, "当前订单已经申请过退单"),
    CANCEL_HAS_REJECTED_REFUND(65, "当前订单已被拒绝"),
    ORDER_REMARK_CAN_NOT_BLANK(66, "请输入要商品描述"),
    APPLYING_ORDER_CANNOT_ALLOT(67, "待处理取消订单不可分单"),
    ORDER_CANCEL_NOT_ACCEPT(67, "当前订单异常无法接单，请刷新"),
    SHOP_ADDRESS_NOT_EXIST_MERCH(69, "当前店铺所在区域暂未开通骑士星球业务"),
    SETTLE_BILL_OVER_FINAL_DATE(70, "您有欠款账单已逾期，请还款后下单"),
    SETTLE_BILL_NOT_PAY_ABLE(71, "账单还未出账，暂不能支付"),
    ORDER_BILL_AMOUNT_ERROR(72, "账单金额异常"),
    ORDER_BILL_NOT_UNPAID(73, "账单不是待出账状态，不能出账"),
    OVER_SEARCH_CROSS_DAY(74, "时间查询扩大超过预设值")
    ;

    private long code;
    private String message;

    OrderExceptionConstants(long code, String message) {
//        this.code = StartCode.EXPRESS_ORDER_CODE + code;
        this.code =  + code;
        this.message = message;
    }


//    @Override
//    public long getCode() {
//        return code;
//    }
//
//    @Override
//    public String getMessage() {
//        return message;
//    }
}

package zhizelianorder.context;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author xiong
 */
public interface OrderConstants {

    /**
     * ExpressOrderStatusHandler  service 名称前缀
     */
    String ExpressOrderStatusHandler = "ExpressOrderStatusHandler-";

    /**
     * bp 用户为 大于1000   实为真实用户id
     */
    interface OperatorUserId {
        /**
         * 快递员修改（如配送完成 等）
         */
        Long COURIER_USER = 2L;
        /**
         * 手机端用户修改（如下单，取消订单，申请退款 等）
         */
        Long MOBILE_USER = 1L;
        /**
         * 系统修改 （如定时器，自动接单 等）
         */
        Long SYSTEM = 0L;
    }

    /**
     * 新增或修改时同步修改 {@link OrderConstants.Status}
     * 新增或修改时同步查看 handlers.status.ExpressOrderStatusHandler 的实现类是否有相应的状态
     */
    interface StatusConstant {
        /**
         * 已下单，待支付
         */
        String CREATED = "created";
        /**
         * 已支付(待分单)
         */
        String PAID = "paid";
        /**
         * 已分单(待接单)
         */
        String ALLOTED = "alloted";
        /**
         * 已接单(待配送)
         */
        String ACCEPTED = "accepted";
        /**
         * 已取货
         */
        String FETCHED = "fetched";

        /**
         * 配送完成
         */
        String ARRIVED = "arrived";

        /**
         * 已退款
         */
        String REFUNDED = "refunded";

        /**
         * 取消订单等待快递员确认
         */
        String CONFIRM = "confirm";

        /**
         * 已作废
         */
        String ABANDON = "abandon";


    }


    /**
     * 订单状态
     * 新增或修改时同步修改 {@link OrderConstants.StatusConstant}
     * 新增或修改时同步查看 handlers.status.ExpressOrderStatusHandler 的实现类是否有相应的状态
     */
    @Getter
    @AllArgsConstructor
    enum Status {
        CREATED(StatusConstant.CREATED, "待支付", "created", "订单已提交", "", "", 0),
        PAID(StatusConstant.PAID, "已支付", "paid", "待取货", "", "待分配", 200),
        ALLOTED(StatusConstant.ALLOTED, "已分单", "wait_accept", "待取货", "待接单", "已分配", 300),
        ACCEPTED(StatusConstant.ACCEPTED, "已接单", "accepted", "待取货", "待取货", "待配送", 400),
        FETCHED(StatusConstant.FETCHED, "已取货", "sending", "配送中", "配送中", "配送中", 500),
        ARRIVED(StatusConstant.ARRIVED, "配送完成", "arrived", "已完成", "已完成", "已送达", 600),
        REFUNDED(StatusConstant.REFUNDED, "已退款", "cancled", "已取消", "", "已退款", 700),
        ABANDON(StatusConstant.ABANDON, "已作废", "", "", "", "", 800),
        ;

        private String status;
        private String statusName;
        private String mobileStatus;//小程序状态
        private String mobileName;//小程序状态名字
        private String expressName;//配送app
        private String bpName;//bss系统
        private Integer priority;

        public boolean equals(String status) {
            return this.getStatus().equals(status);
        }

        public boolean eqStatus(String status) {
            return this.getStatus().equals(status);
        }

        public boolean eqStatus(Status status) {
            return this.equals(status);
        }


        public static Status getStatusByStatus(String statusCode) {
            for (Status status : Status.values()) {
                if (status.getStatus().equals(statusCode)) {
                    return status;
                }
            }
            return null;
        }

        public static String getStatusExpressNameByStatus(String statusCode) {
            Status status = getStatusByStatus(statusCode);
            if (status != null) {
                return status.getExpressName();
            }
            return null;
        }

        public static String getStatusBpNameByStatus(String statusCode) {
            Status status = getStatusByStatus(statusCode);
            if (status != null) {
                return status.getBpName();
            }
            return null;
        }

        public static String getStatusStatusNameByStatus(String statusCode) {
            Status status = getStatusByStatus(statusCode);
            if (status != null) {
                return status.getStatusName();
            }
            return null;
        }

        public static String getStatusMobileStatusByStatus(String statusCode) {
            Status status = getStatusByStatus(statusCode);
            if (status != null) {
                return status.getMobileStatus();
            }
            return null;
        }


        public static String getStatusMobileNameByStatus(String statusCode) {
            Status status = getStatusByStatus(statusCode);
            if (status != null) {
                return status.getMobileName();
            }
            return null;
        }

        private static Status getStatus(Predicate<Status> filter, Comparator<Status> sorted) {
            Optional<Status> first = Arrays.asList(Status.values()).stream()
                    .filter(filter)
                    .sorted(sorted)
                    .findFirst();
            if (first.isPresent()) {
                return first.get();
            }
            return null;
        }

        public static Status getNextStatus(Status nowStatus) {
            if (Integer.valueOf(0).equals(nowStatus.getPriority())) {
                return null;
            }
            Predicate<Status> filter = (o) -> o.getPriority() > 0 && o.getPriority().compareTo(nowStatus.getPriority()) > 0;
            Comparator<Status> sorted = (o1, o2) -> o1.getPriority().compareTo(o2.getPriority());

            if (nowStatus.getPriority() < 0) {
                filter = (o) -> o.getPriority() < 0 && o.getPriority().compareTo(nowStatus.getPriority()) < 0;
            }
            return getStatus(filter, sorted);
        }

        public static Status getPreviousStatus(Status nowStatus) {
            if (Integer.valueOf(0).equals(nowStatus.getPriority())) {
                return null;
            }
            Predicate<Status> filter = (o) -> o.getPriority() > 0 && o.getPriority().compareTo(nowStatus.getPriority()) < 0;
            Comparator<Status> sorted = (o1, o2) -> o2.getPriority().compareTo(o1.getPriority());

            if (nowStatus.getPriority() < 0) {
                filter = (o) -> o.getPriority() < 0 && o.getPriority().compareTo(nowStatus.getPriority()) > 0;
            }
            return getStatus(filter, sorted);
        }

        @Override
        public String toString() {
            return this.getStatus();
        }

    }

    /**
     * bp对应状态翻译
     */
    @Getter
    @AllArgsConstructor
    enum BpQueryStatus {
        //已支付-->配送完成
        ALL("all", "", Arrays.asList(
                StatusConstant.PAID,
                StatusConstant.ALLOTED,
                StatusConstant.ACCEPTED,
                StatusConstant.FETCHED,
                StatusConstant.ARRIVED,
                StatusConstant.REFUNDED)),

        WAIT_SEND("wait_send", "待配送", Arrays.asList(
                StatusConstant.PAID,
                StatusConstant.ALLOTED,
                StatusConstant.ACCEPTED)),

        SENDING("sending", "配送中", Arrays.asList(
                StatusConstant.FETCHED)),

        ARRIVED("arrived", "已送达", Arrays.asList(
                StatusConstant.ARRIVED)),

        //分配相关(含待分配,已分配)
        ALLOT_ALL("allot_all", "含待分配,已分配", Arrays.asList(
                StatusConstant.PAID,
                StatusConstant.ALLOTED,
                StatusConstant.ACCEPTED,
                StatusConstant.FETCHED)),

        NOTALLOCAT("notallocat", "待分配", Arrays.asList(
                StatusConstant.PAID)),

        ALLOATED("allocated", "已分配", Arrays.asList(
                StatusConstant.ALLOTED,
                StatusConstant.ACCEPTED)),

        CANCELED("cancelled", "已取消", Arrays.asList(
                StatusConstant.REFUNDED)),

        ;
        private String queryStatus;
        private String statusName;
        private List<String> status;

        public static List<String> getBpQueryStatusStatusByQueryStatus(String querystatus) {
            for (BpQueryStatus bpQueryStatus : BpQueryStatus.values()) {
                if (bpQueryStatus.getQueryStatus().equals(querystatus)) {
                    return bpQueryStatus.getStatus();
                }
            }
            return null;
        }
    }

    /**
     * OrderWay指订单的来源
     * <p>
     * 新增类型时 注意订单状态改变的推送
     * com.iwubida.express.order.services.ExpressOrderUpdateService
     */
    @Getter
    @AllArgsConstructor
    enum OrderWay {
        //小程序普通下单
        APPLET("applet", "普通下单", "普通下单"),
        //小程序一键下单
        APPLET_FAST("applet-fast", "一键下单", "一键下单"),
        //美团推送单
        MEITUAN_SCH("meituan-sch", "美团", "普通下单"),
        //饿了么推送单
        ELEME_SCH("eleme_sch", "饿了么", "普通下单"),
        //94懒推单
        JBG("jbg", "94懒", "普通下单"),
        //骑士星球跑腿
        APPLET_PROXY("applet-proxy", "骑士星球跑腿", "跑腿代购"),
        //94懒跑腿
        JBG_PROXY("jbg-proxy", "94懒(跑腿)", "普通下单");
        private String code;
        private String name;
        private String courierCodeName;

        public boolean equals(String status) {
            return this.getCode().equals(status);
        }

        @Override
        public String toString() {
            return this.getCode();
        }

        public static String getNameByCode(String code) {
            for (OrderWay orderWay : OrderWay.values()) {
                if (orderWay.getCode().equals(code)) {
                    return orderWay.getName();
                }
            }
            return null;
        }

        public static String getCourierCodeNameByCode(String code) {
            for (OrderWay orderWay : OrderWay.values()) {
                if (orderWay.getCode().equals(code)) {
                    return orderWay.getCourierCodeName();
                }
            }
            return null;
        }

        public static OrderWay getOrderWayByCode(String code) {
            for (OrderWay orderWay : OrderWay.values()) {
                if (orderWay.getCode().equals(code)) {
                    return orderWay;
                }
            }
            return null;
        }
    }

    /**
     * 这里的OrderWayType 指下单方式
     */
    @Getter
    @AllArgsConstructor
    enum OrderWayType {
        //一键下单
        FAST_ORDER("fast", "一键下单", Arrays.asList(OrderConstants.OrderWay.APPLET_FAST.getCode())),
        //普通下单
        GENERAL_ORDER("general", "普通下单", Arrays.asList(OrderConstants.OrderWay.APPLET.getCode(), OrderWay.MEITUAN_SCH.getCode(), OrderWay.ELEME_SCH.getCode(), OrderWay.JBG.getCode(), OrderWay.JBG_PROXY.getCode())),
        //跑腿代购 特指骑士星球跑腿 94懒跑腿下单方式为普通下单
        PROXY_ORDER("proxy", "跑腿代购", Arrays.asList(OrderConstants.OrderWay.APPLET_PROXY.getCode()));
        private String code;
        private String typeName;
        //此状态下允许的订单来源
        private List<String> status;
    }

    @Getter
    @AllArgsConstructor
    enum shopType {
        //跑腿购买方式为骑手选择
        nearby("nearby", "骑手就近购买"),
        //跑腿购买方式为用户指定
        designation("designation", "指定地址购买");
        private String code;
        private String type;

        public static shopType getOrderWayByCode(String code) {
            for (shopType shopType : shopType.values()) {
                if (shopType.getCode().equals(code)) {
                    return shopType;
                }
            }
            return null;
        }

    }

    @Getter
    @AllArgsConstructor
    enum OrderType {
        //立即取货配送单
        now("now", "及时"),
        //预约取货配送单
        predict("predict", "预约"),
        ;
        private String code;
        private String name;

        public boolean equals(String status) {
            return this.getCode().equals(status);
        }

        @Override
        public String toString() {
            return this.getCode();
        }
    }

    /**
     * 手机配送app对应状态翻译
     */
    @Getter
    @AllArgsConstructor
    enum ExpressQueryStatus {
        //待录单
        WAIT_ACCEPT("wait_accept", "待接单", Arrays.asList(
                StatusConstant.ALLOTED)),
        //待录单实际是待取货状态中 快速下单方式的一个体现  没有自己的实际状态
        WAIT_WRITE("wait_write", "待录单", Arrays.asList(
                StatusConstant.ACCEPTED)),
        //待取货
        WAIT_FETCH("wait_fetch", "待取货", Arrays.asList(
                StatusConstant.ACCEPTED)),
        //配送中
        SENDING("sending", "配送中", Arrays.asList(
                StatusConstant.FETCHED)),
        //已完成
        ARRIVED("arrived", "已完成", Arrays.asList(
                StatusConstant.ARRIVED)),
        ;
        private String queryStatus;
        private String statusName;
        private List<String> status;

        public static List<String> getExpressQueryStatusStatusByQueryStatus(String querystatus) {
            for (ExpressQueryStatus expressQueryStatus : ExpressQueryStatus.values()) {
                if (expressQueryStatus.getQueryStatus().equals(querystatus)) {
                    return expressQueryStatus.getStatus();
                }
            }
            return null;
        }
    }

    /**
     * 商家小程序对应状态翻译
     */
    @Getter
    @AllArgsConstructor
    enum MobileQueryStatus {
        ALL("all", "待配送,配送中,已完成,已取消", Arrays.asList(
                StatusConstant.PAID,
                StatusConstant.ALLOTED,
                StatusConstant.ACCEPTED,
                StatusConstant.FETCHED,
                StatusConstant.ARRIVED,
                StatusConstant.REFUNDED
        )),

        PAID("paid", "待配送", Arrays.asList(
                StatusConstant.PAID,
                StatusConstant.ALLOTED,
                StatusConstant.ACCEPTED)),

  /*      WAIT_ACCEPT("wait_accept", "待接单", Arrays.asList(
                StatusConstant.ALLOTED)),

        ACCEPTED("accepted", "已接单", Arrays.asList(
                StatusConstant.ACCEPTED)),*/

        SENDING("sending", "配送中", Arrays.asList(
                StatusConstant.FETCHED)),
        ARRIVED("arrived", "已完成", Arrays.asList(
                StatusConstant.ARRIVED)),
        CANCLED("cancled", "已取消", Arrays.asList(
                StatusConstant.REFUNDED)),
        ;
        private String queryStatus;
        private String statusName;
        private List<String> status;

        public static List<String> getMobileQueryStatusStatusByQueryStatus(String querystatus) {
            for (MobileQueryStatus mobileQueryStatus : MobileQueryStatus.values()) {
                if (mobileQueryStatus.getQueryStatus().equals(querystatus)) {
                    return mobileQueryStatus.getStatus();
                }
            }
            return null;
        }
    }

    /**
     * 统计类型
     */
    @Getter
    @AllArgsConstructor
    enum MobileStatisticsStatus {
        PAID(StatusConstant.PAID, "待配送", Arrays.asList(
                StatusConstant.PAID,
                StatusConstant.ALLOTED,
                StatusConstant.ACCEPTED
        )),

        FETCHED(StatusConstant.FETCHED, "配送中", Arrays.asList(
                StatusConstant.FETCHED
        )),

        ARRIVED(StatusConstant.ARRIVED, "已完成", Arrays.asList(
                StatusConstant.ARRIVED
        )),
        REFUNDED(StatusConstant.REFUNDED, "已退款", Arrays.asList(
                StatusConstant.REFUNDED
        )),
        ;
        private String queryStatus;
        private String statusName;
        private List<String> status;

    }


    interface ExpressOrderAddrConstant {
        interface Type {
            String FROM = "from";
            String DEST = "dest";
        }
    }

    interface OrderTradeStatus {
        /**
         * 未支付
         */
        String created = "created";
        /**
         * 已支付
         */
        String paid = "paid";
        /**
         * 已退款
         */
        String refunded = "refunded";
    }

    /**
     * 分单类型
     */
    interface OrderAllotType {
        /**
         * 系统分单
         */
        String system = "system";
        /**
         * 人工分单
         */
        String person = "person";
    }

    /**
     * 分单类型
     */
    interface DistributionBasicTime {
        /**
         * 取件延迟时间
         */
        int fetchTime = 3;
        /**
         * 取件到货时间
         */
        int finishTime = 15;

    }

    /**
     * 转单状态
     */
    interface OrderAllotTurnStatus {
        /*
         *等待接受
         */
        String WAIT_ACCEPT = "wait_accept";
        /*
         *已接受
         */
        String ACCEPTED = "accepted";
        /*
         *已拒收
         */
        String NO_ACCEPT = "no_accept";
        /*
         *已撤销
         */
        String REVOCATION = "revocation";
        /*
         *站长作废
         */
        String SHOP_ABANDON = "shop_abandon";
        /*
         *站长普通转单
         */
        String SHOP_GENERAL_TURN = "shop_general_turn";
    }

    /**
     * 转单类型   turn_shop:站长转单, turn_courier:快递员申请',
     */
    interface TurnType {
        /*
         *站长转单
         */
        String TURN_SHOP = "turn_shop";
        /*
         *快递员申请
         */
        String TURN_COURIER = "turn_courier";


    }

    /**
     * 转单配送员类型 推送相关快递员的类型
     */
    interface OrderAllotTurnCourierType {
        /*
         *转单发起快递员
         */
        String FROM_COURIER = "from_courier";
        /*
         *转单目标快递员
         */
        String DEST_COURIER = "dest_courier";
    }

    /**
     * 转单操作人
     */
    interface OrderAllotTurnOperator {
        /*
         *配送员
         */
        String COURIER = "courier";
        /*
         *门店
         */
        String SHOP = "shop";

    }

    /**
     * 推送业务类型,都是订单相关
     */
    interface BusinessType {

        String SHOP_OPERATION_PUSH_FROM = "shopOperationPushFrom"; //门店操作分单推送原快递员  的推送type
        String COURIER_TURN_REQUEST = "courierTurnRequest";    //配送员发起转单请求的推送type
        String COURIER_TURN_ACCEPT = "courierTurnAccept";    //目标配送员接受转单申请 的推送type
        String COURIER_TURN_NO_ACCEPT = "courierTurnNoAccept";    //目标配送员拒绝转单申请 的推送type
        String COURIER_TURN_REVOCATION = "courierTurnRevocation";    //发起配送员撤销申请 的推送type
        String SHOP_ABANDON_TO_PUSH_DEST = "shopAbandonToPushDest";    //门店撤销对目标快递员产生的撤销通知 的推送type

        String ORDER_CANCLE_APPLIED = "cancleRunleg";    //用户取消订单申请请求的推送type

        //String courierTurnResponse = "courierTurnResponse";    //配送员回应是否接单的推送type
    }

    /**
     * 后付账单状态
     */
    interface OrderBill {
        //支付状态
        interface PayStatus {
            String UNPAID = "unpaid";  //待支付
            String PAID = "paid";      //已支付
            String CANCELED = "canceled"; //支付已撤销
            String REFUNDED = "refunded"; //已退款
        }

        interface RefundStatus {
            String NOT = "not"; //未退款
            String PART = "part"; //部分退款
            String ALL = "all";  //全额退款
        }
    }

    interface SettleWay {
        String REAL = "real"; //实时结算
        String PREP = "prep"; //预付费结算
        String AFTER = "after";//后付结算
        String BALANCE_CREDIT = "balance_credit";// 余额透支后付 为了兼容历史数据
    }

    /**
     * 交易类型
     */
    interface TransType {
        String payment = "payment";
        String refund = "refund";
    }

    /**
     * 结算渠道
     */
    @Getter
    @AllArgsConstructor
    enum ChannelSettleWay {
        weixin(SettleWay.REAL),
        alipay(SettleWay.REAL),
        balance(SettleWay.PREP);
        String value;
    }

    /**
     * 跑腿单的最大直线距离 单位：米
     */
    Long PROXY_ORDER_MAX_DISTANCE = Long.valueOf(3000);


    /**
     * 跑腿取消订单状态
     */
    @Getter
    @AllArgsConstructor
    enum ProxyOrderRefundStatus {
        //用户发起申请
        applied("applied", "已申请"),
        //骑手同意取消
        agreed("agreed", "已同意"),
        //骑手拒绝申请
        rejected("rejected", "已拒绝");
        String status;
        String statusDesc;

        public static ProxyOrderRefundStatus getOrderWayByStatus(String status) {
            for (ProxyOrderRefundStatus proxyOrderRefundStatus : ProxyOrderRefundStatus.values()) {
                if (proxyOrderRefundStatus.getStatus().equals(status)) {
                    return proxyOrderRefundStatus;
                }
            }
            return null;
        }
    }


}

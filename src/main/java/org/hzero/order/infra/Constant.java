package org.hzero.order.infra;

/**
 * @author haibo.wang@hand-china.com
 */
public interface Constant {

    /**
     * 销售订单状态,新建
     */
    public final String STATUS_NEW = "NEW";

    /**
     * 销售订单状态,已提交
     */
    public final String STATUS_SUBMITED = "SUBMITED";

    /**
     * 销售订单状态,已审批
     */
    public final String STATUS_APPROVED = "APPROVED";

    /**
     * 销售订单状态,已拒绝
     */
    public final String STATUS_REJECTED ="REJECTED";

    /**
     * 销售订单状态,已关闭
     */
    public final String STATUS_CLOSED = "CLOSED";

    /**
     * 销售订单编码规则
     */
    public final String CODE_RULE = "ORDER.CODE";

    /**
     * lov销售订单状态
     */
    public final String  ORDER_STATUS = "HZERO.ORDER.STATUS";

    interface Pattern {
        String DATE = "yyyy-MM-dd";
    }

}

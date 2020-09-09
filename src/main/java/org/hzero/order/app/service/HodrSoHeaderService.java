package org.hzero.order.app.service;

import org.hzero.order.domain.entity.HodrSoHeader;

import java.util.List;

/**
 * 销售订单头应用服务
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:33
 */
public interface HodrSoHeaderService {

    /**
     * 创建销售订单头
     * @param hodrSoHeader 销售订单头信息
     * @return
     */
    int headerCreate(HodrSoHeader hodrSoHeader);

    /**
     * 修改销售订单头
     * @param hodrSoHeader 销售订单头信息
     * @return
     */
    int headerUpdate(HodrSoHeader hodrSoHeader);

    /**
     * 提交销售订单
     * @param hodrSoHeaderId 销售订单头id
     * @return
     */
    HodrSoHeader headerSubmit(Long hodrSoHeaderId);

    /**
     * 审批销售订单
     * @param hodrSoHeaderId 销售订单头id
     * @return
     */
    HodrSoHeader headerApprove(Long hodrSoHeaderId);

    /**
     * 拒绝销售订单
     * @param hodrSoHeaderId 销售订单头id
     * @return
     */
    HodrSoHeader headerReject(Long hodrSoHeaderId);

    /**
     * 整单删除
     * @param hodrSoHeader 销售订单头信息
     */
    void delete(HodrSoHeader hodrSoHeader);

    /**
     * 定时关闭已审批的订单
     * @param hodrSoHeaderList 状态为已审批(APPROVED)的订单
     */
    void batchUpdate(List<HodrSoHeader> hodrSoHeaderList);

    /**
     * 导入excel
     * @param hodrSoheader
     * @return
     */
    void importExcel(HodrSoHeader hodrSoheader);

}

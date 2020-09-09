package org.hzero.order.domain.repository;

import org.hzero.mybatis.base.BaseRepository;
import org.hzero.order.domain.entity.HodrSoHeader;

import java.util.List;

/**
 * 销售订单头资源库
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:33
 */
public interface HodrSoHeaderRepository extends BaseRepository<HodrSoHeader> {

    /**
     * 查询销售订单状态为APPROVED的订单
     * @param stauts 订单状态
     * @return
     */
    List<HodrSoHeader> selectApprovedOrder(String stauts);

    /**
     * 销售订单头信息
     * @param soHeaderId 订单头ID
     * @return
     */
    HodrSoHeader selectHeaderBySoHeaderId(Long soHeaderId);

    /**
     * 获得companyId
     * @param companyNum
     * @return
     */
    Long selectCompanyId(String companyNum);

    /**
     * 获得customerId
     * @param customerNum
     * @return
     */
    Long selectCustomerId(String customerNum);

    /**
     * 获取itemId
     * @param itemCode
     * @return
     */
    Long selectItemId(String itemCode);

    /**
     * 查询订单头id
     * @param orderNum
     * @return
     */
    HodrSoHeader selectHeaderId(String orderNum);

    
}

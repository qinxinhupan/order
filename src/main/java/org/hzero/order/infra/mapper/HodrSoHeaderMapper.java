package org.hzero.order.infra.mapper;

import org.hzero.order.domain.entity.HodrSoHeader;
import io.choerodon.mybatis.common.BaseMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * 销售订单头Mapper
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:33
 */
public interface HodrSoHeaderMapper extends BaseMapper<HodrSoHeader> {

    /**
     * 销售订单头信息
     * @param soHeaderId 订单头ID
     * @return
     */
    HodrSoHeader selectHeaderBySoHeaderId(Long soHeaderId);

    /**
     * 查询销售订单状态为APPROVED的订单
     * @param status 订单状态
     * @return
     */
    List<HodrSoHeader> selectApprovedOrder(String status);

    /**
     *
     * @param companyNum
     * @return
     */
    Long selectCompanyId(@Param(value = "companyNum") String companyNum);

    /**
     *
     * @param customerNum
     * @return
     */
    Long selectCustomerId(@Param("customerNum") String customerNum);

    /**
     *
     * @param itemCode
     * @return
     */
    Long selectItemId(@Param(value = "itemCode") String itemCode);

    /**
     * 查询订单头id
     * @param codeNum
     * @return
     */
    HodrSoHeader selectHeaderId(@Param(value = "codeNum") String codeNum);

}

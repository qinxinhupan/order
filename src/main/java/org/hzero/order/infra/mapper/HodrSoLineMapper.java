package org.hzero.order.infra.mapper;

import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.order.api.controller.dto.OrderExportExcelDTO;
import org.hzero.order.api.controller.dto.OrderLineExportDTO;
import org.hzero.order.domain.entity.HodrSoLine;
import io.choerodon.mybatis.common.BaseMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 销售订单行Mapper
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:32
 */
public interface HodrSoLineMapper extends BaseMapper<HodrSoLine> {
    /**
     * 条件查询销售订单汇总
     * @param hodrSoLine
     * @return
     */
    List<HodrSoLine> selectByConditionLike(HodrSoLine hodrSoLine);

    /**
     * 销售订单明细头信息
     * @param soHeaderId
     * @return
     */
    HodrSoLine selectHeaderBySoHeaderId(Long soHeaderId);

    /**
     * 销售订单明细行信息
     * @param soHeaderId
     * @return
     */
    List<HodrSoLine> selectLineBySoHeaderId(Long soHeaderId);

    /**
     * 查询lineNumber
     * @param hodrSoLine
     * @return
     */
    Long lineNum(HodrSoLine hodrSoLine);

    /**
     * 导出销售订单头
     * @param hodrSoLine
     * @return
     */
    List<OrderExportExcelDTO> selectOrderHeader(HodrSoLine hodrSoLine);

    /**
     * 导出销售订单行
     * @param soHeaderId
     * @return
     */
    List<OrderLineExportDTO> selectOrderLine(Long soHeaderId);

    /**
     * 查询行id
     * @param soHeaderId
     * @param lineNumber
     * @return
     */
    HodrSoLine selectLineId(@Param(value = "soHeaderId") Long soHeaderId,@Param(value = "lineNumber") Long lineNumber);

}

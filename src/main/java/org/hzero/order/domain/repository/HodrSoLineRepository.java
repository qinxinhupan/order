package org.hzero.order.domain.repository;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.BaseRepository;
import org.hzero.order.api.controller.dto.OrderExportExcelDTO;
import org.hzero.order.api.controller.dto.OrderLineExportDTO;
import org.hzero.order.domain.entity.HodrSoLine;

import java.util.List;

/**
 * 销售订单行资源库
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:32
 */
public interface HodrSoLineRepository extends BaseRepository<HodrSoLine> {
    /**
     * 带分页条件查询销售订单汇总
     * @param hodrSoLine
     * @return
     */
    Page<HodrSoLine> selectByConditionLike(PageRequest pageRequest, HodrSoLine hodrSoLine);

    /**
     * 销售订单明细头信息
     * @param soHeaderId
     * @return
     */
    HodrSoLine selectHeaderBySoHeaderId(Long soHeaderId);

    /**
     * 销售订单明细行信息
     * @param pageRequest
     * @param soHeaderId
     * @return
     */
    Page<HodrSoLine> selectLineBySoHeaderId(PageRequest pageRequest, Long soHeaderId);

    /**
     * 查询lineNum
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
    HodrSoLine selectLineId(Long soHeaderId,Long lineNumber);

    
}

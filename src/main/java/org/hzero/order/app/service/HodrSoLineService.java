package org.hzero.order.app.service;

import org.hzero.export.vo.ExportParam;
import org.hzero.order.api.controller.dto.OrderExportExcelDTO;
import org.hzero.order.domain.entity.HodrSoLine;

import java.util.List;
import java.util.Map;

/**
 * 销售订单行应用服务
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:32
 */
public interface HodrSoLineService {

    /**
     * 批量保存销售订单行信息
     * @param list
     * @return
     */
    int batchSaveOrderLine(List<HodrSoLine> list);

    /**
     * 批量删除销售订单行信息
     * @param list
     * @return
     */
    int batchDeleteOrderLine(List<HodrSoLine> list);

    /**
     * 导出销售订单
     * @param exportParam
     * @param hodrSoLine
     * @return
     */
    List<OrderExportExcelDTO> excelExport(ExportParam exportParam, HodrSoLine hodrSoLine);

    /**
     *
     * @return
     */
    Map<String,String> map();
}

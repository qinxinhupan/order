package org.hzero.order.app.service.impl;

import io.choerodon.core.exception.CommonException;
import io.choerodon.mybatis.domain.AuditDomain;
import org.hzero.boot.platform.lov.annotation.ProcessLovValue;
import org.hzero.boot.platform.lov.dto.LovValueDTO;
import org.hzero.boot.platform.lov.feign.LovFeignClient;
import org.hzero.core.base.BaseConstants;
import org.hzero.export.vo.ExportParam;
import org.hzero.mybatis.helper.SecurityTokenHelper;
import org.hzero.order.api.controller.dto.OrderExportExcelDTO;
import org.hzero.order.api.controller.dto.OrderLineExportDTO;
import org.hzero.order.app.service.HodrSoLineService;
import org.hzero.order.domain.entity.HodrSoHeader;
import org.hzero.order.domain.entity.HodrSoLine;
import org.hzero.order.domain.repository.HodrSoHeaderRepository;
import org.hzero.order.domain.repository.HodrSoLineRepository;
import org.hzero.order.infra.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 销售订单行应用服务默认实现
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:32
 */
@Service
public class HodrSoLineServiceImpl implements HodrSoLineService {
    @Autowired
    private HodrSoLineRepository hodrSoLineRepository;
    @Autowired
    private HodrSoHeaderRepository hodrSoHeaderRepository;
    @Autowired
    private LovFeignClient lovFeignClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSaveOrderLine(List<HodrSoLine> list) {
        list.forEach(hodrSoLine -> {
            if (AuditDomain.RecordStatus.create.equals(hodrSoLine.get_status())) {
//                HodrSoLine hodrSoLineValid = new HodrSoLine();
//                hodrSoLineValid.setLineNumber(hodrSoLine.getLineNumber());
//                hodrSoLineValid.setSoLineId(hodrSoLine.getSoLineId());
//                hodrSoLineValid.setSoHeaderId(hodrSoLine.getSoHeaderId());
//                System.err.println(hodrSoLineValid.getLineNumber());
//                System.err.println(hodrSoLineValid.getSoLineId());
//                if(hodrSoLineRepository.selectCount(hodrSoLineValid) > 0) {
//                    throw new CommonException("行号已存在,请重新输入");
//                }
                HodrSoHeader hodrSoHeader = new HodrSoHeader();
                hodrSoHeader.setSoHeaderId(hodrSoLine.getSoHeaderId());
                if (hodrSoHeaderRepository.selectCount(hodrSoHeader) == 0) {
                    throw new CommonException("订单头不存在!");
                }
                Long hodrSoLineNum = hodrSoLineRepository.lineNum(hodrSoLine);
                hodrSoLine.setLineNumber(hodrSoLineNum + 1);
            } else if (AuditDomain.RecordStatus.update.equals(hodrSoLine.get_status())) {
                SecurityTokenHelper.validToken(hodrSoLine);
            }
        });
        return hodrSoLineRepository.batchSaveSelective(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteOrderLine(List<HodrSoLine> list) {
        return hodrSoLineRepository.batchDeleteByPrimaryKey(list);
    }

    @Override
    @ProcessLovValue
    public List<OrderExportExcelDTO> excelExport(ExportParam exportParam, HodrSoLine hodrSoLine) {
        List<OrderExportExcelDTO> orderExportExcelDTOList = hodrSoLineRepository.selectOrderHeader(hodrSoLine);
        orderExportExcelDTOList.forEach(orderExportExcelDTO -> {
            orderExportExcelDTO.dateToString();
            List<OrderLineExportDTO> orderLineExportDTOList = hodrSoLineRepository
                    .selectOrderLine(orderExportExcelDTO.getSoHeaderId());
            orderExportExcelDTO.setOrderLineExportDTOList(orderLineExportDTOList);
        });
        return orderExportExcelDTOList;
    }

    @Override
    public Map<String, String> map() {
        List<LovValueDTO> lovValueDTOS = lovFeignClient.queryLovValue(Constant.ORDER_STATUS,null);
        return lovValueDTOS.stream().collect(Collectors.toMap(LovValueDTO::getMeaning,LovValueDTO::getValue));
    }
}

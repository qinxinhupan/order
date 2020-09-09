package org.hzero.order.infra.repository.impl;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.order.api.controller.dto.OrderExportExcelDTO;
import org.hzero.order.api.controller.dto.OrderLineExportDTO;
import org.hzero.order.domain.entity.HodrSoLine;
import org.hzero.order.domain.repository.HodrSoLineRepository;
import org.hzero.order.infra.mapper.HodrSoLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 销售订单行 资源库实现
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:32
 */
@Component
public class HodrSoLineRepositoryImpl extends BaseRepositoryImpl<HodrSoLine> implements HodrSoLineRepository {

    @Autowired
    private HodrSoLineMapper hodrSoLineMapper;
    @Override
    public Page<HodrSoLine> selectByConditionLike(PageRequest pageRequest, HodrSoLine hodrSoLine) {
        return PageHelper.doPage(pageRequest,()->hodrSoLineMapper.selectByConditionLike(hodrSoLine));
    }

    @Override
    public HodrSoLine selectHeaderBySoHeaderId(Long soHeaderId) {
        return this.hodrSoLineMapper.selectHeaderBySoHeaderId(soHeaderId);
    }

    @Override
    public Page<HodrSoLine> selectLineBySoHeaderId(PageRequest pageRequest, Long soHeaderId) {
        return PageHelper.doPageAndSort(pageRequest, ()->hodrSoLineMapper.selectLineBySoHeaderId(soHeaderId));
    }

    @Override
    public Long lineNum(HodrSoLine hodrSoLine) {
        return hodrSoLineMapper.lineNum(hodrSoLine);
    }

    @Override
    public List<OrderExportExcelDTO> selectOrderHeader(HodrSoLine hodrSoLine) {
        return hodrSoLineMapper.selectOrderHeader(hodrSoLine);
    }

    @Override
    public List<OrderLineExportDTO> selectOrderLine(Long soHeaderId) {
        return hodrSoLineMapper.selectOrderLine(soHeaderId);
    }

    @Override
    public HodrSoLine selectLineId(Long soHeaderId,Long lineNumber) {
        return hodrSoLineMapper.selectLineId(soHeaderId,lineNumber);
    }

}

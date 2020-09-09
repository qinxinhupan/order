package org.hzero.order.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.order.domain.entity.HodrSoHeader;
import org.hzero.order.domain.repository.HodrSoHeaderRepository;
import org.hzero.order.infra.mapper.HodrSoHeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 销售订单头 资源库实现
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:33
 */
@Component
public class HodrSoHeaderRepositoryImpl extends BaseRepositoryImpl<HodrSoHeader> implements HodrSoHeaderRepository {

    private final HodrSoHeaderMapper hodrSoHeaderMapper;

    @Autowired
    public HodrSoHeaderRepositoryImpl(HodrSoHeaderMapper hodrSoHeaderMapper) {
        this.hodrSoHeaderMapper = hodrSoHeaderMapper;
    }

    @Override
    public List<HodrSoHeader> selectApprovedOrder(String status) {
        return hodrSoHeaderMapper.selectApprovedOrder(status);
    }

    @Override
    public HodrSoHeader selectHeaderBySoHeaderId(Long soHeaderId) {
        return hodrSoHeaderMapper.selectHeaderBySoHeaderId(soHeaderId);
    }

    @Override
    public Long selectCompanyId(String companyNum) {
        return hodrSoHeaderMapper.selectCompanyId(companyNum);
    }

    @Override
    public Long selectCustomerId(String customerNum) {
        return hodrSoHeaderMapper.selectCustomerId(customerNum);
    }

    @Override
    public Long selectItemId(String itemCode) {
        return hodrSoHeaderMapper.selectItemId(itemCode);
    }

    @Override
    public HodrSoHeader selectHeaderId(String orderNum) {
        return hodrSoHeaderMapper.selectHeaderId(orderNum);
    }
}

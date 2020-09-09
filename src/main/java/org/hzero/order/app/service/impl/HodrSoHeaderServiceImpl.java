package org.hzero.order.app.service.impl;

import com.alibaba.excel.annotation.ExcelProperty;
import io.choerodon.core.exception.CommonException;
import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.hzero.order.app.service.HodrSoHeaderService;
import org.hzero.order.domain.entity.HodrSoHeader;
import org.hzero.order.domain.entity.HodrSoLine;
import org.hzero.order.domain.repository.HodrSoHeaderRepository;
import org.hzero.order.domain.repository.HodrSoLineRepository;
import org.hzero.order.infra.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 销售订单头应用服务默认实现
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:33
 */
@Service
public class HodrSoHeaderServiceImpl implements HodrSoHeaderService {

    @Autowired
    private HodrSoHeaderRepository hodrSoHeaderRepository;
    @Autowired
    private HodrSoLineRepository hodrSoLineRepository;
    @Autowired
    private CodeRuleBuilder codeRuleBuilder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int headerCreate(HodrSoHeader hodrSoHeader) {
        hodrSoHeader.setOrderNumber(codeRuleBuilder.generateCode(Constant.CODE_RULE,(Map)null));
        hodrSoHeader.setOrderStatus(Constant.STATUS_NEW);
        return hodrSoHeaderRepository.insertSelective(hodrSoHeader);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int headerUpdate(HodrSoHeader hodrSoHeader) {
        if (Constant.STATUS_SUBMITED.equals(hodrSoHeader.getOrderStatus()) ||
                Constant.STATUS_APPROVED.equals(hodrSoHeader.getOrderStatus())) {
            throw new CommonException("订单状态为已提交或已审批,不能更改");
        }
        return hodrSoHeaderRepository.updateByPrimaryKeySelective(hodrSoHeader);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HodrSoHeader headerSubmit(Long hodrSoHeaderId) {
        HodrSoHeader hodrSoHeaderValid = hodrSoHeaderRepository.selectByPrimaryKey(hodrSoHeaderId);
            if (Constant.STATUS_APPROVED.equals(hodrSoHeaderValid.getOrderStatus())||
                    Constant.STATUS_SUBMITED.equals(hodrSoHeaderValid.getOrderStatus())) {
                throw new CommonException("订单已提交或已审批");
            }
            hodrSoHeaderValid.setOrderStatus(Constant.STATUS_SUBMITED);
        hodrSoHeaderRepository.updateByPrimaryKeySelective(hodrSoHeaderValid);
        return hodrSoHeaderValid;
    }

    @Override
    public HodrSoHeader headerApprove(Long hodrSoHeaderId) {
        HodrSoHeader hodrSoHeaderValid = hodrSoHeaderRepository.selectByPrimaryKey(hodrSoHeaderId);
        if (!Constant.STATUS_SUBMITED.equals(hodrSoHeaderValid.getOrderStatus())) {
            throw new CommonException("非已提交订单");
        }
        hodrSoHeaderValid.setOrderStatus(Constant.STATUS_APPROVED);
        hodrSoHeaderRepository.updateByPrimaryKeySelective(hodrSoHeaderValid);
        return hodrSoHeaderValid;
    }

    @Override
    public HodrSoHeader headerReject(Long hodrSoHeaderId) {
        HodrSoHeader hodrSoHeaderValid = hodrSoHeaderRepository.selectByPrimaryKey(hodrSoHeaderId);
        if (!Constant.STATUS_SUBMITED.equals(hodrSoHeaderValid.getOrderStatus())) {
            throw new CommonException("非已提交订单");
        }
        hodrSoHeaderValid.setOrderStatus(Constant.STATUS_REJECTED);
        hodrSoHeaderRepository.updateByPrimaryKeySelective(hodrSoHeaderValid);
        return hodrSoHeaderValid;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(HodrSoHeader hodrSoHeader) {
        if(Objects.nonNull(hodrSoHeader)) {
            HodrSoHeader hodrSoHeaderVal = hodrSoHeaderRepository.selectByPrimaryKey(hodrSoHeader);
            if (Constant.STATUS_SUBMITED.equals(hodrSoHeaderVal.getOrderStatus()) ||
                            Constant.STATUS_APPROVED.equals(hodrSoHeaderVal.getOrderStatus())) {
                throw new CommonException("订单状态为已提交或已审批,不能删除");
            }
            HodrSoLine hodrSoLine = new HodrSoLine();
            hodrSoLine.setSoHeaderId(hodrSoHeader.getSoHeaderId());
            List<HodrSoLine> hodrSoLineList = hodrSoLineRepository.select(hodrSoLine);
            hodrSoLineRepository.batchDeleteByPrimaryKey(hodrSoLineList);
            hodrSoHeaderRepository.deleteByPrimaryKey(hodrSoHeader);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdate(List<HodrSoHeader> hodrSoHeaderList) {
        hodrSoHeaderRepository.batchUpdateByPrimaryKeySelective(hodrSoHeaderList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importExcel(HodrSoHeader hodrSoheader) {

//        HodrSoHeader hodrSoHeaderVO = new HodrSoHeader();
//        hodrSoHeaderVO.setOrderNumber(hodrSoheader.getOrderNumber());
        HodrSoHeader hodrSoHeaderVO = hodrSoHeaderRepository.selectHeaderId(hodrSoheader.getOrderNumber());
        System.err.println(hodrSoHeaderVO);
        if (Objects.nonNull(hodrSoHeaderVO)) {
            hodrSoheader.setSoHeaderId(hodrSoHeaderVO.getSoHeaderId());
            hodrSoheader.setObjectVersionNumber(hodrSoHeaderVO.getObjectVersionNumber());
            hodrSoHeaderRepository.updateByPrimaryKeySelective(hodrSoheader);
        } else {
            hodrSoHeaderRepository.insertSelective(hodrSoheader);
        }


        HodrSoHeader hodrSoHeaderInfo = hodrSoHeaderRepository.selectHeaderId(hodrSoheader.getOrderNumber());
        hodrSoheader.getHodrSoLineList().forEach(hodrSoLine -> {
            hodrSoLine.setSoHeaderId(hodrSoHeaderInfo.getSoHeaderId());
            HodrSoLine hodrSoLineV = new HodrSoLine();
            hodrSoLineV.setLineNumber(hodrSoLine.getLineNumber());
            hodrSoLineV.setSoHeaderId(hodrSoLine.getSoHeaderId());
            if (hodrSoLineRepository.selectCount(hodrSoLineV) > 0) {
                HodrSoLine hodrSoLineVo = hodrSoLineRepository.selectLineId(hodrSoLine.getSoHeaderId(),hodrSoLine.getLineNumber());
                hodrSoLine.setSoLineId(hodrSoLineVo.getSoLineId());
                hodrSoLine.setObjectVersionNumber(hodrSoLineVo.getObjectVersionNumber());
                hodrSoLineRepository.updateByPrimaryKey(hodrSoLine);
            } else {
                hodrSoLineRepository.insertSelective(hodrSoLine);
            }

        });
    }
}

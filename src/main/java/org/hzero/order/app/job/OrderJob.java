package org.hzero.order.app.job;

import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;
import org.hzero.order.app.service.HodrSoHeaderService;
import org.hzero.order.domain.entity.HodrSoHeader;
import org.hzero.order.domain.repository.HodrSoHeaderRepository;
import org.hzero.order.infra.Constant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author haibo.wang@hand-china.com
 */
@Service
@JobHandler("orderJob")
public class OrderJob implements IJobHandler {

    private final HodrSoHeaderService hodrSoHeaderService;

    private final HodrSoHeaderRepository hodrSoHeaderRepository;

    public OrderJob(HodrSoHeaderService hodrSoHeaderService,
                    HodrSoHeaderRepository hodrSoHeaderRepository) {
        this.hodrSoHeaderService = hodrSoHeaderService;
        this.hodrSoHeaderRepository = hodrSoHeaderRepository;
    }


    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {
        try {
            List<HodrSoHeader> list = hodrSoHeaderRepository.selectApprovedOrder(Constant.STATUS_APPROVED);
            list.forEach(hodrSoHeader -> hodrSoHeader.setOrderStatus(Constant.STATUS_CLOSED));
            hodrSoHeaderService.batchUpdate(list);
            tool.info("执行成功");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            tool.error(e.getMessage());
            return ReturnT.FAILURE;
        }

    }
}

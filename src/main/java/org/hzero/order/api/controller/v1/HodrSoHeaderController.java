package org.hzero.order.api.controller.v1;

import io.swagger.annotations.Api;
import org.hzero.boot.platform.lov.annotation.ProcessLovValue;
import org.hzero.core.base.BaseConstants;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.hzero.order.app.service.HodrSoHeaderService;
import org.hzero.order.config.SwaggerApiConfig;
import org.hzero.order.domain.entity.HodrSoHeader;
import org.hzero.order.domain.repository.HodrSoHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.hzero.mybatis.helper.SecurityTokenHelper;


import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;


/**
 * 销售订单头 管理 API
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:33
 */
@Api(tags = SwaggerApiConfig.HZERO_ORDER_HEADER)
@RestController("hodrSoHeaderController.v1")
@RequestMapping("/v1/{organizationId}/hodr-so-headers")
public class HodrSoHeaderController extends BaseController {

    @Autowired
    private HodrSoHeaderRepository hodrSoHeaderRepository;
    @Autowired
    private HodrSoHeaderService hodrSoHeaderService;


    @ApiOperation(value = "销售订单头信息")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @GetMapping("/{soHeaderId}")
    public ResponseEntity<HodrSoHeader> headerDetail(@PathVariable(value = "organizationId") Long organizationId,
                                                   @PathVariable Long soHeaderId) {
        return Results.success(hodrSoHeaderRepository.selectHeaderBySoHeaderId(soHeaderId));
    }

    @ApiOperation(value = "创建销售订单头")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @PostMapping
    public ResponseEntity<HodrSoHeader> headerCreate(@PathVariable(value = "organizationId") Long organizationId,
                                                   @RequestBody HodrSoHeader hodrSoHeader) {
        validObject(hodrSoHeader);
        hodrSoHeaderService.headerCreate(hodrSoHeader);
        return Results.success(hodrSoHeader);
    }

    @ApiOperation(value = "修改销售订单头")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @PutMapping
    public ResponseEntity<HodrSoHeader> update(@PathVariable(value = "organizationId") Long organizationId,
                                               @RequestBody HodrSoHeader hodrSoHeader) {
        validObject(hodrSoHeader);
        SecurityTokenHelper.validToken(hodrSoHeader);
        hodrSoHeaderService.headerUpdate(hodrSoHeader);
        return Results.success(hodrSoHeader);
    }

    @ApiOperation(value = "提交销售订单")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @PutMapping("/submit/{soHeaderId}")
    public ResponseEntity<HodrSoHeader> submit(@PathVariable(value = "organizationId") Long organizationId,
                                               @PathVariable(value = "soHeaderId") Long soHeaderId) {
        return Results.success(hodrSoHeaderService.headerSubmit(soHeaderId));
    }

    @ApiOperation(value = "审批销售订单")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @PutMapping("/approve/{soHeaderId}")
    public ResponseEntity<HodrSoHeader> approve(@PathVariable(value = "organizationId") Long organizationId,
                                                @PathVariable(value = "soHeaderId") Long soHeaderId) {
        return Results.success(hodrSoHeaderService.headerApprove(soHeaderId));
    }

    @ApiOperation(value = "拒绝销售订单")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @PutMapping("/reject/{soHeaderId}")
    public ResponseEntity<HodrSoHeader> reject(@PathVariable(value = "organizationId") Long organizationId,
                                               @PathVariable(value = "soHeaderId") Long soHeaderId) {
        return Results.success(hodrSoHeaderService.headerReject(soHeaderId));
    }


    @ApiOperation(value = "整单删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @DeleteMapping
    public ResponseEntity<Void> remove(@PathVariable(value = "organizationId") Long organizationId,
                                       @RequestBody HodrSoHeader hodrSoHeader) {
        SecurityTokenHelper.validToken(hodrSoHeader);
        hodrSoHeaderService.delete(hodrSoHeader);
        return ResponseEntity.noContent().build();
    }

}

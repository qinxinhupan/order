package org.hzero.order.api.controller.v1;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.platform.lov.annotation.ProcessLovValue;
import org.hzero.core.base.BaseConstants;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.hzero.export.annotation.ExcelExport;
import org.hzero.export.vo.ExportParam;
import org.hzero.mybatis.helper.SecurityTokenHelper;
import org.hzero.order.api.controller.dto.OrderExportExcelDTO;
import org.hzero.order.api.controller.dto.OrderImportExcelDTO;
import org.hzero.order.app.listener.ExcelListener;
import org.hzero.order.app.service.HodrSoHeaderService;
import org.hzero.order.app.service.HodrSoLineService;
import org.hzero.order.config.SwaggerApiConfig;
import org.hzero.order.domain.entity.HodrSoLine;
import org.hzero.order.domain.repository.HodrSoHeaderRepository;
import org.hzero.order.domain.repository.HodrSoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 销售订单行 管理 API
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:32
 */
@Api(tags = SwaggerApiConfig.HZERO_ORDER)
@RestController("hodrSoLineController.v1")
@RequestMapping("/v1/{organizationId}/hodr-so-lines")
public class HodrSoLineController extends BaseController {

    @Autowired
    private HodrSoLineRepository hodrSoLineRepository;
    @Autowired
    private HodrSoLineService hodrSoLineService;
    @Autowired
    private HodrSoHeaderService hodrSoHeaderService;
    @Autowired
    private HodrSoHeaderRepository hodrSoHeaderRepository;


    @ApiOperation(value = "销售订单汇总查询")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @GetMapping
    public ResponseEntity<Page<HodrSoLine>> list(HodrSoLine hodrSoLine,
                                                 @PathVariable(value = "organizationId") Long organizationId,
                                                 @ApiIgnore @SortDefault(value = HodrSoLine.FIELD_SO_LINE_ID,
                                                         direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<HodrSoLine> list = hodrSoLineRepository.selectByConditionLike(pageRequest, hodrSoLine);
        return Results.success(list);
    }

    @ApiOperation(value = "销售订单汇总导出Excel")
    @Permission(level = ResourceLevel.ORGANIZATION,permissionPublic = true)
    @ExcelExport(OrderExportExcelDTO.class)
//    @ProcessLovValue
    @GetMapping("/export")
    public ResponseEntity<List<OrderExportExcelDTO>> export(HodrSoLine hodrSoLine,
                                                            ExportParam exportParam,
                                                            HttpServletResponse response,
                                                            @PathVariable(value = "organizationId") Long organizationId
                                                            ) {
        return Results.success( hodrSoLineService.excelExport(exportParam, hodrSoLine));
    }

    @ApiOperation(value = "销售订单汇总导入Excel")
    @Permission(level = ResourceLevel.ORGANIZATION,permissionPublic = true)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @PostMapping("/import")
    public ResponseEntity<Void> excelImport(@PathVariable(value = "organizationId") Long organizationId,
                                            MultipartFile multipartFile) throws IOException {
        ExcelListener excelListener =new ExcelListener(hodrSoLineService,hodrSoHeaderService,hodrSoHeaderRepository);
        ExcelReaderBuilder builder = EasyExcel.read(multipartFile.getInputStream(),OrderImportExcelDTO.class, excelListener);
        ExcelReader excelReader = builder.build();
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
        sheets.forEach(readSheet -> {
            System.err.println(readSheet.getSheetName());
            excelReader.read(readSheet);
        });
        excelReader.finish();
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "销售订单明细行信息")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @GetMapping("/{soHeaderId}/line")
    public ResponseEntity<Page<HodrSoLine>> lineDetail(@PathVariable(value = "organizationId") Long organizationId,
                                                       @PathVariable Long soHeaderId,
                                                       @ApiIgnore @SortDefault(value = HodrSoLine.FIELD_SO_LINE_ID,
                                                               direction = Sort.Direction.DESC)
                                                               PageRequest pageRequest) {
        return Results.success(hodrSoLineRepository.selectLineBySoHeaderId(pageRequest, soHeaderId));
    }




    @ApiOperation(value = "批量创建/修改销售订单行")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @PostMapping
    public ResponseEntity<List<HodrSoLine>> lineCreateOrUpdate(@PathVariable(value = "organizationId") Long organizationId,
                                                       @RequestBody List<HodrSoLine> hodrSoLineList) {
        validList(hodrSoLineList);
        hodrSoLineService.batchSaveOrderLine(hodrSoLineList);
        return Results.success(hodrSoLineList);
    }


    @ApiOperation(value = "批量删除销售订单行")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @DeleteMapping
    public ResponseEntity<Void> lineRemove(@PathVariable(value = "organizationId") Long organizationId,
                                           @RequestBody List<HodrSoLine> hodrSoLineList) {
        SecurityTokenHelper.validToken(hodrSoLineList);
        hodrSoLineService.batchDeleteOrderLine(hodrSoLineList);
        return Results.success();
    }

}

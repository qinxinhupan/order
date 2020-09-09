package org.hzero.order.app.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import io.choerodon.core.exception.CommonException;
import org.hzero.order.api.controller.dto.OrderImportExcelDTO;
import org.hzero.order.app.service.HodrSoHeaderService;
import org.hzero.order.app.service.HodrSoLineService;
import org.hzero.order.domain.entity.HodrSoHeader;
import org.hzero.order.domain.entity.HodrSoLine;
import org.hzero.order.domain.repository.HodrSoHeaderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author haibo.wang@hand-china.com
 */

public class ExcelListener extends AnalysisEventListener<OrderImportExcelDTO> {

    private List<OrderImportExcelDTO> datas = new ArrayList<>();

    private List<HodrSoHeader> hodrSoHeaderList = new ArrayList<>();

    private HodrSoLineService hodrSoLineService;

    private HodrSoHeaderService hodrSoHeaderService;

    private HodrSoHeaderRepository hodrSoHeaderRepository;

    public ExcelListener(HodrSoLineService hodrSoLineService, HodrSoHeaderService hodrSoHeaderService, HodrSoHeaderRepository hodrSoHeaderRepository) {
        this.hodrSoLineService = hodrSoLineService;
        this.hodrSoHeaderService = hodrSoHeaderService;
        this.hodrSoHeaderRepository = hodrSoHeaderRepository;
    }

    HodrSoHeader hodrSoHeader = new HodrSoHeader();
    HodrSoLine hodrSoLine = new HodrSoLine();

    HodrSoHeader hodrSoHeaderDTO;
    HodrSoLine hodrSoLineDTO;
    int i = 0;

    @Override
    public void invoke(OrderImportExcelDTO orderImportExcelDTO, AnalysisContext context) {
        Map<String, String> map = hodrSoLineService.map();
        orderImportExcelDTO.setOrderStatus(map.get(orderImportExcelDTO.getOrderStatusMeaning()));
        try {
            orderImportExcelDTO.setCompanyId(hodrSoHeaderRepository.selectCompanyId(orderImportExcelDTO.getCompanyNumber()));
            orderImportExcelDTO.setCustomerId(hodrSoHeaderRepository.selectCustomerId(orderImportExcelDTO.getCustomerNmber()));
            orderImportExcelDTO.setItemId(hodrSoHeaderRepository.selectItemId(orderImportExcelDTO.getItemCode()));
            datas.add(orderImportExcelDTO);
        } catch (Exception e) {
            throw new CommonException("excel数据存在问题");
        }
        if(hodrSoHeader.getOrderNumber()==null) {

            hodrSoHeader.setOrderNumber(orderImportExcelDTO.getOrderNumber());
            hodrSoHeader.setOrderStatus(orderImportExcelDTO.getOrderStatus());
            hodrSoHeader.setOrderDate(orderImportExcelDTO.getOrderDate());
            hodrSoHeader.setCompanyId(orderImportExcelDTO.getCompanyId());
            hodrSoHeader.setCustomerId(orderImportExcelDTO.getCustomerId());

            hodrSoLine.setLineNumber(orderImportExcelDTO.getLineNumber());
            hodrSoLine.setItemId(orderImportExcelDTO.getItemId());
            hodrSoLine.setOrderQuantity(orderImportExcelDTO.getOrderQuantity());
            hodrSoLine.setOrderQuantityUom(orderImportExcelDTO.getOrderQuantityUom());
            hodrSoLine.setUnitSellingPrice(orderImportExcelDTO.getUnitSellingPrice());
            hodrSoLine.setDescription(orderImportExcelDTO.getDescription());
//            BeanUtils.copyProperties(orderImportExcelDTO,hodrSoHeader);
//            BeanUtils.copyProperties(orderImportExcelDTO,hodrSoLine);
            hodrSoHeader.setHodrSoLineList(new ArrayList<>());
            HodrSoLine hodrSoLineDTO = new HodrSoLine();
            BeanUtils.copyProperties(hodrSoLine,hodrSoLineDTO);
            hodrSoHeader.getHodrSoLineList().add(hodrSoLineDTO);

        }
        else if (hodrSoHeader.getOrderNumber()!=null && hodrSoHeader.getOrderNumber().equals(orderImportExcelDTO.getOrderNumber())) {
            hodrSoHeaderDTO = new HodrSoHeader();
            hodrSoLineDTO = new HodrSoLine();
            BeanUtils.copyProperties(orderImportExcelDTO,hodrSoLineDTO);
            System.err.println("llllll"+hodrSoLineDTO);
            hodrSoHeader.getHodrSoLineList().add(hodrSoLineDTO);

        } else if(hodrSoHeader.getOrderNumber()!=null && !hodrSoHeader.getOrderNumber().equals(orderImportExcelDTO.getOrderNumber())){
            System.err.println(hodrSoHeader.getOrderNumber()+"--------------"+orderImportExcelDTO.getOrderNumber());
            System.err.println("HEADER"+hodrSoHeader);
            System.err.println("#####");
            System.err.println("LINE"+hodrSoHeader.getHodrSoLineList());
            hodrSoHeaderDTO = new HodrSoHeader();
            hodrSoLineDTO = new HodrSoLine();
            BeanUtils.copyProperties(hodrSoHeader,hodrSoHeaderDTO);
            hodrSoHeaderList.add(hodrSoHeaderDTO);
            System.err.println(i++);
            hodrSoHeader.setHodrSoLineList(new ArrayList<>());
//            hodrSoHeader.getHodrSoLineList().clear();
            BeanUtils.copyProperties(orderImportExcelDTO,hodrSoHeader);
            BeanUtils.copyProperties(orderImportExcelDTO,hodrSoLineDTO);
            hodrSoHeader.getHodrSoLineList().add(hodrSoLineDTO);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        hodrSoHeaderList.add(hodrSoHeader);
        hodrSoHeaderList.forEach(hodrSoHeaderVO ->{
            System.err.println(hodrSoHeaderVO.getOrderNumber());
            System.err.println(hodrSoHeaderVO.getHodrSoLineList());
            hodrSoHeaderService.importExcel(hodrSoHeaderVO);
        } );
    }

    public List<OrderImportExcelDTO> getDatas() {
        return datas;
    }

    public void setDatas(List<OrderImportExcelDTO> datas) {
        this.datas = datas;
    }
}

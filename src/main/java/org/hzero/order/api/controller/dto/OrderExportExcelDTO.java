package org.hzero.order.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.choerodon.core.exception.CommonException;
import org.hzero.boot.platform.lov.annotation.LovValue;
import org.hzero.core.cache.Cacheable;
import org.hzero.export.annotation.ExcelColumn;
import org.hzero.export.annotation.ExcelSheet;
import org.hzero.order.infra.Constant;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author haibo.wang@hand-china.com
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ExcelSheet(zh = "订单数据", en = "Order record")
public class OrderExportExcelDTO implements Cacheable {

    private Long soHeaderId;
    @ExcelColumn(zh = "销售订单号", en = "orderNumber", showInChildren = true)
    private String orderNumber;
    @ExcelColumn(zh = "公司名称", en = "companyName")
    private String companyName;
    @ExcelColumn(zh = "客户名称", en = "customerName")
    private String customerName;
//    @ExcelColumn(zh = "订单日期", en = "orderDate")
    private Date orderDate;
    @LovValue(value = Constant.ORDER_STATUS)
    private String orderStatus;
    @ExcelColumn(zh = "订单状态", en = "orderStatus")
    private String orderStatusMeaning;
    @ExcelColumn(zh = "订单金额", en = "orderAmount")
    private BigDecimal orderAmount;

    @ExcelColumn(zh = "订单行列表", en = "orderLineExportDTOList",child = true)
    private List<OrderLineExportDTO> orderLineExportDTOList;

    @ExcelColumn(zh = "订单日期", en = "orderDate")
    private String orderTime;


    public void dateToString() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.Pattern.DATE);
            if (Objects.nonNull(this.orderDate)) {
                this.orderTime = simpleDateFormat.format(this.orderDate);
            }
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }

    }
    public Long getSoHeaderId() {
        return soHeaderId;
    }

    public void setSoHeaderId(Long soHeaderId) {
        this.soHeaderId = soHeaderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusMeaning() {
        return orderStatusMeaning;
    }

    public void setOrderStatusMeaning(String orderStatusMeaning) {
        this.orderStatusMeaning = orderStatusMeaning;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public List<OrderLineExportDTO> getOrderLineExportDTOList() {
        return orderLineExportDTOList;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setOrderLineExportDTOList(List<OrderLineExportDTO> orderLineExportDTOList) {
        this.orderLineExportDTOList = orderLineExportDTOList;
    }
}

package org.hzero.order.api.controller.dto;

import org.hzero.export.annotation.ExcelColumn;
import org.hzero.export.annotation.ExcelSheet;

import java.math.BigDecimal;

/**
 * @author haibo.wang@hand-china.com
 */
@ExcelSheet(zh = "订单行", en = "Order line")
public class OrderLineExportDTO {

    @ExcelColumn(zh = "行号", en = "lineNumber")
    private Long lineNumber;
    @ExcelColumn(zh = "物料编码", en = "itemCode")
    private String itemCode;
    @ExcelColumn(zh = "物料描述", en = "itemDescription")
    private String itemDescription;
    @ExcelColumn(zh = "产品单位", en = "orderQuantityUom")
    private String orderQuantityUom;
    @ExcelColumn(zh = "数量", en = "orderQuantity")
    private BigDecimal orderQuantity;
    @ExcelColumn(zh = "销售单价", en = "unitSellingPrice")
    private BigDecimal unitSellingPrice;
    @ExcelColumn(zh = "行金额", en = "lineAmount")
    private BigDecimal lineAmount;
    @ExcelColumn(zh = "备注", en = "description")
    private String description;

    public Long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getOrderQuantityUom() {
        return orderQuantityUom;
    }

    public void setOrderQuantityUom(String orderQuantityUom) {
        this.orderQuantityUom = orderQuantityUom;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public void setUnitSellingPrice(BigDecimal unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }

    public BigDecimal getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(BigDecimal lineAmount) {
        this.lineAmount = lineAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

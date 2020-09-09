package org.hzero.order.api.controller.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.annotation.JSONField;
import org.hzero.boot.platform.lov.annotation.LovValue;
import org.hzero.order.infra.Constant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author haibo.wang@hand-china.com
 */
public class OrderImportExcelDTO {

    @ExcelProperty(value = "order_number")
    @JSONField(name = "order_number" )
    private String orderNumber;
    @ExcelProperty(value = "company_number")
    @JSONField(name= "company_number" )
    private String companyNumber;
    @ExcelProperty(value = "order_date")
    @JSONField(name= "order_date" )
    private Date orderDate;
//    @LovValue(lovCode = Constant.ORDER_STATUS)
    @ExcelProperty(value = "order_status")
    @JSONField(name= "order_status" )
    private String orderStatus;
    @ExcelProperty(value = "order_status")
    private String orderStatusMeaning;
    @ExcelProperty(value = "customer_number")
    @JSONField(name= "customer_number" )
    private String customerNmber;
    @ExcelProperty(value = "line_number")
    @JSONField(name= "line_number" )
    private Long lineNumber;
    @ExcelProperty(value = "item_code")
    @JSONField(name= "item_code" )
    private String itemCode;
    @ExcelProperty(value = "item_meaning")
    @JSONField(name = "item_meaning")
    private String itemMeaning;
    @ExcelProperty(value = "order_quantity")
    @JSONField(name= "order_quantity" )
    private BigDecimal orderQuantity;
    @ExcelProperty(value = "order_quantity_uom")
    @JSONField(name= "order_quantity_uom" )
    private String orderQuantityUom;
    @ExcelProperty(value = "unit_selling_price")
    @JSONField(name= "unit_selling_price" )
    private BigDecimal unitSellingPrice;
    @ExcelProperty(value = "description")
    @JSONField(name= "description" )
    private String description;
    @ExcelProperty(value = "addition1")
    @JSONField(name= "addition1" )
    private String addition1;
    @ExcelProperty(value = "addition2")
    @JSONField(name= "addition2" )
    private String addition2;
    @ExcelProperty(value = "addition3")
    @JSONField(name= "addition3" )
    private String addition3;
    @ExcelProperty(value = "addition4")
    @JSONField(name= "addition4" )
    private String addition4;
    @ExcelProperty(value = "addition5")
    @JSONField(name= "addition5" )
    private String addition5;

    private Long itemId;

    private Long companyId;

    private Long customerId;




    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
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

    public String getCustomerNmber() {
        return customerNmber;
    }

    public void setCustomerNmber(String customerNmber) {
        this.customerNmber = customerNmber;
    }

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

    public String getItemMeaning() {
        return itemMeaning;
    }

    public void setItemMeaning(String itemMeaning) {
        this.itemMeaning = itemMeaning;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderQuantityUom() {
        return orderQuantityUom;
    }

    public void setOrderQuantityUom(String orderQuantityUom) {
        this.orderQuantityUom = orderQuantityUom;
    }

    public BigDecimal getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public void setUnitSellingPrice(BigDecimal unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddition1() {
        return addition1;
    }

    public void setAddition1(String addition1) {
        this.addition1 = addition1;
    }

    public String getAddition2() {
        return addition2;
    }

    public void setAddition2(String addition2) {
        this.addition2 = addition2;
    }

    public String getAddition3() {
        return addition3;
    }

    public void setAddition3(String addition3) {
        this.addition3 = addition3;
    }

    public String getAddition4() {
        return addition4;
    }

    public void setAddition4(String addition4) {
        this.addition4 = addition4;
    }

    public String getAddition5() {
        return addition5;
    }

    public void setAddition5(String addition5) {
        this.addition5 = addition5;
    }

    public String getOrderStatusMeaning() {
        return orderStatusMeaning;
    }

    public void setOrderStatusMeaning(String orderStatusMeaning) {
        this.orderStatusMeaning = orderStatusMeaning;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}

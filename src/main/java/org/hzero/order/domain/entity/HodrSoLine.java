package org.hzero.order.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import io.choerodon.mybatis.domain.AuditDomain;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hzero.boot.platform.lov.annotation.LovValue;
import org.hzero.export.annotation.ExcelColumn;
import org.hzero.export.annotation.ExcelSheet;
import org.hzero.order.infra.Constant;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 销售订单行
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:32
 */
@ApiModel("销售订单行")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_so_line")
@ExcelSheet(zh = "销售订单", en = "Order")
public class HodrSoLine extends AuditDomain {

    public static final String FIELD_SO_LINE_ID = "soLineId";
    public static final String FIELD_SO_HEADER_ID = "soHeaderId";
    public static final String FIELD_LINE_NUMBER = "lineNumber";
    public static final String FIELD_ITEM_ID = "itemId";
    public static final String FIELD_ORDER_QUANTITY = "orderQuantity";
    public static final String FIELD_ORDER_QUANTITY_UOM = "orderQuantityUom";
    public static final String FIELD_UNIT_SELLING_PRICE = "unitSellingPrice";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_ADDITION1 = "addition1";
    public static final String FIELD_ADDITION2 = "addition2";
    public static final String FIELD_ADDITION3 = "addition3";
    public static final String FIELD_ADDITION4 = "addition4";
    public static final String FIELD_ADDITION5 = "addition5";

    //
    // 业务方法(按public protected private顺序排列)
    // ------------------------------------------------------------------------------

    //
    // 数据库字段
    // ------------------------------------------------------------------------------


    @ApiModelProperty("表ID，主键，供其他表做外键")
    @Id
    @GeneratedValue
    private Long soLineId;
    @ApiModelProperty(value = "订单头ID")
    @NotNull
    private Long soHeaderId;
    @ApiModelProperty(value = "行号")
    @NotNull
    private Long lineNumber;
    @ApiModelProperty(value = "产品ID")
    @NotNull
    private Long itemId;
    @ApiModelProperty(value = "数量")
    @NotNull
    private BigDecimal orderQuantity;
    @ApiModelProperty(value = "产品单位")
    @NotBlank
    private String orderQuantityUom;
    @ApiModelProperty(value = "销售单价")
    @NotNull
    private BigDecimal unitSellingPrice;
   @ApiModelProperty(value = "备注")    
    private String description;
   @ApiModelProperty(value = "附加信息1")    
    private String addition1;
   @ApiModelProperty(value = "附加信息2")    
    private String addition2;
   @ApiModelProperty(value = "附加信息3")    
    private String addition3;
   @ApiModelProperty(value = "附加信息4")    
    private String addition4;
   @ApiModelProperty(value = "附加信息5")    
    private String addition5;

	//
    // 非数据库字段
    // ------------------------------------------------------------------------------

	@Transient
	@ApiModelProperty(value = "公司id")
	private Long companyId;
	@ExcelColumn(zh = "公司名称", en = "companyName")
	@ApiModelProperty(value = "公司名称")
	@Transient
	private String companyName;
	@Transient
	@ApiModelProperty(value = "客户id")
	private Long customerId;
	@ApiModelProperty(value = "客户名称")
	@Transient
	@ExcelColumn(zh = "客户名称", en = "customerName")
	private String customerName;
	@ApiModelProperty(value = "订单编号")
	@Transient
	@ExcelColumn(zh = "销售订单号", en = "orderNumber", showInChildren = true)
	private String orderNumber;
	@ApiModelProperty(value = "物料描述")
	@Transient
	private String itemDescription;
	@ApiModelProperty(value = "订单状态")
	@Transient
	@ExcelColumn(zh = "订单状态", en = "orderStatus")
	@LovValue(value = Constant.ORDER_STATUS)
	private String orderStatus;
	@ApiModelProperty(value = "销售订单状态meaning")
	@Transient
	private String orderStatusMeaning;
	@ApiModelProperty(value = "订单日期")
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelColumn(zh = "订单日期", en = "orderDate")
	private Date orderDate;
	@Transient
	@ApiModelProperty(value = "订单金额")
	@ExcelColumn(zh = "订单金额", en = "orderAmount")
	private BigDecimal orderAmount;
	@Transient
	@ApiModelProperty(value = "行金额")
	private BigDecimal lineAmount;

	@Transient
	@ExcelColumn
	private List<HodrSoLine> hodrSoLineList;

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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getLineAmount() {
		return lineAmount;
	}

	public void setLineAmount(BigDecimal lineAmount) {
		this.lineAmount = lineAmount;
	}

	public String getOrderStatusMeaning() {
		return orderStatusMeaning;
	}

	public void setOrderStatusMeaning(String orderStatusMeaning) {
		this.orderStatusMeaning = orderStatusMeaning;
	}

	public List<HodrSoLine> getHodrSoLineList() {
		return hodrSoLineList;
	}

	public void setHodrSoLineList(List<HodrSoLine> hodrSoLineList) {
		this.hodrSoLineList = hodrSoLineList;
	}

	//
    // getter/setter
    // ------------------------------------------------------------------------------

    /**
     * @return 表ID，主键，供其他表做外键
     */
	public Long getSoLineId() {
		return soLineId;
	}

	public void setSoLineId(Long soLineId) {
		this.soLineId = soLineId;
	}
    /**
     * @return 订单头ID
     */
	public Long getSoHeaderId() {
		return soHeaderId;
	}

	public void setSoHeaderId(Long soHeaderId) {
		this.soHeaderId = soHeaderId;
	}
    /**
     * @return 行号
     */
	public Long getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Long lineNumber) {
		this.lineNumber = lineNumber;
	}
    /**
     * @return 产品ID
     */
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
    /**
     * @return 数量
     */
	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
    /**
     * @return 产品单位
     */
	public String getOrderQuantityUom() {
		return orderQuantityUom;
	}

	public void setOrderQuantityUom(String orderQuantityUom) {
		this.orderQuantityUom = orderQuantityUom;
	}
    /**
     * @return 销售单价
     */
	public BigDecimal getUnitSellingPrice() {
		return unitSellingPrice;
	}

	public void setUnitSellingPrice(BigDecimal unitSellingPrice) {
		this.unitSellingPrice = unitSellingPrice;
	}
    /**
     * @return 备注
     */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    /**
     * @return 附加信息1
     */
	public String getAddition1() {
		return addition1;
	}

	public void setAddition1(String addition1) {
		this.addition1 = addition1;
	}
    /**
     * @return 附加信息2
     */
	public String getAddition2() {
		return addition2;
	}

	public void setAddition2(String addition2) {
		this.addition2 = addition2;
	}
    /**
     * @return 附加信息3
     */
	public String getAddition3() {
		return addition3;
	}

	public void setAddition3(String addition3) {
		this.addition3 = addition3;
	}
    /**
     * @return 附加信息4
     */
	public String getAddition4() {
		return addition4;
	}

	public void setAddition4(String addition4) {
		this.addition4 = addition4;
	}
    /**
     * @return 附加信息5
     */
	public String getAddition5() {
		return addition5;
	}

	public void setAddition5(String addition5) {
		this.addition5 = addition5;
	}

}

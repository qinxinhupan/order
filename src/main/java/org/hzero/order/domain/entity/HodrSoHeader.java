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
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hzero.boot.platform.lov.annotation.LovValue;
import org.hzero.export.annotation.ExcelColumn;
import org.hzero.order.infra.Constant;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售订单头
 *
 * @author haibo.wang@hand-china.com 2020-08-29 14:32:33
 */
@ApiModel("销售订单头")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_so_header")
public class HodrSoHeader extends AuditDomain {

    public static final String FIELD_SO_HEADER_ID = "soHeaderId";
    public static final String FIELD_ORDER_NUMBER = "orderNumber";
    public static final String FIELD_COMPANY_ID = "companyId";
    public static final String FIELD_ORDER_DATE = "orderDate";
    public static final String FIELD_ORDER_STATUS = "orderStatus";
    public static final String FIELD_CUSTOMER_ID = "customerId";

    //
    // 业务方法(按public protected private顺序排列)
    // ------------------------------------------------------------------------------

    //
    // 数据库字段
    // ------------------------------------------------------------------------------


    @ApiModelProperty("表ID，主键，供其他表做外键")
    @Id
    @GeneratedValue
    private Long soHeaderId;
    @ApiModelProperty(value = "订单编号")
    @NotBlank
    private String orderNumber;
    @ApiModelProperty(value = "公司ID")
    @NotNull
    private Long companyId;
    @ApiModelProperty(value = "订单日期")
    @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    @ApiModelProperty(value = "")
    @NotBlank
	@LovValue(value = Constant.ORDER_STATUS)
    private String orderStatus;
    @ApiModelProperty(value = "")
    @NotNull
    private Long customerId;

    @Transient
    private List<HodrSoLine> hodrSoLineList;

	//
    // 非数据库字段
    // ------------------------------------------------------------------------------

	@ApiModelProperty(value = "销售订单状态meaning")
	@Transient
	private String orderStatusMeaning;
	@ApiModelProperty(value = "公司名称")
	@Transient
	private String companyName;
	@Transient
	@ApiModelProperty(value = "订单金额")
	private BigDecimal orderAmount;
	@ApiModelProperty(value = "客户名称")
	@Transient
	private String customerName;

	@Transient
	@ApiModelProperty(value = "客户编号")
	private String customerNum;
	@ApiModelProperty(value = "公司编号")
	@Transient
	private String companyNum;
	@ApiModelProperty(value = "物料编码")
	@Transient
	private String itemCode;

    //
    // getter/setter
    // ------------------------------------------------------------------------------

    /**
     * @return 表ID，主键，供其他表做外键
     */
	public Long getSoHeaderId() {
		return soHeaderId;
	}

	public void setSoHeaderId(Long soHeaderId) {
		this.soHeaderId = soHeaderId;
	}
    /**
     * @return 订单编号
     */
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
    /**
     * @return 公司ID
     */
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
    /**
     * @return 订单日期
     */
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
    /**
     * @return 
     */
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
    /**
     * @return 
     */
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getOrderStatusMeaning() {
		return orderStatusMeaning;
	}

	public void setOrderStatusMeaning(String orderStatusMeaning) {
		this.orderStatusMeaning = orderStatusMeaning;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getCompanyNum() {
		return companyNum;
	}

	public void setCompanyNum(String companyNum) {
		this.companyNum = companyNum;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public List<HodrSoLine> getHodrSoLineList() {
		return hodrSoLineList;
	}

	public void setHodrSoLineList(List<HodrSoLine> hodrSoLineList) {
		this.hodrSoLineList = hodrSoLineList;
	}
}

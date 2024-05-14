package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 家政服务订单
 *
 * @author 
 * @email
 */
@TableName("jiazhengfuwu_order")
public class JiazhengfuwuOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiazhengfuwuOrderEntity() {

	}

	public JiazhengfuwuOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 订单号
     */
    @TableField(value = "jiazhengfuwu_order_uuid_number")

    private String jiazhengfuwuOrderUuidNumber;


    /**
     * 联系人
     */
    @TableField(value = "jiazhengfuwu_lianxiren_name")

    private String jiazhengfuwuLianxirenName;


    /**
     * 联系人电话
     */
    @TableField(value = "jiazhengfuwu_lianxiren_phone")

    private String jiazhengfuwuLianxirenPhone;


    /**
     * 服务地址
     */
    @TableField(value = "jiazhengfuwu_lianxiren_address")

    private String jiazhengfuwuLianxirenAddress;


    /**
     * 家政服务
     */
    @TableField(value = "jiazhengfuwu_id")

    private Integer jiazhengfuwuId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 员工
     */
    @TableField(value = "yuangong_id")

    private Integer yuangongId;


    /**
     * 小时
     */
    @TableField(value = "buy_number")

    private Integer buyNumber;


    /**
     * 实付价格
     */
    @TableField(value = "jiazhengfuwu_order_true_price")

    private Double jiazhengfuwuOrderTruePrice;


    /**
     * 订单类型
     */
    @TableField(value = "jiazhengfuwu_order_types")

    private Integer jiazhengfuwuOrderTypes;


    /**
     * 支付类型
     */
    @TableField(value = "jiazhengfuwu_order_payment_types")

    private Integer jiazhengfuwuOrderPaymentTypes;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "yuyue_time")

    private Date yuyueTime;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getJiazhengfuwuOrderUuidNumber() {
        return jiazhengfuwuOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setJiazhengfuwuOrderUuidNumber(String jiazhengfuwuOrderUuidNumber) {
        this.jiazhengfuwuOrderUuidNumber = jiazhengfuwuOrderUuidNumber;
    }
    /**
	 * 设置：联系人
	 */
    public String getJiazhengfuwuLianxirenName() {
        return jiazhengfuwuLianxirenName;
    }


    /**
	 * 获取：联系人
	 */

    public void setJiazhengfuwuLianxirenName(String jiazhengfuwuLianxirenName) {
        this.jiazhengfuwuLianxirenName = jiazhengfuwuLianxirenName;
    }
    /**
	 * 设置：联系人电话
	 */
    public String getJiazhengfuwuLianxirenPhone() {
        return jiazhengfuwuLianxirenPhone;
    }


    /**
	 * 获取：联系人电话
	 */

    public void setJiazhengfuwuLianxirenPhone(String jiazhengfuwuLianxirenPhone) {
        this.jiazhengfuwuLianxirenPhone = jiazhengfuwuLianxirenPhone;
    }
    /**
	 * 设置：服务地址
	 */
    public String getJiazhengfuwuLianxirenAddress() {
        return jiazhengfuwuLianxirenAddress;
    }


    /**
	 * 获取：服务地址
	 */

    public void setJiazhengfuwuLianxirenAddress(String jiazhengfuwuLianxirenAddress) {
        this.jiazhengfuwuLianxirenAddress = jiazhengfuwuLianxirenAddress;
    }
    /**
	 * 设置：家政服务
	 */
    public Integer getJiazhengfuwuId() {
        return jiazhengfuwuId;
    }


    /**
	 * 获取：家政服务
	 */

    public void setJiazhengfuwuId(Integer jiazhengfuwuId) {
        this.jiazhengfuwuId = jiazhengfuwuId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：员工
	 */
    public Integer getYuangongId() {
        return yuangongId;
    }


    /**
	 * 获取：员工
	 */

    public void setYuangongId(Integer yuangongId) {
        this.yuangongId = yuangongId;
    }
    /**
	 * 设置：小时
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：小时
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getJiazhengfuwuOrderTruePrice() {
        return jiazhengfuwuOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setJiazhengfuwuOrderTruePrice(Double jiazhengfuwuOrderTruePrice) {
        this.jiazhengfuwuOrderTruePrice = jiazhengfuwuOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getJiazhengfuwuOrderTypes() {
        return jiazhengfuwuOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setJiazhengfuwuOrderTypes(Integer jiazhengfuwuOrderTypes) {
        this.jiazhengfuwuOrderTypes = jiazhengfuwuOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getJiazhengfuwuOrderPaymentTypes() {
        return jiazhengfuwuOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setJiazhengfuwuOrderPaymentTypes(Integer jiazhengfuwuOrderPaymentTypes) {
        this.jiazhengfuwuOrderPaymentTypes = jiazhengfuwuOrderPaymentTypes;
    }
    /**
	 * 设置：预约时间
	 */
    public Date getYuyueTime() {
        return yuyueTime;
    }


    /**
	 * 获取：预约时间
	 */

    public void setYuyueTime(Date yuyueTime) {
        this.yuyueTime = yuyueTime;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "JiazhengfuwuOrder{" +
            "id=" + id +
            ", jiazhengfuwuOrderUuidNumber=" + jiazhengfuwuOrderUuidNumber +
            ", jiazhengfuwuLianxirenName=" + jiazhengfuwuLianxirenName +
            ", jiazhengfuwuLianxirenPhone=" + jiazhengfuwuLianxirenPhone +
            ", jiazhengfuwuLianxirenAddress=" + jiazhengfuwuLianxirenAddress +
            ", jiazhengfuwuId=" + jiazhengfuwuId +
            ", yonghuId=" + yonghuId +
            ", yuangongId=" + yuangongId +
            ", buyNumber=" + buyNumber +
            ", jiazhengfuwuOrderTruePrice=" + jiazhengfuwuOrderTruePrice +
            ", jiazhengfuwuOrderTypes=" + jiazhengfuwuOrderTypes +
            ", jiazhengfuwuOrderPaymentTypes=" + jiazhengfuwuOrderPaymentTypes +
            ", yuyueTime=" + yuyueTime +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}

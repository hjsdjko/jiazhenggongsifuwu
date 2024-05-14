package com.entity.model;

import com.entity.JiazhengfuwuOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 家政服务订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiazhengfuwuOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String jiazhengfuwuOrderUuidNumber;


    /**
     * 联系人
     */
    private String jiazhengfuwuLianxirenName;


    /**
     * 联系人电话
     */
    private String jiazhengfuwuLianxirenPhone;


    /**
     * 服务地址
     */
    private String jiazhengfuwuLianxirenAddress;


    /**
     * 家政服务
     */
    private Integer jiazhengfuwuId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 员工
     */
    private Integer yuangongId;


    /**
     * 小时
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double jiazhengfuwuOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer jiazhengfuwuOrderTypes;


    /**
     * 支付类型
     */
    private Integer jiazhengfuwuOrderPaymentTypes;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date yuyueTime;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getJiazhengfuwuOrderUuidNumber() {
        return jiazhengfuwuOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setJiazhengfuwuOrderUuidNumber(String jiazhengfuwuOrderUuidNumber) {
        this.jiazhengfuwuOrderUuidNumber = jiazhengfuwuOrderUuidNumber;
    }
    /**
	 * 获取：联系人
	 */
    public String getJiazhengfuwuLianxirenName() {
        return jiazhengfuwuLianxirenName;
    }


    /**
	 * 设置：联系人
	 */
    public void setJiazhengfuwuLianxirenName(String jiazhengfuwuLianxirenName) {
        this.jiazhengfuwuLianxirenName = jiazhengfuwuLianxirenName;
    }
    /**
	 * 获取：联系人电话
	 */
    public String getJiazhengfuwuLianxirenPhone() {
        return jiazhengfuwuLianxirenPhone;
    }


    /**
	 * 设置：联系人电话
	 */
    public void setJiazhengfuwuLianxirenPhone(String jiazhengfuwuLianxirenPhone) {
        this.jiazhengfuwuLianxirenPhone = jiazhengfuwuLianxirenPhone;
    }
    /**
	 * 获取：服务地址
	 */
    public String getJiazhengfuwuLianxirenAddress() {
        return jiazhengfuwuLianxirenAddress;
    }


    /**
	 * 设置：服务地址
	 */
    public void setJiazhengfuwuLianxirenAddress(String jiazhengfuwuLianxirenAddress) {
        this.jiazhengfuwuLianxirenAddress = jiazhengfuwuLianxirenAddress;
    }
    /**
	 * 获取：家政服务
	 */
    public Integer getJiazhengfuwuId() {
        return jiazhengfuwuId;
    }


    /**
	 * 设置：家政服务
	 */
    public void setJiazhengfuwuId(Integer jiazhengfuwuId) {
        this.jiazhengfuwuId = jiazhengfuwuId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：员工
	 */
    public Integer getYuangongId() {
        return yuangongId;
    }


    /**
	 * 设置：员工
	 */
    public void setYuangongId(Integer yuangongId) {
        this.yuangongId = yuangongId;
    }
    /**
	 * 获取：小时
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：小时
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getJiazhengfuwuOrderTruePrice() {
        return jiazhengfuwuOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setJiazhengfuwuOrderTruePrice(Double jiazhengfuwuOrderTruePrice) {
        this.jiazhengfuwuOrderTruePrice = jiazhengfuwuOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getJiazhengfuwuOrderTypes() {
        return jiazhengfuwuOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setJiazhengfuwuOrderTypes(Integer jiazhengfuwuOrderTypes) {
        this.jiazhengfuwuOrderTypes = jiazhengfuwuOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getJiazhengfuwuOrderPaymentTypes() {
        return jiazhengfuwuOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setJiazhengfuwuOrderPaymentTypes(Integer jiazhengfuwuOrderPaymentTypes) {
        this.jiazhengfuwuOrderPaymentTypes = jiazhengfuwuOrderPaymentTypes;
    }
    /**
	 * 获取：预约时间
	 */
    public Date getYuyueTime() {
        return yuyueTime;
    }


    /**
	 * 设置：预约时间
	 */
    public void setYuyueTime(Date yuyueTime) {
        this.yuyueTime = yuyueTime;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }

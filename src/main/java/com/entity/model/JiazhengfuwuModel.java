package com.entity.model;

import com.entity.JiazhengfuwuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 家政服务
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiazhengfuwuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 服务名称
     */
    private String jiazhengfuwuName;


    /**
     * 服务照片
     */
    private String jiazhengfuwuPhoto;


    /**
     * 家政服务类型
     */
    private Integer jiazhengfuwuTypes;


    /**
     * 服务原价/小时
     */
    private Double jiazhengfuwuOldMoney;


    /**
     * 现价/小时
     */
    private Double jiazhengfuwuNewMoney;


    /**
     * 点击次数
     */
    private Integer jiazhengfuwuClicknum;


    /**
     * 服务介绍
     */
    private String jiazhengfuwuContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer jiazhengfuwuDelete;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：服务名称
	 */
    public String getJiazhengfuwuName() {
        return jiazhengfuwuName;
    }


    /**
	 * 设置：服务名称
	 */
    public void setJiazhengfuwuName(String jiazhengfuwuName) {
        this.jiazhengfuwuName = jiazhengfuwuName;
    }
    /**
	 * 获取：服务照片
	 */
    public String getJiazhengfuwuPhoto() {
        return jiazhengfuwuPhoto;
    }


    /**
	 * 设置：服务照片
	 */
    public void setJiazhengfuwuPhoto(String jiazhengfuwuPhoto) {
        this.jiazhengfuwuPhoto = jiazhengfuwuPhoto;
    }
    /**
	 * 获取：家政服务类型
	 */
    public Integer getJiazhengfuwuTypes() {
        return jiazhengfuwuTypes;
    }


    /**
	 * 设置：家政服务类型
	 */
    public void setJiazhengfuwuTypes(Integer jiazhengfuwuTypes) {
        this.jiazhengfuwuTypes = jiazhengfuwuTypes;
    }
    /**
	 * 获取：服务原价/小时
	 */
    public Double getJiazhengfuwuOldMoney() {
        return jiazhengfuwuOldMoney;
    }


    /**
	 * 设置：服务原价/小时
	 */
    public void setJiazhengfuwuOldMoney(Double jiazhengfuwuOldMoney) {
        this.jiazhengfuwuOldMoney = jiazhengfuwuOldMoney;
    }
    /**
	 * 获取：现价/小时
	 */
    public Double getJiazhengfuwuNewMoney() {
        return jiazhengfuwuNewMoney;
    }


    /**
	 * 设置：现价/小时
	 */
    public void setJiazhengfuwuNewMoney(Double jiazhengfuwuNewMoney) {
        this.jiazhengfuwuNewMoney = jiazhengfuwuNewMoney;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getJiazhengfuwuClicknum() {
        return jiazhengfuwuClicknum;
    }


    /**
	 * 设置：点击次数
	 */
    public void setJiazhengfuwuClicknum(Integer jiazhengfuwuClicknum) {
        this.jiazhengfuwuClicknum = jiazhengfuwuClicknum;
    }
    /**
	 * 获取：服务介绍
	 */
    public String getJiazhengfuwuContent() {
        return jiazhengfuwuContent;
    }


    /**
	 * 设置：服务介绍
	 */
    public void setJiazhengfuwuContent(String jiazhengfuwuContent) {
        this.jiazhengfuwuContent = jiazhengfuwuContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getJiazhengfuwuDelete() {
        return jiazhengfuwuDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setJiazhengfuwuDelete(Integer jiazhengfuwuDelete) {
        this.jiazhengfuwuDelete = jiazhengfuwuDelete;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }

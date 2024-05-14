package com.entity.vo;

import com.entity.JiazhengfuwuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 家政服务
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jiazhengfuwu")
public class JiazhengfuwuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 服务名称
     */

    @TableField(value = "jiazhengfuwu_name")
    private String jiazhengfuwuName;


    /**
     * 服务照片
     */

    @TableField(value = "jiazhengfuwu_photo")
    private String jiazhengfuwuPhoto;


    /**
     * 家政服务类型
     */

    @TableField(value = "jiazhengfuwu_types")
    private Integer jiazhengfuwuTypes;


    /**
     * 服务原价/小时
     */

    @TableField(value = "jiazhengfuwu_old_money")
    private Double jiazhengfuwuOldMoney;


    /**
     * 现价/小时
     */

    @TableField(value = "jiazhengfuwu_new_money")
    private Double jiazhengfuwuNewMoney;


    /**
     * 点击次数
     */

    @TableField(value = "jiazhengfuwu_clicknum")
    private Integer jiazhengfuwuClicknum;


    /**
     * 服务介绍
     */

    @TableField(value = "jiazhengfuwu_content")
    private String jiazhengfuwuContent;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "jiazhengfuwu_delete")
    private Integer jiazhengfuwuDelete;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：服务名称
	 */
    public String getJiazhengfuwuName() {
        return jiazhengfuwuName;
    }


    /**
	 * 获取：服务名称
	 */

    public void setJiazhengfuwuName(String jiazhengfuwuName) {
        this.jiazhengfuwuName = jiazhengfuwuName;
    }
    /**
	 * 设置：服务照片
	 */
    public String getJiazhengfuwuPhoto() {
        return jiazhengfuwuPhoto;
    }


    /**
	 * 获取：服务照片
	 */

    public void setJiazhengfuwuPhoto(String jiazhengfuwuPhoto) {
        this.jiazhengfuwuPhoto = jiazhengfuwuPhoto;
    }
    /**
	 * 设置：家政服务类型
	 */
    public Integer getJiazhengfuwuTypes() {
        return jiazhengfuwuTypes;
    }


    /**
	 * 获取：家政服务类型
	 */

    public void setJiazhengfuwuTypes(Integer jiazhengfuwuTypes) {
        this.jiazhengfuwuTypes = jiazhengfuwuTypes;
    }
    /**
	 * 设置：服务原价/小时
	 */
    public Double getJiazhengfuwuOldMoney() {
        return jiazhengfuwuOldMoney;
    }


    /**
	 * 获取：服务原价/小时
	 */

    public void setJiazhengfuwuOldMoney(Double jiazhengfuwuOldMoney) {
        this.jiazhengfuwuOldMoney = jiazhengfuwuOldMoney;
    }
    /**
	 * 设置：现价/小时
	 */
    public Double getJiazhengfuwuNewMoney() {
        return jiazhengfuwuNewMoney;
    }


    /**
	 * 获取：现价/小时
	 */

    public void setJiazhengfuwuNewMoney(Double jiazhengfuwuNewMoney) {
        this.jiazhengfuwuNewMoney = jiazhengfuwuNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getJiazhengfuwuClicknum() {
        return jiazhengfuwuClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setJiazhengfuwuClicknum(Integer jiazhengfuwuClicknum) {
        this.jiazhengfuwuClicknum = jiazhengfuwuClicknum;
    }
    /**
	 * 设置：服务介绍
	 */
    public String getJiazhengfuwuContent() {
        return jiazhengfuwuContent;
    }


    /**
	 * 获取：服务介绍
	 */

    public void setJiazhengfuwuContent(String jiazhengfuwuContent) {
        this.jiazhengfuwuContent = jiazhengfuwuContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getJiazhengfuwuDelete() {
        return jiazhengfuwuDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setJiazhengfuwuDelete(Integer jiazhengfuwuDelete) {
        this.jiazhengfuwuDelete = jiazhengfuwuDelete;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

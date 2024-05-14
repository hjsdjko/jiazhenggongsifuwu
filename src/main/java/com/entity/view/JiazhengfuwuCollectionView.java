package com.entity.view;

import com.entity.JiazhengfuwuCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 家政服务收藏
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("jiazhengfuwu_collection")
public class JiazhengfuwuCollectionView extends JiazhengfuwuCollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 类型的值
		*/
		private String jiazhengfuwuCollectionValue;



		//级联表 jiazhengfuwu
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
				* 家政服务类型的值
				*/
				private String jiazhengfuwuValue;
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
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer jiazhengfuwuDelete;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;

	public JiazhengfuwuCollectionView() {

	}

	public JiazhengfuwuCollectionView(JiazhengfuwuCollectionEntity jiazhengfuwuCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, jiazhengfuwuCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 类型的值
			*/
			public String getJiazhengfuwuCollectionValue() {
				return jiazhengfuwuCollectionValue;
			}
			/**
			* 设置： 类型的值
			*/
			public void setJiazhengfuwuCollectionValue(String jiazhengfuwuCollectionValue) {
				this.jiazhengfuwuCollectionValue = jiazhengfuwuCollectionValue;
			}







				//级联表的get和set jiazhengfuwu
					/**
					* 获取： 服务名称
					*/
					public String getJiazhengfuwuName() {
						return jiazhengfuwuName;
					}
					/**
					* 设置： 服务名称
					*/
					public void setJiazhengfuwuName(String jiazhengfuwuName) {
						this.jiazhengfuwuName = jiazhengfuwuName;
					}
					/**
					* 获取： 服务照片
					*/
					public String getJiazhengfuwuPhoto() {
						return jiazhengfuwuPhoto;
					}
					/**
					* 设置： 服务照片
					*/
					public void setJiazhengfuwuPhoto(String jiazhengfuwuPhoto) {
						this.jiazhengfuwuPhoto = jiazhengfuwuPhoto;
					}
					/**
					* 获取： 家政服务类型
					*/
					public Integer getJiazhengfuwuTypes() {
						return jiazhengfuwuTypes;
					}
					/**
					* 设置： 家政服务类型
					*/
					public void setJiazhengfuwuTypes(Integer jiazhengfuwuTypes) {
						this.jiazhengfuwuTypes = jiazhengfuwuTypes;
					}


						/**
						* 获取： 家政服务类型的值
						*/
						public String getJiazhengfuwuValue() {
							return jiazhengfuwuValue;
						}
						/**
						* 设置： 家政服务类型的值
						*/
						public void setJiazhengfuwuValue(String jiazhengfuwuValue) {
							this.jiazhengfuwuValue = jiazhengfuwuValue;
						}
					/**
					* 获取： 服务原价/小时
					*/
					public Double getJiazhengfuwuOldMoney() {
						return jiazhengfuwuOldMoney;
					}
					/**
					* 设置： 服务原价/小时
					*/
					public void setJiazhengfuwuOldMoney(Double jiazhengfuwuOldMoney) {
						this.jiazhengfuwuOldMoney = jiazhengfuwuOldMoney;
					}
					/**
					* 获取： 现价/小时
					*/
					public Double getJiazhengfuwuNewMoney() {
						return jiazhengfuwuNewMoney;
					}
					/**
					* 设置： 现价/小时
					*/
					public void setJiazhengfuwuNewMoney(Double jiazhengfuwuNewMoney) {
						this.jiazhengfuwuNewMoney = jiazhengfuwuNewMoney;
					}
					/**
					* 获取： 点击次数
					*/
					public Integer getJiazhengfuwuClicknum() {
						return jiazhengfuwuClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setJiazhengfuwuClicknum(Integer jiazhengfuwuClicknum) {
						this.jiazhengfuwuClicknum = jiazhengfuwuClicknum;
					}
					/**
					* 获取： 服务介绍
					*/
					public String getJiazhengfuwuContent() {
						return jiazhengfuwuContent;
					}
					/**
					* 设置： 服务介绍
					*/
					public void setJiazhengfuwuContent(String jiazhengfuwuContent) {
						this.jiazhengfuwuContent = jiazhengfuwuContent;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getJiazhengfuwuDelete() {
						return jiazhengfuwuDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setJiazhengfuwuDelete(Integer jiazhengfuwuDelete) {
						this.jiazhengfuwuDelete = jiazhengfuwuDelete;
					}
















				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}






}

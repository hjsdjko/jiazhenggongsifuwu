package com.dao;

import com.entity.JiazhengfuwuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiazhengfuwuView;

/**
 * 家政服务 Dao 接口
 *
 * @author 
 */
public interface JiazhengfuwuDao extends BaseMapper<JiazhengfuwuEntity> {

   List<JiazhengfuwuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

package com.dao;

import com.entity.JiazhengfuwuLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiazhengfuwuLiuyanView;

/**
 * 家政服务留言 Dao 接口
 *
 * @author 
 */
public interface JiazhengfuwuLiuyanDao extends BaseMapper<JiazhengfuwuLiuyanEntity> {

   List<JiazhengfuwuLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

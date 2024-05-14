package com.dao;

import com.entity.JiazhengfuwuCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiazhengfuwuCommentbackView;

/**
 * 家政服务评价 Dao 接口
 *
 * @author 
 */
public interface JiazhengfuwuCommentbackDao extends BaseMapper<JiazhengfuwuCommentbackEntity> {

   List<JiazhengfuwuCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

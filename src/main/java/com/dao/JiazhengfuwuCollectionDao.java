package com.dao;

import com.entity.JiazhengfuwuCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiazhengfuwuCollectionView;

/**
 * 家政服务收藏 Dao 接口
 *
 * @author 
 */
public interface JiazhengfuwuCollectionDao extends BaseMapper<JiazhengfuwuCollectionEntity> {

   List<JiazhengfuwuCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

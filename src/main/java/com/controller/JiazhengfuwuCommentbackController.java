
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 家政服务评价
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiazhengfuwuCommentback")
public class JiazhengfuwuCommentbackController {
    private static final Logger logger = LoggerFactory.getLogger(JiazhengfuwuCommentbackController.class);

    @Autowired
    private JiazhengfuwuCommentbackService jiazhengfuwuCommentbackService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private JiazhengfuwuService jiazhengfuwuService;
    @Autowired
    private YonghuService yonghuService;

    @Autowired
    private YuangongService yuangongService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("员工".equals(role))
            params.put("yuangongId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = jiazhengfuwuCommentbackService.queryPage(params);

        //字典表数据转换
        List<JiazhengfuwuCommentbackView> list =(List<JiazhengfuwuCommentbackView>)page.getList();
        for(JiazhengfuwuCommentbackView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiazhengfuwuCommentbackEntity jiazhengfuwuCommentback = jiazhengfuwuCommentbackService.selectById(id);
        if(jiazhengfuwuCommentback !=null){
            //entity转view
            JiazhengfuwuCommentbackView view = new JiazhengfuwuCommentbackView();
            BeanUtils.copyProperties( jiazhengfuwuCommentback , view );//把实体数据重构到view中

                //级联表
                JiazhengfuwuEntity jiazhengfuwu = jiazhengfuwuService.selectById(jiazhengfuwuCommentback.getJiazhengfuwuId());
                if(jiazhengfuwu != null){
                    BeanUtils.copyProperties( jiazhengfuwu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiazhengfuwuId(jiazhengfuwu.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(jiazhengfuwuCommentback.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JiazhengfuwuCommentbackEntity jiazhengfuwuCommentback, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiazhengfuwuCommentback:{}",this.getClass().getName(),jiazhengfuwuCommentback.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jiazhengfuwuCommentback.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        jiazhengfuwuCommentback.setInsertTime(new Date());
        jiazhengfuwuCommentback.setCreateTime(new Date());
        jiazhengfuwuCommentbackService.insert(jiazhengfuwuCommentback);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiazhengfuwuCommentbackEntity jiazhengfuwuCommentback, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiazhengfuwuCommentback:{}",this.getClass().getName(),jiazhengfuwuCommentback.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jiazhengfuwuCommentback.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JiazhengfuwuCommentbackEntity> queryWrapper = new EntityWrapper<JiazhengfuwuCommentbackEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiazhengfuwuCommentbackEntity jiazhengfuwuCommentbackEntity = jiazhengfuwuCommentbackService.selectOne(queryWrapper);
        jiazhengfuwuCommentback.setUpdateTime(new Date());
        if(jiazhengfuwuCommentbackEntity==null){
            jiazhengfuwuCommentbackService.updateById(jiazhengfuwuCommentback);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jiazhengfuwuCommentbackService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JiazhengfuwuCommentbackEntity> jiazhengfuwuCommentbackList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JiazhengfuwuCommentbackEntity jiazhengfuwuCommentbackEntity = new JiazhengfuwuCommentbackEntity();
//                            jiazhengfuwuCommentbackEntity.setJiazhengfuwuId(Integer.valueOf(data.get(0)));   //家政服务 要改的
//                            jiazhengfuwuCommentbackEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jiazhengfuwuCommentbackEntity.setJiazhengfuwuCommentbackText(data.get(0));                    //评价内容 要改的
//                            jiazhengfuwuCommentbackEntity.setInsertTime(date);//时间
//                            jiazhengfuwuCommentbackEntity.setReplyText(data.get(0));                    //回复内容 要改的
//                            jiazhengfuwuCommentbackEntity.setUpdateTime(new Date(data.get(0)));          //回复时间 要改的
//                            jiazhengfuwuCommentbackEntity.setCreateTime(date);//时间
                            jiazhengfuwuCommentbackList.add(jiazhengfuwuCommentbackEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jiazhengfuwuCommentbackService.insertBatch(jiazhengfuwuCommentbackList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = jiazhengfuwuCommentbackService.queryPage(params);

        //字典表数据转换
        List<JiazhengfuwuCommentbackView> list =(List<JiazhengfuwuCommentbackView>)page.getList();
        for(JiazhengfuwuCommentbackView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiazhengfuwuCommentbackEntity jiazhengfuwuCommentback = jiazhengfuwuCommentbackService.selectById(id);
            if(jiazhengfuwuCommentback !=null){


                //entity转view
                JiazhengfuwuCommentbackView view = new JiazhengfuwuCommentbackView();
                BeanUtils.copyProperties( jiazhengfuwuCommentback , view );//把实体数据重构到view中

                //级联表
                    JiazhengfuwuEntity jiazhengfuwu = jiazhengfuwuService.selectById(jiazhengfuwuCommentback.getJiazhengfuwuId());
                if(jiazhengfuwu != null){
                    BeanUtils.copyProperties( jiazhengfuwu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiazhengfuwuId(jiazhengfuwu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jiazhengfuwuCommentback.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody JiazhengfuwuCommentbackEntity jiazhengfuwuCommentback, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiazhengfuwuCommentback:{}",this.getClass().getName(),jiazhengfuwuCommentback.toString());
        jiazhengfuwuCommentback.setInsertTime(new Date());
        jiazhengfuwuCommentback.setCreateTime(new Date());
        jiazhengfuwuCommentbackService.insert(jiazhengfuwuCommentback);
        return R.ok();
        }


}

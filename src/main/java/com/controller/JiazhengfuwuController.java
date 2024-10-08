
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
 * 家政服务
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiazhengfuwu")
public class JiazhengfuwuController {
    private static final Logger logger = LoggerFactory.getLogger(JiazhengfuwuController.class);

    @Autowired
    private JiazhengfuwuService jiazhengfuwuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

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
        params.put("jiazhengfuwuDeleteStart",1);params.put("jiazhengfuwuDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = jiazhengfuwuService.queryPage(params);

        //字典表数据转换
        List<JiazhengfuwuView> list =(List<JiazhengfuwuView>)page.getList();
        for(JiazhengfuwuView c:list){
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
        JiazhengfuwuEntity jiazhengfuwu = jiazhengfuwuService.selectById(id);
        if(jiazhengfuwu !=null){
            //entity转view
            JiazhengfuwuView view = new JiazhengfuwuView();
            BeanUtils.copyProperties( jiazhengfuwu , view );//把实体数据重构到view中

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
    public R save(@RequestBody JiazhengfuwuEntity jiazhengfuwu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiazhengfuwu:{}",this.getClass().getName(),jiazhengfuwu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JiazhengfuwuEntity> queryWrapper = new EntityWrapper<JiazhengfuwuEntity>()
            .eq("jiazhengfuwu_name", jiazhengfuwu.getJiazhengfuwuName())
            .eq("jiazhengfuwu_types", jiazhengfuwu.getJiazhengfuwuTypes())
            .eq("jiazhengfuwu_clicknum", jiazhengfuwu.getJiazhengfuwuClicknum())
            .eq("shangxia_types", jiazhengfuwu.getShangxiaTypes())
            .eq("jiazhengfuwu_delete", jiazhengfuwu.getJiazhengfuwuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiazhengfuwuEntity jiazhengfuwuEntity = jiazhengfuwuService.selectOne(queryWrapper);
        if(jiazhengfuwuEntity==null){
            jiazhengfuwu.setJiazhengfuwuClicknum(1);
            jiazhengfuwu.setShangxiaTypes(1);
            jiazhengfuwu.setJiazhengfuwuDelete(1);
            jiazhengfuwu.setCreateTime(new Date());
            jiazhengfuwuService.insert(jiazhengfuwu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiazhengfuwuEntity jiazhengfuwu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiazhengfuwu:{}",this.getClass().getName(),jiazhengfuwu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<JiazhengfuwuEntity> queryWrapper = new EntityWrapper<JiazhengfuwuEntity>()
            .notIn("id",jiazhengfuwu.getId())
            .andNew()
            .eq("jiazhengfuwu_name", jiazhengfuwu.getJiazhengfuwuName())
            .eq("jiazhengfuwu_types", jiazhengfuwu.getJiazhengfuwuTypes())
            .eq("jiazhengfuwu_clicknum", jiazhengfuwu.getJiazhengfuwuClicknum())
            .eq("shangxia_types", jiazhengfuwu.getShangxiaTypes())
            .eq("jiazhengfuwu_delete", jiazhengfuwu.getJiazhengfuwuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiazhengfuwuEntity jiazhengfuwuEntity = jiazhengfuwuService.selectOne(queryWrapper);
        if("".equals(jiazhengfuwu.getJiazhengfuwuPhoto()) || "null".equals(jiazhengfuwu.getJiazhengfuwuPhoto())){
                jiazhengfuwu.setJiazhengfuwuPhoto(null);
        }
        if(jiazhengfuwuEntity==null){
            jiazhengfuwuService.updateById(jiazhengfuwu);//根据id更新
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
        ArrayList<JiazhengfuwuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            JiazhengfuwuEntity jiazhengfuwuEntity = new JiazhengfuwuEntity();
            jiazhengfuwuEntity.setId(id);
            jiazhengfuwuEntity.setJiazhengfuwuDelete(2);
            list.add(jiazhengfuwuEntity);
        }
        if(list != null && list.size() >0){
            jiazhengfuwuService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JiazhengfuwuEntity> jiazhengfuwuList = new ArrayList<>();//上传的东西
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
                            JiazhengfuwuEntity jiazhengfuwuEntity = new JiazhengfuwuEntity();
//                            jiazhengfuwuEntity.setJiazhengfuwuName(data.get(0));                    //服务名称 要改的
//                            jiazhengfuwuEntity.setJiazhengfuwuPhoto("");//照片
//                            jiazhengfuwuEntity.setJiazhengfuwuTypes(Integer.valueOf(data.get(0)));   //家政服务类型 要改的
//                            jiazhengfuwuEntity.setJiazhengfuwuOldMoney(data.get(0));                    //服务原价/小时 要改的
//                            jiazhengfuwuEntity.setJiazhengfuwuNewMoney(data.get(0));                    //现价/小时 要改的
//                            jiazhengfuwuEntity.setJiazhengfuwuClicknum(Integer.valueOf(data.get(0)));   //点击次数 要改的
//                            jiazhengfuwuEntity.setJiazhengfuwuContent("");//照片
//                            jiazhengfuwuEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            jiazhengfuwuEntity.setJiazhengfuwuDelete(1);//逻辑删除字段
//                            jiazhengfuwuEntity.setCreateTime(date);//时间
                            jiazhengfuwuList.add(jiazhengfuwuEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jiazhengfuwuService.insertBatch(jiazhengfuwuList);
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
        PageUtils page = jiazhengfuwuService.queryPage(params);

        //字典表数据转换
        List<JiazhengfuwuView> list =(List<JiazhengfuwuView>)page.getList();
        for(JiazhengfuwuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiazhengfuwuEntity jiazhengfuwu = jiazhengfuwuService.selectById(id);
            if(jiazhengfuwu !=null){

                //点击数量加1
                jiazhengfuwu.setJiazhengfuwuClicknum(jiazhengfuwu.getJiazhengfuwuClicknum()+1);
                jiazhengfuwuService.updateById(jiazhengfuwu);

                //entity转view
                JiazhengfuwuView view = new JiazhengfuwuView();
                BeanUtils.copyProperties( jiazhengfuwu , view );//把实体数据重构到view中

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
    public R add(@RequestBody JiazhengfuwuEntity jiazhengfuwu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiazhengfuwu:{}",this.getClass().getName(),jiazhengfuwu.toString());
        Wrapper<JiazhengfuwuEntity> queryWrapper = new EntityWrapper<JiazhengfuwuEntity>()
            .eq("jiazhengfuwu_name", jiazhengfuwu.getJiazhengfuwuName())
            .eq("jiazhengfuwu_types", jiazhengfuwu.getJiazhengfuwuTypes())
            .eq("jiazhengfuwu_clicknum", jiazhengfuwu.getJiazhengfuwuClicknum())
            .eq("shangxia_types", jiazhengfuwu.getShangxiaTypes())
            .eq("jiazhengfuwu_delete", jiazhengfuwu.getJiazhengfuwuDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiazhengfuwuEntity jiazhengfuwuEntity = jiazhengfuwuService.selectOne(queryWrapper);
        if(jiazhengfuwuEntity==null){
            jiazhengfuwu.setJiazhengfuwuDelete(1);
            jiazhengfuwu.setCreateTime(new Date());
        jiazhengfuwuService.insert(jiazhengfuwu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}

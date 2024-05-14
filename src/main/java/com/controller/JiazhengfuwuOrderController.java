
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
 * 家政服务订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiazhengfuwuOrder")
public class JiazhengfuwuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(JiazhengfuwuOrderController.class);

    @Autowired
    private JiazhengfuwuOrderService jiazhengfuwuOrderService;


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
@Autowired
private JiazhengfuwuCommentbackService jiazhengfuwuCommentbackService;



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
        PageUtils page = jiazhengfuwuOrderService.queryPage(params);

        //字典表数据转换
        List<JiazhengfuwuOrderView> list =(List<JiazhengfuwuOrderView>)page.getList();
        for(JiazhengfuwuOrderView c:list){
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
        JiazhengfuwuOrderEntity jiazhengfuwuOrder = jiazhengfuwuOrderService.selectById(id);
        if(jiazhengfuwuOrder !=null){
            //entity转view
            JiazhengfuwuOrderView view = new JiazhengfuwuOrderView();
            BeanUtils.copyProperties( jiazhengfuwuOrder , view );//把实体数据重构到view中

                //级联表
                JiazhengfuwuEntity jiazhengfuwu = jiazhengfuwuService.selectById(jiazhengfuwuOrder.getJiazhengfuwuId());
                if(jiazhengfuwu != null){
                    BeanUtils.copyProperties( jiazhengfuwu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiazhengfuwuId(jiazhengfuwu.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(jiazhengfuwuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                YuangongEntity yuangong = yuangongService.selectById(jiazhengfuwuOrder.getYuangongId());
                if(yuangong != null){
                    BeanUtils.copyProperties( yuangong , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYuangongId(yuangong.getId());
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
    public R save(@RequestBody JiazhengfuwuOrderEntity jiazhengfuwuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiazhengfuwuOrder:{}",this.getClass().getName(),jiazhengfuwuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jiazhengfuwuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("员工".equals(role))
            jiazhengfuwuOrder.setYuangongId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        jiazhengfuwuOrder.setInsertTime(new Date());
        jiazhengfuwuOrder.setCreateTime(new Date());
        jiazhengfuwuOrderService.insert(jiazhengfuwuOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiazhengfuwuOrderEntity jiazhengfuwuOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiazhengfuwuOrder:{}",this.getClass().getName(),jiazhengfuwuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jiazhengfuwuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("员工".equals(role))
//            jiazhengfuwuOrder.setYuangongId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JiazhengfuwuOrderEntity> queryWrapper = new EntityWrapper<JiazhengfuwuOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiazhengfuwuOrderEntity jiazhengfuwuOrderEntity = jiazhengfuwuOrderService.selectOne(queryWrapper);
        if(jiazhengfuwuOrderEntity==null){
            jiazhengfuwuOrderService.updateById(jiazhengfuwuOrder);//根据id更新
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
        jiazhengfuwuOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JiazhengfuwuOrderEntity> jiazhengfuwuOrderList = new ArrayList<>();//上传的东西
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
                            JiazhengfuwuOrderEntity jiazhengfuwuOrderEntity = new JiazhengfuwuOrderEntity();
//                            jiazhengfuwuOrderEntity.setJiazhengfuwuOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            jiazhengfuwuOrderEntity.setJiazhengfuwuLianxirenName(data.get(0));                    //联系人 要改的
//                            jiazhengfuwuOrderEntity.setJiazhengfuwuLianxirenPhone(data.get(0));                    //联系人电话 要改的
//                            jiazhengfuwuOrderEntity.setJiazhengfuwuLianxirenAddress(data.get(0));                    //服务地址 要改的
//                            jiazhengfuwuOrderEntity.setJiazhengfuwuId(Integer.valueOf(data.get(0)));   //家政服务 要改的
//                            jiazhengfuwuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jiazhengfuwuOrderEntity.setYuangongId(Integer.valueOf(data.get(0)));   //员工 要改的
//                            jiazhengfuwuOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //小时 要改的
//                            jiazhengfuwuOrderEntity.setJiazhengfuwuOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            jiazhengfuwuOrderEntity.setJiazhengfuwuOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            jiazhengfuwuOrderEntity.setJiazhengfuwuOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            jiazhengfuwuOrderEntity.setYuyueTime(new Date(data.get(0)));          //预约时间 要改的
//                            jiazhengfuwuOrderEntity.setInsertTime(date);//时间
//                            jiazhengfuwuOrderEntity.setCreateTime(date);//时间
                            jiazhengfuwuOrderList.add(jiazhengfuwuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("jiazhengfuwuOrderUuidNumber")){
                                    List<String> jiazhengfuwuOrderUuidNumber = seachFields.get("jiazhengfuwuOrderUuidNumber");
                                    jiazhengfuwuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jiazhengfuwuOrderUuidNumber = new ArrayList<>();
                                    jiazhengfuwuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jiazhengfuwuOrderUuidNumber",jiazhengfuwuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<JiazhengfuwuOrderEntity> jiazhengfuwuOrderEntities_jiazhengfuwuOrderUuidNumber = jiazhengfuwuOrderService.selectList(new EntityWrapper<JiazhengfuwuOrderEntity>().in("jiazhengfuwu_order_uuid_number", seachFields.get("jiazhengfuwuOrderUuidNumber")));
                        if(jiazhengfuwuOrderEntities_jiazhengfuwuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiazhengfuwuOrderEntity s:jiazhengfuwuOrderEntities_jiazhengfuwuOrderUuidNumber){
                                repeatFields.add(s.getJiazhengfuwuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiazhengfuwuOrderService.insertBatch(jiazhengfuwuOrderList);
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
        PageUtils page = jiazhengfuwuOrderService.queryPage(params);

        //字典表数据转换
        List<JiazhengfuwuOrderView> list =(List<JiazhengfuwuOrderView>)page.getList();
        for(JiazhengfuwuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiazhengfuwuOrderEntity jiazhengfuwuOrder = jiazhengfuwuOrderService.selectById(id);
            if(jiazhengfuwuOrder !=null){


                //entity转view
                JiazhengfuwuOrderView view = new JiazhengfuwuOrderView();
                BeanUtils.copyProperties( jiazhengfuwuOrder , view );//把实体数据重构到view中

                //级联表
                    JiazhengfuwuEntity jiazhengfuwu = jiazhengfuwuService.selectById(jiazhengfuwuOrder.getJiazhengfuwuId());
                if(jiazhengfuwu != null){
                    BeanUtils.copyProperties( jiazhengfuwu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiazhengfuwuId(jiazhengfuwu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jiazhengfuwuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    YuangongEntity yuangong = yuangongService.selectById(jiazhengfuwuOrder.getYuangongId());
                if(yuangong != null){
                    BeanUtils.copyProperties( yuangong , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYuangongId(yuangong.getId());
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
    public R add(@RequestBody JiazhengfuwuOrderEntity jiazhengfuwuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiazhengfuwuOrder:{}",this.getClass().getName(),jiazhengfuwuOrder.toString());
            JiazhengfuwuEntity jiazhengfuwuEntity = jiazhengfuwuService.selectById(jiazhengfuwuOrder.getJiazhengfuwuId());
            if(jiazhengfuwuEntity == null){
                return R.error(511,"查不到该家政服务");
            }else if(jiazhengfuwuOrder.getJiazhengfuwuLianxirenName() == null){
                return R.error("联系人不能为空");
            }else if(jiazhengfuwuOrder.getJiazhengfuwuLianxirenPhone() == null){
                return R.error("联系人手机号不能为空");
            }else if(jiazhengfuwuOrder.getJiazhengfuwuLianxirenAddress() == null){
                return R.error("服务地址不能为空");
            }else if(jiazhengfuwuOrder.getYuyueTime() == null){
                return R.error("预约时间不能为空");
            }
            else if(jiazhengfuwuEntity.getJiazhengfuwuNewMoney() == null){
                return R.error(511,"家政服务价格不能为空");
            }

        double suoxu = jiazhengfuwuOrder.getBuyNumber() * jiazhengfuwuEntity.getJiazhengfuwuNewMoney();

        YonghuEntity yonghuEntity = yonghuService.selectById(jiazhengfuwuOrder.getYonghuId());
        double balance = yonghuEntity.getNewMoney() - suoxu;

        if(balance <0)
            return R.error("您的余额不够预约此次家政服务");

        yonghuEntity.setNewMoney(balance);

        yonghuService.updateById(yonghuEntity);


        //计算所获得积分
            jiazhengfuwuOrder.setJiazhengfuwuOrderTypes(3); //设置订单状态为已支付
            jiazhengfuwuOrder.setJiazhengfuwuOrderTruePrice(suoxu); //设置实付价格
            jiazhengfuwuOrder.setJiazhengfuwuOrderUuidNumber(String.valueOf(new Date().getTime()));
            jiazhengfuwuOrder.setJiazhengfuwuOrderPaymentTypes(1);
            jiazhengfuwuOrder.setInsertTime(new Date());
            jiazhengfuwuOrder.setCreateTime(new Date());
            jiazhengfuwuOrderService.insert(jiazhengfuwuOrder);//新增订单
            return R.ok();
    }











    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            JiazhengfuwuOrderEntity jiazhengfuwuOrder = jiazhengfuwuOrderService.selectById(id);
            Integer buyNumber = jiazhengfuwuOrder.getBuyNumber();
            Integer jiazhengfuwuOrderPaymentTypes = jiazhengfuwuOrder.getJiazhengfuwuOrderPaymentTypes();
            Integer jiazhengfuwuId = jiazhengfuwuOrder.getJiazhengfuwuId();
            if(jiazhengfuwuId == null)
                return R.error(511,"查不到该家政服务");
            JiazhengfuwuEntity jiazhengfuwuEntity = jiazhengfuwuService.selectById(jiazhengfuwuId);
            if(jiazhengfuwuEntity == null)
                return R.error(511,"查不到该家政服务");
            Double jiazhengfuwuNewMoney = jiazhengfuwuEntity.getJiazhengfuwuNewMoney();
            if(jiazhengfuwuNewMoney == null)
                return R.error(511,"家政服务价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");



            //判断是什么支付方式 1代表余额 2代表积分
            if(jiazhengfuwuOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = jiazhengfuwuEntity.getJiazhengfuwuNewMoney() * buyNumber;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney()+money);
            }


            jiazhengfuwuOrder.setJiazhengfuwuOrderTypes(2);//设置订单状态为退款
            jiazhengfuwuOrderService.updateById(jiazhengfuwuOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            jiazhengfuwuService.updateById(jiazhengfuwuEntity);//更新订单中家政服务的信息
            return R.ok();
    }


    /**
     * 安排
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        JiazhengfuwuOrderEntity  jiazhengfuwuOrderEntity = new  JiazhengfuwuOrderEntity();;
        jiazhengfuwuOrderEntity.setId(id);
        jiazhengfuwuOrderEntity.setJiazhengfuwuOrderTypes(4);
        boolean b =  jiazhengfuwuOrderService.updateById( jiazhengfuwuOrderEntity);
        if(!b){
            return R.error("安排出错");
        }
        return R.ok();
    }









    /**
     * 服务
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        JiazhengfuwuOrderEntity  jiazhengfuwuOrderEntity = new  JiazhengfuwuOrderEntity();
        jiazhengfuwuOrderEntity.setId(id);
        jiazhengfuwuOrderEntity.setJiazhengfuwuOrderTypes(5);
        boolean b =  jiazhengfuwuOrderService.updateById( jiazhengfuwuOrderEntity);
        if(!b){
            return R.error("服务出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer jiazhengfuwuCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            JiazhengfuwuOrderEntity jiazhengfuwuOrder = jiazhengfuwuOrderService.selectById(id);
        if(jiazhengfuwuOrder == null)
            return R.error(511,"查不到该订单");
        if(jiazhengfuwuOrder.getJiazhengfuwuOrderTypes() != 5)
            return R.error(511,"您不能评价");
        Integer jiazhengfuwuId = jiazhengfuwuOrder.getJiazhengfuwuId();
        if(jiazhengfuwuId == null)
            return R.error(511,"查不到该家政服务");

        JiazhengfuwuCommentbackEntity jiazhengfuwuCommentbackEntity = new JiazhengfuwuCommentbackEntity();
            jiazhengfuwuCommentbackEntity.setId(id);
            jiazhengfuwuCommentbackEntity.setJiazhengfuwuId(jiazhengfuwuId);
            jiazhengfuwuCommentbackEntity.setYonghuId(jiazhengfuwuOrder.getYonghuId());
            jiazhengfuwuCommentbackEntity.setJiazhengfuwuCommentbackText(commentbackText);
            jiazhengfuwuCommentbackEntity.setInsertTime(new Date());
            jiazhengfuwuCommentbackEntity.setReplyText(null);
            jiazhengfuwuCommentbackEntity.setUpdateTime(null);
            jiazhengfuwuCommentbackEntity.setCreateTime(new Date());
            jiazhengfuwuCommentbackService.insert(jiazhengfuwuCommentbackEntity);

            jiazhengfuwuOrder.setJiazhengfuwuOrderTypes(1);//设置订单状态为已评价
            jiazhengfuwuOrderService.updateById(jiazhengfuwuOrder);//根据id更新
            return R.ok();
    }







}

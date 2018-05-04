package com.richgo.thrsys.product;

import com.richgo.thrsys.entity.product.*;
import com.richgo.thrsys.service.product.ThrsysProductInfoService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxinyu on 2017/11/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThrsysProductInfoServiceTest {
    @Autowired
    private ThrsysProductInfoService thrsysProductInfoService;
    // todo 查询产品列表信息
    @Test
    public void test_getProductList() throws Exception{
        //查询测试数据： select C_BROKERACCOUNT from TFUNDBROKER  H000076
        Map<String, Object> resultMap = thrsysProductInfoService.getProductList("0",null,null,null,null,
        null,1,10,"H000076");
        System.out.println(resultMap.size());
        //输出结果：total  23 list 的size 10

    }

    // todo 获取产品详细信息
    @Test
    public void  test_getProductInformation(){
        //查询测试数据：select L_PROSERIALNO from tsaleproject where C_DEPTNODECODE like '00010001%' ;
        ThrsysProductInformationVO thrsysProductInformationVO = thrsysProductInfoService.getProductInformation(6002);
        System.out.println(thrsysProductInformationVO.getFundName());
        //输出结果：中信资本-暴风科技3号并购基金
    }
    // todo 根据产品代码获取净值走势
    @Test
    public void test_getProductNetTrend(){
        // 查询测试数据：select c_fundcode,d_cdate,f_netvalue from tfundday  取 c_fundcode
        List<ThrsysProductNetValue> resultList = thrsysProductInfoService.getProductNetTrend("HT3697",4);
        System.out.println(resultList.size());
        // 输出结果：151  成立至今
    }

    // todo 根据产品代码获取收益级别
    @Test
    public void test_getProductProfitInfo(){
        // 查询测试数据：select  c_fundcode,c_profitclass,f_profit,f_amountmin,f_amountMax from ttrustfundprofit;  取 c_fundcode 字段
        List<ThrsysProductProfitInfo> resultList = thrsysProductInfoService.getProductProfitInfo("HT1392");
        System.out.println(resultList);
        //输出结果： 2
    }

    // todo  根据产品代码获取发行方案
    @Test
    public void test_getProductIssuingScheme(){
        // 查询测试数据：select L_PROSERIALNO from tsaleproject where C_DEPTNODECODE like '00010001%' ;
        ThrsysProductIssuingScheme thrsysProductIssuingScheme = thrsysProductInfoService.getProductIssuingScheme(6002);
        System.out.println(thrsysProductIssuingScheme.getCollectContralMode());
        //输出结果：1

    }

    // todo 根据产品名称和用户id搜索产品
    @Test
    public void test_searchProductList(){
        // 查询测试数据：    中信资本-暴风科技3号并购基金  H003512
      /*  select *
                from tsaleproject a
        inner
        join tsaleprofundrelation b
        on a.l_proserialno = b.l_proserialno
        inner
        join tfundinfo c
        on b.c_fundcode = c.c_fundcode
        where a.C_DEPTNODECODE like '00010001%'*/
        Map<String, Object> resultList  =  thrsysProductInfoService.searchProductList("中信资本","H003512",1,10);
      //  Map<String, Object> resultList  =  thrsysProductInfoService.searchProductList("","H003512",1,10);
        System.out.println(resultList.size());
        //输出结果：total 12 list的size 10
    }

    // todo 根据项目代码查询产品募集账户信息
    @Test
    public void  test_getProductCollectionAccount(){
        //查询测试数据：select L_PROSERIALNO from tsaleproject where C_DEPTNODECODE like '00010001%' and C_RAISEBANKDESC is not null;
        ThrsysProductCollectionAccount thrsysProductCollectionAccount = thrsysProductInfoService.getProductCollectionAccount(5828);
        System.out.println(thrsysProductCollectionAccount.getRaiseName());
        //输出结果：属性都为null的实体对象 ，与逻辑符合
    }

    // todo 根据项目代码查询合同/推介附件
    @Test
    public void test_getProductContractMaterial(){
        //查询测试数据：select L_PROSERIALNO from tsaleproject where C_DEPTNODECODE like '00010001%' ;
        Map<String,Object>  resultMap  =  thrsysProductInfoService.getProductContractMaterial(6002,1,10);
        System.out.println(resultMap.size());
        //输出结果：total 5 list 的 size 5
    }

    // todo 下载产品合同材料附件
    @Test
    public void test_downloadProductContractMaterial()throws Exception{
        //查询测试数据：
        Map<String,Object> resultMap = thrsysProductInfoService.downloadProductContractMaterial(2794);
        System.out.println(resultMap.size());
        //输出结果："fileName" -> "201511-财通基金定增专享-徐峥.gif"
    }
    // todo 根据代码和名称查询产品列表
    @Test
    public void  test_selectProductList(){
        //查询测试数据：
        /*select c.c_fundcode,
                a.l_proserialno,
                a.c_name,
                a.c_yield,
                a.c_deadline,
                a.f_perminbalance,
                a.f_scale,
                (select sum(F_BALANCE) from treserve e where e.L_PROSERIALNO=a.L_PROSERIALNO AND  e.C_STATUS  IN ('4','7','d'))  f_factcollect,
                decode(a.c_status, '1', '待发行', '2', '发行中', '3',
                        '存续', '存续') c_fundstatus,
                a.c_raiseBankDesc,
                a.c_incomedistribution idb,
                a.d_expirationdate
        from tsaleproject a
                inner
        join tsaleprofundrelation b
        on a.l_proserialno = b.l_proserialno
        inner
        join tfundinfo c
        on b.c_fundcode = c.c_fundcode
        where a.C_DEPTNODECODE like '00010001%'*/
        Map<String,Object> resultMap = thrsysProductInfoService.selectProductList("0000FC","中信资本",1,10);
        System.out.println(resultMap.size());
        //输出结果：total：1 list.size() 1
    }

    // todo  根据理顾编号，产品名称模糊搜索发行中的产品
    @Test
    public void test_openProductList(){
        //查询测试数据："民生","H003512"
        Map<String, Object> result = thrsysProductInfoService.openProductList("民生","H003512",1,10);
        System.out.println(result.size());
        //输出结果：total :2 list 的size 2
    }
   // todo  根据预约金额与产品代码查询对应受益级别
    @Test
    public void test_getReserveProfitclass(){
      // 查询测试数据：select * from ttrustfundprofit 取c_fundcode
        String result  =  thrsysProductInfoService.getReserveProfitclass("HT1392",50000);
        System.out.println(result);
        //输出测试结果："0"
    }

    // todo 根据产品代码与受益级别查询对应记录条数
    @Test
    public void test_countReserveProfit(){
        //查询测试数据：select * from ttrustfundprofit; c_fundcode c_profit
        int result  =  thrsysProductInfoService.countReserveProfit("HT1392","1");
        System.out.println(result);
        //输出结果：1
    }
    // todo 获取总募集金额
    @Test
    public void test_getFactCollect(){
        //查询测试数据：select e.L_PROSERIALNO from treserve e where e.c_status  in ('4','7','d')
        Double result = thrsysProductInfoService.getFactCollect(5921L);
        System.out.println(result);
        //输出结果：2.442E8

    }
   // todo  查询合同/推介附件
    @Test
    public void test_getProductContractMaterial_1(){
        //查询测试数据：select L_PROSERIALNO from tsaleproject where C_DEPTNODECODE like '00010001%' ;
      List<ThrsysProductAccessory>  resultList = thrsysProductInfoService.getProductContractMaterial(6002);
        System.out.println(resultList.size());
        //输出结果：5
    }
}

package com.richgo.thrsys.customer;

import com.richgo.thrsys.entity.customer.AssetStockBO;
import com.richgo.thrsys.entity.customer.ShareOrderBO;
import com.richgo.thrsys.entity.customer.TsharecurrentsBO;
import com.richgo.thrsys.service.customer.ThrsysAssetStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuxinyu on 2017/11/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThrsysAssetStockServiceTest {
    @Autowired
    private ThrsysAssetStockService thrsysAssetStockService;
    // todo
    @Test
    public void test_getCustNoByAssetChange() throws Exception{
        //查询测试数据：select DISTINCT  t.C_CUSTNO,t.D_LASTMODIFY from  tstaticshares t  2015-06-02
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2015-06-02");
        Date date1 = sdf1.parse("2015-04-06 13:12:11");
        List<String>  result = thrsysAssetStockService.getCustNoByAssetChange(date);
        List<String>  result1 = thrsysAssetStockService.getCustNoByAssetChange(date);
        System.out.println(result.size()+"--------"+result1.size());
   //输出结果：1568--------1568
    }

    // todo
    @Test
    public void test_getOldDataCustNoList(){
        //无参数，直接调用。
        List<String> result = thrsysAssetStockService.getOldDataCustNoList();
        System.out.println(result.size());
        //输出结果：67268
    }
    // todo
    @Test
    public void test_getOldDataAssetStock(){
        // 查询测试数据：select  t.C_CUSTNO  from  TSHARECURRENTS t
        List<TsharecurrentsBO> result =  thrsysAssetStockService.getOldDataAssetStock("000007202710");
        System.out.println(result.size());
        //输出结果：1
    }
    // todo  获取指定客户资产份额流水
    @Test
    public void test_getAssetStockByCustNo(){
        // 查询测试数据:select t.C_CUSTNO  from  tsharecurrents t left join   tfundinfo f on t.c_fundcode=f.c_fundcode
        Map<String, Object> result  =  thrsysAssetStockService.getAssetStockByCustNo("000007202710",1,10);
        System.out.println(result.size());
        //输出结果：total ：2 list 的 size ：2
    }

    // todo 获取指定时间客户资产份额流水记录
    @Test
    public void  test_getAssetStockByDateList() throws Exception{
        //查询测试数据: select  t.d_cdate from  tsharecurrents t
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2014-07-12 13:12:11");
        List<ShareOrderBO> result = thrsysAssetStockService.getAssetStockByDateList(date,1,10);
        System.out.println(result.size());
        //输出结果：10
    }
    // todo 获取指定时间客户资产份额流水记录总数
    @Test
    public  void  test_getAssetStockCountByDate()throws Exception{
        //查询测试数据：select  t.d_cdate from  tsharecurrents t;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2014-07-12 13:12:11");
        Integer result = thrsysAssetStockService.getAssetStockCountByDate(date);
        System.out.println(result);
        //输出结果：2110
    }

    // todo 根据收益类别查询所有份额流水的客户号
    @Test
    public void  test_getAssetStockByTypeList() throws Exception{
        /*查询测试数据：select DISTINCT t.C_CUSTNO,j.C_INCOMEDISTRIBUTION,t.D_SHAREVALIDDATE from  TSHARECURRENTS t
                          left join  tsaleprofundrelation s on s.c_fundcode=t.c_fundcode
                          left join  tsaleproject   j on j.l_proserialno = s.l_proserialno
                        where  t.F_LASTSHARES != 0
                        and j.c_name not LIKE '%稳金%' and  j.c_name not LIKE '%稳裕%'*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2015-06-10 00:00:00");
        List<String> result = thrsysAssetStockService.getAssetStockByTypeList("2",date);
        System.out.println(result.size());
        //输出结果：35243
    }


    @Test
    public  void test_getAllAssetCount(){
        Integer a = thrsysAssetStockService.getAllAssetCount();
        System.out.println(a);
    }

    @Test
    public  void test_getAllAssetByPage(){
        List<AssetStockBO> list = thrsysAssetStockService.getAllAssetByPage(1,5);
        System.out.println(list.size());
    }
}

package com.richgo.thrsys.emp;

import com.richgo.thrsys.entity.ThrsysEmp;
import com.richgo.thrsys.entity.ThrsysIncrement;
import com.richgo.thrsys.service.emp.ThrsysIncrementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxinyu on 2017/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThrsysIncrementServiceTest {
    @Autowired
    private ThrsysIncrementService thrsysIncrementService;
    // todo 根据时间戳查询增量信息
    @Test
    public void test_getIncrement(){
        /*查询测试数据：SELECT
        c_serialno, c_com_emp_no, c_oldduty, c_newduty, c_olddeptcode, c_newdeptcode,
                c_type, d_dealtime,ts,dr,validtime,alterman,billno
        FROM  VOPERATEDES    取 ts 字段*/
        List<ThrsysIncrement>  resultList = thrsysIncrementService.getIncrement("2016-11-03 17:11:59");
        System.out.println(resultList.size());
        //输出结果：48369
    }
    //todo 根据员工编号查询员工信息
    @Test
    public void test_getPsndocInfo(){
      // 查询测试数据：select code from htcf_org_psndoc_view WHERE   org_code='03' and name is not null  取任意code
        ThrsysEmp thrsysEmp = thrsysIncrementService.getPsndocInfo("H005189");
        System.out.println(thrsysEmp.getName()+"---"+thrsysEmp.getAge());
        //输出结果：张学梁---36
    }

    //todo 获取所有用户code
    @Test
    public void test_getPsndocCodeList(){
        //没有参数，直接调用
        List<String> resultList = thrsysIncrementService.getPsndocCodeList();
        System.out.println(resultList.size());
        //输出结果:5972
    }
    //todo 获取所有部门code
    @Test
    public void test_getOrgsList(){
        //没有参数，直接调用
        List<String> resultList = thrsysIncrementService.getOrgsList();
        System.out.println(resultList.size());
        //输出结果：2604
    }
    //todo 根据员工编号查询员工信息（所有）
    @Test
    public  void  test_getPsndocAllInfo(){
        //查询测试数据：select code from htcf_org_psndoc_view WHERE  name is not null  取任意code
        ThrsysEmp thrsysEmp = thrsysIncrementService.getPsndocAllInfo("H014207");
        System.out.println(thrsysEmp.getName()+"---"+thrsysEmp.getAge());
        //输出结果：李红丽---31
    }

    //todo 查询所有员工的code
    @Test
    public void test_getPsndocAllCodeList(){
        //没有参数，直接调用
        List<String> resultList = thrsysIncrementService.getPsndocAllCodeList();
        System.out.println(resultList.size());
        //输出结果：16214
    }
    //todo 根据code查询理顾头像信息并上传到文件服务器
    @Test
    public void test_uploadEmpPhoto(){
      // 查询测试数据：select code,photo from htcf_org_psndoc_view where name is not null and photo is not null; 取任意code
        Map<String,Object> resultMap = thrsysIncrementService.uploadEmpPhoto("H007868");
        System.out.println(resultMap);
        //输出结果：0000  成功
    }
   // todo 根据文件服务器照片地址　删除照片信息
   @Test
    public void test_deletePhotoFile(){
    // 查询测试数据：test_uploadEmpPhoto() 方法的测试数据
       Map<String,Object>  resultMap = thrsysIncrementService.deletePhotoFile("group2/M00/00/6C/rBCjRFoNXAOAeQbdACbiOWaRowM016.png");
       System.out.println(resultMap);
       //输出结果：status :0000 message:图片文件删除成功
   }

}

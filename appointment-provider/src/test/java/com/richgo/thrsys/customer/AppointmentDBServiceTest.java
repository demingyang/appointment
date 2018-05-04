package com.richgo.thrsys.customer;

import com.richgo.thrsys.entity.customer.VideoRequest;
import com.richgo.thrsys.service.customer.AppointmentDBService;
import com.richgo.thrsys.service.reserve.ThrsysReserveService;
import com.richgo.thrsys.service.reserve.ThrsysSaleProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhouxj on 2017/11/16.
 * 视频见证预约多金数据存储服务
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class AppointmentDBServiceTest {
    @Autowired
    private AppointmentDBService appointmentDBService;

    /**
     * 获取视频预约信息
     *    70000026
     * @throws Exception
     */
  @Test
    public void testQueryAptInfo() throws Exception {
      VideoRequest  videoInfo=appointmentDBService.queryAptInfo("70000026");
      System.out.println(videoInfo.getSerialNo()+" ,"+videoInfo.getOrderTypeCode()+","+videoInfo.getOrderCode()+","+videoInfo.getStatus());
    }
    /**
     * 获取视频预约信息
     *
     * @throws Exception
     */
    @Test
    public void testQueryAptInfo2() throws Exception {
        VideoRequest  videoInfo=appointmentDBService.queryAptInfo("1","201705170000257236","");
        System.out.println(videoInfo.getSerialNo()+" ,"+videoInfo.getOrderTypeCode()+","+videoInfo.getOrderCode()+","+videoInfo.getStatus());
    }

    /**
     * 保存视频预约信息详情
     *
     * @throws Exception
     */
    @Test
    public void testSaveAptInfo() throws Exception {
        VideoRequest  videoinfo0=new VideoRequest();
        videoinfo0.setEmpNo("000000");
        videoinfo0.setOrderCode("201705170000288236");
        videoinfo0.setOrderTypeCode("1");
        Integer  videoInfo=appointmentDBService.saveAptInfo(videoinfo0);
        System.out.println("L_SERIALNO : "+videoInfo);
    }
    /**
     *  保存预约信息详情
     *  --6  000000  optType:3   8：08        9：09   memo:发工资啦
     *  在数据库中查找字段
     *  select *  from TVIDEOREQUEST_DETAIL t where t.c_memo  like '%发工资啦%';
     * @throws Exception
     */
    @Test
    public void testsaveAptInfoDetail() throws Exception {
        String memo="发工资啦！";
        VideoRequest  videoinfo0=new VideoRequest();
        videoinfo0.setSerialNo(6);
        videoinfo0.setEmpNo("000000");
        videoinfo0.setStartTime("8:08");
        videoinfo0.setEndTime("9:09");
        Integer  videoInfo=appointmentDBService.saveAptInfoDetail(3,memo,videoinfo0);
        System.out.println("插入TVIDEOREQUEST_DETAIL表中行数 "+videoInfo);
    }

    /**
     *  取消预约
     *select *  from TVIDEOREQUEST t where t.c_objno='201705170000288236';
     * @throws Exception
     */
    @Test
    public void testCancelAptInfo() throws Exception {
        Integer  videoInfo=appointmentDBService.cancelAptInfo("70000876");
        System.out.println("TVIDEOREQUEST表中行数 "+videoInfo);
    }

    /**
     *  删除预约
     *select *  from TVIDEOREQUEST t where t.c_objno='201705170000288236';
     * @throws Exception
     */
    @Test
    public void testRemoveAptInfo() throws Exception {
        Integer  videoInfo=appointmentDBService.removeAptInfo("70000884");
        System.out.println("TVIDEOREQUEST表中行数 "+videoInfo);
    }
}

package com.richgo.thrsys.post.job;

import com.richgo.thrsys.entity.ThrsysPostJob;
import com.richgo.thrsys.service.post.job.ThrsysPostJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuxinyu on 2017/11/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThrsysPostJobServiceTest {
    @Autowired
    private ThrsysPostJobService thrsysPostJobService;
    // todo 根据岗位编码查询岗位信息
    @Test
    public void test_selectByJobCode(){
        //查询测试数据：select postJob.JOBCODE  as jobCode from htcf_org_psnjob_view postJob 0306
        ThrsysPostJob thrsysPostJob = thrsysPostJobService.selectByJobCode("0306");
        System.out.println(thrsysPostJob.getJobName());
        //输出结果：表或视图不存在 ，未配置数据源
    }

}

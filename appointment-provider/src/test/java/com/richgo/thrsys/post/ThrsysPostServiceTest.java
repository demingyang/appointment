package com.richgo.thrsys.post;

import com.richgo.thrsys.entity.ThrsysPost;
import com.richgo.thrsys.service.post.ThrsysPostService;
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
public class ThrsysPostServiceTest {
    @Autowired
    private ThrsysPostService thrsysPostService;
    // todo 根据岗位编码查询岗位信息
    @Test
     public void test_selectByPostCode(){
        // 查询测试数据：select  post.POSTCODE as  postCode from htcf_org_post_view post  取 postcode 字段： YXFX-008
        ThrsysPost thrsysPost = thrsysPostService.selectByPostCode("P0000022");
        System.out.println(thrsysPost.getPostName());
        //输出结果 ：异常，方法未配置数据源，表和视图不存在
    }


}

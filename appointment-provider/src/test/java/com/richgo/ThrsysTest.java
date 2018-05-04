package com.richgo;

import com.richgo.thrsys.entity.fundcrm.RegistVO;
import com.richgo.thrsys.service.fundcrm.ThrsysFundcrmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by YangDeming on 2017/9/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class ThrsysTest {
    @Autowired
    private ThrsysFundcrmService thrsysFundcrmService;

    @Test
    public void testAddCustRegister() throws Exception {
        RegistVO registVO = new RegistVO();
        registVO.setCustno("");
        registVO.setSerialno("");
        registVO.setCusttype("1");
        registVO.setAreacode("Areacode");
        registVO.setOpentype("1");
        registVO.setCustname("葫芦娃15105#");
        registVO.setIdentitytype("0");
        registVO.setIdentityno("120226199811280995");
        registVO.setIdvaliddatebeg("1949/10/1");
        registVO.setIdnovaliddate("2018-06-09");
        registVO.setSex("1");
        registVO.setBirthday("19850127");
        registVO.setEducation("幼");
        registVO.setNationality("156");
        registVO.setFundbrokerID("H006069");
        registVO.setShowfundbrokerid("超级管理员");
        registVO.setVocation("1");
        registVO.setBeneficary("陆展元");
        registVO.setMobileno("15811331005");
        registVO.setPhone("Phone");
        registVO.setEmail("Email");
        registVO.setFaxno("Faxno");
        registVO.setZipcode("Zipcod");
        registVO.setAddress("Address");
        registVO.setCustscenephoto("14646dsfg65sd4gs6d5f4g");
        registVO.setAccessoryIds("44464646lp");
        registVO.setStatus("1");
        registVO.setRemark("可以通过");
        registVO.setResult("1");
        /*ThrsysUnregister unregister = new ThrsysUnregister();
        BeanUtil.copyBeanToBean(unregister,registVO);
        System.out.println(unregister.toString());*/

        Map<String, Object> stringObjectMap = thrsysFundcrmService.addCustRegister(registVO);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(stringObjectMap.get("status"));
        System.out.println(stringObjectMap.get("message"));
        System.out.println(stringObjectMap.get("custno"));
    }

    @Test
    public void testBindBankCard() throws Exception {

    }

    @Test
    public void testRealNameAuthentication() throws Exception {
        String custno = "200007349018";
        String custname = "zhouxueyan";
        String custtype = "1";
        String idtype = "";
        String idno = "";
        String mobileno = "13830000002";
        String operator = "H013782";
        Map<String, Object> stringObjectMap = thrsysFundcrmService.realNameAuthentication(custno, custname, custtype, idtype, idno, mobileno, operator);
        System.out.println(stringObjectMap.get("message"));
    }

    @Test
    public void testInsert() throws Exception {
        RegistVO registVO = new RegistVO();
//        registVO.setCustno("200007350432");
        registVO.setCusttype("0");
        registVO.setAreacode("Areacode");
        registVO.setOpentype("1");
        registVO.setCustname("葫芦娃5#");
        registVO.setIdentitytype("0");
        registVO.setIdentityno("120226199051289926");
        registVO.setIdvaliddatebeg("1949/10/1");
        registVO.setIdnovaliddate("2018-06-09");
        registVO.setSex("1");
        registVO.setBirthday("19850127");
        registVO.setEducation("幼");
        registVO.setNationality("156");
        registVO.setFundbrokerID("H006069");
        registVO.setShowfundbrokerid("超级管理员");
        registVO.setVocation("1");
        registVO.setBeneficary("陆展元");
        registVO.setMobileno("15811301420");
        registVO.setPhone("Phone");
        registVO.setEmail("Email");
        registVO.setFaxno("Faxno");
        registVO.setZipcode("Zipcod");
        registVO.setAddress("Address");
        registVO.setCustscenephoto("14646dsfg65sd4gs6d5f4g");
        registVO.setAccessoryIds("44464646lp");
        registVO.setStatus("1");
        registVO.setRemark("可以通过");
        registVO.setResult("1");
        registVO.setContactMobileno("15811301420");
        thrsysFundcrmService.insert(registVO);

    }

    @Test
    public void testKey() throws Exception {
        for (int i = 1; i < 1500; i++) {

            thrsysFundcrmService.getNextCustNo();
        }
        System.out.println(thrsysFundcrmService.getNextCustNo());


    }
}



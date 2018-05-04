package com.appointment.manager;

import com.richgo.http.HttpResultBean;
import com.richgo.http.HttpUtil;
import com.richgo.security.VideoInterviewUtil;
import com.richgo.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2017/6/6
 * Time: 19:27
 * User: Kayle 视频面签预约服务通讯管理
 */
@Component
public class CommunicationManagerImpl implements CommunicationManager {

    private static final String KEY = ConfigUtil.getValue("videoInterview.key");
    private static final String ORG_CODE = ConfigUtil.getValue("videoInterview.orgCode");
    private static final String URL = ConfigUtil.getValue("videoInterview.url");

    /* 日志对象 */
    private final static Logger log = LoggerFactory.getLogger(CommunicationManager.class);

    public String post(String api, String jsonParam) {

        log.info("调用视频面签接口{}({})", api, jsonParam);
        String param = "";
        try {
            param = VideoInterviewUtil.encrypt(jsonParam, KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> paraMap = new HashMap();
        paraMap.put("orgCode", ORG_CODE);
        paraMap.put("param", param);

        try {
            long startTime = System.currentTimeMillis();
            HttpResultBean result = HttpUtil.getHttpPost(URL + api, paraMap, "UTF-8");
            long endTime = System.currentTimeMillis();
            log.info("执行时间 : {}" ,endTime - startTime);

            int status = result.getHttpStatus();
            String resultContext = result.getResultContext();

            if (200 == status) {
                log.info("访问接口{}成功，返回结果{}", api, resultContext);
                return resultContext;
            } else {
                log.info("访问接口{}失败，状态码为{}", api, status);
                return null;
            }
        } catch (IOException e) {
            log.info("请求数据异常", e.getMessage(), e);
            e.printStackTrace();
            return null;
        }
    }

}

package com.appointment.service;

import com.appointment.entity.VideoRequest;

/**
 * Date: 2017/6/8
 * Time: 9:42
 * User: Kayle 视频见证预约多金数据存储服务
 */
public interface AppointmentDBService {

    /**
     * 获取视频预约信息
     * @param videoRequestId 视频预约主键ID
     * @return
     */
    VideoRequest queryAptInfo(String videoRequestId);

    /**
     * 获取视频预约信息
     * @param orderTypeCode
     * @param orderCode
     * @param empNo
     * @return
     */
    VideoRequest queryAptInfo(String orderTypeCode, String orderCode, String empNo);

    /**
     * 保存预约信息
     * @param videoRequest 视频面签请求对象
     * @return 1：操作成功，0：操作失败
     */
    Integer saveAptInfo(VideoRequest videoRequest);

    /**(
     * 保存预约信息详情
     * @param optType 操作类型
     * @param memo 备注
     * @param videoRequest 预约请求
     * @return
     */
    Integer saveAptInfoDetail(Integer optType, String memo, VideoRequest videoRequest);

    /**
     * 取消预约
     * @param videoRequestId 视频预约编号
     * @return 1：操作成功，0：操作失败
     */
    Integer cancelAptInfo(String videoRequestId);

    /**
     * 删除预约信息
     * @param videoRequestId 视频预约编号
     * @return 1：操作成功，0：操作失败
     */
    Integer removeAptInfo(String videoRequestId);
}

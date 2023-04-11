package cn.loveapp.doudianyun.service.service;

import cn.loveapp.doudianyun.service.api.request.DditoOrderSearchItemGetRequest;
import cn.loveapp.doudianyun.service.api.response.DditoOrderSearchItemGetResponse;

/**
 * @Author: zhongzijie
 * @Date: 2023/4/11 11:12
 * @Description:
 */
public interface TestService {

    /**
     * 根据nick获取订购记录
     *
     * @param request
     * @return
     */
    DditoOrderSearchItemGetResponse dditoOrderSearchItemGet(DditoOrderSearchItemGetRequest request);
}

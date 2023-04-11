package cn.loveapp.doudianyun.service.controller;

import cn.loveapp.doudianyun.service.api.domain.DditoOrderSearchItemDTO;
import cn.loveapp.doudianyun.service.api.request.DditoOrderSearchItemGetRequest;
import cn.loveapp.doudianyun.service.api.response.DditoOrderSearchItemGetResponse;
import cn.loveapp.doudianyun.service.service.TestService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhongzijie
 * @Date: 2023/4/11 10:55
 * @Description:
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/dditoOrderSearchItem.get")
    public DditoOrderSearchItemGetResponse dditoOrderSearchItemGet(@RequestBody DditoOrderSearchItemGetRequest request) {
        log.info("请求参数: [{}]", JSONObject.toJSONString(request));
        DditoOrderSearchItemGetResponse response = testService.dditoOrderSearchItemGet(request);
        return response;
    }
}

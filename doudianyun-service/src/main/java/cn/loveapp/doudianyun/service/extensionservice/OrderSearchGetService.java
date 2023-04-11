package cn.loveapp.doudianyun.service.extensionservice;

import cn.loveapp.doudianyun.service.api.request.DditoOrderSearchItemGetRequest;
import cn.loveapp.doudianyun.service.api.response.DditoOrderSearchItemGetResponse;
import cn.loveapp.doudianyun.service.service.TestService;
import com.jinritemai.cloud.base.api.BaseRequest;
import com.jinritemai.cloud.base.api.BaseResponse;
import com.jinritemai.cloud.base.api.ExtensionService;
import com.jinritemai.cloud.base.api.ExtensionServiceHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhongzijie
 * @Date: 2023/4/11 14:59
 * @Description: 示例 - 访问数据库查询订购记录表
 * @Description: 示例 - 访问redis
 */
@ExtensionService("dditoOrderSearchItem.get")
@Slf4j
public class OrderSearchGetService implements ExtensionServiceHandler<DditoOrderSearchItemGetRequest, DditoOrderSearchItemGetResponse> {

    @Autowired
    private TestService testService;

    /**
     * 如果要存取字符串数据，用stringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 如果要存取对象数据（复杂数据），用redisTemplate
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public BaseResponse<DditoOrderSearchItemGetResponse> handle(BaseRequest<DditoOrderSearchItemGetRequest> req) {
        // 往redis存一个key为testredis、value为hello的string类型数据，并设置10分钟的超时时间
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set("testredis", "hello", 10, TimeUnit.MINUTES);
        // 读取testredis的值
        String testredis = opsForValue.get("testredis");
        log.info("testredis: " + testredis);

        // 往redis存一个hash类型数据
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("test:redis", "k1", "v1");
        hashOperations.put("test:redis", "k2", "v2");
        // 读取hash类型数据
        Object k1 = hashOperations.get("test:redis", "k1");
        log.info("test:redis k1: " + k1);

        DditoOrderSearchItemGetRequest request = req.getData();
        DditoOrderSearchItemGetResponse response = testService.dditoOrderSearchItemGet(request);
        BaseResponse<DditoOrderSearchItemGetResponse> rsp = BaseResponse.<DditoOrderSearchItemGetResponse>builder()
                .success(true)
                .data(response)
                .build();
        return rsp;
    }
}

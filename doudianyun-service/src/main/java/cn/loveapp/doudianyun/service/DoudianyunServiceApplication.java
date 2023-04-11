package cn.loveapp.doudianyun.service;

import cn.loveapp.doudianyun.common.constant.ActiveProfileConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @Author: zhongzijie
 * @Date: 2023/4/10 16:08
 * @Description: 抖店云服务启动类
 */
@SpringBootApplication(scanBasePackages = {"cn.loveapp.doudianyun","com.jinritemai"})
@MapperScan("cn.loveapp.doudianyun.db.common.mapper")
public class DoudianyunServiceApplication {

    public static void main(String[] args) {
        initProfiles();
        defaultProfile();
        saveProfile();
        SpringApplication app = new SpringApplication(DoudianyunServiceApplication.class);
        app.run(args);
    }

    private static void saveProfile() {
        String activeProfile = System.getProperty("spring.profiles.active");
        ActiveProfileConstant.ACTIVE_PROFILE = activeProfile;
    }

    private static void defaultProfile() {
        String activeProfile = System.getProperty("spring.profiles.active");
        if (!StringUtils.hasText(activeProfile)) {
            System.setProperty("spring.profiles.active", "dev");
        }
    }

    private static void initProfiles() {
        String env = System.getenv("SPRING_PROFILES_ACTIVE");
        Optional.ofNullable(env).ifPresent((e) -> {
            System.setProperty("spring.profiles.active", env);
        });
    }
}

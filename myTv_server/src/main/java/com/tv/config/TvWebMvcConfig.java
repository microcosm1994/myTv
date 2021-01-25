package com.tv.config;

import com.tv.interceptor.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class TvWebMvcConfig extends WebMvcConfigurerAdapter {
    @Resource
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/user/register");
        excludePathList.add("/verify/getCode");
        registry.addInterceptor(adminInterceptor).excludePathPatterns(excludePathList);
        super.addInterceptors(registry);
    }
}

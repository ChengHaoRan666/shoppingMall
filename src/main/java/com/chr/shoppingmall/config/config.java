package com.chr.shoppingmall.config;

import com.chr.shoppingmall.interceptor.loginInterceptor;
import com.chr.shoppingmall.interceptor.sellerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 程浩然
 * @Create: 2024/12/9 - 17:40
 * @Description: 配置类
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.chr.shoppingmall.mapper")
public class config implements WebMvcConfigurer {

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/addCart")
                .excludePathPatterns("/addStar")
                .excludePathPatterns("/")
                .excludePathPatterns("/comments");
        registry.addInterceptor(new sellerInterceptor()).addPathPatterns("/seller/*");
    }
}

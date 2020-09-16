/*package com.net.chatroom;

import com.net.chatroom.web.interceptor.UserAuthInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfiguration implements WebMvcConfigurer {

    //    放行被拦截的资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        第一个方法里面的参数 表示的是在页面上引用的url地址
//        第二个方法里面的参数 表示的是当前资源具体的位置
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    //    注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAuthInteceptor()).addPathPatterns("/**")
                .excludePathPatterns("/hr/login")
                .excludePathPatterns("/index.html");
    }
}*/

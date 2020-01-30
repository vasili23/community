package work.flipped.community.config;

import work.flipped.community.controller.interceptor.LoginRequiredInterceptor;
import work.flipped.community.controller.interceptor.LoginTicketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    @Autowired
    private LoginRequiredInterceptor loginRequiredInterceptor;

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(alphaInterceptor); // 这样写拦截所有请求
        // registry.addInterceptor(alphaInterceptor).excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg","/**/*.jpeg"); // 会排除静态资源拦截
        registry.addInterceptor(loginTicketInterceptor).
                excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg","/**/*.jpeg");
        registry.addInterceptor(loginRequiredInterceptor).
                excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg","/**/*.jpeg");
    }
}

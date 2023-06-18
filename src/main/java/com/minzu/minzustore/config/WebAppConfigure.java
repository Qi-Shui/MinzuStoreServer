package com.minzu.minzustore.config;

import com.minzu.minzustore.interceptor.SysInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigure implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE","OPTIONS")
                .maxAge(3600);
    }

    @Value("${productImagesFilePath}")
    private String productImagesFilePath;

    @Value("${swiperImagesFilePath}")
    private String swiperImagesFilePath;

    @Value("${homeTypeImagesFilePath}")
    private String homeTypeImagesFilePath;

    @Value("${adminAvatarImagesFilePath}")
    private String adminAvatarImagesFilePath;

    @Value("${productsDetailImagesFilePath}")
    private String productsDetailImagesFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/swiper/**").addResourceLocations("file:"+swiperImagesFilePath);
        registry.addResourceHandler("/image/homeType/**").addResourceLocations("file:"+homeTypeImagesFilePath);
        registry.addResourceHandler("/image/product/**").addResourceLocations("file:"+productImagesFilePath);
        registry.addResourceHandler("/image/productDetail/**").addResourceLocations("file:"+productsDetailImagesFilePath);
        registry.addResourceHandler("/image/adminAvatar/**").addResourceLocations("file:"+adminAvatarImagesFilePath);
    }

    @Bean
    public SysInterceptor sysInterceptor() {
        return new SysInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = new String[] {"/adminLogin","/product/**","/homeType/**","/user/wxlogin","/weixinpay/**"};
        registry.addInterceptor(sysInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }

}

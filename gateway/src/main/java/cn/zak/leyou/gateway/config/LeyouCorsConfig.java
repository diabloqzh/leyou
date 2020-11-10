package cn.zak.leyou.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LeyouCorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        //设定跨域配置的config
        CorsConfiguration configuration = new CorsConfiguration();
        //设定Access-Control-Allow-Origin:
//        List<String> origin=new ArrayList<>();
//        origin.add("manage.leyou.com");
//        configuration.setAllowedOrigins(origin);
        configuration.addAllowedOrigin("http://manage.leyou.com");
        //设定Access-Control-Allow-Credentials
        configuration.setAllowCredentials(true);
        //设定Access-Control-Allow-Methods
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("HEAD");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        //设定Access-Control-Allow-Headers
        configuration.addAllowedHeader("*");
        //设定Access-Control-Max-Age
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource configurationSource =new UrlBasedCorsConfigurationSource();
        //配置路由适用什么配置
        configurationSource.registerCorsConfiguration("/**",configuration);
        CorsFilter corsFilter = new CorsFilter(configurationSource);
        return corsFilter;
    }

}

package com.parking.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.parking.constant.ConfigConstant;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Điều này chỉ áp dụng cho các URL bắt đầu bằng /api/
                .allowedOrigins("http://localhost:" + ConfigConstant.frontEndPort) // Cho phép frontend chạy trên http://localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Cho phép các phương thức này
                .allowedHeaders("Content-Type", "Authorization"); // Cho phép các tiêu đề này
        System.out.println("http://localhost:" + ConfigConstant.frontEndPort);
    }
}
//Trong ví dụ trên, chúng ta đã cấu hình CORS để cho phép frontend chạy trên http://localhost:3000 gửi các yêu cầu POST, GET, PUT, và DELETE với các tiêu đề "Content-Type" và "Authorization".
//
//Sau khi bạn đã thực hiện cấu hình CORS như trên, frontend của bạn sẽ có thể gửi các yêu cầu POST tới backend chạy trên http://localhost:8080 mà không gặp lỗi CORS.
//


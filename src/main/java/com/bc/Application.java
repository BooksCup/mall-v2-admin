package com.bc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-22 09:51
 **/
@MapperScan("com.bc.wd.mapper")
@SpringBootApplication(scanBasePackages = {"com.bc.wd"})
@ServletComponentScan("com.bc.wd")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

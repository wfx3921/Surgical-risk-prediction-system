package com.ouc.riskprediction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement//用于@Transactional注解
public class RiskPrediction {
    public static void main(String[] args) {
        SpringApplication.run(RiskPrediction.class,args);
        log.info("项目启动成功....");
    }
}

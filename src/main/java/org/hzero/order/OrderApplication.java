package org.hzero.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

import io.choerodon.resource.annoation.EnableChoerodonResourceServer;

/**
 * 启动类
 * @author 28255
 */
@RestController
@EnableDiscoveryClient
@EnableChoerodonResourceServer
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}

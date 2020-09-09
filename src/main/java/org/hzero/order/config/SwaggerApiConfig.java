package org.hzero.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger Api 描述配置
 */
@Configuration
public class SwaggerApiConfig {
    public static final String HZERO_ORDER = "HodrSo";
    public static final String HZERO_ORDER_HEADER = "hzeroOrderHeader";


    @Autowired
    public SwaggerApiConfig(Docket docket) {
        docket.tags(
                new Tag(HZERO_ORDER, "订单汇总"),
                new Tag(HZERO_ORDER_HEADER,"销售订单头")
        );
    }
}

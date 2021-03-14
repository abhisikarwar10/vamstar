package com.example.demo.Configuration;

import com.braintreegateway.BraintreeGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;


@Configuration
public class BraintreeGatewayFactory {

    @Value("${BT_ENVIRONMENT}")
    private String btEnvironment;
    @Value("${BT_MERCHANT_ID}")
    private String btMerchantId;
    @Value("${BT_PUBLIC_KEY}")
    private String btPublicKey;
    @Value("${BT_PRIVATE_KEY}")
    private String btPrivateKey;

    @Bean
    public BraintreeGateway fromConfigMapping() {
        return new BraintreeGateway(btEnvironment, btMerchantId, btPublicKey, btPrivateKey);
    }


}
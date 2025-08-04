package com.example.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Value("${gateway:stripe}")
    private String gateway;


    @Bean
    public PaymentService stripe(){
        return new StripePaymentService();
    }
    @Bean
    public PaymentService paypal(){
        return new PaypalPaymentService();
    }
    @Bean

    public OrderService orderService(){
        if(gateway.equals( "paypal")){
            return new OrderService(paypal());
        }
        else{
        return new OrderService(stripe());
        }}
}

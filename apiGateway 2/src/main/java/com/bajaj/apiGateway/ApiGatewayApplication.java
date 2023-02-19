package com.bajaj.apiGateway;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.bajaj.apiGateway.config.RedisHashComponent;
import com.bajaj.apiGateway.dto.ApiKey;
import com.bajaj.apiGateway.util.AppConstants;

import jakarta.annotation.PostConstruct;


@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {
	
	@Autowired
	RedisHashComponent redisHashComponent;
	
	@PostConstruct//after the instance is created it will be invoked
	public void initKeysToRedis() {
		List<ApiKey> lists = new ArrayList<>();
		lists.add(new ApiKey("343C-ED0B-4137-B27E", Stream.of(AppConstants.USER_SERVICE_KEY,
                AppConstants.REFERRAL_SERVICE_KEY,AppConstants.BUYINGDETAILS_SERVICE_KEY,AppConstants.CUSTOMER_SERVICE_KEY).collect(Collectors.toList())));
		lists.add(new ApiKey("343C-KE36-4937-B27E", Stream.of(AppConstants.USER_SERVICE_KEY).collect(Collectors.toList())));
		lists.add(new ApiKey("G543-HY67-8765-G5FE", Stream.of(AppConstants.REFERRAL_SERVICE_KEY).collect(Collectors.toList())));
		lists.add(new ApiKey("C654-DF56-2365-KJ12", Stream.of(AppConstants.CUSTOMER_SERVICE_KEY).collect(Collectors.toList())));
		lists.add(new ApiKey("I876-LO87-0945-LO87", Stream.of(AppConstants.BUYINGDETAILS_SERVICE_KEY).collect(Collectors.toList())));

		List<Object> lst = redisHashComponent.hValues(AppConstants.RECORD_KEY);
        if (lst.isEmpty()) {
            lists.forEach(k -> redisHashComponent.hSet(AppConstants.RECORD_KEY, k.getGatewayKey(), k));
        }
	}
	
	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(AppConstants.USER_SERVICE_KEY,
						r -> r.path("/api/user-service/**")
                        .filters(f -> f.stripPrefix(2)).uri("lb://User-Service"))
                .route(AppConstants.BUYINGDETAILS_SERVICE_KEY,
						r -> r.path("/api/productDetails-service/**")
                        .filters(f -> f.stripPrefix(2)).uri("lb://ProductDetails-Service"))
                .route(AppConstants.CUSTOMER_SERVICE_KEY,
						r -> r.path("/api/customer-service/**")
                        .filters(f -> f.stripPrefix(2)).uri("lb://Customer-Service"))
                .route(AppConstants.REFERRAL_SERVICE_KEY,
						r -> r.path("/api/referral-service/**")
                        .filters(f -> f.stripPrefix(2)).uri("lb://Referral-Service"))
                .build();
    }

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}

package com.bajaj.apiGateway.config;

import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.bajaj.apiGateway.util.MapperUtils;


@Component
public class RedisHashComponent {
	
//	@Autowired
    private final RedisTemplate<String,Object> redisTemplate;
    
    public RedisHashComponent(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
	
	public void hSet(String key,Object hashKey,Object value) {//value is object of ApiKey
		Map ruleHash= MapperUtils.objectMapper(value,Map.class);//hashkey is your gatewaykey in ApiKey
		redisTemplate.opsForHash().put(key,hashKey,ruleHash);//String key is Record_keys in APPConstant
	}
	
	public List<Object> hValues(String key){
        return  redisTemplate.opsForHash().values(key);
    }

    public Object hGet(String key,Object hashKey){
       return redisTemplate.opsForHash().get(key, hashKey);//get The Services Keys
    }
	

}

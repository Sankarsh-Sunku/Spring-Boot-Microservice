package com.bajaj.apiGateway.util;


import com.fasterxml.jackson.databind.ObjectMapper;


//@UtilityClass
public class MapperUtils {
	
	 static ObjectMapper mapper = new ObjectMapper();
	
	public static <T> T objectMapper(Object object,Class<T> contentClassType){
        return mapper.convertValue(object, contentClassType);
    }

	
	

}

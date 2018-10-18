package com.durain.bootu.ordermgmt.order.service.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object fromJson(String string, TypeReference<?> typeReference) {
		try {
			return objectMapper.readValue(string, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

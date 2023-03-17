package com.flightApplication.Admin.RestAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;

import com.flightApplication.Admin.Exception.CustomException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {
	@Override
	public Exception decode(String methodKey, Response response) {
		try {
			System.out.println("check");
			InputStream asInputStream = response.body().asInputStream();
			InputStreamReader isr = new InputStreamReader(asInputStream,StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            
      		br.lines().forEach(line -> builder.append(line));
      		JsonParser jsonParser = JsonParserFactory.getJsonParser();
      		HashMap<String, Object> parseMap = (HashMap<String, Object>) jsonParser.parseMap(builder.toString());
      		String message = parseMap.get("message").toString();
      		String status = parseMap.get("status").toString();
      		System.out.println("message "+message);
      		System.out.println("status "+status);
      		return new CustomException(message, HttpStatus.valueOf(response.status()));
		}
		catch (IOException e) {
//			System.out.println(e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

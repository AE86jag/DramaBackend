package com.spmystery.episode.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    //TODO 自动注入，自定义配置
    private RestTemplate restTemplate = new RestTemplate();

    public <T> T get(String url, Class<T> responseType) {
        return restTemplate.getForEntity(url, responseType).getBody();
    }
}

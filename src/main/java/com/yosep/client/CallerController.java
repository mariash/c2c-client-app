package com.yosep.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
public class CallerController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Value("${backend.url}")
    private String backendUrl;

    @GetMapping("/call")
    public String call() {

        System.out.println("URL: " + backendUrl);

        String response = restTemplate.getForObject(backendUrl + "/hello", String.class);
        return  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) + ", " + env.getProperty("vcap.application.instance_id") + ", " + response;
    }
}
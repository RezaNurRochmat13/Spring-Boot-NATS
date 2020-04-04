package com.spring.boot.natsstreams.module.controller.synchronous.publisher;

import com.spring.boot.natsstreams.module.configuration.NatsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class Publisher {
    @Autowired
    private NatsConfiguration natsConfiguration;

    @PostMapping("/publish")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> publishMessages() throws IOException, InterruptedException {
        Map<String, Object> map = new HashMap<>();
        String messages = "Data Order Baru";
        natsConfiguration
                .setupNatsConnection()
                .publish("orders", messages.getBytes());
        map.put("message", "Published successfully");
        return map;
    }
}

package com.spring.boot.natsstreams.module.controller.synchronous.subscriber;

import com.spring.boot.natsstreams.module.configuration.NatsConfiguration;
import io.nats.client.Message;
import io.nats.client.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class Subscriber {
    @Autowired
    private NatsConfiguration natsConfiguration;

    @GetMapping("/subscribe-messages")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> subscribeMessage() throws IOException, InterruptedException {
        Map<String, Object> map = new HashMap<>();
        Subscription subscription = natsConfiguration.setupNatsConnection()
                .subscribe("orders");
        Message message = subscription.nextMessage(Duration.ZERO);
        String messagesResponses = new String(message.getData());

        map.put("message", "Subsribed successfully");
        map.put("body", messagesResponses);
        return map;
    }
}

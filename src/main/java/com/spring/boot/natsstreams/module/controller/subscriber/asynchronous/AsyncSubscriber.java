package com.spring.boot.natsstreams.module.controller.subscriber.asynchronous;

import com.spring.boot.natsstreams.module.configuration.NatsConfiguration;
import io.nats.client.Dispatcher;
import io.nats.client.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class AsyncSubscriber {

    @Autowired
    private NatsConfiguration natsConfiguration;

    @Scheduled(fixedRate = 5000)
    public void asyncSubscription() throws IOException, InterruptedException {
        Dispatcher dispatcher = natsConfiguration
                .setupNatsConnection()
                .createDispatcher((msg) -> {});
        Subscription subscription = dispatcher.subscribe("orders", (msg) -> {
            String response = new String(msg.getData(), StandardCharsets.UTF_8);
            System.out.println("Receiving Message Asynchronously : " + response);
        });
        dispatcher.unsubscribe(subscription, 1);
    }
}

package com.spring.boot.natsstreams.module.controller.subscriber.synchronous;

import com.spring.boot.natsstreams.module.configuration.NatsConfiguration;
import io.nats.client.Message;
import io.nats.client.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;

@RestController
@RequestMapping("api")
public class SyncSubscriber {
    @Autowired
    private NatsConfiguration natsConfiguration;

    @RequestMapping("/sync-subscriber")
    public String synchronousSubscribeMessage() throws IOException, InterruptedException {
        Subscription subscription = natsConfiguration.setupNatsConnection()
                .subscribe("orders");
        Message message = subscription.nextMessage(Duration.ZERO);
        String messagesResponses = new String(message.getData());
        System.out.println("Received Message Synchronously : " + messagesResponses);
        return messagesResponses;
    }
}

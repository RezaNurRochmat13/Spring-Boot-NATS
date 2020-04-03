package com.spring.boot.natsstreams.module.controller.asynchronous.subscriber;

import com.spring.boot.natsstreams.module.configuration.NatsConfiguration;
import io.nats.client.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("api")
public class AsyncSubscriber {

    @Autowired
    private NatsConfiguration natsConfiguration;

    @Scheduled(fixedRate = 5000)
    public void asyncSubscription() {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Dispatcher dispatcher = natsConfiguration
                    .setupNatsConnection()
                    .createDispatcher((message -> {
                        String msg = new String(message.getData());
                        System.out.println("Receiving Messages Asynchronously : " + msg);
                        latch.countDown();
                    }));

            dispatcher.subscribe("orders");
            dispatcher.unsubscribe("orders");
            latch.wait();
            natsConfiguration.setupNatsConnection().close();
        } catch (Exception err) {
            System.out.println("Error catched : " + err.getMessage());
        }


    }
}

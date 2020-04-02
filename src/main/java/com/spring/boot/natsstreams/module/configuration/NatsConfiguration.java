package com.spring.boot.natsstreams.module.configuration;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NatsConfiguration {
    @Bean
    public Connection setupNatsConnection() throws IOException, InterruptedException {
        Connection connection = Nats.connect();
        return connection;
    }
}

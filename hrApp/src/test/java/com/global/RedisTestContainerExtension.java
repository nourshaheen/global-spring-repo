package com.global;

import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;

public class RedisTestContainerExtension implements BeforeAllCallback {

    private static AtomicBoolean started = new AtomicBoolean(false);

    private static GenericContainer redis = new GenericContainer("redis:6.2.6").withExposedPorts(6379);

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!started.get()) {
            redis.start();
            System.setProperty("jhipster.cache.redis.server", "redis://" + redis.getContainerIpAddress() + ":" + redis.getMappedPort(6379));
            started.set(true);
        }
    }
}

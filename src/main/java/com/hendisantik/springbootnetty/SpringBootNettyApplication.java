package com.hendisantik.springbootnetty;

import com.hendisantik.springbootnetty.server.NettyServer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationProperties(prefix = "netty")
@Setter
@Getter
public class SpringBootNettyApplication {

    private Boss boss;

    private Worker worker;

    private int port;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringBootNettyApplication.class, args);
        NettyServer bean = applicationContext.getBean(NettyServer.class);
        bean.start();
    }

    @Bean
    public NettyServer nettyServer() {
        return new NettyServer(boss.thread, worker.threads, port);
    }

    @Setter
    public static class Worker {
        private int threads;
    }

    @Setter
    public static class Boss {
        private int thread;
    }
}
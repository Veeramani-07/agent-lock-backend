package com.agentlock.agent_lock_backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"Repository", "repository", "com.agentlock"}) // Spring-a explicit-a scan panna vaikkum
@EntityScan(basePackages = {"Model", "model", "entity", "com.agentlock"})
public class AgentLockBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentLockBackendApplication.class, args);
    }
}
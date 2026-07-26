package com.agentlock.agent_lock_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.agentlock.agent_lock_backend", "controller", "config", "service", "Repository", "Entity", "dto"})
@EnableJpaRepositories(basePackages = {"Repository", "repository", "com.agentlock"})
@EntityScan(basePackages = {"Entity", "entity", "Model", "model", "com.agentlock"})
public class AgentLockBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentLockBackendApplication.class, args);
    }
}
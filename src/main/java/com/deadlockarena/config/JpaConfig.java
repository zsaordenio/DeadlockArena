package com.deadlockarena.config;

import com.deadlockarena.backend.persistence.domain.base.DeadlockArenaAuditorAware;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration targeting the JPA and transactions of database.
 *
 * @author Phong Vo
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EntityScan(basePackages = "com.deadlockarena.backend.persistence.domain")
@EnableJpaRepositories(basePackages = "com.deadlockarena.backend.persistence.repository")
public class JpaConfig {

  /**
   * A bean to be served for teh AuditorAware interface.
   *
   * @return auditorAware instance.
   */
  @Bean
  public AuditorAware<String> auditorAware() {
    return new DeadlockArenaAuditorAware();
  }
}

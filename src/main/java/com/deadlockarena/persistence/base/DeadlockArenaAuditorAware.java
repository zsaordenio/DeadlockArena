package com.deadlockarena.persistence.base;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * This class gets the application's current auditor.
 *
 * @author Phong Vo
 * @version 1.0
 * @since 1.0
 */
public class DeadlockArenaAuditorAware implements AuditorAware<String> {

  /**
   * Returns the current auditor of the application.
   *
   * @return the current auditor
   */
  @Override
  public Optional<String> getCurrentAuditor() {
    // the system will be used as the current auditor.
    return Optional.of("system");

  }
}

package com.deadlockarena.config.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * The DevelopmentConfig class handles all the configurations needed for development environment.
 *
 * @author Phong Vo
 * @version 1.0
 * @since 1.0
 */
@Configuration
@Profile(value = {"dev"})
public class DevelopmentConfig {
}

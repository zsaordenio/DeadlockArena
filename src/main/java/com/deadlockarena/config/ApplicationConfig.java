package com.deadlockarena.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * The ApplicationConfig class provides beans needed for all environments.
 *
 * @author Phong Vo
 * @version 1.0
 * @since 1.0
 */
@Configuration
@Profile(value = { "app" })
public class ApplicationConfig {
}

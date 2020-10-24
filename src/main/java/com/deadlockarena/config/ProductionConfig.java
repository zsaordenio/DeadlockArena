package com.deadlockarena.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * The ProductionConfig class handles all the configurations needed for
 * production environment.
 *
 * @author Phong Vo
 * @version 1.0
 * @since 1.0
 */
@Configuration
@Profile(value = { "prod" })
public class ProductionConfig {
}

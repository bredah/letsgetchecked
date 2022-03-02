package dev.bredah.config;

import org.aeonbits.owner.ConfigCache;

public final class ConfigurationFactory {

  private ConfigurationFactory() {}

  public static EnvironmentConfig environment() {
    return ConfigCache.getOrCreate(EnvironmentConfig.class);
  }
}

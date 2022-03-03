package dev.bredah.config;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
  "system:properties",
  "system:env",
  "classpath:grid.properties",
  "classpath:environment.properties",
})
public interface EnvironmentConfig extends Config {
  @Key("target")
  String target();

  @Key("browser")
  String browser();

  @Key("headless")
  Boolean headless();

  @Key("url_base")
  String url();

  @Key("timeout")
  int timeout();

  @Key("grid_url")
  String gridUrl();

  @Key("grid_port")
  String gridPort();
}

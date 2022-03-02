package dev.bredah.config;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
  "system:properties",
  "classpath:environment.properties",
  "classpath:grid.properties"
})
public interface EnvironmentConfig extends Config {
  @Key("target")
  String target();

  @Key("browser")
  String browser();

  @Key("headless")
  Boolean headless();

  @Key("url.base")
  String url();

  @Key("timeout")
  int timeout();

  @Key("grid.url")
  String gridUrl();

  @Key("grid.port")
  String gridPort();
}

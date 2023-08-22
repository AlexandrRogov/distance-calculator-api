package gmail.rohaualexandr.distancecalculatorapi.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "redis-config")
public class RedisProperties {
    private String redisHost;
    private int redisPort;
    private Duration timeout;
}

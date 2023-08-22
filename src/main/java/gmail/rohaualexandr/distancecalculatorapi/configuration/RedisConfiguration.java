package gmail.rohaualexandr.distancecalculatorapi.configuration;

import io.github.bucket4j.distributed.ExpirationAfterWriteStrategy;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class RedisConfiguration {

    private final RedisProperties redisProperties;
    private final BucketsProperties bucketsProperties;

    @Bean
    public RedisClient redisClient() {
        RedisURI redisURI = RedisURI.create(redisProperties.getRedisHost(), redisProperties.getRedisPort());
        redisURI.setTimeout(redisProperties.getTimeout());

        return RedisClient.create(redisURI);
    }

    @Bean
    public LettuceBasedProxyManager proxyManager(RedisClient redisClient) {
        return LettuceBasedProxyManager.
                builderFor(redisClient).
                withExpirationStrategy(
                        ExpirationAfterWriteStrategy.basedOnTimeForRefillingBucketUpToMax(
                                bucketsProperties.getBasedOnTimeForRefillingBucketUpToMax()
                        )
                )
                .build();
    }
}

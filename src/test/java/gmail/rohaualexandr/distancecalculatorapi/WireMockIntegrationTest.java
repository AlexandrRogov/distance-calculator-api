package gmail.rohaualexandr.distancecalculatorapi;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;


@SpringBootTest
@AutoConfigureMockMvc
public class WireMockIntegrationTest {

    static {
        GenericContainer<?> redis =
                new GenericContainer<>(DockerImageName.parse("redis:5"))
                        .waitingFor(Wait.defaultWaitStrategy())
                        .withExposedPorts(6379)
                        .withEnv("ALLOW_EMPTY_PASSWORD", "yes");
        redis.start();

        System.setProperty("redis-config.redisHost", redis.getHost());
        System.setProperty("redis-config.redisPort", redis.getMappedPort(6379).toString());
    }

    private static WireMockServer wireMockServer;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected RedisClient redisClient;

    @BeforeAll
    static void init() {
        wireMockServer = new WireMockServer(
                new WireMockConfiguration()
                        .port(7070)
        );
        wireMockServer.start();
        WireMock.configureFor("localhost", 7070);
    }

    @AfterEach
    public void flashAll() {
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        connect.sync().flushall();
        connect.close();
    }
}

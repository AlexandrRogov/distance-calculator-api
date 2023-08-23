package gmail.rohaualexandr.distancecalculatorapi;

import gmail.rohaualexandr.distancecalculatorapi.configuration.BucketsProperties;
import gmail.rohaualexandr.distancecalculatorapi.configuration.RedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
        RedisProperties.class,
        BucketsProperties.class
})
@SpringBootApplication
public class DistanceCalculatorApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistanceCalculatorApiApplication.class, args);
    }
}

package gmail.rohaualexandr.distancecalculatorapi;

import gmail.rohaualexandr.distancecalculatorapi.configuration.BucketsProperties;
import gmail.rohaualexandr.distancecalculatorapi.configuration.RedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableConfigurationProperties({
        RedisProperties.class,
        BucketsProperties.class
})
@EnableSwagger2
@SpringBootApplication
public class DistanceCalculatorApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistanceCalculatorApiApplication.class, args);
    }
}

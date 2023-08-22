package gmail.rohaualexandr.distancecalculatorapi.filter;

import gmail.rohaualexandr.distancecalculatorapi.configuration.BucketsProperties;
import gmail.rohaualexandr.distancecalculatorapi.controller.DistanceController;
import io.github.bucket4j.*;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@Component
public class IpThrottlingFilter extends GenericFilterBean {

    private final LettuceBasedProxyManager proxyManager;
    private final BucketsProperties bucketsProperties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!httpRequest.getRequestURI().contains(DistanceController.URL)) {
            chain.doFilter(request, response);
            return;
        }

        BucketConfiguration configuration = BucketConfiguration.builder()
                .addLimit(Bandwidth.simple(bucketsProperties.getCapacity(), bucketsProperties.getPeriod()))
                .build();
        Bucket bucket = proxyManager.builder().build(httpRequest.getRemoteAddr().getBytes(), configuration);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            chain.doFilter(request, response);
        } else {
            httpResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        }
    }
}

package gmail.rohaualexandr.distancecalculatorapi.controller;

import gmail.rohaualexandr.distancecalculatorapi.WireMockIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DistanceControllerTest extends WireMockIntegrationTest {

    @Test
    public void getCheckRestrictRequest() {
        String url = "/distance?latitude1=1&longitude1=1&latitude2=1&longitude2=1";
        IntStream.rangeClosed(1, 4)
                .boxed()
                .forEach(count -> mockMvcGet(url, HttpStatus.OK.value()));

        mockMvcGet(url, HttpStatus.TOO_MANY_REQUESTS.value());
    }

    @Test
    public void getBedRequest() {
        String url = "/distance?latitude1=1&longitude1=1&latitude2=1";

        mockMvcGet(url, HttpStatus.BAD_REQUEST.value());
    }

    private void mockMvcGet(String url, int httpStatus) {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get(url)
                            .header("Remote_Addr", "129.0.0.1")
                    )
                    .andExpect(status().is(httpStatus));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

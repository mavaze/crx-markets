package io.github.mavaze.crxmarkets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = MOCK)
public class ApiControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final static String urlUnderTest = "/v1/api/algorithm/find-water-volume";

    @Test
    public void shouldComputeWaterVolumeSuccess() throws Exception {

        mockMvc.perform(post(urlUnderTest)
                .contentType(APPLICATION_JSON_VALUE)
                .content("[7, 1, 8, 11, 7, 9, 12]")
                .accept(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.volume", is(12))
        );
    }

    @Test
    public void shouldFailComputeWaterVolumeOnInvalidInput() throws Exception {

        mockMvc.perform(post(urlUnderTest)
                .contentType(APPLICATION_JSON_VALUE)
                .content("7, 1, 8, 11, 7, 9, 12") // not an array representation
                .accept(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}

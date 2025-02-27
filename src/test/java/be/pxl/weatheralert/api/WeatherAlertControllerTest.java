package be.pxl.weatheralert.api;

import be.pxl.weatheralert.domain.impl.SmsSubscriber;
import be.pxl.weatheralert.domain.impl.WeatherAlert;
import be.pxl.weatheralert.service.WeatherAlertService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherAlertController.class)
public class WeatherAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherAlertService weatherAlertService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void subscribeTest() throws Exception {
        SmsSubscriber smsSubscriber = new SmsSubscriber("nickname", "123456");
        String json = objectMapper.writeValueAsString(smsSubscriber);

        mockMvc.perform(post("/weatheralert/subscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isAccepted());

        verify(weatherAlertService).addSubscriber(smsSubscriber);
    }


    @Test
    public void subscribeTestMissing() throws Exception {
        SmsSubscriber smsSubscriber = new SmsSubscriber("nickname", "");
        String json = objectMapper.writeValueAsString(smsSubscriber);

        mockMvc.perform(post("/weatheralert/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void unsubscribeTest() throws Exception {
        SmsSubscriber smsSubscriber = new SmsSubscriber("nickname", "123456");
        String json = objectMapper.writeValueAsString(smsSubscriber);

        mockMvc.perform(post("/weatheralert/unsubscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isAccepted());
        verify(weatherAlertService).removeSubscriber(smsSubscriber);
    }

    @Test
    public void createWeatherAlertTest() throws Exception {
        WeatherAlert weatherAlert = new WeatherAlert();
        String json = objectMapper.writeValueAsString(weatherAlert);

        mockMvc.perform(post("/weatheralert/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }
}

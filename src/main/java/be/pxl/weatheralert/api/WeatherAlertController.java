package be.pxl.weatheralert.api;

import be.pxl.weatheralert.domain.impl.SmsSubscriber;
import be.pxl.weatheralert.domain.impl.WeatherAlert;
import be.pxl.weatheralert.service.WeatherAlertService;
import be.pxl.weatheralert.service.WeatherAlertServiceWithSms;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weatheralert")
public class WeatherAlertController {

    private final WeatherAlertService weatherAlertService;

    @Autowired
    public WeatherAlertController(WeatherAlertService weatherAlertService) {
        this.weatherAlertService = weatherAlertService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(@RequestBody @Valid SmsSubscriber smsSubscriber) {
        weatherAlertService.addSubscriber(smsSubscriber);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<Void>  unsubscribe(@RequestBody @Valid SmsSubscriber smsSubscriber) {
        weatherAlertService.removeSubscriber(smsSubscriber);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/send")
    public ResponseEntity<Void> createWeatherAlert(@RequestBody @Valid WeatherAlert weatherAlert) {
        weatherAlertService.send(weatherAlert);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

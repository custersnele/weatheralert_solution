package be.pxl.weatheralert.service;

import be.pxl.weatheralert.domain.Alert;
import be.pxl.weatheralert.domain.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherAlertServiceWithSms {
	private final List<Subscriber> subscribers = new ArrayList<>();

	private final MessageService smsService;

	@Autowired
	public WeatherAlertServiceWithSms(MessageService smsService) {
		this.smsService = smsService;
	}

	public void addSubscriber(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	public void removeSubscriber(Subscriber subscriber) {
		subscribers.remove(subscriber);
	}

	public void send(Alert message) {
		subscribers.forEach(s -> smsService.sendMessage(s, message));
	}
}
